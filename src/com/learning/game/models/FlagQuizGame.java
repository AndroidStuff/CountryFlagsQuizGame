package com.learning.game.models;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class FlagQuizGame {
	private static final String TAG = "FlagQuizGame";
	private Context appContext;
	private int correctAnswersCount;
	private String currentImage;
	private int currentQuestionNumber;
	private List<String> flagImageNameList;
	private String[] imageFilePaths;
	private List<String> quizQuestionsList;

	public FlagQuizGame(Context context,
			int questionNumber,
			int numberOfCorrectAnswers,
			List<String> flagImageNameList,
			List<String> quizQuestionsList) {
		this.appContext = context.getApplicationContext();
		this.currentQuestionNumber = questionNumber;
		this.correctAnswersCount = numberOfCorrectAnswers;
		this.flagImageNameList = flagImageNameList;
		this.quizQuestionsList = quizQuestionsList;
	}

	public List<String> answerOptions() {
		List<String> shuffledCountryNames =
				Arrays.asList(new String[] {correctAnswer(), pickIncorrectCountryName(), pickIncorrectCountryName()});
		Log.d("ANSWER_OPTIONS", shuffledCountryNames.get(0) + "," + shuffledCountryNames.get(1) + ","  + shuffledCountryNames.get(2));
		Collections.shuffle(shuffledCountryNames);
		return shuffledCountryNames;
	}

	public String correctAnswer() {
		return deriveCountryName(currentImage);
	}

	public String deriveCountryName(final String nextImage) {
		return nextImage.substring(nextImage.indexOf("-")+1);
	}

	public int getCorrectAnswersCount() {
		return correctAnswersCount;
	}

	public String getCurrentImage() {
		return currentImage;
	}

	public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}

	public List<String> getFlagImageNameList() {
		return flagImageNameList;
	}

	public String[] getImageFilePaths() {
		return imageFilePaths;
	}

	public List<String> getQuizQuestionsList() {
		return quizQuestionsList;
	}

	public void incrementCorrectAnswersCount() {
		correctAnswersCount++;
	}

	public void incrementCurrentQuestionNmber() {
		currentQuestionNumber++;
	}

	public String nextImage() {
		currentImage = quizQuestionsList.remove(0);
		Log.d("currentImage", currentImage);
		return currentImage;
	}

	public void resetQuiz() {
		correctAnswersCount = 0;
		currentQuestionNumber = 0;
		reLoadFlagImageNameList();
		reloadQuizQuestions();
	}

	public void setCurrentQuestionNumber(int questionNumber) {
		this.currentQuestionNumber = questionNumber;
	}

	private String pickIncorrectCountryName() {
		String countryName = null;
		while(true) {
			countryName = deriveCountryName(randomFlag());
			if(! correctAnswer().equalsIgnoreCase(countryName)) break;
		}
		return countryName;
	}

	private String randomFlag() {
		Random random = new Random();
		int flagIndex = random.nextInt(flagImageNameList.size());
		return flagImageNameList.get(flagIndex);
	}

	private void reLoadFlagImageNameList() {
		flagImageNameList.clear();

		final AssetManager assets = appContext.getAssets();
		imageFilePaths = fetchImageFilePaths(assets);
		addToFlagImageNames(imageFilePaths);
	}

	private void reloadQuizQuestions() {
		final int MAX_QUESTIONS = 10;
		for(int i=0; i< MAX_QUESTIONS;) {
			final String flagName = randomFlag();
			if(! quizQuestionsList.contains(flagName)) {
				quizQuestionsList.add(flagName);
				i++;
			}
		}
	}

	private void addToFlagImageNames(String[] imageFilePaths) {
		for (String path : imageFilePaths) {
			flagImageNameList.add(path.replace(".png", ""));
		}
	}

	private String[] fetchImageFilePaths(final AssetManager assets) {
		try {
			return assets.list("asia");
		} catch (IOException e) {
			Log.e(TAG, "Error loading image file names ", e);
			return new String[0];
		}
	}

}