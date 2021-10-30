package sessionizing;

import java.util.*;

public class PageViewMap {
    Map<String,Map<String,List<PageView>>> pagesPerVisitorperSiteperSite;

    public PageViewMap(){
        this.pagesPerVisitorperSiteperSite = new HashMap<>();
    }

    public void addPage(PageView page){
        System.out.println(page.getVisitorId());
        if(this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()) == null) {
//                this.pagesPerVisitorperSiteperSite.put(page.getVisitorId() , new ArrayList<>());
            this.pagesPerVisitorperSiteperSite.put(page.getVisitorId(), new HashMap<>());
        }
        if(this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()) != null &&
                this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()).get(page.getSiteUrl()) == null){

            this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()).put(page.getSiteUrl(), new ArrayList<>());
        }
        this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()).get(page.getSiteUrl()).add(page);
    }

    public Map<String, Map<String, List<PageView>>> getPages() {
        return pagesPerVisitorperSiteperSite;
    }

    public void sortMapByTimestamp(){
        List<PageView> pages;
        String visitorId;
        Set<Map.Entry<String, List<PageView>>> siteUrlMap;

        for(Map.Entry<String, Map<String, List<PageView>>> visitorEntry: this.pagesPerVisitorperSiteperSite.entrySet()){
            visitorId = visitorEntry.getKey();
            siteUrlMap = visitorEntry.getValue().entrySet();

            for(Map.Entry<String, List<PageView>> siteEntry: siteUrlMap){
                pages = siteEntry.getValue();
                pages.sort(Comparator.comparing(PageView::getDate).reversed());
            }
        }
    }
}
