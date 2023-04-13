package fx.itemstackarray.packetapi.example;

import fx.itemstackarray.packetapi.PacketAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListenerExample implements Listener {


    /**
     * @param event Uninjects the player from the PacketDecoder
     */
    @EventHandler
    private void onQuit(final PlayerQuitEvent event) {
        // Uninject the player from your decoder
        PacketAPI.getPacketInjector().unInject();
    }
}
