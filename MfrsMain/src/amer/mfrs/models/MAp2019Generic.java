/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.mfrs.models;

import amer.common.dbHandler;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JackS
 */
public class MAp2019Generic extends MFTableInterface {

  private final int colcount;
  private boolean isRunningState;

  public MAp2019Generic(ResultSetMetaData rsmd) throws SQLException {

    // build the headers
    colcount = rsmd.getColumnCount();

    tableCOntent = new ArrayList<ArrayList<String>>();
    columnNames = new String[colcount];
    types = new Class[colcount]; //{java.lang.String.class

    for (int i = 0; i < colcount; i++) {
      columnNames[i] = rsmd.getColumnLabel(i + 1);
      types[i] = java.lang.String.class;
    }
  }
  
  public void stopLoad(){
    isRunningState = false;
  }

  public void loadData(ResultSet rs) throws SQLException {

    isRunningState = true;
    int counter = 0;
    while (rs.next()) {
      counter++;
      ArrayList<String> data = new ArrayList<>();
      if(!isRunningState){
        break;
      }

      for (int i = 1; i <= colcount; i++) {
        data.add(dbHandler.dbGetString(rs, i));
      }

      add(data);
      
      if(counter % 30 == 0){
        refire();
      }
      
    }

  }
}
