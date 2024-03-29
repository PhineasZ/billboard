package cc.mousse.billboard;

import cc.mousse.billboard.command.Command;
import cc.mousse.billboard.command.TabCommand;
import cc.mousse.billboard.config.ConfigLoader;
import cc.mousse.billboard.event.JoinEvent;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * @author PhineasZ
 */
public final class Application extends JavaPlugin {

  private static Application instance;

  @Override
  public void onEnable() {
    // Plugin startup logic
    setInstance(this);
    // 注册插件
    getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    // 注册命令
    val command = Bukkit.getPluginCommand("billboard");
    Objects.requireNonNull(command).setExecutor(new Command());
    // 设置命令提示
    command.setTabCompleter(new TabCommand());
    // 获取消息
    ConfigLoader.init();
    this.getLogger().info("插件已加载");
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    // 卸载插件
    Bukkit.getScheduler().cancelTasks(this);
    this.getLogger().info("插件已卸载");
  }

  public static Application getInstance() {
    return instance;
  }

  private static void setInstance(Application application) {
    instance = application;
  }
}
