package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RowLayoutHorizontal {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		new Button(shell, SWT.PUSH).setText("one");
		new Button(shell, SWT.PUSH).setText("two");
		new Button(shell, SWT.PUSH).setText("three");
		new Button(shell, SWT.PUSH).setText("four");
		new Button(shell, SWT.PUSH).setText("five");
		new Button(shell, SWT.PUSH).setText("six");
		new Button(shell, SWT.PUSH).setText("seven");
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
