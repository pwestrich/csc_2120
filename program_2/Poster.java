public class Poster extends Submission {
   private String description;
   public final int MAX_LENGTH = 2000;
   
   public Poster(String author, String title, String description) throws SubmissionException {
       super(author, title);
       if (description == null || description.length() == 0 || description.length() > MAX_LENGTH) {
           throw new SubmissionException("Problem with poster description");
       }
       this.description = description;
   }
   
   public String getDescription() {
       return description;
   }
   
   public String getState() {
       return super.getState() + "," + description;
   }
   
   public String toString() {
       return "Poster:\r\n" + super.toString() + "\r\nDescription: " + description;
   }
   
}