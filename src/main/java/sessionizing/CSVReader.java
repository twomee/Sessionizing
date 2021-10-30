package sessionizing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CSVReader {
    private final String CSV_FILES_PATH = "/home/ido/Desktop/JavaHomeTest/";
    private final String SUFFIX = ".csv";


    public Date convertTimestampToDate(String timestamp){
        Timestamp stamp = new Timestamp(Long.valueOf(timestamp));
        Date date = new Date(stamp.getTime());
        return date;
    }

    public PageViewMap read() {
        String line = "";
        String splitBy = ",";
        File folder = new File(CSV_FILES_PATH);
        File[] listOfFiles = folder.listFiles();
        PageViewMap pageViewMap = new PageViewMap();
        PageView pageView;
        try {

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getName().toLowerCase().endsWith(SUFFIX)) {
                    BufferedReader br = new BufferedReader(new FileReader(listOfFiles[i]));
                    while ((line = br.readLine()) != null)
                    {
                        String[] page = line.split(splitBy);
                        pageView = new PageView(page[0], page[1], page[2], convertTimestampToDate(page[3]));
                        pageViewMap.addPage(pageView);
                        System.out.println("sessionizing.PageView [visitor id=" + page[0] + ", site url=" + page[1] + ", page view url=" + page[2] + ", timestamp=" + page[3]);
                    }
                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }

        return pageViewMap;
    }
}
