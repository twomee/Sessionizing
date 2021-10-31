package sessionizing;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionizingController {

    @RequestMapping(value = "/num_sessions",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> numberOfSession(HttpEntity<String> httpEntity){
        SessionManager sessionManager = new SessionManager();
        try {
            String stringData = httpEntity.getBody();
            JSONObject jsonData = null;
            jsonData = new JSONObject(stringData);
            String siteUrl = jsonData.getString("siteUrl");
            System.out.println(siteUrl);
            System.out.println(sessionManager.getSessionsCountPerSite(siteUrl));
            return ResponseEntity.ok(JSONObject.quote(sessionManager.getSessionsCountPerSite(siteUrl)));
        } catch (JSONException e) {

            e.printStackTrace();
            return ResponseEntity.ok(JSONObject.quote(e.getMessage()));
        }
    }

    @RequestMapping(value = "/median_session_length",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> MedianSessionLength(HttpEntity<String> httpEntity){
        SessionManager sessionManager = new SessionManager();
        try {
            String stringData = httpEntity.getBody();
            JSONObject jsonData = null;
            jsonData = new JSONObject(stringData);
            String siteUrl = jsonData.getString("siteUrl");
            System.out.println(siteUrl);
            System.out.println(sessionManager.getSessionsCountPerSite(siteUrl));
            return ResponseEntity.ok(JSONObject.quote(sessionManager.getMedianSessionsLengthPerSite(siteUrl)));
        } catch (JSONException e) {

            e.printStackTrace();
            return ResponseEntity.ok(JSONObject.quote(e.getMessage()));
        }
    }

    @RequestMapping(value = "/num_unique_visited_sites",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> UniqueVisits(HttpEntity<String> httpEntity){
        SessionManager sessionManager = new SessionManager();
        try {
            String stringData = httpEntity.getBody();
            JSONObject jsonData = null;
            jsonData = new JSONObject(stringData);
            String visitorId = jsonData.getString("visitorId");
            System.out.println(visitorId);
            System.out.println(sessionManager.getNumberOfUniqueVisited(visitorId));
            return ResponseEntity.ok(JSONObject.quote(sessionManager.getNumberOfUniqueVisited(visitorId)));
        } catch (JSONException e) {
            e.printStackTrace();
            return ResponseEntity.ok(JSONObject.quote(e.getMessage()));
        }
    }
}
