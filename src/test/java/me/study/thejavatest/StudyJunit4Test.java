package me.study.thejavatest;

import org.junit.Before;
import org.junit.Test;

public class StudyJunit4Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void test() {
        System.out.println("test");
    }
}
