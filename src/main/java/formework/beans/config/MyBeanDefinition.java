package formework.beans.config;

import lombok.Data;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-14
 * VersionV1.0
 * @description
 */
@Data
public class MyBeanDefinition {

    private String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;
}
