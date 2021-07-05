package com.ironhack.homework_1;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public abstract class Character {
    private int id;
    private String name;
    private double hp;
    private boolean isAlive;

    //idCount is incremented to allocate a new ID when a character is instantiated.
    private static int idCount = 0;
    //Keep track of character classes by name for user selection and by class reference so their constructors can be accessed at runtime.
    private static String[] classNames = {"Warrior", "Wizard", "Archer", "Rogue", "Necromancer"};
    private static Class[] possibleClasses = {Warrior.class, Wizard.class, Archer.class, Rogue.class, Necromancer.class};

    //Default constructor for use when creating randomised characters
    public Character(){
        this.isAlive = true;
        setName();
        setId();
    }

    //Constructor that allows user input of character name
    public Character(String name){
        this.isAlive = true;
        setName(name);
        setId();
    }


    //Getters and setters for Character.java private properties
    public int getId() {
        return id;
    }
    public void setId() {
        this.id = ++idCount;
    }
    public String getName() {
        return name;
    }
    //Overloaded setName function to enable direct passing of name as well as randomisation.
    public void setName(){
        this.name = Names.randomName();
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getHp() {
        return hp;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    //Abstract functions implemented by all subclasses
    abstract String attack(Character character);
    abstract String manualAttack(Character character);
    abstract void receiveDamage(double damage);
    abstract String printStats();
    abstract String printSimpleStats();
    abstract String toCsvFormat();

    //Prints out the basics of a character object
    public String printSimpleIntroduction(){
        if (this.getClass() != Skeleton.class) {
            return this.getName() + " the " + this.getClass().getSimpleName();
        }
        else {
            return this.getName();
        }
    }

    private static double hpBound(double min, double max, double hp){
        return hp > max ? max : Math.max(hp, min);
    }

    //Character object parsing for use when importing from CSV file. Checks each character is correctly formatted
    //with the appropriate amount of parameters a character should have.
    public static Character addCharacter(String[] parameters){
        switch(parameters[0].trim().toLowerCase()){
            case "warrior":
                if(parameters.length == 5){
                    try{
                        return new Warrior(parameters[1].trim(), Integer.parseInt(parameters[2].trim()),
                                Integer.parseInt(parameters[3].trim()), Menu.isHardcore()
                                ? hpBound(100.0, 250.0, Double.parseDouble(parameters[4].trim()))
                                : hpBound(100.0, 200.0, Double.parseDouble(parameters[4].trim())));
                    }catch (NumberFormatException e){
                        return null;
                    }
                }else{
                    Printer.printFormatted("ERROR: incorrect number of parameters for Warrior. This Character will not be added to the party");
                }

            case "rogue":
                if(parameters.length == 5){
                    try{
                        return new Rogue(parameters[1].trim(), Integer.parseInt(parameters[2].trim()),
                                Integer.parseInt(parameters[3].trim()), hpBound(40.0, 120.0, Double.parseDouble(parameters[4].trim())));
                    }catch (NumberFormatException e){
                        return null;
                    }
                }else{
                    Printer.printFormatted("ERROR: incorrect number of parameters for Rogue. This Character will not be added to the party");
                }

            case "necromancer":
                if(parameters.length == 5){
                    try{
                        return new Necromancer(parameters[1].trim(), Integer.parseInt(parameters[2].trim()),
                                Integer.parseInt(parameters[3].trim()), hpBound(50.0, 100.0, Double.parseDouble(parameters[4].trim())));
                    }catch (NumberFormatException e){
                        return null;
                    }
                }else{
                    Printer.printFormatted("ERROR: incorrect number of parameters for Necromancer. This Character will not be added to the party");
                }

            case "wizard":
                if(parameters.length == 5){
                    try{
                        return new Wizard(parameters[1].trim(), Integer.parseInt(parameters[2].trim()),
                                Integer.parseInt(parameters[3].trim()), Menu.isHardcore()
                                ? hpBound(50.0, 120.0, Double.parseDouble(parameters[4].trim()))
                                : hpBound(50.0, 100.0, Double.parseDouble(parameters[4].trim())));
                    }
                    catch (NumberFormatException e){
                        return null;
                    }
                }else{
                    Printer.printFormatted("ERROR: incorrect number of parameters for Wizard. This Character will not be added to the party");
                    return null;
                }

            case "archer":
                if(parameters.length == 5){
                    try{
                        return new Archer(parameters[1].trim(), Integer.parseInt(parameters[2].trim()),
                                Integer.parseInt(parameters[3].trim()), hpBound(50.0, 120.0, Double.parseDouble(parameters[4].trim())));
                    }
                    catch (NumberFormatException e){
                        return null;
                    }
                }else{
                    Printer.printFormatted("ERROR: incorrect number of parameters for Archer. This Character will not be added to the party");
                    return null;
                }

            case "Skeleton":
                if(parameters.length == 5){
                    try{
                        return new Skeleton(parameters[1].trim(), Integer.parseInt(parameters[2].trim()),
                                Integer.parseInt(parameters[3].trim()), hpBound(1.0, 30.0, Double.parseDouble(parameters[4].trim())));
                    }
                    catch (NumberFormatException e){
                        return null;
                    }
                }else{
                    Printer.printFormatted("ERROR: incorrect number of parameters for Skeleton. This Character will not be added to the party");
                    return null;
                }

            default:
                Printer.printFormatted("ERROR: Character " + parameters[0] + " not found");
                return null;
        }
    }

    //Function that uses .class references to invoke a constructor of possible class types at random
    public static Character getRandom() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int rndClassIndex = (int) Math.floor(Math.random() * possibleClasses.length);
        Character charRnd = (Character) possibleClasses[rndClassIndex].getDeclaredConstructor().newInstance();
        return charRnd;
    }

    //Intermediary method to pass control to subclass implementations of character creation function based on user input.
    public static Character createCustom(){
        Scanner scanner = new Scanner(System.in);
        Printer.printFormatted("Choose a class for your new Character: ");
        boolean chosen = false;
        while (!chosen) {
            for (int i = 0; i < classNames.length; i++) {
                Printer.printFormatted((i + 1) + ". " + classNames[i]);
            }
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice){
                    case 1:
                        chosen = true;
                        return Warrior.createCustom();
                    case 2:
                        chosen = true;
                        return Wizard.createCustom();
                    case 3:
                        chosen = true;
                        return Archer.createCustom();
                    case 4:
                        chosen = true;
                        return Rogue.createCustom();
                    case 5:
                        chosen = true;
                        return Necromancer.createCustom();
                    default:
                        break;
                }

            }
            catch (NumberFormatException e){
                Printer.printFormatted("Please choose a valid option!");
            }
        }
        return null;
    }
}
