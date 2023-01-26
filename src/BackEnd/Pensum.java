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
			int semester = 0;
			int row = 0;
			int[] prerequisites;
			String[] classN;
			String[] prer;
			sc.nextLine();
			while(sc.hasNextLine()) {
				classN = sc.nextLine().split(",");
				prerequisites = null;
				if(!classN[3].equals("0")) {
					prer = classN[3].split(";");
					prerequisites = new int[prer.length];
					for(int i = 0; i < prer.length; i ++) {
						prerequisites[i] = Integer.parseInt(prer[i]);
					}
				}
				if(Integer.parseInt(classN[6]) > semester) {
					row = 1;
					semester = Integer.parseInt(classN[6]);
				}
				sp.insert(
					row,
					semester,
					new Class(
						Integer.parseInt(classN[0]),
						Integer.parseInt(classN[1]),
						Integer.parseInt(classN[2]),
						semester,
						prerequisites,
						Boolean.valueOf(classN[4]),
						false,
						classN[5]
					)
				);
				row += 1;
			}
			sp.graphic(root.replace(".csv",""));
			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}