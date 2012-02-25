/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import java.util.ArrayList;
import java.util.HashMap;
import net.crossroadsgaming.oblituscore.CoreManager;
import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import net.crossroadsgaming.oblituscore.skills.AbstractSkillTypes.OblitusSkill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.ListWidgetItem;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Chris
 */
public class PlayerMenu {
    private SpoutPlayer sPlayer;
    private OblitusPlayer oPlayer;
    private InGameHUD screen;
    private EnhancedPopup main, skillSelection, attributes, about;
    
    private GenericButton character, skills, mastery;
    private GenericListWidget skillList;
    
    private HashMap<ListWidgetItem, OblitusSkill> skillItems = new HashMap<ListWidgetItem, OblitusSkill>();
    
    public PlayerMenu(SpoutPlayer sp){
        this.sPlayer = sp;
        this.oPlayer = CoreManager.getPlayer(sp);
        this.screen = sp.getMainScreen();
        initializeMainScreen();
        initializeSkillScreen();
    }
    
    public void openPage(GenericPopup newPage){
        if (screen.getActivePopup() != null)
            screen.getActivePopup().close();
        screen.attachPopupScreen(newPage);
    }
    
    private void initializeMainScreen(){
        main = new EnhancedPopup(getPlugin(), screen, 400, 200, "OblitusCraft");
        main.setBackground("http://dl.dropbox.com/u/31465574/ScrollBackground.png");
        openPage(main);
    }
    
    private void initializeSkillScreen(){
        skillSelection = new EnhancedPopup(getPlugin(), screen, 400, 200, "Skills");
        skillSelection.setBackground("http://dl.dropbox.com/u/31465574/ScrollBackground.png");
        skillList = new GenericListWidget();
        for (OblitusSkill skill : oPlayer.getSkills()){
            ChatColor color = oPlayer.hasSkill(skill)? ChatColor.GREEN : ChatColor.DARK_RED;
            ListWidgetItem listItem = new ListWidgetItem(color+skill.getName(), "", skill.getIcon());
            skillItems.put(listItem, skill);
            skillList.setX(skillSelection.getWidth()/2).setY(10).setWidth(skillSelection.getWidth()/2-10).setHeight(skillSelection.getHeight()-10);
            skillList.addItem(listItem);
            skillSelection.attachRelWidget(skillList);
        }
        openPage(skillSelection);
    }
    
    private OblitusHUD getPlugin(){
        return (OblitusHUD)Bukkit.getPluginManager().getPlugin("OblitusHUD");
    }
    
}
