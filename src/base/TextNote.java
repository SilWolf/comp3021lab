package base;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextNote extends Note {
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public String getContent() {
		return this.content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		
		try {
			result = new String(Files.readAllBytes(Paths.get(absolutePath))).replaceAll(" ", "_");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		try {
			String fullPath = ".\\" + pathFolder + File.separator + this.getTitle() + ".txt";
			fullPath = fullPath.replaceAll(" ", "_");
			Files.write(Paths.get(fullPath), this.content.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
