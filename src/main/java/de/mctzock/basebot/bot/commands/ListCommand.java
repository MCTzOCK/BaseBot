/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 08.12.2020
 */

package de.mctzock.basebot.bot.commands;

import de.mctzock.basebot.bot.Bot;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class ListCommand implements Command{

    public void exec(String[] args, MessageCreateEvent event) {
        if(args.length == 0){
            EmbedBuilder b = new EmbedBuilder();
            b.setFooter(Bot.getInstance().getName(), "https://cdn.discordapp.com/embed/avatars/1.png");
            b.setTitle("Liste aller Befehle");
            b.setColor(Color.CYAN);
            for(String cmd : Bot.getInstance().getCommands().keySet()){
                b.addField(Bot.getInstance().getPrefix() + cmd, Bot.getInstance().getCommands().get(cmd).getDescription());
            }
            event.getMessage().getChannel().sendMessage(b);
        }
    }

    @Override
    public String getDescription() {
        return "Liste alle Befehle auf!";
    }
}
