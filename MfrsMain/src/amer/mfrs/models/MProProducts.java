/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.mfrs.models;

import java.util.ArrayList;

/**
 *
 * @author amer
 */
public class MProProducts extends MFTableInterface {

  public MProProducts() {
    
    tableCOntent = new ArrayList<ArrayList<String>>();
    
    columnNames = new String[]{"NAME",
      "POID_ID0",
      "BILL_DESCRIPTION",
      "PRE_CONFIGURED_PRICE",
      "PERMITTED",
      "PRODUCT_TYPE",
      "GLID",
      "GLID_DESCR",
      "EVENT_TYPE",
      "RESOURCE",
      "GL_ACCOUNT",
      "TM_PRODUCT_CODE",
      "TM_RELATIONSHIP_CODE",
      "TM_SERVICE_CLASS_CODE",
      "TM_COMMERCIAL_OFFER",
      "QUANTITY",
      "SERVICE_POID_TYPE"};

    types = new Class[]{
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class
    };
    
    
  }
}
