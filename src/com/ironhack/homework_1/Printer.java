package com.ironhack.homework_1;

import java.util.Collections;

public class Printer {
    public Printer() {
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
