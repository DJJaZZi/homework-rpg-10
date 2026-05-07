package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RewardSortedQuestIterator implements QuestIterator {
    private final List<Quest> sortedQuests;
    private int position = 0;

    public RewardSortedQuestIterator(List<Quest> quests) {
        this.sortedQuests = new ArrayList<>(quests);
        this.sortedQuests.sort((q1, q2) -> Integer.compare(q2.getRewardGold(), q1.getRewardGold()));
    }

    @Override
    public boolean hasNext() {
        return position < sortedQuests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) throw new NoSuchElementException();
        return sortedQuests.get(position++);
    }
}