package formework.context.support;

import formework.context.MyApplicationContext;

/**
 * Name
 * 通过解耦方式获得IoC容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口
 * 将自动调用setApplicationContext（）方法。
 *
 * @author xuxb
 * Date 2019-12-14
 * VersionV1.0
 * @description
 */
public interface MyApplicationContextAware {

    void setApplicationContext(MyApplicationContext applicationContext);
}
