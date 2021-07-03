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

    @Override
    public String toCsvFormat() {
        return "Archer," + super.getName() + "," + getDexterity() + "," + getEnergy() + "," + super.getHp() + "\n";
    }

    public static Archer createCustom(){
        if (Character.hardcore == true) {
            Scanner scanner = new Scanner(System.in);
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
            Scanner scanner = new Scanner(System.in);
            int dex = 0;
            int Energy = 0;
            int hp = 0;
            System.out.println("What would you like to call your Archer?");
            String name = scanner.nextLine();
            dex = statInput(5, 25, "Please enter a value for Dexterity between 10 and 50");
            Energy = statInput(5, 20, "Please enter a value for Energy between 10 and 50");
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
                    this.Energy -= 10;
                    return "Spread Shot|" + this.Dexterity;
                }
            } else {
                for (Character ch : Menu.getParty2().getPartyCharacters()){
                    ch.receiveDamage(this.Dexterity / 5);
                    this.Energy -= 10;
                    Map<String, Double> attackDetails = new HashMap<>();
                    attackDetails.put("Heavy Attack", (double) this.Dexterity);
                    return "Spread Shot|" + this.Dexterity;
                }
            }
            return "Spread Shot|" + this.Dexterity;
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
}