package ru.egor9814.app.a8gpe2.license;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import ru.egor9814.app.a8gpe2.R;
import ru.egor9814.app.a8gpe2.xml.Attribute;
import ru.egor9814.app.a8gpe2.xml.Node;
import ru.egor9814.app.a8gpe2.xml.XMLParser;

/**
 * Created by egor9814 on 09.05.2016.
 */
public class LicensesShowDialog extends DialogFragment {

	public static class License{
		public final String name, version, url, text;
		private License(String name, String version, String url, String text) {
			this.name = name;
			this.version = version;
			this.url = url;
			this.text = text;
		}
		private License(Node node){
			this(getAttribute(node, "name"), getAttribute(node, "version"), getAttribute(node, "url"), node.getText());
		}
		private static String getAttribute(Node node, String key){
			Attribute attr = node.getAttribute(key);
			if(attr == null) return null;
			return attr.value;
		}
	}

	private static final List<License> licenses = new ArrayList<>();
	private List<License> getLicenses(){
		if(licenses.size() == 0){
			XmlPullParser xml = getResources().getXml(R.xml.licenses);
			try {
				new XMLParser().loadXml(xml, new XMLParser.OnXmlParseListener() {
					@Override
					public void onStart() {}
					@Override
					public void onResult(Node result) {
						for(Node l : result.getChilds()){
							licenses.add(new License(l));
						}
					}
				});
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return licenses;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final List<License> licenses = getLicenses();
		List<String> adapter = new ArrayList<>();
		for(License l : licenses){
			if(l.name == null) continue;
			String name = l.name;
			if(l.version != null) name += " v" + l.version;
			adapter.add(name);
		}
		if(licenses.size() != adapter.size()) return new AlertDialog.Builder(getActivity()).setTitle("error").create();
		return new AlertDialog.Builder(getActivity())
				.setItems(adapter.toArray(new String[adapter.size()]), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(getActivity(), LicenseViewActivity.class);
						i.putExtra("license_name", licenses.get(which).name);
						i.putExtra("license_text", licenses.get(which).text);
						i.putExtra("license_url", licenses.get(which).url);
						getActivity().startActivity(i);
					}
				})
				.create();
	}
}
