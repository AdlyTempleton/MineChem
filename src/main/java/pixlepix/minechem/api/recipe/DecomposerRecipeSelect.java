package pixlepix.minechem.api.recipe;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.api.core.Chemical;

import java.util.ArrayList;

public class DecomposerRecipeSelect extends DecomposerRecipeChance {

	ArrayList<DecomposerRecipe> possibleRecipes = new ArrayList<DecomposerRecipe>();

	public DecomposerRecipeSelect(ItemStack input, float chance, DecomposerRecipe... possibleRecipes) {
		super(input, chance);
		for (DecomposerRecipe recipe : possibleRecipes)
			this.possibleRecipes.add(recipe);
	}

	@Nullable
	@Override
	public ArrayList<Chemical> getOutput() {
		if (random.nextFloat() < this.chance) {
			DecomposerRecipe selectedRecipe = possibleRecipes.get(random.nextInt(possibleRecipes.size()));
			return selectedRecipe.getOutput();
		}
		return null;
	}

	@Override
	public ArrayList<Chemical> getOutputRaw() {
		return possibleRecipes.get(0).getOutputRaw();
	}

	public ArrayList<DecomposerRecipe> getAllPossibleRecipes() {
		return this.possibleRecipes;
	}

}
