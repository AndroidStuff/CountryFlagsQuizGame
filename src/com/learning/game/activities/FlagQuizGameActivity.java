package com.learning.game.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.flagquizgame.R;
import com.learning.game.models.FlagQuizGame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class FlagQuizGameActivity extends Activity {

	private static final String TAG = "FlagQuizGameActivity";
	private FlagQuizGame flagQuizGame;
	private Handler handler;
	private final OnDismissListener onDismissListener = new OnDismissListener() {
		@Override
		public void onDismiss(DialogInterface dialog) {
			flagQuizGame.resetQuiz();
			loadNextQuestion();
		}
	};
	private OnClickListener answerClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			submitAnswer((Button) v);
		}
	};

	@Bind(R.id.resultTextView) TextView resultBox;
	@Bind(R.id.buttonTableLayout) TableLayout buttonTableLayout;
	@Bind(R.id.questionNumberTextView) TextView questionNumberTextView;
	@Bind(R.id.flagImageView) ImageView flagImageView;
	@BindColor(R.color.incorrect_answer) int COLOR_INCORRECT_ANSWER;
	@BindColor(R.color.correct_answer) int COLOR_CORRECT_ANSWER;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flag_quiz_game);
		ButterKnife.setDebug(true);
		ButterKnife.bind(this);

		if(savedInstanceState != null) {
			flagQuizGame.setCurrentQuestionNumber(savedInstanceState.getInt("questionNumber"));
		}

		handler = new Handler();
		flagQuizGame = new FlagQuizGame(getApplicationContext(), 0, 0, new ArrayList<String>(), new ArrayList<String>());
		flagQuizGame.resetQuiz();
		loadNextQuestion();
	}

	private void loadNextQuestion() {
		clearResultBox();
		flagQuizGame.nextImage();
		removeOldAnswerOptionButtons();
		addNewAnswerOptionButtons();
		loadFlag(flagQuizGame.getCurrentImage());

		incrementQuestionNumberAndUpdateTitle();
	}

	private void addNewAnswerOptionButtons() {
		List<String> shuffledCountryNames = flagQuizGame.answerOptions();
		Log.i("ANSWER_OPTIONS", shuffledCountryNames.get(0) + "," + shuffledCountryNames.get(1) + ","  + shuffledCountryNames.get(2));

		TableRow row = (TableRow) buttonTableLayout.getChildAt(0);
		row.addView(newOptionButton(shuffledCountryNames.get(0)));
		row.addView(newOptionButton(shuffledCountryNames.get(1)));
		row.addView(newOptionButton(shuffledCountryNames.get(2)));
	}

	private Button newOptionButton(String text) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Button optionButton1 = (Button) inflater.inflate(R.layout.option_button, null);
		optionButton1.setText(text);
		optionButton1.setOnClickListener(answerClickListener);
		return optionButton1;
	}

	private void submitAnswer(Button submittedAnswerButton) {
		String guess = submittedAnswerButton.getText().toString();
		removeClickablilityFromAllAnswerOptionButtons();
		submittedAnswerButton.setEnabled(false);
		if (guess.equals(flagQuizGame.correctAnswer())) {
			flagQuizGame.incrementCorrectAnswersCount();
			displayResultAsCorrect();
		}else {
			displayResultAsWrong();
		}
		if(flagQuizGame.getCurrentQuestionNumber() == 10) {
			alertDialog();
			return;
		}
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				loadNextQuestion();
			}
		}, 1000);
	}

	private void alertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(FlagQuizGameActivity.this);
		builder.setTitle("Final Score");
		builder.setMessage("Your got " + flagQuizGame.getCorrectAnswersCount() + " right out of 10.");

		AlertDialog dialog = builder.create();
		dialog.setOnDismissListener(onDismissListener);
		dialog.show();
	}

	private void displayResultAsWrong() {
		resultBox.setText("Wrong!");
		resultBox.setTextColor(COLOR_INCORRECT_ANSWER);
	}

	private void displayResultAsCorrect() {
		resultBox.setText("Correct!");
		resultBox.setTextColor(COLOR_CORRECT_ANSWER);
	}

	private void removeOldAnswerOptionButtons() {
		for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
			((TableRow) buttonTableLayout.getChildAt(row)).removeAllViews();
	}

	private void removeClickablilityFromAllAnswerOptionButtons() {
		for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
			((TableRow) buttonTableLayout.getChildAt(row)).setClickable(false);
	}

	private void incrementQuestionNumberAndUpdateTitle() {
		flagQuizGame.incrementCurrentQuestionNmber();
		questionNumberTextView.setText("Question " + flagQuizGame.getCurrentQuestionNumber() + "of 10.");
	}

	private void loadFlag(String nextImage) {
		String imagePath = "asia/" +nextImage + ".png";
		try {
			getAssets().open(imagePath);
			flagImageView.setImageDrawable(Drawable.createFromStream(getAssets().open(imagePath),imagePath));
		} catch (IOException e) {
			Log.e(TAG, "Error loading " + imagePath, e);
		}
	}

	private void clearResultBox() {
		resultBox.setText("");
	}

}
