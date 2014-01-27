package pixlepix.minechem.common;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.common.utils.ConstantValue;

public class CommonProxy implements ConstantValue {
	public static int CUSTOM_RENDER_ID;

	public void registerRenderers() {

	}

	@Nullable
	public World getClientWorld() {
		return null;
	}

	public void registerHooks() {
	}

	@Nullable
	public EntityPlayer findEntityPlayerByName(String name) {
		WorldServer[] servers = FMLCommonHandler.instance().getMinecraftServerInstance().worldServers;
		EntityPlayer player = null;
		for (WorldServer server : servers) {
			player = server.getPlayerEntityByName(name);
			if (player != null)
				return player;
		}
		return player;
	}

	@Nullable
	public String getCurrentLanguage() {
		return null;
	}

	public void addName(Object obj, String s) {
	}

	public void addLocalization(String s1, String string) {
	}

	@NotNull
	public String getItemDisplayName(ItemStack newStack) {
		return "";
	}
}
