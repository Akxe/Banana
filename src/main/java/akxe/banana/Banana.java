package akxe.banana;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import akxe.banana.DeathInfo;
import net.minecraft.util.text.TextComponentTranslation;

@Mod(modid = Banana.MODID, name = Banana.MODNAME, version = Banana.VERSION)
public class Banana
{
	@Mod.Instance(Banana.MODID)
	public static Banana instance;
	
    public static final String MODID = "banana";
	public static final String MODNAME = "Banana, beter than vanilla";
    public static final String VERSION = "0.1";
    
    public DeathInfo DeathInfo;
    
    public Banana() {
    	this.DeathInfo = new DeathInfo();
    }
    
    public static final String[] getLocalisedComment(String commentName) {
    	return (new TextComponentTranslation(commentName)).toString().split("<br>");
    }
    /*@Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new DeathInfo());
        System.out.println("DeathInfo registered");
    }*/
}
