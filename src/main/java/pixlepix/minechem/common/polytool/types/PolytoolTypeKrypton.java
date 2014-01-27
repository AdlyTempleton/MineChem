package pixlepix.minechem.common.polytool.types;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.common.polytool.PolytoolUpgradeType;

public class PolytoolTypeKrypton extends PolytoolUpgradeType {

	public PolytoolTypeKrypton() {
		super();
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block) {

		return 0;
	}

	@Override
	public void hitEntity(ItemStack itemStack, EntityLivingBase target,
	                      EntityLivingBase player) {
		if (target instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer) target;
			if (entityPlayer.getTotalArmorValue() > 23) {
				if (entityPlayer.attackEntityFrom(DamageSource.outOfWorld, power)) ;
			}
		}
	}

	@Override
	public void onBlockDestroyed(ItemStack itemStack, World world, int id,
	                             int x, int y, int z, EntityLivingBase entityLiving) {
	}

	@NotNull
	@Override
	public EnumElement getElement() {

		return EnumElement.Kr;
	}

	@Override
	public void onTick() {
	}

	@Override
	public String getDescription() {

		return "Does extra damage to heavily armored players";
	}

}
