package io.github.yuko1101.holdcam.fabric.mixin;

import io.github.yuko1101.holdcam.HoldCam;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

// priority is lower than fabric-api so that the keybinding is registered before fabric-api hook is called
@Mixin(value = Options.class, priority = -1000)
public class OptionsMixin {
    @Mutable
    @Final
    @Shadow
    public KeyMapping[] keyMappings;

    @Inject(at = @At("HEAD"), method = "load()V")
    public void loadHook(CallbackInfo ci) {
        try {
            var clazz = Class.forName("net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper", false, this.getClass().getClassLoader());
            for (var keyMapping : HoldCam.getKeyMappings()) {
                clazz.getDeclaredMethod("registerKeyMapping", KeyMapping.class).invoke(null, keyMapping);
            }
            HoldCam.LOGGER.info("Registered HoldCam keybindings via Fabric API");
        } catch (ReflectiveOperationException e) {
            HoldCam.LOGGER.info("Failed to register HoldCam keybindings via Fabric API, falling back to Mixin");
            // TODO: add translations for keybindings to the game
            ArrayList<KeyMapping> newKeysAll = new ArrayList<>(List.of(keyMappings));
            newKeysAll.addAll(HoldCam.getKeyMappings());
            keyMappings = newKeysAll.toArray(new KeyMapping[0]);
            HoldCam.LOGGER.info("Registered HoldCam keybindings via Mixin");
        }
    }
}
