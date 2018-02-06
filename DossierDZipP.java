import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DossierDZipP {
	public Path nomDossier;
	public ArrayList<Fichier> listFiles = new ArrayList<>();
	public DossierDZipP(Path n) throws IOException {
		nomDossier = n;
		//Path p = Paths.get(s);
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(n);
			for (Path file : stream) {
				//System.out.println(file.toString());
				Fichier f = new Fichier(file);
				listFiles.add(f);
			}
		}
		catch (DirectoryIteratorException ex) {
			throw ex.getCause();
		}
	}
	
	//A modifier apres: et s'il y a des paquetages???
	public boolean verifierStructure(TypeFichier t) {	//Verifier la structure du dossier, renvoie true si tout est bon
		boolean flag = true;
		for (int i=0; i<listFiles.size(); i++) {
			if (listFiles.get(i).type!=t) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public boolean compiler(Runtime r, ArrayList<Fichier> list) throws IOException {
		boolean result = true;
		for (int i=0; i<list.size(); i++) {
			String[] cmd = {"javac", "Soumission/"+nomDossier.toString()+"/"+list.get(i)};
			try {	
				Process pro = r.exec(cmd);
				System.out.println(pro.getOutputStream());
				System.out.println(pro.getErrorStream());
			}
			catch (IOException e) {
				System.out.println(e);
				result = false;
			}
		}
		return result;
	}
	
}
