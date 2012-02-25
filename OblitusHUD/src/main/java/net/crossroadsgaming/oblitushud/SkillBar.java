/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import net.crossroadsgaming.oblituscore.skills.AbstractSkillTypes.OblitusSkill;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;

/**
 *
 * @author Chris
 */
public class SkillBar extends ScreenObject {

    private GenericTexture sBar_Background, sBar_Outline, slot1, slot2, slot3, slot4;
    private GenericGradient activeBox;
    public SkillBar(OblitusPlayer player) {
        super(player, 0, 0, 138, 63);
        x = WIDTH/2 - width/2;
        y = HEIGHT - height - 20;
        initializeSkillBar();
        initializeSkillIcons();
    }
    
    @Override
    public void display() {
        screen.attachWidgets(plugin, sBar_Background, sBar_Outline, slot1, slot2, slot3, slot4, activeBox);
    }
    
    public void update(){
        for (int i = 1; i < 5; i++){
            setSkillIcon(player.getSlottedSkill(i), i);
        }
        activeBox.setX(getActiveSlot().getX()).setY(getActiveSlot().getY());
    }
    
    public void setSkillIcon(OblitusSkill skill, int slot) {
        switch (slot) {
            case 1:
                slot1.setUrl(skill.getIcon());
                break;
            case 2:
                slot2.setUrl(skill.getIcon());
                break;
            case 3:
                slot3.setUrl(skill.getIcon());
                break;
            case 4:
                slot4.setUrl(skill.getIcon());
                break;
        }
    }
    
    public GenericTexture getActiveSlot(){
        switch(player.getActiveSlot()){
            case 1:
                return slot1;
            case 2:
                return slot2;
            case 3:
                return slot3;
            case 4:
                return slot4;
            default:
                return null;
        }
    }
    
    private void initializeSkillBar(){
        sBar_Background = new GenericTexture("http://dl.dropbox.com/u/31465574/SkillBarBackground3.png");
        sBar_Outline = new GenericTexture("http://dl.dropbox.com/u/31465574/SkillBarOutlines.png");
        buildSkillBar(sBar_Background, RenderPriority.Highest);
        buildSkillBar(sBar_Outline, RenderPriority.Low);
    }
    
    private void initializeSkillIcons(){
        slot1 = new GenericTexture(player.getSlottedSkill(1).getIcon());
        slot1.setX(x+(int)(.164*width)).setY((int)(.145*height)+y+(int)(.240*height)).setWidth((int)(.13*width)).setHeight((int)(.27*height)).setPriority(RenderPriority.High);
        System.out.println("Bar X: "+x+" Skill 1 X:"+((int)(.164*width)+x)+" Bar Y: "+y+" Skill 1 Y: "+(y+(int)(.118*height)));
        slot2 = new GenericTexture(player.getSlottedSkill(2).getIcon());
        slot2.setX(x+(int)(.327*width)).setY((int)(.145*height)+y+(int)(.240*height)).setWidth((int)(.13*width)).setHeight((int)(.27*height)).setPriority(RenderPriority.High);
        slot3 = new GenericTexture(player.getSlottedSkill(3).getIcon());
        slot3.setX(x+(int)(.564*width)).setY((int)(.145*height)+y+(int)(.240*height)).setWidth((int)(.13*width)).setHeight((int)(.27*height)).setPriority(RenderPriority.High);
        slot4 = new GenericTexture(player.getSlottedSkill(4).getIcon());
        slot4.setX(x+(int)(.727*width)).setY((int)(.145*height)+y+(int)(.240*height)).setWidth((int)(.13*width)).setHeight((int)(.27*height)).setPriority(RenderPriority.High);
        activeBox = new GenericGradient();
        activeBox.setColor(new Color(1.0F, 1.0F, 0F, .25F));
        activeBox.setWidth((int)(.13*width)).setHeight((int)(.27*height)).setX(getActiveSlot().getX()).setY(getActiveSlot().getY());
        activeBox.setPriority(RenderPriority.Normal);
    }
    
    private void buildSkillBar(GenericTexture sBarPiece, RenderPriority priority) {
        sBarPiece.setWidth(width).setHeight(height).setX(x).setY(y);
        sBarPiece.setPriority(priority);
    }
}
