package cf.wyslmzc.yuhao.additions.addon.items.blocks;

import cf.wyslmzc.yuhao.additions.addon.blocks.IBlockAdded;
import cf.wyslmzc.yuhao.additions.addon.items.IItemAdded;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public interface IItemAddedBlock extends IItemAdded {

	public void setBlock(IBlockAdded block);
	
	public Block getBlock();
	
	public default ItemBlock getAsItemBlock() {
		if (!(this instanceof ItemBlock)) {
			throw new IllegalArgumentException("An IItemAddedBlock must be an instance of ItemBlock.");
		}
		return (ItemBlock) this;
	}
	
}
