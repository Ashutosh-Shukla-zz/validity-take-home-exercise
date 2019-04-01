package com.validity.takehometest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.validity.takehometest.model.Person;
import com.validity.takehometest.utils.CsvFileParseUtil;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FindDuplicatesService 
{
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

        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        Metaphone metaPhone = new Metaphone();

        ArrayList<Person> mockList = new ArrayList<Person>();
        ArrayList<Person> uniqueList = new ArrayList<Person>();
        Map< Person , List<Person>> duplicateMap = new HashMap< Person, List<Person>>();

        for (int i = 0; i < csvPersonList.size(); i++) 
        {
            if (mockList.contains(csvPersonList.get(i)))
                continue;

            ArrayList<Person> tempList = new ArrayList<Person>();
            for (int j = i+1; j < csvPersonList.size(); j++) 
            {
                
               if ((levenshteinDistance.apply(csvPersonList.get(i).getEmail(), csvPersonList.get(j).getEmail()) <2) || 
                        (levenshteinDistance.apply(csvPersonList.get(i).getPhone(), csvPersonList.get(j).getPhone()) <2))
               {
                   if (  (metaPhone.isMetaphoneEqual(csvPersonList.get(i).getFirst_name(), csvPersonList.get(j).getFirst_name())) ||  
                         (metaPhone.isMetaphoneEqual(csvPersonList.get(i).getLast_name(), csvPersonList.get(j).getLast_name())) )
                   {
                        //POSSIBLE DUPLICATE
                        tempList.add(csvPersonList.get(j));

                        mockList.add(csvPersonList.get(j));
                   }
               }
            }
            if (tempList.size()!=0)
                duplicateMap.put(csvPersonList.get(i), tempList);
            else
                uniqueList.add(csvPersonList.get(i));
          }


        //   -----creating list of arraylist to return-------
        totalList = getListFromMap(duplicateMap);
        
        totalList.add(uniqueList);
        logger.debug("totalList size::"+totalList.size());


        return totalList;
        
    }

    public List< List<Person>> getListFromMap (Map< Person , List<Person>> inputMap)
    {
        logger.info("Getting separate lists from map");
        List< List<Person> > resultList = new ArrayList< List<Person>>();

        for (Map.Entry<Person, List<Person>> entry : inputMap.entrySet()) 
        {
            List<Person> possibleDuplicateList  = new ArrayList<Person>();
            possibleDuplicateList.add(entry.getKey());
            List<Person> temp  = entry.getValue();
            for (Person per : temp)
                possibleDuplicateList.add(per);

            resultList.add(possibleDuplicateList);
        }
        return resultList;
    }

}