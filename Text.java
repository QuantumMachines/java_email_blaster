// search String

import java.*;
import java.util.*;


import javafx.util.Pair; 

public class Text{
  private final CharSequence target;
  private final String subtarget = "$*$";
  private String replaceTarget; // replace with name on csv
  private boolean found;
  private int startPos;
  private Pair positions;  // use it for finding particular text
  private String textReplaced;

  Text(){
  	target = "$*$";
  	found = false;
  	startPos = 0;
  }
  
  public boolean searchPattern(String textMsg){

 	  	if(textMsg.contains(target)){
    		startPos = textMsg.indexOf(subtarget);
    		System.out.println("Position at "+ startPos + " and " + (startPos+2));
        	//return new Pair<Integer, Integer>(startPos, startPos+2);
        	return true;
    	
    	}else{
    		System.out.println("Unable to find!, Please insert mark to use this feature.");
    		//return new Pair<Integer, Integer>(null , null);
    		return false;
    	}
	

  }

  public String replacePattern(CharSequence replaceWith , String textMsg , boolean present){
        if(present == true){
	    	textReplaced = textMsg.replace( "$*$" , replaceWith);
  	     	return textReplaced;
  	     }else{
  	     	System.out.println("Unable to replace.");
  	     	return textMsg;
  	     }
  }

	
  public static void main (String [] args){
          Text t = new Text();
          String example = "change fbhgdzges $*$    iofhsdfkhbkjfkjwfvhifwejfk$*$  , rghrokgbrhjgkjwfgjsgjb   $*$";
          boolean flag = t.searchPattern(example);
          example = t.replacePattern("yea" , example, flag );

          System.out.println(example);


  }


}
