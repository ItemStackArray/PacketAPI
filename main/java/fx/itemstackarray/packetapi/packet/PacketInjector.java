package fx.itemstackarray.packetapi.packet;


import fx.itemstackarray.packetapi.util.INet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class PacketInjector implements INet {

    /**
     * Set the player
     */
    private final Player player;

    public PacketInjector(Player player) {
        this.player = player;
    }

    /**
     * Set the injector name
     */

    @Setter
    private String injector_name;

    /**
     * Set the channel
     */
    private Channel channel;

    /**
     * @apiNote Inject the player into the decoder
     */

    @Override
    public void inject() {
        final CraftPlayer craftPlayer = (CraftPlayer) this.player;
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;

        /*
         * Add the pipeline
         */

        channel.pipeline().addAfter("decoder", (injector_name != null ? injector_name : "anticrash_decoder"), new MessageToMessageDecoder<Packet<?>>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) {
                //Call the PacketEvent
                Bukkit.getPluginManager().callEvent(new PacketEvent(packet, player));
                list.add(packet);
            }
        });
    }

    @Override
    public void unInject() {
        /*
         * Check if a channel with the name of the injector exist
         */
        if (channel.pipeline().get(injector_name) != null) {
            channel.pipeline().remove(injector_name);
        }
    }
}
