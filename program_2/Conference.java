import java.util.ArrayList;
public class Conference implements Statable, java.io.Serializable {
    private String name;
    private String conferenceChair;
    private String startDate;
    private String endDate;
    public final int MAX_REGISTRATION;
    public final double FEE;
    public static final double PENALTY = 0.25;
    private boolean cancelFree;
    private boolean subsClosed;
    private boolean regClosed;
    private double revenue;
    private int numRegistrations;
    public ArrayList<String> registrants;
    public ArrayList<String> waitingList;
    public ArrayList<String> cancellations;
    public ArrayList<Submission> submissions;
    public ArrayList<Submission> acceptances;
    public ArrayList<Submission> rejections;
    
    public Conference(String name, String conferenceChair, String start, String end,
                      int max, double fee, boolean isCancelFree) throws ConferenceException {
       if (name == null || name.length() == 0 || conferenceChair == null || conferenceChair.length() == 0 ||
           start == null || start.length() == 0 || end == null || end.length() == 0) {
               throw new ConferenceException("Null/empty string passed in");
       }
       if (Utilities.invalidDate(start) || Utilities.invalidDate(end)) {
           throw new ConferenceException("Invalid date passed in");
       }
       if (end.compareTo(start) < 0) {
           throw new ConferenceException("Conference starts after it ends");
       }
       if (max <= 0 || fee < 0) {
           throw new ConferenceException("Invalid capacity or price");
       }
       this.name = name;
       this.conferenceChair = conferenceChair;
       startDate = start;
       endDate = end;
       MAX_REGISTRATION = max;
       FEE = fee;
       cancelFree = isCancelFree;
       subsClosed = false;
       regClosed = false;
       revenue = 0;
       numRegistrations = 0;
       registrants = new ArrayList<String>();
       waitingList = new ArrayList<String>();
       cancellations = new ArrayList<String>();
       submissions = new ArrayList<Submission>();
       acceptances = new ArrayList<Submission>();
       rejections = new ArrayList<Submission>();           
    }
    
    public Conference(String name, String chair, String start, String end,
                      int max, double fee, boolean cancelFree, boolean subsClosed,
                      boolean regsClosed, double revenue,
                      ArrayList<String> regs, ArrayList<String> waits, ArrayList<String> cancels,
                      ArrayList<Submission> subs, ArrayList<Submission> accs, ArrayList<Submission> rejs) throws ConferenceException{
       this(name, chair, start, end, max, fee, cancelFree);
       this.subsClosed = subsClosed;
       this.regClosed = regClosed;
       this.revenue = revenue;
       registrants = regs;
       waitingList = waits;
       cancellations = cancels;
       submissions = subs;
       acceptances = accs;
       rejections = rejs;
    }
    
    public String getName() {
        return name;
    }
    
