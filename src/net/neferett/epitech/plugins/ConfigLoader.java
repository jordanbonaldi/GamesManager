package net.neferett.epitech.plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.neferett.epitech.utils.json.JSONException;
import net.neferett.epitech.utils.json.JSONObject;

public class ConfigLoader {
	
	private String config;
	
	private String path;
	
	private File file;
		
	private FileReader fr;
	
	private BufferedReader reader;
	
	private JSONObject object;
	
	private String filecontent;
	
	public ConfigLoader(String path, String configname) throws IOException, JSONException {
		this.path = path;
		this.config = configname;
		
		this.file = new File(this.path + "/" + this.config);
		
		this.read();
		
		this.build();
	}
	
	private void read() throws IOException {
		this.fr = new FileReader(this.file);
		
		this.reader = new BufferedReader(this.fr);
		
		StringBuilder sb = new StringBuilder();
		
		String s = null;
		
		while ((s = this.reader.readLine()) != null) {
			sb.append(s);
		}
		
		this.filecontent = sb.toString();
	}
	
	private void build() throws JSONException {
		this.object = new JSONObject(this.filecontent);
	}
	
	public String getPackage() throws JSONException {
		return this.object.getString("package");
	}

}
