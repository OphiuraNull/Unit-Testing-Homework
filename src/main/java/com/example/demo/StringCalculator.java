//Command to run jacoco tests:
//mvn clean package jacoco:report

package com.example.stringcalculator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.*;

@Service 
public class StringCalculator {
	
	static final String seperatorError = "Invalid argument given to Add function, validation failed. Make sure extra seperator is configured properly.";
	static final String genericError = "Invalid argument given to Add function, validation failed.";
	static final String negativeError = "Negatives not allowed.";
	
	private static int timesCalled = 0;
	
	public int Add(String numbers){
		timesCalled++;
		
		validateInput(numbers); 
		
		ArrayList <Integer> nums = getNumArray(numbers);
		int total = 0;
		
		for (int num:nums){
			total += num;	
		}
	
		return total;
	}
	
	public static int getCalledCount(){
		return timesCalled;
	}	
	
	public void validateInput(String str){
		validateNoNegatives(str);
		
		if (regexMatch(str,"^//\\D\n")){
			if (!validateExtraSeperator(str)){
				throw new RuntimeException(seperatorError);
			}	
		}	
		else {
			
			if (!regexMatch(str,"^(\\d+((,|\n)\\d+)*)?$")){
					throw new RuntimeException(genericError);
			}	
		}
		
	}	
	
	public void validateNoNegatives(String str){
		ArrayList<String> negatives = getAllRegexMatches(str, "-\\d+");
		if (negatives.size() > 0){
			String out = "";
			for (String item:negatives){
				out +=  " " + item;
			}	
			throw new RuntimeException(negativeError+out);
		}	
	}	
	
	public boolean validateExtraSeperator(String str){
		String match = getFirstRegexMatch(str,"^//(\\D)\n",1);
		
		return regexMatch(str, "^//"+match+"\n(\\d+((,|\n|"+match+")\\d+)*)?$");
		
	}
	
	public ArrayList<Integer> getNumArray(String numbers){
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		ArrayList<String> numberStrings = getAllRegexMatches(numbers, "\\d+");
		
		for (String num:numberStrings){
			output.add(Integer.parseInt(num));
		}
		
		return output;
	}
	
	public boolean regexMatch(String str, String regexPattern){
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		
		return matcher.find();
	}	
	
	public String getFirstRegexMatch(String str, String regexPattern, int groupNo){
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		
		if (matcher.find()){
			return matcher.group(groupNo);
		}
		else{
			return "";
		}		
	}	
	
	public ArrayList<String> getAllRegexMatches(String str, String regexPattern){
		
		ArrayList<String> output = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		
		while (matcher.find()){
			output.add(matcher.group());
		}
		
		return output;
		
	}	
	
}	