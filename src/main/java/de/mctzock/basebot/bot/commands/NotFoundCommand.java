/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 08.12.2020
 */

package de.mctzock.basebot.bot.commands;

import de.mctzock.basebot.bot.Bot;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class NotFoundCommand implements Command{

    @Override
    public void exec(String[] args, MessageCreateEvent event) {
        EmbedBuilder b = new EmbedBuilder()
        .setTitle("Fehler")
        .setFooter(Bot.getInstance().getName())
        .setColor(Color.RED)
        .setDescription("Dieser Befehl wurde nicht gefunden.");
        event.getMessage().getChannel().sendMessage(b);
    }

    @Override
    public String getDescription() {
        return "";
    }
}
