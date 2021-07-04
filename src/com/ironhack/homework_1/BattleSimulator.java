package com.ironhack.homework_1;

import java.util.*;

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
            if (!Menu.getSmallLog()) {
                if (round == 1){
                    System.out.println("+=============================================================================================================================+");
                }else{
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
                }
                String roundString = "Round Nº " + round;
                roundString = Menu.centerString(roundString, 125);
                System.out.println(roundString);
            }

            String c1attack = !Menu.isHardcore() ? c1.attack(c2) : c1.manualAttack(c2); //they attack at the same time
            String[] c1AttackDetails = {c1attack.substring(0, c1attack.indexOf('|')), c1attack.substring(c1attack.indexOf('|') + 1, c1attack.length())};
            String c2attack = !Menu.isHardcore() ? c2.attack(c1) : c2.manualAttack(c1);
            String[] c2AttackDetails = {c2attack.substring(0, c2attack.indexOf('|')), c2attack.substring(c2attack.indexOf('|') + 1, c2attack.length())};

            if (!Menu.getSmallLog()) {
                Printer.printFormatted("");
            }

            if(!c1.isAlive() && !c2.isAlive()){
                this.graveyard.add(c1);
                this.graveyard.add(c2);
                this.party1.removeCharacter(c1);
                this.party2.removeCharacter(c2);
                Printer.printFormatted("Both fighters have fallen in combat!");
            }
            else if(!c1.isAlive()){
                this.graveyard.add(c1);
                this.party1.removeCharacter(c1);

                Printer.printFormatted("Fighter " + c1.getName() + " has fallen in combat!");
                Printer.printFormatted("");
                Printer.printFormatted("The winner is " + c2.getName());
            }
            else if(!c2.isAlive()){
                this.graveyard.add(c2);
                this.party2.removeCharacter(c2);

                Printer.printFormatted("Fighter " + c2.getName() + " has fallen in combat!");
                Printer.printFormatted("");
                Printer.printFormatted("The winner is " + c1.getName());
            }
            else{
                if (!Menu.getSmallLog()) {

                    Menu.battleSpeedPause();
                    Printer.printFormatted(Menu.getParty1().getPartyName() + ": " + c1.getName() + " the " +
                            c1.getClass().getSimpleName() + " attacks " + c2.getName() + " with " + c1AttackDetails[0] + " dealing " + c1AttackDetails[1] + " damage!");

                    Menu.battleSpeedPause();
                    Printer.printFormatted(Menu.getParty2().getPartyName() + ": " + c2.getName() + " the " +
                            c2.getClass().getSimpleName() + " attacks " + c1.getName() + " with " + c2AttackDetails[0] + " dealing " + c2AttackDetails[1] + " damage!");

                    Menu.battleSpeedPause();
                    Printer.printFormatted("");
                    Printer.printFormatted(c1.getName() + " has " + c1.getHp() + " HP");

                    Menu.battleSpeedPause();
                    Printer.printFormatted(c2.getName() + " has " + c2.getHp() + " HP");

                    Menu.battleSpeedPause();
                }
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
        Printer.printFormatted("");
        System.out.println("+=============================================================================================================================+");
        Printer.printFormatted("Player 1 V/S Player 2");
        Printer.printLine(2);
        Printer.partyPrint(party1, party2);
        Printer.printFormatted("PRESS ENTER TO START BATTLE");
        System.out.print("+=============================================================================================================================+");
        new Scanner(System.in).nextLine();

        int fight = 0;
        // battle is going to happen meanwhile party1 and party2 has at least one element
        while(!isPartyEmpty(this.party1) && !isPartyEmpty(this.party2)){
            fight++;
            Character f1 = getRandomChar(party1);
            Character f2 = getRandomChar(party2);

            System.out.println("+=============================================================================================================================+");
            String fightTitle = "FIGHT " + fight + "  -  " + f1.printSimpleIntroduction() + "  VS  " + f2.printSimpleIntroduction();
            Printer.printChosenMenus(new String[]{fightTitle});
            if (Menu.getBattleSpeed() != 0) {
                Printer.printFormatted("PRESS ENTER TO START");
                new Scanner(System.in).nextLine();
            }

        //choose random characters for Duel
            duel(f1, f2);
        }

        System.out.println("+=============================================================================================================================+");
        Printer.printFormatted("END OF THE BATTLE");
        Printer.printFormatted("");


        if(isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.printFormatted("DRAW: Both parties are dead!");
        }
        else if (!isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.printFormatted("Player 1 WINS");
        }
        else {
            Printer.printFormatted("Player 2 WINS");
        }
        System.out.println("+=============================================================================================================================+");
    }
    // Method battle simulate a fight and you can choose your fighter.

    public void battle(){
        System.out.println("+=============================================================================================================================+");
        System.out.println("|                 ______  _______ _______ _______        _______      _______ _______ _______  ______ _______                 |");
        System.out.println("|                 |_____] |_____|    |       |    |      |______      |______    |    |_____| |_____/    |                    |");
        System.out.println("|                 |_____] |     |    |       |    |_____ |______      ______|    |    |     | |    \\_    |                    |");
        Printer.printFormatted("");
        System.out.println("+=============================================================================================================================+");
        Printer.printFormatted("Player 1 V/S Player 2");
        Printer.printLine(2);
        Printer.partyPrint(party1, party2);
        Printer.printFormatted("PRESS ENTER TO START BATTLE");
        System.out.print("+=============================================================================================================================+");
        new Scanner(System.in).nextLine();

        // battle is going to happen meanwhile party1 and party2 has at least one element
        int fight = 0;
        while(!isPartyEmpty(this.party1) && !isPartyEmpty(this.party2)){
            fight++;
            System.out.println("+=============================================================================================================================+");
            String fightTitle = "FIGHT " + fight;
            Printer.printChosenMenus(new String[]{fightTitle});

            Character f1 = getChar(party1);
            Character f2 = getChar(party2);

            //choose characters for Duel
            duel(f1,f2);
        }



        System.out.println("+=============================================================================================================================+");
        Printer.printFormatted("END OF THE BATTLE");
        Printer.printFormatted("");


        if(isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.printFormatted("DRAW: Both parties are dead!");
        }
        else if (!isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.printFormatted("Player 1 WINS");
        }
        else {
            Printer.printFormatted("Player 2 WINS");
        }
        System.out.println("+=============================================================================================================================+");
    }

    // Parties setter

    public void setParty1(Party p1){
       this.party1 = p1;
    }

    public void setParty2(Party p2){
        this.party2 = p2;
    }

}
