package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.StructureDefinition;
import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.ListDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.MapDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;


import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class BeanFactoryImpl implements BeanFactory {
    private BeanDefinitionRegistry beanDefinitionRegistry;
    private Map<BeanDefinition, Object> singletons = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }


    @Override
    public <T> T getBean(Class<T> beanClass) {
        return (T) getBean(beanClass.getName());
    }


    @Override
    public Object getBean(String beanName) {
        return getBean(beanDefinitionRegistry.getBeanDefinition(beanName));
    }


    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }

    /**
     * aaa.
     * @param definition asd.
     * @param <T> asd.
     * @return asd.
     */
    public <T> T getBean(BeanDefinition definition) {
        try {
            Class<T> beanClass = (Class<T>) Class.forName(definition.getClassName());
            String beanScope = definition.getScope();
            T instance;

            if ("singleton".equals(beanScope) && singletons.containsKey(definition)) {
                instance = (T) singletons.get(definition);
                return instance;
            } else {
                instance = beanClass.getDeclaredConstructor().newInstance();
                if ("singleton".equals(beanScope)) {
                    singletons.put(definition, instance);
                }
            }

            Collection<BeanPropertyDefinition> properties = definition.getProperties();
            if (properties == null) {
                return instance;
            } else {
                checkProperties(properties);
            }

            for (BeanPropertyDefinition property : properties) {
                Field beanField = beanClass.getDeclaredField(property.getName());
                beanField.setAccessible(true);

                String propertyValue = property.getValue();
                if (propertyValue != null) {
                    if (propertyValue.matches("\\d+")) {
                        beanField.set(instance, Integer.parseInt(property.getValue()));
                    } else {
                        beanField.set(instance, property.getValue());
                    }
                }

                String propertyRef = property.getRef();
                if (propertyRef != null) {
                    beanField.set(instance, getBean(propertyRef));
                }

                StructureDefinition propertyData = property.getData();
                if (propertyData != null) {
                    if (propertyData.getClass() == ListDefinitionImpl.class) {
                        List<String> items = new ArrayList<>();
                        for (ListDefinition.ListItemDefinition itemDefinition
                                : ((ListDefinition) propertyData).getItems()) {
                            if (itemDefinition.getValue() != null) {
                                items.add(itemDefinition.getValue());
                            }
                        }
                        beanField.set(instance, items);
                    }

                    if (propertyData.getClass() == MapDefinitionImpl.class) {
                        Map<String, Object> entries = new HashMap<>();
                        for (MapDefinition.MapEntryDefinition entryDefinition
                                : ((MapDefinition) propertyData).getValues()) {
                            if (entryDefinition.getValue() != null
                                    && entryDefinition.getRef() != null) {
                                throw new RuntimeException();
                            }

                            if (entryDefinition.getValue() != null) {
                                entries.put(entryDefinition.getKey(), entryDefinition.getValue());
                            }

                            if (entryDefinition.getRef() != null) {
                                entries.put(entryDefinition.getKey(),
                                        getBean(entryDefinition.getRef()));
                            }
                        }
                        beanField.set(instance, entries);
                    }
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkProperties(Collection<BeanPropertyDefinition> properties) {
        for (BeanPropertyDefinition property : properties) {
            if (property.getValue() == null
                    && property.getRef() == null
                    && property.getData() == null) {
                throw new RuntimeException();
            }
        }
    }
}
