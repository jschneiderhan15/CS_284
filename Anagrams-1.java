//Jack Schneiderhan
//I pledge my honor that I have abided by the Stevens Honor System.

package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;

public class Anagrams {
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character, Integer> letterTable = new HashMap<Character,Integer>();
	Map<Long, ArrayList<String>> anagramTable = new HashMap<Long, ArrayList<String>>();
	
	/*
	 * default constructor for Anagrams class
	 */
	public Anagrams() {
		buildLetterTable();
	}
	
	/*
	 * method that builds the hash table letterTable
	 */
	private void buildLetterTable() {
		Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for(int i = 0; i < primes.length; i++) {
			letterTable.put(letters[i], primes[i]);
		}
	}
	
	/*
	 * computes the hash code of the string s
	 * adds the hashcode to the hash table AnagramTable
	 * @param String s string to be added to the table
	 */
	private void addWord(String s) {
		Long hashCode = myHashCode(s);
		//if the hashcode already exists
		if(this.anagramTable.get(hashCode) != null){
			this.anagramTable.get(hashCode).add(s);
		}
		//if the hashcode is new
		else {
			ArrayList<String> hashList = new ArrayList<String>();
			hashList.add(s);
			this.anagramTable.put(hashCode, hashList);
		}
	}
	
	/*
	 * computes the hashcode of the given string s.
	 * @param String s string to convert hashcode into
	 */
	private Long myHashCode(String s) {
		Long hashcode = (long) 1;
		char[] stringArray = s.toCharArray();
		for(int i = 0; i < stringArray.length; i++) {
			hashcode *= letterTable.get(stringArray[i]);
		}
		return hashcode;
	}
	
	/*
	 * receives the name of a file containing words,
	 * and builds the hash table anagramTable
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}
	
	/*
	 * returns the entries in AnagramTable that have the
	 * largest number of anagrams. 
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		Long max = (long) 0;
		ArrayList<Map.Entry<Long, ArrayList<String>>> entries = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		for(Map.Entry<Long, ArrayList<String>> index : anagramTable.entrySet()) {
			//if the given anagram has a larger size than the max
			if(index.getValue().size() > max) {
				max = (long) index.getValue().size();
				entries.clear();
				entries.add(index);
			}
			//if the given anagram has an equal size to the max
			else if(index.getValue().size() == max) {
				entries.add(index);
			}
		}
		return entries;
	}
	
	//I slightly altered the main class to make it look more like the intended output given in the homework file. 
	//Sorry if this messes something up!
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Elapsed Time: " + seconds);
		System.out.println("Key of max anagrams: " + maxEntries.get(0).getKey());
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams: " + maxEntries.get(0).getValue().size());
	}
}
