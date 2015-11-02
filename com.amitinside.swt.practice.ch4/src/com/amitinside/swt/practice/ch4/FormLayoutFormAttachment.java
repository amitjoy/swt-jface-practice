package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FormLayoutFormAttachment {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final FormLayout layout = new FormLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		shell.setLayout(layout);
		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Button");
		FormData data = new FormData();
		data.height = 50;
		data.right = new FormAttachment(100, -50);
		data.left = new FormAttachment(0, 10);
		data.top = new FormAttachment(1, 4, 0);
		button.setLayoutData(data);

		final Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("Button 2");
		data = new FormData();
		button2.setLayoutData(data);
		data.bottom = new FormAttachment(100, 0);
		data.top = new FormAttachment(button, 5);
		data.left = new FormAttachment(button, 0, SWT.LEFT);
		data.right = new FormAttachment(button, 0, SWT.RIGHT);

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
