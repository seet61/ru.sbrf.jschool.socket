package ru.sbrf.jschool.socket.tcp;

import java.io.Serializable;

/**
 * Created by SBT-Pozdnyakov-AN on 02.07.2018.
 */
public class TestClass implements Serializable{
    private String field;

    public TestClass(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "field='" + field + '\'' +
                '}';
    }
}
