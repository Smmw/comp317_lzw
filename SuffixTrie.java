/**
 * An implementation of a suffix trie
 * Will likely be generic when implemented
 *
 * Severin Mahoney-Marsh 2014
 * 1181754
 */
public class SuffixTrie<T>{
	Node root;
	// For steping/walking I havn't named the method yet
	Node pos;
	// The next avaliable index
	int index = 0;
	// Stops the trie from growing forever
	int limit;
	
	/*
	 * Makes a SuffixTrie with no data
	 */
	public SuffixTrie(int limit){
		self.limit = limit;
	}

	/*
	 * Adds a node to the trie at the current position
	 */
	public void Add(T data){
		// Check if no root
		// If so add node as root, set pos to root, return
		// Check if pos has no child
		// If so add child
		// Else go to leftest sister of child and add
	}

	/*
	 * Used to walk through the trie
	 */
	public boolean Step(T data){
		// If pos is null throw hissy fit
		// If pos has child data
		// Pos is child byte
		// Return false, did not trip
		// Else return true, did trip
	}

	/*
	 * Checks if a node has a child with the value data
	 */
	public boolean hasChild(Node n, T data){
		// Child is n.down
		// While child not null
		// If child.data is data return true
		// End
		// Return false
	}

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
