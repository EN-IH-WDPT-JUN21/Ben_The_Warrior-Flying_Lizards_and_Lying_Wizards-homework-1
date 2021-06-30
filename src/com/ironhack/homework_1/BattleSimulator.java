package com.ironhack.homework_1;

import java.util.ArrayList;
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
    private void Duel(Character c1, Character c2){
        int round = 0;

        while(c1.isAlive() || c2.isAlive()){
            round += 1;
            System.out.println("==========    Round N°"+round+"   ============");

            c1.attack(c2); //they attack at the same time
            c2.attack(c1);

            if(!c1.isAlive() || !c2.isAlive()){
                this.graveyard.add(c1);
                this.graveyard.add(c2);
                this.party1.removeCharacter(c1);
                this.party2.removeCharacter(c2);
                System.out.println("Both fighters have fallen in combat");
            }
            else if(!c1.isAlive() && c2.isAlive()){
                this.graveyard.add(c1);
                this.party1.removeCharacter(c1);
                System.out.println("Fighter "+c1.getName()+ "has fallen in combat");
                System.out.println("The winner is "+c2.getName());
            }
            else if(c1.isAlive() && !c2.isAlive()){
                this.graveyard.add(c2);
                this.party1.removeCharacter(c2);
                System.out.println("Fighter "+c2.getName()+ "has fallen in combat");
                System.out.println("The winner is "+c1.getName());
            }
            else{
                System.out.println("Party 1 fighter "+c1.getName()+ " attacks "+c2.getName());
                System.out.println("Party 2 fighter "+c2.getName()+ " attacks "+c1.getName());
                System.out.println(c1.getName()+" it has "+c1.getHp()+" HP");
                System.out.println(c2.getName()+" it has "+c2.getHp()+" HP");
            }


        }

    }

    // BattleSimulator prints a pretty and detailed log (request? or default?)

    // BattleSimulator choose random fighters for each duel in method battleRandom.

    public void battleRandom(){
        System.out.println("=========   BATTLE BEGINS   =========="+"\n"+
                "======== "+party1.getPartyName()+" V/S "+party2.getPartyName()+" ========");

        // battle is going to happen meanwhile party1 and party2 has at least one element
        while(!isPartyEmpty(this.party1) || !isPartyEmpty(this.party2)){

        //choose random characters for Duel
            Duel(getRandomChar(party1),getRandomChar(party2));
        }



        System.out.println("======   END OF THE BATTLE   ======");

        if(isPartyEmpty(this.party1) || isPartyEmpty(this.party2)){
            System.out.println("Both parties are dead, it's a draw");
        }
        else if (!isPartyEmpty(this.party1) || isPartyEmpty(this.party2)){
            System.out.println("We have a winner: "+party1.getPartyName());
        }
        else {
            System.out.println("We have a winner: "+party2.getPartyName());
        }

    }
    // Method battle simulate a fight and you can choose your fighter.

    public void battle(){
        System.out.println("=========   BATTLE BEGINS   =========="+"\n"+
                "======== "+party1.getPartyName()+" V/S "+party2.getPartyName()+" ========");

        // battle is going to happen meanwhile party1 and party2 has at least one element
        while(!isPartyEmpty(this.party1) || !isPartyEmpty(this.party2)){

            //choose characters for Duel
            Duel(getChar(party1),getChar(party2));
        }



        System.out.println("======   END OF THE BATTLE   ======");

        if(isPartyEmpty(this.party1) || isPartyEmpty(this.party2)){
            System.out.println("Both parties are dead, it's a draw");
        }
        else if (!isPartyEmpty(this.party1) || isPartyEmpty(this.party2)){
            System.out.println("We have a winner: "+party1.getPartyName());
        }
        else {
            System.out.println("We have a winner: "+party2.getPartyName());
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
