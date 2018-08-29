package cc.shinichi.sherlockutillibrary.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {

	/**
	 * 调用系统软件打开文件, 会自动判断文件类型
	 *
	 * @author 工藤一号 gougou@16fan.com
	 * @date 2017年6月8日  上午10:46:50
	 */
	public static void openFileBySystemApp(Context context, File file) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置intent的Action属性
		intent.setAction(Intent.ACTION_VIEW);
		// 获取文件file的MIME类型
		String type = getMIMEType(file);
		// 设置intent的data和Type属性。
		intent.setDataAndType(/* uri */Uri.fromFile(file), type);
		// 跳转
		context.startActivity(intent);
	}

	/**
	 * 得到文件类型
	 *
	 * @author 工藤一号 gougou@16fan.com
	 * @date 2017年6月8日  上午10:48:30
	 */
	public static String getMIMEType(File file) {
		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "") return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) {
			if (end.equals(MIME_MapTable[i][0])) type = MIME_MapTable[i][1];
		}
		return type;
	}

	public static final String[][] MIME_MapTable = {
		//{后缀名， MIME类型}
		{ ".3gp", "video/3gpp" }, { ".apk", "application/vnd.android.package-archive" }, { ".asf", "video/x-ms-asf" },
		{ ".avi", "video/x-msvideo" }, { ".bin", "application/octet-stream" }, { ".bmp", "image/bmp" },
		{ ".c", "text/plain" }, { ".class", "application/octet-stream" }, { ".conf", "text/plain" },
		{ ".cpp", "text/plain" }, { ".doc", "application/msword" },
		{ ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
		{ ".xls", "application/vnd.ms-excel" },
		{ ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
		{ ".exe", "application/octet-stream" }, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" },
		{ ".gz", "application/x-gzip" }, { ".h", "text/plain" }, { ".htm", "text/html" }, { ".html", "text/html" },
		{ ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
		{ ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" }, { ".log", "text/plain" },
		{ ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" },
		{ ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" },
		{ ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" }, { ".mp4", "video/mp4" },
		{ ".mpc", "application/vnd.mpohun.certificate" }, { ".mpe", "video/mpeg" }, { ".mpeg", "video/mpeg" },
		{ ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" },
		{ ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" },
		{ ".png", "image/png" }, { ".pps", "application/vnd.ms-powerpoint" },
		{ ".ppt", "application/vnd.ms-powerpoint" },
		{ ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
		{ ".prop", "text/plain" }, { ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
		{ ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" },
		{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" },
		{ ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, { ".wps", "application/vnd.ms-works" },
		{ ".xml", "text/plain" }, { ".z", "application/x-compress" }, { ".zip", "application/x-zip-compressed" },
		{ "", "*/*" }
	};

	/**
	 * 根据文件路径拷贝文件
	 *
	 * @param resourceFile 源文件
	 * @param targetPath 目标路径（包含文件名和文件格式）
	 * @return boolean 成功true、失败false
	 */
	public static boolean copyFile(File resourceFile, String targetPath, String fileName) {
		boolean result = false;
		if (resourceFile == null || TextUtils.isEmpty(targetPath)) {
			return result;
		}
		File target = new File(targetPath);
		if (target.exists()) {
			target.delete(); // 已存在的话先删除
		} else {
			try {
				target.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		File targetFile = new File(targetPath.concat(fileName));
		if (targetFile.exists()) {
			targetFile.delete();
		} else {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileChannel resourceChannel = null;
		FileChannel targetChannel = null;
		try {
			resourceChannel = new FileInputStream(resourceFile).getChannel();
			targetChannel = new FileOutputStream(targetFile).getChannel();
			resourceChannel.transferTo(0, resourceChannel.size(), targetChannel);
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		try {
			resourceChannel.close();
			targetChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}