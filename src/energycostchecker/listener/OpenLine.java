package energycostchecker.listener;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

public class OpenLine {
	
	OpenFile openThisFile = new OpenFile();
	
	public void openLine(String message,Integer line) {		
		try {
			openThisFile.fileOpen(message);
		} catch (PartInitException e) {
		}
		IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage pages = win.getActivePage();
		IEditorPart openEditors = pages.getActiveEditor();
		
		if(openEditors instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) openEditors;
			IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
			IRegion lineInfo =null;
			try {
				lineInfo = document.getLineInformation(line - 1);
				textEditor.selectAndReveal(lineInfo.getOffset(),lineInfo.getLength());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
