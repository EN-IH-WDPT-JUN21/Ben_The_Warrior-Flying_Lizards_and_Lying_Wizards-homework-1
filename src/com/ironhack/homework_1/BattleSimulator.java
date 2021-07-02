package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BattleSimulator {
    private Party party1;
    private Party party2;
    private List<Character> graveyard;

    public BattleSimulator(Party party1, Party party2){
        setParty1(party1);
        setParty2(party2);
        this.graveyard = new ArrayList<>();
    }

    public void clearGraveyard(){
        this.graveyard.clear();
    }

    // Method that returns graveyard
    public List<Character> graveyard(){
        return this.graveyard; // return the graveyard
    }
    // Method that print graveyard
    public void printGraveyard(){
        for(Character c1:this.graveyard){
            System.out.println(c1.getName());
        }
    }

    // create additional internal methods to help the battle implementation that can be private

    // check if the party is empty, if is true so we have one winner
    private boolean isPartyEmpty(Party party){
        return party.getPartyCharacters().isEmpty();
    }

    // choose one random fighter from party

    private Character getRandomChar(Party p1){
       return p1.selectRandomCharacter();
    }

    private Character getChar(Party p1){
        return p1.selectCharacter();
    }


    // private Method that simulate a single duel
    private void duel(Character c1, Character c2){
        int round = 0;

        while(c1.isAlive() && c2.isAlive()){
            round += 1;
            if (round == 1){
                System.out.println("+=============================================================================================================================+");
            }else{
                System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
            }
            StringBuilder roundString = new StringBuilder("|");
            int roundLength = 9 + Integer.toString(round).length();
            int spaces = 125 - roundLength;
            boolean even = spaces % 2 == 0;
            roundString.append(String.join("", Collections.nCopies(spaces / 2, " ")));
            roundString.append("Round Nº ").append(round);
            if(even){
                roundString.append(String.join("", Collections.nCopies(spaces / 2, " ")));
            }else{
                roundString.append(String.join("", Collections.nCopies((spaces / 2) + 1, " ")));
            }
            roundString.append("|");
            System.out.println(roundString);

            c1.attack(c2); //they attack at the same time
            c2.attack(c1);

            System.out.println("|                                                                                                                             |");

            if(!c1.isAlive() && !c2.isAlive()){
                this.graveyard.add(c1);
                this.graveyard.add(c2);
                this.party1.removeCharacter(c1);
                this.party2.removeCharacter(c2);
                System.out.println("| Both fighters have fallen in combat!                                                                                        |");
            }
            else if(!c1.isAlive()){
                StringBuilder print1 = new StringBuilder("| Fighter ");
                StringBuilder print2 = new StringBuilder("| The winner is ");
                this.graveyard.add(c1);
                this.party1.removeCharacter(c1);

                print1.append(c1.getName());
                print1.append(" has fallen in combat!");
                print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                print1.append("|");

                print2.append(c2.getName());
                print2.append(String.join("", Collections.nCopies(126 - print2.toString().length(), " ")));
                print2.append("|");

                System.out.println(print1.toString());
                System.out.println("|                                                                                                                             |");
                System.out.println(print2.toString());
            }
            else if(!c2.isAlive()){
                StringBuilder print1 = new StringBuilder("| Fighter ");
                StringBuilder print2 = new StringBuilder("| The winner is ");
                this.graveyard.add(c2);
                this.party2.removeCharacter(c2);

                print1.append(c2.getName());
                print1.append(" has fallen in combat!");
                print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                print1.append("|");

                print2.append(c1.getName());
                print2.append(String.join("", Collections.nCopies(126 - print2.toString().length(), " ")));
                print2.append("|");

                System.out.println(print1.toString());
                System.out.println("|                                                                                                                             |");
                System.out.println(print2.toString());
            }
            else{
                StringBuilder print1 = new StringBuilder("| Player 1: Fighter " + c1.getName() + " attacks " + c2.getName());
                StringBuilder print2 = new StringBuilder("| Player 2: Fighter " + c2.getName() + " attacks " + c1.getName());
                StringBuilder print3 = new StringBuilder("| " + c1.getName() + " has " + c1.getHp() + " HP");
                StringBuilder print4 = new StringBuilder("| " + c2.getName() + " has " + c2.getHp() + " HP");

                print1.append(String.join("", Collections.nCopies(126 - print1.toString().length(), " ")));
                print1.append("|");
                print2.append(String.join("", Collections.nCopies(126 - print2.toString().length(), " ")));
                print2.append("|");
                print3.append(String.join("", Collections.nCopies(126 - print3.toString().length(), " ")));
                print3.append("|");
                print4.append(String.join("", Collections.nCopies(126 - print4.toString().length(), " ")));
                print4.append("|");

                System.out.println(print1);
                System.out.println(print2);
                System.out.println("|                                                                                                                             |");
                System.out.println(print3);
                System.out.println(print4);
            }


        }

    }

    // BattleSimulator prints a pretty and detailed log (request? or default?)

    // BattleSimulator choose random fighters for each duel in method battleRandom.

    public void battleRandom(){
        System.out.println("+=============================================================================================================================+");
        System.out.println("|                 ______  _______ _______ _______        _______      _______ _______ _______  ______ _______                 |");
        System.out.println("|                 |_____] |_____|    |       |    |      |______      |______    |    |_____| |_____/    |                    |");
        System.out.println("|                 |_____] |     |    |       |    |_____ |______      ______|    |    |     | |    \\_    |                    |");
        System.out.println("|                                                                                                                             |");
        System.out.println("+=============================================================================================================================+");
        System.out.println("|                                                    Player 1 V/S Player 2                                                    |");

        int fight = 0;
        // battle is going to happen meanwhile party1 and party2 has at least one element
        while(!isPartyEmpty(this.party1) && !isPartyEmpty(this.party2)){
            fight++;

            StringBuilder fightPrint = new StringBuilder("|");
            int fightLength = 6 + Integer.toString(fight).length();
            int spaces = 125 - fightLength;
            boolean even = spaces % 2 == 0;
            fightPrint.append(String.join("", Collections.nCopies(spaces / 2, " ")));
            fightPrint.append("FIGHT ").append(fight);
            if(even){
                fightPrint.append(String.join("", Collections.nCopies(spaces / 2, " ")));
            }else{
                fightPrint.append(String.join("", Collections.nCopies((spaces / 2) + 1, " ")));
            }
            fightPrint.append("|");

            System.out.println("+=============================================================================================================================+");
            System.out.println(fightPrint);

        //choose random characters for Duel
            duel(getRandomChar(party1),getRandomChar(party2));
        }


        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                      END OF THE BATTLE                                                      |");
        System.out.println("|                                                                                                                             |");


        if(isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            System.out.println("|                                                DRAW: Both parties are dead!                                                 |");
        }
        else if (!isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            System.out.println("|                                                        Player 1 WINS                                                        |");
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        }
        else {
            System.out.println("|                                                        Player 2 WINS                                                        |");
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        }

    }
    // Method battle simulate a fight and you can choose your fighter.

    public void battle(){
        System.out.println("+=============================================================================================================================+");
        System.out.println("|                 ______  _______ _______ _______        _______      _______ _______ _______  ______ _______                 |");
        System.out.println("|                 |_____] |_____|    |       |    |      |______      |______    |    |_____| |_____/    |                    |");
        System.out.println("|                 |_____] |     |    |       |    |_____ |______      ______|    |    |     | |    \\_    |                    |");
        System.out.println("|                                                                                                                             |");
        System.out.println("+=============================================================================================================================+");
        System.out.println("|                                                    Player 1 V/S Player 2                                                    |");

        // battle is going to happen meanwhile party1 and party2 has at least one element
        while(!isPartyEmpty(this.party1) && !isPartyEmpty(this.party2)){

            //choose characters for Duel
            duel(getChar(this.party1),getChar(this.party2));
        }



        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                      END OF THE BATTLE                                                      |");
        System.out.println("|                                                                                                                             |");


        if(isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            System.out.println("|                                                DRAW: Both parties are dead!                                                 |");
        }
        else if (!isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            System.out.println("|                                                        Player 1 WINS                                                        |");
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        }
        else {
            System.out.println("|                                                        Player 2 WINS                                                        |");
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
        }

    }

    // Parties setter

    public void setParty1(Party p1){
       this.party1 = p1;
    }

    public void setParty2(Party p2){
        this.party2 = p2;
    }

}
