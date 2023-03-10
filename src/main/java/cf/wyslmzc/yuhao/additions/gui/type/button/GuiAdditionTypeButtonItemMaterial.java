package cf.wyslmzc.yuhao.additions.gui.type.button;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.GuiViewItemMaterials;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeItemMaterial;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;

/**
 * Button to see the list of creative tabs.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiAdditionTypeButtonItemMaterial extends GuiAdditionTypeButtonColored {
	
	public static final int BUTTON_COLOR_HOVER = 0xff491c69;
	public static final int BUTTON_COLOR_LIGHT = 0xff733b9a;
	public static final int BUTTON_COLOR_DARK = 0xff2b103e;

	public GuiAdditionTypeButtonItemMaterial(GuiView viewScreen, Addon addon) {
		super(viewScreen, addon);
		
		int numAdded = AdditionTypeItemMaterial.INSTANCE.getAllAdditions(this.addon).size();
		
		this.setLabel(I18n.format("gui.view.addon.itemMaterials.label", numAdded));
		this.setColors(CommonGuiUtils.ADDITION_BUTTON_COLOR, BUTTON_COLOR_HOVER, BUTTON_COLOR_LIGHT, BUTTON_COLOR_DARK);
	}

	@Override
	public void onClick() {
		CommonGuiUtils.playClickSound();
		this.viewScreen.mc.displayGuiScreen(new GuiViewItemMaterials(this.viewScreen, I18n.format("gui.view.addon.itemMaterials.title", this.addon.name), this.addon));
	}
	
	@Override
	public boolean isAdvanced() {
		return true;
	}

}
