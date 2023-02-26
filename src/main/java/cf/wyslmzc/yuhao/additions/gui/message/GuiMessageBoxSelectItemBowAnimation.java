package cf.wyslmzc.yuhao.additions.gui.message;

import java.io.File;
import java.io.IOException;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.items.ItemAddedBow;
import cf.wyslmzc.yuhao.additions.util.models.ItemModelManager;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a popup message to select an animation for an animated texture.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiMessageBoxSelectItemBowAnimation extends GuiMessageBoxSelectItemAnimation {

	protected ItemAddedBow bowItem;
	protected String textureEnding;

	public GuiMessageBoxSelectItemBowAnimation(GuiScreen parentScreen, Addon addon, ItemAddedBow item, String textureEnding, boolean isColor) {
		super(parentScreen, addon, item, isColor);
		this.bowItem = item;
	}
	
	protected void saveTextureAnimation(File fileChosen) throws IOException {
		super.saveTextureAnimation(fileChosen);
		ItemModelManager.saveBowPullingTextureAnimation(this.addon, this.bowItem, textureEnding, this.isColor, fileChosen);
	}
}
