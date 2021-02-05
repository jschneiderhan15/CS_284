/*Jack Schneiderhan
  CS284-C
  Homework assignment #1, due February 12th @ 11:59 PM
  "I pledge my honor that I have abided by the Stevens Honor System.
*/
package test;

public class BinaryNumber {
	
	private int[] data;
	private int length;
	
	/**default constructor, constructs a binary number 
	* consisting of 0s with inputted length
	* @param length of the binary number
	*/
	public BinaryNumber(int binLength) {
		length = binLength;
		data = new int[length];
		for (int i = 0; i < length; i++)
		{
			data[i] = 0;
		}
	}
	
	/**constructor that takes input of a binary number
	* @param the binary number
	*/
	public BinaryNumber(String str) {
		int strLength = str.length();
		data = new int[strLength];
		for (int i = 0; i < strLength; i++) {
			String tempStr = str.substring(i, i+1);
			if(tempStr.equals("1")) {
				data[i] = 1;
			}
			else if(tempStr.equals("0"))
			{
				data[i] = 0;		
			}
			else {
				System.out.println("ERROR: Number contains a digit other than 0 or 1.\nPlease Enter a number that only contains these digits.\nNumber is defaulting to same length as entered, but only 0s.");
				for(int j = 0; j < strLength; j++) {
					data[j] = 0;
				}
				break;
			}
		}
	}
	
	/**returns the length of the binary number
	 * @return length of the binary number
	 */
	public int getLength()
	{
		return data.length;
	}
	
	/**returns the integer array representation of the binary number
	 * @return array form of the binary number
	 */
	public int[] getInnerArray() {
		return data;
	}
	
	/**returns a digit of the binary number
	 * @param index of the wanted digit
	 * @return the digit being looked for
	 */
	public int getDigit(int index) {
		if(index > data.length - 1) {
			System.out.print("ERROR: That index is out of bounds. Index must be less than ");
			return data.length;
		}
		return data[index];
			
	}
	
	/**returns the decimal form of the binary number
	 * @return the decimal form
	 */
	public int toDecimal(){
		int power = 0;
		int counter = data.length - 1;
		int count = 0;
		for(int i = 0; i < data.length; i++)
		{
			if(data[counter] == 0) {
				counter--;
				power++;
			}
			else {
				count += Math.pow(2, power);
				power++;
				counter--;
			}
		}
		return count;
	}
	
	/**shifts all digits in a certain direction a certain amount of spaces
	 * 1 indicates to the right, -1 indicates to the left
	 * @param the direction of the movement
	 * @param the amount of spaces the digits are moving
	 */
	public void bitShift(int direction, int amount) {
		int[] newArr;
		if(direction == 1) {
			if(amount > this.getLength()) {
				System.out.println("ERROR: Amount is greater than array size. Array remains the same.");
				newArr = data;
			}
			else if(amount < 0){
				System.out.println("ERROR: Amount must be greater than or equal to 0.\nArray remains the same.");
				newArr = data;
			}
			else {
				newArr = new int[this.getLength() - amount];
				for(int i = 0; i < newArr.length; i++) {
					newArr[i] = data[i];
				}
			}
		}
		else if(direction == -1) {
			newArr = new int[this.getLength() + amount];
			for(int i = 0; i < data.length; i++) {
				newArr[i] = data[i];
			}
			for(int h = this.getLength(); h <(amount + length); h++) {
				newArr[h] = 0;
			}
		}
		else
		{
			System.out.println("Invalid Direction. Array remains the same.");
			newArr = data;
		}
		
		data = newArr;
	}
	
