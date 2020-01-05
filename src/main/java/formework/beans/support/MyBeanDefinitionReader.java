package formework.beans.support;

import formework.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-14
 * VersionV1.0
 * @description
 */
public class MyBeanDefinitionReader {

    private List<String> registryBeanClasses = new ArrayList<>();

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader(String... locations) {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(
                locations[0].replace("classpath:", ""));
        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String property) {
        URL url = this.getClass().getResource("/" + property.replaceAll("\\.", "/"));
        File classPath = null;
        try {
            classPath = new File(java.net.URLDecoder.decode(url.getFile(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(property + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (property + "." + file.getName().replace(".class", ""));
                registryBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return config;
    }

    //把配置文件中的class装换成beanDefinition
    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> result = new ArrayList<>();
        for (String className : registryBeanClasses) {
            MyBeanDefinition myBeanDefinition = doCreateBeanDefinition(className);
            if (Objects.isNull(myBeanDefinition)) {
                continue;
            }
            result.add(myBeanDefinition);
        }
        return result;
    }

    private MyBeanDefinition doCreateBeanDefinition(String className) {
        try {
            Class<?> beanClass = Class.forName(className);
            if (!beanClass.isInterface()) {
                MyBeanDefinition beanDefinition = new MyBeanDefinition();
                beanDefinition.setBeanClassName(className);
                beanDefinition.setFactoryBeanName(toLowerFirstCase(beanClass.getSimpleName()));
                return beanDefinition;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
