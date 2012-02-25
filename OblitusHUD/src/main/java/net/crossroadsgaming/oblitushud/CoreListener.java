/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import java.util.HashMap;
import net.crossroadsgaming.oblituscore.CoreManager;
import net.crossroadsgaming.oblituscore.eventHandling.OblitusCoreListener;
import net.crossroadsgaming.oblituscore.eventHandling.EnergyChangeEvent;
import net.crossroadsgaming.oblituscore.eventHandling.HealthChangeEvent;
import net.crossroadsgaming.oblituscore.eventHandling.SkillCastEvent;
import net.crossroadsgaming.oblituscore.eventHandling.SkillCastingEvent;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Chris
 */
public class CoreListener extends OblitusCoreListener {

    private OblitusHUD plugin;
    private HashMap<SpoutPlayer, GenericGradient> skillBars;

    public CoreListener(OblitusHUD instance) {
        this.plugin = instance;
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvent(Event.Type.CUSTOM_EVENT, this, Priority.High, plugin);
        this.skillBars = new HashMap<SpoutPlayer, GenericGradient>();
    }

    @Override
    public void onSkillCasting(SkillCastingEvent event) {
        SpoutPlayer sp = (SpoutPlayer) event.getPlayer().getHandle();
        //GenericLabel skillLabel = new GenericLabel(event.getName());

        if (skillBars.get(sp) == null) {
            GenericGradient skillBar = new GenericGradient();
            skillBar.setColor(new Color(0F, 1.0F, 0F, 1F));
            InGameHUD pScreen = sp.getMainScreen();
            int WIDTH = pScreen.getWidth();
            int HEIGHT = pScreen.getHeight();
            pScreen.setDirty(true);
            //skillLabel.setX(WIDTH/2).setY(HEIGHT-70).setWidth(50).setHeight(20);
            skillBar.setDirty(true);
            skillBar.setX((WIDTH / 2) - 50).setY(HEIGHT - 80).setWidth(event.getStage() * 20).setHeight(10);
            skillBar.setPriority(RenderPriority.Highest);
            pScreen.attachWidget(plugin, skillBar);
            skillBars.put(sp, skillBar);
        }
        
        else{skillBars.get(sp).setWidth(event.getStage()*20);}
    }
    
    @Override
    public void onHealthChange(HealthChangeEvent event){
        HUDManager.getHUD(event.getPlayer()).getPlayerBar().updateHealth();
    }
    
    @Override
    public void onSkillCast(SkillCastEvent event){
        GenericGradient bar = skillBars.get((SpoutPlayer)event.getPlayer().getHandle());
        bar.setWidth(0);
    }
    
    @Override
    public void onEnergyChange(EnergyChangeEvent event){
        HUDManager.getHUD(event.getPlayer()).getPlayerBar().updateEnergy();
    }
}
