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
public class MCSService extends MFTableInterface {

  public MCSService() {
    tableCOntent = new ArrayList<ArrayList<String>>();
    columnNames = new String[]{"ACCOUNT_NO", "SERVICE_POID",
      "SERVICE_NO", "SEGMENT_CODE", "INDUSTRY_CODE", "PTT", "EXCHANGE_ID", "COST_CENTER"};
    types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class,
       java.lang.String.class, java.lang.String.class, java.lang.String.class,
       java.lang.String.class, java.lang.String.class};
  }
}
