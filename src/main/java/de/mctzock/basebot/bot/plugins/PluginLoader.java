/*
 * Copyright (c) 2020 Ben Siebert. All rights reserved.
 * Created on 09.12.2020
 */

package de.mctzock.basebot.bot.plugins;

import org.ini4j.Ini;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipInputStream;

public class PluginLoader {

    public static ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    public static void reloadPlugins(){
        File pDir = new File("./plugins");
        if(!pDir.isDirectory()){pDir.mkdir();}
        File[] files = pDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar");
            }
        });

        for(File plugin : files){
            // get plugins meta data
            System.out.println("[PLUGINS] Trying to load plugin " + plugin.getName());
            try {
                JarFile jar = new JarFile(plugin);
                JarEntry meta = jar.getJarEntry("plugin.ini");
                Ini i = new Ini(jar.getInputStream(meta));
                if(i.get("plugin", "main") != null){
                    String mainClass = i.get("plugin", "main");
                    URLClassLoader child = new URLClassLoader(
                            new URL[] {plugin.toURI().toURL()},
                            PluginLoader.class.getClassLoader()
                    );
                    Class main = Class.forName(mainClass, true, child);
                    Method mainMethod = main.getDeclaredMethod("load");
                    Object instance = main.newInstance();
                    mainMethod.invoke(instance);
                    plugins.add(new Plugin(mainClass));
                }else {
                    System.err.println("[PLUGINS] Could not load plugin " + plugin.getName() + ": No main class defined in plugin.ini");
                }
            } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                // e.printStackTrace();
                System.err.println("[PLUGINS] Could not load plugin " + plugin.getName() + ": " + e.getMessage());
            }
        }
    }
}
