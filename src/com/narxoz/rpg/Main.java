package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        List<Hero> heroes = Arrays.asList(
                new Hero("Arthur", 100, 20, 10),
                new Hero("Merlin", 80, 40, 5)
        );

        QuestLog questLog = new QuestLog();
        questLog.addQuest(new Quest("Collection of herbs", "Collect 10 healing herbs.", 50, QuestPriority.LOW));
        questLog.addQuest(new Quest("Caravan security", "Protect the merchants before reaching the city.", 150, QuestPriority.MEDIUM));
        questLog.addQuest(new Quest("Kill the Goblin King", "Clear the cave of the leader.", 500, QuestPriority.HIGH));
        questLog.addQuest(new Quest("Mail delivery", "Take the letter to the neighboring village.", 20, QuestPriority.LOW));
        questLog.addQuest(new Quest("City defense", "Fight off the undead's night attack!", 1000, QuestPriority.URGENT));

        CouncilEngine engine = new CouncilEngine(heroes, questLog);
        CouncilRunResult result = engine.runCouncil();

        System.out.println("\n--- Council results ---");
        System.out.println("Total number of quests reviewed: " + result.getQuestsReviewed());
        System.out.println("Urgent quests found: " + result.getHighPriorityQuestsFound());
        System.out.println("Officers participated: " + result.getOfficersParticipating());
    }
}