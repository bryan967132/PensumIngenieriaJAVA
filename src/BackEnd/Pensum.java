package BackEnd;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import SparseMatrix.SparseMatrix;
public class Pensum {
	SparseMatrix sp = new SparseMatrix();
	public void readFilePensum(String root) {
		try {
			BufferedReader file = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(root),"utf-8"
				)
			);
			Scanner sc = new Scanner(file);
			String[] classN;
			String[] prer;
			int[] prerequisites;
			while(sc.hasNextLine()) {
				classN = sc.nextLine().split(",");
				prerequisites = new int[0];
				if(!classN[3].replace(" ","").equals("")) {
					prer = classN[3].split(";");
					prerequisites = new int[prer.length];
					for(int i = 0; i < prer.length; i ++) {
						prerequisites[i] = Integer.parseInt(prer[i]);
					}
				}
				Class clasS = new Class(
						Integer.parseInt(classN[0]),
						Integer.parseInt(classN[1]),
						Integer.parseInt(classN[2]),
						Integer.parseInt(classN[6]),
						prerequisites,
						Boolean.valueOf(classN[4]),
						false,
						classN[5]
				);
				//System.out.println(clasS);
				sp.insert(clasS);
			}
			sc.close();
			System.out.println(sp.dot());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}