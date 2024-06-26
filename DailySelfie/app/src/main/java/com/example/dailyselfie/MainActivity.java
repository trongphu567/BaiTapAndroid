package com.example.dailyselfie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final long INTERVAL_TWO_MINUTES = 2*60*1000L;

    private String mCurrentSelfieName;
    private String mCurrentPhotoPath;
    private SelfieRecordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "getExternalStorageState() = " + Environment.getExternalStorageState());
        ListView selfieList = findViewById(R.id.selfie_list);

        mAdapter = new SelfieRecordAdapter(getApplicationContext());
        selfieList.setAdapter(mAdapter);
        selfieList.setOnItemClickListener((parent, view, position, id) -> {
            SelfieRecord selfieRecord = (SelfieRecord) mAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, ImageDetail.class);
            intent.putExtra(Intent.EXTRA_TEXT, selfieRecord.getPath());
            startActivity(intent);
        });
        createSelfieAlarm();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_camera) {
            dispatchTakePictureIntent();
            return true;
        }
        
        if (id == R.id.action_delete_selected) {
            deleteSelectedSelfies();
            return true;
        }

        if (id == R.id.action_delete_all) {
            deleteAllSelfies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private void dispatchTakePictureIntent()  {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePicture.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File photoFile = new File(mCurrentPhotoPath);
            File selfieFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mCurrentSelfieName + ".jpg");
            photoFile.renameTo(selfieFile);

            SelfieRecord selfieRecord = new SelfieRecord(Uri.fromFile(selfieFile).getPath(), mCurrentSelfieName);
            Log.d(LOG_TAG, selfieRecord.getPath() + " - " + selfieRecord.getDisplayName());
            mAdapter.add(selfieRecord);
        } else {
            File photoFile = new File(mCurrentPhotoPath);
            photoFile.delete();
        }

    }

    private void deleteSelectedSelfies() {
        ArrayList<SelfieRecord> selectedSelfie = mAdapter.getSelectedRecord();
        for (SelfieRecord record: selectedSelfie) {
            File selfieFile = new File(record.getPath());
            selfieFile.delete();
        }
        mAdapter.clearSelected();

    }

    private void deleteAllSelfies() {
        for (SelfieRecord record: mAdapter.getAllRecords()) {
            File selfieFile = new File(record.getPath());
            selfieFile.delete();
        }
        mAdapter.clearAll();
    }
    private File createImageFile() throws IOException {
        mCurrentSelfieName = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
        File imageFile = File.createTempFile(mCurrentSelfieName, ".jpg", getExternalFilesDir(null));

        mCurrentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;

    }
    private void createSelfieAlarm() {
        Intent intent = new Intent(this, SelfieNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + INTERVAL_TWO_MINUTES,
                INTERVAL_TWO_MINUTES,
                pendingIntent);
    }
}