package fx.itemstackarray.packetapi.packet;

import fx.itemstackarray.packetapi.util.INet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketInjector extends MessageToMessageDecoder<Packet<?>> implements INet {

    /**
     * Set the player
     */
    private final Player player;
    @Getter
    private final Map<String, PacketInjector> injection = new HashMap<>();

    public PacketInjector(Player player) {
        this.player = player;
    }

    /**
     * Set the injector name
     */

    @Setter
    private String decoder_name;
    @Setter
    private String splitter_name;
    @Setter
    private String decompress_name;

    /**
     * Set the channel
     */
    private Channel channel;

    /**
     * @apiNote Inject the player into the decoder
     */

    @Override
    public final void inject() {
        final CraftPlayer craftPlayer = (CraftPlayer) this.player;

        /* Set the channel to craftplayer#gethandle#playerconnection#a */
        this.channel = craftPlayer.getHandle().playerConnection.a().channel;

        // Put the player in the injection hashmap
        this.getInjection().put(this.player.getName(), this);


        /*
         * Add the pipeline
         */

        if (this.channel.pipeline().get("decoder") != null && this.channel.pipeline().get("splitter") != null && this.channel.pipeline().get("decompress") != null) {
            this.channel.pipeline().addAfter("decoder", decoder_name, this);
            this.channel.pipeline().addAfter("splitter", splitter_name, this);
            this.channel.pipeline().addAfter("decompress", decompress_name, this);
        }
        this.channel.pipeline().addBefore("decoder", decoder_name, this);
        this.channel.pipeline().addBefore("splitter", splitter_name, this);
        this.channel.pipeline().addBefore("decompress", decompress_name, this);
    }


    /**
     * Uninject the player from the pipelines ("> elysium_decoder, elysium_splitter, elysium_decompress")
     */
    @Override
    public final void unInject() {
        final CraftPlayer craftPlayer = (CraftPlayer) this.player;
        this.channel = craftPlayer.getHandle().playerConnection.a().channel;

        if (this.channel.pipeline().get("elysium_decoder") != null && this.channel.pipeline().get("elysium_splitter") != null && this.channel.pipeline().get("elysium_decompress") != null) {
            channel.pipeline().remove("elysium_decoder");
            channel.pipeline().remove("elysium_splitter");
            channel.pipeline().remove("elysium_decompress");
        }
        this.getInjection().remove(this.player.getName());
        this.channel.close();
    }

    @Override
    protected void decode(final ChannelHandlerContext channelHandlerContext, final Packet<?> packet, final List<Object> list) {

        //Call the packet event
        Bukkit.getPluginManager().callEvent(new PacketEvent(packet, player));
        list.add(packet);
    }
}
