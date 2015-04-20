package jeckeldoormod.content.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BlockModTrapDoor extends BlockTrapDoor
{
	public BlockModTrapDoor(Material material)
	{
		super(material);
		this.disableStats();
	}

	public boolean hasTransparency() { return this._transparency; }
	public void hasTransparency(boolean value) { this._transparency = value; }
	private boolean _transparency;

	@SideOnly(Side.CLIENT)
	@Override public int getRenderBlockPass() { return (this.hasTransparency() ? 1 : 0); }
}
