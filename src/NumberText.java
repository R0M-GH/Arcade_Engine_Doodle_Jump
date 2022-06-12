import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NumberText extends Text {
	private int score;
	private String text;

	public NumberText() {
		score = 0;
		this.setFont(new Font("Georgia", 30));
		updateDisplay();
		text = "Height: ";
	}

	public NumberText(String t) {
		score = 0;
		this.setFont(new Font("Georgia", 30));
		text = t;
		updateDisplay();
	}

	public NumberText(int startingScore, int fontSize) {
		score = startingScore;
		this.setFont(new Font(fontSize));
		updateDisplay();
	}

	public void updateDisplay() {
		this.setText(text + score);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
		updateDisplay();
	}

	public double getHeight() {
		return this.getFont().getSize();
	}
}