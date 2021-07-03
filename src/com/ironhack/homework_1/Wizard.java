package com.ironhack.homework_1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Wizard extends Character implements Attacker{
    /*
    mana - number to represent a resource the wizard consumes to cast spells
    intelligence - number to calculate how strong the wizard spells are
    */
    private int mana;
    private int intelligence;

    static Scanner scanner = new Scanner(System.in);

    public Wizard(){
        super();
        mana = 10 + (int)(Math.random() * 40 + 1);
        intelligence = 1 + (int)(Math.random() * 49 + 1);
        setHp(50 + (int)(Math.random() * 50 + 1));
    }

    public Wizard(String name, int intel, int mana, double hp){
        super(name);
        setIntelligence(intel);
        setMana(mana);
        setHp(hp);
    }

    private static void printFormatted(String message){
        StringBuilder print0 = new StringBuilder("| " + message);
        print0.append(String.join("", Collections.nCopies(126 - print0.toString().length(), " ")));
        print0.append("|");
        System.out.println(print0);
    }

    private static int statInput(int statMin, int statMax, String message){
        StringBuilder print0 = new StringBuilder("| " + message);
        print0.append(String.join("", Collections.nCopies(126 - print0.toString().length(), " ")));
        print0.append("|");
        System.out.println(print0);
        String tmp = scanner.nextLine();
        try {
            int choice = Integer.parseInt(tmp);
            if (choice >= statMin && choice <= statMax){
                return choice;
            }
            else {
                StringBuilder print1 = new StringBuilder("| Please enter a valid number");
                print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                print1.append("|");
                System.out.println(print1);
                statInput(statMin, statMax, message);
            }
        }
        catch (NumberFormatException e){
            StringBuilder print2 = new StringBuilder("| Please enter a valid number");
            print2.append(String.join("", Collections.nCopies(126 - print2.toString().length(), " ")));
            print2.append("|");
            System.out.println(print2);
            statInput(statMin, statMax, message);
        }
        return statMin;
    }
    
        @Override
    public String toCsvFormat() {
        return "Wizard," + super.getName() + "," + getIntelligence() + "," + getMana() + "," + super.getHp() + "\n";
    }

    public static Wizard createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int intel = 20;
            int mana = 30;
            int hp = 75;
            printFormatted("What would you like to call your Wizard?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                printFormatted(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                printFormatted("1. Increase Intelligence: " + intel + " => " + (intel + 3));
                printFormatted("2. Increase Mana: " + mana + " => " + (mana + 5));
                printFormatted("3. Increase Hit Points: " + hp + " => " + (hp + 5));
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
                            printFormatted("Please choose a valid option!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    printFormatted("Please choose a valid option!");
                }
            }
            return new Wizard(name, intel, mana, hp);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int intel = 0;
            int mana = 0;
            int hp = 0;
            printFormatted("What would you like to call your Wizard?");
            String name = scanner.nextLine();
            intel = statInput(10, 50, "Please enter a value for Intelligence between 10 and 50");
            mana = statInput(10, 50, "Please enter a value for Mana between 10 and 50");
            hp = statInput(50, 100, "Please enter a value for Hp between 50 and 100");
            //scanner.close();
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

    public String attack(Character character) {
        if (this.mana >= 5){
            character.receiveDamage(this.intelligence);
            this.mana -= 5;
            return "Fireball|" + this.intelligence;
        }
        else {
            character.receiveDamage(2);
            this.mana++;
            return "Staff Hit|" + 2;
        }
    }

    public String manualAttack(Character character) {
        if (this.mana >= 5){
            while (true){
                printFormatted(this.getName() + " attacks with: ");
                printFormatted("1. Fireball");
                printFormatted("Turn your foe to cinders!");
                printFormatted("Expend 5 mana to deal damage equal to your intelligence: " + this.intelligence + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                printFormatted("2. Staff Hit");
                printFormatted("Whack!");
                printFormatted("Recover 1 mana while giving your opponent a whack with your staff: " + "2 Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            character.receiveDamage(this.intelligence);
                            this.mana -= 5;
                            return "Fireball|" + this.intelligence;
                        case 2:
                            character.receiveDamage(2);
                            this.mana++;
                            return "Staff Hit|" + 2;
                        default:
                            printFormatted("Choose an attack by entering 1 or 2");
                    }
                }
                catch (NumberFormatException e){
                    printFormatted("Choose an attack by entering 1 or 2");
                }
            }
        }
        else {
            while (true){
                printFormatted(this.getName() + " attacks with: ");
                printFormatted("1. Fireball   ---   NOT ENOUGH MANA: " + this.mana + "/5 Mana required.");
                printFormatted("Turn your foe to cinders!");
                printFormatted("Expend 5 mana to deal damage equal to your intelligence: " + this.intelligence + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                printFormatted("2. Staff Hit");
                printFormatted("Whack!");
                printFormatted("Recover 1 mana while giving your opponent a whack with your staff: " + "2 Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            printFormatted("Not enough Mana!");
                            break;
                        case 2:
                            character.receiveDamage(2);
                            this.mana++;
                            return "Staff Hit|" + 2;
                        default:
                            printFormatted("Choose an attack by entering 1 or 2");
                    }
                }
                catch (NumberFormatException e){
                    printFormatted("Choose an attack by entering 1 or 2");
                }
            }
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
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp()  + " / Mana:" + this.getMana() + " / Intelligence:" + this.getIntelligence();
    }
}
