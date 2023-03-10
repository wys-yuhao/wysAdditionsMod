package cf.wyslmzc.yuhao.additions.gui.type.card;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.functions.FunctionAdded;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonFunction;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditFunction;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeFunction;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

/**
 * Displays info about an added function.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiAdditionCardFunction extends GuiAdditionCardColored {

	private FunctionAdded addition;
	private String textCount;
	private String textId;
	private String filterId;
	
	public GuiAdditionCardFunction(GuiView viewScreen, Addon addon, FunctionAdded addition) {
		super(viewScreen, addon);
		
		this.addition = addition;
		
		this.textCount = TextFormatting.GRAY + I18n.format("gui.view.additionType.commands", TextFormatting.RESET + String.valueOf(this.addition.commands.size()) + TextFormatting.GRAY);
		this.textId = TextFormatting.GRAY + I18n.format("gui.view.additionType.id", TextFormatting.RESET + this.addition.id.toString() + TextFormatting.GRAY);
		
		this.filterId = this.textId.toLowerCase();
		
		this.setColors(GuiAdditionTypeButtonFunction.BUTTON_COLOR_DARK, GuiAdditionTypeButtonFunction.BUTTON_COLOR_HOVER);
	}
	
	@Override
	public int getCardHeight() {
		return 60;
	}

	@Override
	protected void drawCardInfo(int mouseX, int mouseY) {
		CommonGuiUtils.drawStringWithDots(this.viewScreen.getFontRenderer(), this.textId, this.width - 60, this.x + 10, this.y + 8, 0xFFFFFF);
		CommonGuiUtils.drawStringWithDots(this.viewScreen.getFontRenderer(), this.textCount, this.width - 10, this.x + 10, this.y + 25, 0xFFFFFF);
	}

	@Override
	protected void editAddition() {
		this.viewScreen.mc.displayGuiScreen(new GuiEditFunction(this.viewScreen, I18n.format("gui.edit.editing", this.addition.id), this.addon, this.addition));
	}

	@Override
	protected void duplicateAddition() {
		GuiEditFunction editScreen = new GuiEditFunction(this.viewScreen, I18n.format("gui.edit.function.title"), this.addon, null);
		editScreen.copyFrom(this.addition);
		this.viewScreen.mc.displayGuiScreen(editScreen);		
	}

	@Override
	protected void deleteAddition() {
		AdditionTypeFunction.INSTANCE.deleteAddition(this.addon, this.addition);
	}
	
	@Override
	public boolean filterApplies(String filter) {
		return filter == null || filter.isEmpty() || this.filterId.contains(filter.toLowerCase());
	}

}
