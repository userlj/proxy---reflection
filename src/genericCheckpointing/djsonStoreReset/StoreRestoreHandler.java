package genericCheckpointing.djsonStoreReset;

import genericCheckpointing.util.CheckTag;
import genericCheckpointing.util.Checker;
import genericCheckpointing.util.FileMode;
import genericCheckpointing.util.ObjStrGenerator;
import genericCheckpointing.util.ParaChecker;
import genericCheckpointing.util.ParserI;
import genericCheckpointing.util.WireFormat;
import genericCheckpointing.util.XmlObjParser;
import genericCheckpointing.util.XmlStrGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * handler to handle invoke method
 */
public class StoreRestoreHandler implements InvocationHandler {
	
	private Writer writer;
	private BufferedReader reader;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		CheckTag chkTag = null;
		String mthdName = method.getName();
		Checker<Object> checker = new ParaChecker<Object>();
		Object retVal = null;
		
		// implementation of method writeDJSON
		if(mthdName.equals("writeDJSON")) {
			
			chkTag = CheckTag.WRITE;
			ObjStrGenerator generator = null;
			try {
					checker.check(args, chkTag);
					WireFormat format = (WireFormat) args[1];
					switch (format) {

					case DJSON:	// not implemented yet!
						break;

					case XML:
						generator = new XmlStrGenerator();	// generate xml format document
						break;

					default:
						break;
				}
					
				String objStr = generator.generate(args[0]);
				writer.write(objStr);	// write string generated to file
				
			} catch (Exception e){
				System.err.println(e.getMessage());
				//e.printStackTrace();
				System.exit(1);
				
			}			
		
		// implementation of method readDJSON
		} else if (mthdName.equals("readDJSON")){
			String lineStr = null;
			StringBuilder sb = new StringBuilder();
			sb.append("<persons>");	// read only one element each time
			
			while ((lineStr = reader.readLine()) != null) {
				if (!lineStr.matches(".*persons.*")) {	// if not root tag
					sb.append(lineStr);
					if (lineStr.contains("</person>")) {	// close element
						break;
					}
				}
			}
			sb.append("</persons>");	// close root
			String objStrRead = sb.toString(); // string read from element in xml
			
			ParserI parser = new XmlObjParser();
			retVal = parser.parser(objStrRead);	// parse xml string to java object
			
		}
		
		return retVal;	// return object
	}
	

	/**
	 * open file with mode(write or read)
	 * @param fileName
	 * @param mode WRITE_MODE or READ_MODE
	 * @throws IOException
	 */
	public void openFile(String fileName, FileMode mode) throws IOException {
		
		switch (mode) {
		case WRITE_MODE:
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("<persons>");
			writer.write(System.getProperty("line.separator"));
			break;
			
		case READ_MODE:
			reader = new BufferedReader(new FileReader(fileName));
			break;

		default:
			break;
		}
	}
	
	/**
	 * read from xml file
	 * @param mode
	 * @throws IOException
	 */
	public void closeFile(FileMode mode) throws IOException {
		switch (mode) {
		case WRITE_MODE:
			if (writer != null) {
				writer.write("</persons>");
				writer.close();
			}
			break;
			
		case READ_MODE:
			if (reader != null) {
				reader.close();
			}
			break;
			
		default:
			break;
		}
	}
}
