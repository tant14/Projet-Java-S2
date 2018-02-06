import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	/*A modifier apres:
	 * Verifier si tous les fichiers dans Soumission sont .zip (ou autre types comme .jar, .tar(.gz), etc.
	 * Modifier dans "commande" le nom du dossier de destination
	 */
	public static void main (String[] args) throws IOException, InterruptedException {
		String[] commande = {"unzip", "Soumission/lala.zip", "-d", "Soumission/lala"};
		//a modifier: le nom du dossier de destination
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec(commande);
		p.waitFor();
		Path pa = Paths.get("Soumission");
		ArrayList<Path> listDossier = new ArrayList<>();
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(pa);
			for (Path dossier : stream) {
				listDossier.add(dossier);
			}
		}
		catch (DirectoryIteratorException ex) {
			throw ex.getCause();
		}
		Path n = listDossier.get(0);
		DossierDZipP d = new DossierDZipP(n);
		System.out.println(d.nomDossier.toString());
		for (int i=0; i<d.listFiles.size(); i++) {
			System.out.println(d.listFiles.get(i).nom.toString());
		}
		boolean b = d.verifierStructure(TypeFichier.java);
		System.out.println(b);
		boolean b1 = d.compiler(runtime, d.listFiles);
		System.out.println(b1);
	}
}
