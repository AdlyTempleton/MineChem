package pixlepix.minechem.common.polytool.types;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.common.EnumColor;
import pixlepix.minechem.common.polytool.PolytoolUpgradeType;

public class PolytoolTypeMercury extends PolytoolUpgradeType {

	public PolytoolTypeMercury() {
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
		int search = (int) (4 * power + 1);
		for (int i = 0; i < search; i++) {
			int found = world.getBlockId(x, y - i, z);
			if (entityLiving instanceof EntityPlayer && found == Block.lavaMoving.blockID || found == Block.lavaStill.blockID) {
				((EntityPlayer) entityLiving).addChatMessage(EnumColor.RED + "WARNING: LAVA UNDERNEATH");
				break;
			}
		}
	}

	@Override
	public EnumElement getElement() {

		return EnumElement.Hg;
	}

	@Override
	public void onTick() {
	}

	@Override
	public String getDescription() {

		return "Warns of lava underneath";
	}

}
