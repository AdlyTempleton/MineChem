package pixlepix.minechem.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.client.gui.*;
import pixlepix.minechem.common.containers.*;
import pixlepix.minechem.common.polytool.ContainerPolytool;
import pixlepix.minechem.common.polytool.GuiPolytool;
import pixlepix.minechem.common.tileentity.*;

public class GuiHandler implements IGuiHandler {

	public static final int GUI_ID_TILEENTITY = 0;
	public static final int GUI_ID_JOURNAL = 1;
	public static final int GUI_TABLE = 2;

	public static final int GUI_ID_POLYTOOL = 3;

	@Nullable
	@Override
	public Object getServerGuiElement(int ID, @NotNull EntityPlayer player, @NotNull World world, int x, int y, int z) {
		if (ID == GUI_ID_JOURNAL) {
			return getServerGuiElementForJournal(player, world);
		}
		if (ID == GUI_ID_POLYTOOL) {
			return getServerGuiElementForPolytool(player, world);
		}
		if (ID == GUI_TABLE) {
			return new CotainerTable(player.inventory);
		}
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityDecomposer)
			return new ContainerDecomposer(player.inventory, (TileEntityDecomposer) tileEntity);
		if (tileEntity instanceof TileEntityMicroscope)
			return new ContainerMicroscope(player.inventory, (TileEntityMicroscope) tileEntity);
		if (tileEntity instanceof TileEntitySynthesis)
			return new ContainerSynthesis(player.inventory, (TileEntitySynthesis) tileEntity);
		if (tileEntity instanceof TileEntityFusion) {
			return new ContainerFusion(player.inventory, (TileEntityFusion) tileEntity);
		}
		if (tileEntity instanceof TileEntityFission) {
			return new ContainerFission(player.inventory, (TileEntityFission) tileEntity);
		}

		if (tileEntity instanceof TileEntityProxy) {
			return getServerGuiElementFromProxy((TileEntityProxy) tileEntity, player);
		}

		if (tileEntity instanceof TileEntityBlueprintProjector)
			return new ContainerProjector(player.inventory, (TileEntityBlueprintProjector) tileEntity);
		if (tileEntity instanceof TileEntityChemicalStorage)
			return new ContainerChemicalStorage(player.inventory, (TileEntityChemicalStorage) tileEntity);
		return null;
	}

	@NotNull
	private Object getServerGuiElementForPolytool(@NotNull EntityPlayer player,
	                                              World world) {

		return new ContainerPolytool(player);
	}

	@Nullable
	public Object getServerGuiElementFromProxy(@NotNull TileEntityProxy proxy, @NotNull EntityPlayer player) {
		TileEntity tileEntity = proxy.getManager();
		if (tileEntity instanceof TileEntityFusion)
			return new ContainerFusion(player.inventory, (TileEntityFusion) tileEntity);

		if (tileEntity instanceof TileEntityFission)
			return new ContainerFission(player.inventory, (TileEntityFission) tileEntity);
		return null;
	}

	@NotNull
	public Object getServerGuiElementForJournal(@NotNull EntityPlayer entityPlayer, World world) {
		return new ContainerChemistJournal(entityPlayer.inventory);
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int ID, @NotNull EntityPlayer player, @NotNull World world, int x, int y, int z) {
		if (ID == GUI_ID_JOURNAL)
			return getClientGuiElementForJournal(player, world);
		if (ID == GUI_TABLE)
			return getClientGuiForJournal(player, world);

		if (ID == GUI_ID_POLYTOOL)
			return getClientGuiForPolytool(player, world);
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityDecomposer)
			return new GuiDecomposer(player.inventory, (TileEntityDecomposer) tileEntity);
		if (tileEntity instanceof TileEntityMicroscope)
			return new GuiMicroscope(player.inventory, (TileEntityMicroscope) tileEntity);
		if (tileEntity instanceof TileEntitySynthesis)
			return new GuiSynthesis(player.inventory, (TileEntitySynthesis) tileEntity);
		if (tileEntity instanceof TileEntityFusion)
			return new GuiFusion(player.inventory, (TileEntityFusion) tileEntity);
		if (tileEntity instanceof TileEntityProxy) {
			return getClientGuiElementFromProxy((TileEntityProxy) tileEntity, player);
		}
		if (tileEntity instanceof TileEntityBlueprintProjector)
			return new GuiProjector(player.inventory, (TileEntityBlueprintProjector) tileEntity);
		if (tileEntity instanceof TileEntityChemicalStorage)
			return new GuiChemicalStorage(player.inventory, (TileEntityChemicalStorage) tileEntity);
		if (tileEntity instanceof TileEntityFission) {
			return new GuiFission(player.inventory, (TileEntityFission) tileEntity);
		}
		return null;
	}

	@NotNull
	private GuiPolytool getClientGuiForPolytool(@NotNull EntityPlayer player, World world) {

		return new GuiPolytool(new ContainerPolytool(player));
	}

	@Nullable
	public Object getClientGuiElementFromProxy(@NotNull TileEntityProxy proxy, @NotNull EntityPlayer player) {
		TileEntity tileEntity = proxy.getManager();
		if (tileEntity instanceof TileEntityFusion)
			return new GuiFusion(player.inventory, (TileEntityFusion) tileEntity);

		if (tileEntity instanceof TileEntityFission)
			return new GuiFission(player.inventory, (TileEntityFission) tileEntity);
		return null;
	}

	@NotNull
	public Object getClientGuiElementForJournal(@NotNull EntityPlayer player, World world) {
		return new GuiChemistJournal(player);
	}

	@NotNull
	public Object getClientGuiForJournal(@NotNull EntityPlayer player, World world) {
		return new GuiTableOfElements(player);
	}

}
