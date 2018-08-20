package com.revature.eval.java.core;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.lang.IllegalArgumentException;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	
	public String reverse(String string) {
		char[] stringArray = string.toCharArray();
		int arrLength = stringArray.length;
		int arrMiddle = arrLength/2;
		char first;
		char last;
		String reversed;
		for(int a=0; a<arrMiddle; a++) {
			first = stringArray[a];
			last = stringArray[arrLength-(a+1)];
			stringArray[a]=last;
			stringArray[arrLength-(a+1)] = first;
		}
		reversed = new String(stringArray);
		return reversed;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String acronym = "";
		String delims = "[ ,-]+";
		phrase = phrase.toUpperCase();
		String[] splits = phrase.split(delims);
		for(String part: splits) {
			acronym+= part.charAt(0);
		}
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			boolean equal = sideOne==sideTwo&&sideOne==sideThree?true:false;
			return equal;
		}

		public boolean isIsosceles() {
			boolean isosceles = sideOne==sideTwo||sideOne==sideThree||sideTwo==sideThree?true:false;
			return isosceles;
		}

		public boolean isScalene() {
			boolean scalene = sideOne!=sideTwo&&sideOne!=sideThree&&sideTwo!=sideThree?true:false;
			return scalene;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		char[] letters = {'A','E','I','O','U','L','N','R','S','T','D','G','B','C','M','P','F','H','V','W','Y','K','J','X','Q','Z'};
		int[]  scores  = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 8, 10,10};
		int total = 0;
		string = string.toUpperCase();
		char[] charArr = string.toCharArray();
		for(char letter: charArr) {
			for(int i =0; i<26; i++) {
				if(letter==letters[i]) {
					total+=scores[i];
					continue;
				}
			}
		}
		return total;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException{
		String number = "";
		String delims = "[- ().]+";
//		char[] illegal = {'a','b','c','@','!',':'};
		String[] splits = string.split(delims);
		number = String.join("", splits);
		if(number.length()>11) {
				throw new IllegalArgumentException();
			}
		for(char a: number.toCharArray()) {
			if(a=='a'||a=='b'||a=='c'||a=='@'||a=='!'||a==':') {
					throw new IllegalArgumentException();
			}
		}
		return number;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		String[] sArr = string.split("[ ,\n]+");
		HashMap<String, Integer> sScore = new HashMap<>();
		int value;
		for(String s : sArr) {
			if(sScore.containsKey(s)) {
				value = sScore.get(s);
				value++;
				sScore.put(s, value);
				}else if(!sScore.containsKey(s)) {
					sScore.put(s, 1);
				}
		}
		return sScore;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;
		private Integer mid;
		private Integer beg;
		private Integer end;

		public int indexOf(String t) {
			if(sortedList.get(mid).toString().compareTo(t)==0) {
					return mid;
			}else if(sortedList.get(mid).toString().compareTo(t)>0){
				end = mid-1;
				mid = beg + (end-1)/2;
				return indexOf(t);
			}else if(sortedList.get(mid).toString().compareTo(t)<0){
				beg = mid+1;
				mid = beg + (end-1)/2;
				return indexOf(t);
			}
			return -1;
		}
		public int indexOf(int t) {
		if((Integer)sortedList.get(mid)==t) {
			return mid;
		}else if((Integer)sortedList.get(mid)>t){
			end = mid-1;
			mid = beg + (end-beg)/2;
			return indexOf(t);
		}else if((Integer)sortedList.get(mid)<t){
			beg = mid+1;
			mid = beg + (end-beg)/2;
			return indexOf(t);
		}
		return -1;
		}
		//constructor
		public BinarySearch(List<T> sortedList) {
			super();
			mid = (sortedList.size()-1)/2;
			beg = 0;
			end = sortedList.size()-1;
			
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String[] sArr = string.split("[ ]+");
		char first;
		String subAy="";
		String newWord;
		for(String s: sArr) {
			if (s.startsWith("th")) {
				newWord = s.substring(2).concat("thay");
				if(sArr.length>1&& Arrays.asList(sArr).indexOf(s)<sArr.length-1) {
					newWord = newWord.concat(" ");
				}
				subAy = subAy.concat(newWord);
			}else if(s.startsWith("sch")){
				newWord = s.substring(3).concat("schay");
				if(sArr.length>1&& Arrays.asList(sArr).indexOf(s)<sArr.length-1) {
					newWord = newWord.concat(" ");
				}
				subAy = subAy.concat(newWord);
			}else if(s.startsWith("qu")){
				newWord = s.substring(2).concat("quay");
				if(sArr.length>1&& Arrays.asList(sArr).indexOf(s)<sArr.length-1) {
					newWord = newWord.concat(" ");
				}
				subAy = subAy.concat(newWord);
			}else {
				first = s.charAt(0);
				if(first!='a') {
					newWord=s.substring(1)+first;
					newWord=newWord.concat("ay");
					if(sArr.length>1&& Arrays.asList(sArr).indexOf(s)<sArr.length-1) {
						newWord = newWord.concat(" ");
					}
					subAy=subAy.concat(newWord);
				}else {
					subAy=s.concat("ay");
				}
			}
		}
		return subAy;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String number = String.valueOf(input);
		String[] digits = number.split("");
		int[] armArr=new int[digits.length];
		for(int i =0;i<digits.length;i++) {
			armArr[i] = Integer.parseInt(digits[i]);
			armArr[i] = (int) Math.pow(armArr[i], digits.length);
		}
		int sum = IntStream.of(armArr).sum();
		if(sum==input)
			return true;
		//separate the input into an array of integers.
		//take the power (where the power is the number of integers) of each
		//integer in the array of integers and store into the array.
		//take the total of the array.
		//check if the input == total.
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> factors = new ArrayList<Long>();
		int indexCount =0;
		while(l%2==0) {
			factors.add(indexCount,2L);
			indexCount++;
			l/=2;
			System.out.println(l);
		}
		
	for(long big=3L;big <= Math.sqrt(l); big+=2) {
		
		while(l%big==0) {
			factors.add(indexCount,big);
			indexCount++;
			l/=big;
			System.out.println(l);
		}
	}
	if(l>2)
		factors.add(indexCount,l);
		System.out.println(factors.toString());
		return factors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			String strIndexLower = "abcdefghijklmnopqrstuvwxyz";
			String strIndexUpper = strIndexLower.toUpperCase();
			int encode=0;
			String encStr = "";
			//Take string input
			for(int o=0; o<string.length();o++)
			{
				if(strIndexLower.contains(Character.toString(string.charAt(o)))) {
				encode = (strIndexLower.indexOf(string.charAt(o))+key)%26;
				encStr+=(Character.toString(strIndexLower.charAt(encode)));
				}else if(strIndexUpper.contains(Character.toString(string.charAt(o)))) {
					encode = (strIndexUpper.indexOf(string.charAt(o))+key)%26;
					encStr+=(Character.toString(strIndexUpper.charAt(encode)));
				}else {
					encStr+=(Character.toString(string.charAt(o)));
				}
			}
			//for each char in charArray, convert it into its index on the
			//cipher.
			//Then add the key value.
			//Then mod that by 26.
			//then convert it back to letters
			//return the resulting charArray as a string
			return encStr;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		return 0;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String index = "abcdefghijklmnopqrstuvwxyz";
			String cipher = "zyxwvutsrqponmlkjihgfedcba";
			string = string.toLowerCase();
			String[] strArr = string.split("[ ,.]+");
			String noSpace="";
			int encode=0;
			String encStr="";
			for(String s:strArr) {
				noSpace+=s;
			}
			noSpace.toLowerCase();
			for(int i = 0;i<noSpace.length();i++) {
				encode = index.indexOf(noSpace.charAt(i));
				try {
				encStr+=(Character.toString(cipher.charAt(encode)));
				}catch (StringIndexOutOfBoundsException e) {
					encStr+=noSpace.charAt(i);
				}
			}
			System.out.println(encStr);
			//get the index of the letter and replace it with the letter with
			//the same index in the cipher
			//add a space every 5th character
			String temp ="";
//			if(encStr.length()>5) {
				for(int i =5;i<encStr.length()+5;i+=5) {
					try {
					temp+=encStr.substring(i-5, i)+" ";
					}catch(StringIndexOutOfBoundsException e) {
						temp+=encStr.substring(i-5);
					}
				}
				if (temp.charAt(temp.length() - 1) == ' ') {
			        temp = temp.substring(0, temp.length() - 1);
			    }
//			}
			System.out.println(temp);
			return temp;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String index = "abcdefghijklmnopqrstuvwxyz";
			String cipher = "zyxwvutsrqponmlkjihgfedcba";
			string = string.toLowerCase();
			String[] strArr = string.split("[ ,.]+");
			String noSpace="";
			int encode=0;
			String encStr="";
			for(String s:strArr) {
				noSpace+=s;
			}
			noSpace.toLowerCase();
			for(int i = 0;i<noSpace.length();i++) {
				try {
				encode = index.indexOf(noSpace.charAt(i));
				encStr+=(Character.toString(cipher.charAt(encode)));
				}catch (StringIndexOutOfBoundsException e) {
					encStr+=Character.toString(noSpace.charAt(i));
				}
			}
			return encStr;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		String[] strArr = string.split("-");
		String isbn="";
		String[] digits;
		boolean hasX = false;
		for(String s:strArr)
			isbn+=s;
		if(isbn.substring(isbn.length()-1,isbn.length()).equals("X"))
		{
			hasX=true;
			isbn = isbn.substring(0, isbn.length() - 1);
		}
		digits = isbn.split("");
		int[] isbnArr = new int[digits.length];
		try {
			for(int i=0;i<isbnArr.length;i++)
			{
				isbnArr[i]=Integer.parseInt(digits[i]);
			}
		}catch(NumberFormatException e) {
			return false;
		}
		for(int i = 0;i<isbnArr.length;i++)
		{
			isbnArr[i] = isbnArr[i]*(10-i);
		}
		int sum = IntStream.of(isbnArr).sum();
		if(hasX)
		{
			sum+=10;
		}
		if(sum%11==0)
			return true;
		//multiply each int in the array by whatever it needs to be for the thing
		//mod11 it.
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		String[] strArr = string.split(" ");
		String temp = "";
		for(String s : strArr)
			temp+=s;
		strArr = temp.split("");
		String[] aArr = "abcdefghijklmnopqrstuvwxyz".split("");
		HashMap<String, Integer> sScore = new HashMap<>();
		int value=0;
		for(String s : aArr) {
			if(!sScore.containsKey(s)) {
				sScore.put(s, value);
				}
		}
		for(String s : strArr) {
			if(sScore.containsKey(s)) {
				sScore.put(s, 1);
			}
		}
		for(String s : aArr)
		{
			if(sScore.get(s)==0)
				return false;
		}
		//just do that thing we did for counting words but initialize
		//the data structure with all 26 letters as keys that have value 0.
		//ezpz
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		Temporal giga;
//		Duration time = Duration.
//		giga = given.plus(time);
		//just add 1,000,000,000 seconds to a date given.
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		Set numbers;
		//make a list of unique numbers
		//construct a for loop that checks each number to see if it is a multiple
		//of any of the numbers in the set.
		//on a new/unique number, add it to the list. on a non-unique number, ignore.
		//get the sum of the numbers in the set.
		return 0;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		String[] strArr = string.split("[ ]+");
		String input = "";
		for(String s:strArr)
			input+=s;
		String[] intArr = input.split("");
		int[] digits = new int[intArr.length];
		try {
		for(int i = 0; i<intArr.length;i++)
			digits[i]=Integer.parseInt(intArr[i]);
		}catch(NumberFormatException e) {
			return false;
		}
		for(int i=digits.length-2;i>=0;i-=2)
		{
			digits[i]*=2;
			if(digits[i]>9)
				digits[i]-=9;
		}
		for(int i: digits)
		System.out.println(i);
		int sum = IntStream.of(digits).sum();
		if(sum%10==0)
			return true;
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		String[] strArr= string.split("[ ?]+");
		String operand=null;
		int num1=0;
		int num2=0;
		int result=0;
		for(String s:strArr) {
			if(s.equals("What")||s.equals("is"))
				continue;
			try {
				if(num1==0)
					num1 = Integer.parseInt(s);
				else if(num2==0)
					num2 = Integer.parseInt(s);
			} catch(NumberFormatException e) {
				if(operand==null)
					operand=s;
			}
		}
		switch(operand)
		{
		case "plus":
			result = num1+num2;
			break;
		case "minus":
			result = num1-num2;
			break;
		case "multiplied":
			result = num1*num2;
			break;
		case "divided":
			result = num1/num2;
			break;
		}
		//get number
		//get operand
		//get number
		//do thing based on switch case
		return result;
	}

}
