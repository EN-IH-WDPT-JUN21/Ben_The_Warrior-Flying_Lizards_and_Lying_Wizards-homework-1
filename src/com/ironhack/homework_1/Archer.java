package com.ironhack.homework_1;

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
        return "Archer," + super.getName() + "," + getDexterity() + "," + getEnergy() + "," + super.getHp() + "\n";
    }

    public static Archer createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int dex = 5;
            int Energy = 10;
            int hp = 50;
            System.out.println("What would you like to call your Archer?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                System.out.println(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                System.out.println("1. Increase Dexterity: " + dex + " => " + (dex + 3));
                System.out.println("2. Increase Energy: " + Energy + " => " + (Energy + 2));
                System.out.println("3. Increase Hit Points: " + hp + " => " + (hp + 7));
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
                            System.out.println("Please choose a valid option!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please choose a valid option!");
                }
            }
            return new Archer(name, dex, Energy, hp);
        }
        else {
            int dex = 0;
            int Energy = 0;
            int hp = 0;
            System.out.println("| What would you like to call your Archer?");
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
                System.out.println(this.getName() + " attacks with: ");
                System.out.println("1. Spread Shot");
                System.out.println("_____________________________________________________________________________________________________________________________________");
                System.out.println("Release a clutch of arrows damaging all members of the enemy party.");
                System.out.println("Expend 10 Energy to deal damage to each enemy equal to 1 fifth of your dexterity: " + (this.Dexterity / 5) + " Damage to each enemy");
                System.out.println("");
                System.out.println("2. Aimed shot");
                System.out.println("_____________________________________________________________________________________________________________________________________");
                System.out.println("Take aim and deliver a single arrow to center mass.");
                System.out.println("Recover 2 Energy deal damage equal to half your dexterity to your opponent: " + (this.Dexterity / 2) + " Damage");
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
                System.out.println("1. Spread Shot   ---   NOT ENOUGH ENERGY " + this.Energy + "/10 Energy required");
                System.out.println("_____________________________________________________________________________________________________________________________________");
                System.out.println("Release a clutch of arrows damaging all members of the enemy party.");
                System.out.println("Expend 10 Energy to deal damage to each enemy equal to 1 fifth of your dexterity: " + (this.Dexterity / 5) + " Damage to each enemy");
                System.out.println("");
                System.out.println("2. Aimed shot");
                System.out.println("_____________________________________________________________________________________________________________________________________");
                System.out.println("Take aim and deliver a single arrow to center mass.");
                System.out.println("Recover 2 Energy deal damage equal to half your dexterity to your opponent: " + (this.Dexterity / 2) + " Damage");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            System.out.println("Not enough Energy!");
                            break;
                        case 2:
                            character.receiveDamage( (double) this.Dexterity / 2);
                            this.Energy += 2;
                            return "Aimed Shot|" + (this.Dexterity / 2);
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Dexterity: " + this.getDexterity() + ", Energy: " + this.getEnergy() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Energy:" + this.getEnergy() + " / Dexterity:" + this.getDexterity() ;
    }
}
