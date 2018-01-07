package de.uni_stuttgart.sopra_ws1617_team8.tong.Contacts;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter.DatabaseAdapter;
import de.uni_stuttgart.sopra_ws1617_team8.tong.Permissions;
import de.uni_stuttgart.sopra_ws1617_team8.tong.R;

public class Export extends AppCompatActivity implements View.OnClickListener {

    Button bExport;
    TextView tError;
    private final String DATABASENAME = "tong";
    private final int BUFFER_SIZE = 8096;
    // Database Configuration
    private DatabaseAdapter db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exportcontacts);
        openDB();
        initViews();
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

    private void initViews() {
        tError = (TextView) findViewById(R.id.txtError);
        bExport = (Button) findViewById(R.id.btnExportPath);
        bExport.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnExportPath){
            Toast.makeText(this, "Export Database", Toast.LENGTH_SHORT).show();
            try {
                zip(createFilePaths(), this.getFilesDir().getPath().toString() + "tongToImportExport.zip");
                String export = getResources().getString(R.string.file_exported);
                tError.setText(export + " to " + this.getFilesDir().getPath().toString() + "/tongToImportExport.zip");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Method to export the Database
     * @return the Path where the Database is exported to
     */
    private String exportDB() throws NullPointerException{
        Permissions permissions = new Permissions();
        permissions.checkStoragePermission(this, this);

        File backupDB = null;
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                //String  currentDBPath= db.getDatabasePath();
                String currentDBPath = "//data//" + "de.uni_stuttgart.sopra_ws1617_team8.tong" + "//databases//" + DATABASENAME;
                String backupDBPath  =  DATABASENAME;
                File currentDB = new File(data, currentDBPath);
                backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return backupDB.toString();
    }

    public String[] createFilePaths(){
        List<Integer> pl = new ArrayList<>();

        Cursor cur = db.getDBOneRows();
        if(cur.moveToFirst()){
            cur.moveToFirst();
            pl.add(cur.getInt(DatabaseAdapter.COL_P_ID));
            while (cur.moveToNext()){
                pl.add(cur.getInt(DatabaseAdapter.COL_P_ID));
            }
        }

        String[] filePaths = new String[pl.size() + 1];
        List<Integer> pList = new ArrayList<>();
        filePaths[0] = exportDB();

        for (int i = 0; i < pl.size(); i++){
            pList.add(pl.get(i));
        }
        for (int j = 0; j < pList.size(); j++) {
            Cursor cu = db.getDBTwoSpecificPath(String.valueOf(pList.get(j)));
            if (cu.moveToFirst()){
                cu.moveToFirst();
                filePaths[j+1] = cu.getString(DatabaseAdapter.COL_A_FILE);
            }
        }
        return filePaths;
    }


    public void zip(String[] files, String zipFile) throws IOException {
        BufferedInputStream origin;
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        try {
            byte data[] = new byte[BUFFER_SIZE];

            for (int i = 0; i < files.length; i++) {
                FileInputStream fi = new FileInputStream(files[i]);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try {
                    ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                }
                finally {
                    origin.close();
                }
            }
        }
        finally {
            out.close();
        }
    }

}
