public class Paper extends Submission {
    private String subjectArea;
    private String url;
    private int numPages;
    public static final int MAX_PAGES = 10;
    
    public Paper(String author, String title, String subjectArea, String url, int numPages) throws SubmissionException {
        super(author, title);
        if (subjectArea == null || subjectArea.length() == 0 || url == null || url.length() == 0 || !url.startsWith("http://")) {
            throw new SubmissionException("Problem with paper subject or url");
        }
        if (numPages < (MAX_PAGES + 1) / 2 || numPages > MAX_PAGES) {
            throw new SubmissionException("Problem with paper # of pages");
        }
        this.subjectArea = subjectArea;
        this.url = url;
        this.numPages = numPages;
    }
    
    public String getSubjectArea() {
        return subjectArea;
    }
    
    public String getURL() {
        return url;
    }
    
    public int getNumPages() {
        return numPages;
    }
    
    public String getState() {
        return super.getState() + "," + subjectArea + "," + url + "," + numPages;
    }
    
    public String toString() {
        return "Paper:\r\n" + super.toString() + "\r\nSubject: " + subjectArea + "\r\nURL: " + url +
               "\r\n# Pages: " + numPages;
    }
}