import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Parking Menu");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JButton addBtn = new JButton("Vehicle Entry");
        addBtn.setBounds(120, 50, 160, 40);

        JButton viewBtn = new JButton("View All Vehicles");
        viewBtn.setBounds(120, 110, 160, 40);

        JButton deleteBtn = new JButton("Vehicle Exit");
        deleteBtn.setBounds(120, 170, 160, 40);

        addBtn.addActionListener(e -> showAddVehicleDialog(frame));

        viewBtn.addActionListener(e -> showViewVehiclesDialog(frame));

        deleteBtn.addActionListener(e -> showDeleteVehicleDialog(frame));

        frame.add(addBtn);
        frame.add(viewBtn);
        frame.add(deleteBtn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void showAddVehicleDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Vehicle Entry", true);
        dialog.setSize(350, 250);
        dialog.setLayout(null);

        // Auto-fill current time and date
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        JLabel l1 = new JLabel("Reg No:");
        l1.setBounds(20, 20, 100, 25);
        JTextField t1 = new JTextField();
        t1.setBounds(120, 20, 150, 25);

        JLabel l2 = new JLabel("Type:");
        l2.setBounds(20, 60, 100, 25);
        JTextField t2 = new JTextField();
        t2.setBounds(120, 60, 150, 25);

        JLabel l3 = new JLabel("Time Slot:");
        l3.setBounds(20, 100, 100, 25);
        JTextField t3 = new JTextField(currentTime);
        t3.setBounds(120, 100, 150, 25);
        t3.setEditable(false); // Make it read-only

        JLabel l4 = new JLabel("Entry Date:");
        l4.setBounds(20, 140, 100, 25);
        JTextField t4 = new JTextField(currentDate);
        t4.setBounds(120, 140, 150, 25);
        t4.setEditable(false); // Make it read-only

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(120, 180, 80, 30);

        saveBtn.addActionListener(e -> {
            String regNo = t1.getText().trim();
            String type = t2.getText().trim();
            String timeSlot = t3.getText().trim();
            String entryDate = t4.getText().trim();

            if (regNo.isEmpty() || type.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in registration number and vehicle type.", "Missing Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String result = Vehicle.addVehicle(regNo, type, timeSlot, entryDate);
            JOptionPane.showMessageDialog(dialog, result);
            dialog.dispose();
        });

        dialog.add(l1); dialog.add(t1);
        dialog.add(l2); dialog.add(t2);
        dialog.add(l3); dialog.add(t3);
        dialog.add(l4); dialog.add(t4);
        dialog.add(saveBtn);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private static void showViewVehiclesDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "View All Vehicles", true);
        dialog.setSize(500, 400);
        dialog.setLayout(null);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(20, 20, 450, 300);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(200, 330, 80, 30);

        closeBtn.addActionListener(e -> dialog.dispose());

        dialog.add(scrollPane);
        dialog.add(closeBtn);

        // Load vehicles data
        String result = Vehicle.viewVehicles();
        outputArea.setText(result);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private static void showDeleteVehicleDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Vehicle Exit", true);
        dialog.setSize(300, 150);
        dialog.setLayout(null);

        JLabel label = new JLabel("Enter Registration Number:");
        label.setBounds(10, 20, 160, 25);
        JTextField regField = new JTextField();
        regField.setBounds(170, 20, 120, 25);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(100, 60, 80, 30);

        deleteBtn.addActionListener(e -> {
            String regNo = regField.getText().trim();
            if (regNo.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Enter the vehicle registration number.", "Missing Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String result = Vehicle.exitVehicleByRegNo(regNo);
            JOptionPane.showMessageDialog(dialog, result);
            dialog.dispose();
        });

        dialog.add(label);
        dialog.add(regField);
        dialog.add(deleteBtn);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
