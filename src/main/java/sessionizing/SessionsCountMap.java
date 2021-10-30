package sessionizing;

import java.util.HashMap;
import java.util.Map;

public class SessionsCountMap {
    Map<String, Integer> sessionsCountMap;

    public SessionsCountMap(){
        this.sessionsCountMap = new HashMap<>();
    }

    public void addSessionCount(String siteUrl, Integer count){
        if(this.sessionsCountMap.get(siteUrl) == null){
            this.sessionsCountMap.put(siteUrl, count);
        } else{
            this.sessionsCountMap.put(siteUrl, this.sessionsCountMap.get(siteUrl) + count);
        }
    }

    public Map<String, Integer> getSessionMap() {
        return sessionsCountMap;
    }



}
