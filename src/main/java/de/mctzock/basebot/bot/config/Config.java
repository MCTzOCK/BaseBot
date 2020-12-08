/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 08.12.2020
 */

package de.mctzock.basebot.bot.config;

import org.ini4j.Ini;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    private String token;

    public Config(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static Config getConfig(){
        File c = new File("./config.ini");
        if(!c.exists()){
            try {
                c.createNewFile();
                FileWriter w = new FileWriter(c);
                w.write("[login]\ntoken = 0");
                w.close();
                System.out.println("Is this your first start? We have created a blank for you! Please configure it before you start again!");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Ini i = new Ini(c);
            String token = i.get("login", "token");
            Config cf = new Config(token);
            return cf;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
