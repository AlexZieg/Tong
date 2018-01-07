package de.uni_stuttgart.sopra_ws1617_team8.tong;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter.DatabaseAdapter;
import de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter.RecyclerViewAdapterMainContact;
import de.uni_stuttgart.sopra_ws1617_team8.tong.Contacts.ContactCard;
import de.uni_stuttgart.sopra_ws1617_team8.tong.Lists.PersonList;

/**
 * Class which Contains the Search output. It will lead you to another Activity by clicking a single Item from
 * the RecyclerView.
 */
public class SearchMain extends Fragment implements RecyclerViewAdapterMainContact.PersonClickListener, View.OnClickListener {

    //Prepare the RecyclerView
    private RecyclerView rv;
    public RecyclerViewAdapterMainContact adapter;

    private ImageButton bFilter;
    private TextView textFilter;
    private int searchFilter;
    String toSearch;

    RadioButton rButName, rButGivName, rButNothing, rButRegion, rButCompany;
    RadioGroup rButGroup;

    // Database Configuration for the SearchView
    private DatabaseAdapter db;

    private EditText edSearch;

    //Lists
    private List<String> lGivenName, lSurName, lRegion, lCompany, lTitle;
    private List<Integer> lPID;
    private List<Float> lRating;
    private List<PersonList> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeLists();
        openDB();
    }

    /**
     * Initialize the Lists we need for the RecyclerView
     */
    private void initializeLists() {
        lCompany = new ArrayList<>();
        lRegion = new ArrayList<>();
        lSurName = new ArrayList<>();
        lGivenName = new ArrayList<>();
        lTitle = new ArrayList<>();
        lPID = new ArrayList<>();
        lRating = new ArrayList<>();
    }

    /**
     * Method to start the Connection to the Database
     */
    private void openDB() {
        db = new DatabaseAdapter(getContext());
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
     * Method to update the Data on the RecyclerView List each Time a
     * View on the Fragment has changed
     */
    public void onResume() {
        super.onResume();
        openDB();
        edSearch.getText().clear();
        getData();
        refreshRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = new ArrayList<>();
        //Inflate the layout for the Fragment
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        textFilter = (TextView) rootView.findViewById(R.id.txtFilter);

        textFilter.setText(R.string.searchNoPref);
        bFilter = (ImageButton) rootView.findViewById(R.id.iBFilter);
        bFilter.setOnClickListener(this);

        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);

        edSearch = (EditText) rootView.findViewById(R.id.searchData);
        getData();
        edSearch.setSingleLine();
        edSearch.addTextChangedListener(new TextWatcher() {
            /**
             * Method for the Events before an Text is input to the EditText
             * @param charSequence .
             * @param i .
             * @param i1 .
             * @param i2 .
             */
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * Method for the Event while an Text is input to the EditText
             * @param charSequence .
             * @param i .
             * @param i1 .
             * @param i2 .
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * Method for the Event after an Text is input to the EditText this changes our
             * SearchView from a static to a dynamic SearchView
             * @param editable .
             */
            @Override
            public void afterTextChanged(Editable editable) {
                //searchRecyclerList(getData());
                searchRecyclerList(setNewSearchList(getSearchFilter()));
            }
        });

        return rootView;
    }

    /**
     * Method to handle the Click from a CardView - important to handle single ContatcCards
     *
     * @param v        view that was clicked
     * @param position position that was clicked
     */
    public void itemClickPerson(View v, int position) {
        if (v.getId() == R.id.personCard) {
            startNewIntent(position, searchFilter);
        }
    }

    private void startNewIntent(int position, int searchFilter) {
        //Sending the Data from this Activity to the ContactCard - no new Database Stream is needed.
        Intent intent = new Intent(getContext(), ContactCard.class);
        intent.putExtra("title", "" + setNewSearchList(searchFilter).get(position).title);
        intent.putExtra("surname", "" + setNewSearchList(searchFilter).get(position).surname);
        intent.putExtra("givenname", "" + setNewSearchList(searchFilter).get(position).givenname);
        intent.putExtra("region", "" + setNewSearchList(searchFilter).get(position).region);
        intent.putExtra("company", "" + setNewSearchList(searchFilter).get(position).company);
        intent.putExtra("filePath", "" + getFilePath(setNewSearchList(searchFilter), position));
        intent.putExtra("PID", "" + setNewSearchList(searchFilter).get(position).PID);
        intent.putExtra("Rating", setNewSearchList(searchFilter).get(position).rating);
        startActivity(intent);
    }


    /**
     * Method to set the Data of a PersonList
     *
     * @param company   List of the companies
     * @param region    List of the regions
     * @param surname   List of the surnames
     * @param givenName List of the givenname
     * @param rating    list of the ratings
     * @param pid       list of the pids
     * @param title     list of the titles
     */
    private void setDataPList(List<String> company, List<String> region, List<String> surname,
                              List<String> givenName, List<Float> rating, List<Integer> pid, List<String> title) {
        data = new ArrayList<>();
        for (int i = 0; i < givenName.size(); i++) {
            PersonList pl = new PersonList();
            pl.company = company.get(i);
            pl.title = title.get(i);
            pl.region = region.get(i);
            pl.surname = surname.get(i);
            pl.givenname = givenName.get(i);
            pl.PID = pid.get(i);
            pl.rating = rating.get(i);
            data.add(pl);
        }
    }

    /**
     * Method to return the DataList
     *
     * @return DataList
     */
    public List<PersonList> getDataPList() {
        return this.data;
    }

    /**
     * Method to fill the Data to the CardView
     *
     * @return List Contains data for the CardView
     */
    public List<PersonList> getData() {
        // if we need a searchList go this branch
        initializeLists();

        toSearch = edSearch.getText().toString();
        Cursor cur = db.getDBOneFilteredList(toSearch);

        data = new ArrayList<>();
        if (cur.moveToFirst()) {
            cur.moveToFirst();
            lCompany.add(cur.getString(DatabaseAdapter.COL_P_COM));
            lTitle.add(cur.getString(DatabaseAdapter.COL_P_TITLE));
            lRegion.add(cur.getString(DatabaseAdapter.COL_P_REG));
            lSurName.add(cur.getString(DatabaseAdapter.COL_P_SUR));
            lGivenName.add(cur.getString(DatabaseAdapter.COL_P_GIVEN));
            lPID.add(cur.getInt(DatabaseAdapter.COL_P_ID));
            while (cur.moveToNext()) {
                lCompany.add(cur.getString(DatabaseAdapter.COL_P_COM));
                lTitle.add(cur.getString(DatabaseAdapter.COL_P_TITLE));
                lRegion.add(cur.getString(DatabaseAdapter.COL_P_REG));
                lSurName.add(cur.getString(DatabaseAdapter.COL_P_SUR));
                lGivenName.add(cur.getString(DatabaseAdapter.COL_P_GIVEN));
                lPID.add(cur.getInt(DatabaseAdapter.COL_P_ID));
            }
        }

        if (data != null) {
            data.clear();
        }

        // Setup the Rating
        getlRating(lPID);

        setDataPList(lCompany, lRegion, lSurName, lGivenName, lRating, lPID, lTitle);

        return getDataPList();
    }

    /**
     * HelpterMethod to get the exact FilePath
     *
     * @param list     list of the Person which is created
     * @param position position of the Card which is clicked
     * @return a String Value contains the Filepath
     */
    public String getFilePath(List<PersonList> list, int position) {
        Cursor cur = db.getDBTwoSpecificPath(String.valueOf(list.get(position).PID));
        String getPath = "";
        if (cur.moveToFirst()) {
            getPath = cur.getString(DatabaseAdapter.COL_A_FILE);
        }
        return getPath;
    }

    /**
     * Method to get the Rating List
     *
     * @param list PersonID List
     * @return List<Float> of the Rating
     */
    private List<Float> getlRating(List list) {
        for (int i = 0; i < list.size(); i++) {
            Cursor cur = db.getDBTwoSpecificPath(String.valueOf(list.get(i)));
            if (cur.moveToFirst()) {
                lRating.add(cur.getFloat(DatabaseAdapter.COL_A_RATING));
            }
        }
        return lRating;
    }

    /**
     * Method for Refreshing the RecyclerView
     */
    public void refreshRecyclerView() {
        //Show the List
        adapter = new RecyclerViewAdapterMainContact(getContext(), getData());
        adapter.setPersonClickListener(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
    }

    /**
     * Method to generate the searched List and Display it into the RecyclerView
     *
     * @param list list of the Person which is created
     */
    public void searchRecyclerList(List<PersonList> list) {
        adapter = new RecyclerViewAdapterMainContact(getContext(), list);
        adapter.setPersonClickListener(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iBFilter) {

            // Instantiate the Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            // Set the individual View of the Alert Dialog
            View v = getActivity().getLayoutInflater().inflate(R.layout.search_dialog, null);
            builder.setView(v);

            rButName = (RadioButton) v.findViewById(R.id.rName);
            rButGivName = (RadioButton) v.findViewById(R.id.rGivName);
            rButGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
            rButNothing = (RadioButton) v.findViewById(R.id.rNothing);
            rButCompany = (RadioButton) v.findViewById(R.id.rCompany);
            rButRegion = (RadioButton) v.findViewById(R.id.rRegion);


            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // getting the Value we need to know for the Database Selection
                    setSearchFilter();
                }
            });


            // Disable the function to cancle the Dialog
            builder.setCancelable(false);

            // Create the Dialog and show it
            AlertDialog dialog = builder.create();

            //Build the Dialog

            dialog.show();
        }

    }

    /**
     * Method to define the filter which the user searched for
     */
    private void setSearchFilter() {
        int setID = rButGroup.getCheckedRadioButtonId();
        if (setID == rButName.getId()) {
            textFilter.setText(getString(R.string.searchName));
            searchFilter = 0;
        } else if (setID == rButGivName.getId()) {
            textFilter.setText(R.string.searchGiven);
            searchFilter = 1;
        } else if (setID == rButRegion.getId()) {
            textFilter.setText(R.string.searchRegion);
            searchFilter = 2;
        } else if (setID == rButCompany.getId()) {
            textFilter.setText(R.string.searchCompany);
            searchFilter = 3;
        } else {
            textFilter.setText(R.string.searchNoPref);
            searchFilter = 4;
        }
    }

    /**
     * Method to get the Search Parameter
     *
     * @return the search Parameter
     */
    private int getSearchFilter() {
        return this.searchFilter;
    }

    /**
     * Method to specify the Searched List
     *
     * @param searchFilter the given searchParameter
     * @return a List contains the search Parameter
     */
    public List<PersonList> setNewSearchList(int searchFilter) {
        // Step 0 Pre-Setup
        List<PersonList> searchPersonList = new ArrayList<>();
        String getEditText;

        // First Copy all the saved Person Data into a new List
        searchPersonList.addAll(getDataPList());

        // Then check the Filter
        switch (searchFilter) {
            // 0 == Surname
            case 0:
                // Setup the Searched Key and the new List;
                getEditText = edSearch.getText().toString();
                List<PersonList> searchPersonListSurName = new ArrayList<>();
                // Iterate through the Copyed List to get all the Persons the user searched for
                for (int i = 0; i < searchPersonList.size(); i++) {
                    // Check at which position we have the searched String
                    if (searchPersonList.get(i).surname.contains(getEditText)) {
                        searchPersonListSurName.add(searchPersonList.get(i));
                    }
                }
                return searchPersonListSurName;
            // 1 == Given Name
            case 1:
                // Setup the Searched Key and the new List;
                getEditText = edSearch.getText().toString();
                List<PersonList> searchPersonListGivenName = new ArrayList<>();
                // Iterate through the Copyed List to get all the Persons the user searched for
                for (int i = 0; i < searchPersonList.size(); i++) {
                    // Check at which position we have the searched String
                    if (searchPersonList.get(i).givenname.contains(getEditText)) {
                        searchPersonListGivenName.add(searchPersonList.get(i));
                    }
                }
                return searchPersonListGivenName;
            // 2 == Region
            case 2:
                // Setup the Searched Key and the new List;
                getEditText = edSearch.getText().toString();
                List<PersonList> searchPersonListRegion = new ArrayList<>();
                // Iterate through the Copyed List to get all the Persons the user searched for
                for (int i = 0; i < searchPersonList.size(); i++) {
                    // Check at which position we have the searched String
                    if (searchPersonList.get(i).region.contains(getEditText)) {
                        searchPersonListRegion.add(searchPersonList.get(i));
                    }
                }
                return searchPersonListRegion;
            // 3 == Company
            case 3:  // Setup the Searched Key and the new List;
                getEditText = edSearch.getText().toString();
                List<PersonList> searchPersonListCompany = new ArrayList<>();
                // Iterate through the Copyed List to get all the Persons the user searched for
                for (int i = 0; i < searchPersonList.size(); i++) {
                    // Check at which position we have the searched String
                    if (searchPersonList.get(i).company.contains(getEditText)) {
                        searchPersonListCompany.add(searchPersonList.get(i));
                    }
                }
                return searchPersonListCompany;
            // 4 == No Preferences
            case 4:
                getEditText = edSearch.getText().toString();
                List<PersonList> searchPersonListNothing = new ArrayList<>();
                // Iterate through the Copyed List to get all the Persons the user searched for
                for (int i = 0; i < searchPersonList.size(); i++) {
                    // Check at which position we have the searched String
                    if (searchPersonList.get(i).region.contains(getEditText) ||
                            searchPersonList.get(i).company.contains(getEditText) ||
                            searchPersonList.get(i).surname.contains(getEditText) ||
                            searchPersonList.get(i).givenname.contains(getEditText)) {
                        searchPersonListNothing.add(searchPersonList.get(i));
                    }
                }
                return searchPersonListNothing;
        }
        return searchPersonList;
    }
}

