package com.epam.university.java.core.task018;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Task018Impl implements Task018 {

    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {
        if (toCheck == null || annotationToFind == null) {
            throw new IllegalArgumentException();
        }

        return isAnnotMethod(toCheck, annotationToFind)
                || isAnnotPackage(toCheck, annotationToFind)
                || isAnnotConstructor(toCheck, annotationToFind)
                || isAnnotClass(toCheck, annotationToFind)
                || isAnnotField(toCheck, annotationToFind)
                || isAnnotMethodArg(toCheck, annotationToFind);
    }

    private boolean isAnnotMethodArg(Object toCheck, Class<?> annotationToFind) {
        Method[] methods = toCheck.getClass().getMethods();
        for (Method m : methods) {
            for (Parameter p : m.getParameters()) {
                for (Annotation a : p.getAnnotations()) {
                    if (a.annotationType().equals(annotationToFind)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isAnnotField(Object toCheck, Class<?> annotationToFind) {
        Field[] fields = toCheck.getClass().getDeclaredFields();
        for (Field f : fields) {
            for (Annotation a : f.getAnnotations()) {
                if (a.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAnnotClass(Object toCheck, Class<?> annotationToFind) {
        for (Annotation a : toCheck.getClass().getAnnotations()) {
            if (a.annotationType().equals(annotationToFind)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnnotConstructor(Object toCheck, Class<?> annotationToFind) {
        Constructor[] constructors = toCheck.getClass().getConstructors();
        for (Constructor c : constructors) {
            for (Annotation a : c.getAnnotations()) {
                if (a.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAnnotPackage(Object toCheck, Class<?> annotationToFind) {
        Annotation[] annotations = toCheck.getClass().getPackage().getAnnotations();
        for (Annotation a : annotations) {
            if (a.annotationType().equals(annotationToFind)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnnotMethod(Object toCheck, Class<?> annotationToFind) {
        Method[] methods = toCheck.getClass().getMethods();
        for (Method m : methods) {
            for (Annotation a : m.getAnnotations()) {
                if (a.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        return false;
    }

}
