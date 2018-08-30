package cc.shinichi.sherlockutillibrary.utility.common;

import android.content.ClipboardManager;
import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author 工藤
 * @email gougou@16fan.com
 * cc.shinichi.sherlockutillibrary.utility.common
 * create at 2018/8/29  11:55
 * description:
 */
public class ZFBUtil {

	public ZFBUtil() {
	}

	public void copyKey(Context context) {
		if (context == null) {
			return;
		}
		try {
			ClipboardManager cmb =
				(ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
			String data = "%e6%94%af%e4%bb%98%e5%ae%9d%e5%8f%91%e7%ba%a2%e5%8c%85%e5%95%a6%ef%bc%81%e4%ba%ba%e4%ba%"
				+ "ba%e5%8f%af%e9%a2%86%ef%bc%8c%e5%a4%a9%e5%a4%a9%e5%8f%af%e9%a2%86%ef%bc%81%e9%95%bf"
				+ "%e6%8c%89%e5%a4%8d%e5%88%b6%e6%ad%a4%e6%b6%88%e6%81%af%ef%bc%8c%e6%89%93%e5%bc%80%e6%"
				+ "94%af%e4%bb%98%e5%ae%9d%e9%a2%86%e7%ba%a2%e5%8c%85%ef%bc%81cTR6r19187";
			try {
				data = URLDecoder.decode(data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (cmb != null) {
				cmb.setText(data.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}