package com.ironhack.homework_1;


import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static String[] battle(Party party1, Party party2){
        // create battle simulator with the menu's parties and do the battle and return the resulting graveyard
        /*
        Check party1 and party2 have >0 Characters
        BattleSimulator battleSimulator = new battleSimulator(Party1, Party2)
        return battleSimulator.battle()
        */
        return null; // return the graveyard
    }

    public void printGraveyard(){
        //
    }


     public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
            Scanner scanner = new Scanner(System.in);
            PartyCreator pc = new PartyCreator();
            System.out.println("Welcome to the ECC Flying Lizards and Lying Wizards game!");
            System.out.println("Choose your players");
            Party party1 = new Party("Player 1");
            Party party2 = new Party("Player 2");
            List<Character> graveyard = new ArrayList<>();

            boolean isRunning = true;
            while (isRunning) {
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
                    /*System.out.println("Where do you want to save the party?");
                    String fileName = scanner.nextLine();
                    pc.saveParty(fileName, party1);
                    //isRunning2 = false;*/
                    break;
                case "x":   // Exit program
                    isRunning2 = false;
                    break;
                default:    // Repeats...
                    System.out.println("Select a valid option...");
                    break;
            }
        }
    }
}
