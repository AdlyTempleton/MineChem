package ljdp.minechem.api.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import ljdp.minechem.api.core.Chemical;
import net.minecraft.item.ItemStack;

public class SynthesisRecipe {

   public static ArrayList<SynthesisRecipe> recipes = new ArrayList<SynthesisRecipe>();
   private ItemStack output;
   private Chemical[] shapedRecipe;
   private ArrayList unshapedRecipe;
   private int energyCost;
   private boolean isShaped;


   public static SynthesisRecipe add(SynthesisRecipe recipe) {
      recipes.add(recipe);
      return recipe;
   }

   public static void remove(ItemStack itemStack){
		ArrayList<SynthesisRecipe> recipes = SynthesisRecipe.search(itemStack);
		
		for (SynthesisRecipe recipe : recipes){
			SynthesisRecipe.recipes.remove(recipe);
		}
   }
   
   public static ArrayList<SynthesisRecipe> search(ItemStack itemStack){
	  ArrayList<SynthesisRecipe> results = new ArrayList<SynthesisRecipe>(); 
	   
	  for (SynthesisRecipe recipe : SynthesisRecipe.recipes){
		  if (itemStack.isItemEqual(recipe.output)){
			  results.add(recipe);
		  }
	  }
	  
	  return results;
	   
   }   
   
   public SynthesisRecipe(ItemStack output, boolean isShaped, int energyCost, Chemical ... shapedRecipe) {
      this.output = output;
      this.isShaped = isShaped;
      this.energyCost = energyCost;
      this.shapedRecipe = shapedRecipe;
      this.unshapedRecipe = new ArrayList();
      Chemical[] chemicalArray = shapedRecipe;

      for(int i = 0; i < shapedRecipe.length; i++) {
         Chemical chemical = chemicalArray[i];
         if(chemical != null) {
            this.unshapedRecipe.add(chemical);
         }
      }

   }

   public SynthesisRecipe(ItemStack output, boolean isShaped, int energyCost, ArrayList unshapedRecipe) {
      this.output = output;
      this.isShaped = isShaped;
      this.energyCost = energyCost;
      this.shapedRecipe = (Chemical[])unshapedRecipe.toArray(new Chemical[unshapedRecipe.size()]);
      this.unshapedRecipe = unshapedRecipe;
   }

   public ItemStack getOutput() {
      return this.output;
   }

   public boolean isShaped() {
      return this.isShaped;
   }

   public int energyCost() {
      return this.energyCost/30;
   }

   public Chemical[] getShapedRecipe() {
      return this.shapedRecipe;
   }

   public ArrayList getShapelessRecipe() {
      return this.unshapedRecipe;
   }

   public int getIngredientCount() {
      int count = 0;

      Chemical chemical;
      for(Iterator i = this.unshapedRecipe.iterator(); i.hasNext(); count += chemical.amount) {
         chemical = (Chemical)i.next();
      }

      return count;
   }

}
