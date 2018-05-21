package energycostchecker.view;

import java.awt.GridLayout;
import java.net.URI;
import java.util.ArrayList;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.*;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.part.*;

import energycostchecker.ColumnPlugin;
import energycostchecker.listener.*;

public class SelectionView extends ViewPart {
	
	private static SelectionView INSTANCE;
	
	public static SelectionView getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SelectionView();
		}
		return INSTANCE;
	}

	static Integer sum =0;
	String Message="";
	private PageBook pagebook;
	private TableViewer tableviewer;
	private TextViewer textviewer;
	private String name="";
	URI _path;
	String _type;
	ArrayList<ColumnPlugin> myCol = new ArrayList<ColumnPlugin>();
	Action DoubleClickAction;

	OpenFile openThisFile = new OpenFile();
	
	OpenLine openThisLine = new OpenLine();
	// TODO FixMe
	/*ItemSelectListener listener = new ItemSelectListener();
	
	ISelectionListener thislistener = listener.getListener();*/
	
	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			
			if (sourcepart != SelectionView.this) {
			    showSelection(sourcepart, selection);
			}
		}
	};
	

	@SuppressWarnings("restriction")
	public void showSelection(IWorkbenchPart sourcepart, ISelection selection) {
		setContentDescription(sourcepart.getTitle() + " (" + selection.getClass().getName() + ")");
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection is = (IStructuredSelection) selection;
			Object firstElement = is.getFirstElement();
			if(firstElement instanceof PackageFragment) {
				showItemsPck(((PackageFragment) firstElement));
			}
			else if(firstElement instanceof ICompilationUnit) {
				showItemsSrc((ICompilationUnit) firstElement);
			}
			else {
				try {
				showAlert(firstElement.getClass().getName());
				}catch(Exception e) {
					
				}
			}
		}
				
	}

	
	public void showAlert(String firstElement) {
		textviewer.setDocument(new Document("Please select a PackageFragment or a Java file.\n"+firstElement+" is not a Package or Java file"));
		pagebook.showPage(textviewer.getControl());
	}
	
	private void createColumnCU(){
		String[] titles = {"Path" ,"Class" , "Message", "Line"};
		int[] bounds = {200,200,0};
		
		TableViewerColumn col = createTableViewerColumn(titles[0],bounds[2],0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._filepath);
			}
		});
		col=createTableViewerColumn(titles[1],bounds[1],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._filewarning);
			}
		});
		col=createTableViewerColumn(titles[2],bounds[0],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._filepack);
			}
		});
		col=createTableViewerColumn(titles[3],bounds[1],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._Line);
			}
		});
	}
	
	private void createColumnPA() {
		
		String[] titles = {"Type", "Class" , "Message", "Cost" , "Path"};
		int[] bounds = {100,250,0};
		
		TableViewerColumn col = createTableViewerColumn(titles[0],bounds[0],0);
		
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._type);
			}
		});
		col=createTableViewerColumn(titles[1],bounds[1],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._filepath);
			}
			
		});
		col=createTableViewerColumn(titles[2],bounds[1],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._filewarning);
			}
		});
		col=createTableViewerColumn(titles[3],bounds[0],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._Line);
			}
		});
		col=createTableViewerColumn(titles[4],bounds[2],1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ColumnPlugin p = (ColumnPlugin) element;
				return String.valueOf(p._filepack);
			}
		});
		
	}

	public void showItemsSrc(ICompilationUnit item) {
		ConvertFile convert = ConvertFile.getInstance();
		myCol = convert.ConvertingCompilation(item);
		Clear();
		createColumnCU();
		tableviewer.setInput(myCol);
		pagebook.showPage(tableviewer.getControl());
		myCol.clear();
	}

	@SuppressWarnings("restriction")
	public void showItemsPck(PackageFragment item) {
		
		try {
			IJavaElement[] it = item.getChildren();
			for (IJavaElement i:it) {
				ConvertFile convert = ConvertFile.getInstance();
				convert.ConvertingPackage(i);
				_path = convert.getPath();
				_type = convert.get_type();
				name = convert.getName();
				Message = convert.getMessage();
				sum = convert.getSum();
				myCol.add(new ColumnPlugin(_type,name,Message,sum,_path.toString()));
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		Clear();
		createColumnPA();
		tableviewer.setInput(myCol);
		pagebook.showPage(tableviewer.getControl());
		myCol.clear();
	}
	
	public void Clear() {
		TableColumn [] columns = tableviewer.getTable().getColumns();
		for (TableColumn column:columns) {
			column.dispose();
		}
		tableviewer.setInput(null);
		tableviewer.refresh();
		
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,final int colNumber) {	
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableviewer,SWT.LEFT | SWT.LEFT);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(false);
		column.setMoveable(true);
		
		return viewerColumn;
	}
	
	public void createPartControl(Composite parent) {
		pagebook = new PageBook(parent, SWT.NONE);
		tableviewer = new TableViewer(pagebook, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableviewer.getTable().setLayoutData(new GridLayout());
		tableviewer.getTable().setLinesVisible(true);
		tableviewer.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tableviewer.getTable().setHeaderVisible(true);
		tableviewer.setContentProvider(new ArrayContentProvider());
		makeAction();
		hookDoubleClickAction();
		getSite().setSelectionProvider(tableviewer);
		textviewer = new TextViewer(pagebook, SWT.H_SCROLL | SWT.V_SCROLL);
		textviewer.setEditable(false);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);
	}
	//TODO FixMe
	private void makeAction() {
		DoubleClickAction = new Action() {
			public void run() {
				int index = tableviewer.getTable().getSelectionIndex();
				Table table = tableviewer.getTable();
				int a = table.getColumnCount();
				if(a == 5) {
					try {
						String openFile = table.getItem(index).getText(4);
						openThisFile.fileOpen(openFile);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}else if(a == 4) {
					String openLine = table.getItem(index).getText(0);
					String i = table.getItem(index).getText(3);
					Integer line = Integer.parseInt(i);
					openThisLine.openLine(openLine,line);
				}
			}
		};
	}
	//TODO FixMe
	private void hookDoubleClickAction() {
		tableviewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				DoubleClickAction.run();
			}
		});
	}
	

	public void setFocus() {
		pagebook.setFocus();
	}

	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(listener);
		super.dispose();
	}

}
