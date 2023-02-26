package cf.wyslmzc.yuhao.additions.gui.view.edit.loottable;

import cf.wyslmzc.yuhao.additions.AdditionsMod;
import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTableAdded;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePreset;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBox;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentStringInput;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeLootTable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Page for adding a new loot table preset or editing an existing one.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since January 2019
 */
public abstract class GuiEditLootTableWithPreset<T extends LootTablePreset> extends GuiEdit {
	
	protected Addon addon;
	
    protected boolean isNew;
    protected T lootTable;
    protected T copyFrom;

    protected GuiComponentStringInput lootTableIdInput;
    
	public GuiEditLootTableWithPreset(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title);
		this.addon = addon;
	}

	@Override
	public void initComponents() {
		
		this.lootTableIdInput = new GuiComponentStringInput(I18n.format("gui.edit.lootTable.id.label"), this);
		if (this.isNew) {
			this.lootTableIdInput.setRequired();
			this.lootTableIdInput.setMaxStringLength(32);
			this.lootTableIdInput.setInfo(new TextComponentTranslation("gui.edit.id.info"));
			this.lootTableIdInput.setValidator(input -> input.matches("[a-z0-9\\_]*"));
		} else {
			this.lootTableIdInput.setEnabled(false);
			this.lootTableIdInput.setMaxStringLength(1024);
			this.lootTableIdInput.setInfo(new TextComponentTranslation("gui.edit.id.noEdit.info"));
			this.lootTableIdInput.setDefaultText(this.lootTable.id.toString());
		}
	}
	
	@Override
	public void saveObject() {
		ResourceLocation location = this.isNew ? new ResourceLocation(AdditionsMod.MOD_ID, this.addon.id + "-" + this.lootTableIdInput.getText()) : new ResourceLocation(this.lootTable.id.toString());
		
		if (this.lootTableIdInput.getText().isEmpty()) {
			this.mc.displayGuiScreen(new GuiMessageBox(this, I18n.format("gui.edit.lootTable.problem.title"), new TextComponentTranslation("gui.edit.lootTable.problem.noId", location), I18n.format("gui.buttons.back")));
			return;
		}
		
		if (this.isNew && AdditionTypeLootTable.INSTANCE.doesLootTableExistForAddon(this.addon, location)) {
			this.mc.displayGuiScreen(new GuiMessageBox(this, I18n.format("gui.edit.item.problem.title"), new TextComponentTranslation("gui.edit.item.problem.duplicate", location), I18n.format("gui.buttons.back")));
			return;
		}
		
		this.lootTable.id = location;
		
		LootTableAdded lootTable;
		
		if (this.isNew) {
			lootTable = new LootTableAdded(location, this.lootTable);
		} else {
			lootTable = AdditionTypeLootTable.INSTANCE.getLootTableForLocation(this.addon, location).orElseThrow(() -> new IllegalStateException("Expected to find a loot table with location " + location));
		}
		
		AdditionTypeLootTable.INSTANCE.saveAddition(this.addon, lootTable);
		
		if (FMLCommonHandler.instance().getMinecraftServerInstance() != null && FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0) != null
				&& FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0).getLootTableManager() != null) {
			FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0).getLootTableManager().reloadLootTables();
		}
		
		if (this.parentScreen instanceof GuiView) {
			((GuiView) this.parentScreen).refreshView();
		}
		
		this.mc.displayGuiScreen(new GuiMessageBox(this.parentScreen, I18n.format("gui.edit.lootTable.success.title"), new TextComponentTranslation("gui.edit.lootTable.success.message"), I18n.format("gui.buttons.continue")));
	}
	
	public void copyFrom(T item) {
		this.copyFrom = item;
		
		if (this.initializedComponents) {
			this.copyFromOther();
		}
	}
	
	protected void copyFromOther() {
		this.copyFrom = null;
	}

}
