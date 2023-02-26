package cf.wyslmzc.yuhao.additions.gui.type.card;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonStructure;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeStructure;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

/**
 * Displays info about an added structure.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiAdditionCardStructure extends GuiAdditionCardColored {

	private ResourceLocation addition;
	
	private String textId;
	private String filterId;
	
	public GuiAdditionCardStructure(GuiView viewScreen, Addon addon, ResourceLocation addition) {
		super(viewScreen, addon);
		
		this.addition = addition;
		
		this.textId = TextFormatting.GRAY + I18n.format("gui.view.additionType.id", TextFormatting.RESET + this.addition.toString() + TextFormatting.GRAY);
		this.filterId = this.textId.toLowerCase();
		
		this.allowEdit = false;
		
		this.setColors(GuiAdditionTypeButtonStructure.BUTTON_COLOR_DARK, GuiAdditionTypeButtonStructure.BUTTON_COLOR_HOVER);
	}
	
	@Override
	public int getCardHeight() {
		return 43;
	}

	@Override
	protected void drawCardInfo(int mouseX, int mouseY) {
		CommonGuiUtils.drawStringWithDots(this.viewScreen.getFontRenderer(), this.textId, this.width - 30, this.x + 10, this.y + 8, 0xFFFFFF);
	}

	@Override
	protected void deleteAddition() {
		AdditionTypeStructure.INSTANCE.deleteAddition(this.addon, this.addition);
	}
	
	@Override
	public boolean filterApplies(String filter) {
		return filter == null || filter.isEmpty() || this.filterId.contains(filter.toLowerCase());
	}

}