    public String getChair() {
        return conferenceChair;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public boolean isCancelFree() {
        return cancelFree;
    }
    
    public boolean isRegClosed() {
        return regClosed;
    }
    
    public boolean canSubmit() {
        return !subsClosed;
    }
    
    public double getRevenue() {
        return revenue;
    }
    
    public ArrayList<String> getRegistrants() {
        return registrants;
    }
    
    public ArrayList<String> getWaitingList() {
        return waitingList;
    }
    
    public ArrayList<String> getCancellations() {
        return cancellations;
    }
   
    public int getNumRegistrants() {
        return registrants.size();
    }
    
    public int getNumCancellations() {
        return cancellations.size();
    }
    
    public int getWaitingListSize() {
        return waitingList.size();
    }
    
    public double getCancellationPercentage() {
        return ((double) getNumCancellations()) / numRegistrations;
    }
    
    public String submit(Submission sub) {
        String result = "Submission unsuccessful";
        if (canSubmit()) {
            if (!submissions.contains(sub)) {
               submissions.add(sub);
               result = "Submission " + submissions.size() + ": " + sub.getType();
            }
            else {
               result = "Duplicate " + sub.getType() + " not added";
            }
        }
        return result;
    }

    public void closeSubmissions() {
        subsClosed = true;
    }
    
    public String closeRegistrations() {
        String result = "Registrations closed";
        if (waitingList.size() > 0) {
           result += "\nRemaining on Waiting List:";   
           for (String name : waitingList) {
              result += "\n" + name;
           }
        }
        regClosed = true;
        return result;
    }
    
    public String accept(int subNum) {
        String result = "Acceptance unsuccessful";
        if (subsClosed && subNum >= 1 && subNum <= submissions.size()) {
            Submission sub = submissions.get(subNum - 1);
            if (!acceptances.contains(sub)) {
               acceptances.add(sub);
               result = "Acceptance of " + sub + " successful";
            }
            else {
                result = sub + " already accepted. Not doing again.";
            }
        }
        return result;
    }
    
    public double getAcceptanceRate() {
        double result = 0;
        double numSubmits;
        if (subsClosed) {
            numSubmits = submissions.size();
            if (numSubmits > 0) {
               result = acceptances.size() / numSubmits * 100;
            }
        }
        return result;
    }
    
    public String reject(int subNum) {
        String result = "Rejection unsuccessful";
        if (subsClosed && subNum >= 1 && subNum <= submissions.size()) {
            Submission sub = submissions.get(subNum - 1);
            if (!rejections.contains(sub)) {
               rejections.add(sub);
               result = "Rejection of " + sub + " successful";
            }
            else {
                result = sub + " already rejected. Not doing again.";
            }
        }
        return result;        
    }
    
    public String register(String attendee) {
        String result = "Register: " + attendee;
        if (!regClosed && attendee != null && attendee.length() > 0) {
            if (!registrants.contains(attendee)) {
                if (registrants.size() < MAX_REGISTRATION) {
                    registrants.add(attendee);
                    numRegistrations++;
                    result += " registered";
                    revenue += FEE;
                }
                else {
                    if (!waitingList.contains(attendee)) {
                        waitingList.add(attendee);
                        result += " added to waiting list";
                    }
                    else {
                        result += " already on waiting list, not added again";
                    }
                }
            }
            else {
                result += " already registered, not added again";
            }
        }
        else {
            result += " invalid";
        }
        return result;
    }
    
    public String cancel(String who) {
        String result = "Cancel: " + who;
        if (who != null && who.length() > 0) {
            if (registrants.contains(who)) {
                registrants.remove(who);
                cancellations.add(who);
                if (cancelFree) {
                    revenue -= FEE;
                }
                else {
                    revenue -= (1 - PENALTY) * FEE;
                }
                result += " cancelled";
                if (waitingList.size() > 0) {
                    String firstName = waitingList.remove(0);
                    registrants.add(firstName);
                    numRegistrations++;
                    result += " and " + firstName + " registered from the waiting list";
                    revenue += FEE;
                }
            }
            else {
                result += " did not register";
            }
        }
        else {
            result += " invalid";
        }
        return result;
    }
    
    public String getState() {
        return name + "," + conferenceChair + "," + startDate + "," + endDate + "," + MAX_REGISTRATION + "," +
               FEE + "," + cancelFree + "," + subsClosed + "," + 
               regClosed + "," + revenue + "," + registrants + "," +
               waitingList + "," + cancellations + "," + submissions + "," + acceptances + "," + rejections;
    }
    
    public String toString() {
        return "Conference Name: " + name + "\r\nConference Chair: " + conferenceChair + 
               "\r\nStarts: " + startDate + "\r\nEnds: " + endDate +
               "\r\nMax #: " + MAX_REGISTRATION + "\r\nFee: " + Currency.formatCurrency(FEE) + 
               "\r\nCancel Free?: " + cancelFree + "\r\nSubs Closed?: " + subsClosed +
               "\r\nReg. Closed?: " + regClosed +
               "\r\nRevenue: " + Currency.formatCurrency(revenue) + "\r\nRegs.: " + registrants +
               "\r\nWaiting: " + waitingList + "\r\nCancels: " + cancellations + 
               "\r\nSubmissions: " + submissions + "\r\nAcceptances: " + acceptances +
               "\r\nRejections: " + rejections;
    }    
    
    public boolean equals(Object obj) {
        if (obj instanceof Conference) {
            return equals((Conference) obj);
        }
        else {
           return false;
        }
    }
    
    public boolean equals(Conference conf) {
        return name.equalsIgnoreCase(conf.getName());
    }
}