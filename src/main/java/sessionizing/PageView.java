package sessionizing;

import java.util.Date;

public class PageView {

    String visitorId;
    String siteUrl;
    String pageViewUrl;
    Date date;

    public PageView(String visitorId, String siteUrl, String pageViewUrl, Date date) {
        this.visitorId = visitorId;
        this.siteUrl = siteUrl;
        this.pageViewUrl = pageViewUrl;
        this.date = date;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getPageViewUrl() {
        return pageViewUrl;
    }

    public Date getDate() {
        return date;
    }

}
