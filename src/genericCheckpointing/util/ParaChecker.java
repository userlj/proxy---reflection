package genericCheckpointing.util;


public class ParaChecker<T> implements Checker<T> {

	@Override
	public void check(T[] objArr, CheckTag chkTag) {
		
		switch (chkTag) {
		case DRIVER:
			if (objArr.length != 2) {
				throw new IllegalArgumentException("Two arguments required! Yours is " + objArr.length);
			}

			if (objArr[0] == null || objArr[1] == null) {
				throw new IllegalArgumentException("Arguments are not null!");
			}
			
			if (objArr[0] instanceof String) {
				if (!((String)objArr[0]).matches("^\\d+$")) {
					throw new IllegalArgumentException("First argument is non-negative integer! Yours is " + objArr[0]);
				}
			}
			
			break;
			
			
		case WRITE:
			
			if (objArr.length != 2) {
				throw new IllegalArgumentException("Two arguments required! Yours is " + objArr.length);
			}
			
			if (objArr[0] == null || objArr[1] == null) {
				throw new IllegalArgumentException("Arguments are not null!");
			}
			
			if (!(objArr[1] instanceof WireFormat)) {
				throw new IllegalArgumentException("Format is illegal! Should be DJSON or XML");
			}
			break;
			
			
		case READ:
			
			if (objArr.length != 1) {
				throw new IllegalArgumentException("One arguments required! Yours is " + objArr.length);
			}
			
			if (objArr[0] == null) {
				throw new IllegalArgumentException("Argument is not null!");
			}
			
			if (!(objArr[0] instanceof WireFormat)) {
				throw new IllegalArgumentException("Format is illegal! Should be DJSON or XML");
			}
			break;
			
			
		default:
			break;
		}
		
		

	}

}
