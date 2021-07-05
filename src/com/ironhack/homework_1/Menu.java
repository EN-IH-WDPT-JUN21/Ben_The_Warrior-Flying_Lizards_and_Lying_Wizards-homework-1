package com.ironhack.homework_1;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final PartyCreator pc = new PartyCreator();
    private static final Party party1 = new Party("Player 1");
    private static final Party party2 = new Party("Player 2");
    private static final Scanner scanner = new Scanner(System.in);
    private static final BattleSimulator bt = new BattleSimulator(party1, party2);
    private static boolean smallLog = false;
    private static boolean hardcore = false;
    private static int partySize = 5;
    private static int battleSpeed = 0;

    public static int getBattleSpeed() {
        return battleSpeed;
    }

    public static void battleSpeedPause() {
        try {
            if (Menu.getBattleSpeed()==1) Thread.sleep(150);
            if (Menu.getBattleSpeed()==2) Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean getSmallLog() {
        return smallLog;
    }

    public static boolean isHardcore() {
        return hardcore;
    }

    public static void setHardcore(boolean hardcore) {
        Menu.hardcore = hardcore;
    }

    public static int getPartySize(){
        return partySize;
    }

    public static void setPartySize(){
        // loops party size limit through the values 5, 10 and 20
        if(Menu.partySize == 20){
            // when changing the limit from 20 to 5, if a party has more than the 5 elements the user is asked if he
            // wants to proceed and clear the parties previously created or cancel the settings change
            if(party1.getPartyCharacters().size() > 5 || party2.getPartyCharacters().size() > 5){
                int confirmation = askYesNoBack("By changing the party size limit, the previously created parties will be erased. Confirm changes? [ yes | no ]", false);
                if(confirmation == 1){
                    party1.clearParty();
                    party2.clearParty();
                    Menu.partySize = 5;
                }
            }
        }else if(Menu.partySize == 5){
            Menu.partySize = 10;
        }else if(Menu.partySize == 10){
            Menu.partySize = 20;
        }
    }

    public static Party getParty1() {
        return party1;
    }

    public static Party getParty2() {
        return party2;
    }

    public static String centerString(String middle, int width){
        // method used to center a string in a string of length width and surrounded by |
        StringBuilder str = new StringBuilder("|");
        // calculation of the number of spaces required
        int middleLength = middle.length();
        int spaces = width - middleLength;
        // if the number of spaces is even they are divided equally before and after the String middle
        // if the number of spaces is odd they are divided such a way that after the String middle there is one more space
        boolean even = spaces % 2 == 0;
        str.append(String.join("", Collections.nCopies(spaces / 2, " ")));
        str.append(middle);
        if(even){
            str.append(String.join("", Collections.nCopies(spaces / 2, " ")));
        }else{
            str.append(String.join("", Collections.nCopies((spaces / 2) + 1, " ")));
        }
        str.append("|");
        return str.toString();
    }

    // Menu -> Party management
    // menu where the party management options are available
    public static void partyManagement() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input = "";
        while (true) {
            // prints of available parties and party management options
            Printer.printChosenMenus(new String[]{"PARTY - " + party1.getPartyName(), "PARTY - " + party2.getPartyName()}, false, true);
            Printer.partyPrint(party1, party2);
            Printer.printChosenMenus(new String[]{"1 - Import party", "2 - Export party", "3 - Create manually", "4 - Random party", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Import party
                case "1":
                    partyManagement_importParty();
                    break;
                // Party management -> Export party
                case "2":
                    partyManagement_exportParty();
                    break;
                // Party management -> Create manually
                case "3":
                    partyManagement_createManually();
                    break;
                //Party management -> Random party
                case "4":
                    partyManagement_randomParty();
                    break;
                // Return to the main menu
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Party management -> Import party
    // menu to import parties from csv
    public static void partyManagement_importParty(){
        String input = "";
        boolean running = false;
        while(true){
            // print of import options
            Printer.printChosenMenus(new String[]{"1- To Player 1", "2 - To Player 2", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Import party -> To Player 1
                case "1":
                    running = true;
                    while(running){
                        // if file was found add each character to the Player 1, else print error message
                        // if import fails verify if user wants to retry import
                        try{
                            Printer.printFormatted("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party1, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                            if(Menu.askYesNoBack("Retry import? [ yes | no ]", false) == 2){
                                running = false;
                            }
                        }
                    }
                    break;
                // Party management -> Import party -> To Player 2
                case "2":
                    running = true;
                    while(running){
                        // if file was found add each character to the Player 2, else print error message
                        // if import fails verify if user wants to retry import
                        try{
                            Printer.printFormatted("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party2, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                            if(Menu.askYesNoBack("Retry import? [ yes | no ]", false) == 2){
                                running = false;
                            }
                        }
                    }
                    break;
                // Return to Party management
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Party management -> Export party
    // menu to export parties to csv
    public static void partyManagement_exportParty() throws IOException {
        String input = "";
        boolean running = false;

        while(true) {
            // print of export options
            Printer.printChosenMenus(new String[]{"1 - Export Player 1", "2 - Export Player 2", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Export party -> Export Player 1
                case "1":
                    // if party is empty warn user and ask if wants to proceed
                    running = true;
                    if (party2.getPartyCharacters().isEmpty()){
                        Printer.printFormatted("Party is empty! Exporting will create an empty .csv file.");
                        if(Menu.askYesNoBack("Do you wish to proceed? [ yes | no ]", false) == 2){
                            running = false;
                        }
                    }
                    // ask for the file to export
                    // if not able to save ask user if he wants to retry export process
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.saveParty(party1, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                            if(Menu.askYesNoBack("Retry export? [ yes | no ]", false) == 2){
                                running = false;
                            }
                        }
                    }
                    break;
                // Party management -> Export party -> Export Player 2
                case "2":
                    // if party is empty warn user and ask if wants to proceed
                    running = true;
                    if (party2.getPartyCharacters().isEmpty()){
                        Printer.printFormatted("Party is empty! Exporting will create an empty .csv file.");
                        if(Menu.askYesNoBack("Do you wish to proceed? [ yes | no ]", false) == 2){
                            running = false;
                        }
                    }
                    // ask for the file to export
                    // if not able to save ask user if he wants to retry export process
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.saveParty(party2, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                            if(Menu.askYesNoBack("Retry export? [ yes | no ]", false) == 2){
                                running = false;
                            }
                        }
                    }
                    break;
                // Return to Party management
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Party management -> Create manually
    // menu where the options to manually create characters are available
    public static void partyManagement_createManually(){
        String input = "";
        while(true){
            // print manual character creation options
            Printer.printChosenMenus(new String[]{"1 - Player 1", "2 - Player 2", "3 - Single character", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Create manually -> Party 1
                case "1":
                    partyManagement_createManually_party(party1);
                    break;
                // Party management -> Create manually -> Party 2
                case "2":
                    partyManagement_createManually_party(party2);
                    break;
                // Party management -> Create manually -> Single character
                case "3":
                    partyManagement_createManually_singleCharacter();
                    break;
                // Return to Party management
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Party management -> Create manually -> Party
    // menu to add manually characters to a specific party
    public static void partyManagement_createManually_party(Party party){
        String input = "";
        // if party is not empty, ask the user if the party should be erased
        if(!party.getPartyCharacters().isEmpty()){
            int confirmation = askYesNoBack("Do you want to delete the characters previously added to this party? [ yes | no ]",false);
            if (confirmation==1){
                party.clearParty();
            }else{
                Printer.printFormatted("Previously added characters preserved!");
            }
        }
        // Print party elements and, the current size and limit and the available options (add, delete, back)
        while(true){
            String partyInfo = "Party size (" + party.getPartyCharacters().size() + "/" + Menu.getPartySize() + ")";
            Printer.printChosenMenus(new String[]{party.getPartyName()}, false, true);
            Printer.partyPrint(party);
            if (party.getPartyCharacters().size() == Menu.getPartySize()){
                Printer.printChosenMenus(new String[]{partyInfo, "1 - Delete character", "b - Back"}, false,false);
            }else if (party.getPartyCharacters().isEmpty()){
                Printer.printChosenMenus(new String[]{partyInfo, "1 - Add character", "b - Back"}, false,false);
            }else {
                Printer.printChosenMenus(new String[]{partyInfo, "1 - Add character", "2 - Delete character", "b - Back"}, false,false);
            }
            // check which options are available to correctly map input to the action to take
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    if (party.getPartyCharacters().size() == Menu.getPartySize()){
                        int confirmation = askYesNoBack("Are you sure you want to delete a character? [ yes | no ]",false);
                        if (confirmation == 1) pc.removeCharacter(party);
                    } else {
                        pc.addCharacter(party);
                    }
                    break;
                case "2":
                    if (party.getPartyCharacters().size() > 0 && party.getPartyCharacters().size() != Menu.getPartySize()){
                        int confirmation = askYesNoBack("Are you sure you want to delete a character? [ yes | no ]",false);
                        if (confirmation == 1) pc.removeCharacter(party);
                    }else{
                        Printer.printFormatted("Select a valid option...");
                    }
                    break;
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Party management -> Create manually -> Single character
    // menu to create a character and add it to a party or export it to a csv file
    public static void partyManagement_createManually_singleCharacter(){
        String input = "";
        while(true) {
            // print manual creation options
            Printer.printChosenMenus(new String[]{"1 - Add to Player 1", "2 - Add to Player 2", "3 - Add to exported party", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()) {
                // Menu -> Party management -> Create manually -> Single character -> Add to Player 1
                // check if party is not full to add a character
                case "1":
                    if (party1.getPartyCharacters().size() < Menu.getPartySize()){
                        pc.addCharacter(party1);
                    }else {
                        Printer.printFormatted("Party limit reached, unable to add more characters! If needed change in settings!");
                    }
                    break;
                // Menu -> Party management -> Create manually -> Single character -> Add to Player 2
                // check if party is not full to add a character
                case "2":
                    if (party2.getPartyCharacters().size() < Menu.getPartySize()){
                        pc.addCharacter(party2);
                    }else {
                        Printer.printFormatted("Party limit reached, unable to add more characters! If needed change in settings!");
                    }
                    break;
                // Menu -> Party management -> Create manually -> Single character -> Add to exported party
                case "3":
                    boolean running = true;
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.addCharacter(input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                // Return to Create manually
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Random party
    // Menu for creating a random party. Selects which party and add random character to them.
    public static void partyManagement_randomParty() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        while(true){
            String input = "";
            Printer.printChosenMenus(new String[]{"1 - Player 1", "2 - Player 2", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":       // Selects party 1
                    if(!party1.getPartyCharacters().isEmpty()){ //Check if empty. Empty: Randomise a new party.
                        // Not Empty: Asks to delete party:
                        // Yes - clears party and randomise a new one.
                        // No - fills the rest of the spaces with new randomised characters.
                        int choice = askYesNoBack("Do you want to delete the existing characters in party 1? [ yes | no | back ]",true);
                        // 1 -yes, 2 -no, 3 -back.
                        if(choice==1){
                            party1.clearParty();
                            pc.randomParty(party1);
                        }else if(choice==2){
                            Printer.printFormatted("Previously added characters preserved!");
                            pc.randomParty(party1);
                        }else{
                            Printer.printFormatted("Random party was not created!");
                            break;
                        }
                    }else{
                        pc.randomParty(party1);
                    }
                    break;
                case "2":   // Selects party 2. Same functionalities as party1.
                    if(!party2.getPartyCharacters().isEmpty()){
                        int choice = askYesNoBack("Do you want to delete the existing characters in party 2? [ yes | no | back ]",true);
                        // 1 -yes, 2 -no, 3 -back.
                        if(choice==1){
                            party2.clearParty();
                            pc.randomParty(party2);
                        }else if(choice==2){
                            Printer.printFormatted("Previously added characters preserved!");
                            pc.randomParty(party2);
                        }else{
                            Printer.printFormatted("Random party was not created!");
                            break;
                        }
                    }else{
                        pc.randomParty(party2);
                    }
                    break;
                case "b":   // Returns to previous menus.
                    return;
                default:    // Shows error and repeats Random party menu.
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Battle
    // Menu to start manual and random battles
    public static void battleMenu() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input = "";
        while(true){
            // Print options available when in hardcore mode and in normal mode
            if (Menu.isHardcore()){
                Printer.printChosenMenus(new String[]{"1 - Manual battle", "b - Back"}, false,false);
            }else{
                Printer.printChosenMenus(new String[]{"1 - 1v1 Duel", "2 - Random battle", "b - Back"}, false,false);
            }
            input = scanner.nextLine();
            int confirmation;
            switch (input){
                // Menu -> Battle -> 1v1 Duel / Manual battle
                // Confirm if player wants to start a battle, create random party for empty parties and start battle
                case "1":
                    confirmation = askYesNoBack("Are you sure you want to start a "+(isHardcore()? "manual battle":"1v1 duel")+"? [ yes | no ]", false);
                    if (confirmation == 1){
                        if(party1.getPartyCharacters().isEmpty()){
                            pc.randomParty(party1);
                        }
                        if(party2.getPartyCharacters().isEmpty()){
                            pc.randomParty(party2);
                        }
                        bt.battle();
                    }
                    break;
                // Menu -> Battle -> Random battle
                // Confirm if player wants to start a battle, create random party for empty parties and start battle
                case "2":
                    confirmation = askYesNoBack("Are you sure you want to start a random battle? [ yes | no ]", false);
                    if (confirmation == 1){
                        if(party1.getPartyCharacters().isEmpty()){
                            pc.randomParty(party1);
                        }
                        if(party2.getPartyCharacters().isEmpty()){
                            pc.randomParty(party2);
                        }
                        bt.battleRandom();
                    }
                    break;
                // Return to Menu
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Menu -> Graveyard
    // Menu where the graveyard is displayed
    public static void printGraveyard(){
        String input = "";
        // print graveyard ascii art
        Printer.printPart("graveyard");
        List<Character> graveyard = bt.graveyard();

        // search for longest name in the dead characters
        int startInt = 0;
        int longestName = 0;
        for (Character character : graveyard) {
            int length = character.getName().length();
            if(longestName < length){
                longestName = length;
            }
        }
        // define how many names appear side by side from the longest name length
        // graveyardLine() method creates the string with multiple names to print
        if(longestName < 15){
            //print 7 names in the same line
            while(startInt < graveyard.size()){
                startInt = Printer.graveyardLine(graveyard,7,new int[]{17,17,17,17,17,17,17},startInt);
            }
        }else if(longestName < 18){
            //print 6 names in the same line
            while(startInt < graveyard.size()){
                startInt = Printer.graveyardLine(graveyard,6,new int[]{20,20,20,20,20,20},startInt);
            }
        }else if(longestName < 22) {
            //print 5 names in the same line
            while (startInt < graveyard.size()) {
                startInt = Printer.graveyardLine(graveyard, 5, new int[]{24, 24, 25, 24, 24}, startInt);
            }
        }else if(longestName < 28){
            //print 4 names in the same line
            while (startInt < graveyard.size()) {
                startInt = Printer.graveyardLine(graveyard, 4, new int[]{30,31,31,30}, startInt);
            }
        }else if(longestName < 39){
            //print 3 names in the same line
            while(startInt < graveyard.size()){
                startInt = Printer.graveyardLine(graveyard,3,new int[]{41,41,41},startInt);
            }
        }else if(longestName < 60){
            //print 2 names in the same line
            while(startInt < graveyard.size()){
                startInt = Printer.graveyardLine(graveyard,2,new int[]{62,62},startInt);
            }
        }else{
            // print only 1 name in the same line
            while(startInt < graveyard.size()){
                startInt = Printer.graveyardLine(graveyard,1,new int[]{125},startInt);
            }
        }
        Printer.printChosenMenus(new String[]{"1 - Clear graveyard", "b - Back"}, false,false);
        while(true){
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Menu -> Graveyard -> Clear graveyard
                // Clears the graveyard
                case "1":
                    bt.clearGraveyard();
                    printGraveyard();
                    // returns to menu so that when the player chooses to go back this method also closes and the previous
                    // graveyard does not appear
                    return;
                // Return to Menu
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    public static void settings(){
        String input = "";
        // String arrays with possible setting options for each setting
        String[] gameMode = {"1 - Game mode [Normal]","1 - Game mode [Hardcore]"};
        String[] logMode = {"2 - Log mode [Reduced Logs]","2 - Log mode [Full Logs]"};
        String[] gameSpeed = {"3 -  Battle speed [Instant]","3 -  Battle speed [Fast]","3 -  Battle speed [Slow]"};
        String[] partySize = {"4 - Party size limit [5]", "4 - Party size limit [10]", "4 - Party size limit [20]"};

        while(true){
            Printer.printPart("settings");
            Printer.printLine(4);
            // create string with current settings and print
            String[] stringArr = new String[]{hardcore ? gameMode[1] : gameMode[0],
                    smallLog ? logMode[0] : logMode[1],
                    battleSpeed == 1 ? gameSpeed[1] : battleSpeed == 2 ? gameSpeed[2] : gameSpeed[0],
                    Menu.getPartySize() == 5 ? partySize[0] : Menu.getPartySize() == 10 ? partySize[1] : partySize[2]};

            Printer.printChosenMenus(stringArr,false,false);

            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Menu -> Settings -> Game mode
                // change the game mode
                case "1":
                    hardcore = !hardcore;
                    if(hardcore){
                        Printer.printPart("hardcore");
                    }
                    break;
                // Menu -> Settings -> Log mode
                // change the log mode
                case "2":
                    smallLog = !smallLog;
                    break;
                // Menu -> Settings -> Battle speed
                // change the battle speed
                case "3":
                    if (battleSpeed == 0) {
                        battleSpeed = 1;
                    } else if (battleSpeed ==1){
                        battleSpeed = 2;
                    }else {
                        battleSpeed = 0;
                    }
                    break;
                // Menu -> Settings -> Party size limit
                // change the party size limit
                case "4":
                    setPartySize();
                    break;
                // Return to Menu
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    // Ask the user for confirmation to delete existing party. y - returns 1. n - returns 2. b - returns 3.
    private static int askYesNoBack(String message,boolean backOption) {
        String input = "";
        while (true){
            Printer.printFormatted(message);
            input = scanner.nextLine();
            if(input.length() == 0){
                input = "empty";
            }
            switch (input.toLowerCase().charAt(0)){
                case 'y':
                    return 1;
                case 'n':
                    return 2;
                case 'b':
                    if (backOption){
                        return 3;
                    } else {
                        Printer.printFormatted("Select a valid option...");
                    }
                    break;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Character> graveyard = new ArrayList<>();
        String input = "";
        boolean isRunning = true;

        while(isRunning){
            boolean mainMenu = true;
            while(mainMenu){
                // Print game name and main menu options
                Printer.printPart("main");
                Printer.printChosenMenus(new String[]{"1 - Party management", "2 - Battle", "3 - Show graveyard", "4 - Settings", "x - Quit"}, false,false);
                input = scanner.nextLine();
                switch (input.toLowerCase()){
                    // Menu -> Party management
                    case "1":
                        partyManagement();
                        break;
                    // Menu -> Battle
                    case "2":
                        battleMenu();
                        break;
                    // Menu -> Show graveyard
                    case "3":
                        printGraveyard();
                        break;
                    // Menu -> Settings
                    case "4":
                        settings();
                        break;
                    // Menu -> Quit
                    case "x":
                        mainMenu = false;
                        isRunning = false;
                        break;
                    default:
                        Printer.printFormatted("Select a valid option...");
                        break;
                }
            }
        }
    }
}
