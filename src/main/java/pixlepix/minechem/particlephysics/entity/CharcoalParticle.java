package pixlepix.minechem.particlephysics.entity;

import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.particlephysics.api.BaseParticle;

public class CharcoalParticle extends BaseParticle {

	public CharcoalParticle(World par1World) {
		super(par1World);
	}

	@Override
	public float getStartingPotential() {
		// TODO Auto-generated method stub
		return 3000;
	}

	@NotNull
	@Override
	public String getName() {
		return "Charcoal";
	}

	@Override
	public void onCollideWithParticle(BaseParticle particle) {
		if (!(particle instanceof PaperParticle || particle instanceof CharcoalParticle)) {
			particle.setDead();
			this.potential += (0.5 * particle.potential);
		}
	}

}
