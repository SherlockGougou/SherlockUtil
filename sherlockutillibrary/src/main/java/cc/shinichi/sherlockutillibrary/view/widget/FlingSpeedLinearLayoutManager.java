package cc.shinichi.sherlockutillibrary.view.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by SherlockHolmes on 2017/12/13.09:54
 * 可自定义recyclerview滚动速度的布局管理
 */

public class FlingSpeedLinearLayoutManager extends LinearLayoutManager {

	private double speedRatio = 0.8;

	public FlingSpeedLinearLayoutManager(Context context, double speedRatio) {
		super(context);
		this.speedRatio = speedRatio;
	}

	public FlingSpeedLinearLayoutManager(Context context, int orientation, boolean reverseLayout, double speedRatio) {
		super(context, orientation, reverseLayout);
		this.speedRatio = speedRatio;
	}

	public FlingSpeedLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes,
		double speedRatio) {
		super(context, attrs, defStyleAttr, defStyleRes);
		this.speedRatio = speedRatio;
	}

	@Override public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
		int a = super.scrollVerticallyBy((int) (speedRatio * dy), recycler, state);
		//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
		if (a == (int) (speedRatio * dy)) {
			return dy;
		}
		return a;
	}
}