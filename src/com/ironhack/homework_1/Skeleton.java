package com.ironhack.homework_1;
import java.util.Scanner;

//Class extends character as a subclass and implements the attack interface to check for conformity.
public class Skeleton extends Warrior implements Attacker{
    private int stamina;
    private int strength;

    //Instantiates a scanner object to be used in class body.
    static Scanner scanner = new Scanner(System.in);

    //Constructor that can be used to create objects with specific property values.
    public Skeleton(String name, int str, int stam, double hp){
        super(name);
        setStrength(Math.min(str, 30));
        setStamina(Math.min(stam, 30));
        setHp(hp);
    }

    //Getters and setters for Warrior specific properties.
    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = Math.max(1, stamina);
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = Math.max(1, strength);
    }

    //Function to output characters to CSV file in standardized format that can be parsed back when CSV file is read from.
    @Override
    public String toCsvFormat() {
        return "Skeleton," + super.getName() + "," + getStrength() + "," + getStamina() +"," + super.getHp() + "\n";
    }


    //Function that takes a target character as an input and chooses an attack based on current Energy value.
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

    //Function that allows direct control of character during battles. Enabled when hardcore mode is turned on.
    //Informs user about the details of an attack and how much damage it will deal. Allows more strategic user of resources.
    //Returns data about chosen attack that is printed during battle logging.
    public String manualAttack(Character character) {
        if (this.stamina >= 5){
            while (true){
                Printer.printFormatted(this.getName() + " attacks with: ");
                Printer.printFormatted("1. Heavy Attack");
                Printer.printFormatted("A strong cleaving stroke with equipped weapon.");
                Printer.printFormatted("Expend 5 stamina to deal damage equal to your strength: " + this.strength + " Damage");
                Printer.printLine(1);
                Printer.printFormatted("2. Weak Attack");
                Printer.printFormatted("Wear your opponent down with a basic strike while conversing energy.");
                Printer.printFormatted("Recover 1 stamina to deal damage equal to half your strength: " + (this.strength / 2) + " Damage");
                Printer.printLine(1);
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
                            Printer.printFormatted("Choose an attack by entering 1 or 2");
                    }
                }
                catch (NumberFormatException e){
                    Printer.printFormatted("Choose an attack by entering 1 or 2");
                }
            }
        }
        else {
            while (true){
                Printer.printFormatted(this.getName() + " attacks with: ");
                Printer.printFormatted("1. Heavy Attack   ---    NOT ENOUGH STAMINA: " + this.stamina + "/5 Stamina required.");
                Printer.printFormatted("A strong cleaving stroke with equipped weapon.");
                Printer.printFormatted("Expend 5 stamina to deal damage equal to your strength: " + this.strength + " Damage");
                Printer.printLine(1);
                Printer.printFormatted("2. Weak Attack");
                Printer.printFormatted("Wear your opponent down with a basic strike while conversing energy.");
                Printer.printFormatted("Recover 1 stamina to deal damage equal to half your strength: " + (this.strength / 2) + " Damage");
                Printer.printLine(1);
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            Printer.printFormatted("Not enough Stamina!");
                            break;
                        case 2:
                            character.receiveDamage(this.strength / 2.0);
                            this.stamina++;
                            return "Weak Attack|" + (this.strength / 2);
                        default:
                            Printer.printFormatted("Choose an attack by entering 1 or 2");
                    }
                }
                catch (NumberFormatException e){
                    Printer.printFormatted("Choose an attack by entering 1 or 2");
                }
            }
        }
    }

    //Damage is passed to the character when attacked. This is separated into it's own function so it can be called
    //to enable attacks that are not directed at the target of a characters .attack method. Allows party wide attacks etc.
    public void receiveDamage(double damage){
        setHp(getHp() - damage);
        if (getHp() <= 0){
            setAlive(false);
        }
    }

    //Functions to return a characters stats when requested to enabled logging/saving.
    public String printStats(){
        return this.getName() + "; Id: " + this.getId() + ", Strength: " + this.getStrength() + ", Stamina: " + this.getStamina() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Stamina:" + this.getStamina() + " / Strength:" + this.getStrength();
    }
}
