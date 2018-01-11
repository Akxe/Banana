package akxe.banana;

import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InventorySorter {

    
    @SubscribeEvent
    public static void ConteinerEvent(PlayerContainerEvent event) {
    	System.out.println("Container opened!");
        String containerName = event.getContainer().toString();
        
        System.out.println(containerName);
    }
}
