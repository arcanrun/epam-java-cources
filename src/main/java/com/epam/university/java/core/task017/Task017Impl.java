package com.epam.university.java.core.task017;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Task017Impl implements Task017 {
    @Override
    public String formatString(Object... args) {
        checkArgs(args);
        return String.format("You know %s, %s!", args[0], args[1]);
    }

    @Override
    public String formatNumbers(Object... args) {
        checkArgs(args);
        return String.format(Locale.ENGLISH, "%1$.1f, %1$.2f, %1$+.2f, %1$a", args[0]);
    }

    @Override
    public String formatDates(Object... args) {
        checkArgs(args);
        return new SimpleDateFormat("yyyy.dd.MM").format(args[0]);
    }

    /**
     * Check if args is valid.
     *
     * @param args anything
     */
    private void checkArgs(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
        }
    }

}
