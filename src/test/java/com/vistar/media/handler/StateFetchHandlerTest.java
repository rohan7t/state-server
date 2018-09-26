package com.vistar.media.handler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vistar.media.model.GeoLocation;

/**
 * @author Rohan Tigadi
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateFetchHandlerTest {

    @Autowired
    StateFetchHandler stateFetchHandler;

    @Test
    public void testIsInsidePositive() {
	List<GeoLocation> list = new ArrayList<GeoLocation>();
	list.add(new GeoLocation(-77.475793, 39.719623));
	list.add(new GeoLocation(-80.524269, 39.721209));
	list.add(new GeoLocation(-80.520592, 41.986872));
	list.add(new GeoLocation(-74.705273, 41.375059));
	list.add(new GeoLocation(-75.142901, 39.881602));
	list.add(new GeoLocation(-77.475793, 39.719623));

	GeoLocation p = new GeoLocation(-77.036133, 40.513799);
	assertTrue(stateFetchHandler.isInside(list, p));
    }

    @Test
    public void testIsInsideNegative() {
	List<GeoLocation> list = new ArrayList<GeoLocation>();
	list.add(new GeoLocation(-77.475793, 39.719623));
	list.add(new GeoLocation(-80.524269, 39.721209));
	list.add(new GeoLocation(-80.520592, 41.986872));
	list.add(new GeoLocation(-74.705273, 41.375059));
	list.add(new GeoLocation(-75.142901, 39.881602));
	list.add(new GeoLocation(-77.475793, 39.719623));

	GeoLocation p = new GeoLocation(77.036133, 40.513799);
	assertFalse(stateFetchHandler.isInside(list, p));
    }

    @Test
    public void testIsInsideNoPolygon() {
	List<GeoLocation> list = new ArrayList<GeoLocation>();
	list.add(new GeoLocation(-77.475793, 39.719623));
	list.add(new GeoLocation(-80.524269, 39.721209));

	GeoLocation p = new GeoLocation(77.036133, 40.513799);
	assertFalse(stateFetchHandler.isInside(list, p));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsInsideInvalid() {
	List<GeoLocation> list = new ArrayList<GeoLocation>();
	list.add(new GeoLocation(-77.475793, 39.719623));
	list.add(new GeoLocation(-80.524269, 39.721209));
	list.add(new GeoLocation(-80.520592, 41.986872));
	list.add(new GeoLocation(-74.705273, 41.375059));
	list.add(new GeoLocation(-75.142901, 39.881602));
	list.add(new GeoLocation(-77.475793, 39.719623));

	GeoLocation p = new GeoLocation(77.036133, 40.513799);
	stateFetchHandler.isInside(null, p);
    }

}
