package com.validity.takehometest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.junit.Test;



public class FindDuplicatesServiceTest {
	
	@Test
    public void checkLevenshteinDistanceTest() 
    {
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        int result = levenshteinDistance.apply( "mitali", "metali");
        assertEquals(result, 1);
    }
    
    @Test
    public void checkMetaphoneTest() 
    {
        Metaphone metaPhone = new Metaphone();
        boolean result = metaPhone.isMetaphoneEqual("Jacquelyn", "Jacqueline");
        assertTrue(result);
	}
}