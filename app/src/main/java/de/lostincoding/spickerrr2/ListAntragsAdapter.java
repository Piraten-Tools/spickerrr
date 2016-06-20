package de.lostincoding.spickerrr2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.lostincoding.spickerrr2.model.Antrag;

/**
 * Created by lostincoding on 20.06.16.
 */
public class ListAntragsAdapter extends ArrayAdapter<Antrag> {
    private final Activity context;
    private final Antrag[] antraege;

    static class ViewHolder {
        public TextView id;
       public TextView title;
    }

    public ListAntragsAdapter(Activity context, Antrag[] antraege) {
        super(context, R.layout.advanced_antrags_list_item, antraege);
        this.context = context;
        this.antraege = antraege;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.advanced_antrags_list_item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.id = (TextView) rowView.findViewById(R.id.idview);
            viewHolder.title =(TextView) rowView.findViewById(R.id.titleview);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        Antrag currentAntrag=antraege[position];
        holder.id.setText(currentAntrag.getId());
        holder.title.setText(currentAntrag.getTitle());


        return rowView;
    }
}




