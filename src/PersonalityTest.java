/**
Namho An
COSI12b
PA01
1/28/2015
**/
import java.io.*;
import java.util.*;
public class PersonalityTest {
	public static final int dimen = 4; 
	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = input();
		PrintStream out = new PrintStream(new File("output.txt"));
		
		int count = getCount(inputFile);
		
		String[] names = new String[count];
		String[] answers = new String[count];
		collect(inputFile, count, names, answers);
		
		for (int i = 0; i < count; i++) {
			String[] dimensions = new String[dimen];
			int[] numOfA = new int[dimen];
			int[] numOfB = new int[dimen];
			int[] percentOfB = new int[dimen];
			String[] format = new String[dimen];	
			String[] type = new String[dimen];
			
			sortingAnswers(answers[i], dimensions);
			analyzeAnswers(dimensions, numOfA, numOfB);
			numOfAlphabet(numOfA, numOfB, format);
			toPercentageB(numOfA, numOfB, percentOfB);
			toLetter(percentOfB, type);
			printResults(i, names, percentOfB, type, format, out);			
		}
	}
	//prompt that make user to input the name of the txt file
	public static File input() {
		Scanner console = new Scanner(System.in);
		System.out.println("Input file name: ");
		String inputFileName = console.nextLine();
		File inputFile = new File(inputFileName);
		while(!inputFile.exists()){
			System.out.println("File not found. Try again: ");
			inputFileName = console.nextLine();
			inputFile = new File(inputFileName);
		}
		return inputFile;
	}
	//figuring out answers from number of people
	public static int getCount(File inputFile) throws FileNotFoundException{
		Scanner input = new Scanner(inputFile);
		int count = 0;
		while(input.hasNextLine()){
			count++;
			input.nextLine();
		}
		return count/2;
	}
	//collect the names and answers 
	public static void collect(File inputFile, int count, String[] names, String[] answers) throws FileNotFoundException {
		Scanner console = new Scanner(inputFile);
		for(int i = 0; i < count; i++){
			names[i] = console.nextLine();
			answers[i] = console.nextLine();
		}
	}
	//sorting the answers into 4 different types by order of 1 2 2 2
	public static void sortingAnswers(String answers, String[] dimensions) {
		for(int i = 0; i < 10; i++){
			dimensions[0] += answers.charAt(7*i);
			for(int j = 1; j < dimensions.length; j++){
				dimensions[j] += answers.charAt(7*i+2*j-1);
				dimensions[j] += answers.charAt(7*i+2*j);
			}
		}
	}
	//counting number of A or a, and B or b
	public static void analyzeAnswers(String[] dimensions, int[] numberOfA, int[] numberOfB) {
		for(int i = 0; i < dimensions.length; i++){
			String answers = dimensions[i];
			for(int j = 0; j < answers.length(); j++){
				char answer = answers.charAt(j);
				if(answer == 65 || answer == 97){
					numberOfA[i]++;
				} if(answer == 66 || answer == 98){
					numberOfB[i]++;
				}
			}
		}
	}
	//formating the structure of XA-XB
	public static void numOfAlphabet(int[] numOfA, int[] numOfB, String[] format){
		for(int i = 0; i < format.length; i++) {
			format[i] = numOfA[i] + "A-" + numOfB[i] + "B";
		}
	}
	//calculating the percent of B
	public static void toPercentageB(int[] numOfA, int[] numOfB, int[] percentOfB) {
		for(int i = 0; i < percentOfB.length; i++){
			percentOfB[i]  = 100 * numOfB[i] / (numOfA[i]+numOfB[i]);
		}
	}
	//changing the percent of B to the Letter type
	public static void toLetter(int[] percentOfB, String[] type) {
		String[] firstRow = {"I", "N", "F", "P"};
		String[] secondRow = {"E", "S", "T", "J"};
		for(int i = 0; i < percentOfB.length; i++){
			if(percentOfB[i] > 50){
				type[i] = firstRow[i];
			} else if(percentOfB[i] < 50){
				type[i] = secondRow[i];
			} else type[i] = "X";
		}
	}
	//forming the result output as a text file
	public static void printResults(int i, String[] names, int[] percentOfB, String[] type, String[] format, PrintStream out){
		out.println(names[i]);
		for(int j = 0; j < format.length; j++){
		out.print(format[j] + " ");
		}
		out.println();
		out.print("[");
		for(int k = 0; k < percentOfB.length-1; k++) {
			out.print(percentOfB[k] + "%, ");
		}
		out.print(percentOfB[3] + "%] = ");
		for(int l = 0; l < type.length; l++){
			out.print(type[l]);
		}
		out.println();
		out.println();
	}
}
