package com.example.dailyselfie;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class SelfieRecordAdapter extends BaseAdapter {
    private ArrayList<SelfieRecord> recordList = new ArrayList<SelfieRecord>();
    private static LayoutInflater inflater = null;
    private Context mContext;

    public SelfieRecordAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);

        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir != null) {
            File[] selfieFiles = storageDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jpg");
                }
            });

            for (File file: selfieFiles) {
                SelfieRecord selfieRecord = new SelfieRecord(file.getAbsolutePath(), file.getName());
                recordList.add(selfieRecord);
            }
        }


    }
    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return recordList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        final SelfieRecordView selfieRecordView;
        final SelfieRecord currentRecord = recordList.get(position);

        if (convertView == null) {
            selfieRecordView = new SelfieRecordView();
            newView = inflater.inflate(R.layout.selfie_listitem, parent, false);
            selfieRecordView.selected = (CheckBox) newView.findViewById(R.id.checkbox_selected);
            selfieRecordView.thumb = (ImageView) newView.findViewById(R.id.thumbnail);
            selfieRecordView.selfieDate = (TextView) newView.findViewById(R.id.selfie_date);
            newView.setTag(selfieRecordView);
        }
        else {
            selfieRecordView = (SelfieRecordView) newView.getTag();
        }

        selfieRecordView.selected.setChecked(currentRecord.getSelected());
        selfieRecordView.selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentRecord.setSelected(isChecked);
            }
        });
        ImageHelper.setImageFromFilePath(currentRecord.getPath(), selfieRecordView.thumb);
        selfieRecordView.selfieDate.setText(currentRecord.getDisplayName());

        return newView;


    }

    public static class SelfieRecordView {
        CheckBox selected;
        ImageView thumb;
        TextView selfieDate;

    }

    public void add(SelfieRecord selfieRecord) {
        recordList.add(selfieRecord);
        notifyDataSetChanged();
    }

    public ArrayList<SelfieRecord> getAllRecords() {
        return recordList;
    }

    public ArrayList<SelfieRecord> getSelectedRecord() {
        ArrayList<SelfieRecord> selectedRecordList = new ArrayList<>();
        for (SelfieRecord record: recordList) {
            if (record.getSelected()) {
                selectedRecordList.add(record);
            }
        }
        return selectedRecordList;
    }

    public void clearAll() {
        recordList.clear();
    }

    public void clearSelected() {
        recordList.removeAll(getSelectedRecord());
        notifyDataSetChanged();
    }
}
