package com.ironhack.homework_1;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
    private static final PartyCreator pc = new PartyCreator();
    private static final Party party1 = new Party("Player 1");
    private static final Party party2 = new Party("Player 2");
    private static final Scanner scanner = new Scanner(System.in);
    private static final BattleSimulator bt = new BattleSimulator(party1, party2);
    private static boolean smallLog = false;
    private static boolean hardcore = false;
    private static int battleSpeed = 0;

    public static int getBattleSpeed() {
        return battleSpeed;
    }

    public static void battleSpeedPause(int slowMilliseconds,int fastMilliseconds) {
        try {
            if (Menu.getBattleSpeed()==1) Thread.sleep(slowMilliseconds);
            if (Menu.getBattleSpeed()==2) Thread.sleep(fastMilliseconds);
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

    public static Party getParty1() {
        return party1;
    }

    public static Party getParty2() {
        return party2;
    }

    public static void partyManagement() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input = "";
        while(true){
            System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
            System.out.println("|    1 - Import party    |    2 - Export party    |   3 - Create manually   |    4 - Random party    |        b - Back        |");
            System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
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
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }
    public static void partyManagement_importParty(){
        String input = "";
        boolean running = false;
        while(true){
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            System.out.println("|             1 - To Player 1             |             2 - To Player 2             |                b - Back                 |");
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Import party -> To Player 1
                case "1":
                    running = true;
                    while(running){
                        try{
                            System.out.println("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party1, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Import party -> To Player 2
                case "2":
                    running = true;
                    while(running){
                        try{
                            System.out.println("What is the file you want to import from?");
                            input = scanner.nextLine();
                            pc.importParty(party2, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Import party -> Back
                case "b":
                    return;
                default:
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }
    public static void partyManagement_exportParty() throws IOException {
        String input = "";
        boolean running = false;
        while(true) {
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            System.out.println("|           1 - Export Player 1           |           2 - Export Player 2           |                b - Back                 |");
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                // Party management -> Export party -> Export Player 1
                case "1":
                    running = true;
                    while(running){
                        try{
                            System.out.println("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.saveParty(party1, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Export party -> Export Player 2
                case "2":
                    running = true;
                    while(running){
                        try{
                            System.out.println("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.importParty(party2, input);
                            running = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                // Party management -> Export party -> Back
                case "b":
                    return;
                default:
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }
    public static void partyManagement_createManually(){
        String input = "";
        while(true){
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
            System.out.println("|         1 - Player 1         |         2 - Player 2          |     3 - Single character      |           b - Back           |");
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
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
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }
    public static void partyManagement_createManually_party(Party party){
        String input = "";
        if(!party.getPartyCharacters().isEmpty()){
            boolean deleteNotSelected = true;
            while(deleteNotSelected){
                System.out.println("| Do you want to delete the characters previously added to this party? [ yes | no ]                                           |");
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
                        System.out.println("Previously added characters preserved!");
                        break;
                    default:
                        System.out.println("| Select a valid option...                                                                                                    |");
                        break;
                }
            }
        }
        while(true){
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            System.out.println("|            Party size (" + party.getPartyCharacters().size() + "/10)            |            1 - Add Character            |                b - Back                 |");
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    pc.addCharacter(party);
                    break;
                case "b":
                    return;
                default:
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }
    public static void partyManagement_createManually_singleCharacter(){
        String input = "";
        while(true) {
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
            System.out.println("|     1 - Add to Player 2      |      2 - Add to Player 2      |   3 - Add to exported party   |           b - Back           |");
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
            input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "1":
                    pc.addCharacter(party1);
                    break;
                case "2":
                    pc.addCharacter(party2);
                    break;
                case "3":
                    boolean running = true;
                    while(running){
                        try{
                            System.out.println("What is the file you want to export to?");
                            input = scanner.nextLine();
                            pc.addCharacter(input);
                            running = false;
                        }catch (FileNotFoundException e){
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "b":
                    return;
                default:
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }
    public static void partyManagement_randomParty() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        while(true){
            String input = "";
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            System.out.println("|              1 - Player 1               |              2 - Player 2               |                b - Back                 |");
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    if(!party1.getPartyCharacters().isEmpty()){
                        boolean deleteNotSelected = true;
                        while (deleteNotSelected){
                            System.out.println("| Are you sure you want to delete the existing characters in party 1? [ yes | no ]                                            |");
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
                                    System.out.println("Random party was not created!");
                                    deleteNotSelected = false;
                                    break;
                                default:
                                    System.out.println("| Select a valid option...                                                                                                    |");
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
                            System.out.println("| Are you sure you want to delete the existing characters in party 1? [ yes | no ]                                            |");
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
                                    System.out.println("Random party was not created!");
                                    deleteNotSelected = false;
                                    break;
                                default:
                                    System.out.println("| Select a valid option...                                                                                                    |");
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

    public static void battleMenu() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String input = "";
        boolean running = false;
        while(true){
            boolean confirmBattle = false;
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            System.out.println("|            1 - Manual battle            |            2 - Random battle            |                b - Back                 |");
            System.out.println("+-----------------------------------------+-----------------------------------------+-----------------------------------------+");
            input = scanner.nextLine();
            switch (input){
                case "1":
                    confirmBattle = true;
                    while (confirmBattle){
                        System.out.println("| Are you sure you want to start a manual battle? [ yes | no ]                                                                |");
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
                                System.out.println("| Select a valid option...                                                                                                    |");
                                break;
                        }
                    }
                    break;
                case "2":
                    confirmBattle = true;
                    while (confirmBattle){
                        System.out.println("| Are you sure you want to start a random battle? [ yes | no ]                                                                |");
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
                                System.out.println("| Select a valid option...                                                                                                    |");
                                break;
                        }
                    }
                    break;
                case "b":
                    return;
                default:
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }

    public static void printGraveyard(){
        String input = "";
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                                                                                             |");
        System.out.println("|                                                           .---.                                                             |");
        System.out.println("|                                                      '-.  |   |  .-'                                                        |");
        System.out.println("|                                                        ___|   |___                                                          |");
        System.out.println("|                                                   -=  [           ]  =-                                                     |");
        System.out.println("|                                                       `---.   .---'                                                         |");
        System.out.println("|                                                    __||__ |   | __||__                                                      |");
        System.out.println("|                                                    '-..-' |   | '-..-'                                                      |");
        System.out.println("|                                                      ||   |   |   ||                                                        |");
        System.out.println("|                                                      ||_.-|   |-,_||                                                        |");
        System.out.println("|                                                    .-\"`   `\"`'`   `\"-.                                                      |");
        System.out.println("|                                                  .'                   '.                                                    |");
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");

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
        System.out.println("+--------------------------------------------------------------+--------------------------------------------------------------+");
        System.out.println("|                     1 - Clear graveyard                      |                           b - Back                           |");
        System.out.println("+--------------------------------------------------------------+--------------------------------------------------------------+");
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
                    System.out.println("| Select a valid option...                                                                                                    |");
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
                    int dif = maxLength[cnt++] - length;
                    int n = dif / 2;
                    boolean even = dif % 2 == 0;
                    str.append(String.join("", Collections.nCopies(n, " ")));
                    str.append(graveyard.get(index).getName());
                    if(even){
                        str.append(String.join("", Collections.nCopies(n, " ")));
                    }else{
                        str.append(String.join("", Collections.nCopies(n+1, " ")));
                    }
                }
            }else{
                str.append(String.join("", Collections.nCopies(maxLength[cnt], " ")));
                cnt++;
            }
            str.append("|");
        }
        System.out.println(str);
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        return index;
    }

    public static void settings(){
        String input = "";
        String[] gameMode = {"    1 - Game mode [Normal]    ","   1 - Game mode [Hardcore]   "};
        String[] logMode = {"  2 - Log mode [Reduced Logs]  ","   2 - Log mode [Full Logs]    "};
        String[] gameSpeed = {"  3 -  Battle speed [Instant]  ","    3 -  Battle speed [Slow]   ","    3 -  Battle speed [Fast]   "};

        while(true){
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                       Change Settings                                                       |");
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");
            System.out.print("|" + (hardcore ? gameMode[1] : gameMode[0]) + "|" + (smallLog ? logMode[0] : logMode[1]) + "|");
            if (battleSpeed==1) {
                System.out.print(gameSpeed[1]);
            } else if (battleSpeed ==2){
                System.out.print(gameSpeed[2]);
            }else {
                System.out.print(gameSpeed[0]);
            }
            System.out.println("|           b - Back           |");
            System.out.println("+------------------------------+-------------------------------+-------------------------------+------------------------------+");

            input = scanner.nextLine();
            switch (input.toLowerCase()){
                case "1":
                    hardcore = !hardcore;
                    if(hardcore){
                        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                                                                                                             |");
                        System.out.println("|                      '||'  '||'     |     '||''|.   '||''|.     ..|'''.|  ..|''||   '||''|.   '||''''|                      |");
                        System.out.println("|                       ||    ||     |||     ||   ||   ||   ||  .|'     '  .|'    ||   ||   ||   ||  .                        |");
                        System.out.println("|                       ||''''||    |  ||    ||''|'    ||    || ||         ||      ||  ||''|'    ||''|                        |");
                        System.out.println("|                       ||    ||   .''''|.   ||   |.   ||    || '|.      . '|.     ||  ||   |.   ||                           |");
                        System.out.println("|                      .||.  .||. .|.  .||. .||.  '|' .||...|'   ''|....'   ''|...|'  .||.  '|' .||.....|                     |");
                        System.out.println("|                                                                                                                             |");
                        System.out.println("|                                                                                                                             |");
                        System.out.println("|                                        '||    ||'  ..|''||   '||''|.   '||''''|                                             |");
                        System.out.println("|                                         |||  |||  .|'    ||   ||   ||   ||  .                                               |");
                        System.out.println("|                                         |'|..'||  ||      ||  ||    ||  ||''|                                               |");
                        System.out.println("|                                         | '|' ||  '|.     ||  ||    ||  ||                                                  |");
                        System.out.println("|                                        .|. | .||.  ''|...|'  .||...|'  .||.....|                                            |");
                        System.out.println("|                                                                                                                             |");
                    }
                    break;
                case "2":
                    smallLog = !smallLog;
                    break;
                case "3":
                    if (battleSpeed == 0) {
                        battleSpeed = 2;
                    } else if (battleSpeed ==2){
                        battleSpeed = 1;
                    }else {
                        battleSpeed = 0;
                    }
                    break;
                case "b":
                    return;
                default:
                    System.out.println("| Select a valid option...                                                                                                    |");
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println("Welcome to the ECC Flying Lizards and Lying Wizards game!");
        System.out.println("Choose your players");
        List<Character> graveyard = new ArrayList<>();
        String input = "";
        boolean isRunning = true;

        while(isRunning){
            boolean mainMenu = true;
            boolean depth1 = false;
            boolean depth2 = false;
            while(mainMenu){
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|  _____ _     _            __    _               _                  _    __        _            _ _ _ _               _      |");
                System.out.println("| |   __| |_ _|_|___ ___   |  |  |_|___ ___ ___ _| |___    ___ ___ _| |  |  |   _ _|_|___ ___   | | | |_|___ ___ ___ _| |___  |");
                System.out.println("| |   __| | | | |   | . |  |  |__| |- _| .'|  _| . |_ -|  | .'|   | . |  |  |__| | | |   | . |  | | | | |- _| .'|  _| . |_ -| |");
                System.out.println("| |__|  |_|_  |_|_|_|_  |  |_____|_|___|__,|_| |___|___|  |__,|_|_|___|  |_____|_  |_|_|_|_  |  |_____|_|___|__,|_| |___|___| |");
                System.out.println("|         |___|     |___|                                                      |___|     |___|                                |");
                System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
                System.out.println("|  1 - Party management  |       2 - Battle       |   3 - Show graveyard    |      4 - Settings      |        x - Quit        |");
                System.out.println("+------------------------+------------------------+-------------------------+------------------------+------------------------+");
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
                        System.out.println("| Select a valid option...                                                                                                    |");
                        break;
                    }
                }
            }
    }
}
