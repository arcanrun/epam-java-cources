package com.epam.university.java.core.task025;

public class Task025Impl implements Task025 {
    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        String correctMsg = "SOS".repeat(sourceMessage.length() / 3);
        int count = 0;
        for (int i = 0; i < sourceMessage.length(); i++) {
            if (correctMsg.charAt(i) != sourceMessage.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
