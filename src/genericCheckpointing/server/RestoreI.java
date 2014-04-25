package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.WireFormat;

/**
 * interface to read from checkpoint file
 */
public interface RestoreI extends StoreRestoreI {
	
	/**
	 * get object by reading from document of a wire format
	 * @param wireFormat
	 * @return a serializable object
	 */
	SerializableObject readDJSON(WireFormat wireFormat);
}
