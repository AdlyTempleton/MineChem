package pixlepix.minechem.computercraft.method;

import dan200.computer.api.IComputerAccess;
import dan200.turtle.api.ITurtleAccess;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.api.util.Util;
import pixlepix.minechem.common.MinechemItems;
import pixlepix.minechem.common.items.ItemElement;
import pixlepix.minechem.computercraft.ICCMethod;

public class GetFormula implements ICCMethod {

	@NotNull
	@Override
	public String getMethodName() {
		return "getChemicalFormula";
	}

	@Nullable
	@Override
	public Object[] call(IComputerAccess computer, @NotNull ITurtleAccess turtle, Object[] arguments) throws Exception {
		Object result = null;
		int selectedSlot = turtle.getSelectedSlot();
		ItemStack selectedStack = turtle.getSlotContents(selectedSlot);
		if (selectedStack != null) {
			if (Util.isStackAMolecule(selectedStack))
				result = MinechemItems.molecule.getFormula(selectedStack);
			if (Util.isStackAnElement(selectedStack))
				result = ItemElement.getShortName(selectedStack);
		}
		return new Object[]{ result };
	}

}
