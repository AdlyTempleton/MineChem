package pixlepix.minechem.computercraft;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import org.jetbrains.annotations.NotNull;

public class CCItems {

	public static Item chemistryUpgrade;
	private static int chemistryUpgradeID;

	public static void loadConfig(@NotNull Configuration config) {
		chemistryUpgradeID = config.getItem(Configuration.CATEGORY_ITEM, "ChemistryTurtleUpgrade", 4760).getInt();
	}

	public static void registerItems() {
		chemistryUpgrade = new ItemChemistryUpgrade(chemistryUpgradeID);
		LanguageRegistry.addName(chemistryUpgrade, "Chemistry Turtle Upgrade");
	}
}
