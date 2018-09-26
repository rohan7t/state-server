package com.vistar.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vistar.media.handler.StateDataHandler;
import com.vistar.media.handler.StateFetchHandler;
import com.vistar.media.model.GeoLocation;

/**
 * @author Rohan Tigadi
 */

@RestController
public class StateController {

    private static final HashMap<String, List<GeoLocation>> STATES_LIST = StateDataHandler.fetchAllStates();
    private static final String LOCATION_INVALID = "The location you provided is not valid\n";
    private static final String LOCATION_NOT_IN_US = "This location is not in the United States";
    private static final String ERROR_OCCURRED = "An error occurred. Please try again\n";
    private static final String NEW_LINE = "\n";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    @Autowired
    private StateFetchHandler stateFetchHandler;

    /**
     * Function that maps POST request to URL '/' for fetching the state in which a
     * point resides.
     * 
     * @param body containing latitude and longitude of the point
     * @return String State in which point lies
     */
    @PostMapping("/")
    public String fetchState(@RequestParam Map<String, String> body) {
	Double latitude, longitude;
	try {
	    latitude = Double.parseDouble(body.get(LATITUDE));
	    longitude = Double.parseDouble(body.get(LONGITUDE));
	    if (latitude == null || longitude == null || latitude.isNaN() || longitude.isNaN()) {
		return LOCATION_INVALID;
	    }
	} catch (NumberFormatException | NullPointerException e) {
	    return LOCATION_INVALID;
	}
	try {
	    GeoLocation point = new GeoLocation(latitude, longitude);
	    boolean found = false;
	    String result = LOCATION_NOT_IN_US;
	    for (Entry<String, List<GeoLocation>> entry : STATES_LIST.entrySet()) {
		found = stateFetchHandler.isInside(entry.getValue(), point);
		if (found) {
		    result = entry.getKey();
		    break;
		}
	    }
	    return result.concat(NEW_LINE);
	} catch (Exception e) {
	    return ERROR_OCCURRED;
	}
    }

}
