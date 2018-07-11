package ru.sbrf.jschool.socket.object;

import java.io.Serializable;

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
