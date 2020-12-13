/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 08.12.2020
 */

package de.mctzock.basebot.main;

import de.mctzock.basebot.bot.Bot;
import de.mctzock.basebot.bot.commands.ListCommand;
import de.mctzock.basebot.bot.plugins.PluginLoader;

public class Main {

    public static void main(String[] args) {
        Bot b = new Bot("BaseBot", "Ben Siebert", "1.0", "b!");
        b.registerCommand("list", new ListCommand());
        // Load Plugins
        PluginLoader.reloadPlugins();

        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run() {
                System.out.println("Exited!");
                super.run();
            }
        });
    }
}
