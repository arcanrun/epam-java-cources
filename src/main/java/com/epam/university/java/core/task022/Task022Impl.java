package com.epam.university.java.core.task022;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task022Impl implements Task022 {
    @Override
    public int maxSum(Collection<Integer> numbers) {
        checkIsOk(numbers);
        return numbers.stream().sorted().skip(1).mapToInt(e -> e).sum();
    }

    @Override
    public int minSum(Collection<Integer> numbers) {
        checkIsOk(numbers);
        return numbers.stream().sorted().limit(numbers.size() - 1).mapToInt(e -> e).sum();
    }

    private void checkIsOk(Collection<Integer> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
