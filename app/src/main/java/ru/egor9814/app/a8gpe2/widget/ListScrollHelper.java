package ru.egor9814.app.a8gpe2.widget;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class ListScrollHelper implements AbsListView.OnScrollListener {

	public interface OnScrollListener {
		void onScroll(int delta, int total);
	}

	private OnScrollListener listener;
	public ListScrollHelper(OnScrollListener listener) {
		this.listener = listener;
	}

	private boolean listScrollStarted;
	private int firstVisibleItem, firstVisibleHegiht, firstVisibleTop,
			firstVisibleBottom, totalScrollDistance;

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(view.getCount() == 0) return;
		switch(scrollState){
			case SCROLL_STATE_IDLE:
				listScrollStarted = false;
				break;
			case SCROLL_STATE_TOUCH_SCROLL:
				final View firstChild = view.getChildAt(0);
				firstVisibleItem = view.getFirstVisiblePosition();
				firstVisibleTop = firstChild.getTop();
				firstVisibleBottom = firstChild.getBottom();
				firstVisibleHegiht = firstChild.getHeight();
				listScrollStarted = true;
				totalScrollDistance = 0;
				break;
		}
	}

	public int getTotalScrollDistance() {
		return totalScrollDistance;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (totalItemCount == 0 || !listScrollStarted) return;
		final View firstChild = view.getChildAt(0);
		final int firstVisibleTop = firstChild.getTop(), firstVisibleBottom = firstChild.getBottom();
		final int firstVisibleHeight = firstChild.getHeight();
		final int delta;
		if (firstVisibleItem > this.firstVisibleItem) {
			this.firstVisibleTop += this.firstVisibleHegiht;
			delta = firstVisibleTop - this.firstVisibleTop;
		} else if (firstVisibleItem < this.firstVisibleItem) {
			this.firstVisibleBottom -= this.firstVisibleHegiht;
			delta = firstVisibleBottom - this.firstVisibleBottom;
		} else {
			delta = firstVisibleBottom - this.firstVisibleBottom;
		}
		this.totalScrollDistance += delta;
		if (listener != null) {
			listener.onScroll(delta, this.totalScrollDistance);
		}
		this.firstVisibleTop = firstVisibleTop;
		this.firstVisibleBottom = firstVisibleBottom;
		this.firstVisibleHegiht = firstVisibleHeight;
		this.firstVisibleItem = firstVisibleItem;
	}
}