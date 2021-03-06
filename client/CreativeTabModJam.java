package fuj1n.awesomeMod.client;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fuj1n.awesomeMod.ModJam;

public final class CreativeTabModJam extends CreativeTabs {
	public CreativeTabModJam(String label) {
		super(label);
	}

	CreativeTabModJam(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * the itemID for the item to be displayed on the tab
	 */
	public int getTabIconItemIndex() {
		return ModJam.awesomeOre.blockID;
	}
}
