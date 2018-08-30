package cc.shinichi.sherlockutillibrary.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by SherlockHolmes on 2017/12/13.10:01
 */

public class FlingSpeedRecyclerView extends RecyclerView {

	private double scale;

	public FlingSpeedRecyclerView(Context context) {
		super(context);
	}

	public FlingSpeedRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public FlingSpeedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	@Override public boolean fling(int velocityX, int velocityY) {
		velocityY *= scale;
		return super.fling(velocityX, velocityY);
	}
}
