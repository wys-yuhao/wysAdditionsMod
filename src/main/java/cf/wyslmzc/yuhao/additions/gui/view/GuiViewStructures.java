package cf.wyslmzc.yuhao.additions.gui.view;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import cf.wyslmzc.yuhao.additions.ClientConfigLoader;
import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.structures.AddonStructureManager;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBoxSelectFile;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBoxTwoButton;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardStructure;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeStructure;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the structures added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiViewStructures extends GuiViewAdditionType {

	public GuiViewStructures(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.popup.structure.addStructure.title"), new TextComponentTranslation("gui.view.addon.structures.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<ResourceLocation> additions = AdditionTypeStructure.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort(null);
		
		for (ResourceLocation addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardStructure(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiMessageBoxAddStructure(this);
	}
	
	protected class GuiMessageBoxAddStructure extends GuiMessageBoxTwoButton {
		
		public GuiMessageBoxAddStructure(GuiScreen parentScreen) {
			super(parentScreen, parentScreen, I18n.format("gui.popup.structure.addStructure.title"), new TextComponentTranslation("gui.popup.structure.addStructure.message"), I18n.format("gui.buttons.back"), I18n.format("gui.buttons.selectFile"));
		}
		
		@Override
	    protected void onSecondButtonClicked() {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("NBT File", "nbt");
			File defaultFolder = ClientConfigLoader.getFileDialogueFolderStructures();
			File structureFolder = defaultFolder;
			
			if (Minecraft.getMinecraft().world != null) {
				structureFolder = new File("saves" + File.separator + Minecraft.getMinecraft().getIntegratedServer().getFolderName(), "structures");
			}

			JFileChooser chooser = CommonGuiUtils.createFileChooser(filter, structureFolder);
			this.mc.displayGuiScreen(new GuiMessageBoxSelectFile(this, chooser, chooserState -> handleFileChosen(chooserState, chooser, defaultFolder)));
		}
		
		protected void handleFileChosen(int chooserOption, JFileChooser chooser, File defaultFolder) {
			if (chooserOption == JFileChooser.APPROVE_OPTION) {
				File fileChosen = chooser.getSelectedFile();
				
				if (!fileChosen.getParentFile().equals(defaultFolder)) {
					ClientConfigLoader.setFileDialogueFolderStructures(fileChosen.getParentFile());
				}
				
				AdditionTypeStructure.INSTANCE.saveStructureFile(addon, fileChosen);
			}
			
			AddonStructureManager.reloadStructures();

			GuiViewStructures.this.refreshView();
			this.mc.displayGuiScreen(this.parentScreen);
		}
	}
}
