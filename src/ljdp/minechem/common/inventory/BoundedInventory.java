package ljdp.minechem.common.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BoundedInventory implements IInventory {

    private final IInventory inventory;
    private final int[] slots;

    public BoundedInventory(IInventory inv, int[] slots) {
        if (inv == null)
            throw new IllegalArgumentException("inv: must not be null");
        if (slots == null)
            throw new IllegalArgumentException("slots: must not be null");
        for (int i = 0; i < slots.length; i++) {
            if (i < 0 || i >= inv.getSizeInventory())
                throw new IllegalArgumentException("slot: out of bounds");
        }

        this.inventory = inv;
        this.slots = slots;
    }

    public ItemStack[] copyInventoryToArray() {
        ItemStack[] itemstacks = new ItemStack[getSizeInventory()];
        for (int slot = 0; slot < getSizeInventory(); slot++) {
            ItemStack itemstack = getStackInSlot(slot);
            if (itemstack != null)
                itemstacks[slot] = itemstack.copy();
            else
                itemstacks[slot] = null;
        }
        return itemstacks;
    }

    public List<ItemStack> copyInventoryToList() {
        List<ItemStack> itemstacks = new ArrayList<ItemStack>();
        for (int slot = 0; slot < getSizeInventory(); slot++) {
            if (getStackInSlot(slot) != null)
                itemstacks.add(getStackInSlot(slot).copy());
        }
        return itemstacks;
    }

    public void setInventoryStacks(ItemStack[] itemstacks) {
        for (int slot = 0; slot < itemstacks.length; slot++)
            setInventorySlotContents(slot, itemstacks[slot]);
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slots[slot]);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return inventory.decrStackSize(slots[slot], amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inventory.getStackInSlotOnClosing(slots[slot]);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory.setInventorySlotContents(slots[slot], stack);
    }

    @Override
    public String getInvName() {
        return inventory.getInvName();
    }

    @Override
    public int getInventoryStackLimit() {
        return inventory.getInventoryStackLimit();
    }

    @Override
    public void onInventoryChanged() {
        inventory.onInventoryChanged();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return inventory.isUseableByPlayer(player);
    }

    @Override
    public void openChest() {
        inventory.openChest();
    }

    @Override
    public void closeChest() {
        inventory.closeChest();
    }

    @Override
    public boolean isInvNameLocalized() {
        return inventory.isInvNameLocalized();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return inventory.isItemValidForSlot(slots[i], itemstack);
    }

}
