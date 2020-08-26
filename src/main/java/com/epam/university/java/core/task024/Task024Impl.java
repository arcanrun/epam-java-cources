package com.epam.university.java.core.task024;

import java.util.Collection;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Task024Impl implements Task024 {
    @Override
    public Collection<String> getWordsCount(String source) {
        if (source.length() == 0) {
            return Collections.emptyList();
        }
        return Arrays
                .stream(source.split("(?=\\p{Lu})"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
