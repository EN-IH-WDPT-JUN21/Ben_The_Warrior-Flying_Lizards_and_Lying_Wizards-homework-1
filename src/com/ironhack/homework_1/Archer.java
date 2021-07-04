package com.ironhack.homework_1;
import java.util.Scanner;

//Class extends character as a subclass and implements the attack interface to check for conformity.
public class Archer extends Character implements Attacker{
    private int Energy;
    private int Dexterity;

    //Instantiates a scanner object to be used in class body.
    static Scanner scanner = new Scanner(System.in);

    //Default constructor that takes no arguments and randomises all properties.
    public Archer(){
        super();
        Energy = 5 + (int)(Math.random() * 20 + 1);
        Dexterity = 5 + (int)(Math.random() * 15 + 1);
        setHp(50 + (int)(Math.random() * 50 + 1));
    }

    //Constructor that can be used to create objects with specific property values.
    public Archer(String name, int dex, int Energy, double hp){
        super(name);
        setDexterity(dex);
        setEnergy(Energy);
        setHp(hp);
    }

    //Getters and setters for Archer specific properties.
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
        return "Archer," + super.getName() + "," + getDexterity() + "," + getEnergy() + "," + super.getHp() + "\n";
    }

    //Function that allows more systematic and controlled character creation that is enabled when Hardcore mode is activated.
    //Starts off user with a pool of upgrade points that can be used to increment stats by set amount to enable a more balanced character creation.
    //While this method allows characters to have a greater individual stat value than is possible with randomisation, this comes at the cost
    //of low values in the other stats. A randomised character can be randomised with close to perfect values in all stats but lower maximums.
    //Trades overall stat totals for the ability to specialize.
    public static Archer createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int dex = 5;
            int Energy = 10;
            int hp = 50;
            Printer.printFormatted("What would you like to call your Archer?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                Printer.printFormatted(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                Printer.printFormatted("| 1. Increase Dexterity: " + dex + " => " + (dex + 3));
                Printer.printFormatted("| 2. Increase Energy: " + Energy + " => " + (Energy + 2));
                Printer.printFormatted("| 3. Increase Hit Points: " + hp + " => " + (hp + 7));
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
                            Printer.printFormatted("Please choose a valid option!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    Printer.printFormatted("Please choose a valid option!");
                }
            }
            return new Archer(name, dex, Energy, hp);
        }
        else {
            int dex = 0;
            int Energy = 0;
            int hp = 0;
            Printer.printFormatted("What would you like to call your Archer?");
            String name = scanner.nextLine();
            dex = statInput(5, 25, "Please enter a value for Dexterity between 5 and 25");
            Energy = statInput(5, 20, "Please enter a value for Energy between 5 and 20");
            hp = statInput(50, 100, "Please enter a value for Hp between 50 and 100");
            //scanner.close();
            return new Archer(name, dex, Energy, hp);
        }
    }

    //Function that takes a target character as an input and chooses an attack based on current Energy value.
    public String attack(Character character) {
        if (this.Energy < 10) {
            character.receiveDamage( (double) this.Dexterity / 2.0);
            this.Energy += 2;
            return "Aimed Shot|" + (this.Dexterity / 2.0);
        } else {
            if (Menu.getParty1().getIdxInParty(character) == -1) {
                for (Character ch : Menu.getParty1().getPartyCharacters()){
                    ch.receiveDamage(this.Dexterity / 5.0);
                }
            } else {
                for (Character ch : Menu.getParty2().getPartyCharacters()){
                    ch.receiveDamage(this.Dexterity / 5.0);
                }
            }
            this.Energy -= 10;
            return "Spread Shot|" + (this.Dexterity / 5.0);
        }
    }

    //Function that allows direct control of character during battles. Enabled when hardcore mode is turned on.
    //Informs user about the details of an attack and how much damage it will deal. Allows more strategic user of resources.
    //Returns data about chosen attack that is printed during battle logging.
    public String manualAttack(Character character) {
        if (this.Energy >= 5){
            while (true){
                Printer.printFormatted(this.getName() + " attacks with: ");
                Printer.printFormatted("1. Spread Shot");
                Printer.printFormatted("Release a clutch of arrows damaging all members of the enemy party.");
                Printer.printFormatted("Expend 10 Energy to deal damage to each enemy: " + (this.Dexterity / 5) + " Damage to each enemy");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                Printer.printFormatted("2. Aimed shot");
                Printer.printFormatted("Take aim and deliver a single arrow to center mass.");
                Printer.printFormatted("Recover 2 Energy deal damage equal to half your dexterity to your opponent: " + (this.Dexterity / 2) + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            if (Menu.getParty1().getIdxInParty(character) == -1) {
                                for (Character ch : Menu.getParty1().getPartyCharacters()){
                                    ch.receiveDamage(this.Dexterity / 5.0);
                                }
                            } else {
                                for (Character ch : Menu.getParty2().getPartyCharacters()){
                                    ch.receiveDamage(this.Dexterity / 5.0);
                                }
                            }
                            this.Energy -= 10;
                            return "Spread Shot|" + (this.Dexterity / 5.0);
                        case 2:
                            character.receiveDamage( (double) this.Dexterity / 2.0);
                            this.Energy += 2;
                            return "Aimed Shot|" + (this.Dexterity / 2.0);
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
                Printer.printFormatted("1. Spread Shot   ---   NOT ENOUGH ENERGY " + this.Energy + "/10 Energy required");
                Printer.printFormatted("Release a clutch of arrows damaging all members of the enemy party.");
                Printer.printFormatted("Expend 10 Energy to deal damage to each enemy: " + (this.Dexterity / 5) + " Damage to each enemy");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                Printer.printFormatted("2. Aimed shot");
                Printer.printFormatted("Take aim and deliver a single arrow to center mass.");
                Printer.printFormatted("Recover 2 Energy deal damage equal to half your dexterity to your opponent: " + (this.Dexterity / 2) + " Damage");
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            Printer.printFormatted("Not enough Energy!");
                            break;
                        case 2:
                            character.receiveDamage( (double) this.Dexterity / 2.0);
                            this.Energy += 2;
                            return "Aimed Shot|" + (this.Dexterity / 2.0);
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Dexterity: " + this.getDexterity() + ", Energy: " + this.getEnergy() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Energy:" + this.getEnergy() + " / Dexterity:" + this.getDexterity() ;
    }
}
