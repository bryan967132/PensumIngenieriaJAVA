package SparseMatrix;
import BackEnd.Class;
public class NodeI {
	public int row;
	public int column;
	public Class clasS;
	public NodeI right;
	public NodeI left;
	public NodeI up;
	public NodeI down;
	public NodeI(int row,int column,Class clasS) {
		this.row = row;
		this.column = column;
		this.clasS = clasS;
		right = null;
		left = null;
		up = null;
		down = null;
	}
}