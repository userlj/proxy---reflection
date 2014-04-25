package genericCheckpointing.util;

import java.lang.reflect.Method;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * class for reflection
 */
public class ReflectUtil {
	
	/**
	 * private empty constructor
	 */
	private ReflectUtil() {}
	
	/**
	 * using reflection to create objects
	 * @param childList
	 * @param clsType
	 * @return object
	 * @throws Exception
	 */
	public static Object reflectObj(NodeList childList, String clsType) throws Exception{
		
		Class<?> cls = Class.forName(clsType);	// create a class using reflection
		Object obj = cls.newInstance();	// create a new instance of the class
		
		for (int j = 0; j < childList.getLength(); j++) {
			
			Node cNode = childList.item(j);
			String cName = cNode.getNodeName();	// ii ff dd ll
			String typeVal = cNode.getAttributes().getNamedItem("fieldType").getNodeValue();	// int float...
			String cVal = cNode.getLastChild().getTextContent().trim();	// 0, 1, 2... 
			
			Method meth = null;
			
			// for different field type, invoke corresponding method
			// eight primitive types
			if ("int".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, int.class);
				meth.invoke(obj, Integer.parseInt(cVal));
			} else if ("float".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, float.class);
				meth.invoke(obj, Float.parseFloat(cVal));
			} else if ("double".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, double.class);
				meth.invoke(obj, Double.parseDouble(cVal));
			} else if ("long".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, long.class);
				meth.invoke(obj, Long.parseLong(cVal));
			} else if ("short".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, short.class);
				meth.invoke(obj, Short.parseShort(cVal));
			} else if ("boolean".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, boolean.class);
				meth.invoke(obj, Boolean.parseBoolean(cVal));
			} else if ("char".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, char.class);
				meth.invoke(obj, cVal.charAt(0));
			} else if ("byte".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, byte.class);
				meth.invoke(obj, Byte.parseByte(cVal));
			} else if ("String".equals(typeVal)) {
				meth = cls.getMethod("set_"+cName, String.class);
				meth.invoke(obj, cVal);
			} else {
				throw new RuntimeException("Type should be int, float, double, long, short, boolean, char, byte or String!");
			}
		}
		return obj;
	}
}
