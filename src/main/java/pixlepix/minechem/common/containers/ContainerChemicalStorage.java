package pixlepix.minechem.common.containers;

import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ContainerChemicalStorage extends ContainerChest {

	public ContainerChemicalStorage(IInventory playerInventory, IInventory blockInventory) {
		super(playerInventory, blockInventory);
	}

	@NotNull
	public List<ItemStack> getStorageInventory() {
		List<ItemStack> storageInventory = new ArrayList<ItemStack>();
		for (int slot = 0; slot < 27; slot++) {
			ItemStack stack = getSlot(slot).getStack();
			if (stack != null)
				storageInventory.add(stack);
		}
		return storageInventory;
	}

	public List<ItemStack> getPlayerInventory() {
		List<ItemStack> playerInventory = new ArrayList<ItemStack>();
		for (int slot = 27; slot < this.inventorySlots.size(); slot++) {
			ItemStack stack = getSlot(slot).getStack();
			if (stack != null)
				playerInventory.add(stack);
		}
		return playerInventory;
	}

}
