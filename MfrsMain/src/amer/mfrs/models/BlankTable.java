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
public class BlankTable extends MFTableInterface{
  public BlankTable(){
    tableCOntent = new ArrayList<ArrayList<String>>();
    columnNames = new String[]{"Output Shall Appear Here"};
    types = new Class[]{java.lang.String.class};
  }
}
