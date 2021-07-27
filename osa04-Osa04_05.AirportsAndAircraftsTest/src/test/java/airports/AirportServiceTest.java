/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Henri
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    private AirportService as;

    @Autowired
    private AirportRepository ar;

    public AirportServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void newAirportToDB() {
        as.create("yy", "kaa");
        assertEquals("yy", ar.findByName("kaa").getIdentifier());
    }

    @Test
    public void airportsFromDB() {
        as.create("uu", "kee");
        as.create("hh", "kii");
        as.create("pp", "koo");

        assertEquals(4, ar.findAll().size());
    }
    
        @Test
    public void noDuplicates() {
        as.create("yy", "kaa");
        
        assertEquals(1, ar.findAll().size());
    }
}
