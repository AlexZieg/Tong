package de.uni_stuttgart.sopra_ws1617_team8.tong;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter.DatabaseAdapter;
import de.uni_stuttgart.sopra_ws1617_team8.tong.Contacts.ContactCard;

/**
 * Class for the DataStructure which the User can Edit. *
 */
public class EditData extends AppCompatActivity implements View.OnClickListener {

    // The given Views of the Class
    EditText edSurname, edGivenName, edRegion, edCompany;
    TextView txtSurname, txtGivenName, txtRegion, txtCompany, txtTitle;
    Spinner spTitle;
    Button btnSubmit;
    RatingBar rOld, rNew;
    List<String> values;

    // Variables for the usage of the Database
    DatabaseAdapter db;
    String PID, titleOfPerson;
    float ratingOld, ratingNew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdata);

        openDB();
        initializeViews();
    }


    /**
     * Method to update the Data on the RecyclerView List each Time a
     * View on the Fragment has changed
     */
    public void onResume() {
        super.onResume();
        openDB();

        Cursor cur = db.getDBOnePerson(Integer.parseInt(getPID()));
        if (cur.moveToFirst()) {
            cur.moveToFirst();
            txtTitle.setText(cur.getString(DatabaseAdapter.COL_P_TITLE));
            txtGivenName.setText(cur.getString(DatabaseAdapter.COL_P_GIVEN));
            txtSurname.setText(cur.getString(DatabaseAdapter.COL_P_SUR));
            txtCompany.setText(cur.getString(DatabaseAdapter.COL_P_COM));
            txtRegion.setText(cur.getString(DatabaseAdapter.COL_P_REG));

            edGivenName.setText(cur.getString(DatabaseAdapter.COL_P_GIVEN));
            edSurname.setText(cur.getString(DatabaseAdapter.COL_P_SUR));
            edRegion.setText(cur.getString(DatabaseAdapter.COL_P_REG));
            edCompany.setText(cur.getString(DatabaseAdapter.COL_P_COM));
        }

        Cursor curTwo = db.getDBTwoSpecificPath(getPID());
        if (curTwo.moveToFirst()){
            curTwo.moveToFirst();
            ratingOld = curTwo.getFloat(DatabaseAdapter.COL_A_RATING);
            rOld.setRating(ratingOld);
        }

    }

    private String getPID() {
        return PID;
    }

    private void setPID(String PID) {
        this.PID = PID;
    }

    private String getTitleOfPerson() {
        return titleOfPerson;
    }

    private void setTitleOfPerson(String titleOfPerson) {
        this.titleOfPerson = titleOfPerson;
    }

    /**
     * Method to open the Stream/Connection to the Database
     */
    private void openDB() {
        db = new DatabaseAdapter(this);
        db.open();
    }

    /**
     * Method to close the Stream/Connection to the Database
     */
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    /**
     * Method to initializeViews the Buttons to the View and give them a clickable feature
     */
    private void initializeViews() {

        Bundle extra = getIntent().getExtras();
        setPID(extra.getString("PID"));
        setTitleOfPerson(extra.getString("title"));
        int p_id = Integer.parseInt(getPID());

        txtTitle = (TextView) findViewById(R.id.textTitle);
        txtGivenName = (TextView) findViewById(R.id.textGivenName);
        txtSurname = (TextView) findViewById(R.id.textSurName);
        txtRegion = (TextView) findViewById(R.id.textRegion);
        txtCompany = (TextView) findViewById(R.id.textCompany);

        edGivenName = (EditText) findViewById(R.id.edGivName);
        edSurname = (EditText) findViewById(R.id.edSurName);
        edRegion = (EditText) findViewById(R.id.edReg);
        edCompany = (EditText) findViewById(R.id.edCom);

        rOld = (RatingBar) findViewById(R.id.ratingOld);
        rNew = (RatingBar) findViewById(R.id.ratingNew);
        rNew.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rNew.setRating(ratingBar.getRating());
                ratingNew = rNew.getRating();
                Toast.makeText(getApplicationContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

        Cursor cur = db.getDBOnePerson(p_id);
        if (cur.moveToFirst()) {
            cur.moveToFirst();
            txtTitle.setText(cur.getString(DatabaseAdapter.COL_P_TITLE));
            txtGivenName.setText(cur.getString(DatabaseAdapter.COL_P_GIVEN));
            txtSurname.setText(cur.getString(DatabaseAdapter.COL_P_SUR));
            txtCompany.setText(cur.getString(DatabaseAdapter.COL_P_COM));
            txtRegion.setText(cur.getString(DatabaseAdapter.COL_P_REG));

            edGivenName.setText(cur.getString(DatabaseAdapter.COL_P_GIVEN));
            edSurname.setText(cur.getString(DatabaseAdapter.COL_P_SUR));
            edRegion.setText(cur.getString(DatabaseAdapter.COL_P_REG));
            edCompany.setText(cur.getString(DatabaseAdapter.COL_P_COM));
        }

        Cursor curTwo = db.getDBTwoSpecificPath(p_id+"");
        if (curTwo.moveToFirst()){
            curTwo.moveToFirst();
            ratingOld = curTwo.getFloat(DatabaseAdapter.COL_A_RATING);
            rOld.setRating(ratingOld);
        }

        spTitle = (Spinner) findViewById(R.id.spinTitle);

        btnSubmit = (Button) findViewById(R.id.btnEditSubmit);
        btnSubmit.setOnClickListener(this);
    }

    /**
     * Method which handles the click on a Button
     *
     * @param view the given View of a Button
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnEditSubmit) {
            int p_id = Integer.parseInt(getPID());
            values = new ArrayList<>();

            // Check fist if something has change, if something has changed, update the
            // Database, if not do nothing
            if (!isEmpty(edGivenName) && !containsNumbers(edGivenName)) {
                db.updateGivenName(p_id, edGivenName.getText().toString());
                txtGivenName.setText(edGivenName.getText().toString());
                values.add(edGivenName.getText().toString());
            } else {
                edGivenName.setError(getResources().getString(R.string.errorEditText));
            }

            if (!isEmpty(edSurname) && !containsNumbers(edSurname)) {
                db.updateSurName(p_id, edSurname.getText().toString());
                txtSurname.setText(edSurname.getText().toString());
                values.add(edSurname.getText().toString());
            } else {
                edSurname.setError(getResources().getString(R.string.errorEditText));
            }

            if (!isEmpty(edRegion)) {
                db.updateRegion(p_id, edRegion.getText().toString());
                txtRegion.setText(edRegion.getText().toString());
                values.add(edRegion.getText().toString());
            } else {
                edRegion.setError(getResources().getString(R.string.errorEditText));
            }

            if (!isEmpty(edCompany)) {
                db.updateCompany(p_id, edCompany.getText().toString());
                txtCompany.setText(edCompany.getText().toString());
                values.add(edCompany.getText().toString());
            } else {
                edCompany.setError(getResources().getString(R.string.errorEditText));
            }

            if (!getTitleOfPerson().equals(getString(R.string.male)) ||
                    !getTitleOfPerson().equals(getString(R.string.Female))) {
                db.updateTitle(p_id, spTitle.getSelectedItem().toString());
                txtTitle.setText(spTitle.getSelectedItem().toString());
                values.add(spTitle.getSelectedItem().toString());
            }

            if (ratingOld != ratingNew){
                db.updateRating(p_id, rNew.getRating());
                values.add(String.valueOf(ratingNew));
            } else {
                values.add(String.valueOf(ratingOld));
            }
        }
        onBackPress(values);
    }


    public void onBackPress(List values) {
        float rating = Float.parseFloat(values.get(5).toString());

        Intent intent = new Intent(this, ContactCard.class);
        intent.putExtra("title", "" + values.get(4));
        intent.putExtra("surname", "" + values.get(1));
        intent.putExtra("givenname", "" + values.get(0));
        intent.putExtra("region", "" + values.get(2));
        intent.putExtra("company", "" + values.get(3));
        intent.putExtra("Rating", "" + rating);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
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
}
