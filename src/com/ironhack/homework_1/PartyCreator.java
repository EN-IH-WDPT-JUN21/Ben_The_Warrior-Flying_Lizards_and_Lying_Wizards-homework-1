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

        String[] directoryAndFile = directoryAndName(csvFile);
        String directory = directoryAndFile[0];
        csvFile = directoryAndFile[1];

        // Add ".csv" to the file name if it was not provided
        csvFile = csvCheck(csvFile, true);

        if(csvFile == null) {
            return null;
        }

        // Search the file in the parties folder and if not found verify if the full directory was provided
        File file = new File("parties/" + csvFile);
        if(!file.exists()) {
            file = new File(directory + csvFile);
            if (!file.exists()) {
                System.err.println("ERROR: File not found! Make sure the .csv file is in the folder parties or the " +
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
                Character character = Character.addCharacter(newCharacter);
                if(character != null){
                    importedParty.addCharacter(Character.addCharacter(newCharacter));
                }
            }
            scanner.close();
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

        String[] directoryAndFile = directoryAndName(csvFile);
        String directory = directoryAndFile[0];
        csvFile = directoryAndFile[1];

        csvFile = csvCheck(csvFile, false);
        if(csvFile == null) {
            return;
        }
        csvFile = csvFile.replaceAll("[\\\\/:*?\"<>|«»]", "");

        if(!directory.equals("") && !directory.equals("parties/")){
            System.out.println("Warning: file saved in parties folder!");
        }

        csvFile = "parties/".concat(csvFile);
        FileWriter fileWriter = new FileWriter(csvFile, false);

        List<Character> characterList = party.getPartyCharacters();

        for(Character character : characterList){
            fileWriter.write(character.toCsvFormat());
        }
        fileWriter.flush();
        fileWriter.close();

        if(csvFile.equals(".csv")){
            System.out.println("Warning: party saved in file parties/unnamed.csv");
        }else if(!csvFile.equals(directoryAndFile[1])){
            System.out.println("Warning: party saved in " + csvFile);
        }

    }
    public static Party randomParty(){
        // Find way to know each of the subclasses of Character?

        Random randomInt = new Random(30);
        Party party = new Party();
        int numCharacters = randomInt.nextInt();
        for(int i = 0; i < randomInt.nextInt(); i++){
            party.addCharacter(Character.getRandom());
        }
        return party;
    }

    public static void addCharacter(Party party){
        // add Warrior warrior to the Party party
        party.addCharacter(Character.createCustom());
    }

    public static void addCharacter(String csvFile) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationExc{
        csvFile = csvCheck(csvFile, false);
        if(csvFile == null) {
            return;
        }

        String[] directoryAndFile = directoryAndName(csvFile);
        String directory = directoryAndFile[0];
        csvFile = directoryAndFile[1];

        if(!directory.equals("") && !directory.equals("parties/")){
            System.out.println("Warning: file saved in parties folder!");
        }

        csvFile = "parties/".concat(csvFile);
        FileWriter fileWriter = new FileWriter(csvFile,true);

        fileWriter.write(Character.createCustom().toCsvFormat());
        fileWriter.flush();
        fileWriter.close();
    }

    private static String csvCheck(String csvFile, boolean toRead){
        // Add ".csv" to the file name if it was not provided
        if(csvFile.length() > 100){
            System.err.println("ERROR: please make sure the file name has less than 100 characters");
            return null;
        }

        if(!csvFile.contains(".")){
            csvFile = csvFile.concat(".csv");
        }else{
            if(csvFile.contains(".") && !csvFile.endsWith(".csv") && toRead) {
                System.err.println("ERROR: Party must be imported from .csv file");
                return null;
            }else if(csvFile.contains(".") && !csvFile.endsWith(".csv") && !toRead){
                System.err.println("Warning: file extension changed to .csv");
                csvFile = csvFile.split("\\.")[0];
                csvFile = csvFile.concat(".csv");
            }else if(!csvFile.endsWith(".csv")){
                csvFile = csvFile.concat(".csv");
            }
        }
        return csvFile;
    }

    private static String[] directoryAndName(String csvFile){
        String[] csvFileParts = csvFile.split("/");
        if(csvFileParts.length == 1){
            return new String[]{"", csvFile};
        }else{
            String directory = "";
            for(int i = 0; i < csvFileParts.length - 2; i++){
                directory = directory.concat(csvFileParts[i]).concat("/");
            }
            return new String[]{directory, csvFileParts[csvFileParts.length-1]};
        }
    }
}
