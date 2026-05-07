package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

public class CouncilEngine {
    private final GuildHall guildHall;
    private final QuestLog questLog;
    private final List<Hero> heroes;

    private final Captain captain;
    private final Scout scout;
    private final Quartermaster quartermaster;
    private final Healer healer;

    public CouncilEngine(List<Hero> heroes, QuestLog questLog) {
        this.heroes = heroes;
        this.questLog = questLog;

        this.guildHall = new GuildHall();

        this.captain = new Captain("Valkire", guildHall);
        this.scout = new Scout("Shadow", guildHall);
        this.quartermaster = new Quartermaster("Grim", guildHall);
        this.healer = new Healer("Elaiza", guildHall);
    }

    public CouncilRunResult runCouncil() {
        System.out.println("\n=== THE GUILD COUNCIL MEETING BEGINS ===");

        System.out.println("\n--- Discussion of preparation ---");
        scout.reportRoute("recon", "Goblin activity has been detected in the Eastern Forest..");
        captain.issueOrder("orders", "Prepare troops for marching to the East!");
        quartermaster.requestSupplies("supplies", "We need more arrows and provisions..");
        healer.prepareAid("healing", "The hospital is ready to receive the wounded.");

        System.out.println("\n--- Task Log Analysis (Direct Order) ---");
        QuestIterator orderedIterator = questLog.getOrderedIterator();
        int totalQuests = 0;
        while (orderedIterator.hasNext()) {
            Quest q = orderedIterator.next();
            System.out.println("Quest: " + q.getName() + " | Reward: " + q.getRewardGold() + " | Priority: " + q.getPriority());
            totalQuests++;
        }

        System.out.println("\n--- Task Log Analysis (High Priority) ---");
        QuestIterator priorityIterator = questLog.getPriorityIterator(QuestPriority.HIGH);
        int highPriorityCount = 0;
        while (priorityIterator.hasNext()) {
            Quest q = priorityIterator.next();
            System.out.println("Urgent quest: " + q.getName());
            highPriorityCount++;
        }

        System.out.println("\n=== THE MEETING IS ENDED ===");
        return new CouncilRunResult(totalQuests, highPriorityCount, 4);
    }
}