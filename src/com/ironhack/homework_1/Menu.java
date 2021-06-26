package com.ironhack.homework_1;


import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    }


     public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the ECC Flying Lizards and Lying Wizards game!");
            System.out.println("Choose your players");
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\nHere are your options...");
                System.out.println("1 -> Create Warrior individually customizing his/her stats and name");
                System.out.println("2 -> Create Wizard individually customizing his/her stats and name");
                System.out.println("3 -> Create a full party of randomly generated Wizards and Warriors ");
                System.out.println("4 -> Import a party using a CSV file");
                System.out.println("x -> Go to the next step");

                String option = scanner.nextLine();
                switch (option) {
                    case "1":   // Create Warrior individually customizing his/her stats and name
                        System.out.println("Write the Warrior name:");
                        String name1 = scanner.nextLine();
                        System.out.println("Write the Warrior stamina:");
                        int stamina1 = scanner.nextInt();
                        Party party1= new Party();
                        Warrior warrior1=new Warrior();
                        PartyCreator pc1= new PartyCreator();
                        pc1.addCharacter(party1, warrior1);
                        break;
                    case "2":   // Create Wizard individually customizing his/her stats and name
                        System.out.println("Write the Wizard name:");
                        String name2 = scanner.nextLine();
                        System.out.println("Write the Wizard stamina:");
                        int stamina2 = scanner.nextInt();
                        Party party2= new Party();
                        Wizard wizard1=new Wizard();
                        PartyCreator pc2= new PartyCreator();
                        pc2.addCharacter(party2, wizard1);
                        break;
                    case "3":   // Create a full party of randomly generated Wizards and Warriors ");
                        PartyCreator pc3= new PartyCreator();
                        pc3.randomParty();
                        break;
                    case "4":   // Import a party using a CSV file
                        System.out.println("Write the path to CSV file with its name:");
                        String csvpath = scanner.nextLine();
                        PartyCreator pc4= new PartyCreator();
                        pc4.importParty(csvpath);
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
                    //    BattleSimulator bt = new BattleSimulator(party1, party2);
                    //    bt.battle();
                    //isRunning2 = false;
                    break;
                case "6":   // Simulate whole party fights with one command
                    System.out.println("Battle of the armies...");
                    //    BattleSimulator bt = new BattleSimulator(party1, party2);
                    //    bt.battle();
                    //isRunning2 = false;
                    break;
                case "7":   // Show the graveyard
                    System.out.println("Graveyard:");
                    //printGraveyard();
                    //isRunning2 = false;
                    break;
                case "8":   // Show a log
                    System.out.println("Log:");
                    //isRunning2 = false;
                    break;
                case "9":   // Export party into an importable CSV file
                    System.out.println("Exporting parties to parties.txt");
                    FileWriter writer0 = new FileWriter("parties.txt", false);
                    writer0.write("Example \n");
                    writer0.write("cd \n");
                    writer0.close();
                    File file0= new File("parties.txt");
                    Scanner scanner0 = new Scanner(file0);
                    while (scanner0.hasNextLine()) {
                        String line = scanner0.nextLine();
                        System.out.println(line);

                    }
                    scanner0.close();
                    //isRunning2 = false;
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
