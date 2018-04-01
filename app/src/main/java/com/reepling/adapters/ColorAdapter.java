package com.reepling.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reepling.R;
import com.reepling.model.ThemeItem;

/**
 * Created by Michaël on 06/03/2018.
 */

public class ColorAdapter extends BaseAdapter{

    private final Context mContext;
    private final ThemeItem[] themeItems;

    public ColorAdapter(@NonNull Context context, @NonNull ThemeItem[] themeItems) {
        this.mContext = context;
        this.themeItems = themeItems;
    }

    // 2
    @Override
    public int getCount() {
        return themeItems.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public ThemeItem getItem(int position) {
        return themeItems[position];
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1
        final ThemeItem themeItem = themeItems[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_row_settings_color, null);
        }

        // 3
        final TextView colorView = convertView.findViewById(R.id.item_tv_settings_color);

        // 4
        try{

            colorView.setBackgroundColor( themeItem.getResourceColorId() );
        }catch (Exception e){
            e.printStackTrace();
        }

        return convertView;
    }
}
