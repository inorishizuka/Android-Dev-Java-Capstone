package com.example.itcapstone.burglarkitten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "burglarkitten";
	// tasks table name
	private static final String TABLE_QUEST = "kitten";
	// tasks Table Columns names
	private static final String KEY_ID = "qid";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; // correct option
	private static final String KEY_OPTA = "opta"; // option a
	private static final String KEY_OPTB = "optb"; // option b
	private static final String KEY_OPTC = "optc"; // option c

	private SQLiteDatabase dbase;

	public QuizHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase = db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
				+ KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
		db.execSQL(sql);
		addQuestion();
		// db.close();
	}

	private void addQuestion() {
		Question q1 = new Question("What is our mascot's fur color?", "Purple", "Green", "Orange", "Orange");
		this.addQuestion(q1);
		Question q2 = new Question("Cats have been known to survive falls of up to how many feet?", "350FT", "560FT", "150FT", "350FT");
		this.addQuestion(q2);
		Question q3 = new Question("A group of cats is called what?", "Pack", "Clowder", "Gang", "Clowder");
		this.addQuestion(q3);
		Question q4 = new Question("Cats sleep what percentage of their lives?", "55%", "25%", "70%", "70%");
		this.addQuestion(q4);
		Question q5 = new Question("A Cat can jump up to X times is length: ", "Nine", "Twelve", "Six", "Six");
		this.addQuestion(q5);
		Question q6 = new Question("When a cat died in ancient Egypt, people would shave what?", "Beards", "Eye Lashes", "Eyebrows", "Eyebrows");
		this.addQuestion(q6);
		Question q7 = new Question("Cats Can't Taste what?", "Bitterness", "Sourness", "Sweetness", "Sweetness");
		this.addQuestion(q7);
		Question q8 = new Question("Owning a cat reduces risk of stroke & heart attack by how much?", "Third", "Half", "Tenth", "Third");
		this.addQuestion(q8);
		Question q9 = new Question("Blue eyed cats have a tendency to be?", "Clumsy", "Deaf", "Colorblind", "Deaf");
		this.addQuestion(q9);
		Question q10 = new Question("What do cats have a strong aversion to?", "Cucumbers", "Citrus", "Bananas", "Citrus");
		this.addQuestion(q10);
		Question q11 = new Question("Which of these is important to a cats nutrition?", "Fat", "Calcium", "Protein", "Fat");
		this.addQuestion(q11);
		Question q12 = new Question("A Cat's hairball is called what?", "Scruffletuff", "Bezoar", "Hackle", "Bezoar");
		this.addQuestion(q12);
		Question q13 = new Question("The worlds most fertile cat gave birth to how many kittens?", "206", "420", "186", "420");
		this.addQuestion(q13);
		Question q14 = new Question("Cats have five toes on the front legs, how many in the hind legs?", "Three", "Five", "Four", "Four");
		this.addQuestion(q14);
		Question q15 = new Question("A cat can make an average of how many sounds?", "100", "24", "16", "100");
		this.addQuestion(q15);
		Question q16 = new Question("A cats pregnancy typically lasts how long?", "9 Weeks", "24 Weeks", "36 Weeks", "9 Weeks");
		this.addQuestion(q16);
		Question q17 = new Question("A female cat is also known to be called what?", "Diva", "Molly", "Foley", "Molly");
		this.addQuestion(q17);
		Question q18 = new Question("Cats sweat through what part of their body?", "Ears", "Paws", "Lips", "Paws");
		this.addQuestion(q18);
		Question q19 = new Question("In Seven Years a pair of cats and their offspring can produce how many kittens?", "400", "40000", "400000", "400000");
		this.addQuestion(q19);
		Question q20 = new Question("The richest cat in the world is heir to how much money?", "$16,600,000", "$2,400,000", "$72,000,000", "$16,600,000");
		this.addQuestion(q20);
		Question q21 = new Question("How many degrees can a cat move its ears?", "45", "90", "180", "180");
		this.addQuestion(q21);
		// END
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}

	// Adding new question
	public void addQuestion(Question quest) {
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());

		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase = this.getReadableDatabase();
		// Random pull through database for random questions.
		Cursor cursor = dbase.rawQuery(selectQuery + " ORDER BY RANDOM()", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));

				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}


}
