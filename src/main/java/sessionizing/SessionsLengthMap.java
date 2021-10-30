package sessionizing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionsLengthMap {
    Map<String, List<Long>> sessionsLengthMap;

    public SessionsLengthMap(){
        this.sessionsLengthMap = new HashMap<>();
    }

    public void addSessionLength(String siteUrl, Long secondDiffrences){
        if(this.sessionsLengthMap.get(siteUrl) == null){
            this.sessionsLengthMap.put(siteUrl, new ArrayList<>());
        }
        this.sessionsLengthMap.get(siteUrl).add(secondDiffrences);
    }

    public Map<String, List<Long>> getSessionMap() {
        return sessionsLengthMap;
    }
}
