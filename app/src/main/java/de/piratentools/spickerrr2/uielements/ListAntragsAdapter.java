package de.piratentools.spickerrr2.uielements;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;

import de.piratentools.spickerrr2.R;
import de.piratentools.spickerrr2.model.Antrag;

/**
 * Created by lostincoding on 20.06.16.
 */
public class ListAntragsAdapter extends ArrayAdapter<Antrag> {
    private final Activity context;
    private final Antrag[] antraege;

    static class ViewHolder {
        public TextView id;
        public TextView title;
        public TextView textPreview;
    }

    public ListAntragsAdapter(Activity context, Antrag[] antraege) {
        super(context, R.layout.advanced_antrags_list_item, antraege);
        this.context = context;
        this.antraege = antraege;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View myView = convertView;
        // reuse views
        if (myView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            myView = inflater.inflate(R.layout.advanced_antrags_list_item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.id = myView.findViewById(R.id.idviewnew);
            viewHolder.title = myView.findViewById(R.id.titleviewnew);
            viewHolder.textPreview = myView.findViewById(R.id.textpreview);
            myView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) myView.getTag();


        Antrag currentAntrag = antraege[position];

        holder.id.setText(currentAntrag.id());

        holder.title.setText(currentAntrag.title());
        String descriptionText = Jsoup.parse(currentAntrag.description()).text();
        holder.textPreview.setText(descriptionText);
        return myView;
    }
}




