/**
 * An implementation of a suffix trie
 * Will likely be generic when implemented
 *
 * Severin Mahoney-Marsh 2014
 * 1181754
 */
public class SuffixTrie<T>{
	/**
	 * A node for the suffix trie
	 * Will be root, internal, or leaf node
	 *
	 * Severin Mahoney-Marsh 2014
	 * 1181754
	 */
	private class Node<T>{
		T data;
		Node left;
		Node down;
		int index;
		
		/*
		 * The humble constructor. Where would we be without it?
		 */
		public Node(T data, int index){
			this.data = data;
			this.index = index;
		}
		
		/*
		 * Gets the data, a shocker I know
		 */
		public T getData(){
			return this.data;
		}

		/*
		 * Sets the node's left node
		 */
		public void setLeft(Node left){
			// Helpful sanity check
			// XXX: Remove this when everything is tested
			if (this.left != null){
				System.err.println("A node has overwritten it's left value!");
			}
			this.left = left;
		}

		/*
		 * Gets the left node
		 */
		public Node getLeft(){
			return this.left;
		}

		/* 
		 * Sets the nodes down node
		 */
		public void setDown(Node down){
			// Helpful sanity check
			// XXX: Remove this when everything is tested
			if (this.down != null){
				System.err.println("A node has overwritten it's child! Won't somebody think of " +
					"the children!");
			}
			this.down = down;
		}

		/*
		 * Gets the down node i.e. the child node
		 */
		public Node getDown(){
			return this.down;
		}

		/*
		 * Gets the index
		 */
		public int getIndex(){
			return this.index;
		}
	}
}
