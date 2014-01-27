package pixlepix.minechem.particlephysics.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.particlephysics.helper.BasicComplexBlock;
import pixlepix.minechem.particlephysics.tile.SeriesReceptorTileEntity;

import java.util.ArrayList;

public class SeriesReceptor extends BasicComplexBlock {

	public SeriesReceptor() {
		super(1180);
	}

	public SeriesReceptor(int i) {
		super(i);
	}

	@Override
	public void addStacksDroppedOnBlockBreak(TileEntity tileEntity, ArrayList<ItemStack> itemStacks) {

	}

	@NotNull
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return "SeriesReceptor";
	}

	@Override
	public boolean hasModel() {
		return true;
	}

	@NotNull
	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return "SeriesReceptorTop";
	}

	@NotNull
	@Override
	public Class getTileEntityClass() {
		return SeriesReceptorTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ItemStack(this), "III", "D  ", "III", 'I', new ItemStack(Item.ingotIron), 'D', new ItemStack(Item.diamond));

	}

	@NotNull
	@Override
	public String getName() {
		return "Series Receptor";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Nullable
	@Override
	public Class getItemBlock() {
		return null;

	}

	@Override
	public boolean topSidedTextures() {
		return true;
	}

}
