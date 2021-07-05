# Flying Lizards and Lying Wizards :dragon: :mage: (homework 1)
## How to navigate the menu
To navigate through the menu the user must input the argument corresponding to the wanted action 
Example:

| 1 - Option 1 | 2 - Option 2 |   b - Back   |
| ------------ | ------------ | ------------ |

- If the player wants to select Option 1, he must input: **1**.
- If the player wants to select Option 2, he must input: **2**.
- If the player wants to go back, he must input: **b**.
- If the user is prompted to confirm a selected option they may input **y/n** or **yes/no**.

## Overview of Features
- 2 Different game modes
- 3 Different battle modes
- 5 Character Classes
- 3 Ways to create a Character
- Over 2500 random Character names!
- 2 Battle log modes
- 3 Battle speeds
- Importing and exporting of entire parties to csv file format
- Adjustable Party size
- Graveyard and game banner with ASCII Art
- Stylised menus

## Available Characters
### Warrior
#### Stats:
| HP | Stamina | Strength |
| :---: | :---: | :---: |
| 100 - 200 | 10 - 50 | 1 - 10 |

#### Skills:
1. **Heavy Attack**
    - A strong cleaving stroke with the equipped weapon.
    - **Damage**: (*Strength*) HP
    - **Cost**: 5 Stamina

2. **Weak Attack**
    - Wear your opponent down with a basic strike while conversing energy.
    - **Damage**: *Strength*/2 HP
    - **Recover**: 1 Stamina
### Wizard
#### Stats:
| HP | Mana | Intelligence |
| :---: | :---: | :---: |
| 50 - 100 | 10 - 50 | 10 - 50 |
#### Skills:
1. **Fireball**
    - Turn your foe to cinders!
    - **Damage**: *Intelligence* HP
    - **Cost**: 5 Mana
2. **Staff Hit**
    - Whack!
    - **Damage**: 2 HP
    - **Recover**: 1 Mana
### Archer
#### Stats:
| HP | Energy | Dexterity |
| :---: | :---: | :---: |
| 50 - 100 | 5 - 20 | 5 - 25 |
### Rogue
#### Stats:
| HP | Luck | Agility |
| :---: | :---: | :---: |
| 40 - 80 | 5 - 15 | 5 - 25 |
### Necromancer
#### Stats:
| HP | Mana | Wisdom |
| :---: | :---: | :---: |
| 50 - 100 | 10 - 20 | 1 - 20 |

## Settings
### Game mode
#### Normal
##### -- Character Creation -- 
The player has 2 options in normal mode for character creation.
1. The player can create fully randomised parties of a give party size. Characters are randomised by class, name and stat values between minimums and maximums. The sizes of these parties can be changed in the settings menu to allow for larger or smaller fights.
2. The player can choose to create a single character with greater control. They can choose the class from a list of available Character classes, the name of the character and can input values for each stat when prompted, between a minimum and maximum.

##### -- Battle --
The player has 2 options for battle in normal mode.
1. A fully automatic battle can be initiated. Each combatant is chosen from each party at random to face off against each other. The combatants act according to rules that govern when they can and can't use certain abilites. Defeated combatants are moved to the graveyard and the party with characters left alive at the end is the winner! As both combatants in a duel can die at the same time, it is also possible to have an overall draw.
2. The player can choose which combatants will fight each other. The behavior of the combatants is still controlled by the same rules that control them for the fully automatic battle.

#### Hardcore
##### -- Character Creation -- 
In hardcore mode the player has 2 options for character creation.
1. The player can create a fully randomised party. This follows the same rules as the option in Normal mode.
2. The player can manually create a character with more fine grained control. The player can choose the class of the character and their name like before but instead of only manually entering the value of the stats between the a minimum and maximum, the player is now allocated 10 stat upgrade points which he can use to upgrade the characters stats. In this mode the player can exceed the maximum randomised value of a stat, but this comes at the cost of severely weaking the other stats. This mode allows you to make a character more specialized at the cost of lower overall stat totals. This is similair to other classic RPG character creation systems but simplified to fit inside the confines of the project.

##### -- Battle --
Hardcore mode unlocks a new battle system that allows players to directly control the combatants behavior. The player can choose the combatants that will fight in each duel and then will be presented with the option of which skill to use. 
This gives players the oppurtunity to play the game in a more strategic way. Why spend your resources delivering a Heavy Attack to an already weak enemy when a Weak Attack would do? 
The selectable options are limited by the resource values of the character and the player is informed if when they don't have enough resources to perform a skill, and how far they are from being able to. The player is also informed before choosing the attack how much damage it does as well as being giving a short flavor description of each attack and any other effects it may have.

### Log mode
#### Full logs
#### Simplified logs
### Battle speed
#### Instant
#### Fast
#### Slow
### Party size limit
This setting controls the limit size of the parties. After a party is full no more characters can be added to the party.
