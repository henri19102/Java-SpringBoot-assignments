
package movies;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorTest extends org.fluentlenium.adapter.junit.FluentTest {
    
    public ActorTest() {
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
    // @Test
    // public void hello() {}
    
    @LocalServerPort
    private Integer port;

    @Test
    public void canSignUp() {
        // Avaa sivu "/ilmoittautuminen"
        goTo("http://localhost:" + port + "/actors");

        // Varmistetaan ettei ilmoittautuneissa ole Rollea
        assertFalse(pageSource().contains("Uuno Turhapuro"));

        // Etsi kenttä, jonka attribuutin 'id' arvo on nimi ja täytä kentän arvoksi Rolle
        find("#name").fill().with("Uuno Turhapuro");

        // Lähetä lomake
        find("form").first().submit();

        // Varmista, että sivulle on lisätty Rolle
        assertTrue(pageSource().contains("Uuno Turhapuro"));

    }
}
