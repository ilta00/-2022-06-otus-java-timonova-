package main.java.ru.otus.timonova.testing;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testing {

    private static final String BEFORE_ANNOTATION = "@main.java.ru.otus.timonova.annotations.Before()";
    private static final String AFTER_ANNOTATION = "@main.java.ru.otus.timonova.annotations.After()";
    private static final String TEST_ANNOTATION = "@main.java.ru.otus.timonova.annotations.Test()";

    public static void run(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.println("Класс не найден\n" + ex.getMessage());
            return;
        }
        Method[] methodsAll = clazz.getDeclaredMethods();

        List<String> beforeMethodList = getAnnotatedMethodListByName(methodsAll, BEFORE_ANNOTATION);
        List<String> afterMethodList = getAnnotatedMethodListByName(methodsAll, AFTER_ANNOTATION);
        List<String> testMethodList = getAnnotatedMethodListByName(methodsAll, TEST_ANNOTATION);

        int passedCount = 0;
        int failCount = 0;
        for (String methodName : testMethodList) {
            boolean isPassed = runTest(clazz, methodName, beforeMethodList, afterMethodList);
            if (isPassed) {
                passedCount++;
                System.out.println("Тест пройден");
            } else {
                failCount++;
                System.out.println("Тест не пройден");
            }
            System.out.println("");
        }

        System.out.println("Выполнено тестов:" + testMethodList.size());
        System.out.println("Успешно: " + passedCount);
        System.out.println("Не успешно: " + failCount);
    }

    private static List<String> getAnnotatedMethodListByName(Method[] methodsAll, String searchAnnotationName) {
        List<String> methodList = new ArrayList<>();
        Arrays.stream(methodsAll).forEach(method -> {
            Annotation[] annotations = method.getDeclaredAnnotations();
            Arrays.stream(annotations).forEach(annotation -> {
                String annotationName = annotation.toString();
                if (annotationName.equals(searchAnnotationName)) {
                    methodList.add(method.getName());
                }
            });
        });
        return methodList;
    }

    private static boolean runTest(Class<?> clazz, String testName, List<String> beforeMethodList, List<String> afterMethodList) {
        System.out.println("Run test " + testName);
        boolean isPassed = true;

        Object object = null;
        try {
            object = instantiate(clazz);
        } catch (RuntimeException ex) {
            System.out.println("Произошла ошибка при создании объекта класса:\n" + ex.getMessage());
            return false;
        }

        runBeforeAndAfter(object, beforeMethodList);
        isPassed = runTest(object, testName);
        runBeforeAndAfter(object, afterMethodList);

        return isPassed;
    }

    private static boolean runTest(Object object, String testName) {
        try {
            callMethod(object, testName);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static void runBeforeAndAfter(Object object, List<String> methodList) {
        Object finalObject = object;
        for (String methodName : methodList) {
            runMethod(object, methodName);
        }
    }

    private static void runMethod(Object object, String methodName) {
        try {
            callMethod(object, methodName);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка при выполнении метода " + methodName + ":\n" + ex.getMessage());
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

    public static Object callMethod(Object object, String name, Object... args) {
        try {
            var method = object.getClass().getDeclaredMethod(name, toClasses(args));
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
