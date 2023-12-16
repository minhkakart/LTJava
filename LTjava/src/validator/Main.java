package validator;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Validator validator = new Validator();
		
		Map<String, String> rules = new java.util.HashMap<String, String>();
		rules.put("name", "required");
	    rules.put("age", "integer");
	    rules.put("email", "required|email");
		
		java.util.Map<String, String> data = new java.util.HashMap<String, String>();
		data.put("name", "John Doe");
		data.put("age", "10");
		data.put("email", "asdas");
		
		boolean isValid = validator.validate(rules, data);
		System.out.println(isValid);
		System.out.println(validator.getMessage());
	}

}
