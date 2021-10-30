package sessionizing;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SessionManager {
    SessionsCountMap sessionsCounthMap;
    SessionsLengthMap sessionsLengthMap;

    public SessionManager() {
        sessionsCounthMap = new SessionsCountMap();
        sessionsLengthMap = new SessionsLengthMap();
        this.createSessionsMapObjects();
    }

    public void createSessionsMapObjects(){
        CSVReader csvReader = new CSVReader();
        PageViewMap pageViewMap = csvReader.read();

        pageViewMap.sortMapByTimestamp();

        List<PageView> pages;
        String siteUrl;
        String visitorId;
        Set<Map.Entry<String, List<PageView>>> siteUrlMap;
        Instant dbInstant;

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
    }

    public String getSessionsCountPerSite(String siteUrl) {
        if(sessionsCounthMap.getSessionMap().get(siteUrl) == null){
            return "0";
        }
        return String.valueOf(sessionsCounthMap.getSessionMap().get(siteUrl));
    }

    public String getMedianSessionsLengthPerSite(String siteUrl) {
        int siteListLength = sessionsLengthMap.getSessionMap().get(siteUrl).size();
        if(sessionsLengthMap.getSessionMap().get(siteUrl) == null){
            return "0";
        }
        return String.valueOf(sessionsLengthMap.getSessionMap().get(siteUrl).get(siteListLength/2)+
                sessionsLengthMap.getSessionMap().get(siteUrl).get((siteListLength/2) + 1));
    }
}
