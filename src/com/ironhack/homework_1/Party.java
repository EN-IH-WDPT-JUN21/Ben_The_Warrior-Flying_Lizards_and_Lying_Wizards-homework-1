package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Party {
    private List<Character> partyCharacters;
    private String partyName;

    // ========== CONSTRUCTORS ==========
    public Party(String partyName) {
        this.partyName = partyName;
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


    // ==================== Characters Management in Party ====================
    //Adds character to party list
    public void addCharacter(Character character) {
        partyCharacters.add(character);
        this.setRepeatedName(character);
        Printer.printFormatted("Added to " + partyName + ": " + character.printStats());
    }

    //Removes character from party list (by character)
    public void removeCharacter(Character character) {
        this.getPartyCharacters().remove(character);
    }

    // Selects character from the party  (USER INPUT METHOD)
    public Character selectCharacter() {
        Scanner scanner = new Scanner(System.in);
        Printer.printChosenMenus(new String[]{getPartyName()}, false, true);
        Printer.printFormatted("Choose a character from your party:");
        // Prints all members of the party with an index (from 1 to n).
        for (int i = 0; i < partyCharacters.size(); i++) {
            Printer.printFormatted("(" + (i + 1) + ")" + " " + partyCharacters.get(i).printStats());
        }
        // Asks user for a valid input and select from the existing characters.
        while (true) {
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= partyCharacters.size()) return partyCharacters.get(choice - 1);
                Printer.printFormatted("Please choose a valid option!");
            } catch (NumberFormatException e) {
                Printer.printFormatted("Please choose a valid option!");
            }
        }
    }

    // Returns a random character from the party
    public Character selectRandomCharacter() {
        Random rand = new Random();
        return partyCharacters.get(rand.nextInt(partyCharacters.size()));
    }

    // Returns the index of the character in the party list.
    public int getIdxInParty(Character character) {
        return partyCharacters.indexOf(character);
    }

    // Renames characters that have repeated names in party. Adds Jr. to the end of the name. Does not include skeletons.
    public void setRepeatedName(Character character) {
        for (Character partyMembers : this.partyCharacters) {
            if (partyMembers.getName().equals(character.getName()) && partyMembers != character && character.getClass() != Skeleton.class) {
                character.setName(character.getName() + " Jr.");
            }
        }
    }

    // Deletes all Characters from party
    public void clearParty() {
        partyCharacters.clear();
        Printer.printFormatted(partyName + " cleared!");
    }

}
