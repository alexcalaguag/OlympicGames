package com.olympicgames.model;

/**
 * 
 * <p>
 * Enum of the competition stages.
 * </p>
 *
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 *
 */
public enum Stage {
	ELIMINATIONS("Eliminations"), EIGHTHFINALS("Fighth Finals"), QUARTERFINALS("Quarter Finals"), SEMIFINAL(
			"Semifinal"), FINAL("Final");

	/**
	 * Stage Description
	 */
	private String description;

	/**
	 * Construct Enum Stage
	 * 
	 * @param description
	 */
	Stage(String description) {
		this.description = description;
	}

	/**
	 * Method that returns the enum stage from the description of stage
	 * 
	 * @param description
	 * @return Stage
	 */
	public static Stage getStageFromDescription(String description) {
		for (Stage stage : Stage.values()) {
			if (stage.description.equals(description))
				return stage;
		}
		return null;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
