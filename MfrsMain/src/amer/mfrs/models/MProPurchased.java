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
public class MProPurchased extends MFTableInterface {

//  boolean[] canEdit = new boolean [] {
//    false, false, false, false, false
//  };
  public MProPurchased() {
    tableCOntent = new ArrayList<ArrayList<String>>();

    columnNames = new String[]{"PURCHASED_POID",
      "PRODUCT_POID",
      "PRODUCT_DESCR",
      "CREATED_T",
      "PURCHASE_START_T",
      "PURCHASE_END_T",
      "STATUS"};

    types = new Class[]{
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class
    };
  }

}
