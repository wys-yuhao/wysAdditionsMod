package cf.wyslmzc.yuhao.additions.gui.view.components;

import java.io.IOException;

import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButton;

import net.minecraft.util.ResourceLocation;

/**
 * Displays a list of every type of addition in an addon (like items, creative tabs, etc.)
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiComponentAdditionTypeButton implements IGuiViewComponent {
	
	private boolean hidden = false;
	
	private GuiAdditionTypeButton button;
	
	public GuiComponentAdditionTypeButton(GuiAdditionTypeButton button) {
		this.button = button;
	}

	@Override
	public boolean isHidden() {
		return this.hidden;
	}
	
	@Override
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	@Override
	public int getHeight(int left, int right) {
		return this.button.getButtonHeight();
	}

	@Override
	public void drawInList(int x, int y, int right, int mouseX, int mouseY) {
		this.button.drawButton(x, y, right, mouseX, mouseY);
	}

	@Override
	public void onMouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (this.button.isHovering(mouseX, mouseY)) {
			this.button.onClick();
		}
	}

	@Override
	public boolean onHandleMouseInput(int mouseX, int mouseY) throws IOException {
		return false;
	}

	@Override
	public void onKeyTyped(char keyTyped, int keyCode) throws IOException {}
	
	public boolean isAdvanced() {
		return this.button.isAdvanced();
	}

}
