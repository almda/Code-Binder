package Handlers;
import java.io.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class FileHandler {
    String path;

    public FileHandler(String path){
        this.path=path;
    }

    public void CopyFile(String codePath) throws FileNotFoundException {
        File file = new File(codePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st="";
        try {
            while ((st = br.readLine()) != null)
                PutFile(st);
        }
        catch (IOException ex){ showMessageDialog(null, "Error");}
    }


    private void PutFile(String content){
        try{
            File file = new File(path);
            write(content,file);
        }
        catch (IOException ex){showMessageDialog(null, "Error");;}
    }

    private void write (String s,File f) throws IOException{
        FileWriter fw = new FileWriter(f,true);
        fw.write(System.getProperty( "line.separator" ));
        fw.write(s);
        fw.close();
    }

    public void CreateCode(String codePath, String codeName, String codeBody){
        File file = new File(codePath+"\\"+codeName+".java");
        if (file.exists())
            showMessageDialog(null, "File Already Exists");
        else{
            try{
                if(file.createNewFile()) {
                    writeToFile(codeBody, file);
                    }
            }
            catch (IOException ex){showMessageDialog(null, "Error");;}
        }//end else
    }//end CreateCode()

    private void writeToFile(String codeBody, File file) throws IOException{
        try (
                BufferedReader reader = new BufferedReader(new StringReader(codeBody));
                PrintWriter writer = new PrintWriter(new FileWriter(file));
        ) {
            reader.lines().forEach(writer::println);
        }
    }

    public boolean deleteFile(){ //path is codePath if this func is activated
        File file = new File(path);
        return file.delete();
    }

    public boolean createDirectories(){
        File files = new File(path+"\\CodeBinderFiles");
        if (files.exists()) return false;

        for(int i=1;i<13;i++)
            for(int j=1;j<7;j++) {
                files = new File(path + "\\CodeBinderFiles\\Subject" + i + "\\" + j);
                if (!files.exists()) files.mkdirs();
            }
        return true;
    }

}
