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
    private final Loremaster loremaster; // Добавлен Loremaster

    public CouncilEngine(List<Hero> heroes, QuestLog questLog) {
        this.heroes = heroes;
        this.questLog = questLog;

        this.guildHall = new GuildHall();

        this.captain = new Captain("Valkire", guildHall);
        this.scout = new Scout("Shadow", guildHall);
        this.quartermaster = new Quartermaster("Grim", guildHall);
        this.healer = new Healer("Elaiza", guildHall);
        this.loremaster = new Loremaster("Eldrin", guildHall);
    }

    public CouncilRunResult runCouncil() {
        System.out.println("\n=== THE GUILD COUNCIL MEETING BEGINS ===");

        System.out.println("\n--- Discussion of preparation ---");
        scout.reportRoute("recon", "Goblin activity has been detected in the Eastern Forest.");
        captain.issueOrder("orders", "Prepare troops for marching to the East!");
        quartermaster.requestSupplies("supplies", "We need more arrows and provisions.");
        healer.prepareAid("healing", "The hospital is ready to receive the wounded.");
        loremaster.shareLore("lore", "The ancient texts say Goblins fear fire!"); // Демонстрация Лоремастера

        int totalQuests = 0;

        System.out.println("\n--- Task Log Analysis (Arrival Order) ---");
        QuestIterator orderedIterator = questLog.ordered();
        while (orderedIterator.hasNext()) {
            Quest q = orderedIterator.next();
            System.out.println("Quest: " + q.getTitle() + " | Reward: " + q.getRewardGold() + " | Priority: " + q.getPriority());
            totalQuests++;
        }

        System.out.println("\n--- Task Log Analysis (Highest Rewards) ---");
        // Демонстрируем новый итератор
        QuestIterator rewardIterator = questLog.sortedByReward();
        while (rewardIterator.hasNext()) {
            Quest q = rewardIterator.next();
            System.out.println("Lucrative quest: " + q.getTitle() + " | Reward: " + q.getRewardGold());
        }

        System.out.println("\n=== THE MEETING IS ENDED ===");

        return new CouncilRunResult(totalQuests, guildHall.getMessagesRouted(), guildHall.getMembersNotified());
    }
}