package cf.wyslmzc.yuhao.additions.gui.type.button;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.GuiViewSoundEvents;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeSoundEvent;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.resources.I18n;

/**
 * Button to see the list of sound events.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since October 2018
 */
public class GuiAdditionTypeButtonSoundEvent extends GuiAdditionTypeButtonColored {
	
	public static final int BUTTON_COLOR_HOVER = 0xff791F67;
	public static final int BUTTON_COLOR_LIGHT = 0xffA4358B;
	public static final int BUTTON_COLOR_DARK = 0xff400936;

	public GuiAdditionTypeButtonSoundEvent(GuiView viewScreen, Addon addon) {
		super(viewScreen, addon);
		
		int numAdded = AdditionTypeSoundEvent.INSTANCE.getAllAdditions(this.addon).size();
		
		this.setLabel(I18n.format("gui.view.addon.soundEvents.label", numAdded));
		this.setColors(CommonGuiUtils.ADDITION_BUTTON_COLOR, BUTTON_COLOR_HOVER, BUTTON_COLOR_LIGHT, BUTTON_COLOR_DARK);
	}

	@Override
	public void onClick() {
		CommonGuiUtils.playClickSound();
		this.viewScreen.mc.displayGuiScreen(new GuiViewSoundEvents(this.viewScreen, I18n.format("gui.view.addon.soundEvents.title", this.addon.name), this.addon));
	}
	
	@Override
	public boolean isAdvanced() {
		return true;
	}

}
