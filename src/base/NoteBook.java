package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Comparable<NoteBook>, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			this.folders = n.folders;
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders() {
		return this.folders;
	}
	
	private boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1 : this.folders) {
			if (f1.equals(new Folder(folderName))) {
				f = f1;
				break;
			}
		}
		
		if (f == null) {
			f = new Folder(folderName);
			this.folders.add(f);
		}
		
		for (Note n : f.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
	}
	
	public void sortFolders() {
		for (Folder folder : this.folders) {
			folder.sortNotes();
		}
		Collections.sort(this.folders);
	}

	@Override
	public int compareTo(NoteBook arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	
	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		for (Folder folder : this.folders) {
			result.addAll(folder.searchNotes(keywords));
		}
		
		return result;
	}
}
