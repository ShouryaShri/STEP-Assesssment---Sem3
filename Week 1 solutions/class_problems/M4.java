class IdCard{
    String name;
    int bookissued;
    IdCard(String name, int bookissued){
        this.name = name;
        this.bookissued=bookissued;
    }
}
public class M4 {
    public static void main(String[] args) {
        IdCard ravi = new IdCard("Ravi",0);
        IdCard duplicate = ravi;
        duplicate.bookissued = 3;
        System.out.println("Ravi's books issued(via first variable): " +ravi.bookissued);
        System.out.println("duplicate == ravi: "+(duplicate == ravi));
        IdCard separate = new IdCard("Ravi",3);
        System.out.println("separate == ravi: "+(separate == ravi));
    }
}
