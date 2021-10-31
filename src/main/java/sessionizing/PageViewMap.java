package sessionizing;

import java.util.*;

public class PageViewMap {
    Map<String,Map<String,List<PageView>>> pagesPerVisitorperSiteperSite;
    Map<String,Set<String>> uniqueSitesPerVisitorMap;

    public PageViewMap(){
        this.pagesPerVisitorperSiteperSite = new HashMap<>();
        this.uniqueSitesPerVisitorMap = new HashMap<>();
    }

    public void addPage(PageView page){
        System.out.println(page.getVisitorId());
        if(this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()) == null) {
            this.pagesPerVisitorperSiteperSite.put(page.getVisitorId(), new HashMap<>());
        }

        if(this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()) != null &&
                this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()).get(page.getSiteUrl()) == null){
            this.pagesPerVisitorperSiteperSite.get(page.getVisitorId()).put(page.getSiteUrl(), new ArrayList<>());

            this.uniqueSitesPerVisitorMap.put(page.getVisitorId(), new HashSet<>());
        }

        this.uniqueSitesPerVisitorMap.get(page.getVisitorId()).add(page.getSiteUrl());
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

    public String getUniqueSitePerVisitorId(String visitorId){
        return String.valueOf(this.uniqueSitesPerVisitorMap.get(visitorId).size());
    }
}
