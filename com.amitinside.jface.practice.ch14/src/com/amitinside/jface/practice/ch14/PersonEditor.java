package com.amitinside.jface.practice.ch14;

import static org.mihalis.opal.utils.SWTGraphicUtil.centerShell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.ralfebert.rcputils.concurrent.UIProcess;
import de.ralfebert.rcputils.databinding.Realms;
import de.ralfebert.rcputils.random.RandomData;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

/**
 * This class demonstrates CellEditors It allows you to create and edit Person
 * objects
 */
public class PersonEditor extends ApplicationWindow {
	// Table column names/properties
	public static final String NAME = "Name";
	public static final String MALE = "Male?";
	public static final String AGE = "Age Range";
	public static final String SHIRT_COLOR = "Shirt Color";

	public static final String[] PROPS = { NAME, MALE, AGE, SHIRT_COLOR };

	// The data model
	private final java.util.List people;

	/**
	 * Constructs a PersonEditor
	 */
	public PersonEditor() {
		super(null);
		people = new ArrayList();
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
		shell.setText("Person Editor");
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
	protected Control createContents(final Composite parent) {
		centerShell(parent.getShell());
		parent.setLayout(new FillLayout(SWT.VERTICAL));
		final Composite upperComposite = new Composite(parent, SWT.NONE);
		final Composite belowComposite = new Composite(parent, SWT.NONE);
		upperComposite.setLayout(new GridLayout(1, false));

		// Add a button to create the new person
		final Button newPerson = new Button(upperComposite, SWT.PUSH);
		newPerson.setText("Create New Person");

		// Add the TableViewer
		final TableViewer tv = new TableViewer(upperComposite,
				SWT.FULL_SELECTION);
		tv.setContentProvider(new PersonContentProvider());
		tv.setLabelProvider(new PersonLabelProvider());
		tv.setInput(people);

		// Set up the table
		final Table table = tv.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		new TableColumn(table, SWT.CENTER).setText(NAME);
		new TableColumn(table, SWT.CENTER).setText(MALE);
		new TableColumn(table, SWT.CENTER).setText(AGE);
		new TableColumn(table, SWT.CENTER).setText(SHIRT_COLOR);

		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// Add a new person when the user clicks button
		newPerson.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				final Person p = new Person();
				p.setName("Name");
				p.setMale(true);
				p.setAgeRange(Integer.valueOf("0"));
				p.setShirtColor(new RGB(255, 0, 0));
				people.add(p);
				tv.refresh();

				final UIProcess uiProcess = new UIProcess(Display.getCurrent(),
						"My Job") {

					@Override
					protected void runInUIThread() {
						try {
							System.out.println("Executing");
							TimeUnit.SECONDS.sleep(10);
						} catch (final InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					protected void runInBackground(IProgressMonitor monitor) {
						// TODO Auto-generated method stub

					}
				};
				uiProcess.schedule();
			}
		});

		// final Create the final cell editors
		final CellEditor[] editors = new CellEditor[4];
		editors[0] = new TextCellEditor(table);
		editors[1] = new CheckboxCellEditor(table);
		editors[2] = new ComboBoxCellEditor(table, AgeRange.INSTANCES,
				SWT.READ_ONLY);
		editors[3] = new ColorCellEditor(table);

		// Set the editors, cell modifier, and column properties
		tv.setColumnProperties(PROPS);
		tv.setCellModifier(new PersonCellModifier(tv));
		tv.setCellEditors(editors);

		// Let's build the other table viewer to use Data Binding Context
		final TableViewerBuilder t = new TableViewerBuilder(belowComposite);

		t.createColumn("Stock").build();
		t.createColumn("Rate").alignRight().build();

		ViewerSupport.bind(t.getTableViewer(), createStocks(),
				BeanProperties.values(new String[] { "name", "rate" }));

		return upperComposite;
	}

	private IObservableList createStocks() {
		final WritableList stocks = new WritableList(Realms.WHATEVER);

		final RandomData rand = new RandomData();
		for (int i = 0; i < 500; i++) {
			stocks.add(new Stock(rand.someCharacters(4,
					RandomData.UPPERCASE_CHARS), new BigDecimal(rand
					.someNumber(1d, 200d))));
			rand.newData();
		}

		final Job job = new Job("stock exchange") {

			@Override
			@SuppressWarnings("unchecked")
			protected IStatus run(IProgressMonitor monitor) {

				for (final Stock stock : (Collection<Stock>) stocks) {
					rand.newData();
					stock.setRate(stock.getRate().add(
							new BigDecimal(rand.someNumber(-20, 20))
									.divide(new BigDecimal(100))));
				}

				this.schedule(200);
				return Status.OK_STATUS;
			}

		};

		job.schedule();

		return stocks;
	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new PersonEditor().run();
	}
}