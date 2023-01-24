package SparseMatrix;
public class HeaderList {
	public NodeH first;
	NodeH last;
	public HeaderList() {
		first = null;
		last = null;
	}
	public void add(int index) {
		if(first != null) {
			if(index < first.index) {
				first.previous = new NodeH(index);
				first.previous.next = first;
				first = first.previous;
				return;
			}
			if(index > last.index) {
				last.next = new NodeH(index);
				last.next.previous = last;
				last = last.next;
				return;
			}
			NodeH current = first;
			while(current.next != null) {
				if(index > current.index && index < current.next.index) {
					NodeH tmp = new NodeH(index);
					tmp.previous = current;
					tmp.next = current.next;
					
					current.next.previous = tmp;
					current.next = tmp;
					return;
				}
				current = current.next;
			}
		}
		first = new NodeH(index);
		last = first;
	}
	public boolean isHereIndex(int index) {
		if(first != null) {
			NodeH current = first;
			while(current != null) {
				if(current.index == index) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}
	public void print() {
		NodeH current = first;
		while(current != null) {
			System.out.print(current.index + " ");
			current = current.next;
		}
	}
}