
package genericCheckpointing.driver;

import genericCheckpointing.djsonStoreReset.StoreRestoreHandler;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.CheckTag;
import genericCheckpointing.util.Checker;
import genericCheckpointing.util.EmployeeRecord;
import genericCheckpointing.util.FileMode;
import genericCheckpointing.util.ParaChecker;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.StudentRecord;
import genericCheckpointing.util.WireFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * driver class
 */
public class Driver {
    
    public static void main(String[] args) {
	
    	Checker<String> checker = new ParaChecker<String>();
    	CheckTag chkTag = null;
    	try {
    		chkTag = CheckTag.DRIVER;
    		checker.check(args, chkTag);
    	
	    	// read the value of NUM_OF_OBJECTS from the command line
	    	int NUM_OF_OBJECTS = Integer.parseInt(args[0]);
	    	// read the value of checkpointFile from the command line
	    	String fileName = args[1];
		
	    	ProxyCreator pc = new ProxyCreator();
			
			// create an instance of StoreRestoreHandler (which implements
			// the InvocationHandler
			StoreRestoreHandler handler = new StoreRestoreHandler();
	    	
			
			// create a proxy
			StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
										 new Class[] {
										     StoreI.class, RestoreI.class
										 }, 
										 handler
										 );
				
			// invoke a method on the DJSONHandler instance to set the file name for checkpointFile and open the file
			handler.openFile(fileName,FileMode.WRITE_MODE);
	
			SerializableObject myErecord;
			SerializableObject mySrecord;
			
			ArrayList<SerializableObject> originalPersons = new ArrayList<SerializableObject>();
			
			for (int i=0; i<NUM_OF_OBJECTS; i++) {
			    
				myErecord = new EmployeeRecord(i, (float)i, (double)i, (long)i);
			    if (i % 2 == 0) {
			    	mySrecord = new StudentRecord(i, (short)i, true);
			    } else {
			    	mySrecord = new StudentRecord(i, (short)i, false);
			    }
			    
			    // store myErecord in a data structure
			    originalPersons.add(myErecord);
			    
			    // store mySrecord in a data structure
			    originalPersons.add(mySrecord);
		
			    ((StoreI) cpointRef).writeDJSON(myErecord, WireFormat.XML);
			    ((StoreI) cpointRef).writeDJSON(mySrecord, WireFormat.XML);
		
			}
			
			handler.closeFile(FileMode.WRITE_MODE);	// Done writing to document
		
			
			handler.openFile(fileName, FileMode.READ_MODE);	// start reading from document
	
			List<SerializableObject> reflectedPersons = new ArrayList<SerializableObject>();
			SerializableObject myRecordRet = null;
		
			for (int j=0; j < 2*NUM_OF_OBJECTS; j++) {
		
			    myRecordRet = ((RestoreI) cpointRef).readDJSON(WireFormat.XML);
			    // store myRecordRet in a data structure
			    reflectedPersons.add(myRecordRet);
			}
		
			// invoke a method on the DJSONHandler instance to close the file
			handler.closeFile(FileMode.READ_MODE);
			
			// compare and confirm that the serialized and deserialzed objects are equal
			SerializableObject originalPerson = null;
			SerializableObject reflectedPerson = null; 
			for (int k=0; k < 2*NUM_OF_OBJECTS; k++) {
				originalPerson = originalPersons.get(k);
				reflectedPerson = reflectedPersons.get(k);
				
				if (originalPerson instanceof EmployeeRecord) {
					if (((EmployeeRecord) originalPerson).equals((EmployeeRecord) reflectedPerson)) {
						System.out.println("The number "+k+" records(employees) are equal.");
					} else {
						System.out.println("The number "+k+" records(employees) are NOT equal.");
					}

				} else if (originalPerson instanceof StudentRecord) {
					if (((StudentRecord) originalPerson).equals((StudentRecord) reflectedPerson)) {
						System.out.println("The number "+k+" records(students) are equal.");
					} else {
						System.out.println("The number "+k+" records(students) are NOT equal.");
					}
				}
			}
		    
    	} catch (Exception e){
    		System.err.println(e.getMessage());
    		//e.printStackTrace();
    		System.exit(1);
    	}
    }
}