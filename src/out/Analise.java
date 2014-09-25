/**
 * 
 */
package out;

import java.io.*;
import java.util.Random;

import kernell.*;

/**
 * @author Bianca
 *
 */
public class Analise {
	
	/**
	 * 
	 * @param input
	 * @param output
	 * @param resultado
	 * @throws IOException
	 */
	public static void AnaliseLoremIpsum(String input, String output, String resultado) throws IOException{
		String []l = new String[14];
		l[0] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ";
		l[1] = "Aenean vestibulum interdum erat eget sagittis. ";
		l[2] = "Phasellus ut est metus. ";
		l[3] = "Nulla facilisi. Sed eget elit consequat, efficitur purus non, sodales purus. ";
		l[4] = "Mauris commodo, enim et venenatis rhoncus, leo metus porttitor ante, vitae pellentesque libero nulla ut leo. ";
		l[5] = "Nullam sed metus auctor, scelerisque est ut, ornare elit. ";
		l[6] = "Proin bibendum, nisl in rhoncus venenatis, libero dui auctor ex, eget eleifend ex dui vel nunc. ";
		l[7] = "Duis rhoncus aliquam quam, sed scelerisque lorem dapibus ac. ";
		l[8] = "Nam posuere magna eget mauris euismod egestas. ";
		l[9] = "Aliquam ac leo vel justo efficitur convallis lacinia sit amet lorem. ";
		l[10]= "Vivamus pulvinar augue mi, non aliquet nisl tincidunt eget. ";
		l[11]= "Aenean feugiat augue non placerat ullamcorper. ";
		l[12]= "Sed orci lacus, sodales vitae tellus tincidunt, tincidunt lacinia velit.";
		l[13]= "Ut a sodales urna. ";
				
		
		File lorem = new File(input);
		File result = new File(resultado);
		File loremc = new File(output);
		
		if (lorem.exists()) lorem.delete();
		if (result.exists()) result.delete();
		
		if (!lorem.exists()) lorem.createNewFile();
		if (!result.exists()) result.createNewFile();
		
		Writer out;
		Writer resultw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result, true)));
		
		String s = new String();
		
		Random gerador = new Random();
		
		for (int i = 1; i<=100; i++){
			s = "";
			System.out.print("Arquivo ");
			System.out.println(i);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(lorem, true)));
			while(lorem.length()<i*1000000) out.append(l[gerador.nextInt(14)]);
			out.close();
			Compact.compactFile(lorem, loremc);
			s += lorem.length();
			s += '\t';
			s += loremc.length();
			s += '\n';
			resultw.append(s);
			
		}
		lorem.delete();
		loremc.delete();
		resultw.close();
	}

	public static void AnaliseASCII(String input, String output, String resultado) throws IOException{
		File ascii = new File(input);
		File result = new File(resultado);
		File asciic = new File(output);
		
		if (ascii.exists()) ascii.delete();
		if (result.exists()) result.delete();
		
		if (!ascii.exists()) ascii.createNewFile();
		if (!result.exists()) result.createNewFile();
		
		Writer out;
		Writer resultw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result, true)));
		
		String s = new String();
		
		Random gerador = new Random();
		
		for (int i = 1; i<=100; i++){
			s = "";
			System.out.print("Arquivo ");
			System.out.println(i);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ascii, true)));
			for(int j = 0; j<1000000; j++) out.append((char)gerador.nextInt(128));
			out.close();
			Compact.compactFile(ascii, asciic);
			s += ascii.length();
			s += '\t';
			s += asciic.length();
			s += '\n';
			resultw.append(s);
			
		}
		ascii.delete();
		asciic.delete();
		resultw.close();
	}
	public static void main(String[] args) throws IOException{
		
	}
}
