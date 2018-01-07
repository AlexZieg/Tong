package de.uni_stuttgart.sopra_ws1617_team8.tong;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter.DatabaseAdapter;

/**
 * Class for the DataStructure which the User can Save.
 */
public class SaveData extends AppCompatActivity implements View.OnClickListener {

    DatabaseAdapter db;

    //EditText and RatingBar
    EditText eGName, eSName, eRegion, eComp;
    RatingBar rBar;
    float rating = 0;
    Permissions permissions;

    //Button and Spinner
    Spinner sTitle;
    Button bSubmit;

    ImageView targetImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_dialog);

        permissions = new Permissions();
        permissions.checkStoragePermission(this, this);

        initializeViews();
        openDB();
    }

    /**
     * Method to start the Connection to the Database
     */
    private void openDB() {
        db = new DatabaseAdapter(this);
        db.open();
    }

    /**
     * Method to close the Connection to the Database
     */
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    /**
     * Method to initializeViews the Buttons to the View and give them a clickable feature
     */
    private void initializeViews() {
        targetImage = (ImageView) findViewById(R.id.targetImage);
        sTitle = (Spinner) findViewById(R.id.spinTitle);

        eGName = (EditText) findViewById(R.id.edGivenName);
        eSName = (EditText) findViewById(R.id.edSurName);
        eRegion = (EditText) findViewById(R.id.edRegion);
        eComp = (EditText) findViewById(R.id.edCompany);

        rBar = (RatingBar) findViewById(R.id.ratingBarAudio);
        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rBar.setRating(ratingBar.getRating());
                rating = rBar.getRating();
                Toast.makeText(getApplicationContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

        bSubmit = (Button) findViewById(R.id.btnSave);
        bSubmit.setOnClickListener(this);
    }

    /**
     * After the user click the submit Button the save Process will
     * begin. It checks if the User has done a correct input and save
     * the data into the Database
     *
     * @param view the given view which the button delivered
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            String title = sTitle.getSelectedItem().toString();
            String gName = eGName.getText().toString();
            String sName = eSName.getText().toString();
            String region = eRegion.getText().toString();
            String comp = eComp.getText().toString();

            if (isEmpty(eGName) || containsNumbers(eGName)) {
                eGName.setError(getResources().getString(R.string.errorEditText));
            } else if (isEmpty(eSName) || containsNumbers(eSName)) {
                eSName.setError(getResources().getString(R.string.errorEditText));
            } else if (isEmpty(eRegion)) {
                eRegion.setError(getResources().getString(R.string.errorEditText));
            } else if (isEmpty(eComp)) {
                eComp.setError(getResources().getString(R.string.errorEditText));
            } else {
                db.insertDBOne(title, gName, sName, region, comp);

                Bundle extra = getIntent().getExtras();

                int PID = getPersonID(sName);

                db.insertDBTwo(PID, extra.getString("filename"), rating);
                Toast.makeText(this, getResources().getString(R.string.save), Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * Method to check if an EditText is Empty
     *
     * @param toCheck Edit Text to check
     * @return true if the EditText is empty, false if it isn't
     */
    private boolean isEmpty(EditText toCheck) {
        return toCheck.getText().toString().trim().length() == 0;
    }

    /**
     * Method to check if the EditText contains Numbers
     *
     * @param toCheck EditText to check
     * @return true if the EditText contains Numbers, false if not
     */
    private boolean containsNumbers(EditText toCheck) {
        String stringToCheck = toCheck.getText().toString().trim();
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (Character.isDigit(stringToCheck.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private Integer getPersonID(String personSurName) {
        Cursor cur = db.getDBOnePersonID(personSurName);
        int personID = 0;
        if (cur.moveToFirst()) {
            cur.moveToFirst();
            personID = cur.getInt(DatabaseAdapter.COL_P_ID);
        }
        return personID;
    }
}