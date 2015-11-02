package com.amitinside.jface.practice.ch14;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Item;

/**
 * This class represents the cell modifier for the PersonEditor program
 */
public class PersonCellModifier implements ICellModifier {
	private final Viewer viewer;

	public PersonCellModifier(Viewer viewer) {
		this.viewer = viewer;
	}

	/**
	 * Returns whether the property can be modified
	 * 
	 * @param element
	 *            the element
	 * @param property
	 *            the property
	 * @return boolean
	 */
	@Override
	public boolean canModify(Object element, String property) {
		// Allow editing of all values
		return true;
	}

	/**
	 * Returns the value for the property
	 * 
	 * @param element
	 *            the element
	 * @param property
	 *            the property
	 * @return Object
	 */
	@Override
	public Object getValue(Object element, String property) {
		final Person p = (Person) element;
		if (PersonEditor.NAME.equals(property))
			return p.getName();
		else if (PersonEditor.MALE.equals(property))
			return Boolean.valueOf(p.isMale());
		else if (PersonEditor.AGE.equals(property))
			return p.getAgeRange();
		else if (PersonEditor.SHIRT_COLOR.equals(property))
			return p.getShirtColor();
		else
			return null;
	}

	/**
	 * Modifies the element
	 * 
	 * @param element
	 *            the element
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 */
	@Override
	public void modify(Object element, String property, Object value) {
		if (element instanceof Item)
			element = ((Item) element).getData();

		final Person p = (Person) element;
		if (PersonEditor.NAME.equals(property))
			p.setName((String) value);
		else if (PersonEditor.MALE.equals(property))
			p.setMale(((Boolean) value).booleanValue());
		else if (PersonEditor.AGE.equals(property))
			p.setAgeRange((Integer) value);
		else if (PersonEditor.SHIRT_COLOR.equals(property))
			p.setShirtColor((RGB) value);

		// Force the viewer to refresh
		viewer.refresh();
	}
}