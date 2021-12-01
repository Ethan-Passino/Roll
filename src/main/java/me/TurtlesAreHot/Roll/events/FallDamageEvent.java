package me.TurtlesAreHot.Roll.events;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.Ability;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.PassiveAbility;
import me.TurtlesAreHot.Roll.Roll;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamageEvent implements Listener {
    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Element chi = Element.getElement("Chi");
            Player player = (Player) e.getEntity();
            BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
            Ability roll = CoreAbility.getAbility(Roll.class);
            if (bPlayer.hasElement(chi) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if(Roll.getCd(player) == null || (System.currentTimeMillis() - Roll.getCd(player) >= roll.getCooldown())) {
                    Roll.removeCooldown(player);
                    e.setDamage(0.0D);
                    e.setCancelled(true);
                    Roll.addCooldown(player);
                    player.sendMessage(ChatColor.GOLD + "You were about to take fall damage but you rolled!");
                } else {
                    player.sendMessage(ChatColor.GOLD + "You tried to roll to prevent fall damage but you failed!");
                }
            }
        }
    }
}
