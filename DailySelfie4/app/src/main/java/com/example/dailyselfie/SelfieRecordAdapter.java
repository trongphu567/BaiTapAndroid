package com.example.dailyselfie;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class SelfieRecordAdapter extends BaseAdapter {

    private ArrayList<SelfieRecord> mRecordList = new ArrayList<>();
    private static LayoutInflater inflater = null;
    private Context mContext;

    public SelfieRecordAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);

        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir != null) {
            File[] selfieFiles = storageDir.listFiles((file, name) -> name.endsWith(".jpg"));

            assert selfieFiles != null;
            for (File file : selfieFiles) {
                SelfieRecord selfieRecord = new SelfieRecord(file.getAbsolutePath(), file.getName());
                mRecordList.add(selfieRecord);
            }
        }
    }

    public int getCount() {
        return mRecordList.size();
    }

    public Object getItem(int position) {
        return mRecordList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View newView = convertView;
        final SelfieRecordView selfieRecordView;

        final SelfieRecord currentRecord = mRecordList.get(position);

        if (null == convertView) {
            selfieRecordView = new SelfieRecordView();
            newView = inflater.inflate(R.layout.selfie_listitem, parent, false);
            selfieRecordView.checkBoxSelected = (CheckBox) newView.findViewById(R.id.checkbox_selected);
            selfieRecordView.thumbnail = (ImageView) newView.findViewById(R.id.thumbnail);
            selfieRecordView.selfieDate = (TextView) newView.findViewById(R.id.selfie_date);
            newView.setTag(selfieRecordView);
        }
        else {
            selfieRecordView = (SelfieRecordView) newView.getTag();
        }

        selfieRecordView.checkBoxSelected.setChecked(currentRecord.getSelected());
        selfieRecordView.checkBoxSelected.setOnCheckedChangeListener((buttonView, isChecked) -> currentRecord.setSelected(isChecked));

        ImageHelper.setImageFromFilePath(currentRecord.getPath(), selfieRecordView.thumbnail);
        selfieRecordView.selfieDate.setText(currentRecord.getDisplayName());

        return newView;
    }

    static class SelfieRecordView {
        CheckBox checkBoxSelected;
        ImageView thumbnail;
        TextView selfieDate;
    }

    public void add(SelfieRecord selfieRecord) {
        mRecordList.add(selfieRecord);
        notifyDataSetChanged();
    }

    public ArrayList<SelfieRecord> getAllRecords() {
        return mRecordList;
    }

    public ArrayList<SelfieRecord> getSelectedRecords() {
        ArrayList<SelfieRecord> mSelectedRecordList = new ArrayList<>();
        for (SelfieRecord record : mRecordList) {
            if (record.getSelected()) {
                mSelectedRecordList.add(record);
            }
        }
        return mSelectedRecordList;
    }

    public void clearAll() {
        mRecordList.clear();
        notifyDataSetChanged();
    }

    public void clearSelected() {
        mRecordList.removeAll(getSelectedRecords());
        notifyDataSetChanged();
    }
}
