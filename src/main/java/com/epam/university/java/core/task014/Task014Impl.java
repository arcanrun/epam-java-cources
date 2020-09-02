package com.epam.university.java.core.task014;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Task014Impl implements Task014 {

    @Override
    public Collection<VampireNumber> getVampireNumbers() {
        Collection<VampireNumber> res = new ArrayList<>();
        for (int i = 10; res.size() < 7; i++) {
            if ((numDigits(i) % 2) != 0) {
                i = i * 10 - 1;
                continue;
            }
            for (int fang1 = 2; fang1 <= Math.sqrt(i) + 1; fang1++) {
                if (i % fang1 == 0) {
                    int fang2 = i / fang1;
                    if (fangCheck(i, fang1, fang2) && fang1 <= fang2) {
                        res.add(new VampireNumberImpl(i, fang1, fang2));
                    }
                }
            }
        }
        return res;
    }

    private int numDigits(long num) {
        return Long.toString(Math.abs(num)).length();
    }

    private boolean fangCheck(int orig, int fang1, int fang2) {
        if (Long.toString(fang1).endsWith("0") && Long.toString(fang2).endsWith("0")) return false;

        int origLen = numDigits(orig);
        if (numDigits(fang1) != origLen / 2 || numDigits(fang2) != origLen / 2) return false;

        byte[] origBytes = Long.toString(orig).getBytes();
        byte[] fangBytes = (Long.toString(fang1) + Long.toString(fang2)).getBytes();
        Arrays.sort(origBytes);
        Arrays.sort(fangBytes);
        return Arrays.equals(origBytes, fangBytes);
    }
}
