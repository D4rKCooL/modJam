package modJam;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="fuj1n.modJam", name=CommonProxyModJam.modName, version=CommonProxyModJam.version)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class ModJam {
	@SidedProxy(serverSide="modJam.CommonProxyModJam", clientSide="modJam.ClientProxyModJam")
	public static CommonProxyModJam proxy;
	public static Configuration config;
	
	//Config values
	//Blocks
	public static int oreAwesomeID = 1024;
	public static int[] woodChairIDs = {
		1025, 1026, 1027, 1028
	};
	public static int[] stoneChairIDs = {
		1029, 1030, 1031, 1032
	};
	//Items
	public static int ingotAwesomeID = 3240;
	public static int woodChairID = 3241;
	public static int stoneChairID = 3242;
	//End Config values
	//Blocks
	public static Block awesomeOre;
	public static Block woodChairNorth;
	public static Block woodChairEast;
	public static Block woodChairSouth;
	public static Block woodChairWest;
	public static Block stoneChairNorth;
	public static Block stoneChairEast;
	public static Block stoneChairSouth;
	public static Block stoneChairWest;
	//Items
	public static Item awesomeIngot;
	public static Item woodChair;
	public static Item stoneChair;
	//CreativeTabs
	public static CreativeTabs modJamCreativeTab;
	//Sub Names
	private static final String[] awesomeColors = { 
		"White", "Orange", "Magenta",
		"Light-Blue", "Yellow", "Lime", "Pink", "Gray", "Light-Gray", "Cyan",
		"Purple", "Blue", "Brown", "Green", "Red", "Black"
	};
	
	@PreInit
	public void PreInit(FMLPreInitializationEvent event){
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		//Blocks
		oreAwesomeID = config.getBlock("Awesome Ore ID", oreAwesomeID).getInt();
		woodChairIDs[0] = config.getBlock("Wooden Chair ID Set", woodChairIDs[0]).getInt();
		stoneChairIDs[0] = config.getBlock("Stone Chair ID Set", stoneChairIDs[0]).getInt();
		refreshChairIDs();
		//Items
		ingotAwesomeID = config.getItem("Awesome Ingot ID", ingotAwesomeID).getInt();
		woodChairID = config.getItem("Wooden Chair Item ID", woodChairID).getInt();
		stoneChairID = config.getItem("Stone Chair Item ID", stoneChairID).getInt();
		config.save();
	} 
	
	public void refreshChairIDs(){
		woodChairIDs[1] = woodChairIDs[0] + 1;
		woodChairIDs[2] = woodChairIDs[1] + 1;
		woodChairIDs[3] = woodChairIDs[2] + 1;
		stoneChairIDs[1] = stoneChairIDs[0] + 1;
		stoneChairIDs[2] = stoneChairIDs[1] + 1;
		stoneChairIDs[3] = stoneChairIDs[2] + 1;
	}
	
	@Init
	public void Init(FMLInitializationEvent event){
		proxy.handler();
		registerCreativeTab();
		initAllBlocks();
		initAllItems();
		registerAllBlocks();
		addAllNames();
		addAllCrafting();
		addAllSmelting();
		registerAllOreDictionary();
	}
	
	public void initAllBlocks(){
		awesomeOre = new BlockAwesomeOre(oreAwesomeID).setHardness(5F).setResistance(5F).setCreativeTab(modJamCreativeTab).setUnlocalizedName("fuj1n.modJam.AwesomeOre");
		woodChairNorth = new BlockChair(woodChairIDs[0], ForgeDirection.NORTH, Block.planks, woodChairID);
		woodChairEast = new BlockChair(woodChairIDs[1], ForgeDirection.EAST, Block.planks, woodChairID);
		woodChairSouth = new BlockChair(woodChairIDs[2], ForgeDirection.SOUTH, Block.planks, woodChairID);
		woodChairWest = new BlockChair(woodChairIDs[3], ForgeDirection.WEST, Block.planks, woodChairID);
		stoneChairNorth = new BlockChair(stoneChairIDs[0], ForgeDirection.NORTH, Block.stone, stoneChairID);
		stoneChairEast = new BlockChair(stoneChairIDs[1], ForgeDirection.EAST, Block.stone, stoneChairID);
		stoneChairSouth = new BlockChair(stoneChairIDs[2], ForgeDirection.SOUTH, Block.stone, stoneChairID);
		stoneChairWest = new BlockChair(stoneChairIDs[3], ForgeDirection.WEST, Block.stone, stoneChairID);
	}
	
	public void initAllItems(){
		awesomeIngot = new ItemAwesomeIngot(ingotAwesomeID).setCreativeTab(modJamCreativeTab);
		woodChair = new ItemChair(woodChairID, 0, this.woodChairIDs[0]).setCreativeTab(modJamCreativeTab);
		stoneChair = new ItemChair(stoneChairID, 1, this.stoneChairIDs[0]).setCreativeTab(modJamCreativeTab);
	}
	
	public void registerAllBlocks(){
		GameRegistry.registerBlock(awesomeOre, ItemAwesomeOre.class, "fuj1n.modJam.awesomeOre");
		GameRegistry.registerBlock(woodChairNorth, "fuj1n.modJam.woodChairNorth");
		GameRegistry.registerBlock(woodChairEast, "fuj1n.modJam.woodChairEast");
		GameRegistry.registerBlock(woodChairSouth, "fuj1n.modJam.woodChairSouth");
		GameRegistry.registerBlock(woodChairWest, "fuj1n.modJam.woodChairWest");
		GameRegistry.registerBlock(stoneChairNorth, "fuj1n.modJam.stoneChairNorth");
		GameRegistry.registerBlock(stoneChairEast, "fuj1n.modJam.stoneChairEast");
		GameRegistry.registerBlock(stoneChairSouth, "fuj1n.modJam.stoneChairSouth");
		GameRegistry.registerBlock(stoneChairWest, "fuj1n.modJam.stoneChairWest");
	}
	
	public void addAllNames(){
		for (int i = 0; i < 16; i++) {
			LanguageRegistry.addName(new ItemStack(awesomeOre, 1, i), awesomeColors[new ItemStack(awesomeOre, 1, i).getItemDamage()] + " Awesome Ore");
			LanguageRegistry.addName(new ItemStack(awesomeIngot, 1, i), awesomeColors[new ItemStack(awesomeIngot, 1, i).getItemDamage()] + " Awesome Ingot");
		}
	}
	
	public void registerCreativeTab(){
		modJamCreativeTab = new CreativeTabModJam("fuj1n.modJam");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + modJamCreativeTab.getTabLabel(), CommonProxyModJam.modName);
	}
	
	public void addAllCrafting(){}
	
	public void addAllSmelting(){
		for (int i = 0; i < 16; i++){
			FurnaceRecipes.smelting().addSmelting(oreAwesomeID, i, new ItemStack(awesomeIngot, 1, i), 0.1F);
		}
	}
	
	public void registerAllOreDictionary(){
		for (int i = 0; i < 16; i++) {
			OreDictionary.registerOre("oreAwesome" + awesomeColors[i], new ItemStack(awesomeOre, 1, i));
			OreDictionary.registerOre("ingotAwesome" + awesomeColors[i], new ItemStack(awesomeIngot, 1, i));
		}
		
	}
}
