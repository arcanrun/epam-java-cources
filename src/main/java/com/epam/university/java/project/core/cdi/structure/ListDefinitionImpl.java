package com.epam.university.java.project.core.cdi.structure;

import javax.xml.bind.annotation.XmlElement;
import java.util.Collection;

public class ListDefinitionImpl implements ListDefinition{

    //@XmlElementWrapper(name = "list")
    @XmlElement(name = "value", type = ListItemDefinitionImpl.class)
    Collection<ListItemDefinition> items;

    @Override
    public Collection<ListItemDefinition> getItems() {
        return items;
    }

    @Override
    public void setItems(Collection<ListItemDefinition> items) {
        this.items = items;
    }

}
