package pixlepix.minechem.common.polytool.types;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.common.polytool.PolytoolUpgradeType;

public class PolytoolTypeSulfur extends PolytoolUpgradeType {

	public PolytoolTypeSulfur() {
		super();
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block) {

		return 0;
	}

	@Override
	public void hitEntity(ItemStack itemStack, @NotNull EntityLivingBase target,
	                      EntityLivingBase player) {
		target.setFire((int) (power + 1));
	}

	@Override
	public void onBlockDestroyed(ItemStack itemStack, World world, int id,
	                             int x, int y, int z, EntityLivingBase entityLiving) {
	}

	@Override
	public EnumElement getElement() {

		return EnumElement.S;
	}

	@Override
	public void onTick() {
	}

	@Override
	public String getDescription() {

		return "Sets entities on fire";
	}

}
