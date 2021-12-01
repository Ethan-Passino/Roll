package me.TurtlesAreHot.Roll;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.ChiAbility;
import com.projectkorra.projectkorra.ability.PassiveAbility;
import com.projectkorra.projectkorra.configuration.ConfigManager;
import me.TurtlesAreHot.Roll.events.FallDamageEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Roll extends ChiAbility implements PassiveAbility, AddonAbility {
    public static final HashMap<Player, Long> cooldowns = new HashMap<>();

    public Long cooldown;

    public Roll(Player p) {
        super(p);
    }

    public void setFields() {
        this.cooldown = ConfigManager.getConfig().getLong("ExtraAbilities.TurtlesAreHot.Chi.Roll.cooldown");
    }

    public void progress() {

    }

    public boolean isSneakAbility() {
        return false;
    }

    public boolean isHarmlessAbility() {
        return true;
    }

    public long getCooldown() {
        return this.cooldown;
    }


    public String getName() {
        return "Roll";
    }

    public String getDescription() {
        return "When you are about to take fall damage you roll and take none.";
    }

    public Location getLocation() {
        return null;
    }

    public void load() {
        // Load everything
        FallDamageEvent rollListener = new FallDamageEvent();
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(rollListener, ProjectKorra.plugin);
        ConfigManager.getConfig().addDefault("ExtraAbilities.TurtlesAreHot.Chi.Roll.cooldown", 3500L);
        ConfigManager.defaultConfig.save();
        setFields();
        ProjectKorra.log.info("Enabled " + getName() + " by " + getAuthor());
    }

    public void stop() {
        ProjectKorra.log.info("Disabled " + getName() + " by " + getAuthor());
        remove();
    }

    public String getAuthor() {
        return "TurtlesAreHot";
    }

    public String getVersion() {
        return "1.0.0";
    }


    public boolean isInstantiable() {
        return false;
    }

    public boolean isProgressable() {
        return false;
    }

    public static void addCooldown(Player p) {
        cooldowns.put(p, System.currentTimeMillis());
    }

    public static void removeCooldown(Player p) {
        cooldowns.remove(p);
    }

    public static Long getCd(Player p) {
        return cooldowns.get(p);
    }
}
