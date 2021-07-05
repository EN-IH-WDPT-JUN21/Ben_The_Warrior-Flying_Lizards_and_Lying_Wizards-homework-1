package com.ironhack.homework_1;
import java.util.Scanner;

//Class extends character as a subclass and implements the attack interface to check for conformity.
public class Wizard extends Character implements Attacker{
    private int mana;
    private int intelligence;

    //Instantiates a scanner object to be used in class body.
    static Scanner scanner = new Scanner(System.in);

    //Default constructor that takes no arguments and randomises all properties.
    public Wizard(){
        super();
        mana = 10 + (int)(Math.random() * 40 + 1);
        intelligence = 1 + (int)(Math.random() * 49 + 1);
        setHp(50 + (int)(Math.random() * 50 + 1));
    }

    //Constructor that can be used to create objects with specific property values.
    public Wizard(String name, int intel, int mana, double hp){
        super(name);
        setIntelligence(intel);
        setMana(mana);
        setHp(hp);
    }

    //Getters and setters for Wizard specific properties.
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
        return "Wizard," + super.getName() + "," + getIntelligence() + "," + getMana() + "," + super.getHp() + "\n";
    }

    //Function that allows more systematic and controlled character creation that is enabled when Hardcore mode is activated.
    //Starts off user with a pool of upgrade points that can be used to increment stats by set amount to enable a more balanced character creation.
    //While this method allows characters to have a greater individual stat value than is possible with randomisation, this comes at the cost
    //of low values in the other stats. A randomised character can be randomised with close to perfect values in all stats but lower maximums.
    //Trades overall stat totals for the ability to specialize.
    public static Wizard createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int intel = 20;
            int mana = 30;
            int hp = 75;
            Printer.printFormatted("What would you like to call your Wizard?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                Printer.printFormatted(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                Printer.printFormatted("1. Increase Intelligence: " + intel + " => " + (intel + 3));
                Printer.printFormatted("2. Increase Mana: " + mana + " => " + (mana + 5));
                Printer.printFormatted("3. Increase Hit Points: " + hp + " => " + (hp + 5));
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
                            Printer.printFormatted("Please choose a valid option!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    Printer.printFormatted("Please choose a valid option!");
                }
            }
            return new Wizard(name, intel, mana, hp);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int intel = 0;
            int mana = 0;
            int hp = 0;
            Printer.printFormatted("What would you like to call your Wizard?");
            String name = scanner.nextLine();
            intel = statInput(10, 50, "Please enter a value for Intelligence between 10 and 50");
            mana = statInput(10, 50, "Please enter a value for Mana between 10 and 50");
            hp = statInput(50, 100, "Please enter a value for Hp between 50 and 100");
            //scanner.close();
            return new Wizard(name, intel, mana, hp);
        }
    }

    //Function that takes a target character as an input and chooses an attack based on current Energy value.
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

    //Function that allows direct control of character during battles. Enabled when hardcore mode is turned on.
    //Informs user about the details of an attack and how much damage it will deal. Allows more strategic user of resources.
    //Returns data about chosen attack that is printed during battle logging.
    public String manualAttack(Character character) {
        if (this.mana >= 5){
            while (true){
                Printer.printFormatted(this.getName() + " attacks with: ");
                Printer.printFormatted("1. Fireball");
                Printer.printFormatted("Turn your foe to cinders!");
                Printer.printFormatted("Expend 5 mana to deal damage equal to your intelligence: " + this.intelligence + " Damage");
                Printer.printLine(1);
                Printer.printFormatted("2. Staff Hit");
                Printer.printFormatted("Whack!");
                Printer.printFormatted("Recover 1 mana while giving your opponent a whack with your staff: " + "2 Damage");
                Printer.printLine(1);
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
                Printer.printFormatted("1. Fireball   ---   NOT ENOUGH MANA: " + this.mana + "/5 Mana required.");
                Printer.printFormatted("Turn your foe to cinders!");
                Printer.printFormatted("Expend 5 mana to deal damage equal to your intelligence: " + this.intelligence + " Damage");
                Printer.printLine(1);
                Printer.printFormatted("2. Staff Hit");
                Printer.printFormatted("Whack!");
                Printer.printFormatted("Recover 1 mana while giving your opponent a whack with your staff: " + "2 Damage");
                Printer.printLine(1);
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            Printer.printFormatted("Not enough Mana!");
                            break;
                        case 2:
                            character.receiveDamage(2);
                            this.mana++;
                            return "Staff Hit|" + 2;
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Intelligence: " + this.getIntelligence() + ", Mana: " + this.getMana() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp()  + " / Mana:" + this.getMana() + " / Intelligence:" + this.getIntelligence();
    }
}
