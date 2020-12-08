/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 08.12.2020
 */

package de.mctzock.basebot.bot;

import de.mctzock.basebot.bot.commands.Command;
import de.mctzock.basebot.bot.config.Config;
import de.mctzock.basebot.bot.events.CommandSendEvent;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.HashMap;

public class Bot {

    private String name;
    private String author;
    private String version;
    private String prefix;
    private static Bot instance;
    private DiscordApi api;
    private HashMap<String, Command> commands = new HashMap<String, Command>();

    public Bot(String name, String author, String version, String prefix) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.prefix = prefix;
        this.instance = this;
        this.init();
    }

    public void registerCommand(String name, Command command){
        this.commands.put(name, command);
    }

    protected void init(){
        DiscordApiBuilder b = new DiscordApiBuilder();
        b.setToken(Config.getConfig().getToken());
        DiscordApi api = b.login().join();
        this.api = api;
        CommandSendEvent.init();
    }

    public void start(){

    }

    public static Bot getInstance() {
        return instance;
    }

    public DiscordApi getApi() {
        return api;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}
