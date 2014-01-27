package cofh.api.transport;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import org.jetbrains.annotations.NotNull;

/**
 * This interface is implemented on Item Conduits. Use it to attempt to eject items into an entry point.
 *
 * @author Zeldo Kavira
 */
public interface IItemConduit {

	/**
	 * Insert items into the conduit. Returns the ItemStack left (null if fully routed). Will only accept items if they have an valid destination.
	 * <p/>
	 * Pass the conduit the side *opposite* the one you are ejecting from!
	 */
	@NotNull
	public ItemStack sendItems(ItemStack item, ForgeDirection from);

}
