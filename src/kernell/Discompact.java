/**
 * 
 */
package kernell;

import java.io.*;

/**
 * 
 * @author Bianca
 *
 */
public abstract class Discompact {
	
	/**
	 * Metodo que descompacta um arquivo de entrada, salvando num arquivo de saida, pelo nome do arquivo
	 *
	 * @param Sinput 		nome do arquivo de entrada
	 * @param Soutput		nome do arquivo de saida
	 * @throws IOException
	 */
	public static void discompactFile(String Sinput, String Soutput) throws IOException{
		File input = new File(Sinput);
		File output = new File(Soutput);
		if (output.exists()) output.delete();
		if (!output.exists()) output.createNewFile();
		discompactFile(input,output);
	}
	
	/**
	 * Metodo que descompacta um arquivo de entrada, salvando num arquivo de saida
	 *
	 * @param input			arquivo de entrada
	 * @param output		arquivo de saida
	 * @throws IOException
	 */
	public static void discompactFile(File input, File output) throws IOException{
		String encoding = "ISO8859_1";
		InputStreamReader inputs = new InputStreamReader (new BufferedInputStream( new FileInputStream(input)),encoding);  
		HuffmanTree h = new HuffmanTree(inputs);
		System.out.println("Montou arvore");
		inputs.close();
		inputs = new InputStreamReader (new BufferedInputStream( new FileInputStream(input)),encoding);  
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output, true), encoding));
		h.decode(inputs, out);
		System.out.println("Descompactou");
	}
}
