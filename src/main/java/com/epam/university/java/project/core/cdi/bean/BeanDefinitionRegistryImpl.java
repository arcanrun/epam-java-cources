package com.epam.university.java.project.core.cdi.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> registry = new HashMap<>();

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        try {
            registry.put(definition.getId(), definition);
            registry.put(definition.getClassName(), definition);

            Class<?>[] interfaces = Class.forName(definition.getClassName()).getInterfaces();

            for (Class<?> eachInterface : interfaces) {
                if (eachInterface != null) {
                    registry.put(eachInterface.getName(), definition);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return registry.get(beanId);
    }


}
