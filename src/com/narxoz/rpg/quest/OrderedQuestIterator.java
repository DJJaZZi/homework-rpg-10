package com.narxoz.rpg.quest;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderedQuestIterator implements QuestIterator {
    private final List<Quest> quests;
    private int position = 0;

    public OrderedQuestIterator(List<Quest> quests) {
        this.quests = quests;
    }

    @Override
    public boolean hasNext() {
        return position < quests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return quests.get(position++);
    }
}