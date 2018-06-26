import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.senai.sc.edu.projetomaria.dao.FamiliaDAO;
import br.senai.sc.edu.projetomaria.io.FamiliaReader;
import br.senai.sc.edu.projetomaria.model.Familia;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path p1 = Paths.get("C:/Users/Aluno/Documents/csv_maria/familia.csv");
		FamiliaDAO dao = new FamiliaDAO();
		FamiliaReader fm = new FamiliaReader(p1);
		List<Familia> familiaList = fm.readFamilia();
		dao.delete(familiaList);
//		for (Familia familia : familiaList) {
//			if(!familiaList.isEmpty()){
//				dao.update(familia);
//			}else{
//				System.out.println("NÃO ESTA INSERINDO");
//			}
//		}
//		
		
	}

}
