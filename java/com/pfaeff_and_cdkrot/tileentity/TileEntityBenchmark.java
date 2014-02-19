package com.pfaeff_and_cdkrot.tileentity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBenchmark extends TileEntity
{	
	/**
	 * Note: that field should be only populated on SERVER
	 */
	public String s="";
	public static final SimpleDateFormat time =  new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat date =  new SimpleDateFormat("dd.MMMMM.yyyy");
	public static final SimpleDateFormat msec = new SimpleDateFormat("S");

    public void validate()
    {
    	super.validate();
    	if (FMLCommonHandler.instance().getEffectiveSide().isServer())
    		s = s.replace("&&x", xCoord+"").replace("&&y", yCoord+"").replace("&&z", zCoord+"");
    }
	
	
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbt)
    {
    	super.readFromNBT(nbt);
    	if (FMLCommonHandler.instance().getEffectiveSide().isServer())
    		s = nbt.getString("txt");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    	if (FMLCommonHandler.instance().getEffectiveSide().isServer())
    		nbt.setString("txt", s);
    }
    
    
    public String getCurText()
    {    	
	Date d = Calendar.getInstance().getTime();
    	return s.replace("&time", time.format(d))
		.replace("&date", date.format(d))
		.replace("&msec", msec.format(d));
    }
}
