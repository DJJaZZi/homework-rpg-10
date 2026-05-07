package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    @Override
    public void register(GuildMember member) {
        addSubscriber("general", member);

        if (member instanceof Captain) {
            addSubscriber("recon", member);
            addSubscriber("supplies", member);
            addSubscriber("healing", member);
        } else if (member instanceof Scout) {
            addSubscriber("orders", member);
        } else if (member instanceof Quartermaster) {
            addSubscriber("orders", member);
        } else if (member instanceof Healer) {
            addSubscriber("orders", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        for (GuildMember member : subscribersFor(topic)) {
            if (member != from) {
                member.receive(topic, from, payload);
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}