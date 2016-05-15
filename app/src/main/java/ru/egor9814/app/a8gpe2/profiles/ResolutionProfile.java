package ru.egor9814.app.a8gpe2.profiles;


import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class ResolutionProfile extends Profile {

	public ResolutionProfile(String name, JSONObject profile) {
		super(name, profile);
	}
	public ResolutionProfile(String name) {
		super(name);
	}

	public int scaleDisplay(){
		return optInt("scaleDisplay", 85);
	}

	public int scaleAABuffer(){
		return optInt("scaleAABuffer", -1);
	}

	public boolean useAAInGameplay(){
		return optBoolean("useAAInGameplay");
	}

	public boolean useAAInMenu(){
		return optBoolean("useAAInMenu");
	}

	public boolean useAA(){
		return optBoolean("useAA");
	}


	public void scaleDisplay(int value){
		put("scaleDisplay", value);
	}

	public void scaleAABuffer(int value){
		put("scaleAABuffer", value);
	}

	public void useAAInGameplay(boolean value){
		put("useAAInGameplay", value);
	}

	public void useAAInMenu(boolean value){
		put("useAAInMenu", value);
	}

	public void useAA(boolean value){
		put("useAA", value);
		put("useAAInGameplay", value);
		put("useAAInMenu", value);
	}

	public void compile(){
		if(getResolutions().initialized()){
			put("resolutions", getResolutions().getProfile());
		} else if(has("resolutions")){
			remove("resolutions");
		}
	}

	private Resolutions resolutions;
	public Resolutions getResolutions() {
		if(resolutions == null){
			JSONObject res = optObject("resolutions");
			if(res == null){
				resolutions = new Resolutions();
			} else {
				resolutions = new Resolutions(res);
			}
		}
		return resolutions;
	}

	public class Resolutions extends Profile {

		Resolutions(JSONObject profile) {
			super(null, profile);
		}
		Resolutions() {
			super(null, new JSONObject());
		}

		public double getMaxDII() {
			return optDouble("maxDII", 8);
		}

		public void setMaxDII(double maxDII) {
			put("maxDII", maxDII);
		}

		public double getMinDII() {
			return optDouble("minDII", 4);
		}

		public void setMinDII(double minDII) {
			put("minDII", minDII);
		}

		public int getMinW() {
			return optInt("minW", 1100);
		}

		public void setMinW(int minW) {
			put("minW", minW);
		}

		public int getMaxW() {
			return optInt("maxW", 1920);
		}

		public void setMaxW(int maxW) {
			put("maxW", maxW);
		}

		public int getMinH() {
			return optInt("minH", 700);
		}

		public void setMinH(int minH) {
			put("minH", minH);
		}

		public int getMaxH() {
			return optInt("maxH", 1080);
		}

		public void setMaxH(int maxH) {
			put("maxH", maxH);
		}

		public boolean initialized(){
			return has("maxDII") || has("maxW") || has("maxH") ||
					has("minDII") || has("minW") || has("minH");
		}
	}
}