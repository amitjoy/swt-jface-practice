package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FormLayoutComplex {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final FormLayout layout = new FormLayout();
		shell.setLayout(layout);
		final Button one = new Button(shell, SWT.PUSH);
		one.setText("One");
		FormData data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, 5);
		data.bottom = new FormAttachment(50, -5);
		data.right = new FormAttachment(50, -5);
		one.setLayoutData(data);

		final Composite composite = new Composite(shell, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		composite.setLayout(gridLayout);
		final Button two = new Button(composite, SWT.PUSH);
		two.setText("two");
		GridData gridData = new GridData(GridData.FILL_BOTH);
		two.setLayoutData(gridData);
		final Button three = new Button(composite, SWT.PUSH);
		three.setText("three");
		gridData = new GridData(GridData.FILL_BOTH);
		three.setLayoutData(gridData);
		final Button four = new Button(composite, SWT.PUSH);
		four.setText("four");
		gridData = new GridData(GridData.FILL_BOTH);
		four.setLayoutData(gridData);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(one, 5);
		data.bottom = new FormAttachment(50, -5);
		data.right = new FormAttachment(100, -5);
		composite.setLayoutData(data);

		final Button five = new Button(shell, SWT.PUSH);
		five.setText("five");
		data = new FormData();
		data.top = new FormAttachment(one, 5);
		data.left = new FormAttachment(0, 5);
		data.bottom = new FormAttachment(100, -5);
		data.right = new FormAttachment(100, -5);
		five.setLayoutData(data);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
