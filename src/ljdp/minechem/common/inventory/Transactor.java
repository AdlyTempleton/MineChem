package ljdp.minechem.common.inventory;

import java.util.ArrayList;
import ljdp.minechem.api.util.Util;
import ljdp.minechem.common.inventory.BoundedInventory;
import net.minecraft.item.ItemStack;

public class Transactor {

   BoundedInventory inventory;
   int maxStackSize;


   public Transactor(BoundedInventory inventory) {
      this.maxStackSize = 64;
      this.inventory = inventory;
   }

   public Transactor(BoundedInventory inventory, int maxStackSize) {
      this(inventory);
      this.maxStackSize = maxStackSize;
   }

   public int add(ItemStack stack, boolean change) {
      int itemsAdded = 0;
      int itemsLeft = stack.stackSize;

      for(int slot = 0; itemsLeft > 0 && slot < this.inventory.getSizeInventory(); ++slot) {
         int resultAdded = this.putStackInSlot(stack, itemsLeft, slot, change);
         itemsAdded += resultAdded;
         itemsLeft -= resultAdded;
      }

      return itemsAdded;
   }

   public ItemStack[] remove(int stackSize, boolean change) {
      int amount = stackSize;
      ArrayList stackList = new ArrayList();

      for(int slot = 0; amount > 0 && slot < this.inventory.getSizeInventory(); ++slot) {
         ItemStack stack = null;
         if(change) {
            stack = this.inventory.decrStackSize(slot, amount);
         } else {
        	if (this.inventory.getStackInSlot(slot) != null){
        		stack = this.inventory.getStackInSlot(slot).copy();
        		stack.stackSize = Math.min(stackSize, stack.stackSize);
        	}
         }

         if(stack != null) {
            amount -= stack.stackSize;
            stackList.add(stack);
         }
      }

      return (ItemStack[])stackList.toArray(new ItemStack[stackList.size()]);
   }

   public ItemStack removeItem(boolean change) {
      for(int slot = 0; slot < this.inventory.getSizeInventory(); slot++) {
         ItemStack stack = this.inventory.getStackInSlot(slot);
         if(stack != null) {
            if(change) {
               return this.inventory.decrStackSize(slot, 1);
            }

            ItemStack targetStack = stack.copy();
            targetStack.stackSize = 1;
            return targetStack;
         }
      }

      return null;
   }

   public int putStackInSlot(ItemStack stack, int amount, int slot, boolean change) {
      ItemStack stackInSlot = this.inventory.getStackInSlot(slot);
      if(stackInSlot == null) {
         ItemStack targetStack = stack.copy();
         targetStack.stackSize = Math.min(amount, this.getMaxStackSize(stack));
         if(change) {
            this.inventory.setInventorySlotContents(slot, targetStack);
         }

         return targetStack.stackSize;
      } else {
         return Util.stacksAreSameKind(stack, stackInSlot)?this.appendStackToSlot(amount, slot, change):0;
      }
   }

   public int appendStackToSlot(int amount, int slot, boolean changeStackSize) {
      ItemStack stackInSlot = this.inventory.getStackInSlot(slot);
      if(stackInSlot.stackSize + amount > this.getMaxStackSize(stackInSlot)) {
         int amountToFill = this.getMaxStackSize(stackInSlot) - stackInSlot.stackSize;
         if(changeStackSize) {
            stackInSlot.stackSize += amountToFill;
         }

         return amountToFill;
      } else {
         if(changeStackSize) {
            stackInSlot.stackSize += amount;
         }

         return amount;
      }
   }

   public int getMaxStackSize(ItemStack stack) {
      return Math.min(stack.getMaxStackSize(), this.maxStackSize);
   }
}
