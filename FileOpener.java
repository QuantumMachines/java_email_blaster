// JFileChooser

import javax.swing.JFileChooser;
import java.io.File;  

import javax.swing.JDialog; 

public class FileOpener extends JDialog{

	private  JFileChooser fileChooser;
	private  String fileDir = null;

	FileOpener(){

      fileChooser = new JFileChooser();

    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

    	int result = fileChooser.showOpenDialog(this);

    	if (result == JFileChooser.APPROVE_OPTION) {
       	 File selectedFile = fileChooser.getSelectedFile();
       	 System.out.println("Selected file: " + selectedFile.getAbsolutePath());
       	 fileDir = selectedFile.getAbsolutePath();
      	}
    }

	public String getFileName(){

       return fileDir;
    }

    public static void main(String[] args){
    	FileOpener fp = new FileOpener();
    	
    }

}