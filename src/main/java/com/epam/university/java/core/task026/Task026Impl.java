package com.epam.university.java.core.task026;

public class Task026Impl implements Task026 {
    @Override
    public String encrypt(String sourceString, int shift) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sourceString.length(); i++) {
            char c = sourceString.charAt(i);
            if (!Character.isAlphabetic(c)) {
                res.append(c);
                continue;
            }
            if (Character.isUpperCase(c)) {
                res.append(
                        (char) ((c - 'A' + shift) % 26 + 'A')
                );
            }
            if (Character.isLowerCase(c)) {
                res.append((char) ((c - 'a' + shift) % 26 + 'a'));
            }
        }

        return res.toString();
    }

    @Override
    public String decrypt(String encryptedString, int shift) {
        return encrypt(encryptedString, 26 - shift);
    }
}
