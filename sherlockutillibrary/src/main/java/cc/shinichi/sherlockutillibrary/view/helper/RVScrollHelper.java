package cc.shinichi.sherlockutillibrary.view.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import cc.shinichi.sherlockutillibrary.utility.common.Print;

public class RVScrollHelper extends RecyclerView.OnScrollListener {

	private static final String TAG = "RVScrollHelper";

	private LayoutManager layoutManager;
	private Listener listener;
	private boolean isNeedPauseGlideOnScrolling = false;

	public RVScrollHelper(LayoutManager layoutManager) {
		this.layoutManager = layoutManager;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void setNeedPauseGlideOnScrolling(boolean needPauseGlideOnScrolling) {
		isNeedPauseGlideOnScrolling = needPauseGlideOnScrolling;
	}

	public interface Listener {

		void getFirstPosition(int position);// 获取当前显示的第一个item的position

		void getLastPosition(int position);// 获取当前显示的最后一个item的position

		void getVisibleItemCount(int count);// 获取当前屏幕显示总条数

		void scrollingToTop();// 正在滑向顶部

		void scrollingToBottom();// 正在滑向底部

		void isTop();// 到达顶部

		void isBottom();// 到达底部

		void onScrollStateChanged(RecyclerView recyclerView, int newState);
	}

	@Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
		super.onScrollStateChanged(recyclerView, newState);
		if (listener != null) {
			listener.onScrollStateChanged(recyclerView, newState);
		}
		if (isNeedPauseGlideOnScrolling) {
			// 不滚动时再加载图片
			//if (newState == RecyclerView.SCROLL_STATE_IDLE) {// resume
			//	GlideUtils.resume(recyclerView.getContext());
			//} else {// pause
			//	GlideUtils.pause(recyclerView.getContext());
			//}
		}
	}

	@Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);
		if (!recyclerView.canScrollVertically(1)) {// 屏幕内容不能再往上滑动了，说明到了底部
			if (listener != null) {
				Print.d(TAG, "isBottom");
				listener.isBottom();
			}
		} else if (!recyclerView.canScrollVertically(-1)) {// 屏幕内容不能再往下滑动了，说明到了顶部
			if (listener != null) {
				Print.d(TAG, "isTop");
				listener.isTop();
			}
		} else if (dy < 0) {// 屏幕内容正在滚动到顶部
			if (listener != null) {
				Print.d(TAG, "scrollingToTop");
				listener.scrollingToTop();
			}
		} else if (dy > 0) {// 屏幕内容正在滚动到底部
			if (listener != null) {
				Print.d(TAG, "scrollingToBottom");
				listener.scrollingToBottom();
			}
		}
		if (layoutManager != null) {
			if (layoutManager instanceof LinearLayoutManager) {
				if (listener != null) {
					//获取最后一个可见view的位置
					int lastItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
					//获取第一个可见view的位置
					int firstItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
					listener.getFirstPosition(firstItemPosition);
					listener.getLastPosition(lastItemPosition);
					listener.getVisibleItemCount(lastItemPosition - firstItemPosition + 1);
				}
			}
		}
	}

	public void destroy() {
		this.listener = null;
		this.layoutManager = null;
		setListener(null);
	}
}