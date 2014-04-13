/**
 * An implementation of a suffix trie
 * Will likely be generic when implemented
 *
 * Severin Mahoney-Marsh 2014
 * 1181754
 */
public class SuffixTrie<T>{
	Node<T> root;
	// For steping/walking I havn't named the method yet
	Node<T> pos;
	// The next avaliable index
	int index = 0;
	// Stops the trie from growing forever
	int limit;
	
	/*
	 * Makes a SuffixTrie with no data
	 */
	public SuffixTrie(int limit){
		this.limit = limit;
	}

	/*
	 * Adds a node to the trie at the current position
	 */
	public void Add(T data){
		Node<T> n = new Node<T>(data, this.index);
		this.index += 1;
		// Check if no root
		// If so add node as root, return
		if (root == null){
			root = n;
		// Check if pos is null
		// If so add node to leftest sister of root
		} else if (pos == null){
			Node<T> leftest = root;
			while (leftest.getLeft() != null){
				leftest = leftest.getLeft();
			}
			leftest.setLeft(n);
		// Check if pos has no child
		// If so add child
		} else if (pos.getDown() == null){
			pos.setLeft(n);
		// Else go to leftest sister of child and add
		} else {
			Node<T> leftest = pos.getDown();
			while (leftest.getLeft() != null){
				leftest = leftest.getLeft();
			}
			leftest.setLeft(n);
		}
	}

	/*
	 * Used to reset the position for stepping and adding
	 * Since I decided not to have a root node it is nessicary to use this
	 * before you start stepping
	 */
	public void Reset(){
		this.pos = this.root;
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
		return true;
	}

	/*
	 * Checks if a node has a child with the value data
	 */
	public boolean hasChild(Node<T> n, T data){
		// Child is n.down
		Node<T> child = n.getDown();
		// While child not null
		while (child != null){
			// If child.data is data return true
			if (child.getData().equals(data)){
				return true;
			}
		}
		// Return false
		return false;
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
		Node<T> left;
		Node<T> down;
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
		public void setLeft(Node<T> left){
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
		public Node<T> getLeft(){
			return this.left;
		}

		/* 
		 * Sets the nodes down node
		 */
		public void setDown(Node<T> down){
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
		public Node<T> getDown(){
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
