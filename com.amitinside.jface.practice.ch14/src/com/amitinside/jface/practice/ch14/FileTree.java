package com.amitinside.jface.practice.ch14;

import static com.amitinside.swt.layout.grid.GridLayoutUtil.applyGridLayout;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates TreeViewer. It shows the drives, directories, and
 * files on the system.
 */
public class FileTree extends ApplicationWindow {
	/**
	 * FileTree constructor
	 */
	public FileTree() {
		super(null);
	}

	/**
	 * Runs the application
	 */
	public void run() {
		// Don't return from open() until window closes
		setBlockOnOpen(true);

		// Open the main window
		open();

		// Dispose the display
		Display.getCurrent().dispose();
	}

	/**
	 * Configures the shell
	 * 
	 * @param shell
	 *            the shell
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);

		// Set the title bar text and the size
		shell.setText("File Tree");
		shell.setSize(400, 400);
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param parent
	 *            the main window
	 * @return Control
	 */
	@Override
	protected Control createContents(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		// composite.setLayout(new GridLayout(1, false));
		applyGridLayout(composite).numColumns(1);

		// Add a checkbox to toggle whether the labels preserve case
		final Button preserveCase = new Button(composite, SWT.CHECK);
		preserveCase.setText("&Preserve case");

		// Create the tree viewer to display the file tree
		final TreeViewer tv = new TreeViewer(composite);
		tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider());
		tv.setInput("root"); // pass a non-null that will be ignored

		// When user checks the checkbox, toggle the preserve case attribute
		// of the label provider
		preserveCase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				final boolean preserveCase = ((Button) event.widget)
						.getSelection();
				final FileTreeLabelProvider ftlp = (FileTreeLabelProvider) tv
						.getLabelProvider();
				ftlp.setPreserveCase(preserveCase);
			}
		});
		return composite;
	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new FileTree().run();
	}
}