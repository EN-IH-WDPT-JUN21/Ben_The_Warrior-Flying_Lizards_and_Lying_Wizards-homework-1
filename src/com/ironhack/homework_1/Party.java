package com.ironhack.homework_1;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private List<Character> partyCharacters;

    public Party() {
        partyCharacters = new ArrayList<>();
    }

    public List<Character> getPartyCharacters() {
        return partyCharacters;
    }

    public void addCharacter(Character character){
        partyCharacters.add(character);
    }

    public Character getRandomCharacter(){
        return null;
    }
}
