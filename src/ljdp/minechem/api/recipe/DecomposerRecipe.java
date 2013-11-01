package ljdp.minechem.api.recipe;

import java.util.ArrayList;

import ljdp.minechem.api.core.Chemical;
import net.minecraft.item.ItemStack;

public class DecomposerRecipe {
    public static ArrayList<DecomposerRecipe> recipes = new ArrayList<DecomposerRecipe>();
    
    private boolean canSynth = true;
    
    ItemStack input;
    ArrayList<Chemical> output = new ArrayList<Chemical>();

    public static DecomposerRecipe add(DecomposerRecipe recipe) {
        recipes.add(recipe);
        return recipe;
    }
    
    public DecomposerRecipe(ItemStack input, boolean canSynth, Chemical... chemicals) {
        this(input, chemicals);
        this.canSynth = canSynth;
    }

    public DecomposerRecipe(boolean canSynth, Chemical... chemicals) {
        this(chemicals);
        this.canSynth = canSynth;
    }

    @Deprecated
    public DecomposerRecipe(ItemStack input, Chemical... chemicals) {
        this(chemicals);
        this.input = input;
    }

    @Deprecated
    public DecomposerRecipe(Chemical... chemicals) {
        for (Chemical chemical : chemicals)
            this.output.add(chemical);
    }
    
    public boolean canSynthesize() {
    	return this.canSynth;
    }

    public ItemStack getInput() {
        return this.input;
    }

    public ArrayList<Chemical> getOutput() {
        return this.output;
    }

    public ArrayList<Chemical> getOutputRaw() {
        return this.output;
    }
}
