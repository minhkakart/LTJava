package validator;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Validator validator = new Validator();
		
		Map<String, String> rules = new java.util.HashMap<String, String>();
		rules.put("name", "required|min:1");
	    rules.put("age", "numeric|min:6|max:20");
	    rules.put("email", "required|email|min:10");
		
		java.util.Map<String, String> data = new java.util.HashMap<String, String>();
		data.put("name", "John Doe");
		data.put("age", "19");
		data.put("email", "nguyenminh2732@gmail.com");
		
		boolean isValid = validator.validate(rules, data);
		System.out.println(isValid);
		System.out.println(validator.getMessage());
	}

}
