package pixlepix.minechem.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pixlepix.minechem.common.ModMinechem;
import pixlepix.minechem.common.containers.ContainerChemicalStorage;
import pixlepix.minechem.common.containers.ContainerChemistJournal;
import pixlepix.minechem.common.containers.ContainerDecomposer;
import pixlepix.minechem.common.containers.ContainerFission;
import pixlepix.minechem.common.containers.ContainerFusion;
import pixlepix.minechem.common.containers.ContainerLeadedChest;
import pixlepix.minechem.common.containers.ContainerMicroscope;
import pixlepix.minechem.common.containers.ContainerProjector;
import pixlepix.minechem.common.containers.ContainerSynthesis;
import pixlepix.minechem.common.containers.CotainerTable;
import pixlepix.minechem.common.polytool.ContainerPolytool;
import pixlepix.minechem.common.polytool.GuiPolytool;
import pixlepix.minechem.common.tileentity.EmitterTileEntity;
import pixlepix.minechem.common.tileentity.TileEntityBlueprintProjector;
import pixlepix.minechem.common.tileentity.TileEntityChemicalStorage;
import pixlepix.minechem.common.tileentity.TileEntityDecomposer;
import pixlepix.minechem.common.tileentity.TileEntityFission;
import pixlepix.minechem.common.tileentity.TileEntityFusion;
import pixlepix.minechem.common.tileentity.TileEntityLeadedChest;
import pixlepix.minechem.common.tileentity.TileEntityMicroscope;
import pixlepix.minechem.common.tileentity.TileEntityProxy;
import pixlepix.minechem.common.tileentity.TileEntitySynthesis;

public class GuiHandler implements IGuiHandler
{

    public static final int GUI_ID_TILEENTITY = 0;
    public static final int GUI_ID_JOURNAL = 1;
    public static final int GUI_TABLE = 2;
    public static final int GUI_ID_POLYTOOL = 3;

    public GuiHandler()
    {
        NetworkRegistry.instance().registerGuiHandler(ModMinechem.INSTANCE, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GUI_ID_JOURNAL)
        {
            return getServerGuiElementForJournal(player, world);
        }

        if (ID == GUI_ID_POLYTOOL)
        {
            return getServerGuiElementForPolytool(player, world);
        }

        if (ID == GUI_TABLE)
        {
            return new CotainerTable(player.inventory);
        }

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityDecomposer)
        {
            return new ContainerDecomposer(player.inventory, (TileEntityDecomposer) tileEntity);
        }

        if (tileEntity instanceof TileEntityLeadedChest)
        {
            return new ContainerLeadedChest(player.inventory, (TileEntityLeadedChest) tileEntity);
        }

        if (tileEntity instanceof TileEntityMicroscope)
        {
            return new ContainerMicroscope(player.inventory, (TileEntityMicroscope) tileEntity);
        }

        if (tileEntity instanceof TileEntitySynthesis)
        {
            return new ContainerSynthesis(player.inventory, (TileEntitySynthesis) tileEntity);
        }

        if (tileEntity instanceof TileEntityFusion)
        {
            return new ContainerFusion(player.inventory, (TileEntityFusion) tileEntity);
        }

        if (tileEntity instanceof TileEntityFission)
        {
            return new ContainerFission(player.inventory, (TileEntityFission) tileEntity);
        }

        if (tileEntity instanceof TileEntityProxy)
        {
            return getServerGuiElementFromProxy((TileEntityProxy) tileEntity, player);
        }

        if (tileEntity instanceof TileEntityBlueprintProjector)
        {
            return new ContainerProjector(player.inventory, (TileEntityBlueprintProjector) tileEntity);
        }

        if (tileEntity instanceof TileEntityChemicalStorage)
        {
            return new ContainerChemicalStorage(player.inventory, (TileEntityChemicalStorage) tileEntity);
        }

        if (tileEntity instanceof EmitterTileEntity)
        {
            return new ContainerEmitter(player.inventory, (EmitterTileEntity) world.getBlockTileEntity(x, y, z));
        }

        return null;
    }

    public Object getServerGuiElementFromProxy(TileEntityProxy proxy, EntityPlayer player)
    {
        TileEntity tileEntity = proxy.getManager();
        if (tileEntity instanceof TileEntityFusion)
        {
            return new ContainerFusion(player.inventory, (TileEntityFusion) tileEntity);
        }

        if (tileEntity instanceof TileEntityFission)
        {
            return new ContainerFission(player.inventory, (TileEntityFission) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GUI_ID_JOURNAL)
        {
            return getClientGuiElementForJournal(player, world);
        }
        if (ID == GUI_TABLE)
        {
            return getClientGuiForJournal(player, world);
        }

        if (ID == GUI_ID_POLYTOOL)
        {
            return getClientGuiForPolytool(player, world);
        }

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity instanceof EmitterTileEntity)
        {
            return new GuiEmitter(player.inventory, (EmitterTileEntity) world.getBlockTileEntity(x, y, z));
        }
        
        if (tileEntity instanceof TileEntityDecomposer)
        {
            return new GuiDecomposer(player.inventory, (TileEntityDecomposer) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityLeadedChest)
        {
            return new GuiLeadedChest(player.inventory, (TileEntityLeadedChest) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityMicroscope)
        {
            return new GuiMicroscope(player.inventory, (TileEntityMicroscope) tileEntity);
        }
        
        if (tileEntity instanceof TileEntitySynthesis)
        {
            return new GuiSynthesis(player.inventory, (TileEntitySynthesis) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityFusion)
        {
            return new GuiFusion(player.inventory, (TileEntityFusion) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityProxy)
        {
            return getClientGuiElementFromProxy((TileEntityProxy) tileEntity, player);
        }
        
        if (tileEntity instanceof TileEntityBlueprintProjector)
        {
            return new GuiProjector(player.inventory, (TileEntityBlueprintProjector) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityChemicalStorage)
        {
            return new GuiChemicalStorage(player.inventory, (TileEntityChemicalStorage) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityFission)
        {
            return new GuiFission(player.inventory, (TileEntityFission) tileEntity);
        }
        return null;
    }

    public Object getClientGuiElementFromProxy(TileEntityProxy proxy, EntityPlayer player)
    {
        TileEntity tileEntity = proxy.getManager();
        if (tileEntity instanceof TileEntityFusion)
        {
            return new GuiFusion(player.inventory, (TileEntityFusion) tileEntity);
        }

        if (tileEntity instanceof TileEntityFission)
        {
            return new GuiFission(player.inventory, (TileEntityFission) tileEntity);
        }
        return null;
    }

    private Object getServerGuiElementForPolytool(EntityPlayer player, World world)
    {

        return new ContainerPolytool(player);
    }

    public Object getServerGuiElementForJournal(EntityPlayer entityPlayer, World world)
    {
        return new ContainerChemistJournal(entityPlayer.inventory);
    }

    private GuiPolytool getClientGuiForPolytool(EntityPlayer player, World world)
    {

        return new GuiPolytool(new ContainerPolytool(player));
    }

    public Object getClientGuiElementForJournal(EntityPlayer player, World world)
    {
        return new GuiChemistJournal(player);
    }

    public Object getClientGuiForJournal(EntityPlayer player, World world)
    {
        return new GuiTableOfElements(player);
    }
}
