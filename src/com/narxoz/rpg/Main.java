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
        questLog.addQuest(new Quest("Collection of herbs", QuestPriority.LOW, 50, false));
        questLog.addQuest(new Quest("Caravan security", QuestPriority.NORMAL, 150, false));
        questLog.addQuest(new Quest("Kill the Goblin King", QuestPriority.HIGH, 500, true));
        questLog.addQuest(new Quest("Mail delivery", QuestPriority.LOW, 20, false));
        questLog.addQuest(new Quest("City defense", QuestPriority.URGENT, 1000, true));

        CouncilEngine engine = new CouncilEngine(heroes, questLog);
        CouncilRunResult result = engine.runCouncil();

        System.out.println("\n--- Council results ---");
        System.out.println("Total quests traversed: " + result.getQuestsTraversed());
        System.out.println("Messages routed: " + result.getMessagesRouted());
        System.out.println("Members notified: " + result.getMembersNotified());
    }
}