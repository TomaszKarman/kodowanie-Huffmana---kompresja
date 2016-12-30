

import java.io.BufferedWriter;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

public class MyFileWriter {

    //Object to write to log file

    private BufferedWriter printWriter;


    public void close() {
        try {
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyFileWriter(String logFilePath) {


        try {

            //Create file writer to write logs throught it

            createFileWriter(logFilePath, false);


        } catch (IOException ex) {
            ex.printStackTrace();
            closeApplication();

        }

    }


    /**
     * Close application with error
     */

    private void closeApplication() {

        //Close printWriter

        try {

            printWriter.close();

        } catch (IOException exc) {

            exc.printStackTrace();

        }

        System.exit(-1);

    }


    /**
     * Open log file to write.
     *
     * @param pathToFile Path to log file
     * @param appendFile Do logs append file. If false then rewrite file.
     */

    private void createFileWriter(String pathToFile, Boolean appendFile) throws IOException {

        File logFile = new File(pathToFile);

        //Check if file exists
        if (!logFile.exists()) {

            //File not exists so create it

            logFile.createNewFile();

        }


        //Open File Writer
        FileWriter fw = new FileWriter(logFile, appendFile);

        printWriter = new BufferedWriter(fw);


    }


    /**
     * Write log to file
     *
     * @param logTxt logTXT
     */

    public void addStringToFile(String logTxt) {
        try {
            printWriter.write(logTxt);
            printWriter.flush();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}