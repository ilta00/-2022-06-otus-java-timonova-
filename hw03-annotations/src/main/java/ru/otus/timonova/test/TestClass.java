package main.java.ru.otus.timonova.test;

import main.java.ru.otus.timonova.annotations.After;
import main.java.ru.otus.timonova.annotations.Before;
import main.java.ru.otus.timonova.annotations.Test;

public class TestClass {

    @Before
    void before1() {
        System.out.println("before1");
    }

    @Before
    void before2() {
        System.out.println("before2");
    }

    @Test
    void test1_PASS() {
        System.out.println("test1_PASS");
    }

    @Test
    void test2_FAIL(){
        System.out.println("test2_FAIL");
        throw new RuntimeException("");
    }

    @Test
    void test3_PASS() {
        System.out.println("test3_PASS");
    }

    @After
    void after1() {
        System.out.println("after1");
    }

    @After
    void after2() {
        System.out.println("after2");
    }
}
