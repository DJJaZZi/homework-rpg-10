package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        System.out.println(" [Lore Master " + getName() + "] shares wisdom (" + topic + "): " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println(" [Lore Master " + getName() + "] records in the chronicle a message from " + from.getName() + " [" + topic + "]: " + payload);
    }
}