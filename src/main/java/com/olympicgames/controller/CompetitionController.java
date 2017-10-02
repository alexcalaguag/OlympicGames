package com.olympicgames.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.olympicgames.exception.ErrorResponseValidation;
import com.olympicgames.exception.ValidateException;
import com.olympicgames.model.Competition;
import com.olympicgames.service.CompetitionService;

/**
 * Implement the Competition controller.
 *
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 */
@RestController
@RequestMapping("/rest")
public class CompetitionController {

	public static final Logger logger = LoggerFactory.getLogger(CompetitionController.class);

	/**
	 * Service Competition Service
	 */
	@Autowired
	CompetitionService competitionService;

	/**
	 * Return a list of all the competitions.
	 * 
	 * @param modality
	 * @return ResponseEntity: A response containing all the competitions
	 */
	@RequestMapping(value = "/competitions/", method = RequestMethod.GET)
	public ResponseEntity<List<Competition>> getCompetitions(
			@RequestParam(value = "modality", required = false) String modality) {
		List<Competition> competitions = competitionService.getCompetitionsByModality(modality);
		return new ResponseEntity<List<Competition>>(competitions, HttpStatus.OK);
	}

	/**
	 * Create a competition
	 * 
	 * @param competition
	 * @param ucBuilder
	 * @return ResponseEntity: A response containing url the competition just
	 *         created.
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/competition/", method = RequestMethod.POST)
	public ResponseEntity<?> createCompetitions(@Valid @RequestBody Competition competition,
			UriComponentsBuilder ucBuilder) throws ValidateException {
		competitionService.saveCompetition(competition);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/competition/{id}").buildAndExpand(competition.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Method that treats the Validate Exception
	 * 
	 * @param exception
	 * @return ResponseEntity: A response containing a validation exception.
	 */
	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<ErrorResponseValidation> exceptionHandler(ValidateException exception) {
		List<String> errors = new ArrayList<String>();
		errors.add(exception.getErrorMessage());
		return new ResponseEntity<ErrorResponseValidation>(mountErrorResponse(errors), HttpStatus.OK);
	}

	/**
	 * Method that treats the MethodArgumentNotValid Exception
	 * 
	 * @param exception
	 * @return ResponseEntity: A response containing a validation exception.
	 */
	@ExceptionHandler()
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseValidation> handleException(MethodArgumentNotValidException exception) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		}
		return new ResponseEntity<ErrorResponseValidation>(mountErrorResponse(errors), HttpStatus.OK);
	}

	/**
	 * Method that mount the ErroResponseValidation
	 * 
	 * @param errors
	 * @return errorResponse
	 */
	private ErrorResponseValidation mountErrorResponse(List<String> errors) {
		ErrorResponseValidation errorResponse = new ErrorResponseValidation();
		errorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		errorResponse.setErrors(errors);
		return errorResponse;
	}

}
