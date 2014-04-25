package genericCheckpointing.util;


/**
 * interface to parse wire format to objects
 */
public interface ParserI {
	
	/**
	 * parse string of wire format to object
	 * @param objStr
	 * @return
	 */
	SerializableObject parser(String objStr);
}
