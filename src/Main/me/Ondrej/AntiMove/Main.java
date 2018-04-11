package me.Ondrej.AntiMove;

        import me.Ondrej.AntiMove.Listeners.Join;
        import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static Main pl;

    @Override
    public void onEnable(){
        getLogger().info("Plugin is enable");
        pl = this;
        onListeners();
    }

    @Override
    public void onDisable(){
        getLogger().info("Plugin is disable");
    }

    private void onListeners(){
        getServer().getPluginManager().registerEvents(new Join(), this);
    }
}
