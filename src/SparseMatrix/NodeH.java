package SparseMatrix;
public class NodeH {
	public int index;
	public NodeH previous;
	public NodeH next;
	public NodeI access;
	public NodeI last;
	public NodeH(int index) {
		this.index = index;
		previous = null;
		next = null;
		access = null;
		last = null;
	}
}