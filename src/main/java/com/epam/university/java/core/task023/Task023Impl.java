package com.epam.university.java.core.task023;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task023Impl implements Task023 {
    @Override
    public String extract(String phoneString) {
        if (phoneString == null) {
            throw new IllegalArgumentException();
        }
        Pattern pattern = Pattern.compile("(\\+{0,1}[78]\\s{0,1}\\({0,1}[\\s-]{0,1})"
                + "(\\d{3})(\\){0,1}[\\s-]{0,1}\\d{3})([\\s-]{0,1}\\d{2})([\\s-]{0,1}\\d{2})");
        Matcher matcher = pattern.matcher(phoneString);
        String res;
        if (matcher.find()) {
            res = matcher.group(2);
        } else {
            throw new IllegalArgumentException();
        }
        return res;
    }
}
