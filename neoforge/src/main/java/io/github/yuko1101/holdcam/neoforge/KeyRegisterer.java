package io.github.yuko1101.holdcam.neoforge;

import io.github.yuko1101.holdcam.HoldCam;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = HoldCam.MOD_ID)
public class KeyRegisterer {
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        for (var keyMapping : HoldCam.getKeyMappings()) {
            event.register(keyMapping);
        }
    }
}
