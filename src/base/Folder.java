package base;

import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		this.notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		this.notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Note> getNotes() {
		return this.notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (int i = 0; i < this.notes.size(); i++) {
			Note note = this.notes.get(i);
			if (note instanceof TextNote) {
				nText++;
			} else if (note instanceof ImageNote) {
				nImage++;
			}
		}
		
		return this.name + ":" + nText + ":" + nImage;
	}
}