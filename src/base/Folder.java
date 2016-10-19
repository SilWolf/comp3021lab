package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, java.io.Serializable{
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
	
	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(this.notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		String[] keywordsArray = keywords.toLowerCase().split(" ");
		
		ArrayList<ArrayList<String>> keywordsGroup = new ArrayList<ArrayList<String>>();
		int start = 0;
		int end = 0;
		while (start < keywordsArray.length && end <= keywordsArray.length) {
			if (keywordsArray[start].equals("or")) {
				start++;
				end = start;
			} else if (end < keywordsArray.length && !keywordsArray[end].equals("or")) {
				end++;
			} else {
				ArrayList<String> tempKeywords = new ArrayList<String>();
				for (int i = start; i < end; i++) {
					tempKeywords.add(keywordsArray[i]);
				}
				keywordsGroup.add(tempKeywords);
				start = end + 1;
				end = start;
			}
		}
		
		for (ArrayList<String> words : keywordsGroup) {
			System.out.println(Arrays.toString(words.toArray()));
		}
		
		
		List<Note> result = new ArrayList<Note>();
		for (Note note : this.notes) {
			for (ArrayList<String> tempKeywords : keywordsGroup) {
				boolean contain = true;
				if (note instanceof ImageNote) {
					ImageNote imageNote = (ImageNote) note;

					for (String keyword : tempKeywords) {
						if (!imageNote.getTitle().toLowerCase().contains(keyword)) {
							contain = false;
							break;
						}
					}
				} else if (note instanceof TextNote) {
					TextNote textNote = (TextNote) note;
					
					for (String keyword : tempKeywords) {
						if (!textNote.getTitle().toLowerCase().contains(keyword) && !textNote.getContent().toLowerCase().contains(keyword)) {
							contain = false;
							break;
						}
					}
				}
				if (contain) {
					result.add(note);
					break;
				}
			}
		}
		
		return result;
	}
}
