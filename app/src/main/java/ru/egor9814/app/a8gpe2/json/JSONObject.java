package ru.egor9814.app.a8gpe2.json;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by egor9814 on 12.05.2016.
 */
public class JSONObject {

	private final Map<String, Object> pairs;
	public JSONObject() {
		pairs = new HashMap<>();
	}
	public JSONObject(String source) throws JSONException {
		this();
		parse(source);
	}


	public void put(String key, Object value){
		pairs.put(key, value);
	}

	public Object get(String key){
		return pairs.get(key);
	}

	public boolean has(String key){
		return pairs.containsKey(key);
	}

	public List<String> keys(){
		return new ArrayList<>(pairs.keySet());
	}

	public List<Object> values(){
		return new ArrayList<>(pairs.values());
	}

	public int size(){
		return pairs.size();
	}

	public Object remove(String key){
		return pairs.remove(key);
	}


	public int optInt(String key, int defValue){
		return has(key) ? (int)get(key) : defValue;
	}
	public int optInt(String key){
		return optInt(key, 0);
	}

	public double optDouble(String key, double defValue){
		return has(key) ? (int)get(key) : defValue;
	}
	public double optDouble(String key){
		return optDouble(key, 0);
	}

	public boolean optBoolean(String key, boolean defValue){
		return has(key) ? (boolean)get(key) : defValue;
	}
	public boolean optBoolean(String key){
		return optBoolean(key, false);
	}

	public String optString(String key, String defValue){
		return has(key) ? (String)get(key) : defValue;
	}
	public String optString(String key){
		return optString(key, null);
	}

	public Object optObject(String key, Object defValue){
		return has(key) ? get(key) : defValue;
	}
	public Object optObject(String key){
		return optObject(key, null);
	}

	public JSONArray optJSONArray(String key, JSONArray defValue){
		return has(key) ? (JSONArray)get(key) : defValue;
	}
	public JSONArray optJSONArray(String key){
		return optJSONArray(key, new JSONArray());
	}

	public JSONObject optJSONObject(String key, JSONObject defValue){
		return has(key) ? (JSONObject)get(key) : defValue;
	}
	public JSONObject optJSONObject(String key){
		return optJSONObject(key, new JSONObject());
	}


	@Override
	public String toString() {
		String result = "";
		for(Map.Entry<String, Object> entry : pairs.entrySet()){
			String value = entry.getValue().toString();
			if(entry.getValue() instanceof String) value = "\"" + value + "\"";
			result += "\n,\"" + entry.getKey() + "\": " + value;
		}
		result = result.length() == 0 ? result : result.substring(2);
		Scanner in = new Scanner(new String(result.getBytes()));
		result = "";
		while(in.hasNextLine()){
			result += "\n\t" + in.nextLine();
		}
		return "{" + result + "\n}";
	}

	private void parse(String in) throws JSONException{
		try{
			Lexer lexer = new Lexer(in);
			Parser parser = new Parser(lexer.tokenize());
			Expression expression = parser.parse();
			JSONObject object = (JSONObject)expression.eval();
			for(String key : object.keys()){
				this.put(key, object.get(key));
			}
		} catch(Exception e){
			throw new JSONException(e.getMessage());
		}
	}
}
