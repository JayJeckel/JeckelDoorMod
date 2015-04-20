package jeckeldoormod.content;

import jeckeldoormod.content.blocks.BlockModDoor;
import jeckeldoormod.content.items.ItemModDoor;
import jeckeldoormod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class DoorBuilder
{
	public static ShapedOreRecipe recipeBasic(final ItemStack output, final Object input)
	{
		return new ShapedOreRecipe(output,
			"?? ",
			"?? ",
			"?? ",
			'?', input);
	}

	public static ShapedOreRecipe recipeColored(final ItemStack output, final Object input, final Object inputSecondary)
	{
		return new ShapedOreRecipe(output,
			"?? ",
			"??%",
			"?? ",
			'?', input, '%', inputSecondary);
	}

	public static ContentData createWood(final String name, final int meta)
	{
		ContentData content = create(name, "wood", false);
		content.addRecipe(recipeBasic(new ItemStack(content.item, 3), new ItemStack(Blocks.planks, 1, meta + 1)));
		return content;
	}

	public static ContentData createColoredMixed(final String name, final int meta)
	{
		ContentData content = create(name, "colored/mixed", true);
		final IRecipe recipe = new ShapedOreRecipe(new ItemStack(content.item, 3),
				"## ",
				"??%",
				"?? ",
				'?', "plankWood", '#', new ItemStack(Blocks.glass), '%', new ItemStack(Items.dye, 1, meta));
		content.addRecipe(recipe);
		return content;
	}

	public static ContentData createColoredSolid(final String name, final int meta)
	{
		ContentData content = create(name, "colored/solid", false);
		content.addRecipe(recipeColored(new ItemStack(content.item, 3), "plankWood", new ItemStack(Items.dye, 1, meta)));
		return content;
	}

	public static ContentData createColoredGlass(final String name, final int meta)
	{
		ContentData content = create(name, "colored/glass", true);
		content.block.setStepSound(Block.soundTypeGlass);
		content.addRecipe(recipeColored(new ItemStack(content.item, 3), new ItemStack(Blocks.glass), new ItemStack(Items.dye, 1, meta)));
		content.addRecipe(recipeBasic(new ItemStack(content.item, 3), new ItemStack(Blocks.stained_glass, 1, meta)));
		return content;
	}

	private static ContentData create(final String name, final String folder, final boolean transparency)
	{
		final BlockModDoor block = new BlockModDoor(Material.wood);
		block.hasTransparency(transparency);
		block.setHardness(3.0F);
		block.setStepSound(Block.soundTypeWood);
		block.setBlockName(name);
		block.setBlockTextureName(Refs.ModId + ":" + (folder.length() > 0 ? folder + "/" : "") + name);
		GameRegistry.registerBlock(block, block.getUnlocalizedName());

		final ItemModDoor item = new ItemModDoor(block);
		block.setDoorItem(item);
		item.setUnlocalizedName(name);
		item.setTextureName(Refs.ModId + ":" + (folder.length() > 0 ? folder + "/" : "") + name);
		GameRegistry.registerItem(item, item.getUnlocalizedName());

		return new ContentData(block, item);
	}
}
