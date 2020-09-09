package com.epam.university.java.core.task025;

public class Task025Impl implements Task025 {
    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage == null) {
            throw new IllegalArgumentException();
        }
        int lengthSourceMsg = sourceMessage.length();
        if (lengthSourceMsg % 3 != 0) {
            while (lengthSourceMsg % 3 != 0) {
                lengthSourceMsg += 1;
            }
        }
        String correctMsg = "SOS".repeat(lengthSourceMsg / 3);
        correctMsg = correctMsg.substring(0, sourceMessage.length());
        int count = 0;
        for (int i = 0; i < sourceMessage.length(); i++) {
            if (correctMsg.charAt(i) != sourceMessage.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
