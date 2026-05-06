import java.sql.*;

public class Vehicle {

    public static String addVehicle(String reg, String type, String timeSlot, String entryDate) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO parking_entries(reg_no, vehicle_type, time_slot, entry_date) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, reg);
            ps.setString(2, type);
            ps.setString(3, timeSlot);
            ps.setDate(4, java.sql.Date.valueOf(entryDate));

            ps.executeUpdate();
            return "Vehicle entry recorded successfully ✅";

        } catch (Exception e) {
            return "Error adding vehicle: " + e.getMessage();
        }
    }

    public static String viewVehicles() {
        StringBuilder output = new StringBuilder();

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT entry_id, reg_no, vehicle_type, time_slot, entry_date, exit_date, status FROM parking_entries");

            output.append("ID | Reg No | Type | Time Slot | Entry Date | Exit Date | Status\n");
            output.append("-------------------------------------------------------------------------------\n");

            while (rs.next()) {
                output.append(rs.getInt("entry_id"))
                      .append(" | ")
                      .append(rs.getString("reg_no"))
                      .append(" | ")
                      .append(rs.getString("vehicle_type"))
                      .append(" | ")
                      .append(rs.getString("time_slot"))
                      .append(" | ")
                      .append(rs.getDate("entry_date"))
                      .append(" | ")
                      .append(rs.getDate("exit_date"))
                      .append(" | ")
                      .append(rs.getString("status"))
                      .append("\n");
            }
        } catch (Exception e) {
            return "Error loading vehicles: " + e.getMessage();
        }

        if (output.length() == 0) {
            return "No vehicles found.";
        }

        return output.toString();
    }

    public static String exitVehicleByRegNo(String regNo) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE parking_entries SET status = 'OUT', exit_date = CURRENT_DATE WHERE reg_no = ? AND status = 'IN'"
            );
            ps.setString(1, regNo);
            int affected = ps.executeUpdate();

            if (affected > 0) {
                return "Vehicle exit recorded successfully ✅";
            } else {
                return "No active vehicle entry found with registration number: " + regNo;
            }
        } catch (Exception e) {
            return "Error exiting vehicle: " + e.getMessage();
        }
    }
}
