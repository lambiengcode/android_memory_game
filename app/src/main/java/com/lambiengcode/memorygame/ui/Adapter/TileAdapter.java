package com.lambiengcode.memorygame.ui.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class TileAdapter extends BaseAdapter {
    Context context;
    List<Boolean> values;

    public TileAdapter(Context context, List<Boolean> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dummyTextView = new TextView(context);
        dummyTextView.setText("");
        dummyTextView.setTextColor(Color.WHITE);
        dummyTextView.setBackgroundColor(values.get(position) ? 0xFF61D7A8 : 0xFF93c4f6);
        dummyTextView.setGravity(Gravity.CENTER);
        dummyTextView.setHeight(150);
        dummyTextView.setWidth(200);

        return dummyTextView;
    }
}
