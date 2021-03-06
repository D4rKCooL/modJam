package fuj1n.awesomeMod.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChair extends Entity {

	public EntityChair(World par1World, int posX, int posY, int posZ, int face) {
		super(par1World);
		this.setSize(0.1F, 0.1F);
		this.setPosition(posX + 0.5, posY - 0.5, posZ + 0.5);
		rotationYaw = face == 0 ? 180 : face == 1 ? 240 : face == 2 ? 0 : face == 3 ? 360 : 0;
	}

	public void mountAct(EntityPlayer par1EntityPlayer) {
		if (!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == par1EntityPlayer)) {
			par1EntityPlayer.mountEntity(this);
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	}

}
