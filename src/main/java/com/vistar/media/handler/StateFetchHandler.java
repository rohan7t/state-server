package com.vistar.media.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vistar.media.model.GeoLocation;

/**
 * @author Rohan Tigadi
 */

@Component("stateFetchHandler")
public class StateFetchHandler {

    /**
     * Function to check if GeoLocation q lies on segment connecting GeoLocation p
     * and GeoLocation r.
     * 
     * @param p GeoLocation point 1
     * @param q GeoLocation point 2 to be checked upon
     * @param r GeoLocation point 3
     * @return True if GeoLocation point p lies on segment joining GeoLocation point
     *         q and GeoLocation point r, else False
     */
    private static boolean onSegment(GeoLocation p, GeoLocation q, GeoLocation r) {
	if (q.getLatitude() <= Math.max(p.getLatitude(), r.getLatitude())
		&& q.getLatitude() >= Math.min(p.getLatitude(), r.getLatitude())
		&& q.getLongitude() <= Math.max(p.getLongitude(), r.getLongitude())
		&& q.getLongitude() >= Math.min(p.getLongitude(), r.getLongitude())) {
	    return true;
	}
	return false;
    }

    /**
     * Function to return the orientation of the three points.
     * 
     * @param p GeoLocation point 1
     * @param q GeoLocation point 2
     * @param r GeoLocation point 3
     * @return Integer value of orientation. 0 - colinear, 1 - clockwise, 2 -
     *         counterclockwise
     */
    private static int orientation(GeoLocation p, GeoLocation q, GeoLocation r) {
	double val = (q.getLongitude() - p.getLongitude()) * (r.getLatitude() - q.getLatitude())
		- (q.getLatitude() - p.getLatitude()) * (r.getLongitude() - q.getLongitude());

	if (val == 0) {
	    return 0;
	}
	return (val > 0) ? 1 : 2;
    }

    /**
     * Function to check if segment joining GeoLocation point p1 and GeoLocation
     * point q1 intersects segment joining GeoLocation point p2 and GeoLocation
     * point q2.
     * 
     * @param p1 GeoLocation point 1 (segment 1)
     * @param q1 GeoLocation point 2 (segment 1)
     * @param p2 GeoLocation point 3 (segment 2)
     * @param q2 GeoLocation point 4 (segment 2)
     * @return
     */
    private static boolean doIntersect(GeoLocation p1, GeoLocation q1, GeoLocation p2, GeoLocation q2) {

	int o1 = orientation(p1, q1, p2);
	int o2 = orientation(p1, q1, q2);
	int o3 = orientation(p2, q2, p1);
	int o4 = orientation(p2, q2, q1);

	if (o1 != o2 && o3 != o4)
	    return true;

	if (o1 == 0 && onSegment(p1, p2, q1))
	    return true;

	if (o2 == 0 && onSegment(p1, q2, q1))
	    return true;

	if (o3 == 0 && onSegment(p2, p1, q2))
	    return true;

	if (o4 == 0 && onSegment(p2, q1, q2))
	    return true;

	return false;
    }

    /**
     * Function to check if a given GeoLocation point is in the mentioned borders
     * represented by List of GeoLocation points using Ray Casting algorithm.
     * 
     * @param list of GeoLocation points of a State
     * @param p    Location to be checked
     * @return True if present in the region, else False
     */
    public boolean isInside(List<GeoLocation> list, GeoLocation p) {
	if (list == null) {
	    throw new IllegalArgumentException();
	}
	double INF = 10000;
	int n = list.size();
	if (n < 3)
	    return false;

	GeoLocation extreme = new GeoLocation(INF, p.getLongitude());

	int count = 0, i = 0;
	do {
	    int next = (i + 1) % n;
	    if (doIntersect(list.get(i), list.get(next), p, extreme)) {
		if (orientation(list.get(i), p, list.get(next)) == 0)
		    return onSegment(list.get(i), p, list.get(next));

		count++;
	    }
	    i = next;
	} while (i != 0);

	return (count & 1) == 1 ? true : false;
    }
}
