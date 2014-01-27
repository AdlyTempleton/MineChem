package pixlepix.minechem.api.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public enum EnumMineral {
	quartz("Quartz", new Molecule(EnumMolecule.siliconDioxide)),
	berlinite("Berlinite", new Molecule(EnumMolecule.aluminiumPhosphate, 4)),;

	// Descriptive name, in en_US. Should not be used; instead, use a
	// localized string from a .properties file.
	private final String descriptiveName;
	// Localization key.
	@NotNull
	private final String localizationKey;

	private final ArrayList<Chemical> components;

	/**
	 * Returns the localized name of this mineral, or an en_US-based
	 * placeholder if no localization was found.
	 *
	 * @return Localized name of this mineral.
	 */
	public String getName() {
		String localizedName =
				LanguageRegistry.instance().getStringLocalization(
						this.localizationKey);
		if (localizedName.isEmpty()) {
			return localizationKey;
		}
		return localizedName;
	}

	public Chemical[] getComposition() {
		return this.components.toArray(new Chemical[this.components.size()]);
	}

	EnumMineral(String descriptiveName, Chemical... chemicals) {
		this.descriptiveName = descriptiveName;
		this.localizationKey = "minechem.mineral." + name();
		this.components = new ArrayList<Chemical>();
		for (Chemical chemical : chemicals) {
			this.components.add(chemical);
		}
	}
}
