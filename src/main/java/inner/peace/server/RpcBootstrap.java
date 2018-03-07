package inner.peace.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Server 端服务的启动类
 *
 * @author
 * @create 2018-01-27 13:09
 **/
public class RpcBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcBootstrap.class);

    public static void main(String[] args) {
        System.out.println("aaa");
        LOGGER.debug("start server");
        new ClassPathXmlApplicationContext("rpc-server.xml");
    }
}
