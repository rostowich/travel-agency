package com.adsnet.voyage.repositories;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoExelFileReaderImpl /*implements IDaoExcelFileReader*/{

	/*private static final Logger logger = LoggerFactory.getLogger(DaoExelFileReaderImpl.class);
	
	@Autowired
	private IDepartureHourRepository departureRepository;
	
	@Autowired
	private IPathRepository pathRepository;
	
	@Override
	public List<Voyage> findAll(String excelFileName) throws IOException, ReadExcelFileErrorException {

		List<Voyage> listOfVoyage=new ArrayList<>();
		FileInputStream excelFile=new FileInputStream(new File(excelFileName));
		Workbook workbook=new XSSFWorkbook(excelFile);
		Sheet datatypeSheet=workbook.getSheetAt(0);
		Iterator<Row> iterator=datatypeSheet.iterator();
		int line=1;
		while(iterator.hasNext()){
			Row currentRow=iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
            String departureHour= cellIterator.next().getStringCellValue();
            String path=cellIterator.next().getStringCellValue();
            String numberOfPlace=cellIterator.next().getStringCellValue();
            //Looking for existence of the departure hour
            Optional<DepartureHour> departureHourExist= departureRepository.findByLabel(departureHour);
            if(!departureHourExist.isPresent())
            	throw new ReadExcelFileErrorException("Erreur lors de la lecture à la ligne "+line+": l'heure de depart precise n'existe pas dans le système");
            //looking for the existence of the path
            Optional<Path> pathExist=pathRepository.findById(path);
            if(!pathExist.isPresent())
            	throw new ReadExcelFileErrorException("Erreur lors de la lecture à la ligne "+line+": le trajet precise n'existe pas dans le système");
            if(!Utils.isNumeric(numberOfPlace) && Integer.parseInt(numberOfPlace)<=0)
            	throw new ReadExcelFileErrorException("Erreur lors de la lecture à la ligne "+line+": le nombre de places est invalide");
           
            Voyage voyage=new Voyage();
            voyage.setArchived(new Boolean(false));
            voyage.setNumberOfPlace(Integer.parseInt(numberOfPlace));
            voyage.setDepartureHour(departureHourExist.get());
            voyage.setNumberOfSalePlace(0);
            voyage.setPath(pathExist.get());
            listOfVoyage.add(voyage);
            line++;
            
		}
		
		return listOfVoyage;
	}
	
	*/
}
