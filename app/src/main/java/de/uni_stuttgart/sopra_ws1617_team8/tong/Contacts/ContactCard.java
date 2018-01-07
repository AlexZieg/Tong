package de.uni_stuttgart.sopra_ws1617_team8.tong.Contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter.DatabaseAdapter;
import de.uni_stuttgart.sopra_ws1617_team8.tong.EditData;
import de.uni_stuttgart.sopra_ws1617_team8.tong.R;


/**
 * Class for the AudioEditor which Contains the Method Pitch, IncreaseVolume, SlowDown, RateAudioFile and Anti disturbance
 */
public class ContactCard extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    // TextViews
    private TextView txtGivenName, txtSurName, txtRegion, txtCompany, txtTitle, pRatingDetail;
    // RatingBar
    private RatingBar pRatingBar;
    // Buttons
    ImageButton bPlay, bPause, bStop, bDelete, bSuggestion, bEdit;
    // MediaPlayer
    private MediaPlayer mPlayer;
    private TextToSpeech tts;


    //Datenbank
    DatabaseAdapter db;

    /**
     * OnCreate Method which starts the activity
     *
     * @param savedInstanceState .
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.large_personview_detail);

        //Enable the Home Button so that we can move back
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        openDB();
        initializeViews();
        setPersonData();
        // Important for API Level > 23
        setupPermission();
    }

    private void openDB() {
        db = new DatabaseAdapter(this);
        db.open();
    }

    /**
     * Method to shutdown all the Streams (Database, TTS, eg.)
     */
    public void onDestroy() {
        db.close();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            super.onDestroy();
        }
    }

    /**
     * Method to check if the User has given the permission to read/write to the
     * external storage
     */
    private void setupPermission() {
        // Check for permissions
        int permissionRW = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        // If we don't have permissions, ask user for permissions
        if (permissionRW != PackageManager.PERMISSION_GRANTED) {
            String[] PERMISSIONS_STORAGE = {
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            int REQUEST_EXTERNAL_STORAGE = 1;

            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    /**
     * Method to get the specific file path to the audio file
     *
     * @return the String where the audio file is saved
     */
    private String getFilePath() {
        Bundle extra = getIntent().getExtras();
        return extra.getString("filePath");
    }

    /**
     * Method to get the specific PID
     *
     * @return the PID String
     */
    private String getPID() {
        Bundle extra = getIntent().getExtras();
        return extra.getString("PID");
    }

    /**
     * Method which prepares the MediaPlayer
     */
    private void setupMediaPlayer() {
        // prepare MediaPlayer
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            if (getFilePath() != null) {
                mPlayer.setDataSource(getFilePath());
            }
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer ep) {
                    mPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize all the needed views
     */
    private void initializeViews() {
        // TextViews
        txtTitle = (TextView) findViewById(R.id.textTitle);
        txtGivenName = (TextView) findViewById(R.id.textGivenName);
        txtSurName = (TextView) findViewById(R.id.textSurName);
        txtRegion = (TextView) findViewById(R.id.textRegion);
        txtCompany = (TextView) findViewById(R.id.textCompany);
        pRatingDetail = (TextView) findViewById(R.id.personRatingDetail);

        pRatingBar = (RatingBar) findViewById(R.id.personRating);

        // Buttons
        bPlay = (ImageButton) findViewById(R.id.iBPlayButton);
        bPause = (ImageButton) findViewById(R.id.iBPauseButton);
        bEdit = (ImageButton) findViewById(R.id.iBEdit);
        bDelete = (ImageButton) findViewById(R.id.iBDelete);
        bStop = (ImageButton) findViewById(R.id.iBStopButton);
        bSuggestion = (ImageButton) findViewById(R.id.iBSuggest);

        bPlay.setOnClickListener(this);
        bPause.setOnClickListener(this);
        bEdit.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bStop.setOnClickListener(this);
        bSuggestion.setOnClickListener(this);

        // Disable Button for the initialize
        bPause.setAlpha(0.4f);
        bPause.setEnabled(false);
        bStop.setAlpha(0.4f);
        bStop.setEnabled(false);

        //Media
        tts = new TextToSpeech(this, this);
    }

    /**
     * Method to display the Data into the Contact Card
     */
    private void setPersonData() {
        Bundle extra = getIntent().getExtras();
        txtTitle.setText(extra.getString("title"));
        txtSurName.setText(extra.getString("surname"));
        txtGivenName.setText(extra.getString("givenname"));
        txtRegion.setText(extra.getString("region"));
        txtCompany.setText(extra.getString("company"));

        float rating = extra.getFloat("Rating");
        pRatingBar.setRating(rating);
        pRatingDetail.setText("(" + rating + ")");
    }

    /**
     * Method to update the Data on the RecyclerView List each Time a
     * View on the Fragment has changed
     */
    public void onResume() {
        super.onResume();
        setPersonData();
    }


    /**
     * Method which handles the clicks on the Button
     *
     * @param view of the Button which is clicked
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iBPlayButton:
                if (!bPause.isEnabled()) {
                    setupMediaPlayer();
                    mPlayer.start();

                    bPause.setAlpha(1.0f);
                    bPause.setEnabled(true);
                    bStop.setAlpha(1.0f);
                    bStop.setEnabled(true);
                } else {
                    mPlayer.start();
                }

                mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                        return true;
                    }
                });

                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        bPause.setAlpha(0.4f);
                        bStop.setAlpha(0.4f);
                        bPause.setEnabled(false);
                        bStop.setEnabled(false);
                    }
                });
                break;
            case R.id.iBPauseButton:
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                } else {
                    mPlayer.start();
                }
                break;
            case R.id.iBEdit:
                editPerson();
                break;
            case R.id.iBDelete:
                deletePerson();
                break;
            case R.id.iBStopButton:
                bPause.setAlpha(0.4f);
                bStop.setAlpha(0.4f);
                bPause.setEnabled(false);
                bStop.setEnabled(false);

                mPlayer.stop();
                break;
            case R.id.iBSuggest:
                String toSpeak = txtGivenName.getText().toString() + " " + txtSurName.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    speakOutOver21(toSpeak);
                } else {
                    speakOutUnder20(toSpeak);
                }
                break;
        }
    }

    /**
     * Helper Method for better reading - start Activity to edit the Person
     */
    private void editPerson() {
        Bundle extra = getIntent().getExtras();

        Toast.makeText(this, "Edit Person", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, EditData.class);
        intent.putExtra("PID", getPID());
        intent.putExtra("FILE", getFilePath());
        intent.putExtra("title", extra.getString("title"));
        startActivity(intent);
    }

    /**
     * Helper Method for better reading - delete the Person from
     * the database and also delete the file
     */
    private boolean deletePerson() {
        Toast.makeText(this, "Delete Person", Toast.LENGTH_LONG).show();
        boolean deleted = false;
        // Delete Audio File
        try {
            File file = new File(getFilePath());
            deleted = file.delete();
            // Delete Person from DB
            db.deleteDBOnePerson(getPID());
            db.deleteDBTwoAudio(getPID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // GoBack To the Main site
        onBackPressed();
        return deleted;
    }


    /**
     * Method which handles the TTS action
     *
     * @param i
     */
    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(getLocation());
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }


    @SuppressWarnings("deprecation")
    private void speakOutUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void speakOutOver21(String text) {
        String utteranceId = this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    /**
     * Helper Method to return the Country
     *
     * @return the Region Code of the Handy
     */
    public Locale getLocation() {
        if (Locale.getDefault().getCountry().equals("US")) {
            return Locale.US;
        }
        if (Locale.getDefault().getCountry().equals("DE")) {
            return Locale.GERMAN;
        }
        if (Locale.getDefault().getCountry().equals("ZI")) {
            return Locale.TRADITIONAL_CHINESE;
        }
        if (Locale.getDefault().getCountry().equals("IT")) {
            return Locale.ITALIAN;
        }
        if (Locale.getDefault().getCountry().equals("FR")) {
            return Locale.FRENCH;
        }
        return Locale.GERMAN;
    }
}
