package net.neferett.epitech.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	private final ArrayList<String>	allFile	= new ArrayList<>();
	private final BufferedReader	br;
	private final String			file;

	public ReadFile(final String path) throws IOException {
		this.file = path;
		this.br = this.Open();
		this.Read();
	}

	public void Close() throws IOException {
		this.br.close();
	}

	public ArrayList<String> getFile() {
		return this.allFile;
	}

	public BufferedReader Open() throws FileNotFoundException {
		return new BufferedReader(new FileReader(this.file));
	}

	public void Read() throws IOException {
		String str;

		while ((str = this.br.readLine()) != null)
			this.allFile.add(str);
	}

}