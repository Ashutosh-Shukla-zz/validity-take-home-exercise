package com.validity.takehometest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.validity.takehometest.model.Person;
import com.validity.takehometest.utils.CsvFileParseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FindDuplicatesService {
    @Autowired
    CsvFileParseUtil csvFileParseUtil;

    private static final Logger logger = LoggerFactory.getLogger(FindDuplicatesService.class);

    public List<List<Person>> processDataFromCSV(MultipartFile multipartFile) throws IOException
    {
        List< List<Person> > totalList = new ArrayList< List<Person>>();

        logger.info("Parsing csv data into a list");
        List<Person> csvPersonList = new ArrayList<Person>();
        csvPersonList =csvFileParseUtil.read(Person.class, multipartFile.getInputStream());
        logger.debug("Number of rows read from CSV::"+csvPersonList.size());

        //Yet to implement business logic
        



        return totalList;
        
    }

}