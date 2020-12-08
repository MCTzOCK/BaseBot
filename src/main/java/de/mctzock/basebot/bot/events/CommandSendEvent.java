/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 08.12.2020
 */

package de.mctzock.basebot.bot.events;

import de.mctzock.basebot.bot.Bot;
import de.mctzock.basebot.bot.commands.NotFoundCommand;

public class CommandSendEvent {

    public static void init(){
        Bot.getInstance().getApi().addMessageCreateListener(event -> {
            if(event.getMessage().getContent().startsWith(Bot.getInstance().getPrefix())){
                String[] s = event.getMessage().getContent().split(" ");
                s[0] = s[0].replaceAll(Bot.getInstance().getPrefix(), "");
                if(Bot.getInstance().getCommands().containsKey(s[0])){
                    String[] args = new String[s.length - 1];
                    for(int i = 0; i < args.length; i++){ args[i] = s[i + 1]; }
                    Bot.getInstance().getCommands().get(s[0]).exec(args, event);
                }else {
                    NotFoundCommand nfc = new NotFoundCommand();
                    nfc.exec(null, event);
                }
            }
        });
    }
}
