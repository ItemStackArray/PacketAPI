package fx.itemstackarray.packetapi;

import fx.itemstackarray.packetapi.packet.PacketInjector;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketAPI extends JavaPlugin {

    @Getter
    @Setter
    private static PacketInjector packetInjector;

    @Override
    public void onEnable() {
        packetInjector.setDecoder_name("your-decoder-name");
        packetInjector.setSplitter_name("your-splitter-name");
        packetInjector.setDecompress_name("your-decompress-name");
    }
}
