package akxe.banana;

import java.util.*;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid = Banana.MODID, category="deathInfo")
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class DeathInfo {
	
	@LangKey("config.banana.common.name.enabled")
	@Comment("config.banana.common.decs.enabled")
	public static boolean enabled = true;
	
	@LangKey("config.banana.deathInfo.name.showGhost")
	@Comment("config.banana.deathInfo.decs.showGhost")
	public static boolean showGhost = true;
	
	@LangKey("config.banana.deathInfo.name.listItems")
	@Comment("config.banana.deathInfo.decs.listItems")
	public static boolean listItems = true;
    
	@SubscribeEvent(priority = EventPriority.HIGH)
    public static void PlayerDied(LivingDropsEvent event) {
    	Entity entity = event.getEntity();
    	FMLClientHandler.instance().getClient();
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if(entity.getUniqueID() == player.getUniqueID()){
    		if(listItems) {
    			
    		}
    		
    		if(showGhost) {
    			
    		}
    		
    		//player.getName()
    		Map<String, Integer> originalDropCounts = countItems(event.getDrops());

    		System.out.println(player.getName());
    		//System.out.println(event.getDrops());
    		System.out.println(originalDropCounts);
		}
    }

	private static Map<String, Integer> countItems(List<EntityItem> items) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		items.forEach((i) -> {
			ItemStack item = i.getItem();
			String name = item.getDisplayName();
			int count = 0;
			if(map.containsKey(name)) {
				count = map.get(name);
			}
			map.put(name, count + item.getCount());
		});
		return map;
	}
}
