package io.github.yuko1101.holdcam;

import net.minecraft.client.CameraType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class CamKey {
    public final KeyMapping keyMapping;
    private final CameraType targetPerspective;

    private boolean isActive = false;

    public CamKey(KeyMapping keyMapping, CameraType targetPerspective) {
        this.keyMapping = keyMapping;
        this.targetPerspective = targetPerspective;
    }

    public void onKeyStateUpdate() {
        if (keyMapping.isDown() && !isActive) {
            isActive = true;
            Minecraft.getInstance().options.setCameraType(targetPerspective);
        } else if (!keyMapping.isDown() && isActive) {
            isActive = false;
            Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
        }
    }
}
