package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestLog {

    private final List<Quest> quests = new ArrayList<>();

    public void addQuest(Quest quest) {
        if (quest != null) {
            quests.add(quest);
        }
    }

    public int size() {
        return quests.size();
    }

    public QuestIterator ordered() {
        return new OrderedQuestIterator(snapshot());
    }

    public QuestIterator sortedByReward() {
        return new RewardSortedQuestIterator(snapshot());
    }

    public QuestIterator reverse() {
        return new ReverseQuestIterator(snapshot());
    }

    public QuestIterator priorityAtLeast(QuestPriority threshold) {
        return new PriorityQuestIterator(snapshot(), threshold);
    }

    List<Quest> snapshot() {
        return Collections.unmodifiableList(new ArrayList<>(quests));
    }
}