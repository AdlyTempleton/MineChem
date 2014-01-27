package pixlepix.minechem.particlephysics.helper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.particlephysics.blocks.Emitter;

public class ParticlePhysicsTab extends CreativeTabs {
	public static ParticlePhysicsTab instance;

	public ParticlePhysicsTab() {
		super("tabParticlePhysics");

		instance = this;
	}

	@Nullable
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(BetterLoader.getBlock(Emitter.class));

	}
}
