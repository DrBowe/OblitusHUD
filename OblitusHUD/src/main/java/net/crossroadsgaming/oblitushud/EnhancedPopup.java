/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.crossroadsgaming.oblitushud;

import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.InGameHUD;

/**
 *
 * @author Chris
 */
public class EnhancedPopup extends GenericPopup{
    private String header;
    private OblitusHUD mainPlugin;
    private InGameHUD mainScreen;
    
    public EnhancedPopup(OblitusHUD plugin, InGameHUD screen, int x, int y , int width, int height, String header){
        this.mainPlugin = plugin;
        this.header = header;
        this.mainScreen = screen;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        initializeHeader();
    }
    
    /**
     * An auto-centered EnhancedPopup
     * @param plugin
     *  Instance of your plugin
     * @param screen
     *  Screen to be attached to, and retrieve width/height from
     * @param width
     *  Width of the Popup
     * @param height
     *  Height of the Popup
     * @param header 
     *  Header of the Popup
     */
    public EnhancedPopup(OblitusHUD plugin, InGameHUD screen, int width, int height, String header){
        this.header = header;
        this.mainScreen = screen;
        this.mainPlugin = plugin;
        setWidth(width);
        setHeight(height);
        setX(mainScreen.getWidth()/2 - width/2);
        setY(mainScreen.getHeight()/2 - height/2);
        initializeHeader();
    }
    
    private void initializeHeader(){
        GenericLabel headerLabel = new GenericLabel(header);
        headerLabel.setResize(true).setFixed(true);
        headerLabel.setX(width/2-headerLabel.getWidth()/2);
        headerLabel.setY((int)(.04*height));
        this.attachRelWidget(headerLabel);
    }
    
    public void attachRelWidget(Widget w){
        w.setX(X+w.getX()).setY(Y+w.getY());
        this.attachWidget(mainPlugin, w);
    }
    
    public void setBackground(String bgUrl){
        GenericTexture background = new GenericTexture(bgUrl);
        background.setX(0).setY(0).setWidth(width).setHeight(height).setPriority(RenderPriority.Highest);
        attachRelWidget(background);
    }
}
