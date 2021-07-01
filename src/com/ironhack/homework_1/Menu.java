package com.ironhack.homework_1;


import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private static final PartyCreator pc = new PartyCreator();
    private static final Party party1 = new Party("Player 1");
    private static final Party party2 = new Party("Player 2");
    private static final Scanner scanner = new Scanner(System.in);

    public static void printGraveyard(){
        //
    }

    public static void partyManagement(){

    }

    public static void partyManagement_importParty(){
        /*Strng
        while(true){
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            System.out.println("|             1 - To Player 1             |             2 - To Player 2             |                b - Back                 |");
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Import party -> To Player 1
                case "1":
                    depth3 = true;
                    while(depth3){
                        try{
                            System.out.println("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party1, input);
                            depth3 = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Import party -> To Player 2
                case "2":
                    depth3 = true;
                    while(depth3){
                        try{
                            System.out.println("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party2, input);
                            depth3 = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Import party -> Back
                case "b":
                    depth2 = false;
                    break;
                default:
                    System.out.println("Select a valid option...");
                    break;
            }
        }*/
    }
    public static void partyManagement_exportParty(){

    }
    public static void partyManagement_createManually(){

    }
    public static void partyManagement_randomParty(){

    }

     public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
            System.out.println("Welcome to the ECC Flying Lizards and Lying Wizards game!");
            System.out.println("Choose your players");
            List<Character> graveyard = new ArrayList<>();
            String input = "";

            boolean isRunning = true;

            while(isRunning){
                boolean mainMenu = true;
                boolean depth1 = false;
                boolean depth2 = false;
                boolean depth3 = false;
                boolean depth4 = false;
                while(mainMenu){
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                    System.out.println("|  _____ _     _            __    _               _                  _    __        _            _ _ _ _               _      |");
                    System.out.println("| |   __| |_ _|_|___ ___   |  |  |_|___ ___ ___ _| |___    ___ ___ _| |  |  |   _ _|_|___ ___   | | | |_|___ ___ ___ _| |___  |");
                    System.out.println("| |   __| | | | |   | . |  |  |__| |- _| .'|  _| . |_ -|  | .'|   | . |  |  |__| | | |   | . |  | | | | |- _| .'|  _| . |_ -| |");
                    System.out.println("| |__|  |_|_  |_|_|_|_  |  |_____|_|___|__,|_| |___|___|  |__,|_|_|___|  |_____|_  |_|_|_|_  |  |_____|_|___|__,|_| |___|___| |");
                    System.out.println("|         |___|     |___|                                                      |___|     |___|                                |");
                    System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
                    System.out.println("|  1 - Party management  |        2 - Play        |   3 - Show graveyard    |      4 - Settings      |        x - Quit        |");
                    System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
                    input = scanner.nextLine();
                    switch (input.toLowerCase()){
                        // Party management
                        case "1":
                            depth1 = true;
                            while(depth1){
                                System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
                                System.out.println("|    1 - Import party    |    2 - Export party    |   3 - Create manually   |    4 - Random party    |        b - Back        |");
                                System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
                                input = scanner.nextLine();
                                switch (input.toLowerCase()){
                                    // Party management -> Import party
                                    case "1":
                                        depth2 = true;
                                        while(depth2){
                                            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
                                            System.out.println("|             1 - To Player 1             |             2 - To Player 2             |                b - Back                 |");
                                            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
                                            input = scanner.nextLine();
                                            switch (input.toLowerCase()){
                                                // Party management -> Import party -> To Player 1
                                                case "1":
                                                    depth3 = true;
                                                    while(depth3){
                                                        try{
                                                            System.out.println("What is the file you want to import from?");
                                                            input = scanner.nextLine();
                                                            pc.importParty(party1, input);
                                                            depth3 = false;
                                                        }catch (FileNotFoundException e){
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                                // Party management -> Import party -> To Player 2
                                                case "2":
                                                    depth3 = true;
                                                    while(depth3){
                                                        try{
                                                            System.out.println("What is the file you want to import from?");
                                                            input = scanner.nextLine();
                                                            pc.importParty(party2, input);
                                                            depth3 = false;
                                                        }catch (FileNotFoundException e){
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                                // Party management -> Import party -> Back
                                                case "b":
                                                    depth2 = false;
                                                    break;
                                                default:
                                                    System.out.println("Select a valid option...");
                                                    break;
                                            }
                                        }
                                        break;
                                    // Party management -> Export party
                                    case "2":
                                        depth2 = true;
                                        while(depth2) {
                                            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
                                            System.out.println("|           1 - Export Player 1           |           2 - Export Player 2           |                b - Back                 |");
                                            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
                                            input = scanner.nextLine();
                                            switch (input.toLowerCase()){
                                                // Party management -> Export party -> Export Player 1
                                                case "1":
                                                    depth3 = true;
                                                    while(depth3){
                                                        try{
                                                            System.out.println("What is the file you want to export to?");
                                                            input = scanner.nextLine();
                                                            pc.saveParty(party1, input);
                                                            depth3 = false;
                                                        }catch (FileNotFoundException e){
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                                // Party management -> Export party -> Export Player 2
                                                case "2":
                                                    depth3 = true;
                                                    while(depth3){
                                                        try{
                                                            System.out.println("What is the file you want to export to?");
                                                            input = scanner.nextLine();
                                                            pc.importParty(party2, input);
                                                            depth3 = false;
                                                        }catch (FileNotFoundException e){
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                                // Party management -> Export party -> Back
                                                case "b":
                                                    depth2 = false;
                                                    break;
                                                default:
                                                    System.out.println("Select a valid option...");
                                                    break;
                                            }
                                        }
                                        break;
                                    // Party management -> Create manually
                                    case "3":
                                        depth2 = true;
                                        while(depth2){
                                            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
                                            System.out.println("|         1 - Party 1          |         2 - Party 2           |     3 - Single character      |           b - Back           |");
                                            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
                                            input = scanner.nextLine();
                                            switch (input.toLowerCase()){
                                                // Party management -> Create manually -> Party 1
                                                case "1":
                                                    depth3 = true;
                                                    if(!party1.getPartyCharacters().isEmpty()){
                                                        depth4 = true;
                                                        while(depth4){
                                                            System.out.println("Do you want to delete the characters previously added to this party? [ yes | no ]");
                                                            input = scanner.nextLine();
                                                            switch (input.toLowerCase().charAt(0)){
                                                                case 'y':
                                                                    party1.clearParty();
                                                                    depth4 = false;
                                                                    break;
                                                                case 'n':
                                                                    depth4 = false;
                                                                    System.out.println("Previously added characters preserved!");
                                                                    break;
                                                                default:
                                                                    System.out.println("Select a valid option...");
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                    System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
                                                    System.out.println("|            Party size (" + party1.getPartyCharacters().size() + "/10)            |            2 - Add Character            |                b - Back                 |");
                                                    System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
                                                    input = scanner.nextLine();
                                                    switch (input.toLowerCase()){}
                                                    break;
                                                case "2":
                                                    break;
                                                case "3":
                                                    break;
                                                case "b":
                                                    depth2 = false;
                                                    break;
                                                default:
                                                    System.out.println("Select a valid option...");
                                                    break;
                                            }
                                        }
                                        break;
                                    //Party management -> Random party
                                    case "4":
                                        depth2 = true;
                                        while(depth2){
                                            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
                                            System.out.println("|       1 - Import party       |       2 - Export party        |       3 - Create party        |           b - Back           |");
                                            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
                                        }
                                        break;
                                    case "b":
                                        depth1 = false;
                                        break;
                                    default:
                                        System.out.println("Select a valid option...");
                                        break;
                                }
                            }
                            break;
                        case "2":

                            break;
                        case "3":
                            break;
                        case "4":
                            break;
                        case "x":
                            mainMenu = false;
                            isRunning = false;
                            break;
                        default:
                            System.out.println("Select a valid option...");
                            break;
                    }
                }
            }



            /*while (isRunning) {

                System.out.println("\nHere are your options...");
                System.out.println("1 -> Create a Character individually customizing his/her stats and name in party 1");
                System.out.println("2 -> Create a Character individually customizing his/her stats and name in party 2");
                System.out.println("3 -> Create a full party of randomly generated Characters in party 1");
                System.out.println("4 -> Create a full party of randomly generated Characters in party 2");
                System.out.println("5 -> Import a party using a CSV file in party 1");
                System.out.println("6 -> Import a party using a CSV file in party 2");
                System.out.println("x -> Go to the next step");

                String option = scanner.nextLine();
                switch (option) {
                    case "1":   // Create Warrior individually customizing his/her stats and name
                        pc.addCharacter(party1);
                        party1.printPartyStats();
                        break;
                    case "2":   // Create Wizard individually customizing his/her stats and name
                        pc.addCharacter(party2);
                        party2.printPartyStats();
                        break;
                    case "3":   // Create a full party of randomly generated Wizards and Warriors ");
                        party1 = pc.randomParty(party1);
                        party1.printPartyStats();
                        break;
                    case "4":
                        party2 = pc.randomParty(party2);
                        party2.printPartyStats();
                        break;
                    case "5":   // Import a party using a CSV file
                        System.out.println("Write the path to CSV file with its name:");
                        String csvpath1 = scanner.nextLine();
                        party1 = pc.importParty(csvpath1);
                        party1.printPartyStats();
                        break;
                    case "6":   // Import a party using a CSV file
                        System.out.println("Write the path to CSV file with its name:");
                        String csvpath2 = scanner.nextLine();
                        party2 = pc.importParty(csvpath2);
                        party2.printPartyStats();
                        break;
                    case "x":   // Exit program
                        isRunning = false;
                        break;
                    default:    // Repeats...
                        System.out.println("Select a valid option...");
                        break;
                }
            }

        System.out.println("Now you can begin...");

        boolean isRunning2 = true;
        while (isRunning2) {

            System.out.println("5 -> Start single battle between individuals");
            System.out.println("6 -> Simulate whole party fights with one command by choosing random fighters on each side");
            System.out.println("7 -> Show the graveyard");
            System.out.println("8 -> Show a log");
            System.out.println("9 -> Export party into an importable CSV file");
            System.out.println("x -> Exit menu");
            String option = scanner.nextLine();
            switch (option) {
                case "5":   // Start single battle between individuals
                    System.out.println("Battle...");
                    BattleSimulator bt = new BattleSimulator(party1, party2);
                    bt.battle();
                    //isRunning2 = false;
                    break;
                case "6":   // Simulate whole party fights with one command
                    System.out.println("Battle of the armies...");
                    BattleSimulator bt2 = new BattleSimulator(party1, party2);
                    bt2.battleRandom();
                    bt2.printGraveyard();
                    //isRunning2 = false;
                    break;
                case "7":   // Show the graveyard
                    for(Character chr : graveyard){
                        chr.printStats();
                    }
                    //System.out.println("Graveyard:");
                    //printGraveyard();
                    //isRunning2 = false;
                    break;
                case "8":   // Show a log
                    System.out.println("Log:");
                    //isRunning2 = false;
                    break;
                case "9":   // Export party into an importable CSV file
                    System.out.println("Where do you want to save the party?");
                    String fileName = scanner.nextLine();
                    pc.saveParty(party1, fileName);
                    //isRunning2 = false;
                    break;
                case "10":   // Export party into an importable CSV file

                    break;
                case "x":   // Exit program
                    isRunning2 = false;
                    break;
                default:    // Repeats...
                    System.out.println("Select a valid option...");
                    break;
            }
        }*/
    }
}
