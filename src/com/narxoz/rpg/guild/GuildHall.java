package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    private int messagesRouted = 0;
    private int membersNotified = 0;

    @Override
    public void register(GuildMember member) {
        addSubscriber("general", member);

        if (member instanceof Captain) {
            addSubscriber("recon", member);
            addSubscriber("supplies", member);
            addSubscriber("healing", member);
            addSubscriber("lore", member);
        } else if (member instanceof Scout) {
            addSubscriber("orders", member);
        } else if (member instanceof Quartermaster) {
            addSubscriber("orders", member);
        } else if (member instanceof Healer) {
            addSubscriber("orders", member);
        } else if (member instanceof Loremaster) {
            addSubscriber("lore", member);
            addSubscriber("history", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        messagesRouted++;
        for (GuildMember member : subscribersFor(topic)) {
            if (member != from) {
                member.receive(topic, from, payload);
                membersNotified++;
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }

    public int getMessagesRouted() { return messagesRouted; }
    public int getMembersNotified() { return membersNotified; }
}