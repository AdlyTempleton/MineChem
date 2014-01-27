package pixlepix.minechem.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.common.blueprint.BlueprintBlock;
import pixlepix.minechem.common.blueprint.MinechemBlueprint;
import pixlepix.minechem.common.network.PacketGhostBlock;
import pixlepix.minechem.common.network.PacketHandler;

public class TileEntityGhostBlock extends MinechemTileEntity {

	private MinechemBlueprint blueprint;
	private int blockID;

	public void setBlueprintAndID(@NotNull MinechemBlueprint blueprint, int blockID) {
		setBlueprint(blueprint);
		setBlockID(blockID);
		this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, blueprint.getBlockLookup().get(this.blockID).metadata, 3);
		if (worldObj != null && !worldObj.isRemote)
			sendUpdatePacket();
	}

	public void setBlueprint(MinechemBlueprint blueprint) {
		this.blueprint = blueprint;
	}

	public MinechemBlueprint getBlueprint() {
		return this.blueprint;
	}

	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public int getBlockID() {
		return this.blockID;
	}

	@NotNull
	public ItemStack getBlockAsItemStack() {
		BlueprintBlock blueprintBlock = this.blueprint.getBlockLookup().get(this.blockID);
		return new ItemStack(blueprintBlock.block, 1, blueprintBlock.metadata);
	}

	@Override
	public void sendUpdatePacket() {
		PacketGhostBlock packet = new PacketGhostBlock(this);
		int dimensionID = worldObj.provider.dimensionId;
		PacketHandler.getInstance().ghostBlockUpdateHandler.sendToAllPlayersInDimension(packet, dimensionID);
	}

	@Override
	public void writeToNBT(@NotNull NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("blueprintID", blueprint.id);
		nbtTagCompound.setInteger("blockID", blockID);
	}

	@Override
	public void readFromNBT(@NotNull NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		this.blockID = nbtTagCompound.getInteger("blockID");
		int blueprintID = nbtTagCompound.getInteger("blueprintID");
		this.blueprint = MinechemBlueprint.blueprints.get(blueprintID);
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Nullable
	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
