package diary;

import java.io.Serializable;

public class DiaryEntry implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
    private String note;

    public DiaryEntry(String date, String note) {
        this.date = date;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Date: " + date + "\nNote: " + note;
    }
}