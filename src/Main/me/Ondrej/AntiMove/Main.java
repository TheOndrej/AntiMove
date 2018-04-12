package me.Ondrej.AntiMove;

        import me.Ondrej.AntiMove.Listeners.Join;
        import me.Ondrej.AntiMove.commands.Freeze;
        import me.Ondrej.AntiMove.commands.Unfreeze;
        import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static Main pl;

    @Override
    public void onEnable(){
        getLogger().info("Plugin is enable");
        pl = this;
        onListeners();
        onCommands();
    }

    @Override
    public void onDisable(){
        getLogger().info("Plugin is disable");
    }

    private void onListeners(){
        getServer().getPluginManager().registerEvents(new Join(), this);
    }

    private void onCommands(){
        getCommand("freeze").setExecutor(new Freeze());
        getCommand("unfreeze").setExecutor(new Unfreeze());
    }
}
