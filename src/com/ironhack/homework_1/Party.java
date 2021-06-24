package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //Adds character to list
    // TODO(JA) - Might be useful to merge with similar methods (when cleaning the code)
    public void addCharacterToParty(Character character) {
        partyCharacters.add(character);
    }

    // Returns a random character from the party
    public Character getRandomCharacter() {
        Random rand = new Random();
        return partyCharacters.get(rand.nextInt(partyCharacters.size()));
    }

    // TODO(JA) Needs to be tested - not sure if getMethods from Warrior and Wizard can be used on Character "cha".
    // Prints each character from the party with their stats.
    // (Can be divided if we create a method to print a single character stats)
    public void logPartyStats() {
//        System.out.println(partyName.toUpperCase());
        for (Character cha : partyCharacters) {
            System.out.println("ID: " + cha.getId);
            System.out.println("Name: " + cha.getName);
            System.out.println("Class: " + cha.getClass().getSimpleName());
            System.out.println("HP: " + cha.getHp);
            if (cha.getClass().getSimpleName().equals("Warrior")) {
                System.out.println("Stamina: " + cha.getStamina);
                System.out.println("Strength: " + cha.getStrength + "\n");
            }
            if (cha.getClass().getSimpleName().equals("Wizard")) {
                System.out.println("Mana: " + cha.getMana);
                System.out.println("Intelligence: " + cha.getIntelligence + "\n");
            }
        }
    }
}
