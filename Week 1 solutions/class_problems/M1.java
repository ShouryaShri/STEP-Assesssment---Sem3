class PlacementRecord{
    String studentName;
    String company;
    double packageLpa;
    PlacementRecord(String studentName, String company, double packageLpa){
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }
    void printRecord(){
        System.out.println(studentName+ " -> "+company+" @ "+packageLpa+" LPA");
    }
}
public class M1 {
    public static void main(String[] args){
        PlacementRecord[] Record = {new PlacementRecord("Ravi","TCS",4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik","Infosys", 4.8)
        };
        for(int i =0; i < Record.length; i++){
            Record[i].printRecord();
        }
    }
}
