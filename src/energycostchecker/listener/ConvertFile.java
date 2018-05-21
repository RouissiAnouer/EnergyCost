package energycostchecker.listener;

import java.net.URI;
import java.util.ArrayList;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import energycostchecker.*;
import energycostchecker.model.*;

public class ConvertFile {
	
	MessageToShow show = new MessageToShow();
	CreateParser parserClass = new CreateParser();
	ASTParser parser;
	static int sum;
	String Message;
	String Methode;
	String name;
	String _type;
	ArrayList<ColumnPlugin> myCol = new ArrayList<ColumnPlugin>();
	URI path;
	CompilationUnit cu;
	
	private static ConvertFile INSTANCE;
	
	public static ConvertFile getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConvertFile();
		}
		return INSTANCE;
	}
	
	
	public ArrayList<ColumnPlugin> ConvertingCompilation(ICompilationUnit item) {
		path = item.getResource().getLocationURI();
		parser = parserClass.ConvertingtoPath(path);
		cu = (CompilationUnit) parser.createAST(null);
		CostCheckerVisitorCU visitor = new CostCheckerVisitorCU(Activator.getDefault().getModel());
		cu.accept(visitor);
		myCol = visitor.getMyCol();
		return myCol;
	}

	public boolean ConvertingPackage(IJavaElement item) {
		path = item.getResource().getLocationURI();
		parser = parserClass.ConvertingtoPath(path);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		CostCheckerVisitorPA visitor = new CostCheckerVisitorPA(Activator.getDefault().getModel());
		cu.accept(visitor);
		sum = visitor.getSum();
		Message = show.CalculateSum(sum);
		_type=visitor.getType();
		name=item.getElementName();
		return false;
	}
	
	public URI getPath() {
		return path;
	}

	public int getSum() {
		return sum;
	}

	public String getMessage() {
		return Message;
	}

	public String getName() {
		return name;
	}

	public String get_type() {
		return _type;
	}

	public ArrayList<ColumnPlugin> getMyCol() {
		return myCol;
	}

	public CompilationUnit getCu() {
		return cu;
	}
} 
