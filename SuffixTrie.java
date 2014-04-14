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
	// TODO: Implement Limit
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
	public void add(T data){
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
	 */
	public void reset(){
		this.pos = null;
	}
	/*
	 * Used to walk through the trie
	 */
	public boolean step(T data){
		// Do something sensible when there is no data
		if (root == null){
			return true;
		}
		// If child is null set child to root
		Node<T> child = pos == null ? root: pos.getDown();
		child = this.getChild(child, data);
		// If pos has child data
		if (child != null){
			// Pos is child byte
			pos = child;
			// Return false, did not trip
			return false;
		} else {
			// Else return true, did trip
			return true;
		}
	}

	/* 
	 * Gets the index of the position
	 */
	public int getIndex(){
		return pos.getIndex();
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

	/*
	 * Gets a child of a node
	 */
	public Node<T> getChild(Node<T> n, T data){
		// Child is n.down
		Node<T> child = n.getDown();
		// While child not null
		while (child != null){
			// If child.data is data return true
			if (child.getData().equals(data)){
				return child;
			}
		}
		// Return false
		return null;
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
