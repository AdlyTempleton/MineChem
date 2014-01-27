package pixlepix.minechem.computercraft.method;

import dan200.computer.api.IComputerAccess;
import dan200.turtle.api.ITurtleAccess;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.computercraft.ICCMethod;
import pixlepix.minechem.computercraft.IMinechemMachinePeripheral;

public class TakeJournal extends InteractMachine implements ICCMethod {

	@NotNull
	@Override
	public String getMethodName() {
		return "takeJournal";
	}

	@Override
	public Object[] call(IComputerAccess computer, ITurtleAccess turtle, Object[] arguments) throws Exception {
		IMinechemMachinePeripheral machine = getMachineInFront(turtle);
		boolean didTake = false;
		if (machine != null) {
			ItemStack takenStack = machine.takeJournal();
			if (takenStack != null) {
				if (turtle.storeItemStack(takenStack)) {
					didTake = true;
				} else {
					machine.putJournal(takenStack);
				}
			}
		}
		return new Object[]{ didTake };
	}

}
