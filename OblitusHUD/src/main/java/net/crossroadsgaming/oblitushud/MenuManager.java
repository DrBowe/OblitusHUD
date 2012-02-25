/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import java.util.HashMap;
import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Chris
 */
public class MenuManager {
    private static HashMap<OblitusPlayer, PlayerMenu> playerMenus = new HashMap<OblitusPlayer, PlayerMenu>();
    
    public static void registerPlayer(OblitusPlayer player){
        if (!playerMenus.containsKey(player)){
            playerMenus.put(player, new PlayerMenu((SpoutPlayer)player.getHandle()));
        }
    }
    
    public static boolean hasPlayer(OblitusPlayer player){
        return playerMenus.containsKey(player);
    }
    
}
