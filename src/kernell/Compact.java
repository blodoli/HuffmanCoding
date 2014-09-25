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
public abstract class Compact {
	/** 
	 * Metodo que descompacta um arquivo de entrada, salvando num arquivo de saida, pelo nome do arquivo
	 *
	 * @param Sinput
	 * @param Soutput
	 * @throws IOException
	 */
	public static void compactFile(String Sinput, String Soutput) throws IOException{
		File input = new File(Sinput);
		File output = new File(Soutput);
		compactFile(input,output);
	}
	/**
	 * Metodo que compacta um arquivo de entrada, salvando num arquivo de saida
	 *
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public static void compactFile(File input, File output) throws IOException{
		if (output.exists()) output.delete();
		if (!output.exists()) output.createNewFile();
		String encoding = "ISO8859_1";
		InputStreamReader inputs = new InputStreamReader (new BufferedInputStream( new FileInputStream(input)),encoding);  
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output, true), encoding));
		out.flush();
		HuffmanTree h = new HuffmanTree(inputs, out);
		System.out.println("Montou arvore");
		CodeTable t = new CodeTable(h);
		System.out.println("Montou tabela");
		Code c = new Code();
		char b;  
		int i = 0; 
		inputs.close();
		inputs = new InputStreamReader (new BufferedInputStream( new FileInputStream(input)),encoding);  
		while ((i=inputs.read()) > -1) {  
			b = (char)i;
			c.append(t.getCode((char)b));
			if (c.length() >= 8){
				i = c.length() - c.length()%8;
				Code c1 = new Code();
				c1.append(c.getCode().substring(0, i));
				out.append(c1.getCodeBin());
				c1.clear();
				c1.append(c.getCode().substring(i));
				c.clear();
				c.append(c1.getCode());
			}
		}  
		System.out.println("Compactou");
		out.append(c.getCodeBin());
		out.close();
	}
}
