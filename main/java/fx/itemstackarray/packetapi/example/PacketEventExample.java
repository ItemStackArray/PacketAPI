package fx.itemstackarray.packetapi.example;

import fx.itemstackarray.packetapi.packet.PacketEvent;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
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

            //Write your code here:
        }
    }
}
