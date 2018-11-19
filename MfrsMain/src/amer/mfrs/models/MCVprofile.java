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
public class MCVprofile extends MFTableInterface {
  public MCVprofile(){
    tableCOntent = new ArrayList<ArrayList<String>>();
    columnNames = new String[]{"ACCOUNT_NUMBER", "SERVICE_NUMBER", "SERVICE_END_DATE", 
      "FINALIZATION_DATE", "REASON", "LOADING_DATE",
       "LAST_UPDATE_DATE", "ASST_INTEG_ID", "ROW_ID", 
       "PROCESS_IND", "COMPLETED_DATE"};
    types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class, java.lang.String.class,
      java.lang.String.class, java.lang.String.class};
  }
}
