package pixlepix.minechem.computercraft.method;

import dan200.computer.api.IComputerAccess;
import dan200.turtle.api.ITurtleAccess;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.api.util.Util;
import pixlepix.minechem.common.items.ItemElement;
import pixlepix.minechem.common.items.ItemMolecule;
import pixlepix.minechem.computercraft.ICCMethod;

public class GetChemicalName implements ICCMethod {

	@NotNull
	@Override
	public String getMethodName() {
		return "getChemicalName";
	}

	@Nullable
	@Override
	public Object[] call(IComputerAccess computer, @NotNull ITurtleAccess turtle, @NotNull Object[] arguments) throws Exception {
		if (arguments.length != 0)
			throw new Exception("Invalid number of arguments");
		Object result = null;
		int selectedSlot = turtle.getSelectedSlot();
		ItemStack selectedStack = turtle.getSlotContents(selectedSlot);
		if (selectedStack != null) {
			if (Util.isStackAnElement(selectedStack)) {
				result = ItemElement.getLongName(selectedStack);
			} else if (Util.isStackAMolecule(selectedStack)) {
				result = ItemMolecule.getMolecule(selectedStack).descriptiveName();
			}
		}
		return new Object[]{ result };
	}

}
