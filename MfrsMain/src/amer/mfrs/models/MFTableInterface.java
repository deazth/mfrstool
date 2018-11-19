/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.mfrs.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amer
 */
public abstract class MFTableInterface extends AbstractTableModel{
  
  protected ArrayList<ArrayList<String>> tableCOntent;
  protected String[] columnNames;
  protected Class[] types;
  
  public void refire() {
    fireTableDataChanged();
  }
  
  public void clearContent() {
    tableCOntent.clear();
    fireTableDataChanged();
  }
  
  public String[] getHeader(){
    return columnNames;
  }
  
  public ArrayList<String> getWholeRow(int row){
    return tableCOntent.get(row);
  }

  public void add(ArrayList<String> data) {

    tableCOntent.add(data);
    fireTableDataChanged();
  }

  @Override
  public void setValueAt(Object value, int row, int column) {
    tableCOntent.get(row).set(column, (String) value);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return tableCOntent.get(rowIndex).get(columnIndex);
  }

  @Override
  public String getColumnName(int col) {
    return columnNames[col];
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public int getRowCount() {
    return tableCOntent.size();
  }

  @Override
  public Class getColumnClass(int columnIndex) {
    return types[columnIndex];
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }
  
}
