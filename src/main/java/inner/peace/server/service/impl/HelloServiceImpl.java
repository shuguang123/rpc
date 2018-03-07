package inner.peace.server.service.impl;

import inner.peace.common.Person;
import inner.peace.server.annotation.RpcService;
import inner.peace.server.service.HelloService;

/**
 * HelloService 的实现类
 *
 * @author
 * @create 2018-01-27 12:55
 **/
@RpcService(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "Hello! " + name;
    }

    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
