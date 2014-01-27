package pixlepix.minechem.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.utils.ConstantValue;

public class PhotonicInduction extends ItemPickaxe {
	public float efficiencyOnProperMaterial;
	@NotNull
	public ItemStack repairID = new ItemStack(Item.ingotIron);

	public PhotonicInduction(int id, EnumToolMaterial enumtoolmaterial, float efficiency) {
		super(id, enumtoolmaterial);
		setUnlocalizedName("minechem.hammer");
		setCreativeTab(ModMinechem.minechemTab);
		this.efficiencyOnProperMaterial = efficiency;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(@NotNull IconRegister ir) {
		itemIcon = ir.registerIcon(ConstantValue.PHOTONIC_INDUCTION_TEX);
	}

	@Override
	public int getItemEnchantability() {
		return 4;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack1, @Nullable ItemStack stack2) {
		return (stack2 != null) && (stack2.itemID == this.repairID.itemID);
	}
}