package com.olympicgames.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.olympicgames.model.Competition;

/**
 * Repository the Competition Entity
 * 
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 *
 */
@RepositoryRestResource
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	/**
	 * Resource that returns a list of competitions by modality.
	 * 
	 * @param modality
	 * @return List the Competitions
	 */
	List<Competition> findByModality(String modality);

	/**
	 * Resource that returns a list of competitions by local and date the a
	 * competition.
	 * 
	 * @param local
	 * @param dateOfCompetition
	 * @return List the Competitions
	 */
	List<Competition> findByLocalAndDateOfCompetition(String local, Date dateOfCompetition);

	/**
	 * Resource that returns a list of chronologically ordered competitions
	 * 
	 * @return List the Competitions
	 */
	List<Competition> findAllByOrderByDateOfCompetitionDesc();
	
	/**
	 * Resource that returns a list of competitions by local and date the a
	 * competition.
	 * @param team
	 * @param opponent
	 * @param modality
	 * @return List the Competitions
	 */
	List<Competition> findByTeamInAndOpponentInAndModality(Collection<String> team,Collection<String> opponent,String modality);


}
