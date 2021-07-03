package com.ironhack.homework_1;

import java.util.*;

public class Warrior extends Character implements Attacker{
    /*stamina - number to represent a resource the warrior consumes to make an attack
    strength - number to calculate how strong the warrior attack is*/
    private int stamina;
    private int strength;

    static Scanner scanner = new Scanner(System.in);

    public Warrior() {
        super();
        setStrength(1 + (int)(Math.random() * 9 + 1));
        setStamina(10 + (int)(Math.random() * 40 + 1));
        setHp(100 + (int)(Math.random() * 100 + 1));
    }

    public Warrior(String name, int str, int stam, double hp){
        super(name);
        setStrength(str);
        setStamina(stam);
        setHp(hp);
    }

    private static int statInput(int statMin, int statMax, String message){
        System.out.println(message);
        String tmp = scanner.nextLine();
        try {
            int choice = Integer.parseInt(tmp);
            if (choice >= statMin && choice <= statMax){
                return choice;
            }
            else {
                System.out.println("Please enter a valid number");
                statInput(statMin, statMax, message);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a valid number");
            statInput(statMin, statMax, message);
        }
        return statMin;
    }
    
        @Override
    public String toCsvFormat() {
        return "Warrior," + super.getName() + "," + getStrength() + "," + getStamina() +"," + super.getHp() + "\n";
    }

    public static Warrior createCustom(){
        if (Menu.isHardcore() == true) {
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
            System.out.println("What would you like to call your Warrior?");
            String name = scanner.nextLine();
            str = statInput(1, 10, "Please enter a value for Strength between 1 and 10");
            stam = statInput(10, 50, "Please enter a value for Stamina between 10 and 50");
            hp = statInput(100, 200, "Please enter a value for Hp between 100 and 200");
            //scanner.close();
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

    public String attack(Character character) {
        if (this.stamina >= 5){
            character.receiveDamage(this.strength);
            this.stamina -= 5;
            return "Heavy Attack|" + this.strength;
        }
        else {
            character.receiveDamage(this.strength / 2.0);
            this.stamina++;
            return "Weak Attack|" + (this.strength / 2);
        }
    }

    public String manualAttack(Character character) {
        if (this.stamina >= 5){
            while (true){
                System.out.println(this.getName() + " attacks with: ");
                System.out.println("1. Heavy Attack");
                System.out.println("_________________________________________________________________________________________________");
                System.out.println("A strong cleaving stroke with equipped weapon.");
                System.out.println("Expend 5 stamina to deal damage equal to your strength: " + this.strength + " Damage");
                System.out.println("");
                System.out.println("2. Weak Attack");
                System.out.println("_________________________________________________________________________________________________");
                System.out.println("Wear your opponent down with a basic strike while conversing energy.");
                System.out.println("Recover 1 stamina to deal damage equal to half your strength: " + (this.strength / 2) + " Damage");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            character.receiveDamage(this.strength);
                            this.stamina -= 5;
                            return "Heavy Attack|" + this.strength;
                        case 2:
                            character.receiveDamage(this.strength / 2.0);
                            this.stamina++;
                            return "Weak Attack|" + (this.strength / 2);
                        default:
                            System.out.println("Choose an attack by entering 1 or 2");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Choose an attack by entering 1 or 2");
                }
            }
        }
        else {
            while (true){
                System.out.println(this.getName() + " attacks with: ");
                System.out.println("1. Heavy Attack   ---    NOT ENOUGH STAMINA: " + this.stamina + "/5 Stamina required.");
                System.out.println("_________________________________________________________________________________________________");
                System.out.println("A strong cleaving stroke with equipped weapon.");
                System.out.println("Expend 5 stamina to deal damage equal to your strength: " + this.strength + " Damage");
                System.out.println("");
                System.out.println("2. Weak Attack");
                System.out.println("_________________________________________________________________________________________________");
                System.out.println("Wear your opponent down with a basic strike while conversing energy.");
                System.out.println("Recover 1 stamina to deal damage equal to half your strength: " + (this.strength / 2) + " Damage");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            System.out.println("Not enough Stamina!");
                            break;
                        case 2:
                            character.receiveDamage(this.strength / 2.0);
                            this.stamina++;
                            return "Weak Attack|" + (this.strength / 2);
                        default:
                            System.out.println("Choose an attack by entering 1 or 2");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Choose an attack by entering 1 or 2");
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Strength: " + this.getStrength() + ", Stamina: " + this.getStamina() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Stamina:" + this.getStamina() + " / Strength:" + this.getStrength();
    }
}
