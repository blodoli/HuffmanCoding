/**
 * 
 */
package kernell;

/**
 * @author Bianca
 *
 */
class Node {
	private char label;
	private int cost;
	private String code;
	private Node left;
	private Node right;
	private Node root;
	
	/**
	 * Construtor por copia
	 * 
	 * @param n No a ser copiado
	 */
	Node (Node n){
		this.label = n.label;
		this.cost = n.cost;
		this.code = n.code;
		this.left = n.left;
		this.right = n.right;
		this.root = n.root;
	}
	
	/**
	 *Construtor que inicia label e cost
	 * 
	 * @param l label
	 * @param c cost
	 */
	Node (char l, int c){
		this.label = l;
		this.cost = c;
		this.code = "";
		this.left = this.right = this.root = null;
	}
	
	/**
	 * Construtor que inicia left e right
	 * 
	 * @param l left
	 * @param r right
	 */
	Node (Node l, Node r){
		this.left = l;
		this.right = r;
		this.left.root = this;
		this.right.root = this;
		this.label = '\0';
		this.cost = l.cost + r.cost;
		this.code = "";
	}
	
	/**
	 * Metodo que retorna o codigo do pai
	 * 
	 * @return root.code codigo do pai
	 */
	String getParentCode(){
		return this.root.code;
	}
	
	/**
	 * Getter de label
	 * 
	 * @return label Atributo label
	 */
	char getLabel (){
		return this.label;
	}
	
	/**
	 * Getter de cost
	 * 
	 * @return cost Atributo cost
	 */
	int getCost (){
		return this.cost;
	}
	
	/**
	 * Getter de code
	 * 
	 * @return code Atributo code
	 */
	String getCode (){
		return this.code;
	}
	
	/**
	 * Setter de code
	 *
	 *@param c code
	 */
	void setCode (String c){
		this.code=c;
	}
	
	/**
	 * Metodo que aumenta o custo em 1
	 */
	void increaseCost(){
		this.cost++;
	}
	
	/**
	 * Metodo que verifica se um no e uma folha
	 * 
	 * @return true se e folha
	 * @return false se nao e folha
	 */
	boolean isLeaf(){
		if (this.right == null && this.left == null) return true;
		else return false;
	}
	
	/**
	 * @return left Subarvore a esquerda do no
	 */
	HuffmanTree LeftSubtree (){
		return new HuffmanTree(this.left);
	}
	
	/**
	 * @return right Subarvore a direita do no
	 */
	HuffmanTree RightSubtree (){
		return new HuffmanTree(this.right);
	}
}
