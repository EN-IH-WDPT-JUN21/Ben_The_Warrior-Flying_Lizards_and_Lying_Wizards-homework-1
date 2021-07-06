package com.ironhack.homework_1;
import java.util.Scanner;

//Class extends character as a subclass and implements the attack interface to check for conformity.
public class Warrior extends Character implements Attacker{
    private int stamina;
    private int strength;

    //Instantiates a scanner object to be used in class body.
    static Scanner scanner = new Scanner(System.in);

    //Default constructor that takes no arguments and randomises all properties.
    public Warrior() {
        super();
        setStrength(1 + (int)(Math.random() * 9 + 1));
        setStamina(10 + (int)(Math.random() * 40 + 1));
        setHp(100 + (int)(Math.random() * 100 + 1));
    }

    //Constructor that can be used to create objects with specific property values.
    public Warrior(String name, int str, int stam, double hp){
        super(name);
        setStrength(Math.min(str, 15));
        setStamina(Math.min(stam, 80));
        setHp(Math.max(Math.min(hp, 250), 100));
    }

    //Default constructor that takes no arguments and randomises all properties.
    public Warrior(String name) {
        super(name);
    }

    //Getters and setters for Warrior specific properties.
    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = Math.max(10, stamina);
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = Math.max(1, strength);
    }

    //Helper function that loops user input until a valid number is entered between statMin and Statmax, displays message.
    //Used for custom character creation loop
    private static int statInput(int statMin, int statMax, String message){
        Printer.printFormatted(message);
        String tmp = scanner.nextLine();
        try {
            int choice = Integer.parseInt(tmp);
            if (choice >= statMin && choice <= statMax){
                return choice;
            }
            else {
                Printer.printFormatted("Please enter a valid number");
                statInput(statMin, statMax, message);
            }
        }
        catch (NumberFormatException e){
            Printer.printFormatted("Please enter a valid number");
            statInput(statMin, statMax, message);
        }
        return statMin;
    }

    //Function to output characters to CSV file in standardized format that can be parsed back when CSV file is read from.
    @Override
    public String toCsvFormat() {
        return "Warrior," + super.getName() + "," + getStrength() + "," + getStamina() +"," + super.getHp() + "\n";
    }

    //Function that allows more systematic and controlled character creation that is enabled when Hardcore mode is activated.
    //Starts off user with a pool of upgrade points that can be used to increment stats by set amount to enable a more balanced character creation.
    //While this method allows characters to have a greater individual stat value than is possible with randomisation, this comes at the cost
    //of low values in the other stats. A randomised character can be randomised with close to perfect values in all stats but lower maximums.
    //Trades overall stat totals for the ability to specialize.
    public static Warrior createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int str = 5;
            int stam = 30;
            int hp = 150;
            Printer.printFormatted("What would you like to call your Warrior?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                Printer.printFormatted(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                Printer.printFormatted("1. Increase Strength: " + str + " => " + (str + 1));
                Printer.printFormatted("2. Increase Stamina: " + stam + " => " + (stam + 5));
                Printer.printFormatted("3. Increase Hit Points: " + hp + " => " + (hp + 10));
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
                            Printer.printFormatted("Please choose a valid option!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    Printer.printFormatted("Please choose a valid option!");
                }
            }
            return new Warrior(name, str, stam, hp);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int str = 0;
            int stam = 0;
            int hp = 0;
            Printer.printFormatted("What would you like to call your Warrior?");
            String name = scanner.nextLine();
            str = statInput(1, 10, "Please enter a value for Strength between 1 and 10");
            stam = statInput(10, 50, "Please enter a value for Stamina between 10 and 50");
            hp = statInput(100, 200, "Please enter a value for Hp between 100 and 200");
            //scanner.close();
            return new Warrior(name, str, stam, hp);
        }
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
            return "Weak Attack|" + (this.strength / 2.0);
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
                Printer.printFormatted("Recover 1 stamina to deal damage equal to half your strength: " + (this.strength / 2.0) + " Damage");
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
                            return "Weak Attack|" + (this.strength / 2.0);
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
                Printer.printFormatted("Recover 1 stamina to deal damage equal to half your strength: " + (this.strength / 2.0) + " Damage");
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
                            return "Weak Attack|" + (this.strength / 2.0);
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Strength: " + this.getStrength() + ", Stamina: " + this.getStamina() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Stamina:" + this.getStamina() + " / Strength:" + this.getStrength();
    }
}
