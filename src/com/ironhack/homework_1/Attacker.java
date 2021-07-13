package com.ironhack.homework_1;

public interface Attacker {
    String attack(Character character);
    String manualAttack(Character character);
    void receiveDamage(double damage);
}
