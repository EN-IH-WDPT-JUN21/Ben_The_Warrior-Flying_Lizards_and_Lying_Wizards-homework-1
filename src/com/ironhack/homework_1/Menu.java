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
    public static boolean getHardcore() {
        return hardcore;
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
        if(Menu.partySize == 20){
            if(party1.getPartyCharacters().size() > 5 || party2.getPartyCharacters().size() > 5){
                boolean confirmChange = true;
                while(confirmChange){
                    Printer.printFormatted("By changing the party size limit, the previously created parties will be erased. Confirm changes? [ yes | no ]");
                    String input = scanner.nextLine();
                    if(input.length() == 0){
                        input = "empty";
                    }
                    switch (input.toLowerCase().charAt(0)){
                        case 'y':
                            party1.clearParty();
                            party2.clearParty();
                            confirmChange = false;
                            break;
                        case 'n':
                            confirmChange = false;
                            break;
                        default:
                            Printer.printFormatted("Select a valid option...");
                            break;
                    }
                }
            }
            Menu.partySize = 5;
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
        StringBuilder str = new StringBuilder("|");
        int middleLength = middle.length();
        int spaces = width - middleLength;
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

    public static void partyManagement() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input = "";
        while (true) {
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
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }
    public static void partyManagement_importParty(){
        String input = "";
        boolean running = false;
        while(true){
            Printer.printChosenMenus(new String[]{"1- To Player 1", "2 - To Player 2", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Import party -> To Player 1
                case "1":
                    running = true;
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party1, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Import party -> To Player 2
                case "2":
                    running = true;
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party2, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Import party -> Back
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }
    public static void partyManagement_exportParty() throws IOException {
        String input = "";
        boolean running = false;
        while(true) {
            Printer.printChosenMenus(new String[]{"1 - Export Player 1", "2 - Export Player 2", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Export party -> Export Player 1
                case "1":
                    running = true;
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.saveParty(party1, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Export party -> Export Player 2
                case "2":
                    running = true;
                    while(running){
                        try{
                            Printer.printFormatted("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.saveParty(party2, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            Printer.printFormatted(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Export party -> Back
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }
    public static void partyManagement_createManually(){
        String input = "";
        while(true){
            Printer.printChosenMenus(new String[]{"1 - Player 1", "2 - Player 2", "3 - Single character", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Create manually -> Party 1
                case "1":
                    partyManagement_createManually_party(party1);
                    break;
                case "2":
                    partyManagement_createManually_party(party2);
                    break;
                case "3":
                    partyManagement_createManually_singleCharacter();
                    break;
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }
    public static void partyManagement_createManually_party(Party party){
        String input = "";
        if(!party.getPartyCharacters().isEmpty()){
            boolean deleteNotSelected = true;
            while(deleteNotSelected){
                Printer.printFormatted("Do you want to delete the characters previously added to this party? [ yes | no ]");
                input = scanner.nextLine();
                if(input.length() == 0){
                    input = "empty";
                }
                switch (input.toLowerCase().charAt(0)){
                    case 'y':
                        party.clearParty();
                        deleteNotSelected = false;
                        break;
                    case 'n':
                        deleteNotSelected = false;
                        Printer.printFormatted("Previously added characters preserved!");
                        break;
                    default:
                        Printer.printFormatted("Select a valid option...");
                        break;
                }
            }
        }
        while(true){
            String partyInfo = "Party size (" + party.getPartyCharacters().size() + "/" + Menu.getPartySize() + ")";
            Printer.printChosenMenus(new String[]{party.getPartyName()}, false, true);
            Printer.partyPrint(party);
            if (party.getPartyCharacters().size() == Menu.getPartySize()){
                Printer.printChosenMenus(new String[]{partyInfo, "1 - Delete character", "b - Back"}, false,false);
            }else{
                Printer.printChosenMenus(new String[]{partyInfo, "1 - Delete character", "2 - Add character", "b - Back"}, false,false);
            }
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    boolean deleteNotSelected = true;
                    while(deleteNotSelected){
                        Printer.printFormatted("Are you sure you want to delete a character? [ yes | no ]");
                        input = scanner.nextLine();
                        if(input.length() == 0){
                            input = "empty";
                        }
                        switch (input.toLowerCase().charAt(0)){
                            case 'y':
                                pc.removeCharacter(party);
                                deleteNotSelected = false;
                                break;
                            case 'n':
                                deleteNotSelected = false;
                                break;
                            default:
                                Printer.printFormatted("Select a valid option...");
                                break;
                        }
                    }
                    break;
                case "2":
                    if (party.getPartyCharacters().size() < Menu.getPartySize()){
                        pc.addCharacter(party);
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
    public static void partyManagement_createManually_singleCharacter(){
        String input = "";
        while(true) {
            Printer.printChosenMenus(new String[]{"1 - Add to Player 1", "2 - Add to Player 2", "3 - Add to exported party", "b - Back"}, false,false);
            input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "1":
                    if (party1.getPartyCharacters().size() < Menu.getPartySize()){
                        pc.addCharacter(party1);
                    }else {
                        Printer.printFormatted("Party limit reached, unable to add more characters! If needed change in settings!");
                    }
                    break;
                case "2":
                    if (party2.getPartyCharacters().size() < Menu.getPartySize()){
                        pc.addCharacter(party2);
                    }else {
                        Printer.printFormatted("Party limit reached, unable to add more characters! If needed change in settings!");
                    }
                    break;
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
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }
    public static void partyManagement_randomParty() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        while(true){
            String input = "";
            Printer.printChosenMenus(new String[]{"1 - Player 1", "2 - Player 2", "b - Back"}, false,false);

            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    if(!party1.getPartyCharacters().isEmpty()){
                        boolean deleteNotSelected = true;
                        while (deleteNotSelected){
                            Printer.printFormatted("Are you sure you want to delete the existing characters in party 1? [ yes | no ]");
                            input = scanner.nextLine();
                            if(input.length() == 0){
                                input = "empty";
                            }
                            switch (input.toLowerCase().charAt(0)){
                                case 'y':
                                    party1.clearParty();
                                    pc.randomParty(party1);
                                    deleteNotSelected = false;
                                    break;
                                case 'n':
                                    Printer.printFormatted("Random party was not created!");
                                    deleteNotSelected = false;
                                    break;
                                default:
                                    Printer.printFormatted("Select a valid option...");
                                    break;
                            }
                        }
                    }else{
                        pc.randomParty(party1);
                    }
                    break;
                case "2":
                    if(!party2.getPartyCharacters().isEmpty()){
                        boolean deleteNotSelected = true;
                        while (deleteNotSelected){
                            Printer.printFormatted("Are you sure you want to delete the existing characters in party 2? [ yes | no ]");
                            input = scanner.nextLine();
                            if(input.length() == 0){
                                input = "empty";
                            }
                            switch (input.toLowerCase().charAt(0)){
                                case 'y':
                                    party2.clearParty();
                                    pc.randomParty(party2);
                                    deleteNotSelected = false;
                                    break;
                                case 'n':
                                    Printer.printFormatted("Random party was not created!");
                                    deleteNotSelected = false;
                                    break;
                                default:
                                    Printer.printFormatted("Select a valid option...");
                                    break;
                            }
                        }
                    }else{
                        pc.randomParty(party2);
                    }
                    break;
                case "b":
                    return;
            }
        }
    }

    public static void battleMenu() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input = "";
        boolean running = false;
        while(true){
            boolean confirmBattle = false;
            if (Menu.isHardcore()){
                Printer.printChosenMenus(new String[]{"1 - Manual battle", "b - Back"}, false,false);
            }else{
                Printer.printChosenMenus(new String[]{"1 - 1v1 Duel", "2 - Random battle", "b - Back"}, false,false);
            }
            input = scanner.nextLine();
            switch (input){
                case "1":
                    confirmBattle = true;
                    while (confirmBattle){
                        if(Menu.isHardcore()){
                            Printer.printFormatted("Are you sure you want to start a manual battle? [ yes | no ]");
                        }else{
                            Printer.printFormatted("Are you sure you want to start a 1v1 Duel? [ yes | no ]");
                        }
                        input = scanner.nextLine();
                        if(input.length() == 0){
                            input = "empty";
                        }
                        switch (input.toLowerCase().charAt(0)){
                            case 'y':
                                if(party1.getPartyCharacters().isEmpty()){
                                    pc.randomParty(party1);
                                }
                                if(party2.getPartyCharacters().isEmpty()){
                                    pc.randomParty(party2);
                                }
                                bt.battle();
                                confirmBattle = false;
                                break;
                            case 'n':
                                confirmBattle = false;
                                break;
                            default:
                                Printer.printFormatted("Select a valid option...");
                                break;
                        }
                    }
                    break;
                case "2":
                    confirmBattle = true;
                    while (confirmBattle && !Menu.isHardcore()){
                        Printer.printFormatted("Are you sure you want to start a random battle? [ yes | no ]");
                        input = scanner.nextLine();
                        if(input.length() == 0){
                            input = "empty";
                        }
                        switch (input.toLowerCase().charAt(0)){
                            case 'y':
                                if(party1.getPartyCharacters().isEmpty()){
                                    pc.randomParty(party1);
                                }
                                if(party2.getPartyCharacters().isEmpty()){
                                    pc.randomParty(party2);
                                }
                                bt.battleRandom();
                                confirmBattle = false;
                                break;
                            case 'n':
                                confirmBattle = false;
                                break;
                            default:
                                Printer.printFormatted("Select a valid option...");
                                break;
                        }
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

    public static void printGraveyard(){
        String input = "";
        Printer.printPart("graveyard");
        List<Character> graveyard = bt.graveyard();

        int startInt = 0;
        int longestName = 0;
        for (Character character : graveyard) {
            int length = character.getName().length();
            if(longestName < length){
                longestName = length;
            }
        }
        if(longestName < 15){
            //print 7 names at a time
            while(startInt < graveyard.size()){
                startInt = graveyardLine(graveyard,7,new int[]{17,17,17,17,17,17,17},startInt);
            }
        }else if(longestName < 18){
            //print 6 names at a time
            while(startInt < graveyard.size()){
                startInt = graveyardLine(graveyard,6,new int[]{20,20,20,20,20,20},startInt);
            }
        }else if(longestName < 22) {
            //print 5 names at a time
            while (startInt < graveyard.size()) {
                startInt = graveyardLine(graveyard, 5, new int[]{24, 24, 25, 24, 24}, startInt);
            }
        }else if(longestName < 28){
            //print 4 names at a time
            while (startInt < graveyard.size()) {
                startInt = graveyardLine(graveyard, 4, new int[]{30,31,31,30}, startInt);
            }
        }else if(longestName < 39){
            //print 3 names at a time
            while(startInt < graveyard.size()){
                startInt = graveyardLine(graveyard,3,new int[]{41,41,41},startInt);
            }
        }else if(longestName < 60){
            //print 2 names at a time
            while(startInt < graveyard.size()){
                startInt = graveyardLine(graveyard,2,new int[]{62,62},startInt);
            }
        }else{
            while(startInt < graveyard.size()){
                startInt = graveyardLine(graveyard,1,new int[]{125},startInt);
            }
        }
        Printer.printChosenMenus(new String[]{"1 - Clear graveyard", "b - Back"}, false,false);
        while(true){
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    bt.clearGraveyard();
                    printGraveyard();
                    return;
                case "b":
                    return;
                default:
                    Printer.printFormatted("Select a valid option...");
                    break;
            }
        }
    }
    public static int graveyardLine(List<Character> graveyard, int numberNames, int[] maxLength, int startIndex){
        int index = startIndex;
        StringBuilder str = new StringBuilder("|");
        int cnt = 0;
        for(; (index < startIndex + numberNames); index++){
            if(index < graveyard.size()){
                int length = graveyard.get(index).getName().length();
                if(length > 125){
                    str.append(" ");
                    str.append(graveyard.get(index).getName().substring(0,120));
                    str.append("... ");
                }else{
                    str.append(Menu.centerString(graveyard.get(index).getName(), maxLength[cnt]).substring(1, maxLength[cnt++] + 1));
                }
            }else{
                str.append(String.join("", Collections.nCopies(maxLength[cnt], " ")));
                cnt++;
            }
            str.append("|");
        }
        System.out.println(str);
        Printer.printLine(1);
        return index;
    }

    public static void settings(){
        String input = "";
        String[] gameMode = {"    1 - Game mode [Normal]    ","   1 - Game mode [Hardcore]   "};
        String[] logMode = {"  2 - Log mode [Reduced Logs]  ","   2 - Log mode [Full Logs]    "};
        String[] gameSpeed = {"  3 -  Battle speed [Instant]  ","    3 -  Battle speed [Fast]   ","    3 -  Battle speed [Slow]   "};
        String[] partySize = {"   4 - Party size limit [5]   ", "  4 - Party size limit [10]   ", "  4 - Party size limit [20]   "};

        while(true){
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                       Change Settings                                        |           b - Back           |");
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
            System.out.print("|" + (hardcore ? gameMode[1] : gameMode[0]) + "|" + (smallLog ? logMode[0] : logMode[1]) + "|");
            if (battleSpeed==1) {
                System.out.print(gameSpeed[1] + "|");
            } else if (battleSpeed ==2){
                System.out.print(gameSpeed[2] + "|");
            }else {
                System.out.print(gameSpeed[0] + "|");
            }
            if(Menu.getPartySize() == 5){
                System.out.println(partySize[0] + "|");
            }else if(Menu.getPartySize() == 10){
                System.out.println(partySize[1] + "|");
            }else if(Menu.getPartySize() == 20){
                System.out.println(partySize[2] + "|");
            }
                System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");

            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    hardcore = !hardcore;
                    if(hardcore){
                        Printer.printPart("hardcore");
                    }
                    break;
                case "2":
                    smallLog = !smallLog;
                    break;
                case "3":
                    if (battleSpeed == 0) {
                        battleSpeed = 1;
                    } else if (battleSpeed ==1){
                        battleSpeed = 2;
                    }else {
                        battleSpeed = 0;
                    }
                    break;
                case "4":
                    setPartySize();
                    break;
                case "b":
                    return;
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
            boolean depth1 = false;
            boolean depth2 = false;
            while(mainMenu){
                Printer.printPart("main");
                Printer.printChosenMenus(new String[]{"1 - Party management", "2 - Battle", "3 - Show graveyard", "4 - Settings", "x - Quit"}, false,false);
                input = scanner.nextLine();
                switch (input.toLowerCase()){
                    // Party management
                    case "1":
                        partyManagement();
                        break;
                    // Battle
                    case "2":
                        battleMenu();
                        break;
                    case "3":
                        printGraveyard();
                        break;
                    case "4":
                        settings();
                        break;
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
