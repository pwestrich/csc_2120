import java.util.ArrayList;

public class Sections
{
   private ArrayList<Section> sections;

   private double[] grade_constants;
   private int total_num_labs;

   //all sections should use the same random number generator
   private Random rand;
   
   public Sections(int num_sections, double[] grade_constants, int total_num_labs)
   {
      this.grade_constants = grade_constants;
      sections = new ArrayList<Section>();

      for (int i = 1; i <= num_sections; i++)
      {
         Section section = new Section(i, total_num_labs);
         sections.add(section);
      }

      rand = Random.getRandomNumberGenerator(); 
   }

   //DO THIS
   //several short methods must be implemented (note: some have been done, see below)

   public int getNumSections(){
      
      return sections.size();
      
   }
   
   public int getNumStudents(int sectionID){ //the number of students in a given section
   
      return (sections.get(sectionID - 1).getNumStudents());
      
   }
   
   public int getNumLabs(int sectionID){ //the number of labs completed by the class for a given section
      
      return sections.get(sectionID - 1).getNumLabs();
      
   }
   
   public int getNumLabs(int sectionID, int studentID){ //the number of labs completed by an individual student
      
      return (sections.get(sectionID - 1).getNumLabs(studentID));
      
   }
   
   public int getPartnerID(int sectionID, int studentID, int labID){ //a student's partner for a given lab (note consistent parameter ordering)
      
      return (sections.get(sectionID - 1).getPartnerID(studentID, labID));
      
   }
   
   public String getStudentName(int sectionID, int studentID){
      
      return sections.get(sectionID - 1).getName(studentID);
      
   }
   
   public boolean isPresent(int sectionID, int studentID, int labID){
      
      return (sections.get(sectionID).isPresent(studentID, labID));
      
   }
   
   public String studentInfo(int sectionID, int studentID){ //all lab grades for a single student (used in top text box in GUI)
      
      return sections.get(sectionID - 1).getStudentInfo(studentID);
      
   }
   
   public String partnerList(int sectionID, int labID){ //list of partners for the requested lab
   
      return sections.get(sectionID - 1).getPartnerList(labID);
   
   }

   public boolean isActive(int sectionID, int studentID){ //is student still in the class?
      
      return sections.get(sectionID - 1).isActive(studentID);
      
   }
   
   public void setInactive(int sectionID, int studentID){ //student has withdrawn
      
      sections.get(sectionID - 1).setInactive(studentID);
      
   }
   
   public void setNotPresent(int sectionID, int studentID, int labID){ //student absent for lab (simply set the lab grade to F)
      
      sections.get(sectionID - 1).setNotPresent(studentID, labID);
      
   }
   
   public void setGrade(int sectionID, int studentID, int labID, char grade){ //update the student's grade for the lab (and partner's)
      
      sections.get(sectionID - 1).setGrade(studentID, labID, grade);
      
   }
   
   public void addLab(int sectionID){ //starting a new lab
      
      sections.get(sectionID - 1).addLab();
      total_num_labs++;
      
   }

   //no work below this point
   public void computePartners(int sectionID)
   {
      if (sectionID <= 0 || sectionID > getNumSections())
      {
         return;
      }

      Section section = sections.get(sectionID - 1);
      section.computePartners(rand);
   }

   public void writeFiles()
   {
      for (Section section : sections)
      {
         section.writeFile(grade_constants, total_num_labs);
      }
   }
}
