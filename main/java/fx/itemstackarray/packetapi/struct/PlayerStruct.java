package fx.itemstackarray.packetapi.struct;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author ItemStackArray
 * @Date 28/04/2023
 */

public class PlayerStruct {

    private final Player player;

    public PlayerStruct(final Player player) {
        this.player = player;
    }

    public final void sendPacket(final Packet<?> packet) {
        final CraftPlayer craftPlayer = (CraftPlayer) this.player;
        craftPlayer.getHandle().playerConnection.sendPacket(packet);
    }

}
