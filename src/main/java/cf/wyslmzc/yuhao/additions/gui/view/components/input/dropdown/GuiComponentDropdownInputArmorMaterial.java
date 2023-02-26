package cf.wyslmzc.yuhao.additions.gui.view.components.input.dropdown;

import java.util.ArrayList;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.items.materials.ArmorMaterialAdded;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;
import cf.wyslmzc.yuhao.additions.gui.view.edit.item.material.GuiEditArmorMaterial;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeItemMaterial;

import net.minecraft.client.resources.I18n;

/**
 * Dropdown list specifically for item materials, which lets you create a new item material on the fly.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since November 2017
 */
public class GuiComponentDropdownInputArmorMaterial extends GuiComponentDropdownInput<ArmorMaterialAdded> {
	
	private final String addNewMaterialId = I18n.format("gui.dropdown.itemMaterial.armor.addNew");
	private Addon addon;
	
	private ArmorMaterialAdded addNewMaterial = new ArmorMaterialAdded(null) {

	    public String getId() {
	    	return addNewMaterialId;
	    }
	    
	};

	public GuiComponentDropdownInputArmorMaterial(String label, GuiEdit editScreen, Addon addon) {
		super(label, editScreen);
		this.addon = addon;

		this.refreshSelections();
	}
	
	@Override
	public String getSelectionName(ArmorMaterialAdded selected) {
		return selected.getId();
	}
	
	@Override
	public void setSelected(ArmorMaterialAdded selected) {
		if (selected != null && selected.getId().equals(addNewMaterialId)) {
			this.editScreen.mc.displayGuiScreen(new GuiEditArmorMaterial(this.editScreen, I18n.format("gui.edit.itemMaterial.armor.title"), this.addon, null));
		} else {
			super.setSelected(selected);
		}
	}
	
	public void refreshSelections() {
		List<ArmorMaterialAdded> itemMaterials = new ArrayList<>();
		itemMaterials.add(this.addNewMaterial);
		itemMaterials.addAll(AdditionTypeItemMaterial.INSTANCE.getAllArmorMaterials());
		
		this.setSelections(itemMaterials);
	}

}
