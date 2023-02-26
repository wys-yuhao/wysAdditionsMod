package cf.wyslmzc.yuhao.additions.gui.type.card;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTableAdded;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetManager;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiLootTablePresetFactory;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBox;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonLootTable;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeLootTable;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

/**
 * Displays info about an added loot table.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiAdditionCardLootTable extends GuiAdditionCardColored {

	private LootTableAdded addition;
	private String textId;
	private String textPresetName = "";
	
	private String filterId;
	private String filterPresetName = "";
	
	public GuiAdditionCardLootTable(GuiView viewScreen, Addon addon, LootTableAdded addition) {
		super(viewScreen, addon);
		
		this.addition = addition;
		
		this.textId = TextFormatting.GRAY + I18n.format("gui.view.additionType.id", TextFormatting.RESET + this.addition.location.toString() + TextFormatting.GRAY);
		this.filterId = this.textId.toLowerCase();
		
		if (addition.preset != null) {
			ResourceLocation type = LootTablePresetManager.getTypeFor(this.addition.preset);
			IGuiLootTablePresetFactory factory = LootTablePresetManager.getGuiFactoryFor(type);
			
			this.textPresetName = TextFormatting.GRAY + I18n.format("gui.view.additionType.preset", TextFormatting.RESET + (factory != null ? factory.getTitle() : type.toString()) + TextFormatting.GRAY);
			this.filterPresetName = (factory != null ? factory.getTitle() : type.toString()).toLowerCase();
		} else {
			this.allowEdit = false;
		}
		
		this.setColors(GuiAdditionTypeButtonLootTable.BUTTON_COLOR_DARK, GuiAdditionTypeButtonLootTable.BUTTON_COLOR_HOVER);
	}
	
	@Override
	public int getCardHeight() {
		return this.addition.preset != null ? 60 : 43;
	}

	@Override
	protected void drawCardInfo(int mouseX, int mouseY) {
		CommonGuiUtils.drawStringWithDots(this.viewScreen.getFontRenderer(), this.textId, this.width - 30, this.x + 10, this.y + 8, 0xFFFFFF);
		
		if (this.addition.preset != null) {
			CommonGuiUtils.drawStringWithDots(this.viewScreen.getFontRenderer(), this.textPresetName, this.width - 20, this.x + 10, this.y + 25, 0xFFFFFF);
		}
	}

	@Override
	protected void editAddition() {
		if (this.addition.preset != null) {
			IGuiLootTablePresetFactory editFactory = LootTablePresetManager.getGuiFactoryFor(LootTablePresetManager.getTypeFor(this.addition.preset));
			
			if (editFactory != null) {
				this.viewScreen.mc.displayGuiScreen(editFactory.getEditScreen(this.viewScreen, this.addon, this.addition.preset));
			} else {
				this.viewScreen.mc.displayGuiScreen(new GuiMessageBox(this.viewScreen, I18n.format("gui.warnDialogue.problem.title"), new TextComponentTranslation("gui.warnDialogue.noTypeGui.message", LootTablePresetManager.getTypeFor(this.addition.preset)), I18n.format("gui.buttons.close")));
			}
		}
	}

	@Override
	protected void duplicateAddition() {
		if (this.addition.preset != null) {
			IGuiLootTablePresetFactory editFactory = LootTablePresetManager.getGuiFactoryFor(LootTablePresetManager.getTypeFor(this.addition.preset));
			
			if (editFactory != null) {
				this.viewScreen.mc.displayGuiScreen(editFactory.getDuplicateScreen(this.viewScreen, this.addon, this.addition.preset));
			} else {
				this.viewScreen.mc.displayGuiScreen(new GuiMessageBox(this.viewScreen, I18n.format("gui.warnDialogue.problem.title"), new TextComponentTranslation("gui.warnDialogue.noTypeGui.message", LootTablePresetManager.getTypeFor(this.addition.preset)), I18n.format("gui.buttons.close")));
			}
		}
	}

	@Override
	protected void deleteAddition() {
		AdditionTypeLootTable.INSTANCE.deleteAddition(this.addon, this.addition);
	}
	
	@Override
	public boolean filterApplies(String filter) {
		return filter == null || filter.isEmpty() || this.filterId.contains(filter.toLowerCase()) || this.filterPresetName.contains(filter.toLowerCase());
	}
}
