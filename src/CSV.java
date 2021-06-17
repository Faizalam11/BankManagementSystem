package finalprojectoop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
interface Icsvwriter{
    String fileExtension = ".csv";
    void writeCSV();
}
interface Icsv{
    void writeCSV(String line);
    String readCSV();
}

public class CSV implements Icsv {
    private final File file;
    CSV(String fileName){
        this.file = new File(fileName);
    }
    CSV(String fileName, String header){
        this.file = new File(fileName);
        try {
            if (file.createNewFile()){
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(header);
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
    }
    public void writeCSV(String line){
        try {
            FileWriter fileWriter = new FileWriter(file,Boolean.TRUE);
            fileWriter.write(line+"\n");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
    }
    public String readCSV(){
        if (file.exists()) {
            String Data = "";
            try {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNext()) {
                    String data = fileReader.nextLine();
                    Data = Data.concat(data);
                }
            } catch (IOException e) {
                System.out.println("An IO error occurred.");
                e.printStackTrace();
            }
            return Data;
        }
        return null;
    }
}
