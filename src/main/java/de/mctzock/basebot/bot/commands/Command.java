package de.mctzock.basebot.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;

public interface Command {

    void exec(String[] args, MessageCreateEvent event);

    String getDescription();
}
