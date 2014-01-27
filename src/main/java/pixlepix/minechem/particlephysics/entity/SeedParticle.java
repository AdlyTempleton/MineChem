package pixlepix.minechem.particlephysics.entity;

import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.particlephysics.api.BaseParticle;

public class SeedParticle extends BaseParticle {

	public SeedParticle(World par1World) {
		super(par1World);
	}

	@Override
	public float getStartingPotential() {
		return 200;
	}

	@NotNull
	@Override
	public String getName() {
		return "Seed";
	}

	@Override
	public void onCollideWithParticle(BaseParticle particle) {
		if (!(particle instanceof SeedParticle)) {
			particle.potential = (float) Math.min(particle.potential * 1.1, particle.getStartingPotential() * 2);
			this.setDead();
		}
	}

}
