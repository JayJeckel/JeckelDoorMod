package jeckeldoormod.content;

import java.util.ArrayList;

import jeckeldoormod.common.tabs.MappedCreativeTab;
import jeckeldoormod.core.Refs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.registry.GameRegistry;

public class ContentManager
{
	public static final String[] woods = new String[] { "spruce", "birch", "jungle", "acacia", "dark_oak" };
	public static final ContentData[] doors_wood = new ContentData[woods.length];
	public static final ContentData[] trapdoors_wood = new ContentData[woods.length];

	public static final String[] colors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
	public static final ContentData[] doors_colored_glass = new ContentData[colors.length];
	public static final ContentData[] doors_colored_mixed = new ContentData[colors.length];
	public static final ContentData[] doors_colored_solid = new ContentData[colors.length];
	public static final ContentData[] trapdoors_colored_glass = new ContentData[colors.length];
	public static final ContentData[] trapdoors_colored_mixed = new ContentData[colors.length];
	public static final ContentData[] trapdoors_colored_solid = new ContentData[colors.length];

	public void pre()
	{
		Items.wooden_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);

		MappedCreativeTab tab = new MappedCreativeTab(Refs.ModId);
		tab.setIconItemStack(new ItemStack(Items.wooden_door));

		tab.addItem(Refs.ModId, Items.wooden_door);

		for (int index = 0; index < woods.length; index++)
		{
			final String name = "door_planks_" + woods[index];
			doors_wood[index] = DoorBuilder.createWood(name, index);
			tab.addItem(Refs.ModId, doors_wood[index].item);
		}

		tab.addItem(Refs.ModId, Items.iron_door);

		for (int index = 0; index < colors.length; index++)
		{
			final String name = "door_colored_glass_" + colors[index];
			doors_colored_glass[index] = DoorBuilder.createColoredGlass(name, index);
			tab.addItem(Refs.ModId, doors_colored_glass[index].item);
		}

		for (int index = 0; index < colors.length; index++)
		{
			final String name = "door_colored_mixed_" + colors[index];
			doors_colored_mixed[index] = DoorBuilder.createColoredMixed(name, index);
			tab.addItem(Refs.ModId, doors_colored_mixed[index].item);
		}

		for (int index = 0; index < colors.length; index++)
		{
			final String name = "door_colored_solid_" + colors[index];
			doors_colored_solid[index] = DoorBuilder.createColoredSolid(name, index);
			tab.addItem(Refs.ModId, doors_colored_solid[index].item);
		}


		tab.addMisc(Refs.ModId, Blocks.trapdoor);

		for (int index = 0; index < woods.length; index++)
		{
			final String name = "trapdoor_planks_" + woods[index];
			trapdoors_wood[index] = TrapdoorBuilder.createWood(name, index);
			tab.addMisc(Refs.ModId, trapdoors_wood[index].block);
		}

		for (int index = 0; index < colors.length; index++)
		{
			final String name = "trapdoor_colored_glass_" + colors[index];
			trapdoors_colored_glass[index] = TrapdoorBuilder.createColoredGlass(name, index);
			tab.addMisc(Refs.ModId, trapdoors_colored_glass[index].block);
		}

		for (int index = 0; index < colors.length; index++)
		{
			final String name = "trapdoor_colored_mixed_" + colors[index];
			trapdoors_colored_mixed[index] = TrapdoorBuilder.createColoredMixed(name, index);
			tab.addMisc(Refs.ModId, trapdoors_colored_mixed[index].block);
		}

		for (int index = 0; index < colors.length; index++)
		{
			final String name = "trapdoor_colored_solid_" + colors[index];
			trapdoors_colored_solid[index] = TrapdoorBuilder.createColoredSolid(name, index);
			tab.addMisc(Refs.ModId, trapdoors_colored_solid[index].block);
		}
	}

	public void initialize()
	{
		removeRecipe(new ItemStack(Items.wooden_door), Refs.getLogger());
		GameRegistry.addRecipe(DoorBuilder.recipeBasic(new ItemStack(Items.wooden_door, 3), new ItemStack(Blocks.planks, 1, 0)));

		removeRecipe(new ItemStack(Blocks.trapdoor), Refs.getLogger());
		GameRegistry.addRecipe(TrapdoorBuilder.recipeBasic(new ItemStack(Blocks.trapdoor, 2), new ItemStack(Blocks.planks, 1, 0)));

		for (int index = 0; index < woods.length; index++)
		{
			doors_wood[index].registerRecipes();
			trapdoors_wood[index].registerRecipes();
		}

		for (int index = 0; index < colors.length; index++)
		{
			doors_colored_glass[index].registerRecipes();
			doors_colored_mixed[index].registerRecipes();
			doors_colored_solid[index].registerRecipes();
			trapdoors_colored_glass[index].registerRecipes();
			trapdoors_colored_mixed[index].registerRecipes();
			trapdoors_colored_solid[index].registerRecipes();
		}
	}

	public void post()
	{
	}

	public static void removeRecipe(final ItemStack stack, final Logger logger)
	{
		@SuppressWarnings("unchecked")
		final ArrayList<IRecipe> recipes = (ArrayList<IRecipe>) CraftingManager.getInstance().getRecipeList();
		for (int index = 0; index < recipes.size(); index++)
		{
			ItemStack result = ((IRecipe)recipes.get(index)).getRecipeOutput();
			if (result != null && OreDictionary.itemMatches(stack, result, false))
			{
				logger.info("Recipe Removed: " + result.getUnlocalizedName() + " -> " + recipes.get(index));
				recipes.remove(index);
			}
		}
	}
}
