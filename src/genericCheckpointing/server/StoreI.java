package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.WireFormat;

/**
 * interface to write serializable objects to checkpoint file
 */
public interface StoreI extends StoreRestoreI {
	
	/**
	 * write an object into document as wire format to document
	 * @param aRecord
	 * @param wireFormat
	 */
	void writeDJSON(SerializableObject aRecord, WireFormat wireFormat);
}
