package org.example.util;

import jakarta.xml.bind.annotation.XmlElement;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



public class ApplicationError {
	public static HashMap<String, String> errorMessageMap = new LinkedHashMap<String, String>();
	public static final String FIELD_REQUIRED_ERR_CODE = "EXP010201"; // for requered parameter validation
	public static final String ACCESS_DENIED_ERR_CODE = "EXP010202";
	public static final String ACCESS_TOKEN_EXPIRED_ERR_CODE = "EXP010203";
	public static final String INVALID_JWT_TOKEN_ERR_CODE = "EXP010204";
	public static final String PASSWORD_CHANGE_IS_REQ_ERR_CODE = "EXP010205";
	public static final String USERNAME_OR_PASSWORD_MISMATCH_ERR_CODE = "EXP010206";
	public static final String DATE_FORMAT_NOT_MATCHED_ERR_CODE = "EXP010207";
	public static final String USER_IS_DISABLED_ERR_CODE = "EXP010207";
	public static final String DATABASE_TRANSACTION_ERR_CODE = "EXP010218";
	public static final String REQUEST_PAYLOAD_IS_INVALID_ERR_CODE = "EXP010219";
	public static final String USER_REQUEST_ALREADY_BEEN_APPROVED_ERR_CODE = "EXP010220";
	public static final String INVALID_EMAIL_ERR_CODE = "EXP010221";
	public static final String INVALID_REQUESTED_ROLE_ERR_CODE = "EXP010222";

	public static final Integer RQEXP404 = 404;

	// ERROR code descriptions
	public static final String ACCESS_DENIED_ERR_DESC = "Access Denied for this functionality !!!";
	public static final String ACCESS_TOKEN_EXPIRED_ERR_DESC = "Access token expired!!!";
	public static final String INVALID_JWT_TOKEN_ERR_DESC = "Invalid JWT token";
	public static final String PASSWORD_CHANGE_IS_REQ_ERR_DESC = "Password change is required.";
	public static final String USERNAME_OR_PASSWORD_MISMATCH_ERR_DESC = "Username or Password mismatch";
	public static final String DATE_FORMAT_NOT_MATCHED_ERR_DESC = "Date format not matched";
	public static final String USER_IS_DISABLED_ERR_DESC = "User is disabled";
	public static final String DATABASE_TRANSACTION_ERR_DESC = "Database Transaction error";
	public static final String REQUEST_PAYLOAD_IS_INVALID_ERR_DESC = "Request Payload is invalid.";
	public static final String USER_REQUEST_ALREADY_BEEN_APPROVED_ERR_DESC = "The user request is already been approved.";
	public static final String INVALID_EMAIL_ERR_DESC = "Invalid Email";
	public static final String INVALID_REQUESTED_ROLE_ERR_DESC = "Invalid Requested Role";
	static {
		errorMessageMap.put(FIELD_REQUIRED_ERR_CODE, "NA");
		errorMessageMap.put(ACCESS_DENIED_ERR_CODE, ACCESS_DENIED_ERR_DESC);
		errorMessageMap.put(ACCESS_TOKEN_EXPIRED_ERR_CODE, ACCESS_TOKEN_EXPIRED_ERR_DESC);
		errorMessageMap.put(INVALID_JWT_TOKEN_ERR_CODE, INVALID_JWT_TOKEN_ERR_DESC);
		errorMessageMap.put(PASSWORD_CHANGE_IS_REQ_ERR_CODE, PASSWORD_CHANGE_IS_REQ_ERR_DESC);
		errorMessageMap.put(USERNAME_OR_PASSWORD_MISMATCH_ERR_CODE, USERNAME_OR_PASSWORD_MISMATCH_ERR_DESC);
		errorMessageMap.put(DATE_FORMAT_NOT_MATCHED_ERR_CODE, DATE_FORMAT_NOT_MATCHED_ERR_DESC);
		errorMessageMap.put(USER_IS_DISABLED_ERR_CODE, USER_IS_DISABLED_ERR_DESC);
		errorMessageMap.put(DATABASE_TRANSACTION_ERR_CODE, DATABASE_TRANSACTION_ERR_DESC);
		errorMessageMap.put(REQUEST_PAYLOAD_IS_INVALID_ERR_CODE, REQUEST_PAYLOAD_IS_INVALID_ERR_DESC);
		errorMessageMap.put(USER_REQUEST_ALREADY_BEEN_APPROVED_ERR_CODE, USER_REQUEST_ALREADY_BEEN_APPROVED_ERR_DESC);
		errorMessageMap.put(INVALID_EMAIL_ERR_CODE, INVALID_EMAIL_ERR_DESC);
		errorMessageMap.put(INVALID_REQUESTED_ROLE_ERR_CODE, INVALID_REQUESTED_ROLE_ERR_DESC);

	}

	public static String validationError(String field) {
		field = CaseConverter.toSentence(CaseConverter.toPascalCase(field));
		return String.format("'%s' is required.", field);
	}

	/**
	 * Validate according to annotation
	 *
	 * @param <T>
	 * @param target
	 * @param targetClass
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> Map<String, String> validateRequired(T target, Class<T> targetClass)
			throws IllegalArgumentException, IllegalAccessException {
//		System.out.println("Checking validation required...");
		Map<String, String> validationMap = new LinkedHashMap<>();
		Field[] fields = targetClass.getDeclaredFields();
		for (Field field : fields) {
			XmlElement element = field.getAnnotation(XmlElement.class);
			if (element != null && element.required()) {
				field.setAccessible(true);
				if (field.get(target) == null || field.get(target).toString().isEmpty()) {
					validationMap.put(element.name(), validationError(element.name()));
				}
			}
		}

		return validationMap;

	}

	public static String getErrorMessage(String errorCode) {
		return errorMessageMap.get(errorCode) != null ? errorMessageMap.get(errorCode) : errorCode;
	}

}
