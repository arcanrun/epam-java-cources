package com.epam.university.java.core.task014;

public class VampireNumberImpl implements VampireNumber {
    private int first;
    private int second;
    private int multiplication;
    /**
     * VampireConstructor.
     */

    public VampireNumberImpl(int multiplication, int first, int second) {
        this.multiplication = multiplication;
        this.first = first;
        this.second = second;
    }

    @Override
    public int getMultiplication() {
        return multiplication;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object value) {
        if (this == value) {
            return true;
        }
        if (value == null || getClass() != value.getClass()) {
            return false;
        }
        VampireNumber vampireNumber = (VampireNumber) value;

        return ((vampireNumber.getFirst() * vampireNumber.getSecond()) == (this.first * this.second)
                && vampireNumber.getMultiplication() == multiplication);
    }

    @Override
    public String toString() {

        return multiplication + ": [" + first + ", " + second + "]\n";

    }
}
