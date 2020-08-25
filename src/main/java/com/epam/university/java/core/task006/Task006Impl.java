package com.epam.university.java.core.task006;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;

public class Task006Impl implements Task006 {
    //http://npm.mipt.ru/books/lab-intro/main.pdf
    @Override
    public double resistance(Collection<Measurement> measurements) {
        if (measurements == null) {
            throw new IllegalArgumentException();
        }
        if(measurements.isEmpty()){
            return 0.0;
        }

        double numerator = 0;
        double denominator = 0;
        double avgAmp = 0;
        double avgVolt = 0;

        for (Measurement m : measurements) {
            numerator += m.getAmperage() * m.getVoltage();
            denominator += Math.pow(m.getAmperage(), 2);
            avgAmp += m.getAmperage();
            avgVolt += m.getVoltage();
        }

        avgAmp = avgAmp / measurements.size();
        avgVolt = avgVolt / measurements.size();

        if(avgAmp == 0){
            return 0.0;
        }

        double res = ((numerator / measurements.size()) - (avgAmp * avgVolt)) / ((denominator / measurements.size()) - (Math.pow(avgAmp, 2)));
        res = Math.round(res * 1000);
        return res / 1000;
    }
}
