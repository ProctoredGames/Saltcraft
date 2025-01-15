package net.proctoredgames.saltcraft.capes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CapeHandler {
    private static final Map<UUID, String> PLAYER_CAPES = new HashMap<>();

    static {
        PLAYER_CAPES.put(UUID.fromString("dd4512f6-149b-47cb-a54e-440387f72eb2"), "saltcraft:textures/capes/developer.png");
        PLAYER_CAPES.put(UUID.fromString("11111111-1111-1111-1111-111111111111"), "saltcraft:textures/capes/contributor.png");
    }

    public static String getCape(UUID uuid) {
        return PLAYER_CAPES.get(uuid);
    }
}
