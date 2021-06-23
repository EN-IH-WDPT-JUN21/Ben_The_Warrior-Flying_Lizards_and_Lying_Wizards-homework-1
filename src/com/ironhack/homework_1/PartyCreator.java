package com.ironhack.homework_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PartyCreator {
    public static Party importParty(String csvFile) throws FileNotFoundException {

        // Initialization of party to be returned
        Party importedParty = new Party();

        // Add ".csv" to the file name if it was not provided
        if(csvFile.length() <= 4){
            csvFile = csvFile.concat(".csv");
        }else{
            if(csvFile.contains(".") && !csvFile.endsWith(".csv")){
                System.err.println("ERROR: Party must be imported from .csv file");
                return null;
            }else{
                csvFile = csvFile.concat(".csv");
            }
        }

        // Search the file in the parties folder and if not found verify if the full directory was provided
        File file = new File("parties/" + csvFile);
        if(!file.exists()) {
            file = new File(csvFile);
            if (!file.exists()) {
                System.err.println("ERROR: File not found! Make sure the .csv file is in the parties folder or the " +
                        "correct directory is provided");
                return null;
            }
        }

        // Scanner initialization
        Scanner scanner = new Scanner(file);

        // While loop to add each Character to the Party
        try{
            while(scanner.hasNextLine()){
                String[] newCharacter = scanner.nextLine().split(",");
                switch (newCharacter[0]){
                    case "warrior":
                        if(newCharacter.length != 7){
                            System.err.println("ERROR: Warrior with incorrect number of parameters!");
                            return null;
                        }
                        importedParty.addCharacter(new Warrior(Integer.parseInt(newCharacter[1]), newCharacter[2],
                                Integer.parseInt(newCharacter[3]), Boolean.parseBoolean(newCharacter[4]),
                                Integer.parseInt(newCharacter[5]), Integer.parseInt(newCharacter[6])));
                        break;
                    case "wizard":
                        if(newCharacter.length != 7){
                            System.err.println("ERROR: Wizard with incorrect number of parameters!");
                            return null;
                        }
                        importedParty.addCharacter(new Wizard(Integer.parseInt(newCharacter[1]), newCharacter[2],
                                Integer.parseInt(newCharacter[3]), Boolean.parseBoolean(newCharacter[4]),
                                Integer.parseInt(newCharacter[5]), Integer.parseInt(newCharacter[6])));
                        break;
                    default:
                        System.err.println("ERROR: Unknown Character!");
                        return null;
                }
                scanner.close();
            }
        }catch(Exception e){
            System.err.println("ERROR: Incorrect argument!");
            return null;
        }
        return importedParty;
    }

    public static Party importParty() throws FileNotFoundException {
        // Try to open file navigator
        File file = new File("");
        Scanner scanner = new Scanner(file);

        return null;
    }
    public static void saveParty(Party party, String csvFile) throws IOException {
        // Save passed in Party party to file <fileName>.csv in the default folder

        if (csvFile.length() > 4) {
            if (csvFile.contains(".") && !csvFile.endsWith(".csv")) {
                System.err.println("ERROR: File extension changed to .csv");
                csvFile = csvFile.split("\\.")[0];
            }
        }
        csvFile = csvFile.concat(".csv");

        FileWriter fileWriter = new FileWriter(csvFile);

        List<Character> characterList = party.getPartyCharacters();

        for(Character character : characterList){
            fileWriter.write(character.toCsvFormat());
        }


    }
    public static Party randomParty(){
        Random randomInt = new Random(20);
        Random randomCharacter = new Random(1);
        Party party = new Party();
        for(int i = 0; i < randomInt.nextInt(); i++){
            switch (randomCharacter.nextInt()){
                case 0:
                    party.addCharacter(new Warrior());
                    break;
                case 1:
                    party.addCharacter(new Wizard());
                    break;
            }
        }
        return party;
    }

    // We could also have the option to save a warrior or a wizard to a csv file instead of directly a party

    public static void addCharacter(Party party, Character character) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // add Warrior warrior to the Party party
        Character.getRandom();
    }
}
