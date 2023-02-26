package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.sounds.SoundEventAdded;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardSoundEvent;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditSoundEvent;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeSoundEvent;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the functions added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since October 2018 
 */
public class GuiViewSoundEvents extends GuiViewAdditionType {

	public GuiViewSoundEvents(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.soundEvent.title"), new TextComponentTranslation("gui.view.addon.soundEvents.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<SoundEventAdded> additions = AdditionTypeSoundEvent.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.getSoundName() == null || second == null || second.getSoundName() == null) {
				return 0;
			}
			return first.getSoundName().compareTo(second.getSoundName());
		});
		
		for (SoundEventAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardSoundEvent(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditSoundEvent(this, I18n.format("gui.edit.soundEvent.title"), this.addon, null);
	}

}
