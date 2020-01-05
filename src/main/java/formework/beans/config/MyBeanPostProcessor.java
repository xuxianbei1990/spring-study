package formework.beans.config;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-21
 * VersionV1.0
 * @description
 */
public class MyBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
