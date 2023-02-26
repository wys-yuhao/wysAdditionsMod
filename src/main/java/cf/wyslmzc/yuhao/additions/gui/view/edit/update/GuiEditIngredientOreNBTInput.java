package cf.wyslmzc.yuhao.additions.gui.view.edit.update;

import cf.wyslmzc.yuhao.additions.addon.recipes.IngredientOreNBT;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentIngredientOreNBTInput;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentItemStackInput;

import net.minecraft.client.gui.GuiScreen;

public class GuiEditIngredientOreNBTInput extends GuiEditIngredientOreNBT {
	private GuiComponentIngredientOreNBTInput parent;
	
	public GuiEditIngredientOreNBTInput(GuiScreen parentScreen, GuiComponentIngredientOreNBTInput parent) {
		super(parentScreen);
		this.parent = parent;
	}
	
	@Override
	public void initComponents() {
		super.initComponents();
		
		this.oreDictInput.setDefaultText(parent.getIngredient().getOreName());
		
		if (!this.parent.getIngredient().getStackList().isEmpty()) {
			this.parent.getIngredient().getStackList().forEach(toAdd -> {
				GuiComponentItemStackInput input = this.itemGroupInput.createBlankComponent();
				input.setDefaultItemStack(toAdd);
				this.itemGroupInput.addDefaultComponent(input);
			});
		}
	}
	
	@Override
	protected void handleIngredientSaved(IngredientOreNBT group) {
        this.parent.setIngredient(group);
    	super.handleIngredientSaved(group);
	}

}
