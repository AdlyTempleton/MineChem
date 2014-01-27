package pixlepix.minechem.common.network;

import cpw.mods.fml.common.network.Player;
import ljdp.easypacket.EasyPacketData;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.common.blueprint.MinechemBlueprint;
import pixlepix.minechem.common.tileentity.TileEntityGhostBlock;

public class PacketGhostBlock extends PacketTileEntityUpdate {

	TileEntityGhostBlock ghostBlock;

	@EasyPacketData
	int blueprintID;
	@EasyPacketData
	int ghostBlockID;

	public PacketGhostBlock(@NotNull TileEntityGhostBlock ghostBlock) {
		super(ghostBlock);
		this.ghostBlock = ghostBlock;
		this.blueprintID = ghostBlock.getBlueprint().id;
		this.ghostBlockID = ghostBlock.getBlockID();
	}

	public PacketGhostBlock() {
		super();
	}

	@Override
	public void onReceive(Player player) {
		super.onReceive(player);
		if (this.tileEntity instanceof TileEntityGhostBlock) {
			this.ghostBlock = (TileEntityGhostBlock) this.tileEntity;
			MinechemBlueprint blueprint = MinechemBlueprint.blueprints.get(this.blueprintID);
			this.ghostBlock.setBlueprint(blueprint);
			this.ghostBlock.setBlockID(this.ghostBlockID);
		}
	}
}
