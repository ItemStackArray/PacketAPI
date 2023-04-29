package fx.itemstackarray.packetapi;

import fx.itemstackarray.packetapi.struct.NotifyStruct;
import fx.itemstackarray.packetapi.struct.PermissionStruct;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketAPI extends JavaPlugin {

    @Getter
    private static PacketAPI INSTANCE;
    @Getter
    private PermissionStruct permissionStruct;
    @Getter
    private NotifyStruct notifyStruct;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.permissionStruct = new PermissionStruct();
        this.notifyStruct = new NotifyStruct();
        this.permissionStruct.setPERMISSION("anticrash.notify");
    }
}
