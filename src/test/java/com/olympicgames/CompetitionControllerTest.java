package com.olympicgames;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.olympicgames.model.Competition;
import com.olympicgames.model.Stage;

/**
 * <p>
 * * Service Test Controller Rest Competition.
 * </p>
 *
 * @author <a href="mailto:alexcalaguag@gmai.com">Alex Calagua</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OlympicGamesApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompetitionControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	/**
	 * Method that tests the creation of a competition via API rest
	 */
	@Test
	public void createCompetitionsTest() {
		ResponseEntity<String> response = createCompetition();
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.CREATED));
		Assert.isTrue(actual.contains("/olympicgames/rest/competition/"));
	}

	/**
	 * Method that tests the service that returns registrations of competitions already registered
	 */
	@Test
	public void getCompetitionsTest() {
		createCompetition();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/olympicgames/rest/competitions/"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"id\":1,\"modality\":\"Volei\",\"local\":\"Estadio Maracana\",\"dateOfCompetition\":\"2017-10-01\",\"startTimeCompetition\":\"10:30:00\",\"endTimeCompetition\":\"12:40:00\",\"team\":\"Argentina\",\"opponent\":\"Brasil\",\"stage\":\"Eliminations\"}]";
		Assert.isTrue(expected.equals(response.getBody()));

	}

	/**
	 * Method that performs the creation call of the competition
	 * @return ResponseEntity<String>
	 */
	private ResponseEntity<String> createCompetition() {
		Competition competition = new Competition("Volei", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T10:30:00.000Z").toDate(),
				new DateTime("1970-01-01T12:40:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());
		HttpEntity<Competition> entity = new HttpEntity<Competition>(competition, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/olympicgames/rest/competition/"),
				HttpMethod.POST, entity, String.class);
		return response;
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
