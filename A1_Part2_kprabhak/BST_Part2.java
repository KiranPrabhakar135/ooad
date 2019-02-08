//CSE 522: Assignment 1, Part 2

  class BST_Part2 {

	  public static void main(String[] args) {
		AbsTree tr = new DupTree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
		}
  }
  
  abstract class AbsTree {

	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null) {
				right = add_node(n);
				right.parent = this;				
			}				
			else
				right.insert(n);
		else if (left == null) {
			left = add_node(n);
			left.parent = this;
		}			
		else
			left.insert(n);
	}
	
	public void delete(int n) {  		
	// adapt Part 1 solution and use here
		//
		// *** do not modify this method ***
		//
		AbsTree t = find(n);
		if(t != null && t.get_duplicates_count() > 1) {
			t.decreament_duplicates_count();
			return;
		}
		if (t == null) { // n is not in the tree
			System.out.println("Unable to delete " + n + " -- not in the tree!");
			return;
		} else if (t.left == null && t.right == null) { // n is at a leaf position
			if (t != this) // if t is not the root of the tree
				case1(t);
			else
				System.out.println("Unable to delete " + n + " -- tree will become empty!");
			return;
		} else if (t.left == null || t.right == null) {
			// t has one subtree only
			if (t != this) { // if t is not the root of the tree
				case2(t);
				return;
			} else { // t is the root of the tree with one subtree
				if (t.left != null)
					case3L(t);
				else
					case3R(t);
				return;
			}
		} else 
			// t has two subtrees; replace n with the smallest value in t's right subtree
			case3R(t);
	}
	
	protected void case1(AbsTree t) {  
	// adapt Part 1 solution and use here
		AbsTree parent = t.parent;
		if(parent != null) {
			if(parent.left == t) {
				parent.left = null;
			}
			else {
				parent.right = null;
			}
			
			t.parent = null;
		}
	}
	
	protected void case2(AbsTree t) { 
	// adapt Part 1 solution and use here
		AbsTree parent = t.parent;
		t.parent = null;
		AbsTree subTree = null;
		if(t.left == null) {
			subTree = t.right;
		}
		else {
			subTree = t.left;
		}
		
		if(parent.left == t) {
			parent.left = subTree;
			
		}
		else {
			parent.right = subTree;
		}
		subTree.parent = parent;
	}
	
	protected void case3L(AbsTree t) { 
	// adapt Part 1 solution and use here
		
		AbsTree max = t.left.max();
		int rootNodeValue = max.value;
		int duplicatesCount = max.get_duplicates_count();
		if(duplicatesCount > 1) {
			max.set_duplicates_count(1);
		}
		t.delete(max.value);
		t.value = rootNodeValue;
		t.set_duplicates_count(duplicatesCount);
		max.set_duplicates_count(duplicatesCount);
	}

	protected void case3R(AbsTree t) {  
	// adapt Part 1 solution and use here
		AbsTree minInRight = t.right.min();
		int rootNodeValue = minInRight.value;	
		int duplicatesCount = minInRight.get_duplicates_count();
		if(duplicatesCount > 1) {
			minInRight.set_duplicates_count(1);
		}
		t.delete(minInRight.value);
		t.value = rootNodeValue;
		t.set_duplicates_count(duplicatesCount);
		minInRight.set_duplicates_count(duplicatesCount);
	}

	private AbsTree find(int n) {
	// adapt Part 1 solution and use here
		if(value == n) {
			return this;
		}
		if(value < n) {			 
			if(right != null) {
				return right.find(n);
			}
			return null;
		}
		else {
			if(left != null) {
				return left.find(n);
			}
			return null;
		}
	}
	
	public AbsTree min() {
		if(this.left == null) {
			return this;
		}
		else {
			return left.min();
		}
	}

	public AbsTree max() {
	// adapt Part 1 solution and use here
		if(this.right == null) {
			return this;
		}
		else {
			return right.max();
		}
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;
	protected AbsTree parent;

	// Protected Abstract Methods
	
	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract void decreament_duplicates_count();
	protected abstract int get_duplicates_count();
	protected abstract void set_duplicates_count(int value);
	// Additional protected abstract methods, as needed
}


class Tree extends AbsTree {

	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}
	
	protected void decreament_duplicates_count() {
				
	}
	
	protected int get_duplicates_count() {
		return 0;
	}
	
	protected void set_duplicates_count(int value) {
		 
	}
	
	// define additional protected methods here, as needed

}

class DupTree extends AbsTree {

	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}
	
	protected void decreament_duplicates_count() {
		count--;
	}
	protected int get_duplicates_count() {
		return count;
	}
	
	protected void set_duplicates_count(int value) {
		 count = value;
	}
	
	// define additional protected methods here, as needed

	protected int count;
}