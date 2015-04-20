package jeckeldoormod.content;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;

public class ContentData
{
	public ContentData(final Block block, final Item item)
	{
		this.block = block;
		this.item = item;
		this.recipes = new ArrayList<IRecipe>();
	}

	public final Block block;
	public final Item item;

	public final List<IRecipe> recipes;

	public void addRecipe(IRecipe recipe) { this.recipes.add(recipe); }

	public void registerRecipes() { for (final IRecipe recipe : this.recipes) { GameRegistry.addRecipe(recipe); } }
}
