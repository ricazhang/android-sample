package edu.duke.oit.httpcolab_sbx_218.innovationcolabsample;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PrinterListActivity extends Activity {
    private ListView lvPrinters;
    private EditText etSearch;
    private PrinterAdapter adapterPrinter;
    private PrinterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_list);

        lvPrinters = (ListView) findViewById(R.id.lvPrinters);
        etSearch = (EditText) findViewById(R.id.etSearchPrinters);

        ArrayList<Printer> printers = new ArrayList<Printer>();
        adapterPrinter = new PrinterAdapter(this, printers);
        lvPrinters.setAdapter(adapterPrinter);

        fetchPrinters();
        setupSearch();
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                PrinterListActivity.this.getAdapter().getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void fetchPrinters() {
        client = new PrinterClient();
        client.getPrinters(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<Printer> printers = Printer.fromJson(response);
                for (Printer p : printers) {
                    adapterPrinter.add(p);
                }
                adapterPrinter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_printer_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public PrinterAdapter getAdapter() {
        return adapterPrinter;
    }
}
