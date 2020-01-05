package formework.test;

import formework.context.MyApplicationContext;
import formework.demo.controller.TestController;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-21
 * VersionV1.0
 * @description
 */
public class TestMySpring {

    public static void main(String[] args) {
        MyApplicationContext myApplicationContext = new MyApplicationContext("classpath:application.properties");
        Object object = myApplicationContext.getBean("testController");
        Object object2 = myApplicationContext.getBean(TestController.class);
        System.out.println(object + ":" + object2);
    }
}
