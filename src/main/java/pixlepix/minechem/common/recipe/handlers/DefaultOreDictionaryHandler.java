package pixlepix.minechem.common.recipe.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.api.core.Chemical;
import pixlepix.minechem.api.core.EnumOre;
import pixlepix.minechem.api.recipe.DecomposerRecipe;
import pixlepix.minechem.api.recipe.SynthesisRecipe;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.recipe.OreDictionaryHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class DefaultOreDictionaryHandler implements OreDictionaryHandler {

	private enum EnumOrePrefix {
		dust, block, ingot, ore, dustSmall, nugget, dustDirty, plate, gem
	}

	private String[] supportedOres;

	@NotNull
	private Map<EnumOre, ArrayList<EnumOrePrefix>> seenOres = new HashMap<EnumOre, ArrayList<EnumOrePrefix>>();

	@NotNull
	private Map<EnumOre, ItemStack> registeredIngots = new HashMap<EnumOre, ItemStack>();

	public DefaultOreDictionaryHandler() {
		ArrayList<String> ores = new ArrayList<String>();
		for (EnumOre ore : EnumOre.values()) {
			ores.add(ore.name());
		}
		supportedOres = ores.toArray(new String[ores.size()]);
	}

	@Nullable
	public String[] parseOreName(@NotNull String oreName) {
		for (EnumOrePrefix prefix : EnumOrePrefix.values()) {
			if (oreName.startsWith(prefix.name())) {
				String remainder = oreName.substring(prefix.name().length())
						.toLowerCase();
				if (Arrays.asList(supportedOres).contains(remainder))
					return new String[]{ prefix.name(), remainder };
			}
		}

		return null;
	}

	@Override
	public boolean canHandle(@NotNull OreRegisterEvent event) {
		if (this.parseOreName(event.Name) != null)
			return true;
		return false;
	}

	@Override
	public void handle(@NotNull OreRegisterEvent event) {
		ModMinechem.logger.log(Level.INFO,
				DefaultOreDictionaryHandler.class.getSimpleName()
						+ " registered : " + event.Name);

		String[] tokens = this.parseOreName(event.Name);
		EnumOrePrefix prefix = EnumOrePrefix.valueOf(tokens[0]);
		EnumOre ore = EnumOre.valueOf(tokens[1]);

		switch (prefix) {
			case ore:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, scaleFloor(
						ore.getComposition(), 3d)));
				//Removed to prevent dupes with RC
				//SynthesisRecipe.add(new SynthesisRecipe(event.Ore, false, 1000, scaleFloor(ore.getComposition(),3d)));
				break;
			case ingot:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, ore
						.getComposition()));
				if (!haveSeen(ore, EnumOrePrefix.dust)
						&& !haveSeen(ore, EnumOrePrefix.dustSmall)) {
					SynthesisRecipe.add(new SynthesisRecipe(event.Ore, false, 1000,
							ore.getComposition()));
					registeredIngots.put(ore, event.Ore);
				}
				break;

			case nugget:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, scaleFloor(
						ore.getComposition(), 1d / 9d)));
				break;
			case dust:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, ore
						.getComposition()));
				unregisterIngot(ore);
				SynthesisRecipe.add(new SynthesisRecipe(event.Ore, false, 1000, ore
						.getComposition()));
				break;
			case dustDirty:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, ore
						.getComposition()));
				break;
			case plate:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, ore
						.getComposition()));
				break;
			case dustSmall:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, scaleFloor(
						ore.getComposition(), 0.25d)));
				unregisterIngot(ore);
				SynthesisRecipe.add(new SynthesisRecipe(event.Ore, false, 1000,
						scaleCeil(ore.getComposition(), 0.25d)));
				break;
			case gem:
				DecomposerRecipe.add(new DecomposerRecipe(event.Ore, ore
						.getComposition()));
				SynthesisRecipe.add(new SynthesisRecipe(event.Ore, false, 1000, ore
						.getComposition()));
				break;

			default:
				ModMinechem.logger.log(Level.WARNING,
						DefaultOreDictionaryHandler.class.getSimpleName()
								+ " : Invalid ore dictionary type.");
				break;
		}

		seen(ore, prefix);
	}

	private void unregisterIngot(EnumOre ore) {
		if (registeredIngots.containsKey(ore)) {
			SynthesisRecipe.remove(registeredIngots.get(ore));
			registeredIngots.remove(ore);
		}
	}

	@NotNull
	private Chemical[] scaleCeil(@NotNull Chemical[] composition, double factor) {
		ArrayList<Chemical> newComposition = new ArrayList<Chemical>();

		for (Chemical chem : composition) {
			Chemical newChem = chem.copy();
			newChem.amount = (int) Math.ceil(chem.amount * factor);
			newComposition.add(newChem);
		}

		return newComposition.toArray(new Chemical[newComposition.size()]);
	}

	@NotNull
	private Chemical[] scaleFloor(@NotNull Chemical[] composition, double factor) {
		ArrayList<Chemical> newComposition = new ArrayList<Chemical>();

		for (Chemical chem : composition) {
			Chemical newChem = chem.copy();
			newChem.amount = (int) Math.floor(chem.amount * factor);
			newComposition.add(newChem);
		}

		return newComposition.toArray(new Chemical[newComposition.size()]);
	}

	private boolean haveSeen(EnumOre ore, EnumOrePrefix prefix) {
		if (this.seenOres.containsKey(ore)
				&& this.seenOres.get(ore).contains(prefix))
			return true;
		return false;
	}

	private void seen(EnumOre ore, EnumOrePrefix prefix) {
		if (!this.seenOres.containsKey(ore))
			this.seenOres.put(ore, new ArrayList<EnumOrePrefix>());
		if (!this.seenOres.get(ore).contains(prefix))
			this.seenOres.get(ore).add(prefix);
	}
}
