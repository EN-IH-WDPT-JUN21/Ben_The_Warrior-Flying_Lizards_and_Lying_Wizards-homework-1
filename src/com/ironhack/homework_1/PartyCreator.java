package com.ironhack.homework_1;

public class PartyCreator {
    public Party importParty(String csvFile){
        // we could have a default folder for the csv files and search there directly
        // if a directory is provided instead of just a file name we could then search that specific directory
        // check if the file name already has the .csv extension or not
        return null; // return imported party
    }
    public void saveParty(Party party, String fileName){
        // Save passed in Party party to file <fileName>.csv in the default folder
    }
    public Party randomParty(){
        return null; // create and return random Party
    }

    // We could also have the option to save a warrior or a wizard to a csv file instead of directly a party

    public void addCharacter(Party party, Character character){
        // add Warrior warrior to the Party party
    }
}
