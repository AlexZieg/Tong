package de.uni_stuttgart.sopra_ws1617_team8.tong;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class for the MediaRecorder and MediaPlayer. It contains the Methods which holds all the
 * MediaPlayer Stuff we need to achieve the Critical Feature 1
 */
public class MediaPlayerMain extends Fragment implements View.OnClickListener {

    // Button Constants
    ImageButton iBStRec, iBSave, iBReset, iBPlay;
    private float alphaLess = 0.4f;
    private boolean recording = false;
    private boolean hasRecord = false;
    Permissions permissions;

    // File
    private File filePath;

    //Audio
    private MediaPlayer mPlayer;
    private MediaRecorder mRecorder;

    //Timer setup egnough Time - so that there is "no Limit"
    CounterClass timer = new CounterClass(300000);
    TextView textTimer;

    public MediaPlayerMain() {
        if (iBStRec != null) {
            iBReset.performClick();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for the Fragment
        View rootView = inflater.inflate(R.layout.fragment_mediaplayer, container, false);

        initializeViews(rootView);
        textTimer.setText(R.string.timer);

        return rootView;
    }


    /**
     * Method to initialize the Buttons to the View and give them a clickable feature
     *
     * @param rootView view of the loaded Fragment
     */
    private void initializeViews(View rootView) {
        //Timer
        textTimer = (TextView) rootView.findViewById(R.id.txtTimer);

        // Initialize the Buttons
        iBStRec = (ImageButton) rootView.findViewById(R.id.iBStartRecord);
        iBSave = (ImageButton) rootView.findViewById(R.id.iBSaveRecord);
        iBReset = (ImageButton) rootView.findViewById(R.id.iBResetRecord);
        iBPlay = (ImageButton) rootView.findViewById(R.id.iBPlayRecord);

        // Disable the Buttons which are you don't need
        disableButtons();

        // Setup the onClickListener
        iBStRec.setOnClickListener(this);
        iBSave.setOnClickListener(this);
        iBReset.setOnClickListener(this);
        iBPlay.setOnClickListener(this);

        // Set a Tag for the ImageButton - this is Important to change the Image by clicking
        iBStRec.setTag(R.drawable.record);
        iBPlay.setTag(R.drawable.play);

        //check if the mic is ready
        if (!hasMicrophone()) {
            iBStRec.setAlpha(alphaLess);
            iBStRec.setEnabled(false);
        }
    }

    /**
     * Method to disable the Buttons and set the visibility level to alphaLess
     */
    private void disableButtons() {
        // Make the non used Buttons less Visible
        iBPlay.setAlpha(alphaLess);
        iBSave.setAlpha(alphaLess);
        iBReset.setAlpha(alphaLess);

        // Make the non used Buttons clickable
        iBPlay.setEnabled(false);
        iBSave.setEnabled(false);
        iBReset.setEnabled(false);
    }

    /**
     * Method to enable the Buttons and set the visibility Level to alphaMax
     */
    private void enableButtons() {
        float alphaMax = 1.0f;
        // Make the non used Buttons full Visible
        iBPlay.setAlpha(alphaMax);
        iBSave.setAlpha(alphaMax);
        iBReset.setAlpha(alphaMax);

        // Make the non used Buttons clickable
        iBPlay.setEnabled(true);
        iBSave.setEnabled(true);
        iBReset.setEnabled(true);

    }


    /**
     * Method to handle the clickable Buttons from the MediaPlayer
     * </p><p>
     * Play/Stop Button handles the ability to start an record, while a record is
     * started the Button switch to a stop button. By pressing the stop button the
     * started record will stop
     * </p><p>
     * Play Button: Start Playing the record File once if it's recorded
     * </p><p>
     * Save Button: Once a record is done this Method handles the save options
     * </p><p>
     * Refresh Button: To discard a record use this Method.
     * </p>
     *
     * @param view given view of the Button which is clicked
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Start Record
            case R.id.iBStartRecord:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) !=
                        PackageManager.PERMISSION_GRANTED) {
                    permissions.checkMicrophonePermission(getContext(), getActivity());
                } else {
                    // Switch the Button from Record to Stop
                    if ((Integer) iBStRec.getTag() == R.drawable.record) {
                        // Change the Images
                        iBStRec.setImageResource(R.drawable.stop);
                        iBStRec.setTag(R.drawable.stop);
                        // start the Timer
                        timer.start();
                        // Disable the Buttons - the user shouldn't start playing if he still record the audiofile
                        disableButtons();

                        //Start the Record
                        try {
                            if (filePath != null) {
                                filePath.delete();
                            }
                            mRecorder = new MediaRecorder();
                            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            saveFileTo(randomFileName());
                            mRecorder.setOutputFile(filePath.getPath());
                            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            mRecorder.prepare();
                            recording = true;
                            hasRecord = true;
                            // Display the result
                            Toast.makeText(getActivity(), getResources().getString(R.string.recStart), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mRecorder.start();
                        timer.start();
                        // Stop the Record
                    } else {
                        timer.cancel();
                        // Switch the Button from Stop to Record
                        iBStRec.setImageResource(R.drawable.record);
                        iBStRec.setTag(R.drawable.record);

                        // Stop the Recording if any Record is active
                        if (isRecorded()) {
                            // Catch the known MediaRecorder error if the Record Button hits to fast
                            try {
                                mRecorder.stop();
                                mRecorder = null;
                                recording = false;
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                            }
                        }

                        // make the other Buttons enable
                        enableButtons();

                        Toast.makeText(getActivity(), getResources().getString(R.string.recFinish), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            // Play Record
            case R.id.iBPlayRecord:
                // Check if a Record is available
                if (isRecording()) {
                    // Switch the Button from Play to Stop
                    if ((Integer) iBPlay.getTag() == R.drawable.play) {
                        iBPlay.setImageResource(R.drawable.stop);
                        iBPlay.setTag(R.drawable.stop);

                        // Play the Record
                        mPlayer = new MediaPlayer();
                        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            if (filePath != null) {
                                mPlayer.setDataSource(filePath.getPath());
                            }
                            mPlayer.prepareAsync();
                            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer ep) {
                                    mPlayer.start();
                                }
                            });
                            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    iBPlay.performClick();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Switch the Button from Stop to Play
                        iBPlay.setImageResource(R.drawable.play);
                        iBPlay.setTag(R.drawable.play);
                        // Stop the Record
                    }
                }
                Toast.makeText(getActivity(), getResources().getString(R.string.recPlay), Toast.LENGTH_SHORT).show();
                break;
            // Save the Record
            case R.id.iBSaveRecord:
                if (isRecording()) {
                    // Disable all Buttons
                    disableButtons();
                    // set Record Button from Stop to Record
                    iBStRec.setImageResource(R.drawable.record);
                    iBStRec.setTag(R.drawable.record);
                    // Start the new Activity which handles the Save Form
                    startActivity(
                            new Intent(getContext(), SaveData.class)
                                    .putExtra("filename", "" + filePath.getPath()));
                }
                textTimer.setText(R.string.timerValue);
                break;
            // Delete all Recorded Data
            case R.id.iBResetRecord:
                if (isRecording()) {
                    recording = false;
                    // Create new Instance of filePath
                    if (filePath != null) {
                        filePath.delete();
                    }
                    // reset the recorded file
                    if (mPlayer != null) {
                        mPlayer.reset();
                        mPlayer.release();
                    }
                    if (mRecorder != null) {
                        mRecorder.reset();
                        mRecorder.release();
                    }
                    // disable all the Buttons which dont need anymore
                    disableButtons();
                    // Set Timer to predefined Value
                    textTimer.setText(R.string.timerValue);
                    // notify the user
                    Toast.makeText(getActivity(), getResources().getString(R.string.recReset), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * Method to proof if there is a record available
     *
     * @return true if a record is available, false if not
     */
    private boolean isRecording() {
        return hasRecord;
    }


    /**
     * Method which checks if the system actual take a record
     *
     * @return true if is in a record
     * false if is not in a record
     */
    private boolean isRecorded() {
        return recording;
    }

    /**
     * Method to generate a random File name which isn't used before
     *
     * @return a String which contains a random File name
     */
    private String randomFileName() {
        String extension = getResources().getString(R.string.extension3gp);
        String randomAudioFileName = getResources().getString(R.string.randomFileName);

        String newFileName = "";

        //generate a RandomString from randomAudioFileName;
        newFileName += randomAudioFileName;

        //generate a Random Integer from 1000 to 100000
        Random rand = new Random();
        int i = rand.nextInt((100000 - 1000) + 100000);
        newFileName += i;

        // Shuffle the two Random "Strings" into a new String
        List<String> letters = Arrays.asList(newFileName.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }

        // extend the String with the correct extension;
        shuffled += extension;

        return shuffled;
    }

    /**
     * Helper Method which generate the whole Filepath with the filename itself
     *
     * @param fileName filename of the file which should be saved
     * @return filepath + filename
     */
    private File saveFileTo(String fileName) {
        filePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
        return filePath;
    }

    /**
     * Helper Method to check if the user got an Microphone or if the Microphone is ready
     */
    private boolean hasMicrophone() {
        PackageManager pManager = getActivity().getPackageManager();
        return pManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }

    public class CounterClass extends CountDownTimer {
        private static final long INTERVAL = 1000;
        private final long duration;

        private CounterClass(long durationMS) {
            super(durationMS, INTERVAL);
            this.duration = durationMS;
        }

        /**
         * Do Nothing
         */
        @Override
        public void onFinish() {
            textTimer.setText(getResources().getString(R.string.timerComplete));
        }


        /**
         * Method to handle each Tick on the Timer
         *
         * @param millis
         */
        @Override
        public void onTick(long millis) {

            textTimer.setText(String.valueOf((int) (duration - millis) / 1000) + getString(R.string.sec));

        }
    }
}
