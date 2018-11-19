/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author amer
 */
public class ExcelCreator {

  private final Workbook wb;
  private Sheet sheet;
  private int datarow;

  private final File outfile;
  private final String me = "Excel Writer";

  public ExcelCreator(File outfile) {
    wb = new HSSFWorkbook();
    this.outfile = outfile;
    datarow = 1;
  }

  public void addSheet(String name, String[] header) {
    Font headerFont = wb.createFont();
    headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 14);
    headerFont.setColor(IndexedColors.DARK_TEAL.getIndex());
    
    CellStyle headerstyle = wb.createCellStyle();
    headerstyle.setFont(headerFont);
    
    sheet = wb.createSheet(name);
    
    Row headerrow = sheet.createRow(0);
    
    for (int i = 0; i < header.length; i++) {
      Cell c = headerrow.createCell(i);
      c.setCellValue(header[i]);
      c.setCellStyle(headerstyle);
    }
    datarow = 1;
    
    Utilities.log(me, name + " tab created", 3);
    
  }
  
  public void addline(ArrayList<String> data){
    Row line = sheet.createRow(datarow++);
    
    for (int i = 0; i < data.size(); i++) {
      Cell c = line.createCell(i);
      c.setCellValue(data.get(i));
    }
    
  }

  public void flush() {
    try {
      Utilities.log(me, "Writing to " + outfile.getAbsolutePath(), constant.DEBUG);
      FileOutputStream fos = new FileOutputStream(outfile);
      wb.write(fos);
      wb.close();
    } catch (FileNotFoundException e) {
      Utilities.logStack(me, e);
    } catch (IOException ioe) {
      Utilities.popup("Error creating file: " + ioe.toString());
      Utilities.logStack(me, ioe);
    }

  }
  
  public void openOutDir(){
    try {
      Runtime.getRuntime().exec("explorer.exe /select," + outfile.getAbsolutePath());
    } catch (IOException e) {
      Utilities.popup("unable to open output file: " + e.getMessage());
    }
    
  }

}
