package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Eine Klasse, die ein Untermenü zum Anzeigen aller Songs erstellt.
 */
class AlleSongsUntermenue extends JFrame {
	
	private List<Song> songs; // Eine Liste von Songs
	
	/**
     * Konstruktor zur Initialisierung des Untermenüs zum Anzeigen aller Songs.
     *
     * @param previousFrame Das vorherige JFrame, von dem aus dieses Untermenü aufgerufen wurde.
     * @param songs Eine Liste von Songs, die angezeigt werden sollen.
     */
    public AlleSongsUntermenue(JFrame previousFrame, List<Song> songs) {
    	this.songs = songs;
        setTitle("Alle Songs");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea(getAllSongsText(songs));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton zurueckButton = new JButton("Zurück");
        zurueckButton.addActionListener(e -> {
            // Schließe das aktuelle Fenster
            dispose();
            
            // Setze das vorherige Fenster auf sichtbar, um dorthin zurückzukehren
            previousFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(zurueckButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }
    
    /**
     * Erstellt eine Zeichenkette mit allen Song-Informationen.
     *
     * @param songs Eine Liste von Songs, die in der Zeichenkette angezeigt werden sollen.
     * @return Eine Zeichenkette mit allen Song-Informationen.
     */
    private String getAllSongsText(List<Song> songs) {
        // Erstellen einer sortierten Kopie der Songs-Liste nach dem Titel
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparing(Song::getTitle)); 

        // Erstellen eines StringBuilder, um den formatierten Text aufzubauen
        StringBuilder allSongsText = new StringBuilder("Alle Songs:\n\n");

        // Schleife durch die sortierten Songs und füge die Details jedes Songs zum Text hinzu
        for (Song song : sortedSongs) {
            allSongsText.append(song.toString()).append("\n");
        }

        // Rückgabe des fertigen formatierten Texts
        return allSongsText.toString();
    }
}