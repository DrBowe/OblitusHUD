package net.crossroadsgaming.oblitushud;

import net.crossroadsgaming.oblituscore.players.OblitusPlayer;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Chris
 */
public class PlayerBar extends ScreenObject{

    private SpoutPlayer splayer;
    private GenericGradient healthBar, energyBar, experienceBar, skillCastBar;
    private GenericTexture pBar_Base, pBar_Background, pBar_HealthAndMana;

    public PlayerBar(OblitusPlayer player) {
        super(player, 0, 0, 200, 35);
        x = WIDTH / 2 - width / 2;
        y = HEIGHT - height - 20;
        cleanMainScreen();
        initializeHealthBar();
        initializeEnergyBar();
        initializeExperienceBar();
        initializePlayerBar();
        screen.attachWidgets(plugin, healthBar, energyBar, /*experienceBar,*/ pBar_Base, pBar_Background, pBar_HealthAndMana);
    }

    public void display() {
        screen.attachWidgets(plugin, healthBar, energyBar/*, experienceBar*/);
    }

    public void updateHealth() {
        int maxWidth = (int) (.415 * width);
        int xCoor = x + (int) (.028 * width);
        setCoordinateWidth(healthBar, xCoor + maxWidth - (int)(player.getPercentHealth() * maxWidth), xCoor+maxWidth);
    }
    
    public void updateEnergy(){
        int maxWidth = (int) (.415 * width);
        energyBar.setWidth((int)(player.getPercentEnergy() * maxWidth));
    }
    

    private void initializePlayerBar() {
        pBar_Base = new GenericTexture("http://dl.dropbox.com/u/31465574/PlayerBarBase.png");
        pBar_Background = new GenericTexture("http://dl.dropbox.com/u/31465574/PlayerBarBackground.png");
        pBar_HealthAndMana = new GenericTexture("http://dl.dropbox.com/u/31465574/PlayerBar_HealthAndMana.png");
        buildPlayerBar(pBar_Base, RenderPriority.Lowest);
        buildPlayerBar(pBar_Background, RenderPriority.High);
        buildPlayerBar(pBar_HealthAndMana, RenderPriority.Normal);
    }

    private void initializeHealthBar() {
        healthBar = new GenericGradient();
        int xCoor, yCoor, maxWidth, relHeight;
        xCoor = x + (int) (.028 * width);
        yCoor = y + (int) (.486 * height);
        relHeight = (int) (.371 * height);
        maxWidth = (int) (.415 * width);
        healthBar.setColor(new Color(0.644F, 0F, 0F, 1F)).setX(xCoor).setY(yCoor).setHeight(relHeight);
        setCoordinateWidth(healthBar, xCoor + maxWidth - (int)(player.getPercentHealth() * maxWidth), xCoor+maxWidth);
        healthBar.setDirty(true);
        healthBar.setPriority(RenderPriority.Low);
    }

    private void initializeEnergyBar() {
        energyBar = new GenericGradient();
        int xCoor, yCoor, maxWidth, relHeight;
        xCoor = x + (int) (0.561 * width);
        yCoor = y + (int) (0.486 * height);
        relHeight = (int) (0.371 * height);
        maxWidth = (int) (.415 * width);
        energyBar.setColor(new Color(0.004F, 0.059F, 0.402F, 1F)).setX(xCoor).setY(yCoor).setHeight(relHeight);
        energyBar.setWidth((int)(player.getPercentEnergy() * maxWidth));
        energyBar.setPriority(RenderPriority.Low);
        energyBar.setDirty(true);
    }

    private void initializeExperienceBar() {
        experienceBar = new GenericGradient();
        experienceBar.setColor(new Color(0F, 1F, 0F, 1F)).setX(10).setY(35).setHeight(5);
        
        experienceBar.setDirty(true);
    }

    private void cleanMainScreen() {
        screen.getExpBar().setY(7);
        screen.getHealthBar().setVisible(false);
        screen.getHungerBar().setVisible(false);
        screen.getArmorBar().setVisible(false);
        screen.setDirty(true);
        screen.getChatBar().setPriority(RenderPriority.Highest);
        screen.getChatTextBox().setY(screen.getChatTextBox().getY() - 63);

    }

    private void setCoordinateWidth(GenericGradient g, int x1, int x2) {
        int max, min;
        if (Math.max(x1, x2) == x1) {
            max = x1;
            min = x2;
        } else {
            max = x2;
            min = x1;
        }
        g.setX(min);
        g.setWidth(max - min);
    }
    
    private void buildPlayerBar(GenericTexture pBarPiece, RenderPriority priority){
        pBarPiece.setWidth(width).setHeight(height).setX(x).setY(y);
        pBarPiece.setPriority(priority);
    }
}
