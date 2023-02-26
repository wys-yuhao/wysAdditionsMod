package cf.wyslmzc.yuhao.additions.gui;

import cf.wyslmzc.yuhao.additions.AdditionsMod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.ITextComponent;

/**
 * Overlays something on another gui screen
 * 
 * @author Rebeca
 */
public abstract class GuiScreenOverlay extends GuiScreen {
	
	protected final GuiScreen parentScreen;

    public GuiScreenOverlay(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	try {
    		if (this.parentScreen.mc == null) {
            	this.parentScreen.setWorldAndResolution(this.mc, this.width, this.height);
            }
    		
    		this.parentScreen.drawScreen(-1, -1, partialTicks);
    	} catch (Exception e) {
    		AdditionsMod.logger.warn("Unable to draw parent screen.", e);
    	}
    	
    	this.drawScreenOverlay(mouseX, mouseY, partialTicks);
    	
    	super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected abstract void drawScreenOverlay(int mouseX, int mouseY, float partialTicks);
	
}
