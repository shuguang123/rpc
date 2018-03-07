package inner.peace.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.ServiceRegistry;

/**
 * ZookeeperServiceRegistry 服务注册容器
 *
 * @author
 * @create 2018-01-27 13:16
 **/
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperServiceRegistry.class);

    private final ZkClient zkClient;

    //创建zookeeper客户端
    public ZooKeeperServiceRegistry(String zkAddress) {

        zkClient= new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);

    }

    /***
     * 注册服务
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址
     */
    public void register(String serviceName, String serviceAddress) {

        //创建registry 节点
        String registryPath= Constant.ZK_REGISTRY_PATH;

        if(!zkClient.exists(registryPath)){
            zkClient.createPersistent(registryPath);
            logger.debug("create registry node: {}", registryPath);
        }

        // 创建 service 节点（持久）
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            logger.debug("create service node: {}", servicePath);
        }

        // 创建 address 节点（临时）
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        logger.debug("create address node: {}", addressNode);
    }
}
