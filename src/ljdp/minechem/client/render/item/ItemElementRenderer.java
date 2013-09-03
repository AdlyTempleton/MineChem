package ljdp.minechem.client.render.item;

import ljdp.minechem.api.core.EnumClassification;
import ljdp.minechem.api.core.EnumElement;
import ljdp.minechem.common.items.ItemElement;
import ljdp.minechem.common.utils.MinechemHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemElementRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        if (type == ItemRenderType.INVENTORY)
            return true;
        if (type == ItemRenderType.EQUIPPED)
            return true;
        if (type == ItemRenderType.ENTITY)
            return true;
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        if (helper == ItemRendererHelper.ENTITY_BOBBING)
            return true;
        if (helper == ItemRendererHelper.ENTITY_ROTATION)
            return true;
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data) {
        ItemElement item = (ItemElement) itemstack.getItem();
        Icon testtube = itemstack.getIconIndex();
        Icon contentsTex = null;
        EnumElement element = ItemElement.getElement(itemstack);
        float duration = 1500;
        float t = (int) (Minecraft.getSystemTime() % duration);
        int frame = (int) MinechemHelper.translateValue(t, 0, duration, 0, 7);
        if (element.roomState() == EnumClassification.gas) {
            contentsTex = item.gas[frame];
        } else if (element.roomState() == EnumClassification.liquid) {
            contentsTex = item.liquid[frame];
        } else {
            contentsTex = item.solid;
        }
        if (type == ItemRenderType.INVENTORY) {
            renderItemInInventory(itemstack, element, testtube, contentsTex);
        } else if (type == ItemRenderType.EQUIPPED) {
            renderItemInEquipped(itemstack, element, testtube, contentsTex);
        } else {
            EntityItem entityItem = (EntityItem) data[1];
            if (entityItem.worldObj == null) {
                float angle = (Minecraft.getSystemTime() % 8000L) / 8000.0F * 360.0F;
                GL11.glPushMatrix();
                GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-0.2F, -0.5F, 0.0F);
                renderItemAsEntity(itemstack, element, testtube, contentsTex);
                GL11.glPopMatrix();
            } else {
                renderItemAsEntity(itemstack, element, testtube, contentsTex);
            }
        }
    }

    private void renderItemInInventory(ItemStack itemstack, EnumElement element, Icon testtube, Icon contents) {
        String shortName = ItemElement.getShortName(itemstack);
        setColorForElement(element);
        drawTexturedRectUV(0, 0, 0, 16, 16, contents);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        drawTexturedRectUV(0, 0, 0, 16, 16, testtube);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        fontRenderer.drawString(shortName, 1, 2, 0x000000);
        fontRenderer.drawString(shortName, 1, 1, 0xEEEEEE);
    }

    private void renderItemInEquipped(ItemStack itemstack, EnumElement element, Icon testtube, Icon contents) {
        /*
         * float scale = .06F; GL11.glPushMatrix(); GL11.glScalef(scale, scale, scale); GL11.glTranslatef(20.0F, 15.0F, 0.0F); GL11.glRotatef(180.0F, 0.0F,
         * 0.0F, 1.0F); GL11.glColor3f(1.0F, 1.0F, 1.0F); setColorForElement(element); drawTexturedRectUV(0, 0, 0, 16, 16, contents); GL11.glColor3f(1.0F, 1.0F,
         * 1.0F); for (float i = 0.0F; i < 1.0F; i += .1F) { drawTexturedRectUV(0, 0, i, 16, 16, testtube); } GL11.glPopMatrix();
         */
        Tessellator tessellator = Tessellator.instance;
        setColorForElement(element);
        ItemRenderer.renderItemIn2D(tessellator, contents.getMaxU(), contents.getMinV(), contents.getMinU(), contents.getMaxV(),
                contents.getOriginX(), contents.getOriginY(), 0.0625F);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemRenderer.renderItemIn2D(tessellator, testtube.getMaxU(), testtube.getMinV(), testtube.getMinU(), testtube.getMaxV(),
                testtube.getOriginX(), testtube.getOriginY(), 0.0625F);
    }

    private void renderItemAsEntity(ItemStack itemstack, EnumElement element, Icon testtube, Icon contents) {
        GL11.glPushMatrix();
        setColorForElement(element);
        drawTextureIn3D(contents);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        drawTextureIn3D(testtube);
        GL11.glPopMatrix();
    }

    private void drawTextureIn3D(Icon texture) {
        Tessellator tesselator = Tessellator.instance;
        float scale = 0.7F;
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        ItemRenderer.renderItemIn2D(tesselator, texture.getMaxU(), texture.getMinV(), texture.getMinU(), texture.getMaxV(),
                texture.getOriginX(), texture.getOriginY(), .05F);
        GL11.glPopMatrix();
    }

    private void drawTexturedRectUV(float x, float y, float z, int w, int h, Icon icon) {
        Tessellator tesselator = Tessellator.instance;
        tesselator.startDrawingQuads();
        tesselator.addVertexWithUV(x, y + h, z, icon.getMinU(), icon.getMaxV());
        tesselator.addVertexWithUV(x + w, y + h, z, icon.getMaxU(), icon.getMaxV());
        tesselator.addVertexWithUV(x + w, y, z, icon.getMaxU(), icon.getMinV());
        tesselator.addVertexWithUV(x, y, z, icon.getMinU(), icon.getMinV());
        tesselator.draw();
    }

    private void setColorForElement(EnumElement element) {
        switch (element.classification()) {
        case actinide:
            GL11.glColor3f(1.0F, 0.0F, 0.0F);
            break;
        case alkaliMetal:
            GL11.glColor3f(0.0F, 1.0F, 0.0F);
            break;
        case alkalineEarthMetal:
            GL11.glColor3f(0.0F, 0.0F, 1.0F);
            break;
        case halogen:
            GL11.glColor3f(1.0F, 1.0F, 0.0F);
            break;
        case inertGas:
            GL11.glColor3f(0.0F, 1.0F, 1.0F);
            break;
        case lanthanide:
            GL11.glColor3f(1.0F, 0.0F, 1.0F);
            break;
        case nonmetal:
            GL11.glColor3f(1.0F, 0.5F, 0.0F);
            break;
        case otherMetal:
            GL11.glColor3f(0.5F, 1.0F, 0.0F);
            break;
        case semimetallic:
            GL11.glColor3f(0.0F, 1.0F, 0.5F);
            break;
        case transitionMetal:
            GL11.glColor3f(0.0F, 0.5F, 1.0F);
            break;
        default:
            break;
        }
    }
}
