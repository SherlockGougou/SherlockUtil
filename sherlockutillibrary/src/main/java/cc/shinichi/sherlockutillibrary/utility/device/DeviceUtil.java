package cc.shinichi.sherlockutillibrary.utility.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import java.io.File;
import java.util.UUID;

/**
 * @author 工藤
 * @email gougou@16fan.com
 * create at 2018/2/7 10:37
 * description:
 */
public class DeviceUtil {

	/**
	 * Build.MANUFACTURER
	 */
	public static final String MANUFACTURER_HUAWEI = "huawei";// 华为
	public static final String MANUFACTURER_MEIZU = "meizu";// 魅族
	public static final String MANUFACTURER_XIAOMI = "xiaomi";// 小米
	public static final String MANUFACTURER_SONY = "sony";// 索尼
	public static final String MANUFACTURER_OPPO = "oppo";
	public static final String MANUFACTURER_LG = "lg";
	public static final String MANUFACTURER_VIVO = "vivo";
	public static final String MANUFACTURER_SAMSUNG = "samsung";// 三星
	public static final String MANUFACTURER_LETV = "letv";// 乐视
	public static final String MANUFACTURER_ZTE = "zte";// 中兴
	public static final String MANUFACTURER_YULONG = "yulong";// 酷派
	public static final String MANUFACTURER_LENOVO = "lenovo";// 联想
	public static final String MANUFACTURER_SMARTISAN = "smartisan";// 锤子
	public static final String MANUFACTURER_360 = "360";// 360
	public static final String MANUFACTURER_ONEPLUS = "oneplus";// OnePlus

	private DeviceUtil() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static String getDeviceManufacture() {
		String lowCase = Build.MANUFACTURER.toLowerCase();
		switch (lowCase) {
			case MANUFACTURER_HUAWEI:
				return MANUFACTURER_HUAWEI;
			case MANUFACTURER_MEIZU:
				return MANUFACTURER_MEIZU;
			case MANUFACTURER_XIAOMI:
				return MANUFACTURER_XIAOMI;
			case MANUFACTURER_SONY:
				return MANUFACTURER_SONY;
			case MANUFACTURER_OPPO:
				return MANUFACTURER_OPPO;
			case MANUFACTURER_LG:
				return MANUFACTURER_LG;
			case MANUFACTURER_LETV:
				return MANUFACTURER_LETV;
			case MANUFACTURER_SMARTISAN:
				return MANUFACTURER_SMARTISAN;
			case MANUFACTURER_360:
				return MANUFACTURER_360;
			case MANUFACTURER_ONEPLUS:
				return MANUFACTURER_ONEPLUS;
			case MANUFACTURER_VIVO:
				return MANUFACTURER_VIVO;
			case MANUFACTURER_SAMSUNG:
				return MANUFACTURER_SAMSUNG;
			case MANUFACTURER_ZTE:
				return MANUFACTURER_ZTE;
			case MANUFACTURER_YULONG:
				return MANUFACTURER_YULONG;
			case MANUFACTURER_LENOVO:
				return MANUFACTURER_LENOVO;
			default:
				return "N/A";
		}
	}

	//获得独一无二的Psuedo ID
	public static String getUniquePsuedoID() {
		String serial = null;
		String m_szDevIDShort = "16"
			+ Build.BOARD.length() % 10
			+ Build.BRAND.length() % 10
			+ Build.CPU_ABI.length() % 10
			+ Build.DEVICE.length() % 10
			+ Build.DISPLAY.length() % 10
			+ Build.HOST.length() % 10
			+ Build.ID.length() % 10
			+ Build.MANUFACTURER.length() % 10
			+ Build.MODEL.length() % 10
			+ Build.PRODUCT.length() % 10
			+ Build.TAGS.length() % 10
			+ Build.TYPE.length() % 10
			+ Build.USER.length() % 10; //13 位
		try {
			serial = android.os.Build.class.getField("SERIAL").get(null).toString();
			//API>=9 使用serial号
			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
		} catch (Exception exception) {
			//serial需要一个初始化
			serial = ""; // 随便一个初始化
		}
		// 未获取到就返回空字符串
		return serial;
	}

	/**
	 * 判断设备是否root
	 *
	 * @return the boolean{@code true}: 是<br>
	 * {@code false}: 否
	 */
	public static boolean isDeviceRoot() {
		String su = "su";
		String[] locations = {
			"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/",
			"/data/local/bin/", "/data/local/"
		};
		for (String location : locations) {
			if (new File(location + su).exists()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取设备系统版本号
	 *
	 * @return 设备系统版本号
	 */
	public static int getSDKVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 获取设备AndroidID
	 *
	 * @param context 上下文
	 * @return AndroidID
	 */
	@SuppressLint("HardwareIds") public static String getAndroidID(Context context) {
		return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
	}

	/**
	 * 获取设备厂商
	 * <p>
	 * 如Xiaomi
	 * </p>
	 *
	 * @return 设备厂商
	 */

	public static String getManufacturer() {
		return Build.MANUFACTURER;
	}

	/**
	 * 获取设备型号
	 * <p>
	 * 如MI2SC
	 * </p>
	 *
	 * @return 设备型号
	 */
	public static String getModel() {
		String model = Build.MODEL;
		if (model != null) {
			model = model.trim().replaceAll("\\s*", "");
		} else {
			model = "";
		}
		return model;
	}
}