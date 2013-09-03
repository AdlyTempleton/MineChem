package ljdp.minechem.common.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemGhostBlock extends ItemBlock {
    private final static String[] subNames = { "white", "orange", "magenta", "lightBlue", "yellow", "lightGreen", "pink", "darkGrey", "lightGrey", "cyan",
            "purple", "blue", "brown", "green", "red", "black" };

    public ItemGhostBlock(int id) {
        super(id);
        setUnlocalizedName("itemGhostBlock");
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
    }

}
