/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import net.crossroadsgaming.oblituscore.CoreManager;
import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;

/**
 *
 * @author Chris
 */
public class OblitusSpoutListener extends SpoutListener{
    public OblitusHUD plugin;
    public OblitusSpoutListener(OblitusHUD instance){
        this.plugin = instance;
        Bukkit.getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, this, Priority.Highest, plugin);
    }
    @Override
    public void onSpoutCraftEnable(SpoutCraftEnableEvent event){
        Player p = event.getPlayer().getPlayer();
        OblitusPlayer cp = CoreManager.getPlayer(p);
        if(!HUDManager.hasPlayer(cp)){
            HUDManager.registerPlayer(cp);           
        } else { 
            HUDManager.getHUD(cp).getPlayerBar().display();
            HUDManager.getHUD(cp).getSkillBar().display();
        }
        
        /*if(!MenuManager.hasPlayer(cp)){
            final OblitusPlayer op = cp;
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                public void run(){
                                MenuManager.registerPlayer(op);
                }
            }, 100);

        }*/

    }
}
