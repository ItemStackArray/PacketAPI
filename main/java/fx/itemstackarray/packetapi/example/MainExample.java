package fx.itemstackarray.packetapi.example;

import fx.itemstackarray.packetapi.packet.PacketInjector;

public final class MainExample /* extends JavaPlugin*/ {

    private static PacketInjector packetInjector;

    public void onEnable() {

        /* Set the decoder name from your AntiCrash */
        packetInjector.setDecoder_name("your-decoder-name");
        /* Set the splitter name from your AntiCrash */
        packetInjector.setSplitter_name("your-splitter-name");
        /* Set the decompress name from your AntiCrash */
        packetInjector.setDecompress_name("your-decompress-name");

    }
}
