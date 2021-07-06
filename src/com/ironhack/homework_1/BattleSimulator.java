package com.ironhack.homework_1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

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
                    Printer.printPart("equalLine");
                }else{
                    Printer.printLine(1);
                }
                String roundString = "Round Nº " + round;
                Printer.centerString(roundString, 125);
            }

            String c1attack = !Menu.isHardcore() ? c1.attack(c2) : c1.manualAttack(c2); //they attack at the same time
            String[] c1AttackDetails = {c1attack.substring(0, c1attack.indexOf('|')), c1attack.substring(c1attack.indexOf('|') + 1, c1attack.length())};
            String c2attack = !Menu.isHardcore() ? c2.attack(c1) : c2.manualAttack(c1);
            String[] c2AttackDetails = {c2attack.substring(0, c2attack.indexOf('|')), c2attack.substring(c2attack.indexOf('|') + 1, c2attack.length())};

            if (!Menu.getSmallLog()) {
                Menu.battleSpeedPause();
                Printer.printFormatted("");
                if (c1.getClass() != Skeleton.class) {
                    if (!c1AttackDetails[0].equals("Summon a Skeleton!")) {
                        Printer.printFormatted(Menu.getParty1().getPartyName() + ": " + c1.getName() + " the " +
                                c1.getClass().getSimpleName() + " attacks " + c2.getName() + " with " + c1AttackDetails[0] + " dealing " + c1AttackDetails[1] + " damage!");
                    }
                    else {
                        Printer.printFormatted(Menu.getParty1().getPartyName() + ": " + c1.getName() + " the " +
                                c1.getClass().getSimpleName() + " summons a Skeleton Warrior to fight for his party!");
                    }
                }
                else {
                    Printer.printFormatted(Menu.getParty1().getPartyName() + ": " + c1.getName() + " attacks " +
                            c2.getName() + " with " + c1AttackDetails[0] + " dealing " + c1AttackDetails[1] + " damage!");
                }

                Menu.battleSpeedPause();
                if (c2.getClass() != Skeleton.class) {
                    if (!c2AttackDetails[0].equals("Summon a Skeleton!")) {
                        Printer.printFormatted(Menu.getParty2().getPartyName() + ": " + c2.getName() + " the " +
                                c2.getClass().getSimpleName() + " attacks " + c1.getName() + " with " + c2AttackDetails[0] + " dealing " + c2AttackDetails[1] + " damage!");
                    }
                    else {
                        Printer.printFormatted(Menu.getParty2().getPartyName() + ": " + c2.getName() + " the " +
                                c2.getClass().getSimpleName() + " summons a Skeleton Warrior to fight for his party!");
                    }
                }
                else {
                    Printer.printFormatted(Menu.getParty2().getPartyName() + ": " + c2.getName() + " attacks " +
                            c1.getName() + " with " + c2AttackDetails[0] + " dealing " + c2AttackDetails[1] + " damage!");
                }
                Menu.battleSpeedPause();
            }
            for (int i = 0; i < party1.getPartyCharacters().size(); i++) {
                if (!party1.getPartyCharacters().get(i).isAlive() && !party1.getPartyCharacters().get(i).equals(c1)){
                    if (!Menu.getSmallLog()){
                        Printer.printFormatted("Party 1's " + party1.getPartyCharacters().get(i).printSimpleIntroduction() + " died in the crossfire!");
                        Menu.battleSpeedPause();
                    }
                    party1.removeCharacter(party1.getPartyCharacters().get(i));
                    i -= 1;
                }
            }
            for (int i = 0; i < party2.getPartyCharacters().size(); i++) {
                if (!party2.getPartyCharacters().get(i).isAlive() && !party2.getPartyCharacters().get(i).equals(c2)){
                    if (!Menu.getSmallLog()) {
                        Printer.printFormatted("Party 2's " + party2.getPartyCharacters().get(i).printSimpleIntroduction() + " died in the crossfire!");
                        if (!Menu.getSmallLog()) Menu.battleSpeedPause();
                    }
                    party2.removeCharacter(party2.getPartyCharacters().get(i));
                    i -= 1;
                }
            }

            if (!Menu.getSmallLog()) {
                Printer.printFormatted("");
                Printer.printFormatted(c1.getName() + " has " + round(c1.getHp())  + " HP");
                Menu.battleSpeedPause();
                Printer.printFormatted(c2.getName() + " has " + round(c2.getHp()) + " HP");
                Menu.battleSpeedPause();
            }

            if(!c1.isAlive() && !c2.isAlive()){
                Printer.printFormatted("");
                if (c1.getClass() != Skeleton.class) {
                    this.graveyard.add(c1);
                }
                if (c2.getClass() != Skeleton.class) {
                    this.graveyard.add(c2);
                }

                this.party1.removeCharacter(c1);
                this.party2.removeCharacter(c2);
                Printer.printFormatted("Both fighters have fallen in combat!");
            }
            else if(!c1.isAlive()){
                Printer.printFormatted("");
                if (c1.getClass() != Skeleton.class) {
                    this.graveyard.add(c1);
                }

                this.party1.removeCharacter(c1);

                Printer.printFormatted("Fighter " + c1.getName() + " has fallen in combat!");
                Printer.printFormatted("");
                Printer.printFormatted("The winner is " + c2.getName());
            }
            else if(!c2.isAlive()){
                Printer.printFormatted("");
                if (c2.getClass() != Skeleton.class) {
                    this.graveyard.add(c2);
                }

                this.party2.removeCharacter(c2);

                Printer.printFormatted("Fighter " + c2.getName() + " has fallen in combat!");
                Printer.printFormatted("");
                Printer.printFormatted("The winner is " + c1.getName());
            }
        }
    }

    // BattleSimulator prints a pretty and detailed log (request? or default?)

    // BattleSimulator choose random fighters for each duel in method battleRandom.

    public void battleRandom(){
        Printer.printPart("equalLine");
        Printer.printPart("battle");
        Printer.printFormatted("");
        Printer.printPart("equalLine");
        Printer.centerString("Player 1 V/S Player 2",125);
        Printer.printLine(2);
        Printer.partyPrint(party1, party2);
        Printer.centerString("PRESS ENTER TO START BATTLE",125);
        Printer.printPart("equalLine");
        new Scanner(System.in).nextLine();

        int fight = 0;
        // battle is going to happen meanwhile party1 and party2 has at least one element
        while(!isPartyEmpty(this.party1) && !isPartyEmpty(this.party2)){
            fight++;
            Character f1 = getRandomChar(party1);
            Character f2 = getRandomChar(party2);

            Printer.printPart("equalLine");
            String fightTitle = "FIGHT " + fight + "  -  " + f1.printSimpleIntroduction() + "  VS  " + f2.printSimpleIntroduction();
            Printer.printChosenMenus(new String[]{fightTitle});
            if (Menu.getBattleSpeed() != 0) {
                Printer.centerString("PRESS ENTER TO START",125);
                Printer.printPart("equalLine");
                new Scanner(System.in).nextLine();
            }

        //choose random characters for Duel
            duel(f1, f2);
        }

        Printer.printPart("equalLine");
        Printer.centerString("END OF THE BATTLE",125);
        Printer.printFormatted("");


        if(isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.centerString("DRAW: Both parties are dead!",125);
        }
        else if (!isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.centerString("Player 1 WINS",125);
        }
        else {
            Printer.centerString("Player 2 WINS",125);
        }

        Iterator<Character> ch1 = party1.getPartyCharacters().iterator();
        Iterator<Character> ch2 = party2.getPartyCharacters().iterator();
        while (ch1.hasNext()){
            Character ch = ch1.next();
            if (ch.getClass() == Skeleton.class){
                ch1.remove();
            }
        }
        while (ch2.hasNext()){
            Character ch = ch2.next();
            if (ch.getClass() == Skeleton.class){
                ch2.remove();
            }
        }
        Printer.printPart("equalLine");
    }
    // Method battle simulate a fight and you can choose your fighter.

    public void battle(){
        Printer.printPart("equalLine");
        Printer.printPart("battle");
        Printer.printFormatted("");
        Printer.printPart("equalLine");
        Printer.centerString("Player 1 V/S Player 2",125);
        Printer.printLine(2);
        Printer.partyPrint(party1, party2);
        Printer.centerString("PRESS ENTER TO START BATTLE",125);
        Printer.printPart("equalLine");
        new Scanner(System.in).nextLine();

        // battle is going to happen meanwhile party1 and party2 has at least one element
        int fight = 0;
        while(!isPartyEmpty(this.party1) && !isPartyEmpty(this.party2)){
            fight++;
            Printer.printPart("equalLine");
            String fightTitle = "FIGHT " + fight;
            Printer.printChosenMenus(new String[]{fightTitle});

            Character f1 = getChar(party1);
            Character f2 = getChar(party2);

            //choose characters for Duel
            duel(f1,f2);
        }



        Printer.printPart("equalLine");
        Printer.centerString("END OF THE BATTLE",125);
        Printer.printFormatted("");


        if(isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.centerString("DRAW: Both parties are dead!",125);
        }
        else if (!isPartyEmpty(this.party1) && isPartyEmpty(this.party2)){
            Printer.centerString("Player 1 WINS",125);
        }
        else {
            Printer.centerString("Player 2 WINS",125);
        }
        Printer.printPart("equalLine");
    }

    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(1, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    // Parties setter
    public void setParty1(Party p1){
       this.party1 = p1;
    }

    public void setParty2(Party p2){
        this.party2 = p2;
    }

}
