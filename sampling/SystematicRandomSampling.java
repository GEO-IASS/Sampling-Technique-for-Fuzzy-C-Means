package sampling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class SystematicRandomSampling {

	public void systematicSampling(String fileLoc,String sampleLoc,int numOfRec,int numOfSamples){

		try{
			File fileRead = new File(fileLoc);
			File fileWrite = new File(sampleLoc);
			BufferedWriter output = new BufferedWriter(new FileWriter(fileWrite));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead)));
		
			String line = null;
			int[] myRandNumbers= new int[numOfSamples];
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			int i=0;
			
			int samplingInterval = (int)(numOfRec/numOfSamples);
			for(i=0;i<samplingInterval;i++)
				numbers.add(i+1);
			Collections.shuffle(numbers);
			int randNum = numbers.get(0);
			
			for(i=0;i<numOfSamples;i++)
				myRandNumbers[i] = randNum + samplingInterval*i;
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
