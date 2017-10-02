package com.olympicgames.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.olympicgames.exception.ValidateException;
import com.olympicgames.model.Competition;
import com.olympicgames.model.Stage;
import com.olympicgames.repository.CompetitionRepository;
import com.olympicgames.service.CompetitionService;

/**
 * 
 * <p>
 * Service Implementation Related to Competition.
 * </p>
 *
 * @author <a href="mailto:alexcalaguag@gmai.com">Alex Calagua</a>
 *
 */
@Service("competitionService")
public class CompetitionServiceImpl implements CompetitionService {

	/**
	 * Repository the Competition
	 */
	@Autowired
	private CompetitionRepository repository;

	@Override
	public void saveCompetition(Competition competition) throws ValidateException {
		this.validate(competition);
		repository.save(competition);
	}

	@Override
	public List<Competition> getCompetitions() {
		return (List<Competition>) repository.findAll();
	}

	@Override
	public List<Competition> getCompetitionsByModality(String modality) {
		if (StringUtils.isEmpty(modality)) {
			return (List<Competition>) repository.findAllByOrderByDateOfCompetitionDesc();
		} else {
			return (List<Competition>) repository.findByModality(modality);
		}
	}

	/**
	 * Method that validates the registration of a competition
	 * 
	 * @param competition
	 * @throws ValidateException
	 */
	private void validate(Competition competition) throws ValidateException {
		validateStageCompetition(competition);
		validateDurationCompetition(competition);
		validateCompetitionSameLocal(competition);
		validateCompetitionSameTime(competition);
		validateCompetitionFinalAndSemifinal(competition);
	}

	/**
	 * Method that validates competitions registered different the stage Final e Semifinal
	 * @param competition
	 * @throws ValidateException
	 */
	private void validateCompetitionFinalAndSemifinal(Competition competition) throws ValidateException {
		List<String> countries = new ArrayList<>();
		countries.add(competition.getTeam());
		countries.add(competition.getOpponent());
		List<Competition> competitions = repository.findByTeamInAndOpponentInAndModality(countries, countries,
				competition.getModality());
		boolean isStageValid = competition.getStage().equals(Stage.FINAL.getDescription())
				|| competition.getStage().equals(Stage.SEMIFINAL.getDescription());
		if (!(isStageValid) && competitions.size() > 0) {
			throw new ValidateException(
					"The competition can not be registered because there is already a competition with the Final and Semifinal stages",
					"EXCEPTION_COMPETITION_DIFFERENT_FINAL_AND_SEMIFINAL");
		}
	}

	/**
	 * Method that validates competitions registered on the same day and at the same
	 * time
	 * 
	 * @param newCompetition
	 * @param competitions
	 * @throws ValidateException
	 */
	private void validateCompetitionSameTime(Competition newCompetition)
			throws ValidateException {
		List<Competition> competitions = repository.findByLocalAndDateOfCompetition(newCompetition.getLocal(),
				newCompetition.getDateOfCompetition());
		for (Competition competition : competitions) {
			Interval interval = new Interval(new DateTime(competition.getStartTimeCompetition(), DateTimeZone.UTC),
					new DateTime(competition.getEndTimeCompetition(), DateTimeZone.UTC));

			if (interval.contains(new DateTime(newCompetition.getStartTimeCompetition()))
					|| interval.contains(new DateTime(newCompetition.getEndTimeCompetition()))) {
				throw new ValidateException("Competicao " + newCompetition.getModality() + " can not be registered in "
						+ newCompetition.getLocal() + " because it is in time conflict with competition "
						+ competition.getModality() + " (" + competition.getStartTimeCompetition() + "-"
						+ competition.getEndTimeCompetition() + ")", "EXCEPTION_COMPETITION_SAME_TIME");
			}
		}
	}

	/**
	 * Method that validates the number of competitions held in the same place
	 * 
	 * @param competition
	 * @return List the Competitions
	 * @throws ValidateException
	 */
	private List<Competition> validateCompetitionSameLocal(Competition competition) throws ValidateException {
		List<Competition> competitions = repository.findByLocalAndDateOfCompetition(competition.getLocal(),
				competition.getDateOfCompetition());
		if (competitions != null && competitions.size() >= 4) {
			throw new ValidateException(
					"Not allowed more than 4 competitions in the same place " + competition.getLocal(),
					"EXCEPTION_COMPETITION_SAME_LOCAL");
		}
		return competitions;
	}
 
	/**
	 * Method that validates the duration of a competition
	 * 
	 * @param competition
	 * @throws ValidateException
	 */
	private void validateDurationCompetition(Competition competition) throws ValidateException {
		DateTime dataFinal = new DateTime(competition.getStartTimeCompetition().getTime());
		DateTime dataInicio = new DateTime(competition.getEndTimeCompetition().getTime());
		Minutes minutes = Minutes.minutesBetween(dataFinal, dataInicio);
		if (minutes.isLessThan(Minutes.minutes(30))) {
			throw new ValidateException("The duration of the competition should be longer than 30 minutes",
					"EXCEPTION_COMPETITION_DURATION");
		}
	}

	/**
	 * Method that validates the insertion of a valid stage
	 * 
	 * @param competition
	 * @throws ValidateException
	 */
	private void validateStageCompetition(Competition competition) throws ValidateException {
		Stage stage = Stage.getStageFromDescription(competition.getStage());
		if (stage == null) {
			throw new ValidateException("The stage " + competition.getStage() + " of the competition is not allowed",
					"EXCEPTION_COMPETITION_STAGE");
		}
	}

}
