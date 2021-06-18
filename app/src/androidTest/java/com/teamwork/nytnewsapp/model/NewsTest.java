package com.teamwork.nytnewsapp.model;

import junit.framework.TestCase;

public class NewsTest extends TestCase {

//NB: Ran outof time to make a meaningful test

    public void testGetResults() {


        News  c=new News();
        c.setCopyright("Copyright of New York Times");
        c.setStatus("New status");

        //Uncomment the next line to fail test
        //   assertEquals("Hello",c.getStatus());

        //Uncomment the next line to pass test
        //  assertEquals("New status",c.getStatus());
    }

    public void testSetResults() {
    }
}