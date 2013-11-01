package ljdp.minechem.common.recipe.handlers;

import java.util.Arrays;
import java.util.logging.Level;

import ljdp.minechem.api.core.Chemical;
import ljdp.minechem.api.core.EnumMolecule;
import ljdp.minechem.api.core.Molecule;
import ljdp.minechem.api.recipe.DecomposerRecipe;
import ljdp.minechem.common.ModMinechem;
import ljdp.minechem.common.recipe.OreDictionaryHandler;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

public class TConOreDictionaryHandler implements OreDictionaryHandler {

	private enum EnumOre {
	    ingotCobalt, 
		ingotManyullyn,
		ingotArdite
	}

	@Override
	public boolean canHandle(OreRegisterEvent event) {
		for (EnumOre ore : EnumOre.values()) {
	        if (ore.name().equals(event.Name)) {
	            return true;
	        }
	    }
		return false;
	}

	@Override
	public void handle(OreRegisterEvent event) {
		EnumOre TCoreD = EnumOre.valueOf(event.Name);
		switch (TCoreD) {
		default: 
	       System.err.println("[MineChem] Severe error involving MineChem's TC intergration, please report this to the github."); 
	       System.err.println("[MineChem] Mod and everything else will still load but do not expect fully working intergration"); 
	       break;
			 
		
		}
	}

}
