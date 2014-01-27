package pixlepix.minechem.common.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.api.core.Chemical;
import pixlepix.minechem.api.recipe.SynthesisRecipe;
import pixlepix.minechem.api.util.Util;
import pixlepix.minechem.common.utils.MinechemHelper;

import java.util.ArrayList;

public class SynthesisRecipeHandler {

	@NotNull
	public static SynthesisRecipeHandler instance = new SynthesisRecipeHandler();

	public SynthesisRecipeHandler() {

	}

	@Nullable
	public SynthesisRecipe getRecipeFromOutput(@NotNull ItemStack output) {
		for (SynthesisRecipe recipe : SynthesisRecipe.recipes) {
			if (Util.stacksAreSameKind(output, recipe.getOutput()))
				return recipe;
		}
		return null;
	}

	@Nullable
	public SynthesisRecipe getRecipeFromInput(@NotNull ItemStack[] input) {
		for (SynthesisRecipe recipe : SynthesisRecipe.recipes) {
			if (itemStacksMatchesRecipe(input, recipe))
				return recipe;
		}
		return null;
	}

	public boolean itemStacksMatchesRecipe(@NotNull ItemStack[] stacks, @NotNull SynthesisRecipe recipe) {
		return itemStacksMatchesRecipe(stacks, recipe, 1);
	}

	public boolean itemStacksMatchesRecipe(@NotNull ItemStack[] stacks, @NotNull SynthesisRecipe recipe, int factor) {
		if (recipe.isShaped())
			return itemStacksMatchesShapedRecipe(stacks, recipe, factor);
		else
			return itemStacksMatchesShapelessRecipe(stacks, recipe, factor);
	}

	private boolean itemStacksMatchesShapelessRecipe(@NotNull ItemStack[] stacks, @NotNull SynthesisRecipe recipe, int factor) {
		ArrayList<ItemStack> stacksList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> shapelessRecipe = MinechemHelper.convertChemicalsIntoItemStacks(recipe.getShapelessRecipe());
		for (ItemStack itemstack : stacks) {
			if (itemstack != null)
				stacksList.add(itemstack.copy());
		}
		for (ItemStack itemstack : stacksList) {
			int ingredientSlot = getIngredientSlotThatMatchesStack(shapelessRecipe, itemstack, 1);
			if (ingredientSlot != -1)
				shapelessRecipe.remove(ingredientSlot);
			else
				return false;
		}
		return shapelessRecipe.size() == 0;
	}

	private boolean itemStacksMatchesShapedRecipe(ItemStack[] stacks, @NotNull SynthesisRecipe recipe, int factor) {
		Chemical[] chemicals = recipe.getShapedRecipe();
		for (int i = 0; i < chemicals.length; i++) {
			if (stacks[i] == null && chemicals[i] != null)
				return false;
			if (chemicals[i] == null && stacks[i] != null)
				return false;
			if (stacks[i] == null || chemicals[i] == null)
				continue;
			if (MinechemHelper.itemStackMatchesChemical(stacks[i], chemicals[i], factor))
				continue;
			else
				return false;
		}
		return true;
	}

	private int getIngredientSlotThatMatchesStack(@NotNull ArrayList<ItemStack> ingredients, @NotNull ItemStack itemstack, int factor) {
		for (int slot = 0; slot < ingredients.size(); slot++) {
			ItemStack ingredientStack = ingredients.get(slot);
			if (ingredientStack != null && Util.stacksAreSameKind(itemstack, ingredientStack) && itemstack.stackSize == ingredientStack.stackSize)
				return slot;
		}
		return -1;
	}

	/**
	 * Clears the crafting inventory.
	 */
	public static boolean takeFromCraftingInventory(SynthesisRecipe recipe, @NotNull final IInventory inv) {
		for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
			inv.setInventorySlotContents(slot, null);
		}
		return true;
	}
}
