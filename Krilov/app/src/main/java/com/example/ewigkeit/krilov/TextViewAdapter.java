package com.example.ewigkeit.krilov;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ewigkeit on 21.11.2017.
 */

public class TextViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] textViewValues;

    public TextViewAdapter(Context context, String[] textViewValues) {
        this.context = context;
        this.textViewValues = textViewValues;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.item, null);
            // set value into textview

        } else {
            gridView = (View) convertView;
        }
        final EditText editText = (EditText) gridView
                .findViewById(R.id.grid_item_label);
        editText.setText(textViewValues[position]);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                    textViewValues[position]=s.toString();
            }
        });
        return gridView;
    }


    @Override
    public int getCount() {
        return textViewValues.length;
    }

    @Override
    public Object getItem(int position) {
        return textViewValues[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
