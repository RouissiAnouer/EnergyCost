package energycostchecker.model;

import org.eclipse.jdt.core.dom.*;

public class CostCheckerVisitorPA extends ASTVisitor {

	private CostCheckerModel model;
	
	private String _type;
	
	private int sum;
	
	public CostCheckerVisitorPA(CostCheckerModel model) {
		this.model = model; 
	}
	
	public boolean visit(TypeDeclaration node) {
		if (node.isInterface()) {
			_type = "Interface";
		} else {
			_type = "Class";
		}
		return true;
	}

	public boolean visit(SimpleType node) {
		String type = node.getName().toString();
		sum += model.getCostModel(type);	
		return true;
	}
	
	public boolean visit(PrimitiveType node) {
		String type = node.getPrimitiveTypeCode().toString();
		sum += model.getCostModel(type);
		return true;	
	}
	
	public String getType() {
		return _type;
	}

	public Integer getSum() {
		return sum;
	}
	
}
