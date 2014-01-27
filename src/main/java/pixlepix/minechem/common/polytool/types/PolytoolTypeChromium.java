package pixlepix.minechem.common.polytool.types;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.common.polytool.PolytoolUpgradeType;

import java.util.Random;

public class PolytoolTypeChromium extends PolytoolUpgradeType {

	public PolytoolTypeChromium() {
		super();
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block) {

		return 0;
	}

	@Override
	public void hitEntity(ItemStack itemStack, EntityLivingBase target,
	                      EntityLivingBase player) {
	}

	@Override
	public void onBlockDestroyed(ItemStack itemStack, @NotNull World world, int id,
	                             int x, int y, int z, EntityLivingBase entityLiving) {
		Random rand = new Random();
		if (!world.isRemote && rand.nextInt(10) < power) {
			if (id == Block.cloth.blockID) {
				world.destroyBlock(x, y, z, false);

				world.spawnEntityInWorld(new EntityItem(world, x + rand.nextDouble(), y + rand.nextDouble(), z + rand.nextDouble(), new ItemStack(Block.cloth.blockID, 1, rand.nextInt(15))));
			}
		}

	}

	@Override
	public EnumElement getElement() {

		return EnumElement.Cr;
	}

	@Override
	public void onTick() {
	}

	@Override
	public String getDescription() {

		return "Chance to change color of wool when mined";
	}

}
