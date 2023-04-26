package fx.itemstackarray.packetapi.example;

import fx.itemstackarray.packetapi.packet.PacketEvent;
import fx.itemstackarray.packetapi.packet.PacketLogger;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PacketEventExample implements Listener {

    @EventHandler
    private final void onPacketSent(final PacketEvent event) throws Exception {

        final PacketLogger packetLogger = new PacketLogger(event.getPacket(), event.getPlayer());

        if (event.getPacket() instanceof PacketPlayInCustomPayload) {
            // Set the packet to the packet from the event
            final PacketPlayInCustomPayload packetPlayInCustomPayload = (PacketPlayInCustomPayload) event.getPacket();

            //Get the packetName
            final String packetName = event.getPacketName();

            // Get the input from packetPlayInCustomPayload.a()
            final String input = packetPlayInCustomPayload.a();

            //Example:

            if (input.equals("MC|BEdit") || input.equals("MC|BSign") || input.equals("MC|BOpen")) {
                // Log the packet
                packetLogger.logPacket();

                // Kick player
                new IChatBaseComponent.ChatSerializer();
                final PacketPlayOutKickDisconnect packetPlayOutKickDisconnect = new PacketPlayOutKickDisconnect(IChatBaseComponent.ChatSerializer.a("\text:\\" + "Disconnected"));
                final CraftPlayer craftPlayer = (CraftPlayer) event.getPlayer();

                craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutKickDisconnect);
            }

            // Notify Player
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.hasPermission("your_permission_here")) {
                    player.sendMessage("The Player " + player.getName() + " has sent a Packet with an Invalid input (Packet: " + packetName + ")" + " (Input: " + input + ")");
                }
            });
        }
    }
}
