package jeckeldoormod.content.blocks;

import java.util.Random;

import jeckeldoormod.core.Refs;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockModDoor extends BlockDoor
{
	public BlockModDoor(Material material)
	{
		super(material);
		this.disableStats();
	}

	public Item getDoorItem() { return this._doorItem; }
	public void setDoorItem(Item item) { this._doorItem = item; }
	private Item _doorItem;

	public boolean hasTransparency() { return this._transparency; }
	public void hasTransparency(boolean value) { this._transparency = value; }
	private boolean _transparency;

	private IIcon _blank;

	@Override public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return (meta & 8) != 0 ? null : this.getDoorItem();
	}

	@SideOnly(Side.CLIENT)
	@Override public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return this.getDoorItem();
	}

	@SideOnly(Side.CLIENT)
	@Override public int getRenderBlockPass() { return (this.hasTransparency() ? 1 : 0); }

	@SideOnly(Side.CLIENT)
	@Override public void registerBlockIcons(IIconRegister registry)
	{
		this._blank = registry.registerIcon(Refs.ModId + ":blank");
		super.registerBlockIcons(registry);
	}

	@SideOnly(Side.CLIENT)
	@Override public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (this.hasTransparency())
		{
			final int meta = world.getBlockMetadata(x, y, z);
			final boolean doorTop = (meta & 8) != 0;
			if (doorTop && side == ForgeDirection.DOWN.ordinal()) { return this._blank; }
			else if (!doorTop && side == ForgeDirection.UP.ordinal()) { return this._blank; }
		}
		return super.getIcon(world, x, y, z, side);
	}
}
