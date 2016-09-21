package base;

import java.util.ArrayList;
import java.util.Collections;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
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
		Collections.sort(this.folders);
	}
}
