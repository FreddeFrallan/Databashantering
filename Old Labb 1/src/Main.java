import java.sql.Connection;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] argv) throws Exception{
        DBConnection db = new DBConnection();
        db.connect();

        Queries.selectAllCarBrands(db.getConn());

        System.out.print("Ange stad: ");
        String city = sc.nextLine();
        Queries.selectAllBilarCertainCity(db.getConn(), city);

        System.out.print("Ange regnr: ");
        String regnr = sc.nextLine();
        System.out.print("Ange ny färg: ");
        String newColor = sc.nextLine();
        Queries.updateCarColor(db.getConn(), regnr, newColor);

        Queries.selectAllBilarCertainCity(db.getConn(), city);

        db.closeConnection();
    }
}
