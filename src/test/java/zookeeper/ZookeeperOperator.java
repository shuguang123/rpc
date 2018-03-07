package zookeeper;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * zookeeper操作类
 *
 * @author Aurora
 * @create 2018-02-08 22:42
 **/
public class ZookeeperOperator {

    private static  final int SESSION_TIMEOUT=5000;

    private static  final  String address="localhost:2181";

    private  ZooKeeper zk;
    private Watcher watcher = new Watcher() {
        public void process(WatchedEvent event) {
            System.out.println(event.toString());
        }
    };

    @Before
    public void init() throws IOException {
        zk = new ZooKeeper(address,SESSION_TIMEOUT,watcher);
    }

    @Test
    public void createNode() throws KeeperException, InterruptedException, UnsupportedEncodingException {

        System.out.println("1.创建ZooKeeper节点(znode:zoo2,数据:myData2,权限:OPEN_ACL_UNSAFE,节点类型:Persistent)");
        zk.create("/zoo2","myData2".getBytes("UTF-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getData() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        System.out.println("是否创建成功");
        System.out.println(new String(zk.getData("/zoo2",false,null)));
    }

    @Test
    public void setData() throws KeeperException, InterruptedException {
        System.out.println("3.修改节点数据:");
        zk.setData("/zoo2", "xiugai1234".getBytes(), -1);
    }

    @Test
    public void getSetData() throws KeeperException, InterruptedException {
        System.out.println("4.查看是否修改成功:");
        System.out.println(new String(zk.getData("/zoo2", false, null)));
    }

    @Test
    public void delete() throws InterruptedException, KeeperException {
        System.out.println("5.删除节点");
        zk.delete("/zoo2", -1);
    }

    @Test
    public void getStat() throws KeeperException, InterruptedException {
        //使用 exists函数时，如果节点不存在将返回一个nul值
        System.out.println("6.查看节点是否被删除:");
        System.out.println("节点状态:["+zk.exists("/zoo2", false)+"]");
    }
}
