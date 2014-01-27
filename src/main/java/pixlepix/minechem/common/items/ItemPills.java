package pixlepix.minechem.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.utils.ConstantValue;

public class ItemPills extends ItemFood {
	// PILLZ HERE!!!!!!!!!!
	public ItemPills(int id, int heal) {
		super(id, heal, 0.4F, false);
		setMaxDamage(0);
		setMaxStackSize(32);
		this.setUnlocalizedName("minechem.itempill");
		this.setCreativeTab(ModMinechem.minechemTab);
		this.setAlwaysEdible();
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, @NotNull EntityPlayer player) {
		if (player.canEat(true)) {
			player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 15;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(@NotNull IconRegister ir) {
		itemIcon = ir.registerIcon(ConstantValue.PILL_TEX);
	}
}
