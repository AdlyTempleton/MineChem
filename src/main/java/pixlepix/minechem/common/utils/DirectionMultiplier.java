package pixlepix.minechem.common.utils;

import net.minecraftforge.common.ForgeDirection;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public enum DirectionMultiplier {
	NORTH(1, 1, 1),
	EAST(-1, 1, 1),
	SOUTH(-1, 1, -1),
	WEST(1, 1, -1),
	UP(1, 1, 1),
	DOWN(1, -1, 1),;

	@NotNull
	public static HashMap<ForgeDirection, DirectionMultiplier> map = new HashMap<ForgeDirection, DirectionMultiplier>() {
		private static final long serialVersionUID = 1L;

		{
			put(ForgeDirection.NORTH, NORTH);
			put(ForgeDirection.EAST, EAST);
			put(ForgeDirection.SOUTH, SOUTH);
			put(ForgeDirection.WEST, WEST);
		}
	};
	public int xMultiplier;
	public int yMultiplier;
	public int zMultiplier;

	private DirectionMultiplier(int x, int y, int z) {
		this.xMultiplier = x;
		this.yMultiplier = y;
		this.zMultiplier = z;
	}
}
