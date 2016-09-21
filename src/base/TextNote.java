package base;

public class TextNote extends Note {
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public String getContent() {
		return this.content;
	}
}
