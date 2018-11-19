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
public class MCVVai extends MFTableInterface {

  public MCVVai() {
    tableCOntent = new ArrayList<ArrayList<String>>();
    columnNames = new String[]{"ASSET_INTEG_ID", "PREV_ASSET_INTEG_ID", "VIRTUAL_ASSET_INTEG_ID", 
      "ORDER_CREATED", "MOD_DATE", "ROW_ID"};
    types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class
    };
  }
  
}
