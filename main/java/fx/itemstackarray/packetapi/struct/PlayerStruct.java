package fx.itemstackarray.packetapi.struct;

import fx.itemstackarray.packetapi.PacketAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import org.bukkit.Bukkit;
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

    public final void kickPlayer(final String reason, final String information, final boolean notify) {
        final CraftPlayer craftPlayer = (CraftPlayer) this.player;

        if (notify) Bukkit.getOnlinePlayers().forEach(player1 -> {
            if (!player1.hasPermission(PacketAPI.getINSTANCE().getPermissionStruct().getPERMISSION())) return;
            player1.sendMessage(PacketAPI.getINSTANCE().getNotifyStruct().getMessage());
        });

        craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutKickDisconnect(IChatBaseComponent.ChatSerializer.a("\text:\\" + reason)));
    }

}
