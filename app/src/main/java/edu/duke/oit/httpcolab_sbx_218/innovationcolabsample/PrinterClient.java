package edu.duke.oit.httpcolab_sbx_218.innovationcolabsample;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by ricazhang on 10/20/15.
 */
public class PrinterClient {
    private final String API_KEY = "3ad251733ebd8496b96b5d9eccc21da4";
    private final String API_BASE_URL = "https://streamer.oit.duke.edu/eprint/printers?";
    private AsyncHttpClient client;

    public PrinterClient() {
        this.client = new AsyncHttpClient();
    }

    // http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=<key>
    public void getPrinters(JsonHttpResponseHandler handler) {
        String url = getApiUrl("printers?");
        RequestParams params = new RequestParams("access_token", API_KEY);
        client.get(url, params, handler);
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }
}
