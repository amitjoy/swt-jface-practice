package com.amitinside.swt.practice.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RowLayoutTest {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginLeft = 12;
		layout.marginTop = 0;
		layout.justify = true;
		shell.setLayout(layout);
		new Button(shell, SWT.PUSH).setText("one");
		new Button(shell, SWT.PUSH).setText("two");
		new Button(shell, SWT.PUSH).setText("three");
		new Button(shell, SWT.PUSH).setText("four");
		new Button(shell, SWT.PUSH).setText("five");
		new Button(shell, SWT.PUSH).setText("six");
		final Button b = new Button(shell, SWT.PUSH);
		b.setText("seven");
		b.setLayoutData(new RowData(100, 100));
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
