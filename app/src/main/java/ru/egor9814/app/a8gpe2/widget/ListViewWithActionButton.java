package ru.egor9814.app.a8gpe2.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class ListViewWithActionButton extends ListView {

	public ListViewWithActionButton(Context context) {
		super(context);
	}

	public ListViewWithActionButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewWithActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(21)
	public ListViewWithActionButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}


	public interface OnActionButtonStateChangeListener{
		void showActionButton();
		void hideActionButton();
	}
	public void setActionButton(final OnActionButtonStateChangeListener onActionButtonStateChangeListener){
		setOnScrollListener(new ListScrollHelper(new ListScrollHelper.OnScrollListener() {
			@Override
			public void onScroll(int delta, int total) {
				if(delta < 0){
					onActionButtonStateChangeListener.hideActionButton();
				} else if(delta > 0){
					onActionButtonStateChangeListener.showActionButton();
				}
			}
		}));
	}
}
