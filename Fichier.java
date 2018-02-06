import java.nio.file.Path;
import java.util.Arrays;

public class Fichier {
	public Path nom;
	public TypeFichier type;
	public Fichier (Path p) {
		nom = p;
		//System.out.println(p.toString());
		String[] t = p.toString().split("[.]");
		System.out.println(Arrays.toString(t));
		switch (t[t.length-1]) {
			case "java":		type = TypeFichier.java;
								break;
			case "c":			type = TypeFichier.c;
								break;
			case "txt":			type = TypeFichier.txt;
								break;
			case "pdf":			type = TypeFichier.pdf;
								break;
			default:			type = TypeFichier.unknown;
								break;
		}
		/*String aux = t[(t.length)-1];
		if (aux.compareTo("java")==0) {
			type = TypeFichier.java;
		}
		else if (aux.compareTo("c")==0)
			type = TypeFichier.c;
		else if (aux.compareTo("pdf")==0)
			type = TypeFichier.pdf;
		else if (aux.compareTo("txt")==0) {
			type = TypeFichier.txt;
		}
		else 
			type = TypeFichier.unknown;*/
	}
}
