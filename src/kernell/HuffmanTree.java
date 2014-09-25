/**
 * 
 */
package kernell;

import java.io.*;
import java.util.*;

/**
 * 
 * @author Bianca
 *
 */
class HuffmanTree {
	private Node root; 		//raiz da arvore de Huffman

	/**
	 *Construtor de uma arvore de Huffman a partir de sua raiz 
	 * 
	 * @param r raiz
	 */
	HuffmanTree(Node r){
		this.root = r;
	}

	/**
	 *Setter de root 
	 * 
	 * @param r raiz
	 */
	void setTree(Node r){
		this.root = r;
	}

	/**
	 * Construtor da arvore de Huffman a partir de um arquivo nao compactado
	 * 
	 * @param inputs		InputStreamReader do arquivo a ser compactado
	 * @param out			Writer do arquivo compactado
	 * @throws IOException 
	 */
	HuffmanTree(InputStreamReader inputs, Writer out) throws IOException{
		Node[] freq = null;
		boolean hasIncreased;
		int different = 0, total = 0;
		char b;  
		int i = 0;  
		String s = new String();
		while ((i=inputs.read()) > -1) { 
			b=(char)i;
			total++;
			hasIncreased = false;
			if(freq!=null){
				for (Node elem : freq){
					if (elem.getLabel() == (char) b) {
						elem.increaseCost();
						hasIncreased = true;
						break;
					}
				}
			}
			if (!hasIncreased){
				different++;
				Node[] _freq = new Node[different];
				for (int i1 = 0; i1<different-1; i1++){
					_freq[i1] = freq[i1];
				}
				_freq[different-1] =  new Node ((char)b, 1);
				freq = _freq;
			}
		}  
		
		s += total;
		s+= ' ';
		s+=different;
		s+=' ';
		for (Node elem : freq){
			s += elem.getLabel();
			s += elem.getCost();
			s += ' ';
		}
		this.buildTree(freq);
		out.write(s);
	}

	/**
	 * Construtor da arvore de Huffman a partir de um arquivo compactado
	 * 
	 * @param inputs		InputStreamReader do arquivo a ser descompactado
	 * @throws IOException 
	 */
	HuffmanTree (InputStreamReader inputs) throws IOException{
		byte b;
		int different = 0, c;
		int i;
		char l;
		Node []freq;
		if ((i=inputs.read()) > -1) {  
			b=(byte)i;
			while (((i=inputs.read()) > -1) && ((b=(byte)i) != ' ')){ }
			while (((i=inputs.read()) > -1) && ((b=(byte)i) != ' ')){
				different *= 10;
				different += (b-'0');
			}
			freq = new Node[different];
			for (int j = 0; j<different; j++){
				c = 0;
				i = inputs.read();
				l = (char)i;
				while (((b=(byte)inputs.read()) != ' ')){
					c *= 10;
					c += (b-'0');
				}
				freq[j] = new Node(l,c);
			}
			this.buildTree(freq);
		}
	}

	/**
	 * Metodo que decodifica um arquivo de entrada (compactado) em um arquivo de saida
	 * 
	 * @param inputs 	InputStreamReader de entrada
	 * @param out		Writer de saida
	 * @throws IOException 
	 */
	void decode(InputStreamReader inputs, Writer out) throws IOException {
		int b=0b10000000;
		long total = 0;
		int different = 0;
		int i;
		HuffmanTree aux = new HuffmanTree(this.root);
		while (((i=inputs.read()) > -1) && ((char)i != ' ')){ 
			total *= 10;
			total += (i-'0');
		}
		while (((i=inputs.read()) > -1) && ((char)i != ' ')){
			different *= 10;
			different += (i-'0');
		}
		for (int j = 0; j<different; j++){
			i = inputs.read();
			while (((char)inputs.read() != ' ')){ }
		}
		while (total > 0){
			i = inputs.read();
			for(int k = 0; k<8; k++){
				if (aux.root.isLeaf()){
					out.append(aux.root.getLabel());
					total--;
					if (total == 0) break;
					aux.setTree(this.root);
				}
				if((i & b) == 0){
					aux = aux.root.LeftSubtree();
				}
				else{
					aux = aux.root.RightSubtree();
				}
				i-= (i & b);
				b = b>>1;
			}
			b=0b10000000;
		}
		out.close();
	}

	/**
	 * Metodo que monta a arvore a partir de uma lista de frequencia de caracteres
	 * 
	 * @param freq Lista de frequencia de caracteres
	 */
	private void buildTree(Node[] freq) {
		Comparator<Node> comp = new NodeComparator();
		PriorityQueue<Node> q = new PriorityQueue<Node>(freq.length, comp);
		for (Node elem : freq) q.add(elem);
		Node aux, aux1, aux2;
		aux1 = q.poll();
		aux2 = q.poll();
		while(aux2!=null){
			aux=new Node(aux1,aux2);
			q.add(aux);
			aux1 = q.poll();
			aux2 = q.poll();
		}
		this.root = new Node(aux1);
		this.encode();
	}

	/**
	 * Metodo que insere os codigos na arvore de Huffman
	 */
	private void encode() {
		this.root.setCode("");
		if (!this.root.isLeaf()){
			this.root.LeftSubtree().encode('0');
			this.root.RightSubtree().encode('1');
		}
	}

	/**
	 * Metodo auxiliar ao que insere os codigos na arvore de Huffman
	 */
	private void encode(char i) {
		String c = this.root.getParentCode();
		this.root.setCode(c+i);
		if (!this.root.isLeaf()){
			this.root.LeftSubtree().encode('0');
			this.root.RightSubtree().encode('1');
		}
	}

	/**
	 * Metodo que monta a tabela de codigos
	 * 
	 * @param t Tabela de codigos
	 */
	void putCodes(CodeTable t){
		if (!(this.root.isLeaf())){
			this.root.LeftSubtree().putCodes(t);
			this.root.RightSubtree().putCodes(t);
		}
		if(this.root.isLeaf()){
			t.putCode(this.root.getLabel(),this.root.getCode());
		}
	}

}

