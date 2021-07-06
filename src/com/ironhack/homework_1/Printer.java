package com.ironhack.homework_1;

import java.util.Collections;
import java.util.List;

public class Printer {
    public Printer() {
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

    public static void centerString(String middle, int width){
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
        System.out.println(str.toString());
    }

    public static void printPart(String printCase){
        switch (printCase){
            case "graveyard":
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
                break;
            case "main":
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|  _____ _     _            __    _               _                  _    __        _            _ _ _ _               _      |");
                System.out.println("| |   __| |_ _|_|___ ___   |  |  |_|___ ___ ___ _| |___    ___ ___ _| |  |  |   _ _|_|___ ___   | | | |_|___ ___ ___ _| |___  |");
                System.out.println("| |   __| | | | |   | . |  |  |__| |- _| .'|  _| . |_ -|  | .'|   | . |  |  |__| | | |   | . |  | | | | |- _| .'|  _| . |_ -| |");
                System.out.println("| |__|  |_|_  |_|_|_|_  |  |_____|_|___|__,|_| |___|___|  |__,|_|_|___|  |_____|_  |_|_|_|_  |  |_____|_|___|__,|_| |___|___| |");
                System.out.println("|         |___|     |___|                                                      |___|     |___|                                |");
                break;
            case "hardcore":
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
                break;

            case "battle":
                System.out.println("|                 ______  _______ _______ _______        _______      _______ _______ _______  ______ _______                 |");
                System.out.println("|                 |_____] |_____|    |       |    |      |______      |______    |    |_____| |_____/    |                    |");
                System.out.println("|                 |_____] |     |    |       |    |_____ |______      ______|    |    |     | |    \\_    |                    |");
                break;

            case "settings":
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|                                       Change Settings                                       |           b - Back            |");
                break;

            case "equalLine":
                System.out.println("+=============================================================================================================================+");
                break;
        }
    }

    public static void printFormatted(String message){
        StringBuilder print0 = new StringBuilder("| " + message);
        print0.append(String.join("", Collections.nCopies(126 - print0.toString().length(), " ")));
        print0.append("|");
        System.out.println(print0);
    }

    // Sector builder without upper and lower borders
    public static void printChosenMenus(String[] options) {
        boolean isValueToLong = false;      // Saves Error to print Error message in the end
        // Setup number of sections. Limit of 6
        int nrOfSections = options.length;
        if (options.length > 6) {
            System.out.println("| WARNING: Only 6 sections are allowed by row! Create a new row for more sections.");
            nrOfSections = 6;
        }
        // ========== Print middle content ==========
        for (int i = 0; i < nrOfSections; i++) {
            // Start StringBuilder and boolean for special cases that have an extra space.
            StringBuilder section = new StringBuilder("|");
            boolean specialSections = (nrOfSections == 4 && (i == 2 || i == 3)) || (nrOfSections == 5 && i == 4);
            // Trim input text and calculate the remaining space in the section.
            options[i] = options[i].trim();
            int spaces;
            if (specialSections) {      // for the special cases add +1 empty space
                spaces = ((125 - nrOfSections + 1) / (nrOfSections)) - options[i].length() + 1;
            } else {
                spaces = ((125 - nrOfSections + 1) / (nrOfSections)) - options[i].length();
            }
            // Deletes end of input text if it doesn't fit in the section.
            if (spaces < 2) {
                isValueToLong = true;       // saves error to inform later....
                while (spaces < 2) {
                    options[i] = options[i].substring(0, options[i].length() - 1);  //calculates new space until it fits
                    // Calculates new space until it fits
                    if (specialSections) {      // for the special cases add +1 empty space
                        spaces = ((125 - nrOfSections + 1) / (nrOfSections)) - options[i].length() + 1;
                    } else {                    // add spaces to fill the section
                        spaces = ((125 - nrOfSections + 1) / (nrOfSections)) - options[i].length();
                    }
                }
            }
            // add spaces to fill the section
            section.append(String.join("", Collections.nCopies(spaces / 2, " ")));
            section.append(options[i]);     // add main input text
            if (spaces % 2 == 0) {  // calculates remaining space to fill. different for odd and even input text lengths
                section.append(String.join("", Collections.nCopies(spaces / 2, " ")));
            } else {
                section.append(String.join("", Collections.nCopies((spaces / 2) + 1, " ")));
            }
            if (i == nrOfSections - 1) {       // close the last section and print everything.
                section.append("|");
                System.out.println(section);
            } else {
                System.out.print(section);
            }
        }
        if (isValueToLong)      // prints error message if it happened.
            System.out.println("| WARNING: Values for option too long for header! End of String deleted!");
    }

    // Sector builder with lower border (calls for simple sector builder)
    public static void printChosenMenus(String[] options, boolean isLowerTitle) {
        // Setup number of sections
        int nrOfSections = options.length;
        if (options.length > 6) {
            nrOfSections = 6;
        }
        // ========== Print middle content ==========
        printChosenMenus(options);      // uses same method with only middle content
        // ========== Print line lower header ==========
        if (isLowerTitle) {
            System.out.println("+=============================================================================================================================+");
        } else {
            printLine(nrOfSections);
        }
    }

    // Sector builder with upper and lower borders (calls for simple sector builder)
    public static void printChosenMenus(String[] options, boolean isLowerTitle, boolean isUpperTitle) {
        // Setup number of sections
        int nrOfSections = options.length;
        if (options.length > 6) {
            nrOfSections = 6;
        }
        // ========== Print line upper header ==========
        if (isUpperTitle) {
            System.out.println("+=============================================================================================================================+");
        } else {
            printLine(nrOfSections);
        }
        // ========== Print middle content ==========
        printChosenMenus(options);      // uses same method with only middle content
        // ========== Print line lower header ==========
        if (isLowerTitle) {
            System.out.println("+=============================================================================================================================+");
        } else {
            printLine(nrOfSections);
        }
    }

    // Border builder (similar steps from sector builder - printChosenMenus)
    public static void printLine(int nrOfSections) {
        for (int i = 0; i < nrOfSections; i++) {
            StringBuilder section = new StringBuilder("+");
            boolean specialSections = (nrOfSections == 4 && (i == 2 || i == 3)) || (nrOfSections == 5 && i == 4);
            int sectionSize;
            if (specialSections) {
                sectionSize = (125 - nrOfSections + 1) / (nrOfSections) + 1;
            } else {
                sectionSize = (125 - nrOfSections + 1) / (nrOfSections);
            }
            section.append(String.join("", Collections.nCopies(sectionSize, "-")));
            if (i == nrOfSections - 1) {
                section.append("+");
                System.out.println(section);
            } else {
                System.out.print(section);
            }
        }
    }

    // ==================== PARTY PRINTS ====================
    public static void partyPrint(Party party1, Party party2) {
        int maxPartySize = Math.max(party1.getPartyCharacters() != null ? party1.getPartyCharacters().size() : 0,
                party2.getPartyCharacters() != null ? party2.getPartyCharacters().size() : 0);
        for (int i = 0; i < maxPartySize; i++) {
            Printer.printChosenMenus(new String[]{i < party1.getPartyCharacters().size() ? party1.getPartyCharacters().get(i).printSimpleIntroduction() : "",
                    i < party2.getPartyCharacters().size() ? party2.getPartyCharacters().get(i).printSimpleIntroduction() : ""});

            Printer.printChosenMenus(new String[]{i < party1.getPartyCharacters().size() ? party1.getPartyCharacters().get(i).printSimpleStats() : "",
                    i < party2.getPartyCharacters().size() ? party2.getPartyCharacters().get(i).printSimpleStats() : ""}, i == maxPartySize - 1);
        }
    }

    public static void partyPrint(Party party) {
        int maxPartySize = party.getPartyCharacters().size();
        for (int i = 0; i < maxPartySize; i++) {
            Printer.printChosenMenus(new String[]{i < party.getPartyCharacters().size() ? party.getPartyCharacters().get(i).printSimpleIntroduction() : ""});
            Printer.printChosenMenus(new String[]{i < party.getPartyCharacters().size() ? party.getPartyCharacters().get(i).printSimpleStats() : ""}, i == maxPartySize - 1);
        }
    }


}
