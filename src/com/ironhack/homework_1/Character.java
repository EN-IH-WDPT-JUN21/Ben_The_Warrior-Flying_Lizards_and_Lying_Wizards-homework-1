package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.List;
import  java.lang.reflect.*;
import java.util.Scanner;

public abstract class Character {
    /*
    id - unique identifier
    name - String
    hp - number representing the health points
    isAlive - flag to determine if the Player is alive
    */

    private int id;
    private String name;
    private double hp;
    private boolean isAlive;

    public static boolean hardcore = false;
    private static int idCount = 0;
    private static String[] classNames = {"Warrior", "Wizard"};
    private static Class[] possibleClasses = {Warrior.class, Wizard.class};

    public Character(){
        this.isAlive = true;
        setName();
        setId();
    }

    public Character(String name){
        this.isAlive = true;
        setName(name);
        setId();
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = ++idCount;
    }

    public String getName() {
        return name;
    }

    public void setName(){
        this.name = Names.randomName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(){
        this.hp = 100;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }



    abstract void attack(Character character);
    abstract void receiveDamage(double damage);
    abstract String printStats();

    public static Character getRandom() throws NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int rndClassIndex = (int) Math.floor(Math.random() * possibleClasses.length);
        Character charRnd = (Character) possibleClasses[rndClassIndex].newInstance();
        return charRnd;
    }

    public static Character createCustom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a class for your new Character: ");
        boolean chosen = false;
        while (chosen == false) {
            for (int i = 0; i < classNames.length; i++) {
                System.out.println((i + 1) + ". " + classNames[i]);
            }
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice){
                    case 1:
                        chosen = true;
                        return Warrior.createCustom();
                    case 2:
                        chosen = true;
                        return Wizard.createCustom();
                    default:
                        break;
                }

            }
            catch (NumberFormatException e){
                System.out.println("Please choose a valid option!");
            }
        }
        return null;
    }
}
