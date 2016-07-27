package sampling;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Used WEKA.jar package
 * http://www.cs.waikato.ac.nz/ml/index.html
*/
import weka.core.*;
import weka.core.converters.*;
import weka.filters.unsupervised.attribute.*;

import java.io.*;

/**
 * @author Dhanesh
 *
 */
class RandomSampleGenerate {
	
		/*Read the instances one by one and save it to a 
		 * new file if the record number matches with 
		 * the random numbers generated
		 */
	public void randomSample(String filePath, String samplePath, int numOfRec, int numOfSamples){
	
		try{
			File fileRead = new File(filePath);
			File fileWrite = new File(samplePath);
			BufferedWriter output = new BufferedWriter(new FileWriter(fileWrite));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead)));
		
			String line = null;
			int[] myRandNumbers= new int[numOfSamples];
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			int i=0;
			
			for(i=0;i<numOfRec;i++)
				numbers.add(i+1);
			Collections.shuffle(numbers);
			for(i=0;i<numOfSamples;i++)
				myRandNumbers[i] = numbers.get(i);
			Arrays.sort(myRandNumbers);
			i=0;
			int j=0;
			while( (line = br.readLine()) != null){
				String [] attr = line.split(",");
				j++;
				if(j == myRandNumbers[i]){
					i++;
					System.out.println(j);
					for(int k=0;k<attr.length;k++){
						
							output.write(Double.parseDouble(attr[k])+",");
							output.flush();
							
					}
					output.newLine();
					output.flush();
				}
			}
		
			br.close();
			output.close();
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
