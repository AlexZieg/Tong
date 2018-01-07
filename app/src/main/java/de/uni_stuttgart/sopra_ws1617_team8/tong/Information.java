package de.uni_stuttgart.sopra_ws1617_team8.tong;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Class for all the Information about the App. E.g. Language Packages, Version Information, Licences, etc.
 */
public class Information extends AppCompatActivity implements View.OnClickListener {

    //ImageButtons Language
    private ImageButton bGerman, bSpain, bUk, bFrance, bChina, bRussia, bItaly, bIndia, infoIcon;
    private ImageView curLanguage;
    private TextView actLang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        initializeViews();
    }

    private void initializeViews() {
        curLanguage = (ImageView) findViewById(R.id.currentLanguage);
        actLang = (TextView) findViewById(R.id.actLanguage);

        bGerman = (ImageButton) findViewById(R.id.iBGerman);
        bSpain = (ImageButton) findViewById(R.id.iBSpain);
        bUk = (ImageButton) findViewById(R.id.iBUK);
        bFrance = (ImageButton) findViewById(R.id.iBFrance);
        bChina = (ImageButton) findViewById(R.id.iBChina);
        bRussia = (ImageButton) findViewById(R.id.iBRussia);
        bItaly = (ImageButton) findViewById(R.id.iBItaly);
        bIndia = (ImageButton) findViewById(R.id.iBIndia);

        infoIcon = (ImageButton) findViewById(R.id.info);
        infoIcon.setOnClickListener(this);

        bGerman.setOnClickListener(this);
        bSpain.setOnClickListener(this);
        bUk.setOnClickListener(this);
        bFrance.setOnClickListener(this);
        bChina.setOnClickListener(this);
        bRussia.setOnClickListener(this);
        bItaly.setOnClickListener(this);
        bIndia.setOnClickListener(this);


        switch (Locale.getDefault().getDisplayCountry()) {
            case "DE":
                curLanguage.setImageResource(R.mipmap.germany);
                actLang.setText(R.string.german);
                break;
            case "EN":
                curLanguage.setImageResource(R.mipmap.unitedkingdom);
                actLang.setText(R.string.uk);
                break;
            case "US":
                curLanguage.setImageResource(R.mipmap.unitedkingdom);
                actLang.setText(R.string.uk);
                break;
            case "RU":
                curLanguage.setImageResource(R.mipmap.russian);
                actLang.setText(R.string.russia);
                break;
            case "ES":
                curLanguage.setImageResource(R.mipmap.spain);
                actLang.setText(R.string.spain);
                break;
            case "IT":
                curLanguage.setImageResource(R.mipmap.italy);
                actLang.setText(R.string.italy);
                break;
            case "FR":
                curLanguage.setImageResource(R.mipmap.france);
                actLang.setText(R.string.french);
                break;
            case "HI":
                curLanguage.setImageResource(R.mipmap.india);
                actLang.setText(R.string.india);
                break;
            case "ZI":
                curLanguage.setImageResource(R.mipmap.china);
                actLang.setText(R.string.chinese);
                break;
            default:
                curLanguage.setImageResource(R.mipmap.germany);
                actLang.setText(R.string.german);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iBGerman:
                Toast.makeText(this, R.string.german, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, R.string.german, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBSpain:
                Toast.makeText(this, R.string.spain, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBUK:
                Toast.makeText(this, R.string.uk, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBFrance:
                Toast.makeText(this, R.string.french, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBChina:
                Toast.makeText(this, R.string.chinese, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBRussia:
                Toast.makeText(this, R.string.russia, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBItaly:
                Toast.makeText(this, R.string.italy, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iBIndia:
                Toast.makeText(this, R.string.india, Toast.LENGTH_SHORT).show();
                break;
            case R.id.info:
                // Instantiate the Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Set the individual View of the Alert Dialog
                View v = this.getLayoutInflater().inflate(R.layout.info_dialog, null);
                builder.setView(v);

                TextView tHead = (TextView) v.findViewById(R.id.txtHeadline);
                TextView tCont = (TextView) v.findViewById(R.id.txtContent);

                tHead.setText(R.string.speechSettings);
                tCont.setText(R.string.speechSettingText);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                // Create the Dialog and show it
                AlertDialog dialog = builder.create();

                //Build the Dialog
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                break;
        }
    }
}
