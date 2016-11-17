
public abstract class Submission implements Statable, java.io.Serializable {

   protected String author;
   protected String title;
   
   public Submission(String author, String title) throws SubmissionException {
       if (author == null || author.length() == 0 || title == null || title.length() == 0) {
           throw new SubmissionException("Invalid author/title");
       }
       this.author = author;
       this.title = title;
   }
   
   public String toString() {
       return "Author: " + author + "\r\n" + "Title: " + title;
   }
   
   public String getState() {
       return author + "," + title;
   }
   
   public String getAuthor() {
       return author;
   }
   
   public String getTitle() {
       return title;
   }
   
   public String getType() {
       return getClass().toString();
   }
   
   public boolean equals(Object obj) {
       if (obj instanceof Submission) {
           return equals((Submission) obj);
       }
       else {
           return false;
       }
   }
   
   public boolean equals(Submission sub) {
       return getState().equalsIgnoreCase(sub.getState());
   }
}
