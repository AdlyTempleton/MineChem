package pixlepix.minechem.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import pixlepix.minechem.api.core.EnumClassification;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.api.core.EnumRadioactivity;
import pixlepix.minechem.api.util.Constants;
import pixlepix.minechem.common.EnumColor;
import pixlepix.minechem.common.MinechemItems;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.RadiationInfo;
import pixlepix.minechem.common.polytool.PolytoolHelper;
import pixlepix.minechem.common.utils.ConstantValue;
import pixlepix.minechem.common.utils.MinechemHelper;

import java.util.EnumMap;
import java.util.List;

public class ItemElement extends Item //implements IStorageTank 
{

	private final static EnumElement[] elements = EnumElement.values();
	private final EnumMap<EnumClassification, Integer> classificationIndexes = new EnumMap<EnumClassification, Integer>(EnumClassification.class);
	public final Icon liquid[] = new Icon[7], gas[] = new Icon[7];
	public Icon solid;

	public ItemElement(int par1) {
		super(par1);
		setCreativeTab(ModMinechem.minechemTab);
		setUnlocalizedName("minechem.itemElement");
		setHasSubtypes(true);
		classificationIndexes.put(EnumClassification.nonmetal, 0);
		classificationIndexes.put(EnumClassification.halogen, 1);
		classificationIndexes.put(EnumClassification.inertGas, 2);
		classificationIndexes.put(EnumClassification.semimetallic, 3);
		classificationIndexes.put(EnumClassification.otherMetal, 4);
		classificationIndexes.put(EnumClassification.alkaliMetal, 5);
		classificationIndexes.put(EnumClassification.alkalineEarthMetal, 6);
		classificationIndexes.put(EnumClassification.transitionMetal, 7);
		classificationIndexes.put(EnumClassification.lanthanide, 8);
		classificationIndexes.put(EnumClassification.actinide, 9);
		classificationIndexes.put(EnumClassification.gas, 1);
		classificationIndexes.put(EnumClassification.solid, 17);
		classificationIndexes.put(EnumClassification.liquid, 33);
	}

	public static String getShortName(@NotNull ItemStack itemstack) {
		int atomicNumber = itemstack.getItemDamage();
		return elements[atomicNumber].name();
	}

	public static String getLongName(@NotNull ItemStack itemstack) {
		int atomicNumber = itemstack.getItemDamage();
		return elements[atomicNumber].descriptiveName();
	}

	public static String getClassification(@NotNull ItemStack itemstack) {
		int atomicNumber = itemstack.getItemDamage();
		return elements[atomicNumber].classification().descriptiveName();
	}

	public static String getRoomState(@NotNull ItemStack itemstack) {
		int atomicNumber = itemstack.getItemDamage();
		return elements[atomicNumber].roomState().descriptiveName();
	}

	public static EnumRadioactivity getRadioactivity(@NotNull ItemStack itemstack) {
		int atomicNumber = itemstack.getItemDamage();
		return elements[atomicNumber].radioactivity();
	}

	public static EnumElement getElement(@NotNull ItemStack itemstack) {
		return EnumElement.elements[itemstack.getItemDamage()];
	}

