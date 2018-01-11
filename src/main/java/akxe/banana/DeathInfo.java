package akxe.banana;

import java.text.SimpleDateFormat;
import java.util.*;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

enum deathMessagesOptions {
	NONE("config.banana.deathInfo.messages.options.none"),
	TIME("config.banana.deathInfo.messages.options.time"),
	LOCATION("config.banana.deathInfo.messages.options.location"),
	ALL("config.banana.deathInfo.messages.options.all");
	
	
    private final String text;

    private deathMessagesOptions(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

@Config(modid = Banana.MODID, category="deathInfo")
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class DeathInfo {
	
	@LangKey("config.banana.common.enabled.name")
	@Comment("config.banana.common.enabled.decs")
	public static boolean enabled = true;
	
	@LangKey("config.banana.deathInfo.showGhost.name")
	@Comment("config.banana.deathInfo.showGhost.decs")
	public static boolean showGhost = true;
	
	@LangKey("config.banana.deathInfo.listItems.name")
	@Comment("config.banana.deathInfo.listItems.decs")
	public static boolean listItems = true;
	
	@LangKey("config.banana.deathInfo.messages.name")
	@Comment("config.banana.deathInfo.messages.decs")
	public static deathMessagesOptions messages = deathMessagesOptions.ALL;

@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGH)
    public static void PlayerDied(LivingDropsEvent event) {
    	Entity entity = event.getEntity();
    	FMLClientHandler.instance().getClient();
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if(entity.getUniqueID().equals(player.getUniqueID())){
    		TextComponentTranslation message = null;
    		switch(messages) {
			case ALL:
				message = (new TextComponentTranslation("chat.banana.deathInfo.death.both", new SimpleDateFormat("HH:mm:ss").format(new Date()), player.getPosition()));
				break;
			case TIME:
				message = (new TextComponentTranslation("chat.banana.deathInfo.death.time", new SimpleDateFormat("HH:mm:ss").format(new Date())));
				break;
			case LOCATION:
				message = (new TextComponentTranslation("chat.banana.deathInfo.death.location", player.getPosition()));
				break;
			case NONE:
				break;
    		}

    		System.out.println(message.getFormattedText());
    		player.sendMessage(message);
    		
    		if(listItems) {
    			message = messages == deathMessagesOptions.NONE ? new TextComponentTranslation("chat.banana.deathInfo.list.noMessage") : null;
        		
        		//Map<String, Integer> originalDropCounts = countItems(event.getDrops());
        		//System.out.println(originalDropCounts);
    			
        		System.out.println("List");
    		}
    		
    		if(showGhost) {
        		System.out.println("Ghost");
    		}
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
