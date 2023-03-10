package cf.wyslmzc.yuhao.additions.gui.view.edit.item;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.items.ItemAddedShovel;

import net.minecraft.client.gui.GuiScreen;

/**
 * Page for adding a new shovel or editing an existing one.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since November 2017
 */
public class GuiEditItemShovel extends GuiEditItemTool<ItemAddedShovel> {
    
	public GuiEditItemShovel(GuiScreen parentScreen, String title, Addon addon, ItemAddedShovel item) {
		super(parentScreen, title, addon);
		
		this.isNew = item == null;
		
		if (this.isNew) {
			this.item = new ItemAddedShovel();
		} else {
			this.item = item;
		}
	}

	@Override
	public void initComponents() {
		super.initComponents();
		
		if (this.copyFrom != null) {
			this.copyFromOther();
		}
		
		this.components.add(this.itemIdInput);
		this.components.add(this.itemNameInput);
		this.components.add(this.itemShinesInput);
		this.components.add(this.itemMaterialInput);
		if (!this.isNew) {
			this.components.add(this.itemTextureButton);
		}
		
		this.advancedComponents.add(this.itemTooltipInput);
		this.advancedComponents.add(this.itemOreDictInput);
		this.advancedComponents.add(this.itemIsBeaconPaymentInput);
		this.advancedComponents.add(this.itemBurnTimeInput);
		this.advancedComponents.add(this.itemContainerInput);
		this.advancedComponents.add(this.itemApplyVanillaAttributesInput);
		this.advancedComponents.add(this.itemAttributesInput);
	}
}
