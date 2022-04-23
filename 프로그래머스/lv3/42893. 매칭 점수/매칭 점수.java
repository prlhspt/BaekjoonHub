import java.util.*;
import java.util.regex.*;

/*
기본 점수 : 검색된 키워드 개수
외부 링크 : 참조한 링크 개수
링크 점수 : 나를 참조하고 있는 링크의 (기본점수/외부링크수)

계산 = 기본 점수 + 링크 점수
*/

class Solution {

    class PageInfo {
        private int index;
        private String url;
        private double defaultScore;
        private double linkScore;
        private List<String> externalLink;

        public PageInfo(int index, String url) {
            this.index = index;
            this.url = url;
        }

        public int getIndex() {
            return index;
        }

        public String getUrl() {
            return url;
        }

        public void computeScore(double defaultScore) {
            computeLinkScore(defaultScore);
            this.defaultScore = defaultScore;
        }

        private void computeLinkScore(double score) {
            this.linkScore = score / externalLink.size();
        }

        public double getDefaultScore() {
            return defaultScore;
        }

        public double getLinkScore() {
            return linkScore;
        }

        public List<String> getExternalLink() {
            return externalLink;
        }

        public void setExternalLink(List<String> externalLink) {
            this.externalLink = externalLink;
        }

    }

    public String findURL(String page) {
        String url = "";
        Matcher homeMatcher = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"")
                .matcher(page);
        while (homeMatcher.find()) {
            url = homeMatcher.group(1);
        }
        return url;
    }

    public List<String> findReferenceURL(String page) {
        ArrayList<String> referenceUrl = new ArrayList<>();

        Matcher referenceMatcher = Pattern.compile("<a href=\"https://(\\S*)\"").matcher(page);
        while (referenceMatcher.find()) {
            referenceUrl.add(referenceMatcher.group(1));
        }

        return referenceUrl;
    }

    public int findWordCount(String page, String word) {
        String body = page.split("<body>")[1].split("</body>")[0].replaceAll("\\d", " ");
        Matcher bodyMatcher = Pattern.compile("\\b(?i)" + word + "\\b").matcher(body);
        int wordCount = 0;
        while (bodyMatcher.find()) {
            wordCount++;
        }
        return wordCount;
    }
    
    public void computeScore(Map<String, PageInfo> pageInfoMap) {
        for(PageInfo pageInfo : pageInfoMap.values()) {
            for (String externalLink : pageInfo.getExternalLink()) {
                if (pageInfoMap.containsKey(externalLink)) {
                    pageInfoMap.get(externalLink).defaultScore += pageInfo.getLinkScore();
                }
            }
        }
    }
    
    public int computeIndex(Map<String, PageInfo> pageInfoMap) {
        double maxScore = 0;
        int answer = 0;
        for (PageInfo pageInfo : pageInfoMap.values()) {
            if (pageInfo.getDefaultScore() == maxScore && pageInfo.getIndex() < answer) {
                answer = pageInfo.index;
            } else if (pageInfo.defaultScore > maxScore) {
                answer = pageInfo.index;
                maxScore = pageInfo.getDefaultScore();
            }
        }
        return answer;
    }

    public int solution(String word, String[] pages) {
        int answer = 0;

        word = word.toLowerCase();

        HashMap<String, PageInfo> pageInfoMap = new HashMap<>();


        for (int i = 0; i < pages.length; i++) {
            pages[i] = pages[i].toLowerCase();

            String url = findURL(pages[i]);
            PageInfo pageInfo = new PageInfo(i, url);

            List<String> referenceURL = findReferenceURL(pages[i]);
            pageInfo.setExternalLink(referenceURL);

            int wordCount = findWordCount(pages[i], word);
            pageInfo.computeScore(wordCount);

            pageInfoMap.put(pageInfo.getUrl(), pageInfo);
        }
        
        computeScore(pageInfoMap);

        answer = computeIndex(pageInfoMap);

        return answer;
    }
}