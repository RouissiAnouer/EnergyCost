package energycostchecker.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.eclipse.jdt.core.dom.*;

public class CreateParser {
	
	@SuppressWarnings("deprecation")
	ASTParser parser = ASTParser.newParser(AST.JLS3);
	public ASTParser ConvertingtoPath(URI path) {
		StringBuilder sb = new StringBuilder();
		try {
		File file = new File(path);
		FileReader fileread;
		fileread = new FileReader(file);
		BufferedReader br = new BufferedReader(fileread);
		String s;
		try {
			while((s = br.readLine()) != null){
				sb.append(s+"\n");
			}
		} catch (IOException e) {e.printStackTrace();}
		}
		catch(Exception e) {e.printStackTrace();		}
		parser.setSource(sb.toString().toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		return parser;
		
	}

}
