package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity { // view?

	private static final String FILENAME = "file.sav"; // model
	private LonelyTwitterActivity activity = this;

	public Button getSaveButton() {
		return saveButton;
	}

	private Button saveButton;

	public EditText getBodyText() {
		return bodyText;
	}

	private EditText bodyText; // model
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); // model

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	private ListView oldTweetsList; // model
	private ArrayAdapter<Tweet> adapter; // model(/view?)

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { // controller

		super.onCreate(savedInstanceState); // model
		setContentView(R.layout.main); // view

		bodyText = (EditText) findViewById(R.id.body); // view
		saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear); // view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); // view

		saveButton.setOnClickListener(new View.OnClickListener() { // controller

			public void onClick(View v) { // controller
				setResult(RESULT_OK); // controller
				String text = bodyText.getText().toString(); // model
				tweets.add(new NormalTweet(text)); // model
				saveInFile(); // model
				adapter.notifyDataSetChanged(); // view
			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() { // controller

			public void onClick(View v) { // controller
				setResult(RESULT_OK); // controller
				bodyText.setText(""); // model
				tweets.clear(); // model
				saveInFile(); // model
				adapter.notifyDataSetChanged(); // view
			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() { // controller
		// TODO Auto-generated method stub
		super.onStart(); // model
		loadFromFile(); // model
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets); // model
		oldTweetsList.setAdapter(adapter); // view
		adapter.notifyDataSetChanged(); // view
	}

	private void loadFromFile() { // model
		try {
			FileInputStream fis = openFileInput(FILENAME); // model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); // model
			Gson gson = new Gson(); // model
			// https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 2015-09-23
			Type arraylistType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); // model
			tweets = gson.fromJson(in, arraylistType); // model

		} catch (FileNotFoundException e) {
			tweets = new ArrayList<Tweet>(); // model
		} catch (IOException e) {
			throw new RuntimeException(e); // view?
		}
		//return tweets;
	}
	
	private void saveInFile() { // model
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0); // model
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos)); // model
			Gson gson = new Gson(); // model
			gson.toJson(tweets, out); // model
			out.flush(); // model
			fos.close(); // model
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); // view?
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); // view?
		}
	}
}