	public static void attackEntityWithRadiationDamage(ItemStack itemstack, int damage, @NotNull Entity entity) {
		entity.attackEntityFrom(DamageSource.generic, damage);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(@NotNull IconRegister ir) {
		itemIcon = ir.registerIcon(ConstantValue.FILLED_TESTTUBE_TEX);
		gas[0] = ir.registerIcon(ConstantValue.ELEMENT_GAS1_TEX);
		gas[1] = ir.registerIcon(ConstantValue.ELEMENT_GAS2_TEX);
		gas[2] = ir.registerIcon(ConstantValue.ELEMENT_GAS3_TEX);
		gas[3] = ir.registerIcon(ConstantValue.ELEMENT_GAS4_TEX);
		gas[4] = ir.registerIcon(ConstantValue.ELEMENT_GAS5_TEX);
		gas[5] = ir.registerIcon(ConstantValue.ELEMENT_GAS6_TEX);
		gas[6] = ir.registerIcon(ConstantValue.ELEMENT_GAS7_TEX);
		liquid[0] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID1_TEX);
		liquid[1] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID2_TEX);
		liquid[2] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID3_TEX);
		liquid[3] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID4_TEX);
		liquid[4] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID5_TEX);
		liquid[5] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID6_TEX);
		liquid[6] = ir.registerIcon(ConstantValue.ELEMENT_LIQUID7_TEX);
		solid = ir.registerIcon(ConstantValue.ELEMENT_SOLID_TEX);
	}

	@NotNull
	@Override
	public String getUnlocalizedName(@NotNull ItemStack par1ItemStack) {
		return "minechem.itemElement." + getShortName(par1ItemStack);
	}

	@Override
	public String getItemDisplayName(@NotNull ItemStack par1ItemStack) {
		return getLongName(par1ItemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(@NotNull ItemStack itemstack, EntityPlayer par2EntityPlayer, @NotNull List par3List, boolean par4) {
		par3List.add(Constants.TEXT_MODIFIER + "9" + getShortName(itemstack) + " (" + (itemstack.getItemDamage() + 1) + ")");

		String radioactivityColor;
		String timeLeft = getRadioactiveLife(itemstack);
		if (!timeLeft.equals("")) {
			timeLeft = "(" + timeLeft + ")";
		}
		EnumRadioactivity radioactivity = getRadioactivity(itemstack);
		switch (radioactivity) {
			case stable:
				radioactivityColor = Constants.TEXT_MODIFIER + "7";
				break;
			case hardlyRadioactive:
				radioactivityColor = Constants.TEXT_MODIFIER + "a";
				break;
			case slightlyRadioactive:
				radioactivityColor = Constants.TEXT_MODIFIER + "2";
				break;
			case radioactive:
				radioactivityColor = Constants.TEXT_MODIFIER + "e";
				break;
			case highlyRadioactive:
				radioactivityColor = Constants.TEXT_MODIFIER + "6";
				break;
			case extremelyRadioactive:
				radioactivityColor = Constants.TEXT_MODIFIER + "4";
				break;
			default:
				radioactivityColor = "";
				break;
		}

		String radioactiveName = MinechemHelper.getLocalString("element.property." + radioactivity.name());
		par3List.add(radioactivityColor + radioactiveName + " " + timeLeft);
		par3List.add(getClassification(itemstack));
		par3List.add(getRoomState(itemstack));

		if (PolytoolHelper.getTypeFromElement(ItemElement.getElement(itemstack), 1) != null) {
			//Polytool Detail
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
				String polytoolDesc = PolytoolHelper.getTypeFromElement(ItemElement.getElement(itemstack), 1).getDescription();
				//String localizedDesc=MinechemHelper.getLocalString("polytool.description."+polytoolDesc);

				//if(localizedDesc.equals("")){
				String localizedDesc = polytoolDesc;
				//}

				par3List.add(EnumColor.AQUA + localizedDesc);

			} else {
				par3List.add(EnumColor.DARK_GREEN + "SHIFT to show polytool information");
			}
		}

	}

	@NotNull
	private String getRadioactiveLife(@NotNull ItemStack itemstack) {
		String timeLeft = "";
		if (getRadioactivity(itemstack) != EnumRadioactivity.stable && itemstack.getTagCompound() != null) {
			NBTTagCompound tagCompound = itemstack.getTagCompound();
			long life = tagCompound.getLong("life");
			if (life < Constants.TICKS_PER_MINUTE)
				timeLeft = (life / Constants.TICKS_PER_SECOND) + "s";
			else if (life < Constants.TICKS_PER_HOUR)
				timeLeft = (life / Constants.TICKS_PER_MINUTE) + "m";
			else if (life < Constants.TICKS_PER_DAY)
				timeLeft = (life / Constants.TICKS_PER_HOUR) + "hr";
		}
		return timeLeft;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs par2CreativeTabs, @NotNull List par3List) {
		for (EnumElement element : EnumElement.values()) {
			par3List.add(new ItemStack(itemID, 1, element.ordinal()));
		}
	}

	@NotNull
	public static Object createStackOf(@NotNull EnumElement element, int amount) {
		return new ItemStack(MinechemItems.element, amount, element.ordinal());
	}

	@NotNull
	public static RadiationInfo getRadiationInfo(@NotNull ItemStack element, @NotNull World world) {
		EnumRadioactivity radioactivity = getRadioactivity(element);
		if (radioactivity == EnumRadioactivity.stable) {
			return new RadiationInfo(element, radioactivity);
		} else {
			NBTTagCompound stackTag = element.getTagCompound();
			if (stackTag == null) {
				return initiateRadioactivity(element, world);
			} else {
				int dimensionID = stackTag.getInteger("dimensionID");
				long lastUpdate = stackTag.getLong("lastUpdate");
				long life = stackTag.getLong("life");
				RadiationInfo info = new RadiationInfo(element, life, lastUpdate, dimensionID, radioactivity);
				return info;
			}
		}
	}

	@NotNull
	public static RadiationInfo initiateRadioactivity(@NotNull ItemStack element, @NotNull World world) {
		EnumRadioactivity radioactivity = getRadioactivity(element);
		int dimensionID = world.provider.dimensionId;
		long lastUpdate = world.getTotalWorldTime();
		long life = radioactivity.getLife();
		RadiationInfo info = new RadiationInfo(element, life, lastUpdate, dimensionID, radioactivity);
		setRadiationInfo(info, element);
		return info;
	}

	public static void setRadiationInfo(@NotNull RadiationInfo radiationInfo, @NotNull ItemStack element) {
		NBTTagCompound stackTag = element.getTagCompound();
		if (stackTag == null)
			stackTag = new NBTTagCompound();
		stackTag.setLong("lastUpdate", radiationInfo.lastRadiationUpdate);
		stackTag.setLong("life", radiationInfo.radiationLife);
		stackTag.setInteger("dimensionID", radiationInfo.dimensionID);
		element.setTagCompound(stackTag);
	}

	@NotNull
	public static RadiationInfo decay(@NotNull ItemStack element, @NotNull World world) {
		int atomicMass = element.getItemDamage();
		element.setItemDamage(atomicMass - 1);
		return initiateRadioactivity(element, world);
	}
	/*
	    @Override
	    public int getGas(EnumGas type, Object... data) {
	        if (type == EnumGas.HYDROGEN || type == EnumGas.OXYGEN)
	            return Constants.GAS_PER_VIAL;
	        else return 0;
	    }

	    @Override
	    public void setGas(EnumGas type, int amount, Object... data) {
	        // Can't store gas here.
	    }

	    @Override
	    public int getMaxGas(EnumGas type, Object... data) {
	        if (type == EnumGas.HYDROGEN || type == EnumGas.OXYGEN)
	            return Constants.GAS_PER_VIAL;
	        else return 0;
	    }

	    @Override
	    public int getRate() {
	        return Constants.GAS_PER_VIAL;
	    }

	    @Override
	    public int addGas(ItemStack itemstack, EnumGas type, int amount) {
	        return amount;
	    }

	    @Override
	    public int removeGas(ItemStack itemstack, EnumGas type, int amount) {
	        if (!canProvideGas(itemstack, type))
	            return 0;
	        int usedItems = Math.min(amount / Constants.GAS_PER_VIAL, itemstack.stackSize);
	        itemstack.stackSize -= usedItems;
	        if (itemstack.stackSize < 0)
	            itemstack.stackSize = 0;

	        return usedItems * Constants.GAS_PER_VIAL;
	    }

	    @Override
	    public boolean canReceiveGas(ItemStack itemstack, EnumGas type) {
	        return false;
	    }

	    @Override
	    public boolean canProvideGas(ItemStack itemstack, EnumGas type) {
	        EnumElement element = ItemElement.getElement(itemstack);
	        if (element == EnumElement.H && type == EnumGas.HYDROGEN)
	            return true;
	        if (element == EnumElement.O && type == EnumGas.OXYGEN)
	            return true;
	        return false;
	    }

	    @Override
	    public EnumGas getGasType(ItemStack itemstack) {
	        EnumElement element = ItemElement.getElement(itemstack);
	        if (element == EnumElement.H)
	            return EnumGas.HYDROGEN;

	        if (element == EnumElement.O)
	            return EnumGas.OXYGEN;

	        return null;
	    }

	    @Override
	    public void setGasType(ItemStack itemstack, EnumGas type) {
	        // Can't set gas type.
	    }
	 */

}
