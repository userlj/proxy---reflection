package genericCheckpointing.util;

/**
 * interface for serializing objects
 */
public interface ObjStrGenerator {
	
	/**
	 * generate string for different wire format
	 * @param obj
	 * @return
	 */
	String generate(Object obj);
}
