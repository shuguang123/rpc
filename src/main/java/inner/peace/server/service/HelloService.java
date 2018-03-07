package inner.peace.server.service;

import inner.peace.common.Person;

/**
 * 普通的service
 *
 * @author
 * @create 2018-01-27 12:49
 **/
public interface HelloService {
    String hello(String name);
    String hello (Person person);
}
