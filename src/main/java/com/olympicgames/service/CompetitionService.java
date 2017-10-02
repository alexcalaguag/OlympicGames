package com.olympicgames.service;

import java.util.List;

import com.olympicgames.exception.ValidateException;
import com.olympicgames.model.Competition;

/**
 * 
 * <p>
 * Service Interface Related to Competition.
 * </p>
 *
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 *
 */
public interface CompetitionService {

	/**
	 * 
	 * Service that saves a competition.
	 * 
	 * @param competition
	 * @throws ValidateException
	 */

	void saveCompetition(Competition competition) throws ValidateException;
	/**
	 * Service that return all competitions.
	 * 
	 * @return List the Competitions
	 */
	List<Competition> getCompetitions();

	/**
	 * Service that return all competitions by modality.
	 * 
	 * @param modality
	 * @return List the Competitions
	 */
	List<Competition> getCompetitionsByModality(String modality);

}
