package ru.otus.timonova.testing;

import ru.otus.timonova.annotations.After;
import ru.otus.timonova.annotations.Before;
import ru.otus.timonova.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTest {

    private static final Class<Before> BEFORE_ANNOTATION = Before.class;
    private static final Class<After> AFTER_ANNOTATION = After.class;
    private static final Class<Test> TEST_ANNOTATION = Test.class;

    public static void run(String className) {
        Class<?> clazz = getClazz(className);
        if (clazz == null) {
            return;
        }

        Method[] methodsAll = clazz.getDeclaredMethods();

        List<Method> beforeMethodList = getAnnotatedMethodList(methodsAll, BEFORE_ANNOTATION);
        List<Method> afterMethodList = getAnnotatedMethodList(methodsAll, AFTER_ANNOTATION);
        List<Method> testMethodList = getAnnotatedMethodList(methodsAll, TEST_ANNOTATION);

        int passedCount = 0;
        for (Method method : testMethodList) {
            boolean isPassed = runTest(clazz, method, beforeMethodList, afterMethodList);
            if (isPassed) {
                passedCount++;
                System.out.println("Тест пройден");
            } else {
                System.out.println("Тест не пройден");
            }
            System.out.println("");
        }

        System.out.println("Выполнено тестов:" + testMethodList.size());
        System.out.println("Успешно: " + passedCount);
        System.out.println("Не успешно: " + (testMethodList.size() - passedCount));
    }

    private static Class<?> getClazz(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.println("Класс не найден\n" + ex.getMessage());
            return null;
        }
    }

    private static List<Method> getAnnotatedMethodList(Method[] methodsAll, Class<? extends Annotation> searchAnnotation) {
        List<Method> methodList = new ArrayList<>();
        Arrays.stream(methodsAll).filter(method -> method.isAnnotationPresent(searchAnnotation)).forEach(method -> {
            methodList.add(method);
        });
        return methodList;
    }

    private static boolean runTest(Class<?> clazz, Method testMethod, List<Method> beforeMethodList, List<Method> afterMethodList) {
        System.out.println("Run test " + testMethod.getName());
        boolean isPassed = true;

        Object object = getTestingObject(clazz);
        if (object == null) {
            return false;
        }

        if (runBeforeAndAfter(object, beforeMethodList)) {
            isPassed = runMethod(object, testMethod, true);
        } else {
            isPassed = false;
        }
        runBeforeAndAfter(object, afterMethodList);

        return isPassed;
    }

    private static Object getTestingObject(Class<?> clazz) {
        try {
            return instantiate(clazz);
        } catch (RuntimeException ex) {
            System.out.println("Произошла ошибка при создании объекта класса:\n" + ex.getMessage());
            return null;
        }
    }

    private static boolean runBeforeAndAfter(Object object, List<Method> methodList) {
        boolean isPassed = true;
        for (Method method : methodList) {
            isPassed = runMethod(object, method, false);
        }
        return isPassed;
    }

    private static boolean runMethod(Object object, Method method, boolean isTest) {
        try {
            callMethod(object, method);
            return true;
        } catch (Exception ex) {
            if (isTest) {
                System.out.println(ex.getMessage());
            } else {
                System.out.println("Произошла ошибка при выполнении метода " + method.getName() + ":\n" + ex.getMessage());
            }
            return false;
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static Object callMethod(Object object, Method method, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
