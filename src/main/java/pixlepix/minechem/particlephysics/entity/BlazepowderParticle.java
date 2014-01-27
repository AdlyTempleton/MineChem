package pixlepix.minechem.particlephysics.entity;

import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.particlephysics.api.BaseParticle;

public class BlazepowderParticle extends BaseParticle {

	public BlazepowderParticle(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getStartingPotential() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@NotNull
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Blazepowder";
	}

	@Override
	public void onCollideWithParticle(BaseParticle particle) {
		if (!(particle instanceof BlazepowderParticle)) {
			particle.effect = 1;
		}
	}

}
