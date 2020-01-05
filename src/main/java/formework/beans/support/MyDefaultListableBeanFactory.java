package formework.beans.support;

import formework.beans.MyBeanWrapper;
import formework.beans.config.MyBeanDefinition;
import formework.context.support.MyAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-14
 * VersionV1.0
 * @description
 */
public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {

    protected Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    /**
     * 单例的ioc容器
     */
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 通用IOC容器
     */
    protected Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();
}
