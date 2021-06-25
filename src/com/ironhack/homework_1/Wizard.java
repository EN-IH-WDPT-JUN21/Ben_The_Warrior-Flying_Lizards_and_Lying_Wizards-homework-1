package com.ironhack.homework_1;

import java.util.Scanner;

public class Wizard extends Character{
    /*
    mana - number to represent a resource the wizard consumes to cast spells
    intelligence - number to calculate how strong the wizard spells are
    */
    private int mana;
    private int intelligence;

    public Wizard(){
        super();
        mana = 10 + (int)(Math.random() * 40 + 1);
        intelligence = 1 + (int)(Math.random() * 49 + 1);
        setHp(50 + (int)(Math.random() * 50 + 1));
    }

    private Wizard(String name, int intel, int mana, int hp){
        super(name);
        setIntelligence(intel);
        setMana(mana);
        setHp(hp);
    }

    private static int statInput(int statMin, int statMax, String message){
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        while (validChoice == false){
            System.out.println(message);
            String tmp = scanner.nextLine();
            try {
                int choice = Integer.parseInt(tmp);
                if (choice >= statMin && choice <= statMax){
                    return choice;
                }
                else {
                    System.out.println("Please enter a valid number");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
            }
        }
        return statMin;
    }

    public static Wizard createCustom(){
        if (Character.hardcore == true) {
            Scanner scanner = new Scanner(System.in);
            int upgradePoints = 10;
            int intel = 20;
            int mana = 30;
            int hp = 75;
            System.out.println("What would you like to call your Wizard?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                System.out.println(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                System.out.println("1. Increase Intelligence: " + intel + " => " + (intel + 3));
                System.out.println("2. Increase Mana: " + mana + " => " + (mana + 5));
                System.out.println("3. Increase Hit Points: " + hp + " => " + (hp + 5));
                String input = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            intel += 3;
                            upgradePoints--;
                            break;
                        case 2:
                            mana += 5;
                            upgradePoints--;
                            break;
                        case 3:
                            hp += 5;
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
            return new Wizard(name, intel, mana, hp);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int intel = 0;
            int mana = 0;
            int hp = 0;
            System.out.println("What would you like to call your Wizard?");
            String name = scanner.nextLine();
            intel = statInput(10, 50, "Please enter a value for strength between 10 and 50");
            mana = statInput(10, 50, "Please enter a value for stamina between 10 and 50");
            hp = statInput(50, 100, "Please enter a value for hp between 50 and 100");
            scanner.close();
            return new Wizard(name, intel, mana, hp);
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void attack(Character character) {
        if (this.mana >= 5){
            character.receiveDamage(this.intelligence);
            this.mana -= 5;
        }
        else {
            character.receiveDamage(2);
            this.mana++;
        }
    }

    public void receiveDamage(double damage){
        setHp(getHp() - damage);
        if (getHp() <= 0){
            setAlive(false);
        }
    }

    public String printStats(){
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Intelligence: " + this.getIntelligence() + ", Mana: " + this.getMana() + ", Hp: " + (int) this.getHp() + ".";
    }
}
