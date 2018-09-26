package com.vistar.media.handler;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vistar.media.model.GeoLocation;

/**
 * @author Rohan Tigadi
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateDataHandlerTest {

    @Test
    public void testFetchAllStatesPositive() {
	HashMap<String, List<GeoLocation>> allStates = StateDataHandler.fetchAllStates();
	assertEquals(allStates.size(), 43);
    }
}
