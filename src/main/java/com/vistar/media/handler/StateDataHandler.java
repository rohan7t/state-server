package com.vistar.media.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vistar.media.model.GeoLocation;

/**
 * @author Rohan Tigadi
 */

public class StateDataHandler {

    private static final String STATES_JSON_FILE_LOCATION = "/static/states.json";
    private static final String STATE_IDENTIFIER = "state";
    private static final String BORDER_IDENTIFIER = "border";

    /**
     * Function that returns all the states present in the input file in the
     * static/states.json
     * 
     * @return HashMap with state name as Key and List of border points as Value
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, List<GeoLocation>> fetchAllStates() {
	HashMap<String, List<GeoLocation>> allStates = new HashMap<String, List<GeoLocation>>();
	JSONParser parser = new JSONParser();
	try {
	    InputStream ip = StateDataHandler.class.getResourceAsStream(STATES_JSON_FILE_LOCATION);
	    BufferedReader buffReader = new BufferedReader(new InputStreamReader(ip));
	    String line;
	    while ((line = buffReader.readLine()) != null) {
		Object obj = parser.parse(line);

		JSONObject jsonObject = (JSONObject) obj;

		String state = (String) jsonObject.get(STATE_IDENTIFIER);
		JSONArray borderList = (JSONArray) jsonObject.get(BORDER_IDENTIFIER);

		Iterator<ArrayList<Double>> iterator = borderList.iterator();
		ArrayList<Double> latlong = new ArrayList<Double>();
		List<GeoLocation> borders = new ArrayList<GeoLocation>();
		while (iterator.hasNext()) {
		    latlong = iterator.next();
		    borders.add(new GeoLocation(latlong.get(1), latlong.get(0)));
		}
		allStates.put(state, borders);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return allStates;
    }
}