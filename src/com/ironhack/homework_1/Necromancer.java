package com.ironhack.homework_1;
import java.util.Scanner;

//Class extends character as a subclass and implements the attack interface to check for conformity.
public class Necromancer extends Character implements Attacker{
    private int Mana;
    private int Wisdom;
    private final int SKELETON_PARTY_SIZE = 20; // Used to define the maximum size of the skeleton party

    //Instantiates a scanner object to be used in class body.
    static Scanner scanner = new Scanner(System.in);

    //Default constructor that takes no arguments and randomises all properties.
    public Necromancer() {
        super();
        setWisdom(1 + (int)(Math.random() * 20 + 1));
        setMana(10 + (int)(Math.random() * 10 + 1));
        setHp(50 + (int)(Math.random() * 50 + 1));
    }

    //Constructor that can be used to create objects with specific property values.
    public Necromancer(String name, int wis, int mana, double hp){
        super(name);
        setWisdom(Math.min(wis, 30));
        setMana(Math.min(mana, 40));
        setHp(hp);
    }

    //Getters and setters for Necromancer specific properties.
    public int getMana() {
        return Mana;
    }
    public void setMana(int Mana) {
        this.Mana = Math.max(Mana, 10);
    }
    public int getWisdom() {
        return Wisdom;
    }
    public void setWisdom(int Wisdom) {
        this.Wisdom = Math.max(Wisdom, 1);
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
        return "Necromancer," + super.getName() + "," + getWisdom() + "," + getMana() +"," + super.getHp() + "\n";
    }

    //Function that allows more systematic and controlled character creation that is enabled when Hardcore mode is activated.
    //Starts off user with a pool of upgrade points that can be used to increment stats by set amount to enable a more balanced character creation.
    //While this method allows characters to have a greater individual stat value than is possible with randomisation, this comes at the cost
    //of low values in the other stats. A randomised character can be randomised with close to perfect values in all stats but lower maximums.
    //Trades overall stat totals for the ability to specialize.
    public static Necromancer createCustom(){
        if (Menu.isHardcore() == true) {
            int upgradePoints = 10;
            int wis = 10;
            int mana = 20;
            int hp = 50;
            Printer.printFormatted("What would you like to call your Necromancer?");
            String name = scanner.nextLine();
            while (upgradePoints > 0) {
                Printer.printFormatted(upgradePoints + " stat points remaining. Choose a stat to upgrade.");
                Printer.printFormatted("1. Increase Wisdom: " + wis + " => " + (wis + 2));
                Printer.printFormatted("2. Increase Mana: " + mana + " => " + (mana + 2));
                Printer.printFormatted("3. Increase Hit Points: " + hp + " => " + (hp + 7));
                String input = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            wis += 2;
                            upgradePoints--;
                            break;
                        case 2:
                            mana += 2;
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
            return new Necromancer(name, wis, mana, hp);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            int wis = 0;
            int mana = 0;
            int hp = 0;
            Printer.printFormatted("What would you like to call your Necromancer?");
            String name = scanner.nextLine();
            wis = statInput(1, 20, "Please enter a value for Wisdom between 1 and 20");
            mana = statInput(10, 20, "Please enter a value for Mana between 10 and 20");
            hp = statInput(50, 100, "Please enter a value for Hp between 50 and 100");
            //scanner.close();
            return new Necromancer(name, wis, mana, hp);
        }
    }

    //Function that takes a target character as an input and chooses an attack based on current Energy value.
    public String attack(Character character) {
        Party necroParty = Menu.getParty1().getIdxInParty(character) != -1 ? Menu.getParty2() : Menu.getParty1();
        if (this.Mana >= (20 + this.Wisdom) && necroParty.getPartyCharacters().size() < SKELETON_PARTY_SIZE){
            necroParty.addCharacter(new Skeleton("Skeleton Warrior", this.Wisdom, this.Wisdom, this.Wisdom));
            this.Mana -= (20 + this.Wisdom);
            return "Summon a Skeleton!|" + 0;
        }
        else {
            character.receiveDamage(this.Wisdom / 2.0);
            this.Mana += 5;
            return "Drain Life|" + (this.Wisdom / 2.0);
        }
    }

    //Function that allows direct control of character during battles. Enabled when hardcore mode is turned on.
    //Informs user about the details of an attack and how much damage it will deal. Allows more strategic user of resources.
    //Returns data about chosen attack that is printed during battle logging.
    public String manualAttack(Character character) {
        Party necroParty = Menu.getParty1().getIdxInParty(character) != -1 ? Menu.getParty2() : Menu.getParty1();
        if (this.Mana >= (20 + this.Wisdom) && necroParty.getPartyCharacters().size() < SKELETON_PARTY_SIZE){
            while (true){
                Printer.printFormatted(this.getName() + " attacks with: ");
                Printer.printFormatted("1. Summon Skeleton Warrior!");
                Printer.printFormatted("Summon a skeletal warrior to fight for your party.");
                Printer.printFormatted("Adds a weak Skeleton Warrior to the party. Deals no damage.");
                Printer.printLine(1);
                Printer.printFormatted("2. Drain Life");
                Printer.printFormatted("Suck the very life force from your opponent!");
                Printer.printFormatted("Recover 5 Mana by sucking energy from your opponent: " + (this.Wisdom / 2.0) + " Damage");
                Printer.printLine(1);
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            necroParty.addCharacter(new Warrior("Skeleton Warrior", this.Wisdom, this.Wisdom, this.Wisdom));
                            this.Mana -= (20 + this.Wisdom);
                            return "Summon a Skeleton!|" + 0;
                        case 2:
                            character.receiveDamage(this.Wisdom / 2.0);
                            this.Mana += 5;
                            return "Life Drain|" + (this.Wisdom / 2.0);
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
                if (this.Mana < 20 + this.Wisdom) {
                    Printer.printFormatted("1. Summon Skeleton Warrior!   --- NOT ENOUGH MANA " + this.Mana + "/" + (this.Wisdom + 20) + " mana required.");
                }
                else {
                    Printer.printFormatted("1. Summon Skeleton Warrior!   --- TOO MANY SKELETONS!");
                }
                Printer.printFormatted("Summon a skeletal warrior to fight for your party.");
                Printer.printFormatted("Adds a weak Skeleton Warrior to the party. Deals no damage.");
                Printer.printLine(1);
                Printer.printFormatted("2. Drain Life");
                Printer.printFormatted("Suck the very life force from your opponent!");
                Printer.printFormatted("Recover 5 Mana by sucking energy from your opponent: " + (this.Wisdom / 2.0) + " Damage");
                Printer.printLine(1);
                String tmp = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(tmp);
                    switch (choice){
                        case 1:
                            Printer.printFormatted("I can't do that!");
                            break;
                        case 2:
                            character.receiveDamage(this.Wisdom / 2.0);
                            this.Mana += 5;
                            return "Life Drain|" + (this.Wisdom / 2.0);
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
        return this.getName() + " the " + this.getClass().getSimpleName() + "; Id: " + this.getId() + ", Wisdom: " + this.getWisdom() + ", Mana: " + this.getMana() + ", Hp: " + (int) this.getHp() + ".";
    }
    public String printSimpleStats(){
        return "Hp:" + (int) this.getHp() + " / Mana:" + this.getMana() + " / Wisdom:" + this.getWisdom();
    }
}
