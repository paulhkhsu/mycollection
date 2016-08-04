package com.myrest.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;

/**
 * This class audit createdBy and created.
 */
public class DBActivityInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		// if the created and createdby are not set then set them.
		setValueIfNull(state, propertyNames, "createdBy", getUser());
		setValueIfNull(state, propertyNames, "created", new Date());

		// Update the updatedBy field.
		setValue(state, propertyNames, "updatedBy", getUser());
		setValue(state, propertyNames, "updated", new Date());

		return true;
	}

	/**
	 * Set the value defined by propartyToSet to value
	 * 
	 * @param currentState
	 * @param propertyNames
	 * @param propertyToSet
	 * @param value
	 */
	private void setValue(Object[] currentState, String[] propertyNames,
			String propertyToSet, Object value) {
		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			if (propertyName.equals(propertyToSet)) {
				currentState[i] = value;
			}
		}
	}

	/**
	 * Set the value defined by propartyToSet to value if the property to set is
	 * null.
	 * 
	 * @param currentState
	 * @param propertyNames
	 * @param propertyToSet
	 * @param value
	 */
	private void setValueIfNull(Object[] currentState, String[] propertyNames,
			String propertyToSet, Object value) {
		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			if (propertyName.equals(propertyToSet)) {
				boolean isEmpty = false;
				if (currentState[i] instanceof String) {
					isEmpty = StringUtils.isEmpty((String) currentState[i]);
				}
				if (currentState[i] == null || isEmpty) {
					currentState[i] = value;
				}
			}
		}
	}

	/**
	 * Get the name of the class that called something from DaoBaseImpl
	 * 
	 * @return the String containing the class name
	 */
	private String getUser() {
		String username = this.getClass().getSimpleName();

		// Authentication authentication = SecurityContextHolder.getContext()
		// .getAuthentication();
		//
		// // See if there is any security authentication enabled.
		// if (authentication != null
		// && StringUtils.isNotEmpty(authentication.getName())) {
		// username = authentication.getName();
		//
		// } else {
		// // Try to figure out what class called this.
		// StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		//
		// for (int i = 0; i < ste.length - 1; i++) {
		// String className = ste[i].getClassName();
		// if (className.toLowerCase().contains("daobaseimpl")) {
		// className = ste[i + 1].getClassName();
		// if (!className.toLowerCase().contains("daobaseimpl")) {
		// String simpleName = className.substring(className.lastIndexOf(".") +
		// 1);
		// username = simpleName + "."
		// + ste[i + 1].getMethodName();
		// break;
		// }
		// }
		// }
		// }
		return username;
	}
}
