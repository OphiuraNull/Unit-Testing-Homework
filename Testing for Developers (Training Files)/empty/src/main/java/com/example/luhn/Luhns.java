package com.example.luhn;

import org.springframework.stereotype.Service;

@Service
public class Luhns {
	
	// Valid cardNumber: 79927398713

	boolean checkLuhn(String cardNo) {
		int nDigits = cardNo.length();
		int nSum = 0;
		boolean isSecond = false;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = cardNo.charAt(i) - '0';

			if (isSecond == true) {
				d = d * 2;
			}

			// We add two digits to handle
			// cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);
	}
	
	int calculateDigit(String cardNo) {
		int nDigits = cardNo.length();
		int nSum = 0;
		boolean isSecond = true;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = cardNo.charAt(i) - '0';

			if (isSecond == true) {
				d = d * 2;
			}

			// We add two digits to handle
			// cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return 10 - nSum % 10;
	}
}

