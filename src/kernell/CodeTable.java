/**
 * 
 */
package kernell;

import java.util.*;

/**
 * @author Bianca
 *
 */
public class CodeTable {
	
	private Map<Character, String> m;
	
	/**
	 * 
	 */
	public CodeTable(HuffmanTree h) {
		m = new TreeMap<Character, String>();
		h.putCodes(this);	
	}
		
	/**
	 * Metodo que retorna o codigo de um char
	 * 
	 * @param c caractere
	 * @return code codigo do caractere
	 */
	String getCode(char c){
		return m.get(c);
	}
	
	/**
	 * Metodo que insere um codigo de um caractere
	 * 
	 * @param c caractere
	 * @param s codigo
	 */
	void putCode(char c, String s){
		m.put(c, s);
	}
	
}
