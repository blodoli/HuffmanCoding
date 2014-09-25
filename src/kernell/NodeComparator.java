/**
 * 
 */
package kernell;

import java.util.Comparator;

/**
 * 
 * @author Bianca
 *
 */
class NodeComparator implements Comparator<Node>
{
	@Override
	public int compare(Node o1, Node o2) {
		if (o1.getCost() < o2.getCost())
		{
			return -1;
		}
		if (o1.getCost() > o2.getCost())
		{
			return 1;
		}
		return 0;
	}
}


