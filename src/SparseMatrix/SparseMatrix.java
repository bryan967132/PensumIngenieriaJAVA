package SparseMatrix;
import BackEnd.Class; 
public class SparseMatrix {
	public HeaderList accessR;
	public HeaderList accessC;
	public SparseMatrix() {
		accessR = new HeaderList();
		accessC = new HeaderList();
	}
	public void insert(Class clasS) {
		if(!accessR.isHereIndex(clasS.getSemester())) {
			accessR.add(clasS.getSemester());
		}
		if(!accessC.isHereIndex(clasS.getCode())) {
			accessC.add(clasS.getCode());
		}
		NodeI node = new NodeI(clasS.getSemester(),clasS.getCode(),clasS);
		addRow(clasS.getSemester(),node);
		addColumn(clasS.getCode(),node);
	}
	public void addRow(int row,NodeI node) {
		NodeH currentR = accessR.first;
		while(currentR != null) {
			if(currentR.index == row) {
				if(currentR.access != null) {
					if(node.column < currentR.access.column) {
						currentR.access.left = node;
						currentR.access.left.right = currentR.access;
						currentR.access = currentR.access.left;
						return;
					}
					if(node.column > currentR.last.column) {
						currentR.last.right = node;
						currentR.last.right.left = currentR.last;
						currentR.last = currentR.last.right;
						return;
					}
					NodeI currentC = currentR.access;
					while(currentC.right != null) {
						if(node.column > currentC.column && node.column < currentC.right.column) {
							node.left = currentC;
							node.right = currentC.right;
							
							currentC.right.left = node;
							currentC.right = node;
							return;
						}
						currentC = currentC.right;
					}
				}
				currentR.access = node;
				currentR.last = currentR.access;
				return;
			}
			currentR = currentR.next;
		}
	}
	private void addColumn(int column,NodeI node) {
		NodeH currentC = accessC.first;
		while(currentC != null) {
			if(currentC.index == column) {
				if(currentC.access != null) {
					if(node.row < currentC.access.row) {
						currentC.access.up = node;
						currentC.access.up.down = currentC.access;
						currentC.access = currentC.access.up;
						return;
					}
					if(node.row > currentC.access.row) {						
						currentC.last.down = node;
						currentC.last.down.up = currentC.last;
						currentC.last = currentC.last.down;
						return;
					}
					NodeI currentR = currentC.access;
					while(currentR.down != null) {
						if(node.row > currentR.row && node.row < currentR.down.row) {
							node.up = currentR;
							node.down = currentR.down;
							
							currentR.down.up = node;
							currentR.down = node;
							return;
						}
						currentR = currentR.down;
					}
				}
				currentC.access = node;
				currentC.last = currentC.access;
				return;
			}
			currentC = currentC.next;
		}
	}
	public Class get(int row,int column) {
		NodeH currentR = accessR.first;
		NodeI currentC;
		while(currentR != null) {
			if(currentR.index == row) {
				currentC = currentR.access;
				while(currentC != null) {
					if(currentC.column == column) {
						return currentC.clasS;
					}
					currentC = currentC.right;
				}
			}
			currentR = currentR.next;
		}
		return null;
	}
	public void print() {
		NodeH currentR = accessR.first;
		NodeI currentC;
		while(currentR != null) {
			currentC = currentR.access;
			while(currentC != null) {
				System.out.println(currentC.clasS);
				currentC = currentC.right;
			}
			System.out.println();
			currentR = currentR.next;
		}
		System.out.println();
	}
	public String dot() {
		String dot = "digraph T{\n\tnode[shape=box fontname=\"Arial\" fillcolor=\"white\" style=filled];";
		dot += "\n\tRoot[label = \"Capa 0\", group=\"0\"];";
		NodeH currentR = accessR.first;
		while(currentR != null) {
			dot += "\n\tF" + currentR.index + "[group=\"0\" fillcolor=\"plum\"];";
			currentR = currentR.next;
		}
		NodeH currentC = accessC.first;
		while(currentC != null) {
			dot += "\n\tC" + currentC.index + "[group=\"" + currentC.index + "\" fillcolor=\"powderblue\"];";
			currentC = currentC.next;
		}
		currentC = accessC.first;
		NodeI currentRI;
		while(currentC != null) {
			currentRI = currentC.access;
			while(currentRI != null) {
				dot += "\n\tN" + currentRI.row + "_" + currentRI.column + "[group=\"" + currentRI.column + "\" label=\"" + currentRI.clasS.getName() + "\"];";
				currentRI = currentRI.down;
			}
			currentC = currentC.next;
		}
		dot += "\n\tsubgraph columnHeader {\n\t\trank = same;";
		String join = "\n\t\tRoot -> ";
		currentC = accessC.first;
		while(currentC != null) {
			join += "C" + currentC.index;
			currentC = currentC.next;
			if(currentC != null) join += " -> ";
		}
		dot += join + ";" + join + "[dir=back];\n\t}";
		currentR = accessR.first;
		NodeI currentCI;
		while(currentR != null) {
			dot += "\n\tsubgraph row" + currentR.index + "{\n\t\trank = same;";
			join = "\n\t\tF" + currentR.index + " -> ";
			currentCI = currentR.access;
			while(currentCI != null) {
				join += "N" + currentCI.row + "_" + currentCI.column;
				currentCI = currentCI.right;
				if(currentCI != null) join += " -> ";
			}
			dot += join + ";" + join + "[dir=back];\n\t}";
			currentR = currentR.next;
		}
		dot += "\n\tsubgraph rowHeader {";
	    join = "\n\t\tRoot -> ";
	    currentR = accessR.first;
	    while(currentR != null) {
	    	join += "F" + currentR.index;
	    	currentR = currentR.next;
			if(currentR != null) join += " -> ";
	    }
	    dot += join + ";" + join + "[dir=back];\n\t}";
	    currentC = accessC.first;
	    while(currentC != null) {
	    	dot += "\n\tsubgraph column" + currentC.index + "{";
	    	join = "\n\t\tC" + currentC.index + " -> ";
	    	currentRI = currentC.access;
	    	while(currentRI != null) {
	    		join += "N" + currentRI.row + "_" + currentRI.column;
	    		currentRI = currentRI.down;
				if(currentRI != null) join += " -> ";
	    	}
	    	dot += join + ";" + join + "[dir=back];\n\t}";
	    	currentC = currentC.next;
	    }
		dot += "\n}";
		return dot;
	}
}