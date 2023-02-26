package cf.wyslmzc.yuhao.additions.gui.registration;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetBlockItemDrop;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetBlockItself;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetEmpty;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetManager;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetOtherLootTable;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiLootTablePresetFactory;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;
import cf.wyslmzc.yuhao.additions.gui.view.edit.loottable.GuiEditLootTablePresetBlockItem;
import cf.wyslmzc.yuhao.additions.gui.view.edit.loottable.GuiEditLootTablePresetBlockItself;
import cf.wyslmzc.yuhao.additions.gui.view.edit.loottable.GuiEditLootTablePresetEmpty;
import cf.wyslmzc.yuhao.additions.gui.view.edit.loottable.GuiEditLootTablePresetOtherLootTable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiFactoryRegistrationLootTablePreset {
	
	public static void registerGuiFactories() {
		LootTablePresetManager.registerGuiFactory(LootTablePresetEmpty.TYPE, new IGuiLootTablePresetFactory<LootTablePresetEmpty>() {
			@Override
			public String getTitle() {
				return I18n.format("type.lootTable.preset.empty.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.lootTable.preset.empty.description");
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, LootTablePresetEmpty preset) {
				return new GuiEditLootTablePresetEmpty(parent, preset == null ? I18n.format("gui.edit.lootTable.preset.empty.title") : I18n.format("gui.edit.editing", preset.id), addon, preset);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, LootTablePresetEmpty preset) {
				GuiEditLootTablePresetEmpty editScreen = new GuiEditLootTablePresetEmpty(parent, I18n.format("gui.edit.lootTable.preset.empty.title"), addon, null);
				editScreen.copyFrom(preset);
				return editScreen;
			}
		});
		
		LootTablePresetManager.registerGuiFactory(LootTablePresetOtherLootTable.TYPE, new IGuiLootTablePresetFactory<LootTablePresetOtherLootTable>() {
			@Override
			public String getTitle() {
				return I18n.format("type.lootTable.preset.otherLootTable.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.lootTable.preset.otherLootTable.description");
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, LootTablePresetOtherLootTable preset) {
				return new GuiEditLootTablePresetOtherLootTable(parent, preset == null ? I18n.format("gui.edit.lootTable.preset.otherLootTable.title") : I18n.format("gui.edit.editing", preset.id), addon, preset);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, LootTablePresetOtherLootTable preset) {
				GuiEditLootTablePresetOtherLootTable editScreen = new GuiEditLootTablePresetOtherLootTable(parent, I18n.format("gui.edit.lootTable.preset.otherLootTable.title"), addon, null);
				editScreen.copyFrom(preset);
				return editScreen;
			}
		});
		
		LootTablePresetManager.registerGuiFactory(LootTablePresetBlockItself.TYPE, new IGuiLootTablePresetFactory<LootTablePresetBlockItself>() {
			@Override
			public String getTitle() {
				return I18n.format("type.lootTable.preset.blockItself.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.lootTable.preset.blockItself.description");
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, LootTablePresetBlockItself preset) {
				return new GuiEditLootTablePresetBlockItself(parent, preset == null ? I18n.format("gui.edit.lootTable.preset.blockItself.title") : I18n.format("gui.edit.editing", preset.id), addon, preset);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, LootTablePresetBlockItself preset) {
				GuiEditLootTablePresetBlockItself editScreen = new GuiEditLootTablePresetBlockItself(parent, I18n.format("gui.edit.lootTable.preset.blockItself.title"), addon, null);
				editScreen.copyFrom(preset);
				return editScreen;
			}
		});
		
		LootTablePresetManager.registerGuiFactory(LootTablePresetBlockItemDrop.TYPE, new IGuiLootTablePresetFactory<LootTablePresetBlockItemDrop>() {
			@Override
			public String getTitle() {
				return I18n.format("type.lootTable.preset.blockItem.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.lootTable.preset.blockItem.description");
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, LootTablePresetBlockItemDrop preset) {
				return new GuiEditLootTablePresetBlockItem(parent, preset == null ? I18n.format("gui.edit.lootTable.preset.blockItem.title") : I18n.format("gui.edit.editing", preset.id), addon, preset);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, LootTablePresetBlockItemDrop preset) {
				GuiEditLootTablePresetBlockItem editScreen = new GuiEditLootTablePresetBlockItem(parent, I18n.format("gui.edit.lootTable.preset.blockItem.title"), addon, null);
				editScreen.copyFrom(preset);
				return editScreen;
			}
		});
	}

}
