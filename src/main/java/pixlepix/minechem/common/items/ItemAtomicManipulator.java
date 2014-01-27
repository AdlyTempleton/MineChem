package pixlepix.minechem.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.utils.ConstantValue;

public class ItemAtomicManipulator extends Item {

	public ItemAtomicManipulator(int id) {
		super(id);
		setCreativeTab(ModMinechem.minechemTab);
		setUnlocalizedName("minechem.itemAtomicManipulator");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(@NotNull IconRegister ir) {
		itemIcon = ir.registerIcon(ConstantValue.ATOMIC_MANIPULATOR_TEX);
	}

}
