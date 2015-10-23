package edu.duke.oit.httpcolab_sbx_218.innovationcolabsample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricazhang on 10/20/15.
 */
public class Printer implements Serializable {
    private String id;
    private String name;
    private String type;
    private String location;
    private String siteId;
    private List<String> statuses = new ArrayList<>();

    public static Printer fromJson(JSONObject jsonObject) {
        Printer p = new Printer();
        try {
            p.id = jsonObject.getString("id");
            p.name = jsonObject.getString("name");
            p.type = jsonObject.getString("type");
            p.location = jsonObject.getString("site");
            p.siteId = jsonObject.getString("site_id");
            JSONArray statusesArray = jsonObject.getJSONArray("statuses");
            for (int i = 0; i < statusesArray.length(); i++) {
                String statusName = statusesArray.getJSONObject(i).getString("name");
                String statusDate = statusesArray.getJSONObject(i).getString("created_at");
                p.statuses.add(statusName + " " + statusDate);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }

    public static ArrayList<Printer> fromJson(JSONArray jsonArray) {
        ArrayList<Printer> businesses = new ArrayList<Printer>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject businessJson = null;
            try {
                businessJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Printer business = Printer.fromJson(businessJson);
            if (business != null) {
                businesses.add(business);

            }
        }

        return businesses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return this.location;
    }
}
