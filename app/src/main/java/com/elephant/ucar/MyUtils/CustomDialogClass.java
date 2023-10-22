package com.elephant.ucar.MyUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.elephant.ucar.Adapters.DialogListViewAdapter;
import com.elephant.ucar.R;

import java.util.ArrayList;
import java.util.List;

public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    ListView listView;
    DialogListViewAdapter adapter;
    List<String> item = new ArrayList<>();

    public CustomDialogClass(Activity a, List<String> num) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.item = num;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        listView = findViewById(R.id.dialogList);

        adapter = new DialogListViewAdapter(getContext(), R.layout.dialog_item, item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.get(position)));
                c.startActivity(intent);
                c.finish();


            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}