import javax.swing.*;
import java.awt.*;

public class ArtifactApp {
    private final JFrame mainFrame;
    private final JTextArea displayArea;
    private final DatabaseHandler databaseHandler;

    public ArtifactApp() {
        mainFrame = new JFrame("Historical Artifacts Database");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        databaseHandler = new DatabaseHandler();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        JMenuItem viewAllArtifacts = new JMenuItem("Se informasjon om alle funngjenstander");
        JMenuItem viewArtifactsByYear = new JMenuItem("Se informasjon om alle funngjenstander eldre enn <årstall>");
        JMenuItem getArtifactCount = new JMenuItem("Få informasjon om antall funngjenstander registrert");
        JMenuItem exitApp = new JMenuItem("Her avslutter du");

        menu.add(viewAllArtifacts);
        menu.add(viewArtifactsByYear);
        menu.add(getArtifactCount);
        menu.addSeparator();
        menu.add(exitApp);

        mainFrame.setJMenuBar(menuBar);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        mainFrame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Action listeners for menu items using lambda expressions
        viewAllArtifacts.addActionListener(e -> showAllArtifacts());
        viewArtifactsByYear.addActionListener(e -> showArtifactsByYear());
        getArtifactCount.addActionListener(e -> showArtifactCount());
        exitApp.addActionListener(e -> System.exit(0));

        mainFrame.setVisible(true);
    }

    private void showAllArtifacts() {
        // Placeholder content instead of database query
        String allArtifacts = databaseHandler.getAllArtifacts();

        displayArea.setText("All artifacts would be displayed here. \n \n"+allArtifacts);
    }

    private void showArtifactsByYear() {
        String yearStr = JOptionPane.showInputDialog(mainFrame, "Angi årstall:");
        if (yearStr != null && !yearStr.isEmpty()) {
            try {
                int year = Integer.parseInt(yearStr);
                String allArtifacts = databaseHandler.getAllArtifactsByYear(year);
                displayArea.setText("Artifacts older than " + year + " would be displayed here. \n\n"+allArtifacts);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Ugyldig årstall", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showArtifactCount() {
        // Placeholder content instead of database query
        displayArea.setText("Total count of artifacts registered is "+ databaseHandler.getTotalArtifactsByCount());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArtifactApp::new);
    }
}
