package cc.shinichi.sherlockutillibrary.utility.device;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

/**
 * Android各大手机品牌手机跳转到权限管理界面
 *
 * @author 工藤一号 gougou@16fan.com
 * @date 2017年6月5日 上午11:36:40
 */
public class PermissionMgrUtil {
	/**
	 * Build.MANUFACTURER
	 */
	public static final String MANUFACTURER_HUAWEI = "huawei";// 华为
	public static final String MANUFACTURER_MEIZU = "meizu";// 魅族
	public static final String MANUFACTURER_XIAOMI = "xiaomi";// 小米
	public static final String MANUFACTURER_REDMI = "redmi";// 小米 红米
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

	/**
	 * 此函数可以自己定义
	 */
	public static void GoToSetting(Activity activity) {
		String lowCase = Build.MANUFACTURER.toLowerCase();
		switch (lowCase) {
			case MANUFACTURER_HUAWEI:
				Huawei(activity);
				break;
			case MANUFACTURER_MEIZU:
				Meizu(activity);
				break;
			case MANUFACTURER_XIAOMI:
			case MANUFACTURER_REDMI:
				Xiaomi(activity);
				break;
			case MANUFACTURER_SONY:
				Sony(activity);
				break;
			case MANUFACTURER_OPPO:
				OPPO(activity);
				break;
			case MANUFACTURER_LG:
				LG(activity);
				break;
			case MANUFACTURER_LETV:
				Letv(activity);
				break;
			case MANUFACTURER_SMARTISAN:
				Smartisan(activity);
				break;
			case MANUFACTURER_360:
				_360(activity);
				break;
			default:
				ApplicationInfo(activity);
				Log.e("goToSetting", "目前暂不支持此系统");
				break;
		}
	}

	public static String getManufacture() {
		String lowCase = Build.MANUFACTURER.toLowerCase();
		return lowCase;
	}

	private static void Huawei(Activity activity) {
		try {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("packageName", activity.getPackageName());
			ComponentName comp =
				new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
			intent.setComponent(comp);
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	private static void Meizu(Activity activity) {
		try {
			Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra("packageName", activity.getPackageName());
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	private static void Xiaomi(Activity activity) {
		try { // upper MIUI 8
			Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
			ComponentName componentName = new ComponentName("com.miui.securitycenter",
				"com.miui.permcenter.permissions.PermissionsEditorActivity");
			intent.setComponent(componentName);
			intent.putExtra("extra_pkgname", activity.getPackageName());
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			try { // MIUI 5/6/7
				Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
				localIntent.setClassName("com.miui.securitycenter",
					"com.miui.permcenter.permissions.AppPermissionsEditorActivity");
				localIntent.putExtra("extra_pkgname", activity.getPackageName());
				activity.startActivity(localIntent);
			} catch (Exception e1) { // 否则跳转到应用详情
				ApplicationInfo(activity);
			}
		}
	}

	private static void Sony(Activity activity) {
		try {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("packageName", activity.getPackageName());
			ComponentName comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
			intent.setComponent(comp);
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	private static void OPPO(Activity activity) {
		try {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("packageName", activity.getPackageName());
			ComponentName comp =
				new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
			intent.setComponent(comp);
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	private static void LG(Activity activity) {
		try {
			Intent intent = new Intent("android.intent.action.MAIN");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("packageName", activity.getPackageName());
			ComponentName comp =
				new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
			intent.setComponent(comp);
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	private static void Letv(Activity activity) {
		try {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("packageName", activity.getPackageName());
			ComponentName comp =
				new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
			intent.setComponent(comp);
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	private static void Smartisan(Activity activity) {
		ApplicationInfo(activity);

		//try {
		//  Intent intent = new Intent();
		//  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//  intent.putExtra("packageName", activity.getPackageName());
		//  ComponentName comp =
		//      new ComponentName("com.smartisanos.security", "com.smartisanos.security.PackageDetail");
		//  intent.setComponent(comp);
		//  activity.startActivity(intent);
		//} catch (Exception e) {
		//  e.printStackTrace();
		//  try {
		//    Intent intent = new Intent();
		//    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//    intent.putExtra("packageName", activity.getPackageName());
		//    ComponentName comp =
		//        new ComponentName("com.smartisanos.security", "com.smartisanos.security.MainActivity");
		//    intent.setComponent(comp);
		//    activity.startActivity(intent);
		//  } catch (Exception e1) {
		//    e1.printStackTrace();
		//    ApplicationInfo(activity);
		//  }
		//}
	}

	/**
	 * 只能打开到自带安全软件
	 */
	private static void _360(Activity activity) {
		try {
			Intent intent = new Intent("android.intent.action.MAIN");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("packageName", activity.getPackageName());
			ComponentName comp =
				new ComponentName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
			intent.setComponent(comp);
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ApplicationInfo(activity);
		}
	}

	/**
	 * 应用信息界面
	 */
	private static void ApplicationInfo(Activity activity) {
		try {
			Intent localIntent = new Intent();
			localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
			activity.startActivity(localIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 系统设置界面
	 */
	public static void SystemConfig(Activity activity) {
		Intent intent = new Intent(Settings.ACTION_SETTINGS);
		activity.startActivity(intent);
	}
}