//Command to run jacoco tests:
//mvn clean package jacoco:report

package com.example.stringcalculator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.*;

@Service 
public class StringCalculator {
	
	public int Add(String numbers){
		
		try {
			validateInput(numbers); 
		}
		catch (Exception e){
			//System.out.println(e.getMessage());	
			return -1;
		}	
		
		ArrayList <Integer> nums = getNumArray(numbers);
		int total = 0;
		
		for (int num:nums){
			total += num;	
		}
			
		return total;
	}
	
	public void validateInput(String str) throws Exception {
		
		if (regexMatch(str,"^//\\D\n")){
			if (!validateExtraSeperator(str)){
				throw new Exception("Invalid argument given to Add function, validation failed. Make sure extra seperator is configured properly.");
			}	
		}	
		else {
			
			if (!regexMatch(str,"^(\\d+((,|\n)\\d+)*)?$")){
					throw new Exception("Invalid argument given to Add function, validation failed.");
			}	
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
	
	//Old Implementation that uses string methods (Only works up until question 3)
	/*
	public boolean IsNumber(char num){
		try {
			int i = Integer.parseInt(Character.toString(num));
		}
		catch (NumberFormatException error){
			return false;
		}	
		
		return true;
	}	
	
	public int NextSeperator(String numbers, int startPosition){
		int position = startPosition;
		while (position < numbers.length() && IsNumber(numbers.charAt(position))){
			position++;
		}
		if (position == numbers.length()){
			return -1; 		//reached end of string
		}
		return position;
	}	
	
	public int Add(String numbers){
		if (numbers == "") return 0;
		
		int startPos = 0;
		int seperator = NextSeperator(numbers, startPos);
		int total = 0;
		while (seperator > -1){
			total += Integer.parseInt(numbers.substring(startPos,seperator));
			startPos = seperator+1;
			seperator = NextSeperator(numbers, startPos);
		}	
		total += Integer.parseInt(numbers.substring(startPos));
		return total;
		
	}
	*/
}	
