/**
 * 
 */
package genericCheckpointing.util;

import java.lang.reflect.Field;

/**
 *	serialize the object to the file checkpointFile in xml format
 */
public class XmlStrGenerator implements ObjStrGenerator {

	@Override
	public String generate(Object obj) {
		
		// get class name of obj(StudentRecord/EmployeeRecord)
		Class<?> cls = obj.getClass();
		String clsName = cls.getName();
		StringBuilder strBdr = new StringBuilder();
		strBdr.append("<person clsType=\"");
		strBdr.append(clsName);
		strBdr.append("\">");
		strBdr.append(System.getProperty("line.separator"));
		
		// get fields of class
		Field[] fieldArr = cls.getDeclaredFields();
		
		String typeStr;
		String nameStr;
		String valueStr;
		
		for (Field f : fieldArr) {
			try {
				f.setAccessible(true);
				typeStr = f.getType().getSimpleName();
				nameStr = f.getName();
				valueStr = f.get(obj).toString();
				
				// serialize the object to the file checkpointFile
				strBdr.append("<");
				strBdr.append(nameStr);
				strBdr.append(" fieldType=\"");
				strBdr.append(typeStr);
				strBdr.append("\">");
				strBdr.append(valueStr);
				strBdr.append("</");
				strBdr.append(nameStr);
				strBdr.append(">");
				strBdr.append(System.getProperty("line.separator"));
				
				
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				//e.printStackTrace();
				System.exit(1);
			} catch (IllegalAccessException e) {
				System.err.println(e.getMessage());
				//e.printStackTrace();
				System.exit(1);
			}
		}
		strBdr.append("</person>");
		strBdr.append(System.getProperty("line.separator"));
		return strBdr.toString();
	}
	
}
