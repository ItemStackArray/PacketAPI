package fx.itemstackarray.packetapi.example;

import fx.itemstackarray.packetapi.PacketAPI;
import fx.itemstackarray.packetapi.packet.PacketInjector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListenerExample implements Listener {


    /**
     * @param event Inject the player into the PacketInjector
     */

    @EventHandler
    private void onJoin(final PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        PacketAPI.setPacketInjector(new PacketInjector(player));

        //Inject the player
        PacketAPI.getPacketInjector().inject();
    }
}
