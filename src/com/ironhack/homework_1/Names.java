package com.ironhack.homework_1;

public class Names {
    private static String[] nameList = {"Ben"};

    public static String randomName() {
        return nameList[(int) Math.floor(Math.random() * nameList.length)];
    }
}