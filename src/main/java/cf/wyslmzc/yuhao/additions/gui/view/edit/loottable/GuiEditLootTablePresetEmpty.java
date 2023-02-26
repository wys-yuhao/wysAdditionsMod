package cf.wyslmzc.yuhao.additions.gui.view.edit.loottable;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetEmpty;

import net.minecraft.client.gui.GuiScreen;

/**
 * Page for adding an empty loot table or editing an existing one (though there's nothing to edit =P).
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since Febuary 2019
 */
public class GuiEditLootTablePresetEmpty extends GuiEditLootTableWithPreset<LootTablePresetEmpty> {
    
	public GuiEditLootTablePresetEmpty(GuiScreen parentScreen, String title, Addon addon, LootTablePresetEmpty preset) {
		super(parentScreen, title, addon);
		
		this.isNew = preset == null;
		
		if (this.isNew) {
			this.lootTable = new LootTablePresetEmpty();
		} else {
			this.lootTable = preset;
		}
	}

	@Override
	public void initComponents() {
		super.initComponents();
		
		if (this.copyFrom != null) {
			this.copyFromOther();
		}
		
		this.components.add(this.lootTableIdInput);
	}
}
