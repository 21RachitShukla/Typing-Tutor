package TypingTutor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {
	JLabel time;
	JLabel score;
	JLabel word;
	JTextField txtWord;
	JButton start;
	JButton stop;
	Timer timer;
	int timeRemaining;
	int Score;
	boolean isRunning;
	String[] Word;

	public TypingTutor(String[] Word) {
		this.Word = Word;
		GridLayout layout = new GridLayout(3, 2);
		Font font = new Font("Comic Sans MS", 1, 150);
		super.setLayout(layout);
		super.setTitle("Typing Tutor");
		time = new JLabel("Time: ");
		time.setForeground(Color.white);
		time.setBorder(BorderFactory.createEtchedBorder(Color.gray, Color.white));
		time.setOpaque(true);
		time.setBackground(Color.black);
		time.setFont(font);
		super.add(time);
		score = new JLabel("Score: ");
		score.setForeground(Color.white);
		score.setBorder(BorderFactory.createEtchedBorder(Color.gray, Color.white));
		score.setOpaque(true);
		score.setBackground(Color.black);
		score.setFont(font);
		super.add(score);
		word = new JLabel();
		word.setBorder(BorderFactory.createLineBorder(Color.black));
		word.setOpaque(true);
		word.setBackground(Color.white);
		word.setFont(font);
		super.add(word);
		txtWord = new JTextField();
		txtWord.setBorder(BorderFactory.createLineBorder(Color.black));
		txtWord.setOpaque(true);
		txtWord.setBackground(Color.white);
		txtWord.setFont(font);
		super.add(txtWord);
		start = new JButton("Start");
		start.setBorder(BorderFactory.createRaisedBevelBorder());
		start.setFont(font);
		start.addActionListener(this);
		super.add(start);
		stop = new JButton("Stop");
		stop.setBorder(BorderFactory.createRaisedBevelBorder());
		stop.setFont(font);
		stop.addActionListener(this);
		super.add(stop);
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		super.setVisible(true);

		setUpGame();
	}

	private void setUpGame() {
		timer = new Timer(1000, this);
		timeRemaining = 6;
		Score = 0;
		isRunning = false;

		time.setText("Time: ");
		score.setText("Score: ");
		word.setText("");
		txtWord.setText("");
		txtWord.setEnabled(false);
		start.setText("Start");
		stop.setText("Stop");
		stop.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start)
			handleStart();
		else if (e.getSource() == stop)
			handleStop();
		else
			handleTimer();
	}

	private void handleTimer() {
		timeRemaining--;
		if (word.getText().equals(txtWord.getText()) && word.getText().length() > 0)
			Score++;
		score.setText("Score: " + Score);
		if (timeRemaining < 0) {
			handleStop();
			return;
		}
		time.setText("Time: " + timeRemaining);
		int idx = (int) (Math.random() * Word.length);
		word.setText(Word[idx]);

	}

	private void handleStop() {
		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again? ");
		if (choice == JOptionPane.YES_OPTION)
			setUpGame();
		else if (choice == JOptionPane.NO_OPTION)
			super.dispose();
		else {
			if (timeRemaining < 0)
				setUpGame();
			else
				timer.start();
		}
	}

	private void handleStart() {
		if (isRunning == false) {
			timer.start();
			txtWord.setEnabled(true);
			start.setText("Pause");
			stop.setEnabled(true);
			isRunning = true;
		} else {
			timer.stop();
			txtWord.setEnabled(false);
			start.setText("Resume");
			isRunning = false;
		}
	}
}
