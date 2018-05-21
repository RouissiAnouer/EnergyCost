package energycostchecker.model;

import java.net.URI;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.*;

import energycostchecker.ColumnPlugin;
import energycostchecker.listener.ConvertFile;



public class CostCheckerVisitorCU extends ASTVisitor {
	
	
	private String name;
	ArrayList<ColumnPlugin> myCol = new ArrayList<ColumnPlugin>();
	String Methode;
	URI path;
	CompilationUnit cu;
	ConvertFile convert = ConvertFile.getInstance();

	public CostCheckerVisitorCU(CostCheckerModel model) {
		path = convert.getPath();
		cu = convert.getCu();
	}
	
	public boolean visit(TypeDeclaration node) {
		name = node.getName().toString()+".java";
		return true;
	}
	
	public boolean visit(MethodDeclaration node) {
		Methode = node.getName().toString();
		return true;
	}

	public boolean visit(SimpleType node) {
		String type = node.getName().toString();
		myCol.add(new ColumnPlugin(path.toString(),Methode,"Declaration of '"+type+"'",cu.getLineNumber(node.getStartPosition())));
		return true;
	}
	
	public boolean visit(PrimitiveType node) {
		String type = node.getPrimitiveTypeCode().toString();	
		myCol.add(new ColumnPlugin(path.toString(),name,"Declaration of '"+type+"'",cu.getLineNumber(node.getStartPosition())));
		return true;
		
	}
	
	public ArrayList<ColumnPlugin> getMyCol() {
		return myCol;
	}

}
