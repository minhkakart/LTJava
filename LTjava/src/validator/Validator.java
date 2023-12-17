package validator;

import java.util.Map;
import java.util.Set;

public class Validator {

	public static final int REQUIRED = 0;
	public static final int NUMERIC = 1;
	public static final int INTEGER = 2;
	public static final int DOUBLE = 3;
	public static final int BOOLEAN = 4;
	public static final int STRING = 5;
	public static final int CHAR = 6;
	public static final int NOT_NULL = 7;
	public static final int EMAIL = 8;
	public static final int MIN = 9;
	public static final int MAX = 10;

	private Map<String, String> rules;
	private Map<String, String> data;
	private Map<String, Integer> mappedRules;

	private boolean isValid;

	private String message;

	public Validator() {
		this.rules = new java.util.HashMap<String, String>();
		this.data = new java.util.HashMap<String, String>();
		this.isValid = true;
		this.message = "Valid";

		this.mappedRules = new java.util.HashMap<String, Integer>();
		this.mappedRules.put("required", REQUIRED);
		this.mappedRules.put("numeric", NUMERIC);
		this.mappedRules.put("integer", INTEGER);
		this.mappedRules.put("double", DOUBLE);
		this.mappedRules.put("boolean", BOOLEAN);
		this.mappedRules.put("string", STRING);
		this.mappedRules.put("char", CHAR);
		this.mappedRules.put("not_null", NOT_NULL);
		this.mappedRules.put("email", EMAIL);
		this.mappedRules.put("min", MIN);
		this.mappedRules.put("max", MAX);
	}

	/**
	 * @param data Map < "field", "value" >
	 * @return boolean
	 */
	public boolean validate(Map<String, String> data) {
		this.data = data;
		this.isValid = true;

		Set<String> keys = this.data.keySet();
		boolean valid = false;
		for (String key : keys) {
			String[] ruleList = this.rules.get(key).replaceAll(" ", "").split("\\|");
			for (String rule : ruleList) {
				
				if (!validateRule(this.mappedRules.get(rule), this.data.get(key))) {
					message = key + ": " + message;
					valid = true;
					break;
				}
			}
			if (valid) {
				break;
			}
		}
		return isValid;
	}

	/**
	 * @param rules Map < "field", "rule1 | rule2" >
	 * @param data  Map < "field", "value" >
	 * @return boolean
	 */
	public boolean validate(Map<String, String> rules, Map<String, String> data) {
		this.rules = rules;
		this.data = data;
		this.isValid = true;

		Set<String> keys = this.data.keySet();
		boolean valid = true;
		for (String key : keys) {
			String[] ruleList = this.rules.get(key).replaceAll(" ", "").split("\\|");
			for (String rule : ruleList) {
				if (rule.contains(":")) {
					String[] params = rule.split(":");
					if (!validateRule(this.mappedRules.get(params[0]), this.data.get(key), params[1])) {
						message = key + ": " + message;
						valid = false;
						break;
					}
				} else {
					if (!validateRule(this.mappedRules.get(rule), this.data.get(key))) {
						message = key + ": " + message;
						valid = false;
						break;
					}
				}
			}
			if (!valid) {
				break;
			}
		}
		return isValid;
	}

	private boolean validateRule(Integer rule, String data, String... params) {

		switch (rule) {
		case REQUIRED:
			if (isBlank(data)) {
				isValid = false;
				message = "field is required";
				return false;
			}
			break;
		case EMAIL:
			if (!isEmail(data)) {
				isValid = false;
				message = "field is not an email";
				return false;
			}
			break;
		case NUMERIC:
			if (!isNumeric(data)) {
				isValid = false;
				message = "field is not a number";
				return false;
			}
			break;
		case INTEGER:
			if (!isInteger(data)) {
				isValid = false;
				message = "field is not an integer";
				return false;
			}
			break;
		case DOUBLE:
			if (!isDouble(data)) {
				isValid = false;
				message = "field is not a double";
				return false;
			}
			break;
		case BOOLEAN:
			if (!isBoolean(data)) {
				isValid = false;
				message = "field is not a boolean";
				return false;
			}
			break;
		case STRING:
			if (!isString(data)) {
				isValid = false;
				message = "field is not a string";
				return false;
			}
			break;
		case CHAR:
			if (!isChar(data)) {
				isValid = false;
				message = "field is not a char";
				return false;
			}
			break;
		case NOT_NULL:
			if (isNull(data)) {
				isValid = false;
				message = "field is not null";
				return false;
			}
			break;
		case MIN:
			if (params.length == 0) {
				message = "Invalid rule params length 0";
				isValid = false;
				return false;
			}
			if (isNumeric(data)) {
				if (Double.parseDouble(data) < Double.parseDouble(params[0])) {
					isValid = false;
					message = "field value is less than " + params[0];
					return false;
				}
			} else {
				if (data.length() < Integer.parseInt(params[0])) {
					isValid = false;
					message = "field length is less than " + params[0];
					return false;
				}
			}
			break;
			case MAX:
				if (params.length == 0) {
					message = "Invalid rule";
					isValid = false;
					return false;
				}
				if (isNumeric(data)) {
					if (Double.parseDouble(data) > Double.parseDouble(params[0])) {
						isValid = false;
						message = "field value is greater than " + params[0];
						return false;
					}
				} else {
					if (data.length() > Integer.parseInt(params[0])) {
						isValid = false;
						message = "field length is greater than " + params[0];
						return false;
					}
				}
				break;
		default:
			message = "Invalid rule";
			isValid = false;
			return false;
		}
		message = "Valid";
		isValid = true;
		return true;
	}

	// TODO: Add your validation methods here!
	public static boolean isNumeric(String str) {
		if (isBlank(str)) {
			return false;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isInteger(String str) {
		if (isBlank(str)) {
			return false;
		}
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isDouble(String str) {
		if (isBlank(str)) {
			return false;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isBoolean(String str) {
		if (isBlank(str)) {
			return false;
		}
		return str.matches("true|false");
	}

	public static boolean isString(String str) {
		if (isBlank(str)) {
			return false;
		}
		return str.length() > 1;
	}

	public static boolean isChar(String str) {
		if (isBlank(str)) {
			return false;
		}
		return str.length() == 1;
	}

	public static boolean isNull(String str) {
		if (isBlank(str)) {
			return false;
		}
		return str.equals("null");
	}

	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		}
		return str.isEmpty();
	}

	public static boolean isEmail(String str) {
		if (isBlank(str)) {
			return false;
		}
		return str.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
	}

	// Getters and Setters
	public void setRules(Map<String, String> rules) {
		this.rules = rules;
	}

	public Map<String, String> getRules() {
		return this.rules;
	}

	public void addRule(String key, String rules) {
		this.rules.put(key, rules);
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

}