	/**function that returns the bitwise or of two numbers
	 * @param first binary number being compared
	 * @param second binary number being compared
	 */
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		int[] bwor = new int[bn1.getLength()];
		int[] def = new int[1];
		def[0] = 0;
		if(bn1.getLength() != bn2.getLength()) {
			System.out.println("ERROR: Numbers must be the same length. Returning [0] as a default:");
			return def;
		}
		else {
			int counter = bn1.getLength() - 1;
			for(int i = 0; i < bn1.getLength(); i++) {
				if(bn1.getInnerArray()[counter] == 1 || bn2.getInnerArray()[counter] == 1) {
					bwor[counter] = 1;
					counter--;
				}
				else {
					bwor[counter] = 0;
					counter--;
				}
			}
			return bwor;
		}
	}
	
	/**function that returns the bitwise and of two numbers
	 * @param first binary number being compared
	 * @param second binary number being compared
	 */
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		int[] def = new int[1];
		def[0] = 0;
		int[] bwand = new int[bn1.getLength()];
		if(bn1.getLength() != bn2.getLength()) {
			System.out.println("ERROR: Numbers must be the same length. Returning [0] as a default:");
			return def;
		}
		else {
			int counter = bn1.getLength() - 1;
			for(int i = 0; i < bn1.getLength(); i++) {
				if(bn1.getInnerArray()[counter] == 1 && bn2.getInnerArray()[counter] == 1) {
					bwand[counter] = 1;
					counter--;
				}
				else {
					bwand[counter] = 0;
					counter--;
				}
			}
			return bwand;
		}
		
	}
	
	/**function that adds a binary number to the binary number being called upon
	 * @param binary number to add to 
	 */
	public void add(BinaryNumber aBinaryNumber) {
		int[] fixArr;
		int[] arrAdd;
			//prepending 0s if needed
			int difference = this.getLength() - aBinaryNumber.getLength();
			if(difference < 0) {
				fixArr = new int[aBinaryNumber.getLength()];
				for(int i = 0; i < Math.abs(difference); i++) {
					fixArr[0] = 0;
				}
				for(int j = 0; j < this.getLength(); j++) {
					fixArr[j + Math.abs(difference)] = this.getInnerArray()[j];
				}
			}
			else if(difference > 0) {
				fixArr = new int[this.getLength()];
				for(int i = 0; i < Math.abs(difference); i++) {
					fixArr[0] = 0;
				}
				for(int j = 0; j < aBinaryNumber.getLength(); j++) {
					fixArr[j + Math.abs(difference)] = aBinaryNumber.getInnerArray()[j];
				}
			}
			else {
				fixArr = this.getInnerArray();
			}
			//adding the binary numbers together
				int carry = 0;
				int[] a;
				int[] b;
				arrAdd = new int[fixArr.length];
				if(difference > 0) {
					a = this.getInnerArray();
					b = fixArr;
				}
				else if(difference < 0) {
					a = aBinaryNumber.getInnerArray();
					b = fixArr;
				}
				else {
					a = this.getInnerArray();
					b = aBinaryNumber.getInnerArray();
				}	
				if(difference == 0) {
					for(int i = this.getLength() - 1; i >= 0; i--) {
						if(a[i] + b[i] + carry == 0 ) {
							carry = 0;
							arrAdd[i] = 0;
						}
						else if(a[i] + b[i] + carry == 1) {
							carry = 0;
							arrAdd[i] = 1;
						}							
						else if(a[i] + b[i] + carry == 2) {
							carry = 1;
							arrAdd[i] = 0;
						}
						else if(a[i] + b[i] + carry == 3) {
							carry = 1;
							arrAdd[i] = 1;
						}					
					}
					//extending the add array, if needed
					 if(carry == 1) {
						int[] newArrAdd = new int[arrAdd.length + 1];
						for(int j =  0; j < arrAdd.length; j++) {
							newArrAdd[j + 1] = arrAdd[j];
						}
						newArrAdd[0] = 1;
						arrAdd = newArrAdd;						
					}
				}
				else if(difference != 0) {
					for(int i = fixArr.length - 1; i >= 0; i--) {
						if(a[i] + b[i] + carry == 0 ) {
							carry = 0;
							arrAdd[i] = 0;
						}
						else if(a[i] + b[i] + carry == 1) {
							carry = 0;
							arrAdd[i] = 1;
						}							
						else if(a[i] + b[i] + carry == 2) {
							carry = 1;
							arrAdd[i] = 0;
						}
						else if(a[i] + b[i] + carry == 3) {
							carry = 1;
							arrAdd[i] = 1;
						}					
					}
					//extending the add array, if needed
					if(carry == 1) {
						int[] newArrAdd = new int[arrAdd.length + 1];
						for(int j =  0; j < arrAdd.length; j++) {
							newArrAdd[j + 1] = arrAdd[j];
						}
						newArrAdd[0] = 1;
						arrAdd = newArrAdd;						
					}
				}
				data = arrAdd;
		}
		
	
	
			
	/**toString method that returns the binary number as a string 
	 * @return the binary number formatted as a string
	 */
	public String toString() {
		String result = "";
		for(int i = 0; i < data.length; i++) {
			result += data[i];
		}
		return result;
	}
	
	
}
