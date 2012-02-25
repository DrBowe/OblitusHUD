/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;


import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import org.bukkit.Bukkit;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Chris
 */
public abstract class ScreenObject {
    protected int x, y, width, height;
    protected int WIDTH, HEIGHT;
    protected OblitusPlayer player;
    protected InGameHUD screen;
    protected OblitusHUD plugin;
    
    public ScreenObject(OblitusPlayer player, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
        screen = ((SpoutPlayer)player.getHandle()).getMainScreen();
        this.WIDTH = screen.getWidth();
        this.HEIGHT = screen.getHeight();
        plugin = (OblitusHUD)Bukkit.getPluginManager().getPlugin("OblitusHUD");
    }
    
    public abstract void display();
    
}
