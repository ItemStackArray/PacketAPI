package fx.itemstackarray.packetapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketAPI extends JavaPlugin {

    @Getter
    private static PacketAPI INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }
}
