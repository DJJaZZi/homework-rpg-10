package com.narxoz.rpg.quest;

import java.util.List;
import java.util.NoSuchElementException;

public class PriorityQuestIterator implements QuestIterator {
    private final List<Quest> quests;
    private final QuestPriority minPriority;
    private int position = 0;

    public PriorityQuestIterator(List<Quest> quests, QuestPriority minPriority) {
        this.quests = quests;
        this.minPriority = minPriority;
    }

    @Override
    public boolean hasNext() {

        while (position < quests.size()) {

            if (quests.get(position).getPriority().compareTo(minPriority) >= 0) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return quests.get(position++);
    }
}