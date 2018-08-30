package cc.shinichi.sherlockutillibrary.utility.core;

import cc.shinichi.sherlockutillibrary.utility.common.Print;
import com.lzh.easythread.Callback;
import com.lzh.easythread.EasyThread;

/**
 * @author 工藤
 * @email gougou@16fan.com
 * com.fan16.cn.util
 * create at 2018/4/12  16:06
 * description:线程池管理器
 */
public class ThreadManager {

	private static final String TAG = "ThreadManager";

	private final static EasyThread io;
	private final static EasyThread cache;
	private final static EasyThread calculator;
	private final static EasyThread file;

	public static EasyThread getIO() {
		return io;
	}

	public static EasyThread getCache() {
		return cache;
	}

	public static EasyThread getCalculator() {
		return calculator;
	}

	public static EasyThread getFile() {
		return file;
	}

	static {
		io = EasyThread.Builder.createFixed(10).setName("IO").setPriority(7).setCallback(new DefaultCallback()).build();
		cache = EasyThread.Builder.createCacheable().setName("cache").setCallback(new DefaultCallback()).build();
		calculator = EasyThread.Builder.createFixed(4)
			.setName("calculator")
			.setPriority(Thread.MAX_PRIORITY)
			.setCallback(new DefaultCallback())
			.build();
		file =
			EasyThread.Builder.createFixed(4).setName("file").setPriority(5).setCallback(new DefaultCallback()).build();
	}

	private static class DefaultCallback implements Callback {

		@Override public void onError(String threadName, Throwable t) {
			Print.d(TAG, "Task with thread " + threadName + " has occurs an error:" + t.getMessage());
		}

		@Override public void onCompleted(String threadName) {
			Print.d(TAG, "Task with thread " + threadName + " completed");
		}

		@Override public void onStart(String threadName) {
			Print.d(TAG, "Task with thread " + threadName + " start running!");
		}
	}
}
