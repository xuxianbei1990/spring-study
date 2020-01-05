package formework.beans;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-14
 * VersionV1.0
 * @description
 */
public interface MyBeanFactory {

    /**
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

    Object getBean(Class clazz);
}
