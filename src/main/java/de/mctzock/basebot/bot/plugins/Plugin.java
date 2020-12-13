/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 09.12.2020
 */

package de.mctzock.basebot.bot.plugins;

public class Plugin {

    private String mainClass;

    public Plugin(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getMainClass() {
        return mainClass;
    }
}
