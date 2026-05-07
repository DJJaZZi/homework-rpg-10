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

        int tempPosition = position;
        while (tempPosition < quests.size()) {
            if (quests.get(tempPosition).getPriority().compareTo(minPriority) >= 0) {
                return true;
            }
            tempPosition++;
        }
        return false;
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (position < quests.size()) {
            Quest current = quests.get(position);
            position++;
            if (current.getPriority().compareTo(minPriority) >= 0) {
                return current;
            }
        }
        throw new NoSuchElementException();
    }
}