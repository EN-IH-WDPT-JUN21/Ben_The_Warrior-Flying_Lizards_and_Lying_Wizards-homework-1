package com.ironhack.homework_1;

abstract class Character {
    /*
    id - unique identifier
    name - String
    hp - number representing the health points
    isAlive - flag to determine if the Player is alive
    */

    private int id;
    private String name;
    private int hp;
    private boolean isAlive;

    abstract void attack(Character character);
}
