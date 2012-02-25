package net.crossroadsgaming.oblitushud;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class OblitusHUD extends JavaPlugin{

    public void onDisable() {
        System.out.println("CryptHUD disabled");
    }

    public void onEnable() {
        new CoreListener(this);
        new OblitusSpoutListener(this);
        System.out.println("CryptHUD enabled");
    }
    
}
