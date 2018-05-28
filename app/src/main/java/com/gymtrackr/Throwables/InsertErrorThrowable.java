package com.gymtrackr.Throwables;


public class InsertErrorThrowable extends Throwable {

    private String tableName;

    public InsertErrorThrowable(String tableName) {
        this.tableName = tableName;
    }
}
