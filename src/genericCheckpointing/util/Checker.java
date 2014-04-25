package genericCheckpointing.util;

/**
 * interface to check input validation
 */
public interface Checker<T> {
	/**
	 * check validation of argument array
	 * @param arr
	 * @param chkTag
	 */
	void check(T[] arr, CheckTag chkTag);
}
