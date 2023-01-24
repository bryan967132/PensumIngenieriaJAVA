package Main;
import BackEnd.Pensum;
public class PensumIngenieria {
	public static void main(String[] args) {
		Pensum p = new Pensum();
		p.readFilePensum("CursosECYS.csv");
	}
}