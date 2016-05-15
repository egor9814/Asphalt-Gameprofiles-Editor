package ru.egor9814.app.a8gpe2.editor;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.widget.ListViewWithActionButton;

/**
 * Created by egor9814 on 01.05.2016.
 */
public class EditorPanel extends ListViewWithActionButton implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

	public EditorPanel(Context context) {
		super(context);
		init();
	}

	public EditorPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public EditorPanel(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@TargetApi(21)
	public EditorPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init(){
		adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
		setAdapter(adapter);
		setOnItemClickListener(this);
		setOnItemLongClickListener(this);
	}


	private ArrayAdapter<String> adapter;
	private ArrayList<String> options = new ArrayList<>();
	public void loadOptions(int resArray){
		options.clear();
		for(String s : getResources().getStringArray(resArray)){
			options.add(s);
		}
	}
	public void addToAdapter(String name){
		adapter.add(name);
		if(onUpdateListener != null) onUpdateListener.onNewElement(name);
	}
	public boolean containsInAdapter(String name){
		for(int i = 0; i < adapter.getCount(); i++){
			if(adapter.getItem(i).equals(name)) return true;
		}
		return false;
	}
	public void pickOption(int position){
		addToAdapter(options.remove(position));
	}
	public void pickOption(String key) {
		int index = options.indexOf(key);
		if(index == -1) addToAdapter(key);
		else pickOption(index);
	}
	public void removeOption(int position){
		String s = adapter.getItem(position);
		adapter.remove(s);
		options.add(s);
		if(onUpdateListener != null) onUpdateListener.onPostRemove(position, s, getItemCount());
	}
	public void removeOption(String key) {
		removeOption(adapter.getPosition(key));
	}
	public void clearAdapter(){
		adapter.clear();
	}

	public String getItem(int position){
		return adapter.getItem(position);
	}
	public int indexOf(String item){
		return adapter.getPosition(item);
	}

	public ArrayList<String> getAvailableOptions() {
		return options;
	}

	public int getItemCount(){
		return adapter.getCount();
	}


	public interface OnEditorClickListener{
		void onEditClick(String name);
	}
	private OnEditorClickListener onEditorClickListener;
	public void setOnEditorClickListener(OnEditorClickListener onEditorClickListener) {
		this.onEditorClickListener = onEditorClickListener;
	}

	public interface OnUpdateListener{
		void onPostRemove(int position, String name, int newSize);
		void onNewElement(String name);
	}
	private OnUpdateListener onUpdateListener;
	public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
		this.onUpdateListener = onUpdateListener;
	}

	public interface OnLongPressListener{
		boolean onLongPress(EditorPanel panel, int position, String name);
	}
	private OnLongPressListener onLongPressListener;
	public void setOnLongPressListener(OnLongPressListener onLongPressListener) {
		this.onLongPressListener = onLongPressListener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
		new AlertDialog.Builder(getContext())
				.setTitle(adapter.getItem(position))
				.setCancelable(true)
				.setPositiveButton(R.string.editor_edit, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(onEditorClickListener != null) onEditorClickListener.onEditClick(adapter.getItem(position));
					}
				})
				.setNegativeButton(R.string.editor_remove, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						remove(position);
						dialog.dismiss();
					}
				})
				.create().show();
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		return onLongPressListener != null && onLongPressListener.onLongPress(this, position, getItem(position));
	}

	private void remove(final int position){
		new AlertDialog.Builder(getContext())
				.setTitle(getResources().getString(R.string.editor_removing) + " " + adapter.getItem(position))
				.setCancelable(true)
				.setPositiveButton(R.string.editor_confirm, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						removeOption(position);
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.create().show();
	}


	public void setName(String value){
		setTag(value);
	}
	public String getName(){
		return (String)getTag();
	}
}
