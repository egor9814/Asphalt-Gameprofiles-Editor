package ru.egor9814.app.a8gpe2.profiles;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.egor9814.app.a8gpe2.json.JSONArray;
import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class Profile {

	private JSONObject profile;
	private String name;
	public final JSONObject getProfile() {
		return profile;
	}


	public Profile(String name, JSONObject profile) {
		this.name = name;
		this.profile = profile;
	}
	public Profile(String name) {
		this.name = name;
		this.profile = new JSONObject();
	}

	public String getName() {
		return name;
	}

	protected boolean optBoolean(String key, boolean value){
		return profile.optBoolean(key, value);
	}
	protected boolean optBoolean(String key){
		return profile.optBoolean(key);
	}

	protected int optInt(String key, int value){
		return profile.optInt(key, value);
	}
	protected int optInt(String key){
		return profile.optInt(key);
	}

	protected String optString(String key, String value){
		return profile.optString(key, value);
	}
	protected String optString(String key){
		return profile.optString(key);
	}

	protected double optDouble(String key, double value){
		return profile.optDouble(key, value);
	}
	protected double optDouble(String key){
		return profile.optDouble(key);
	}

	protected JSONObject optObject(String key){
		return profile.optJSONObject(key);
	}
	protected JSONObject optObject(String key, JSONObject value){
		JSONObject opt = optObject(key);
		return opt == null ? value : opt;
	}

	protected JSONArray optArray(String key){
		return profile.optJSONArray(key);
	}
	protected JSONArray optArray(String key, JSONArray value){
		JSONArray opt = optArray(key);
		return opt == null ? value : opt;
	}


	public void put(String key, boolean value){
		profile.put(key, value);
	}
	public void put(String key, int value){
		profile.put(key, value);
	}
	public void put(String key, String value){
		profile.put(key, value);
	}
	public void put(String key, double value){
		profile.put(key, value);
	}
	public void put(String key, JSONArray value){
		profile.put(key, value);
	}
	public void put(String key, JSONObject value){
		profile.put(key, value);
	}

	public boolean has(String key){
		return profile.has(key);
	}


	public Object get(String key) throws JSONException {
		return profile.get(key);
	}

	public void remove(String key){
		profile.remove(key);
	}


	public String findKey(String key, String[] keys, String[] showedKeys){
		int position = -1;
		for(int i = 0; i < showedKeys.length; i++){
			if(key.equals(showedKeys[i])){
				position = i;
				break;
			}
		}
		if(position == -1) return null;
		return keys[position];
	}

	public List<String> getEnabledKeys(){
		return new ArrayList<>(profile.keys());
	}


	public enum ValueType{
		STRING, DOUBLE, INT, BOOL, NONE
	}
	public ValueType typeOf(String key){
		try {
			Object value = get(key);
			if(value instanceof String){
				return ValueType.STRING;
			} else if(value instanceof Double){
				return ValueType.DOUBLE;
			} else if(value instanceof Integer){
				return ValueType.INT;
			} else if(value instanceof Boolean){
				return ValueType.BOOL;
			}
		} catch(JSONException e) {}
		return ValueType.NONE;
	}
}
