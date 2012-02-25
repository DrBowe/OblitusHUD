/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import net.crossroadsgaming.oblituscore.players.OblitusPlayer;

/**
 *
 * @author Chris
 */
public class PlayerHUD {
    private SkillBar sBar;
    private PlayerBar pBar;
    public PlayerHUD(OblitusPlayer player){
        pBar = new PlayerBar(player);
        sBar = new SkillBar(player);
        pBar.display();
        sBar.display();       
    }
    
    public SkillBar getSkillBar(){
        return sBar;
    }
    
    public PlayerBar getPlayerBar(){
        return pBar;
    }
}
