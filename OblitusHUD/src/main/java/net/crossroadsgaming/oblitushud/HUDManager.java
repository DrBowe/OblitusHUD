/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import java.util.HashMap;
import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import org.bukkit.Bukkit;


/**
 *
 * @author Chris
 */
public class HUDManager {
    private static HashMap<OblitusPlayer, PlayerHUD> playerHuds= new HashMap<OblitusPlayer, PlayerHUD>();
    
    public static PlayerHUD getHUD(OblitusPlayer cp){
        return playerHuds.get(cp);
    }
    
    public static void registerPlayer(OblitusPlayer cp){
        playerHuds.put(cp, new PlayerHUD(cp));
    }
    
    public static boolean hasPlayer(OblitusPlayer cp){
        return playerHuds.containsKey(cp);
    }
    
    private static OblitusHUD getPlugin(){
        return (OblitusHUD)Bukkit.getPluginManager().getPlugin("CryptHUD");
    }
}
