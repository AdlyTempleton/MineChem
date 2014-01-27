package pixlepix.minechem.client.gui.tabs;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.utils.MinechemHelper;

public class TabHelp extends Tab {
	public static Icon helpIcon;

	String helpString;
	int stringWidth;

	public TabHelp(Gui gui, String helpString) {
		super(gui);
		this.helpString = helpString;
		this.maxWidth = 120;
		this.stringWidth = this.maxWidth - 10;
		this.maxHeight = MinechemHelper.getSplitStringHeight(fontRenderer, helpString, this.stringWidth) + 20;
		this.overlayColor = 0x88BBBB;
	}

	@Override
	public void draw(int x, int y) {
		drawBackground(x, y);
		if (!isFullyOpened()) {
			drawIcon(x + 2, y + 3);
			return;
		}
		fontRenderer.drawSplitString(helpString, x + 6, y + 10, this.stringWidth, 0x000000);
	}

	@Nullable
	@Override
	public String getTooltip() {
		if (!isOpen())
			return "Help";
		else
			return null;
	}

	@Override
	public ResourceLocation getIcon() {
		// TODO Auto-generated method stub
		return ModMinechem.ICON_HELP;
	}

}
