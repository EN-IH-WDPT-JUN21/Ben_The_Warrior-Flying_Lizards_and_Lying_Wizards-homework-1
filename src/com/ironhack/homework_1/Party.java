package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Party {
    private final int MAX_STARTING_SIZE = 12; // Only used to define the maximum party size for the manual creation
    private List<Character> partyCharacters;
    private String partyName;


    public Party(List<Character> partyCharacters, String partyName) {
        this.partyCharacters = partyCharacters;
        this.partyName = partyName;
    }

    public Party(String partyName) {
        this.partyName = partyName;
    }

    public Party() {
        this.partyCharacters = new ArrayList<>();
    }

    // ========== GETTERS AND SETTERS ==========
    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public List<Character> getPartyCharacters() {
        return partyCharacters;
    }

    public void setPartyCharacters(List<Character> partyCharacters) {
        this.partyCharacters = partyCharacters;
    }

    // ========== Party Creation ==========
    // Method to select the initial size of the party  (USER INPUT METHOD)
    public int inputPartySize() {
        System.out.println("Input the size of the party:    ( maximum " + MAX_STARTING_SIZE + " Characters )");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= MAX_STARTING_SIZE) return choice;
                System.out.println("Please input a valid choice! Maximum " + MAX_STARTING_SIZE + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid number! Maximum " + MAX_STARTING_SIZE + ".");
            }
        }
    }

    // TODO(JA) Needs to be tested and implemented - waiting for merge to test.
    // Generates random party (party still needs to be previously initialized)
    public void generateRandomParty(int partySize) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < partySize; i++) {
            partyCharacters.add(Character.getRandom());
        }
    }


    // TODO(JA) Needs to be tested and implemented - waiting for merge to test.
    // Generates custom party (party still needs to be previously initialized)
    public void generateCustomParty(int partySize) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < partySize; i++) {
            partyCharacters.add(Character.createCustom());

            //optional, used to stop creation method
            int nextChoice = inputExit(true);
            if (nextChoice == -1) return;       // exits
            if (nextChoice == 1) {              // create remaining party size as random. and exits
                generateRandomParty(partySize - i - 1);
                return;
            }
        }
    }

    // Checks if the user wants to continue custom creation  (USER INPUT METHOD)
    // (doesn't need to be included. just for cases where you regret trying to create 30 characters by hand)
    // (adapted so it could be used to exit other method)  (returns -1 to exit and 0 to continue)
    private static int inputExit(boolean isCreating) {
        System.out.println(isCreating ? "Continue custom creation?   (y)Yes   (r)Randomise Remaining   (n)Exit"
                : "Continue operation?   (y)Yes   (n)Exit");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            try {
                switch (input) {
                    case "y":
                        return 0;
                    case "r":
                        if (isCreating) return 1;
                        System.out.println("Please choose a valid option!");
                        break;
                    case "n":
                        return -1;
                    default:
                        System.out.println("Please choose a valid option!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please choose a valid option!");
            }
        }
    }

    // Deletes all elements from party
    public void clearParty() {
        partyCharacters.clear();
        System.out.println(partyName + " cleared!");
    }

    // ========== Characters in Party ==========
    //Adds character to party list
    public void addCharacterToParty(Character character) {
        System.out.println("Added to " + partyName + ": " + character.printStats());
        partyCharacters.add(character);
    }

    //Removes character from party list (by character)
    public void removeCharacter(Character character) {
        System.out.println("Removed from " + partyName + ": " + character.printStats());
        this.getPartyCharacters().remove(character);
    }

    //Removes character from party list (by index)
    public void removeCharacter(int i) {
        System.out.println("Removed from " + partyName + ": " + this.getPartyCharacters().get(i).printStats());
        this.getPartyCharacters().remove(i);
    }

    // Selects character from the party  (USER INPUT METHOD)
    public Character selectCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a Character from your party:");
        for (int i = 0; i < partyCharacters.size(); i++) {
            System.out.println("(" + (i + 1) + ")" + " " + partyCharacters.get(i).printStats());
        }
        while (true) {
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= partyCharacters.size()) return partyCharacters.get(choice - 1);
                System.out.println("Please choose a valid option!");
            } catch (NumberFormatException e) {
                System.out.println("Please choose a valid option!");
            }
        }
    }

    // Returns a random character from the party
    public Character selectRandomCharacter() {
        Random rand = new Random();
        return partyCharacters.get(rand.nextInt(partyCharacters.size()));
    }

    // Returns the index of the character in the party list (can be useful for further methods) (not in use)
    public int getIdxInParty(Character character) {
        return partyCharacters.indexOf(character);
    }

    // ==================== Log ====================
    // Prints each character from the party with their stats, using "Character.printStats()"
    public void printPartyStats() {
        System.out.println("Party - " + partyName);
        for (Character character : partyCharacters) {
            System.out.println(character.printStats());
        }
        System.out.println("");
    }

}
