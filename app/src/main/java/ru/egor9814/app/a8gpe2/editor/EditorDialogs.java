package ru.egor9814.app.a8gpe2.editor;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONException;

import ru.egor9814.app.a8gpe2.EditorActivity;
import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.profiles.Profile;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class EditorDialogs {

	public interface OnResultListener{
		void onResult(String value);
		void onResult(double value);
		void onResult(int value);
		void onResult(boolean value);
		//void onRemove();
	}

	public static void showStringDialog(Context context, String title, String hint, String value, /*boolean showRemove, */final OnResultListener listener){
		/*final EditText editText = new EditText(context);
		editText.setSingleLine();
		editText.setHint(hint);
		editText.setText(value);*/
		final TextInputLayout editText = normalEditText(context, hint, value);
		AlertDialog.Builder adb = new AlertDialog.Builder(context)
				.setTitle(title)
				.setView(editText)
				.setCancelable(true)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(editText.getEditText().getText().toString());
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		/*if(showRemove){
			adb.setNegativeButton(R.string.editor_remove, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					listener.onRemove();
				}
			});
		}*/
		adb.create().show();
	}
	private static TextInputLayout normalEditText(Context context, String hint, String value){
		TextInputEditText editText = new TextInputEditText(context);
		editText.setSingleLine();
		//editText.setHint(hint);
		editText.setText(value);
		editText.setSelection(editText.length());

		TextInputLayout layout = new TextInputLayout(context);
		layout.setHint(hint);
		layout.addView(editText);
		return layout;
	}
	/*public static void showStringDialog(Context context, String title, String hint, String value, final OnResultListener listener){
		showStringDialog(context, title, hint, value, false, listener);
	}*/


	public static void showBooleanDialog(Context context, String title, boolean value, final OnResultListener listener){
		final SwitchCompat switchCompat = new SwitchCompat(context);
		switchCompat.setText(title);
		switchCompat.setChecked(value);
		switchCompat.setPadding(10, 10, 10, 10);
		new AlertDialog.Builder(context)
				.setCancelable(true)
				.setView(switchCompat)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(switchCompat.isChecked());
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				/*.setNegativeButton(R.string.editor_remove, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onRemove();
					}
				})*/
				.create().show();
	}


	public static void showIntegerDialog(Context context, String title, String hint, int value, final OnResultListener listener){
		final TextInputLayout editText = numberFormatText(context, hint, Integer.toString(value));
		AlertDialog.Builder adb = new AlertDialog.Builder(context)
				.setTitle(title)
				.setView(editText)
				.setCancelable(true)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(Integer.parseInt(editText.getEditText().getText().toString()));
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		adb.create().show();
	}
	public static void showDoubleDialog(Context context, String title, String hint, double value, final OnResultListener listener){
		final TextInputLayout editText = numberFormatText(context, hint, Double.toString(value), false);
		AlertDialog.Builder adb = new AlertDialog.Builder(context)
				.setTitle(title)
				.setView(editText)
				.setCancelable(true)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(Integer.parseInt(editText.getEditText().getText().toString()));
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		adb.create().show();
	}
	private static TextInputLayout numberFormatText(Context context, String hint, String value, boolean onlyDecimal){
		TextInputLayout editText = normalEditText(context, hint, value);
		int type = InputType.TYPE_CLASS_NUMBER;
		if(!onlyDecimal) type |= InputType.TYPE_NUMBER_FLAG_DECIMAL;
		editText.getEditText().setInputType(type);
		return editText;
	}
	private static TextInputLayout numberFormatText(Context context, String hint, String value){
		return numberFormatText(context, hint, value, true);
	}



	public static void showProcessorDialog(Context context, String title, String[] titles, Number[] values, final OnResultListener[] listeners){
		final TextInputLayout core = numberFormatText(context, titles[0], String.valueOf(values[0]));
		final TextInputLayout max = numberFormatText(context, titles[1], String.valueOf(values[1]), false);
		final TextInputLayout min = numberFormatText(context, titles[2], String.valueOf(values[2]), false);
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(core);
		layout.addView(max);
		layout.addView(min);
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setCancelable(true)
				.setView(layout)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listeners[0].onResult(Integer.parseInt(core.getEditText().getText().toString()));
						listeners[1].onResult(Double.parseDouble(max.getEditText().getText().toString()));
						listeners[2].onResult(Double.parseDouble(min.getEditText().getText().toString()));
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

	public interface OnRendererResultListener{
		void onNameChanged(String newName);
		void onParamsClick(String[] paramsNames, String[] params);
	}
	public static void showRendererDialog(Context context, String title, String name, final String[] paramsNames, final String[] params, final OnRendererResultListener listener){
		final TextInputLayout renderer = normalEditText(context, title, name);
		new AlertDialog.Builder(context)
				.setView(renderer)
				.setCancelable(true)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onNameChanged(renderer.getEditText().getText().toString());
					}
				})
				.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.setNeutralButton(R.string.renderer_params, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onParamsClick(paramsNames, params);
					}
				})
				.create().show();
	}
	public static void showNewRendererDialog(Context context, String title, final OnResultListener listener){
		final TextInputLayout text = normalEditText(context, title, "");
		new AlertDialog.Builder(context)
				.setCancelable(true)
				.setView(text)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(text.getEditText().getText().toString());
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
	public interface OnParamEditorListener{
		void onParamChanged(String name, String value);
	}
	public static void showParamEditDialog(Context context, String title, String nameHint, String valueHint, String name, String value, final OnParamEditorListener listener){
		final TextInputLayout n = normalEditText(context, nameHint, name);
		final TextInputLayout v = normalEditText(context, valueHint, value);
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(n);
		layout.addView(v);
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setCancelable(true)
				.setView(layout)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onParamChanged(n.getEditText().getText().toString(), v.getEditText().getText().toString());
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


	public static void showSingleChoiceDialog(Context context, String title, final String[] items, int selected, final OnResultListener listener){
		final ListView list = new ListView(context);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_single_choice, items));
		list.setItemChecked(selected, true);
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setCancelable(true)
				.setView(list)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(list.getCheckedItemPosition());
						listener.onResult(items[list.getCheckedItemPosition()]);
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

	public static void showSelectorDialog(Context context, String title, final String[] items, final OnResultListener listener){
		new AlertDialog.Builder(context)
				.setTitle(title)
				.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(which);
						listener.onResult(items[which]);
					}
				})
				.setCancelable(true)
				.create().show();
	}



	public static void showUnknownItemDialog(Context context, final Profile profile, final String key) throws JSONException {
		String hint = context.getResources().getString(R.string.editor_enter_value);
		switch(profile.typeOf(key)){
			case STRING:
				showStringDialog(context, key, hint, (String)profile.get(key), new EditorActivity.StringResultAdapter() {
					@Override
					public void onResult(String value) {
						profile.put(key, value);
					}
				});
				break;
			case INT:
				showIntegerDialog(context, key, hint, (int) profile.get(key), new EditorActivity.IntegerResultAdapter() {
					@Override
					public void onResult(int value) {
						profile.put(key, value);
					}
				});
				break;
			case DOUBLE:
				showDoubleDialog(context, key, hint, (double) profile.get(key), new EditorActivity.DoubleResultAdapter() {
					@Override
					public void onResult(double value) {
						profile.put(key, value);
					}
				});
				break;
			case BOOL:
				showBooleanDialog(context, key, (boolean) profile.get(key), new EditorActivity.BooleanResultAdapter() {
					@Override
					public void onResult(boolean value) {
						profile.put(key, value);
					}
				});
				break;
			case NONE:
				break;
		}
	}


	public static void showNamedDeviceDialog(Context context, String title, String oldName, final OnResultListener listener){
		final TextInputLayout name = normalEditText(context, context.getResources().getString(R.string.editor_name), oldName);
		new AlertDialog.Builder(context)
				.setTitle(title + " " + oldName)
				.setCancelable(true)
				.setView(name)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onResult(name.getEditText().getText().toString());
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
}
