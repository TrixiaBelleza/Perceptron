import java.util.*;
import java.io.*;
import java.text.*;
public class Main {
	public static void main (String [] args) throws FileNotFoundException {

		//Get filename for input data
		System.out.print("Enter filename for input data: ");
		Scanner sc = new Scanner(System.in);
		String inputFilename = sc.next();

		File inputFile = new File(inputFilename);

		Scanner scannedInput = new Scanner(inputFile);
		
		//iterate scannedInput
		String line;
		String[] tokens;
		double x0 = 0, x1 = 0, w0 = 0, w1 = 0, wb = 0, a = 0, y = 0;

		double r = Double.parseDouble(scannedInput.nextLine()) ;	//learning rate	
		double t = Double.parseDouble(scannedInput.nextLine());		//threshold
		double b = Double.parseDouble(scannedInput.nextLine()); 	//bias

		double z;
		Vector inVector;
		ArrayList<Vector> inputV = new ArrayList<Vector>();
	
		while(scannedInput.hasNext()) {
			line = scannedInput.nextLine();
			tokens = line.split(" ");

			z = Double.parseDouble(tokens[tokens.length - 1]);			//last index of the tokens[] is the z

			//for each token
			for(int i = 0; i < tokens.length-1; i++) {
				if(i == 0) {
					x0 = Double.parseDouble(tokens[i]);
				}
				else if(i == 1) {
					x1 = Double.parseDouble(tokens[i]);
				}
			}

			inVector = new Vector(x0, x1, w0, w1, wb, a, y, z);
			inputV.add(inVector);
		}
		
		
		System.out.println();

		ArrayList<Vector> resultV = new ArrayList<Vector>();
		
		boolean match = false;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			while(!match) {
				for(int i = 0; i < inputV.size(); i++) {
					//newW = currW + (r*x0*(z-y))
					//print all vectors
					a = getA(inputV.get(i).getX0(), inputV.get(i).getX1(), w0, w1, b, wb);
				
					Vector newVec = new Vector(inputV.get(i).getX0(), inputV.get(i).getX1(), w0, w1, wb, a, y, inputV.get(i).getZ()); 
					y = getY(a, t);
					w0 = w0 + (r * inputV.get(i).getX0() * (inputV.get(i).getZ() - y));
					w1 = w1 + (r * inputV.get(i).getX1() * (inputV.get(i).getZ() - y));	
					wb = wb + (r * b * (inputV.get(i).getZ() - y));

					
					resultV.add(newVec);
				}
			
				match = isMatch(resultV, inputV);
			}

			//print resultV
			writer.write("Result: " + "\n");
			writer.write("x0 \t" + "x1 \t" + "b \t" + "w0 \t" + "w1 \t" + "wb \t" + "a \t" + "y \t" + "z \t" + "\n");
			System.out.println("Result :");
			System.out.println("x0 \t" + "x1 \t" + "b \t" + "w0 \t" + "w1 \t" + "wb \t" + "a \t" + "y \t" + "z \t");
			
			for(int j = 0; j < resultV.size(); j++) {
				DecimalFormat numberFormat = new DecimalFormat("#0.0");		//limits the format to 1 decimal place only
				writer.write(String.valueOf(resultV.get(j).getX0()) + " \t" + String.valueOf(resultV.get(j).getX1()) + " \t" + String.valueOf(b) + " \t" + String.valueOf(numberFormat.format(resultV.get(j).getW0())) + " \t" + String.valueOf(numberFormat.format(resultV.get(j).getW1())) + " \t" + String.valueOf(numberFormat.format(resultV.get(j).getWb())) + " \t" + String.valueOf(numberFormat.format(resultV.get(j).getA())) + " \t" +  String.valueOf(numberFormat.format(resultV.get(j).getY())) + " \t" + String.valueOf(numberFormat.format(resultV.get(j).getZ())) + "\n");
				System.out.printf(resultV.get(j).getX0() + " \t" + resultV.get(j).getX1() + " \t" + b + " \t" + numberFormat.format(resultV.get(j).getW0()) + " \t" + numberFormat.format(resultV.get(j).getW1()) + " \t" + numberFormat.format(resultV.get(j).getWb()) + " \t" + numberFormat.format(resultV.get(j).getA()) + " \t" +  numberFormat.format(resultV.get(j).getY()) + " \t" + numberFormat.format(resultV.get(j).getZ()));
				System.out.println();
			}
			System.out.println();
			writer.close();
		} catch(FileNotFoundException e){
			System.out.println("File not found");
		} catch(Exception e){
			System.out.println(e.getMessage());
		}		

	}

	//Checks if w0 is the same for all iteration
	//checks if w1 is the same for all iteration
	//checks if wb is the same for all iteration

	public static boolean isMatch(ArrayList<Vector> resultV, ArrayList<Vector> inputV) {
		double w0 = resultV.get(resultV.size() - inputV.size()).getW0();
		double w1 = resultV.get(resultV.size() - inputV.size()).getW1();
		double wb = resultV.get(resultV.size() - inputV.size()).getWb();
		for(int i = (resultV.size()-inputV.size()); i < resultV.size(); i++) {
			if(w0 != resultV.get(i).getW0() || w1 != resultV.get(i).getW1() || wb != resultV.get(i).getWb()) {
				return false;
			}
		}
		return true;	
	}
	public static double getA(double x0, double x1, double w0, double w1, double b, double wb) {
		double a = (x0*w0) + (x1*w1) + (b*wb); 
		return a;
	}

	public static double getY(double a, double t) {
		// System.out.println("T: " + t);
		// System.out.println("A: " + a);
		if (a > t) {
			// System.out.println("entered");
			return 1;
		}else return 0;
	}

}

