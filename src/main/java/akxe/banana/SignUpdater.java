package akxe.banana;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SignUpdater {
    
    @SubscribeEvent
    public static void RightClickedBlock(PlayerInteractEvent.RightClickBlock event) {
        System.out.println("Some block got right clicked!" + event.getPos());
    }

}
