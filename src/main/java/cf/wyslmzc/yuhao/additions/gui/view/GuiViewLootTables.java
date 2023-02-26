package cf.wyslmzc.yuhao.additions.gui.view;

import java.io.File;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTableAdded;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBoxThreeButton;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardLootTable;
import cf.wyslmzc.yuhao.additions.gui.view.edit.loottable.GuiEditSelectTypeLootTablePreset;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeLootTable;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the loot tables added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiViewLootTables extends GuiViewAdditionType {

	public GuiViewLootTables(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.popup.lootTable.addLootTable.title"), new TextComponentTranslation("gui.view.addon.lootTables.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<LootTableAdded> additions = AdditionTypeLootTable.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.location == null || second == null || second.location == null) {
				return 0;
			}
			return first.location.compareTo(second.location);
		});
		
		for (LootTableAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardLootTable(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiMessageBoxAddLootTable(this);
	}
	
	protected class GuiMessageBoxAddLootTable extends GuiMessageBoxThreeButton {
		
		public GuiMessageBoxAddLootTable(GuiScreen parentScreen) {
			super(parentScreen, new GuiEditSelectTypeLootTablePreset(GuiViewLootTables.this, I18n.format("gui.edit.lootTable.title"), GuiViewLootTables.this.addon), 
					I18n.format("gui.popup.lootTable.addLootTable.title"), new TextComponentTranslation("gui.popup.lootTable.addLootTable.message"), 
					I18n.format("gui.buttons.back"), I18n.format("gui.buttons.goToFolder"), I18n.format("gui.popup.lootTable.usePreset"));
		}
		
		@Override
	    protected void onSecondButtonClicked() {
			File lootTableFolder = AdditionTypeLootTable.INSTANCE.getLootTableFolder(GuiViewLootTables.this.addon);
			
			if (!lootTableFolder.isDirectory()) {
				lootTableFolder.mkdirs();
			}
			
			CommonGuiUtils.openFolder(lootTableFolder);

			this.mc.displayGuiScreen(this.parentScreen);
		}
	}

}
