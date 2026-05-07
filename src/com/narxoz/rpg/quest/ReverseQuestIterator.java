package com.narxoz.rpg.quest;

import java.util.List;
import java.util.NoSuchElementException;

public class ReverseQuestIterator implements QuestIterator {
    private final List<Quest> quests;
    private int position;

    public ReverseQuestIterator(List<Quest> quests) {
        this.quests = quests;

        this.position = quests.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return quests.get(position--);
    }
}