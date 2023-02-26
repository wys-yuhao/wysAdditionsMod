package cf.wyslmzc.yuhao.additions.gui.message;

import java.io.IOException;

import cf.wyslmzc.yuhao.additions.AdditionsMod;
import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.AddonLoader;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a message saying the resources are reloading, since otherwise the game just freezes and people might get confused.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiMessageBoxLockingAddon extends GuiMessageBox {
	
	private final Addon addon;
	private int delay = 20;

	public GuiMessageBoxLockingAddon(GuiScreen parentScreen, Addon addon) {
		super(parentScreen, I18n.format("gui.popup.lockAddon.title"), new TextComponentTranslation("gui.popup.lockAddon.message"), "");
		this.addon = addon;
	}

	@Override
	public void initGui() {
		super.initGui();
		
		this.buttonList.clear();
	}
	
	@Override
	public void updateScreen() {
		if (delay-- <= 0) {
			try {
    			AddonLoader.lockAddon(this.addon);
				
				this.mc.displayGuiScreen(new GuiMessageBox(this.parentScreen, I18n.format("gui.popup.shareAddon.success.title"), new TextComponentTranslation("gui.popup.shareAddon.success.message", this.addon.addonFolder.getName()), I18n.format("gui.buttons.goToFolder")) {
					@Override
				    protected void onFirstButtonClicked() {
						CommonGuiUtils.openFolder(GuiMessageBoxLockingAddon.this.addon.addonFolder.getParentFile());
						
						if (this.parentScreen instanceof GuiView) {
							((GuiView) parentScreen).refreshView();
						}
						
						super.onFirstButtonClicked();
					}
				});
			} catch (IOException e) {
				AdditionsMod.logger.warn("Error while locking addon " + this.addon.id, e);
				
				this.mc.displayGuiScreen(new GuiMessageBox(this.parentScreen, I18n.format("gui.popup.shareAddon.problem.title"), new TextComponentTranslation("gui.popup.shareAddon.problem.message", e.getClass() + ": " + e.getMessage()), I18n.format("gui.buttons.back")));
			}
		}
	}

}
