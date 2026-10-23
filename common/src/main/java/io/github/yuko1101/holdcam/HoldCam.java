package io.github.yuko1101.holdcam;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.CameraType;
import net.minecraft.client.KeyMapping;
import org.slf4j.Logger;

import java.util.List;

public class HoldCam {
	public static final String MOD_ID = "holdcam";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static final CamKey REAR_CAM_KEY = new CamKey(new KeyMapping("key.rearcam", InputConstants.Type.KEYBOARD, InputConstants.KEY_R, KeyMapping.Category.MISC), CameraType.THIRD_PERSON_FRONT);
	private static final CamKey FRONT_CAM_KEY = new CamKey(new KeyMapping("key.frontcam", InputConstants.Type.KEYBOARD, InputConstants.KEY_G, KeyMapping.Category.MISC), CameraType.THIRD_PERSON_BACK);

	public static final List<CamKey> CAM_KEYS = List.of(
			REAR_CAM_KEY,
			FRONT_CAM_KEY
	);

	public static void init() {
	}

	public static void onKeyStateUpdate() {
		REAR_CAM_KEY.onKeyStateUpdate();
		FRONT_CAM_KEY.onKeyStateUpdate();
	}

	public static List<KeyMapping> getKeyMappings() {
		return CAM_KEYS.stream().map(camKey -> camKey.keyMapping).toList();
	}

}
