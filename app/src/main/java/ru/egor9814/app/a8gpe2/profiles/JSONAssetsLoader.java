package ru.egor9814.app.a8gpe2.profiles;

import android.content.res.AssetManager;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 03.05.2016.
 */
public class JSONAssetsLoader {

	private InputStream is;
	public JSONAssetsLoader(InputStream is) {
		this.is = is;
	}
	public JSONAssetsLoader(AssetManager assets, String name) throws IOException {
		this(assets.open(name));
	}

	public JSONObject read() throws JSONException {
		String json = "";
		Scanner in = new Scanner(is);
		while(in.hasNextLine()){
			json += in.nextLine() + "\n";
		}
		return new JSONObject(json);
	}
}
