package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public BeanDefinitionReaderImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(BeanDefinitionImpl.class,
                    BeanPropertyDefinitionImpl.class, BeanList.class).createUnmarshaller();
            BeanList beanList =
                    (BeanList) unmarshaller.unmarshal(resource.getFile());
            Collection<BeanDefinition> beanDefinitions = beanList.getBeanDefinitions();

            for (BeanDefinition d : beanDefinitions) {
                beanDefinitionRegistry.addBeanDefinition(d);
            }
            return beanDefinitions.size();
        } catch (JAXBException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        int sum = 0;
        for (Resource res : resources) {
            sum += loadBeanDefinitions(res);
        }
        return sum;
    }
}
