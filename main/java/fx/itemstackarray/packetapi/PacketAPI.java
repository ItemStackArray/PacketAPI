package fx.itemstackarray.packetapi;

import fx.itemstackarray.packetapi.packet.PacketInjector;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketAPI extends JavaPlugin {

    @Getter
    private static PacketAPI INSTANCE;
    @Getter
    @Setter
    private PacketInjector PACKETINJECTOR;



    @Override
    public void onEnable() {
        INSTANCE = this;
    }
}
