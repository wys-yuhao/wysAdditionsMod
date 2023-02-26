package cf.wyslmzc.yuhao.additions.gui.type.button;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.GuiViewBlocks;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeBlock;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.resources.I18n;

/**
 * Button to see the list of blocks.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiAdditionTypeButtonBlock extends GuiAdditionTypeButtonColored {
	
	public static final int BUTTON_COLOR_HOVER = 0xff144909;
	public static final int BUTTON_COLOR_LIGHT = 0xff30842d;
	public static final int BUTTON_COLOR_DARK = 0xff082108;

	public GuiAdditionTypeButtonBlock(GuiView viewScreen, Addon addon) {
		super(viewScreen, addon);
		
		int numAdded = AdditionTypeBlock.INSTANCE.getAllAdditions(this.addon).size();
		
		this.setLabel(I18n.format("gui.view.addon.blocks.label", numAdded));
		this.setColors(CommonGuiUtils.ADDITION_BUTTON_COLOR, BUTTON_COLOR_HOVER, BUTTON_COLOR_LIGHT, BUTTON_COLOR_DARK);
	}

	@Override
	public void onClick() {
		CommonGuiUtils.playClickSound();
		this.viewScreen.mc.displayGuiScreen(new GuiViewBlocks(this.viewScreen, I18n.format("gui.view.addon.blocks.title", this.addon.name), this.addon));
	}

}
