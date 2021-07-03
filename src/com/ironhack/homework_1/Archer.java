package com.ironhack.homework_1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Archer extends Character implements Attacker{
    /*
    Energy - number to represent a resource the Archer consumes to cast spells
    Dexterity - number to calculate how strong the Archer spells are
    */
    private int Energy;
    private int Dexterity;

    static Scanner scanner = new Scanner(System.in);

    public Archer(){
        super();
        Energy = 5 + (int)(Math.random() * 20 + 1);
        Dexterity = 5 + (int)(Math.random() * 15 + 1);
        setHp(50 + (int)(Math.random() * 50 + 1));
    }

    public Archer(String name, int dex, int Energy, double hp){
        super(name);
        setDexterity(dex);
        setEnergy(Energy);
        setHp(hp);
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
        return "Archer," + super.getName() + "," + getDexterity() + "," + getEnergy() + "," + super.getHp() + "\n";
    }

    public static Archer createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int dex = 5;
            int Energy = 10;
            int hp = 50;
            StringBuilder print3 = new StringBuilder("| What would you like to call your Archer?");
            print3.append(String.join("", Collections.nCopies(126 - print3.toString().length(), " ")));
            print3.append("|");
            System.out.println(print3);
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                System.out.println(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                StringBuilder print4 = new StringBuilder("| " + upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                print4.append(String.join("", Collections.nCopies(126 - print4.toString().length(), " ")));
                print4.append("|");
                System.out.println(print4);
                StringBuilder print5 = new StringBuilder("| 1. Increase Dexterity: " + dex + " => " + (dex + 3));
                print5.append(String.join("", Collections.nCopies(126 - print5.toString().length(), " ")));
                print5.append("|");
                System.out.println(print5);
                StringBuilder print6 = new StringBuilder("| 2. Increase Energy: " + Energy + " => " + (Energy + 2));
                print6.append(String.join("", Collections.nCopies(126 - print6.toString().length(), " ")));
                print6.append("|");
                System.out.println(print6);
                StringBuilder print7 = new StringBuilder("| 3. Increase Hit Points: " + hp + " => " + (hp + 7));
                print7.append(String.join("", Collections.nCopies(126 - print7.toString().length(), " ")));
                print7.append("|");
                System.out.println(print7);
                String input = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            dex += 3;
                            upgradePoints--;
                            break;
                        case 2:
                            Energy += 2;
                            upgradePoints--;
                            break;
                        case 3:
                            hp += 7;
                            upgradePoints--;
                            break;
                        default:
                            StringBuilder print8 = new StringBuilder("| Please choose a valid option!");
                            print8.append(String.join("", Collections.nCopies(126 - print8.toString().length(), " ")));
                            print8.append("|");
                            System.out.println(print8);
                            break;
                    }
                } catch (NumberFormatException e) {
                    StringBuilder print9 = new StringBuilder("| Please choose a valid option!");
                    print9.append(String.join("", Collections.nCopies(126 - print9.toString().length(), " ")));
                    print9.append("|");
                    System.out.println(print9);
                }
            }
            return new Archer(name, dex, Energy, hp);
        }
        else {
            int dex = 0;
            int Energy = 0;
            int hp = 0;
            StringBuilder print9 = new StringBuilder("| What would you like to call your Archer?");
            print9.append(String.join("", Collections.nCopies(126 - print9.toString().length(), " ")));
            print9.append("|");
            System.out.println(print9);
            String name = scanner.nextLine();
            dex = statInput(5, 25, "Please enter a value for Dexterity between 5 and 25");
            Energy = statInput(5, 20, "Please enter a value for Energy between 5 and 20");
            hp = statInput(50, 100, "Please enter a value for Hp between 50 and 100");
            //scanner.close();
            return new Archer(name, dex, Energy, hp);
        }
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int Energy) {
        this.Energy = Math.min(Energy, 30);
    }

    public int getDexterity() {
        return Dexterity;
    }

    public void setDexterity(int Dexterity) {
        this.Dexterity = Math.min(Dexterity, 35);
    }

    public String attack(Character character) {
        if (this.Energy < 10) {
            character.receiveDamage( (double) this.Dexterity / 2);
            this.Energy += 2;
            return "Aimed Shot|" + (this.Dexterity / 2);
        } else {
            if (Menu.getParty1().getIdxInParty(character) == -1) {
                for (Character ch : Menu.getParty1().getPartyCharacters()){
                    ch.receiveDamage(this.Dexterity / 5);
                }
            } else {
                for (Character ch : Menu.getParty2().getPartyCharacters()){
                    ch.receiveDamage(this.Dexterity / 5);
                }
            }
            this.Energy -= 10;
            return "Spread Shot|" + this.Dexterity;
        }
    }

    public String manualAttack(Character character) {
        if (this.Energy >= 5){
            while (true){
                StringBuilder print10 = new StringBuilder("| " + this.getName() + " attacks with: ");
                print10.append(String.join("", Collections.nCopies(126 - print10.toString().length(), " ")));
                print10.append("|");
                System.out.println(print10);
                StringBuilder print11 = new StringBuilder("| 1. Spread Shot");
                print11.append(String.join("", Collections.nCopies(126 - print11.toString().length(), " ")));
                print11.append("|");
                System.out.println(print11);
                StringBuilder print12 = new StringBuilder("| Release a clutch of arrows damaging all members of the enemy party.");
                print12.append(String.join("", Collections.nCopies(126 - print12.toString().length(), " ")));
                print12.append("|");
                System.out.println(print12);
                StringBuilder print13 = new StringBuilder("| Expend 10 Energy to deal damage to each enemy: " + (this.Dexterity / 5) + " Damage to each enemy");
                print13.append(String.join("", Collections.nCopies(126 - print13.toString().length(), " ")));
                print13.append("|");
                System.out.println(print13);
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                StringBuilder print14 = new StringBuilder("| 2. Aimed shot");
                print14.append(String.join("", Collections.nCopies(126 - print14.toString().length(), " ")));
                print14.append("|");
                System.out.println(print14);
                StringBuilder print15 = new StringBuilder("| Take aim and deliver a single arrow to center mass.");
                print15.append(String.join("", Collections.nCopies(126 - print15.toString().length(), " ")));
                print15.append("|");
                System.out.println(print15);
                StringBuilder print16 = new StringBuilder("| Recover 2 Energy deal damage equal to half your dexterity to your opponent: " + (this.Dexterity / 2) + " Damage");
                print16.append(String.join("", Collections.nCopies(126 - print16.toString().length(), " ")));
                print16.append("|");
                System.out.println(print16);
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            for (Character ch : Menu.getParty1().getPartyCharacters()){
                                ch.receiveDamage(this.Dexterity / 5);
                            }
                            this.Energy -= 10;
                            return "Spread Shot|" + this.Dexterity;
                        case 2:
                            character.receiveDamage( (double) this.Dexterity / 2);
                            this.Energy += 2;
                            return "Aimed Shot|" + (this.Dexterity / 2);
                        default:
                            StringBuilder print1 = new StringBuilder("| Choose an attack by entering 1 or 2");
                            print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                            print1.append("|");
                            System.out.println(print1);
                    }
                }
                catch (NumberFormatException e){
                    StringBuilder print1 = new StringBuilder("| Choose an attack by entering 1 or 2");
                    print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                    print1.append("|");
                    System.out.println(print1);
                }
            }
        }
        else {
            while (true){
                StringBuilder print10 = new StringBuilder("| " + this.getName() + " attacks with: ");
                print10.append(String.join("", Collections.nCopies(126 - print10.toString().length(), " ")));
                print10.append("|");
                System.out.println(print10);
                StringBuilder print11 = new StringBuilder("| 1. Spread Shot   ---   NOT ENOUGH ENERGY " + this.Energy + "/10 Energy required");
                print11.append(String.join("", Collections.nCopies(126 - print11.toString().length(), " ")));
                print11.append("|");
                System.out.println(print11);
                StringBuilder print12 = new StringBuilder("| Release a clutch of arrows damaging all members of the enemy party.");
                print12.append(String.join("", Collections.nCopies(126 - print12.toString().length(), " ")));
                print12.append("|");
                System.out.println(print12);
                StringBuilder print13 = new StringBuilder("| Expend 10 Energy to deal damage to each enemy: " + (this.Dexterity / 5) + " Damage to each enemy");
                print13.append(String.join("", Collections.nCopies(126 - print13.toString().length(), " ")));
                print13.append("|");
                System.out.println(print13);
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                StringBuilder print14 = new StringBuilder("| 2. Aimed shot");
                print14.append(String.join("", Collections.nCopies(126 - print14.toString().length(), " ")));
                print14.append("|");
                System.out.println(print14);
                StringBuilder print15 = new StringBuilder("| Take aim and deliver a single arrow to center mass.");
                print15.append(String.join("", Collections.nCopies(126 - print15.toString().length(), " ")));
                print15.append("|");
                System.out.println(print15);
                StringBuilder print16 = new StringBuilder("| Recover 2 Energy deal damage equal to half your dexterity to your opponent: " + (this.Dexterity / 2) + " Damage");
                print16.append(String.join("", Collections.nCopies(126 - print16.toString().length(), " ")));
                print16.append("|");
                System.out.println(print16);
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            System.out.println("Not enough Energy!");
                            StringBuilder print1 = new StringBuilder("| Not enough Energy!");
                            print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                            print1.append("|");
                            System.out.println(print1);
                            break;
                        case 2:
                            character.receiveDamage( (double) this.Dexterity / 2);
                            this.Energy += 2;
                            return "Aimed Shot|" + (this.Dexterity / 2);
                        default:
                            StringBuilder print2 = new StringBuilder("| Choose an attack by entering 1 or 2");
                            print2.append(String.join("", Collections.nCopies(126 - print2.toString().length(), " ")));
                            print2.append("|");
                            System.out.println(print2);
                    }
                }
                catch (NumberFormatException e){
                    StringBuilder print1 = new StringBuilder("| Choose an attack by entering 1 or 2");
                    print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                    print1.append("|");
                    System.out.println(print1);
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Dexterity: " + this.getDexterity() + ", Energy: " + this.getEnergy() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Energy:" + this.getEnergy() + " / Dexterity:" + this.getDexterity() ;
    }
}
