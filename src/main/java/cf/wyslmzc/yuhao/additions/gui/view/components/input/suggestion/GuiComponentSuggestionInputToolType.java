package cf.wyslmzc.yuhao.additions.gui.view.components.input.suggestion;

import java.util.Arrays;
import java.util.List;

import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Dropdown list specifically for tool types.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since December 2018
 */
public class GuiComponentSuggestionInputToolType extends GuiComponentSuggestionInput {
	
	private static final List<String> TOOL_TYPE_SUGGESTIONS = Arrays.asList(new String[]{"sword", "club", "pickaxe", "axe", "shovel", "hoe", "shears", "firestarter"});

	public GuiComponentSuggestionInputToolType(String label, GuiEdit editScreen) {
		super(label, editScreen);

		this.setSuggestions(TOOL_TYPE_SUGGESTIONS);
	}
}
