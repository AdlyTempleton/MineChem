package pixlepix.minechem.common.gates;

import buildcraft.api.core.IIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import org.jetbrains.annotations.Nullable;

public class MineChemIconProvider implements IIconProvider {

	@Nullable
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int iconIndex) {

		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

	}

}
