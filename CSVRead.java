// read csv

import com.opencsv.*;
//import org.apache.commons.lang.*;

import java.io.FileReader;
import java.io.IOException;

import java.util.*;

interface Read{
    public void read(String csvFile) throws Exception;
}

public class CSVRead implements Read{
  private String csvFile;
  private ArrayList<String> contacts;
  private ArrayList<CharSequence> name;

  CSVRead(){
    System.out.println("No file detected");
  }

  CSVRead(String csvFile){
    this.csvFile = csvFile;
    contacts = new ArrayList<String>();
    name = new ArrayList<CharSequence>();
    try{
      read(csvFile);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public void read(String csvFile) throws Exception{

  	CSVReader reader = null;


    try {
            reader = new CSVReader(new FileReader(csvFile)); // takes file reader argument
            String[] line;
            
            while ((line = reader.readNext()) != null) { // will be assigned while checking if its null or no
                //System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
              if (line[2].isEmpty()){ // if no contact it will ignore
                continue;
              }else{
                System.out.println("Company is " + line[0] + " and PIC are " + line[1] + " contact :"+ line[2]);
                contacts.add(line[2]);
                name.add(line[1]);
              }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	System.out.println("End");
        }
  }

  public ArrayList<String> getContacts(){
      return contacts;
  }

   public ArrayList<CharSequence> getName(){
      return name;
  }

  public static void main(String[] args){
   //CSVRead csv = new CSVRead("C:\\Users\\user\\Documents\\ContactList.csv");
   //System.out.println(csv.getName());

  }
    

}
