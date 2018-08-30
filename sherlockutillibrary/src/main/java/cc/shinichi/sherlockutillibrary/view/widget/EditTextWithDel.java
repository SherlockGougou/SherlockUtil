package cc.shinichi.sherlockutillibrary.view.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import cc.shinichi.sherlockutillibrary.R;

/**
 * 带有删除键的EditText
 *
 * @author 工藤一号 gougou@16fan.com
 * @date 2017年6月13日 上午10:05:07
 */
public class EditTextWithDel extends AppCompatEditText {

	private final static String TAG = "EditTextWithDel";
	private Drawable imgInable;
	private Drawable imgAble;
	private Context mContext;

	public EditTextWithDel(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init() {
		// 有内容时显示叉叉, 没内容时不显示叉叉
		imgInable = null;
		imgAble = mContext.getResources().getDrawable(R.drawable.ic_action_clean);
		addTextChangedListener(new TextWatcher() {
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override public void afterTextChanged(Editable s) {
				setDrawable();
			}
		});
		setDrawable();
	}

	// 设置删除图片
	private void setDrawable() {
		if (length() < 1) {
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
		} else {
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
		}
	}

	// 处理删除事件
	@Override public boolean onTouchEvent(MotionEvent event) {
		if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
			int eventX = (int) event.getRawX();
			int eventY = (int) event.getRawY();
			Log.d(TAG, "eventX = " + eventX + "; eventY = " + eventY);
			Rect rect = new Rect();
			getGlobalVisibleRect(rect);
			rect.left = rect.right - 50;
			if (rect.contains(eventX, eventY)) setText("");
		}
		return super.onTouchEvent(event);
	}

	@Override protected void finalize() throws Throwable {
		super.finalize();
	}
}