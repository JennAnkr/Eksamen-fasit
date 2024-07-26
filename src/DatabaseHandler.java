import java.sql.*;

public class DatabaseHandler {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Funn";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Test1234!"; // Sett inn passord her eller bruk miljøvariabler

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    public void insertPerson(int id, String name, String phone, String email) {
        String sql = "INSERT INTO Person(Id, Navn, Tlf, E_post) VALUES(?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            System.out.println("Inserted person with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMuseum(int id, String name, String location) {
        String sql = "INSERT INTO Museum(Id, Navn, Sted) VALUES(?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, location);
            pstmt.executeUpdate();
            System.out.println("Inserted museum with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertVaapen(int id, String coordinates, int personId, Timestamp date, int estimatedYear, Integer museumId, String type, String material, int weight) {
        String sql = "INSERT INTO Vaapen(Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, coordinates);
            pstmt.setInt(3, personId);
            pstmt.setTimestamp(4, date);
            pstmt.setInt(5, estimatedYear);
            if (museumId != null) {
                pstmt.setInt(6, museumId);
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }
            pstmt.setString(7, type);
            pstmt.setString(8, material);
            pstmt.setInt(9, weight);
            pstmt.executeUpdate();
            System.out.println("Inserted vaapen with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSmykke(int id, String coordinates, int personId, Timestamp date, int estimatedYear, Integer museumId, String jewelryType, int estimatedValue, String fileName) {
        String sql = "INSERT INTO Smykke(Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Verdiestimat, filnavn) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, coordinates);
            pstmt.setInt(3, personId);
            pstmt.setTimestamp(4, date);
            pstmt.setInt(5, estimatedYear);
            if (museumId != null) {
                pstmt.setInt(6, museumId);
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }
            pstmt.setString(7, jewelryType);
            pstmt.setInt(8, estimatedValue);
            pstmt.setString(9, fileName);
            pstmt.executeUpdate();
            System.out.println("Inserted smykke with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMynt(int id, String coordinates, int personId, Timestamp date, int estimatedYear, Integer museumId, int diameter, String material) {
        String sql = "INSERT INTO Mynt(Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Diameter, Metall) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, coordinates);
            pstmt.setInt(3, personId);
            pstmt.setTimestamp(4, date);
            pstmt.setInt(5, estimatedYear);
            if (museumId != null) {
                pstmt.setInt(6, museumId);
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }
            pstmt.setInt(7, diameter);
            pstmt.setString(8, material);
            pstmt.executeUpdate();
            System.out.println("Inserted mynt with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllArtifacts() {
        StringBuilder myntResults = new StringBuilder();
        StringBuilder smykkeResults = new StringBuilder();
        StringBuilder vaaponResults = new StringBuilder();
        StringBuilder results = new StringBuilder();
        try (Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM Mynt");
            stmt = conn.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Smykke");
            stmt = conn.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM Vaapen");
            while(rs1.next()){
                myntResults.append(rs1.getString("Metall") + "\n");
            }
            while(rs2.next()){
                smykkeResults.append(rs2.getString("Type") + "\n");
            }
            while(rs3.next()){
                vaaponResults.append(rs3.getString("Type") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results.append("Mynt: \n"+ myntResults +" \n\n"+"Smykke: \n"+ smykkeResults +"\n\n"+"Vaapen: \n"+ vaaponResults).toString();
    }

    public String getAllArtifactsByYear(int year) {

        StringBuilder myntResults = new StringBuilder();
        StringBuilder smykkeResults = new StringBuilder();
        StringBuilder vaaponResults = new StringBuilder();
        StringBuilder results = new StringBuilder();
        try (Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM Mynt WHERE Antatt_årstall < "+year);
            stmt = conn.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Smykke  WHERE Antatt_årstall < "+year);
            stmt = conn.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM Vaapen WHERE Antatt_årstall < "+year);
            while(rs1.next()){
                myntResults.append(rs1.getString("Metall") + "\n");
            }
            while(rs2.next()){
                smykkeResults.append(rs2.getString("Type") + "\n");
            }
            while(rs3.next()){
                vaaponResults.append(rs3.getString("Type") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results.append("Mynt: \n"+ myntResults +" \n\n"+"Smykke: \n"+ smykkeResults +"\n\n"+"Vaapen: \n"+ vaaponResults).toString();
    }

    public int getTotalArtifactsByCount() {
        int result = 0;
        try (Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM Mynt UNION ALL SELECT COUNT(*) FROM Smykke UNION ALL SELECT COUNT(*) FROM Vaapen");
            int index = 1;
            while(rs1.next()){
                result += rs1.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

