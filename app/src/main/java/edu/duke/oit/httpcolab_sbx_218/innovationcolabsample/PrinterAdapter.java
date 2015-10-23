package edu.duke.oit.httpcolab_sbx_218.innovationcolabsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ricazhang on 10/20/15.
 */
public class PrinterAdapter extends ArrayAdapter<Printer> implements Filterable {
    public PrinterAdapter(Context context, ArrayList<Printer> printersArray) {
        super(context, 0, printersArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Printer printer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_printer, null);
        }
        // Lookup view for data population
        TextView printerName = (TextView) convertView.findViewById(R.id.tvPrinterName);
        TextView printerLocation = (TextView) convertView.findViewById(R.id.tvPrinterLocation);
        TextView printerStatus = (TextView) convertView.findViewById(R.id.tvPrinterStatus);
        // Populate the data into the template view using the data object
        printerName.setText(printer.getName());
        printerLocation.setText("Location: " + printer.getLocation());
        String printerStatusString = "";
        for (String status: printer.getStatuses()) {
            printerStatusString += status + "\n";
        }
        printerStatus.setText(printerStatusString);
        //Picasso.with(getContext()).load(printer.getPosterUrl()).into(ivPosterImage);
        // Return the completed view to render on screen
        return convertView;
    }
}
