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
public class MOEai extends MFTableInterface {

  public MOEai() {
    tableCOntent = new ArrayList<ArrayList<String>>();
    columnNames = new String[]{"Order No", "Asset Integ ID", "Product Type", "Billing No", "Relationship Code", "Currency",
       "Part No", "Order Type", "Action Code", "Product", "Completed Date", "BRM Purchased POID", "BRM Product POID",
       "SUB Prod Type"};
    types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class};
  }
}
