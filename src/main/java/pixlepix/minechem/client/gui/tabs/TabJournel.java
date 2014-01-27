package pixlepix.minechem.client.gui.tabs;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import pixlepix.minechem.client.gui.GuiTableOfElements;
import pixlepix.minechem.common.ModMinechem;

public class TabJournel extends Tab {
	public static Icon helpIcon;

	public TabJournel(Gui gui) {
		super(gui);

		this.currentShiftX = GuiTableOfElements.GUI_WIDTH - 411;
		this.currentShiftY = GuiTableOfElements.GUI_HEIGHT - 411;
		this.overlayColor = 0x2F7DAA;
	}

	@Override
	public void draw(int x, int y) {
		drawBackground(x, y);
		if (!isFullyOpened()) {
			drawIcon(x + 2, y + 3);
			return;
		}

	}

	@NotNull
	@Override
	public String getTooltip() {

		return "Journal";
	}

	@Override
	public ResourceLocation getIcon() {
		// TODO Auto-generated method stub
		return ModMinechem.ICON_HELP;
	}

}
