/**
 * 
 */
package com.olympicgames.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * <p>
 * Entity related to an Olympics competition.
 * </p>
 *
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 *
 */
@Entity
public class Competition {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Modality is a mandatory information.")
	private String modality;

	@NotEmpty(message = "Local is a mandatory information.")
	private String local;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Date of Competition is a mandatory information.")
	private Date dateOfCompetition;

	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@NotNull(message = "Start Time Competition is a mandatory information.")
	private Date startTimeCompetition;
 
	@JsonFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@NotNull(message = "End Time Competition is a mandatory information.")
	private Date endTimeCompetition;

	@NotEmpty(message = "Team is a mandatory information.")
	private String team;

	@NotEmpty(message = "Opponent is a mandatory information.")
	private String opponent;
	
	@NotEmpty(message = "Stage is a mandatory information.")
	private String stage;

	
	/**
	 * 
	 */
	public Competition() {
		super();
	}
	
	/**
	 * @param modality
	 * @param local
	 * @param dateOfCompetition
	 * @param startTimeCompetition
	 * @param endTimeCompetition
	 * @param team
	 * @param opponent
	 * @param stage
	 */
	public Competition(String modality, String local, Date dateOfCompetition, Date startTimeCompetition,
			Date endTimeCompetition, String team, String opponent, String stage) {
		super();
		this.modality = modality;
		this.local = local;
		this.dateOfCompetition = dateOfCompetition;
		this.startTimeCompetition = startTimeCompetition;
		this.endTimeCompetition = endTimeCompetition;
		this.team = team;
		this.opponent = opponent;
		this.stage = stage;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the modality
	 */
	public String getModality() {
		return modality;
	}

	/**
	 * @param modality the modality to set
	 */
	public void setModality(String modality) {
		this.modality = modality;
	}

	/**
	 * @return the local
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * @return the dateOfCompetition
	 */
	public Date getDateOfCompetition() {
		return dateOfCompetition;
	}

	/**
	 * @param dateOfCompetition the dateOfCompetition to set
	 */
	public void setDateOfCompetition(Date dateOfCompetition) {
		this.dateOfCompetition = dateOfCompetition;
	}

	/**
	 * @return the startTimeCompetition
	 */
	public Date getStartTimeCompetition() {
		return startTimeCompetition;
	}

	/**
	 * @param startTimeCompetition the startTimeCompetition to set
	 */
	public void setStartTimeCompetition(Date startTimeCompetition) {
		this.startTimeCompetition = startTimeCompetition;
	}

	/**
	 * @return the endTimeCompetition
	 */
	public Date getEndTimeCompetition() {
		return endTimeCompetition;
	}

	/**
	 * @param endTimeCompetition the endTimeCompetition to set
	 */
	public void setEndTimeCompetition(Date endTimeCompetition) {
		this.endTimeCompetition = endTimeCompetition;
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the opponent
	 */
	public String getOpponent() {
		return opponent;
	}

	/**
	 * @param opponent the opponent to set
	 */
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}

	

}
