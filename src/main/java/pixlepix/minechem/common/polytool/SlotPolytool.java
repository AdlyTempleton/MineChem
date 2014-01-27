package pixlepix.minechem.common.polytool;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.common.items.ItemElement;

public class SlotPolytool extends Slot {

	public SlotPolytool(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);

	}

	@Override
	public boolean isItemValid(@Nullable ItemStack itemstack) {

		return itemstack == null || (itemstack.stackSize == 64 && (itemstack.getItem() instanceof ItemElement) && PolytoolHelper.getTypeFromElement(ItemElement.getElement(itemstack), 1) != null);

	}

}
