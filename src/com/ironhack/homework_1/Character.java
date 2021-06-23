package com.ironhack.homework_1;

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

    private static int idCount = 0;

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
}
