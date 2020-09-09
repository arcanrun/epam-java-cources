package com.epam.university.java.core.task020;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Task020Impl implements Task020 {
    @Override
    public int calculate(Collection<String> stones) {
        if (stones == null || stones.size() == 0) {
            throw new IllegalArgumentException();
        }

        return stones.stream()
                .map(e -> Arrays.stream(e.split("")).collect(Collectors.toSet()))
                .reduce((a, b) -> {
                    a.retainAll(b);
                    return a;
                }).get().size();
    }
}
