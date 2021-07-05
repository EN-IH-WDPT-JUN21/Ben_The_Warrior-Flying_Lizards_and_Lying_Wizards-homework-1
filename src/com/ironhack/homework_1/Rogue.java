package com.ironhack.homework_1;
import java.util.Scanner;

//Class extends character as a subclass and implements the attack interface to check for conformity.
public class Rogue extends Character implements Attacker{
    private int Luck;
    private int Agility;
    private int comboCount;

    //Instantiates a scanner object to be used in class body.
    static Scanner scanner = new Scanner(System.in);

    //Default constructor that takes no arguments and randomises all properties.
    public Rogue(){
        super();
        Luck = 5 + (int)(Math.random() * 10 + 1);
        Agility = 5 + (int)(Math.random() * 20 + 1);
        setHp(40 + (int)(Math.random() * 40 + 1));
        comboCount = 0;
    }

    //Constructor that can be used to create objects with specific property values.
    public Rogue(String name, int Agi, int Luck, double hp){
        super(name);
        setAgility(Math.min(Agi, 40));
        setLuck(Math.min(Luck, 30));
        setHp(hp);
        comboCount = 0;
    }

    //Getters and setters for Rogue specific properties.
    public int getLuck() {
        return Luck;
    }
    public void setLuck(int Luck) {
        this.Luck = Math.max(Luck, 5);
    }
    public int getAgility() {
        return Agility;
    }
    public void setAgility(int Agility) {
        this.Agility = Math.max(Agility, 10);
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
        return "Rogue," + super.getName() + "," + getAgility() + "," + getLuck() + "," + super.getHp() + "\n";
    }

    //Function that allows more systematic and controlled character creation that is enabled when Hardcore mode is activated.
    //Starts off user with a pool of upgrade points that can be used to increment stats by set amount to enable a more balanced character creation.
    //While this method allows characters to have a greater individual stat value than is possible with randomisation, this comes at the cost
    //of low values in the other stats. A randomised character can be randomised with close to perfect values in all stats but lower maximums.
    //Trades overall stat totals for the ability to specialize.
    public static Rogue createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int Agi = 10;
            int Luck = 5;
            int hp = 40;
            Printer.printFormatted("What would you like to call your Rogue?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                Printer.printFormatted(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                Printer.printFormatted("| 1. Increase Agility: " + Agi + " => " + (Agi + 3));
                Printer.printFormatted("| 2. Increase Luck: " + Luck + " => " + (Luck + 2));
                Printer.printFormatted("| 3. Increase Hit Points: " + hp + " => " + (hp + 6));
                String input = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            Agi += 3;
                            upgradePoints--;
                            break;
                        case 2:
                            Luck += 2;
                            upgradePoints--;
                            break;
                        case 3:
                            hp += 6;
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
            return new Rogue(name, Agi, Luck, hp);
        }
        else {
            int Agi = 0;
            int Luck = 0;
            int hp = 0;
            Printer.printFormatted("What would you like to call your Rogue?");
            String name = scanner.nextLine();
            Agi = statInput(5, 25, "Please enter a value for Agility between 5 and 25");
            Luck = statInput(5, 15, "Please enter a value for Luck between 5 and 15");
            hp = statInput(40, 80, "Please enter a value for Hp between 40 and 80");
            //scanner.close();
            return new Rogue(name, Agi, Luck, hp);
        }
    }

    //Function that takes a target character as an input and chooses an attack based on current Luck value.
    public String attack(Character character) {
        switch (comboCount){
            case 0:
                character.receiveDamage(Agility);
                this.comboCount++;
                return "Right Hook|" + this.Agility;
            case 1:
                character.receiveDamage(Agility);
                this.comboCount++;
                return "Left Hook" + this.Agility;
            case 2:
                character.receiveDamage(Agility);
                this.comboCount++;
                return "Leg Sweep" + this.Agility;
            default:
                character.receiveDamage(this.Agility * 4.0);
                this.comboCount = 0;
                return "Coup de grâce!|" + (this.Agility * 4.0);
        }
    }

    //Function that allows direct control of character during battles. Enabled when hardcore mode is turned on.
    //Informs user about the details of an attack and how much damage it will deal. Allows more strategic user of resources.
    //Returns data about chosen attack that is printed during battle logging.
    public String manualAttack(Character character) {
        if (this.comboCount == 0){
            while (true){
                Printer.printFormatted(this.getName() + " attacks with: ");
                Printer.printFormatted("1. Shank");
                Printer.printFormatted("Open up your enemies defences with an expertly aimed jab.");
                Printer.printFormatted("Deal light damage and increase your combo-count by 1: " + (this.Agility) + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                Printer.printFormatted("2. Coup de grâce!   ---   NO COMBO POINTS!");
                Printer.printFormatted("Delivery a finishing blow to your opponent!");
                Printer.printFormatted("Reset your combo count. Deal damage equal to your Agility * your combo count + 1: 0 Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            character.receiveDamage(this.Agility);
                            this.comboCount++;
                            this.Luck++;
                            return "Shank|" + (this.Agility);
                        case 2:
                            Printer.printFormatted("No Combo Points!");
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
                Printer.printFormatted("1. Shank");
                Printer.printFormatted("Open up your enemies defences with an expertly aimed jab.");
                Printer.printFormatted("Deal light damage and increase your combo-count by 1: " + (this.Agility) + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                Printer.printFormatted("2. Coup de grâce!   ---   using " + this.comboCount + " Combo Points!");
                Printer.printFormatted("Delivery a finishing blow to your opponent!");
                Printer.printFormatted("Reset your combo count. Deal damage equal to your Agility * your combo count + 1: " + ((this.comboCount + 1) * this.Agility) + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            character.receiveDamage(this.Agility);
                            this.comboCount++;
                            this.Luck++;
                            return "Shank|" + (this.Agility);
                        case 2:
                            character.receiveDamage(this.Agility * (this.comboCount + 1));
                            this.comboCount = 0;
                            this.Luck += 2;
                            return "Coup de grâce!|" + (this.Agility * (this.comboCount + 1));
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
        if (this.Luck >= 5) {
            if (Math.random() > 0.5){
                this.Luck -= 5;
                Printer.printFormatted(this.getName() + " blends into the shadows becoming hard to hit. Immune to damage for this turn!");
            }
            else {
                setHp(getHp() - damage);
            }
        }
        else {
            setHp(getHp() - damage);
        }
        if (getHp() <= 0){
            setAlive(false);
        }
    }

    //Functions to return a characters stats when requested to enabled logging/saving.
    public String printStats(){
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Agility: " + this.getAgility() + ", Luck: " + this.getLuck() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Luck:" + this.getLuck() + " / Agility:" + this.getAgility() ;
    }
}
