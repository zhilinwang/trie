package ds.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/***
 * File manipulate facade.
 * @author Chilam
 *
 */
public class FileReaderFacade {
	public static Logger LOG=LoggerFactory.getLogger(FileReaderFacade.class);
	public static InputStreamReader readFile(String path) {		
		InputStreamReader is =null;
		try {
			is=new InputStreamReader(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			LOG.info(e.getMessage());
		}
		return is;
	}

}
