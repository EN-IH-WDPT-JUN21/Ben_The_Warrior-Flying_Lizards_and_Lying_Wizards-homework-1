package com.ironhack.homework_1;

public interface Attacker {
    public String attack(Character character);
    public String manualAttack(Character character);
    public void receiveDamage(double damage);
}
