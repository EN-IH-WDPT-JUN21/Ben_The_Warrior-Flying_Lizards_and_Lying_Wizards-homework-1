# Flying Lizards and Lying Wizards :dragon: :mage: (homework 1)

## Table of Contents

1. [**Introduction**](#Introduction)
    1. [Overview of Features](#Overview-of-Features)
2. [**Menu**](#Menu)
    1. [How to navigate the menu](#How-to-navigate-the-menu)
3. [**Party Management**](#Party-Management)
    1. [Import Party](#Import-Party)
    2. [Export Party](#Export-Party)
    3. [Create Manually](#Create-Manually)
    4. [Random Party](#Random-Party)
    5. [Available Characters](#Available-Characters)
4. [**Battle**](#Battle)
    1. [1v1 Duel](#1v1-Duel)
    2. [Random Battle](#Random-Battle)
5. [**Settings**](#Settings)
    1. [Hardcore Mode](#Hardcore-Mode)
    2. [Log mode](#Log-mode)
    3. [Battle speed](#Battle-speed)
    4. [Party size limit](#Party-size-limit)
6. [**Hardcore**](#Hardcore)
7. [**The Team**](#The-Team)

## Introduction

This project was created as a collaborative assignment work for the Ironhack bootcamp. The objective of this project was
to develop an RPG simulator that would allow the player to create a party with warriors and wizards and to simulate
battles between two parties.  
Adding to the required objectives of the assignment, we decided to try to implement extra functionality. We believe that
having more overall features could improve the player experience.

### Overview of Features

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

## Menu

The main menu is the basis of the navigation in the game. Its structure can be easily defined in four major sections:
Party Management, Battle, Graveyard, and Settings. Defined as a text-based game, transversing through the game uses only
text-based commands.

### How to navigate the menu

To navigate through the menu the player must input the argument corresponding to the wanted action Example:

| 1 - Option 1 | 2 - Option 2 |   b - Back   |
| ------------ | ------------ | ------------ |

- If the player wants to select Option 1, he must input: **1**.
- If the player wants to select Option 2, he must input: **2**.
- If the player wants to go back, he must input: **b**.
- When the player is prompted to confirm a selected option they may input **y/n** or **yes/no**.

## Party Management

A party contains a group of characters that can be imported, created, or randomly generated. Its size is limited (
default is `5`), and can be increased in the settings.

### Import Party

To import a Party from a .csv file the player is queried to provide the name of the file or the path to the file to
import. If only the name is provided, the filed is searched in the parties folder. If the file is not found in the
specific parties folder the directory provided is used.

The Characters should be added to a .csv file either by exporting a previously created Party or by manually adding
Characters to a .csv file.

The size of the imported party is limited by the party size settings. In the case of importing a party larger than the
maximum size defined, only the initial Characters will be imported. Besides the party size, the values of each Character
attribute cannot be defined outside its maximum and minimum limits. In the this event, the exceeded value will take form
of the maximum or minimum allowed.

The Characters are saved in the following format:

```
Warrior,Name,Strength,Stamina,HP
Wizard,Name,Intelligence,Mana,HP
Archer,Name,Dexterity,Energy,HP
Rogue,Name,Agility,Luck,HP
Necromancer,Name,Wisdom,Mana,HP
```

In the parties folder it is possible to observe a template (template.csv):

```
Warrior,Ben,5,20,150
Wizard,Voldemort,30,30,100
Archer,Robin Hood,25,20,80
Rogue,Daegan,10,5,50
Necromancer,Karthus,15,15,80
````

### Export Party

To export a Party to a .csv file the player is queried to provide the name of the file. Then the Party is saved in the
parties folder.

### Create Manually

To create and manage a party with control, the player can create the party manually. This method requires the player to
choose from a list of available Character classes, name the Character and to define its attributes, between a minimum
and maximum.

In addition, the player can create a single Character and add it to a team or export it.

### Random Party

The player can create fully randomised parties of a give party size. Characters are randomised by class, name and stat
values between minimums and maximums. The sizes of these parties can be changed in the settings menu to allow for larger
or smaller fights.

In the event of trying to create a new random party on top of an already existing party, the player can choose between
deleting the current party, fill the rest of the party with random Characters, or go back.

### Available Characters

#### Warrior

The warrior is a simple Character that has the most health of all Characters. The warrior uses stamina for the Heavy
attacks which can be quite damaging.

##### Stats:

| HP | Stamina | Strength |
| :---: | :---: | :---: |
| 100 - 200 | 10 - 50 | 1 - 10 |

##### Skills:

1. **Heavy Attack**
    - A strong cleaving stroke with the equipped weapon.
    - **Damage**: `Strength` HP
    - **Cost**: `5` Stamina

2. **Weak Attack**
    - Wear your opponent down with a basic strike while conserving energy.
    - **Damage**: `Strength / 2` HP
    - **Recover**: `1` Stamina

#### Wizard

The wizard is a type of mage that requires mana to attack efficiently. Even though they don't have a lot of health, his
Fireball attack can one of the most devastating attacks in the game.

##### Stats:

| HP | Mana | Intelligence |
| :---: | :---: | :---: |
| 50 - 100 | 10 - 50 | 10 - 50 |

##### Skills:

1. **Fireball**
    - Turn your foe to cinders!
    - **Damage**: `Intelligence` HP
    - **Cost**: `5` Mana
2. **Staff Hit**
    - Whack!
    - **Damage**: `2` HP
    - **Recover**: `1` Mana

#### Archer

The archer is a ranged Character that can be quite strong weakening the enemy party. His main attack affects the whole
enemy team, preparing his party for victory!

##### Stats:

| HP | Energy | Dexterity |
| :---: | :---: | :---: |
| 50 - 100 | 5 - 20 | 5 - 25 |

##### Skills:

1. Spread Shot
    - Release a clutch of arrows damaging all members of the enemy party.
    - **Damage**: `Dexterity / 5` HP
    - **Cost**: `10` Energy
2. Aimed Shot
    - Take aim and deliver a single arrow to center mass.
    - **Damage**: `Dexterity / 2` HP
    - **Recover**: `2` Energy

#### Rogue

The Rogue is an agile Character that hits hard, and can be harder to hit. His combo attack can be extremely devastating
it well-prepared.

##### Stats:

| HP | Luck | Agility |
| :---: | :---: | :---: |
| 40 - 80 | 5 - 15 | 5 - 25 |

##### Skills:

1. Shank
    - Open up your enemies defences with an expertly aimed jab.
    - **Damage**: `Agility` HP
    - **Receive**: Combo Point

2. Coup de grâce!
    - Delivery a finishing blow to your opponent!
    - **Damage**: `Agility x (Combo Points + 1)` HP
    - **Cost**: All Combo Points

3. Blend into the shadows
    - Blend into the shadows becoming hard to hit. Immune to damage for this turn!
    - **Receive**: Immunity for 1 turn

#### Necromancer

The necromancer is a Character that can swarm the enemy team with the undead. Although the cost can be high, the
necromancers can summon skeleton warriors to fight for his team.

##### Stats:

| HP | Mana | Wisdom |
| :---: | :---: | :---: |
| 50 - 100 | 10 - 20 | 1 - 20 |

##### Skills:

1. Summon Skeleton Warrior
    - Summon a skeletal warrior to fight for your party. (Party size limited to 20).
    - **Damage**: `0` HP
    - **Cost**: `20 + Wisdom` Mana
    - **Receive**: Skeleton Warrior with stats:
        - HP: `Wisdom`
        - Stamina: `Wisdom`
        - Strength: `Wisdom`

2. Drain Life
    - Suck the very life force from your opponent!
    - **Damage**: `Wisdom / 2` HP
    - **Recover**: `5` Mana

##### Skeleton Warrior:

Summoned into a party by a necromancer, the Skeleton Warrior uses the same fighting ability as the Warrior. This
Character is considered a summoned Character, and, even tho it is added to a party during a battle, it does not remain
in the party after victory. When killed, the skeleton warrior ceases to exist and will not appear in the graveyard.

## Battle

### 1v1 Duel

In 1v1 Duel mode, the player can choose which combatants will fight each other. The combatants act automatically,
according to rules that govern when they can and can't use certain abilities. The player cannot choose the behaviour of
each Character.

Defeated combatants are moved to the graveyard, and the party with characters left alive at the end is the winner! As
both combatants in a duel can die at the same time, it is also possible to have an overall draw.

When starting the battle with an empty team, a randomly generated team will be created.

### Random Battle

In random battle, each combatant is chosen from each party at random to face off against each other. The combatants act
according to rules that govern when they can and can't use certain abilities. The player cannot choose the combatants
nor their behaviour.

Defeated combatants are moved to the graveyard, and the party with characters left alive at the end is the winner! As
both combatants in a duel can die at the same time, it is also possible to have an overall draw.

When starting the battle with an empty team, a randomly generated team will be created.

## Settings

### Hardcore Mode

This option can switch between **Normal Mode** and **Hardcore Mode**, which will change some functionalities and add new
features to the game.

See [Hardcore](#Hardcore) chapter for more information.

### Log mode

#### [x] Full logs

Displays all the actions and information that occurs during a battle.  
Information displayed:

- Party elements;
- Fight number;
- Duellers;
- Round number;
- Attack executed and damage dealt by each dueller each round;
- Each dueller's remaining HP at the end of each round;
- Dueller's casualties at the end of each fight;
- Duel result;
- Battle result.

#### [x] Simplified logs

Reduces the information displayed. Represents only the overall state to the battle.  
Information displayed:

- Party elements;
- Fight number;
- Duellers;
- Dueller's casualties at the end of each fight;
- Duel result;
- Battle result.

### Battle speed

#### [x] Instant

Prints the all the battle logs instantly.

#### [x] Fast

Battle logs are printed fast, with a small delay of `0.15` seconds, and the battle is paused after each fight.  
The player needs to resume the fights when battling in random mode.

#### [x] Slow

Battle logs are printed slower, with a delay of `0.5` seconds, and the battle is paused after each fight.  
The player needs to resume the fights when battling in random mode.

### Party size limit

This setting controls the limit size of the parties. The allowed values the maximum number of Characters per Party
are `5`, `10` and `20`.

When a party is full no more characters can be added or imported to the party. Changing the party size limit from 20 to
5, in cases where the party size already exceeds the limit of 5, will require deleting all the characters from party.

## Hardcore

Hardcore mode changes the behaviour of the game and implements some new features when creating a party and in battle.

##### -- Character Creation --

In hardcore mode the player has 2 options for character creation.

- The player can create a fully randomised party. This follows the same rules as the option in Normal mode.


- The player can manually create a character with more fine-grained control. The player can choose the class of the
  character, and their name like before, but instead of only manually entering the value of the stats between a minimum
  and maximum, the player is now allocated 10 stat upgrade points which he can use to upgrade the characters stats. In
  this mode the player can exceed the maximum randomised value of a stat, but this comes at the cost of severely
  weakening the other stats. This mode allows you to make a character more specialized at the cost of lower overall stat
  totals. This is similar to other classic RPG character creation systems but simplified to fit inside the confines of
  the project.

##### -- Battle --

Hardcore mode unlocks a new battle system that allows players to directly control the combatants behavior:

- The player can choose the combatants that will fight in each duel and then will be presented with the option of which
  skill to use. This gives players the opportunity to play the game in a more strategic way. Why spend your resources
  delivering a Heavy Attack to an already weak enemy when a Weak Attack would do? The selectable options are limited by
  the resource values of the character, and the player is informed if when they don't have enough resources to perform a
  skill, and how far they are from being able to. The player is also informed before choosing the attack how much damage
  it does as well as being given a short flavor description of each attack and any other effects it may have.

## The Team

by:  
**Ben The Warrior**
- [Joaodss](https://github.com/Joaodss)
- [MigNeves](https://github.com/MigNeves)
- [ShaunRly](https://github.com/ShaunRly)
- [simonpedrorios](https://github.com/simonpedrorios)
- [UrszulaF](https://github.com/UrszulaF)

