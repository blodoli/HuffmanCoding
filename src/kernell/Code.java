/**
 * 
 */
package kernell;

/**
 * 
 * @author Bianca
 *
 */
class Code {
	String code;		//codigo em uma String de '0's e '1's
	
	/**
	 * Contrutor que inicia code com ""
	 * 
	 */
	Code (){
		code = "";
	}
	
	/**
	 * Getter do atributo code 
	 * 
	 * @return code	Codigo em forma de uma string de '0's e '1's
	 */
	String getCode(){
		return this.code;
	}
	
	/**
	 * Retorna o atributo code convertido em uma string resultante da String code
	 *  
	 * @return binCode Codigo em forma de uma string
	 */
	String getCodeBin(){
		String binCode = "";
		char []_code = code.toCharArray();
		int c = 0;
		int j = 0;
		for (int i = 0; i<code.length(); i++){
			c *= 2;
			c = (c+(int)_code[i]-(int)'0');
			j++;
			if (j==8){
				binCode  += new Character((char)c).toString();
				j = 0;
				c = 0;
			}
		}
		if (j!=0){
			while (j!=8){
				c *=2;
				j++;
			}
			binCode += new Character((char)c).toString();
		}

		return binCode;
	}

	/**
	 * Metodo que adiciona um trecho ao codigo
	 * 
	 * @param s
	 */
	void append(String s) {
		code+=s;
	}
	
	/**
	 * Metodo que zera o valor de code
	 */
	void clear() {
		code = "";
	}
	
	/**
	 * Metodo que retorna o tamanho do codigo
	 * 
	 * @return code.length() tamanho do codigo
	 */
	int length(){
		return code.length();
	}
}
