package sessionizing;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        PageViewMap pageViewMap = csvReader.read();

        pageViewMap.sortMapByTimestamp();

        List<PageView> pages;
        String siteUrl;
        String visitorId;
        Set<Map.Entry<String, List<PageView>>> siteUrlMap;
        Instant dbInstant;
        SessionsCountMap sessionsCounthMap = new SessionsCountMap();
        SessionsLengthMap sessionsLengthMap = new SessionsLengthMap();
        Integer count = 0;
        Date firstDate = null;
        Date lastDate = null;
        for (Map.Entry<String, Map<String, List<PageView>>> visitorEntry : pageViewMap.getPages().entrySet()) {
            visitorId = visitorEntry.getKey();
            siteUrlMap = visitorEntry.getValue().entrySet();
            System.out.println(visitorId);
            for (Map.Entry<String, List<PageView>> siteEntry : siteUrlMap) {
                siteUrl = siteEntry.getKey();
                System.out.println(siteUrl);
                pages = siteEntry.getValue();
                for (PageView page : pages) {
                    if(count == 0){
                        lastDate = page.getDate();
                    }else if(count == pages.size() - 1){
                        firstDate = page.getDate();
                    }
                    System.out.println(page.getDate());
                    dbInstant = page.getDate().toInstant();
                    Instant aHalfHourAgo = ZonedDateTime.now().minusMinutes(30).toInstant();
                    if(dbInstant.isAfter(aHalfHourAgo)){
                        break;
                    }
                    count++;
                }
                if(pages.size() == count){
                    Long secondsDifferences = Math.abs(lastDate.getTime()-firstDate.getTime())/1000;
                    sessionsCounthMap.addSessionCount(siteUrl, count);
                    sessionsLengthMap.addSessionLength(siteUrl, secondsDifferences);
                }

            }
            count = 0;
        }

//        for(Map.Entry<String, Integer> session: sessionsCounthMap.getSessionMap().entrySet()){
//            System.out.println(session.getKey());
//            System.out.println(session.getValue());
//        }
//
//        for(Map.Entry<String, Long> session: sessionsLengthMap.getSessionMap().entrySet()){
//            System.out.println(session.getKey());
//            System.out.println(session.getValue());
//        }
    }
}
