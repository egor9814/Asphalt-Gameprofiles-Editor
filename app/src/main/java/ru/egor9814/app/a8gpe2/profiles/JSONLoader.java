package ru.egor9814.app.a8gpe2.profiles;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import ru.egor9814.app.a8gpe2.json.JSONObject;

/**
 * Created by egor9814 on 02.05.2016.
 */
public class JSONLoader {

	private RandomAccessFile randomAccessFile;

	public JSONLoader(File file) throws FileNotFoundException {
		randomAccessFile = new RandomAccessFile(file, "r");
	}
	public JSONLoader(String path) throws FileNotFoundException {
		randomAccessFile = new RandomAccessFile(path, "r");
	}

	public JSONObject read() throws IOException, JSONException {
		if(randomAccessFile == null) throw new IOException("invalid file");
		byte[] array = null;
		if(randomAccessFile.length() > 0){
			array = new byte[(int)randomAccessFile.length()];
			randomAccessFile.readFully(array);
		} else {
			throw new IOException("invalid file length");
		}
		return new JSONObject(new String(array));
	}

	public void close() throws IOException {
		randomAccessFile.close();
	}

}
