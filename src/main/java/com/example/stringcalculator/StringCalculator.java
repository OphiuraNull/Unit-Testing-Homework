//Command to run jacoco tests:
//mvn clean package jacoco:report

package com.example.stringcalculator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.*;

@Service 
public class StringCalculator {
	
	//Remember: The Regex looks awful because all escape \ have to be doubled so java doesnt cry
	
	//Constants used to store exception messages
	static final String seperatorError = "Invalid argument given to Add function, validation failed. Make sure extra seperator is configured properly.";
	static final String genericError = "Invalid argument given to Add function, validation failed.";
	static final String negativeError = "Negatives not allowed.";
	
	private static int timesCalled = 0;			//keep track of how many times Add() has been called
	
	public int Add(String numbers){		//Main function for adding numbers
		timesCalled++;
		
		validateInput(numbers); 
		
		ArrayList <Integer> nums = getNumArray(numbers);
		int total = 0;
		
		for (int num:nums){
			if (num <= 1000){
				total += num;	
			}	
		}
	
		return total;
	}
	
	public static int getCalledCount(){		//Self explanatory: returns times Add() has been called
		return timesCalled;
	}	
	
	public void validateInput(String str){		//Helper func that handles input validation and exception throwing
		validateNoNegatives(str);
		
		if (regexMatch(str,"^//(\\D|(\\[[^\\d\\[\\]]+\\])+)\n")){	//checks if configurable seperator is used at start of string
			if (!validateExtraSeperator(str)){
				throw new RuntimeException(seperatorError);
			}	
		}	
		else {
			
			if (!regexMatch(str,"^(\\d+((,|\n)\\d+)*)?$")){		//validate string if no extra seperator is put at start
					throw new RuntimeException(genericError);
			}	
		}
	}	
	
	public void validateNoNegatives(String str){			//Make sure no negative numbers are present in input and throws exception with message if not
		ArrayList<String> negatives = getAllRegexMatches(str, "-\\d+");
		if (negatives.size() > 0){
			String out = "";
			for (String item:negatives){
				out +=  " " + item;
			}	
			throw new RuntimeException(negativeError+out);
		}	
	}	
	
	public boolean validateExtraSeperator(String str){		//validates correct use of extra seperators marked at the start of the input string
		
		if (regexMatch(str, "^//(\\D)\n")){ //single char seperator case
			String match = escapeSpecialChars(getFirstRegexMatch(str,"^//(\\D)\n",1));
			return regexMatch(str, "^//"+match+"\n(\\d+((,|\n|"+match+")\\d+)*)?$");
		}
		else {	//seperator in brackets case     
			ArrayList<String> matches = getAllRegexMatches(str,"\\[([^\\d\\[\\]]+)\\]",1);
			String match = "";
			for (String sep:matches){
				match +=  "|" + escapeSpecialChars(sep);
			}
			//System.out.println("Regex match: "+match);   //output for debugging
			return regexMatch(str, "^//(\\[[^\\d\\[\\]]+\\])+\n(\\d+((,|\n"+match+")\\d+)*)?$");
		}	
	}

	public String escapeSpecialChars(String str){	//used to \\escape regex's reserved characters
		return str.replaceAll("[\\\\\\^\\$\\.\\|\\?\\*\\+]","\\\\$0");
	}	
	
	public ArrayList<Integer> getNumArray(String numbers){	//Returns array of all numbers from input string
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		ArrayList<String> numberStrings = getAllRegexMatches(numbers, "\\d+");
		
		for (String num:numberStrings){
			output.add(Integer.parseInt(num));
		}
		
		return output;
	}
	
	public boolean regexMatch(String str, String regexPattern){		//checks if pattern matches string
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		
		return matcher.find();
	}	
	
	public String getFirstRegexMatch(String str, String regexPattern, int groupNo){		//returns first regex match in string according to pattern. Accepts argument for a capture group number to return
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		
		if (matcher.find()){
			return matcher.group(groupNo);
		}
		else{
			return "";
		}		
	}	
	
	public String getFirstRegexMatch(String str, String regexPattern){		//overloaded method using default group value of 0
		return getFirstRegexMatch(str, regexPattern, 0);
	}	
	
	public ArrayList<String> getAllRegexMatches(String str, String regexPattern, int groupNo){		//returns array of ALL regex matches for a pattern and string. Accepts argument for capturing group
		
		ArrayList<String> output = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		
		while (matcher.find()){
			output.add(matcher.group(groupNo));
		}
		
		return output;
	}	
	
	public ArrayList<String> getAllRegexMatches(String str, String regexPattern){ //overloaded func with default groupNo 0
		return getAllRegexMatches(str, regexPattern, 0);
	}	
	
}	