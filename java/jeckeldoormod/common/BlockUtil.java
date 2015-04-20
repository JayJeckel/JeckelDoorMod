package jeckeldoormod.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockUtil
{
	public static Block getBlock(final ItemStack stack) { return Block.getBlockById(Item.getIdFromItem(stack.getItem())); }

	public static Block getBlock(final Item item) { return Block.getBlockById(Item.getIdFromItem(item)); }

	public static Block getBlock(final ForgeDirection dir, final IBlockAccess world, final int x, final int y, final int z)
	{
		return world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
	}

	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c)
	{
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}
}
