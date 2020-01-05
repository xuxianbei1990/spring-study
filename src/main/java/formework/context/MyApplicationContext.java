package formework.context;

import formework.annotation.MyAutowired;
import formework.annotation.MyController;
import formework.annotation.MyService;
import formework.beans.MyBeanFactory;
import formework.beans.MyBeanWrapper;
import formework.beans.config.MyBeanDefinition;
import formework.beans.config.MyBeanPostProcessor;
import formework.beans.support.MyBeanDefinitionReader;
import formework.beans.support.MyDefaultListableBeanFactory;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-14
 * VersionV1.0
 * @description
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {

    private String[] configLocations;

    private MyBeanDefinitionReader reader;

    public MyApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public Object getBean(String beanName) {
        //初始化
        MyBeanWrapper myBeanWrapper = instantiateBean(beanName, beanDefinitionMap.get(beanName));

        MyBeanPostProcessor postProcessor = new MyBeanPostProcessor();
        postProcessor.postProcessBeforeInitialization(myBeanWrapper.getWrappedInstance(), beanName);

//        if (factoryBeanInstanceCache.containsKey(beanName)) {
//            throw new RuntimeException("beanName is exists");
//        }
        this.factoryBeanInstanceCache.put(beanName, myBeanWrapper);
        //注入
        populateBean(beanName, new MyBeanDefinition(), myBeanWrapper);

        return factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    @Override
    public Object getBean(Class clazz) {
        return getBean(clazz.getName());
    }

    private void populateBean(String beanName, MyBeanDefinition myBeanDefinition, MyBeanWrapper myBeanWrapper) {
        Object instance = myBeanWrapper.getWrappedInstance();
        Class<?> clazz = myBeanWrapper.getWrappedClass();
        if (!(clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class))) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }
            MyAutowired autowired = field.getAnnotation(MyAutowired.class);
            String autoWiredBeanName = autowired.value();
            if (StringUtils.isBlank(autoWiredBeanName)) {
                autoWiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autoWiredBeanName).getWrappedClass());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private MyBeanWrapper instantiateBean(String beanName, MyBeanDefinition myBeanDefinition) {
        //1、实例化对象的类名
        String className = myBeanDefinition.getBeanClassName();
        //2、反射实例化、得到一个对象
        Object instance = null;
        try {
            if (this.singletonObjects.containsKey(className)) {
                instance = this.singletonObjects.get(className);
            }
            if (this.singletonObjects.containsKey(myBeanDefinition.getFactoryBeanName())) {
                instance = this.singletonObjects.get(myBeanDefinition.getFactoryBeanName());
            }
            if (Objects.isNull(instance)) {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.singletonObjects.put(className, instance);
                this.singletonObjects.put(myBeanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、把这个对象封装到beanWrapper中
        MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);
        return beanWrapper;
    }

    @Override
    public void refresh() {
        //定位
        reader = new MyBeanDefinitionReader(configLocations);
        //加载
        List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        //注册
        doRegisterBeanDefinition(beanDefinitions);
        //把非延迟加载的类，提前初始化
        doAutowrited();
    }

    /**
     * 只处理非延迟加载
     */
    private void doAutowrited() {
        for (Map.Entry<String, MyBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            if (!entry.getValue().isLazyInit()) {
                getBean(entry.getKey());
            }
        }
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) {
        beanDefinitions.forEach(t -> {
            beanDefinitionMap.put(t.getFactoryBeanName(), t);
            beanDefinitionMap.put(t.getBeanClassName(), t);
        });
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }
}
