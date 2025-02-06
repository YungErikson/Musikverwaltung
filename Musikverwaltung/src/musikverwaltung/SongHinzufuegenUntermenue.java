package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse repräsentiert das Untermenü zum Hinzufügen von neuen Songs zu den verfügbaren Songs und Playlists.
 * Benutzer können Song-Daten eingeben und neue Songs zu den
 * verfügbaren Songs und den entsprechenden Playlists hinzufügen.
 */
public class SongHinzufuegenUntermenue extends JFrame {
    private List<Song> songs;
    private DefaultListModel<Song> listModel;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;

    /**
     * Konstruktor zur Initialisierung des SongHinzufuegenUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum Hauptmenü zurückzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verfügbaren Songs im System darstellen.
     * @param listModel Das DefaultListModel zur Anzeige der Songs.
     * @param artistPlaylists Eine Map, die Künstler-Playlists speichert.
     * @param genrePlaylists Eine Map, die Genre-Playlists speichert.
     */
    public SongHinzufuegenUntermenue(JFrame previousFrame, List<Song> songs, DefaultListModel<Song> listModel,
                                     Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists) {
        this.songs = songs;
        this.listModel = listModel;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;

        setTitle("Songs hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Titel:");
        JLabel artistLabel = new JLabel("Künstler:");
        JLabel genreLabel = new JLabel("Genre:");

        JButton songHinzufuegenButton = new JButton("Song hinzufügen");
        JButton zurueckButton = new JButton("Zurück");

        JTextField titleField = new JTextField(20);
        JTextField artistField = new JTextField(20);
        JTextField genreField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        gbc.gridx = 1;
        add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(artistLabel, gbc);

        gbc.gridx = 1;
        add(artistField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(genreLabel, gbc);

        gbc.gridx = 1;
        add(genreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(songHinzufuegenButton, gbc);

        gbc.gridy = 4;
        add(zurueckButton, gbc);

        songHinzufuegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hole die eingegebenen Werte für Titel, Künstler und Genre und entferne Leerzeichen
                String title = titleField.getText().trim();
                String artist = artistField.getText().trim();
                String genre = genreField.getText().trim();

                // Überprüfe, ob Titel, Künstler und Genre nicht leer sind
                if (!title.isEmpty() && !artist.isEmpty() && !genre.isEmpty()) {
                    // Erstelle einen neuen Song mit den eingegebenen Daten
                    Song newSong = new Song(title, artist, genre);
                    
                    // Überprüfe, ob der Song bereits vorhanden ist
                    if (!songs.contains(newSong)) {
                        // Füge den neuen Song dem listModel und der songs-Liste hinzu
                        listModel.addElement(newSong);
                        songs.add(newSong);

                        // Füge den neuen Song zur zugehörigen Künstler-Playlist hinzu oder erstelle sie, falls nicht vorhanden
                        if (!artistPlaylists.containsKey(artist)) {
                            artistPlaylists.put(artist, new ArrayList<>());
                        }
                        artistPlaylists.get(artist).add(newSong);

                        // Füge den neuen Song zur zugehörigen Genre-Playlist hinzu oder erstelle sie, falls nicht vorhanden
                        if (!genrePlaylists.containsKey(genre)) {
                            genrePlaylists.put(genre, new ArrayList<>());
                        }
                        genrePlaylists.get(genre).add(newSong);

                        // Zeige eine Erfolgsmeldung an und leere die Eingabefelder
                        JOptionPane.showMessageDialog(null, "Song wurde hinzugefügt.");
                        titleField.setText("");
                        artistField.setText("");
                        genreField.setText("");
                    } else {
                        // Zeige eine Fehlermeldung an, wenn der Song bereits existiert
                        JOptionPane.showMessageDialog(null, "Ein Song mit diesen Daten existiert bereits.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Zeige eine Fehlermeldung an, wenn nicht alle Felder ausgefüllt sind
                    JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        zurueckButton.addActionListener(e -> {
            // Schließe das aktuelle Fenster
            dispose();
            
            // Setze das vorherige Fenster auf sichtbar, um dorthin zurückzukehren
            previousFrame.setVisible(true);
        });

        setVisible(true);
        previousFrame.setVisible(false);
    }
}
