package fx.itemstackarray.packetapi.example;

import fx.itemstackarray.packetapi.packet.PacketEvent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PacketEventExample implements Listener {

    @EventHandler
    private void onPacketSent(final PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInCustomPayload) {
            // Set the packet to the packet from the event
            final PacketPlayInCustomPayload packetPlayInCustomPayload = (PacketPlayInCustomPayload) event.getPacket();

            //Get the packetName
            final String packetName = event.getPacketName();

            // Get the input from packetPlayInCustomPayload.a()
            final String input = packetPlayInCustomPayload.a();

            //Example:

            if (input.equals("MC|BEdit") || input.equals("MC|BSign") || input.equals("MC|BOpen")) {
                // Kick player
                new IChatBaseComponent.ChatSerializer();
                final PacketPlayOutKickDisconnect packetPlayOutKickDisconnect = new PacketPlayOutKickDisconnect(IChatBaseComponent.ChatSerializer.a("\text:\\" + "Disconnected"));
                final CraftPlayer craftPlayer = (CraftPlayer) event.getPlayer();

                craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutKickDisconnect);
            }
        }
    }
}
