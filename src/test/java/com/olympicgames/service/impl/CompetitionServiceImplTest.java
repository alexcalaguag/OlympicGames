package com.olympicgames.service.impl;

import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.olympicgames.OlympicGamesApp;
import com.olympicgames.exception.ValidateException;
import com.olympicgames.model.Competition;
import com.olympicgames.model.Stage;
import com.olympicgames.service.CompetitionService;

/**
 * <p>
 * Service Test Validation Service Competition.
 * </p>
 *
 * @author <a href="mailto:alexcalaguag@gmai.com">Alex Calagua</a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { OlympicGamesApp.class })
@SpringBootTest
public class CompetitionServiceImplTest {
	/**
	 * Service which will do all data retrieval/manipulation work
	 */
	@Autowired
	CompetitionService competitionService;

	/**
	 * Method that test competitions registered on the same day and at the same time
	 */
	@Test
	@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
	public void validateCompetitionSameTimeTest() {
		Competition competition1 = new Competition("Futebol", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T10:00:00.000Z").toDate(),
				new DateTime("1970-01-01T10:30:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());
		Competition competition2 = new Competition("Futebol", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T10:00:00.000Z").toDate(),
				new DateTime("1970-01-01T10:30:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());
		try {
			competitionService.saveCompetition(competition1);
			competitionService.saveCompetition(competition2);
			fail("Expected an ValidateException to be thrown");
		} catch (ValidateException e) {
			Assert.isTrue("EXCEPTION_COMPETITION_SAME_TIME".equals(e.getErrorKey()));

		}
	}

	/**
	 * Method that tests the number exceeded of competitions in the same place
	 */
	@Test
	@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
	public void validateCompetitionSameLocalTest() {
		Competition competition1 = new Competition("Volei", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T10:30:00.000Z").toDate(),
				new DateTime("1970-01-01T11:30:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());
		Competition competition2 = new Competition("Futebol", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T11:30:00.000Z").toDate(),
				new DateTime("1970-01-01T12:30:00.000Z").toDate(), "Bolivia", "China",
				Stage.ELIMINATIONS.getDescription());
		Competition competition3 = new Competition("Natacao", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T12:30:00.000Z").toDate(),
				new DateTime("1970-01-01T13:30:00.000Z").toDate(), "Chile", "Argentina",
				Stage.ELIMINATIONS.getDescription());
		Competition competition4 = new Competition("Ciclismo", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T13:30:00.000Z").toDate(),
				new DateTime("1970-01-01T14:30:00.000Z").toDate(), "Italia", "Colombia",
				Stage.ELIMINATIONS.getDescription());
		Competition competition5 = new Competition("Karate", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T14:30:00.000Z").toDate(),
				new DateTime("1970-01-01T15:30:00.000Z").toDate(), "Portugal", "Brasil",
				Stage.ELIMINATIONS.getDescription());
		try {
			competitionService.saveCompetition(competition1);
			competitionService.saveCompetition(competition2);
			competitionService.saveCompetition(competition3);
			competitionService.saveCompetition(competition4);
			competitionService.saveCompetition(competition5);
			fail("Expected an ValidateException to be thrown");
		} catch (ValidateException e) {
			Assert.isTrue("EXCEPTION_COMPETITION_SAME_LOCAL".equals(e.getErrorKey()));

		}
	}

	/**
	 * Method that test the duration of a competition invalid
	 */
	@Test
	@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
	public void validateDurationCompetitionTest() {
		Competition competition1 = new Competition("Volei", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T10:30:00.000Z").toDate(),
				new DateTime("1970-01-01T10:40:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());

		try {
			competitionService.saveCompetition(competition1);
			fail("Expected an ValidateException to be thrown");
		} catch (ValidateException e) {
			Assert.isTrue("EXCEPTION_COMPETITION_DURATION".equals(e.getErrorKey()));
		}
	}

	/**
	 * Method that tests the insertion of a invalid stage
	 */
	@Test
	@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
	public void validateStageCompetitionTest() {
		Competition competition1 = new Competition("Volei", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T10:30:00.000Z").toDate(),
				new DateTime("1970-01-01T10:40:00.000Z").toDate(), "Argentina", "Brasil", "INITIAL");

		try {
			competitionService.saveCompetition(competition1);
			fail("Expected an ValidateException to be thrown");
		} catch (ValidateException e) {
			Assert.isTrue("EXCEPTION_COMPETITION_STAGE".equals(e.getErrorKey()));
		}
	}

	/**
	 * Method that tests competitions registered different the stage Final e Semifinal
	 */
	@Test
	@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
	public void validateCompetitionFinalAndSemifinalTest() {
		Competition competition1 = new Competition("Volei", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T19:30:00.000Z").toDate(),
				new DateTime("1970-01-01T20:40:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());

		Competition competition2 = new Competition("Volei", "Estadio Maracana",
				new DateTime("2017-10-01T00:00:00.000Z").toDate(), new DateTime("1970-01-01T21:40:00.000Z").toDate(),
				new DateTime("1970-01-01T22:40:00.000Z").toDate(), "Argentina", "Brasil",
				Stage.ELIMINATIONS.getDescription());

		try {
			competitionService.saveCompetition(competition1);
			competitionService.saveCompetition(competition2);
			fail("Expected an ValidateException to be thrown");
		} catch (ValidateException e) {
			Assert.isTrue("EXCEPTION_COMPETITION_DIFFERENT_FINAL_AND_SEMIFINAL".equals(e.getErrorKey()));
		}
	}
}
