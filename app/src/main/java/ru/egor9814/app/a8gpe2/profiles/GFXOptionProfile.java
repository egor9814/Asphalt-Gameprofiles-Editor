package ru.egor9814.app.a8gpe2.profiles;


import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class GFXOptionProfile extends Profile {

	public GFXOptionProfile(String name, JSONObject profile) {
		super(name, profile);
	}
	public GFXOptionProfile(String name) {
		super(name);
	}

	/**private void parse(String type) {
		switch(type){
			case "VERYLOW":
				useShadows(false);
				postFX(false);
				postFXLUTOnly(false);
				useCustomPFX(false);
				useCheapCustomPFX(true);
				useCheapColorCorrection(false);
				useVertexFresnel(true);
				cutoffDistanceOverride(800);
				useCarDirt(false);
				useFog(false);
				useRoadReflection(false);
				useMotionBlur(false);
				useLensflare(false);
				useQualityRoadReflection(false);
				useGlassCrackPFX(false);
				fullScreenBlurQuality(1);
				break;
			case "LOW":
				useShadows(false);
				postFX(false);
				postFXLUTOnly(false);
				useCustomPFX(false);
				useCheapCustomPFX(true);
				useCheapColorCorrection(true);
				useVertexFresnel(true);
				cutoffDistanceOverride(800);
				useCarDirt(true);
				useFog(true);
				useRoadReflection(false);
				useMotionBlur(false);
				useLensflare(true);
				useQualityRoadReflection(false);
				useGlassCrackPFX(false);
				fullScreenBlurQuality(1);
				break;
			case "MEDIUM":
				useShadows(true);
				postFX(true);
				postFXLUTOnly(true);
				useCustomPFX(true);
				useCheapCustomPFX(true);
				useCheapColorCorrection(true);
				useVertexFresnel(false);
				cutoffDistanceOverride(0);
				useCarDirt(true);
				useFog(true);
				useRoadReflection(true);
				useMotionBlur(false);
				useLensflare(true);
				useQualityRoadReflection(false);
				useGlassCrackPFX(false);
				fullScreenBlurQuality(1);
				break;
			case "HIGH":
				useShadows(true);
				postFX(true);
				postFXLUTOnly(false);
				useCustomPFX(true);
				useCheapCustomPFX(true);
				useCheapColorCorrection(false);
				useVertexFresnel(false);
				cutoffDistanceOverride(0);
				useCarDirt(true);
				useFog(true);
				useRoadReflection(true);
				useMotionBlur(true);
				useLensflare(true);
				useQualityRoadReflection(true);
				useGlassCrackPFX(true);
				fullScreenBlurQuality(1);
				break;
		}
	}*/


	public boolean postFX() {
		return optBoolean("postFX", true);
	}

	public boolean postFXLUTOnly(){
		return optBoolean("postFXLUTOnly", true);
	}

	public boolean useShadows() {
		return optBoolean("useShadows", false);
	}

	public boolean useCheapCustomPFX(){
		return optBoolean("useCheapCustomPFX", true);
	}

	public boolean useCheapColorCorrection(){
		return optBoolean("useCheapColorCorrection", false);
	}

	public boolean useVertexFresnel(){
		return optBoolean("useVertexFresnel", true);
	}

	public boolean useCarDirt(){
		return optBoolean("useCarDirt", false);
	}

	public boolean useFog(){
		return optBoolean("useFog", false);
	}

	public boolean useRoadReflection(){
		return optBoolean("useRoadReflection", false);
	}

	public boolean useMotionBlur(){
		return optBoolean("useMotionBlur", false);
	}

	public boolean useLensflare(){
		return optBoolean("useLensflare", false);
	}

	public boolean useQualityRoadReflection(){
		return optBoolean("useQualityRoadReflection", false);
	}

	public boolean useGlassCrackPFX(){
		return optBoolean("useGlassCrackPFX", false);
	}

	public boolean useCustomPFX(){
		return optBoolean("useCustomPFX", false);
	}

	public int cutoffDistanceOverride(){
		return optInt("cutoffDistanceOverride", 800);
	}

	public int fullScreenBlurQuality(){
		return optInt("fullScreenBlurQuality", 1);
	}


	public void postFX(boolean value){
		put("postFX", value);
	}

	public void postFXLUTOnly(boolean value){
		put("postFXLUTOnly", value);
	}

	public void useShadows(boolean value){
		put("useShadows", value);
	}

	public void useCheapCustomPFX(boolean value){
		put("useCheapCustomPFX", value);
	}

	public void useCheapColorCorrection(boolean value){
		put("useCheapColorCorrection", value);
	}

	public void useVertexFresnel(boolean value){
		put("useVertexFresnel", value);
	}

	public void useCarDirt(boolean value){
		put("useCarDirt", value);
	}

	public void useFog(boolean value){
		put("useFog", value);
	}

	public void useRoadReflection(boolean value){
		put("useRoadReflection", value);
	}

	public void useMotionBlur(boolean value){
		put("useMotionBlur", value);
	}

	public void useLensflare(boolean value){
		put("useLensflare", value);
	}

	public void useQualityRoadReflection(boolean value){
		put("useQualityRoadReflection", value);
	}

	public void useGlassCrackPFX(boolean value){
		put("useGlassCrackPFX", value);
	}

	public void useCustomPFX(boolean value){
		put("useCustomPFX", value);
	}

	public void cutoffDistanceOverride(int value){
		put("cutoffDistanceOverride", value);
	}

	public void fullScreenBlurQuality(int value){
		put("fullScreenBlurQuality", value);
	}

}
