package com.lambiengcode.memorygame.ui.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lambiengcode.memorygame.MainActivity;

import java.util.List;

public class TileAdapter extends BaseAdapter {
    Context context;
    List<Boolean> results;

    public TileAdapter(Context context, List<Boolean> results) {
        this.context = context;
        this.results = results;
        //this.shows.addAll(Collections.nCopies(36, false));
    }

    @Override
    public int getCount() {
        return results.size();
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
        double width = MainActivity.widthScreen;
        int sizeMarginHorizontal = 16 * 2;
        int sizeSpaceWidth = dpToPx(4, context);
        TextView tvCard = new TextView(context);
        tvCard.setText("");
        tvCard.setTextColor(Color.WHITE);
        tvCard.setBackgroundColor(results.get(position) ? 0xFF61D7A8 : 0xFF93c4f6);
        tvCard.setGravity(Gravity.CENTER);
        if (results.size() == 100) {
            tvCard.setHeight(((int)Math.round(width - 15 * sizeSpaceWidth - sizeMarginHorizontal) / 10));
        }else if (results.size() == 64) {
            tvCard.setHeight(((int)Math.round(width - 13 * sizeSpaceWidth - sizeMarginHorizontal) / 8));
        } else {
            tvCard.setHeight(((int)Math.round(width - 11 * sizeSpaceWidth - sizeMarginHorizontal) / 6));
        }
        return tvCard;
    }

    public int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
