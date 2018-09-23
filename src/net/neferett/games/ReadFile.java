package net.neferett.games;

import java.util.*;
import java.io.*;

public class ReadFile
{
    private final ArrayList<String> allFile;
    private final BufferedReader br;
    private final String file;
    
    public ReadFile(final String path) throws IOException {
        this.allFile = new ArrayList<String>();
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
        while ((str = this.br.readLine()) != null) {
            this.allFile.add(str);
        }
    }
}
