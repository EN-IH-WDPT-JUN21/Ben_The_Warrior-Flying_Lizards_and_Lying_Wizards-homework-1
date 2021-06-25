package com.ironhack.homework_1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Warrior extends Character{
    /*stamina - number to represent a resource the warrior consumes to make an attack
    strength - number to calculate how strong the warrior attack is*/
    private int stamina;
    private int strength;

    public Warrior() {
        super();
        setStrength(1 + (int)(Math.random() * 9 + 1));
        setStamina(10 + (int)(Math.random() * 40 + 1));
        setHp(100 + (int)(Math.random() * 100 + 1));
    }

    private Warrior(String name, int str, int stam, int hp){
        super(name);
        setStrength(str);
        setStamina(stam);
        setHp(hp);
    }

    public static Warrior createCustom(){
        if (Character.hardcore == true) {
            Scanner scanner = new Scanner(System.in);
            int upgradePoints = 10;
            int str = 5;
            int stam = 30;
            int hp = 150;
            System.out.println("What would you like to call your Warrior?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                System.out.println(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                System.out.println("1. Increase Strength: " + str + " => " + (str + 1));
                System.out.println("2. Increase Stamina: " + stam + " => " + (stam + 5));
                System.out.println("3. Increase Hit Points: " + hp + " => " + (hp + 10));
                String input = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            str++;
                            upgradePoints--;
                            break;
                        case 2:
                            stam += 5;
                            upgradePoints--;
                            break;
                        case 3:
                            hp += 10;
                            upgradePoints--;
                            break;
                        default:
                            System.out.println("Please choose a valid option!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please choose a valid option!");
                }
            }
            return new Warrior(name, str, stam, hp);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int str = 0;
            int stam = 0;
            int hp = 0;
            boolean validChoice = false;
            System.out.println("What would you like to call your Warrior?");
            String name = scanner.nextLine();
            while (validChoice == false){
                System.out.println("Please enter a value for strength between 1 and 10");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    if (choice >= 1 && choice <= 10){
                        str = choice;
                        validChoice = true;
                    }
                    else {
                        System.out.println("Please enter a valid number");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Please enter a valid number");
                }
            }
            validChoice = false;
            while (validChoice == false){
                System.out.println("Please enter a value for stamina between 10 and 50");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    if (choice >= 10 && choice <= 50){
                        stam = choice;
                        validChoice = true;
                    }
                    else {
                        System.out.println("Please enter a valid number");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Please enter a valid number");
                }
            }
            validChoice = false;
            while (validChoice == false){
                System.out.println("Please enter a value for hp between 100 and 200");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    if (choice >= 100 && choice <= 200){
                        hp = choice;
                        validChoice = true;
                    }
                    else {
                        System.out.println("Please enter a valid number");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Please enter a valid number");
                }
            }
            return new Warrior(name, str, stam, hp);
        }
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void attack(Character character) {
        if (this.stamina >= 5){
            character.receiveDamage(this.strength);
            this.stamina -= 5;
        }
        else {
            character.receiveDamage(this.strength / 2.0);
            this.stamina++;
        }
    }

    public void receiveDamage(double damage){
        setHp(getHp() - damage);
        if (getHp() <= 0){
            setAlive(false);
        }
    }

    public String printStats(){
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Strength: " + this.getStrength() + ", Stamina: " + this.getStamina() + ", Hp: " + (int) this.getHp() + ".";
    }
}
