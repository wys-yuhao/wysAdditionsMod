package cf.wyslmzc.yuhao.additions.gui.view;

import java.io.IOException;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.items.materials.ItemMaterialAdded;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardItemMaterial;
import cf.wyslmzc.yuhao.additions.gui.view.edit.item.material.GuiEditArmorMaterial;
import cf.wyslmzc.yuhao.additions.gui.view.edit.item.material.GuiEditToolMaterial;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeItemMaterial;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the items added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiViewItemMaterials extends GuiViewAdditionType {

	private GuiComponentAddAddition addToolTitle = new GuiComponentAddAddition(this, I18n.format("gui.edit.itemMaterial.tool.title")) {
		@Override
		public void onMouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
			int height = 20;
			if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY < this.y + height ) {
				this.viewScreen.mc.displayGuiScreen(new GuiEditToolMaterial(this.viewScreen, I18n.format("gui.edit.itemMaterial.tool.title"), this.viewScreen.addon, null));
			}
		}
	};
	
	public GuiViewItemMaterials(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.itemMaterial.armor.title"), new TextComponentTranslation("gui.view.addon.itemMaterials.none.message", addon.name));
	}
	
	@Override
	public void initComponents() {
		if (!this.addon.locked) {
			this.components.add(this.addToolTitle);
		}
		
		super.initComponents();
	}
	
	@Override
	public void addAdditions() {
		List<ItemMaterialAdded> additions = AdditionTypeItemMaterial.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.getId() == null || second == null || second.getId() == null) {
				return 0;
			}
			return first.getId().compareTo(second.getId());
		});
		
		for (ItemMaterialAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardItemMaterial(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditArmorMaterial(this, I18n.format("gui.edit.itemMaterial.armor.title"), this.addon, null);
	}

}
