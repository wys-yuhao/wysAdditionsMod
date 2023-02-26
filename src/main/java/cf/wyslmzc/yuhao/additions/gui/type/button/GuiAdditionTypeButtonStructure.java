package cf.wyslmzc.yuhao.additions.gui.type.button;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.GuiViewStructures;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeStructure;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;

/**
 * Button to see the list of structures.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018
 */
public class GuiAdditionTypeButtonStructure extends GuiAdditionTypeButtonColored {
	
	public static final int BUTTON_COLOR_HOVER = 0xff311d63;
	public static final int BUTTON_COLOR_LIGHT = 0xff5a3a9a;
	public static final int BUTTON_COLOR_DARK = 0xff130b29;

	public GuiAdditionTypeButtonStructure(GuiView viewScreen, Addon addon) {
		super(viewScreen, addon);
		
		int numAdded = AdditionTypeStructure.INSTANCE.getAllAdditions(this.addon).size();
		
		this.setLabel(I18n.format("gui.view.addon.structures.label", numAdded));
		this.setColors(CommonGuiUtils.ADDITION_BUTTON_COLOR, BUTTON_COLOR_HOVER, BUTTON_COLOR_LIGHT, BUTTON_COLOR_DARK);
	}

	@Override
	public void onClick() {
		CommonGuiUtils.playClickSound();
		this.viewScreen.mc.displayGuiScreen(new GuiViewStructures(this.viewScreen, I18n.format("gui.view.addon.structures.title", this.addon.name), this.addon));
	}
	
	@Override
	public boolean isAdvanced() {
		return true;
	}

}
