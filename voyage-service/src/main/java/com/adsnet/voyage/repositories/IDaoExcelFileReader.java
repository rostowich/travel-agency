package com.adsnet.voyage.repositories;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.exceptions.ReadExcelFileErrorException;


/**
 * This interface is used to group all the useful methods to access and read data into Excel files
 * @author Rostow GOKENG
 *
 */
@Component
public interface IDaoExcelFileReader {

	/**
	 * This function reads a excel file and return all the voyages into it
	 * @param excelFileName
	 * @return
	 * @throws IOException
	 */
	public List<Voyage> findAll(String excelFileName) throws IOException, ReadExcelFileErrorException;

}
