package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Party {
    private List<Character> partyCharacters;
//    private String partyName;     // Not sure if you want to give the party a name

    public Party(List<Character> partyCharacters) {
        this.partyCharacters = partyCharacters;
    }

    public Party() {
        this.partyCharacters = new ArrayList<>();
    }

    // ========== GETTERS AND SETTERS ==========
    public List<Character> getPartyCharacters() {
        return partyCharacters;
    }

    public void setPartyCharacters(List<Character> partyCharacters) {
        this.partyCharacters = partyCharacters;
    }

    // ========== EXTRA Methods ==========

    //Adds character to party list
    // TODO(JA) - Might be useful to merge with similar methods (when cleaning the code)
    public void addCharacterToParty(Character character) {
        partyCharacters.add(character);
    }

    //Removes character from party list (by character)
    public void removeCharacter(Character character) {
        this.getPartyCharacters().remove(character);
    }
    //Removes character from party list (by index)
    public void removeCharacter(int i) {
        this.getPartyCharacters().remove(i);
    }

    // Returns a random character from the party
    public Character getRandomCharacter() {
        Random rand = new Random();
        return partyCharacters.get(rand.nextInt(partyCharacters.size()));
    }

    // TODO(JA) Needs to be tested - logPartyStats method needs to work.
    // Selects character from the party
    public Character selectCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a Character from your party: ");

//        logPartyStats();   // Might be betters to have a print method separate for each character (and print as loop)

        // Maybe something like this but as a Character Method
        for (int i = 0; i < partyCharacters.size(); i++) {
            System.out.println("(" + (i + 1) + ")" + "\t" + partyCharacters.get(i).getClass().getSimpleName());
            System.out.println("\t\tName: " + partyCharacters.get(i).getName);
            System.out.println("\t\tHP: " + partyCharacters.get(i).getHp);
            if (partyCharacters.get(i).getClass().getSimpleName().equals("Warrior")) {
                System.out.println("\t\tStamina: " + partyCharacters.get(i).getStamina);
                System.out.println("\t\tStrength: " + partyCharacters.get(i).getStrength + "\n");
            }
            if (partyCharacters.get(i).getClass().getSimpleName().equals("Wizard")) {
                System.out.println("\t\tMana: " + partyCharacters.get(i).getMana);
                System.out.println("\t\tIntelligence: " + partyCharacters.get(i).getIntelligence + "\n");
            }
        }
        while (true) {
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice < partyCharacters.size()) return partyCharacters.get(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please choose a valid option!");
            }
        }
    }


    // Returns the index of the character in the party list (can be useful for further methods)
    public int getIdxInParty(Character character) {
        return this.getPartyCharacters().indexOf(character);
    }


    // TODO(JA) Needs to be tested - not sure if getMethods from Warrior and Wizard can be used on Character "character".
    // Prints each character from the party with their stats.
    // (Can be divided if we create a method to print a single character stats)
    public void logPartyStats() {
//        System.out.println(partyName.toUpperCase());
        for (Character character : partyCharacters) {
            System.out.println("Class: " + character.getClass().getSimpleName());
            System.out.println("ID: " + character.getId);
            System.out.println("Name: " + character.getName);
            System.out.println("HP: " + character.getHp);

            if (character.getClass().getSimpleName().equals("Warrior")) {
                System.out.println("Stamina: " + character.getStamina);
                System.out.println("Strength: " + character.getStrength + "\n");
            }
            if (character.getClass().getSimpleName().equals("Wizard")) {
                System.out.println("Mana: " + character.getMana);
                System.out.println("Intelligence: " + character.getIntelligence + "\n");
            }
        }
    }
}
