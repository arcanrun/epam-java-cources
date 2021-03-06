package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "beans")
public class BeanList {
    @XmlAnyElement(lax = true)
    private Collection<BeanDefinition> beanDefinitions;

    public Collection<BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }
}
