package pixlepix.minechem.common.polytool.types;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.common.polytool.PolytoolUpgradeType;

public class PolytoolTypeFrancium extends PolytoolUpgradeType {

	public PolytoolTypeFrancium() {
		super();
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block) {

		return 0;
	}

	@Override
	public void hitEntity(ItemStack itemStack, @NotNull EntityLivingBase target,
	                      EntityLivingBase player) {
		if (target.worldObj.isRaining()) {
			target.worldObj.createExplosion(target, target.posX, target.posY, target.posZ, power, true);
		}
	}

	@Override
	public void onBlockDestroyed(ItemStack itemStack, World world, int id,
	                             int x, int y, int z, EntityLivingBase target) {
		if (target.worldObj.isRaining()) {
			target.worldObj.createExplosion(target, target.posX, target.posY, target.posZ, power, true);
		}
	}

	@Override
	public EnumElement getElement() {

		return EnumElement.Fr;
	}

	@Override
	public void onTick() {
	}

	@Override
	public String getDescription() {

		return "Creates explosions when raining";
	}

}
