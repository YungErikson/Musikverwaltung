package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Eine Klasse, die ein Untermenü zum Erstellen von Playlists erstellt.
 */
public class PlaylistErstellenUntermenue extends JFrame {
    private List<Song> songs;
    private Map<String, List<Song>> artistPlaylists;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, List<Song>> userPlaylists;

    /**
     * Konstruktor zur Initialisierung des Untermenüs zum Erstellen von Playlists.
     *
     * @param previousFrame Das vorherige JFrame, von dem aus dieses Untermenü aufgerufen wurde.
     * @param songs Eine Liste von Songs, die für die Playlist-Auswahl zur Verfügung stehen.
     * @param artistPlaylists Eine Map von Künstler-Playlists.
     * @param genrePlaylists Eine Map von Genre-Playlists.
     * @param userPlaylists Eine Map von benutzerdefinierten Playlists.
     */
    public PlaylistErstellenUntermenue(JFrame previousFrame, List<Song> songs,
            Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists,
            Map<String, List<Song>> userPlaylists) {
        this.songs = songs;
        this.artistPlaylists = artistPlaylists;
        this.genrePlaylists = genrePlaylists;
        this.userPlaylists = userPlaylists;

        setTitle("Playlist erstellen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        List<String> songTitles = new ArrayList<>();
        for (Song song : songs) {
            songTitles.add(song.getTitle());
        }
        Collections.sort(songTitles);

        JList<String> songList = new JList<>(songTitles.toArray(new String[0]));
        songList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane songScrollPane = new JScrollPane(songList);

        JTextField playlistNameField = new JTextField();
        playlistNameField.setColumns(20);

        JButton playlistErstellenButton = new JButton("Playlist erstellen");
        JButton zurueckButton = new JButton("Zurück");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(songScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(playlistNameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(playlistErstellenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(zurueckButton, gbc);

        playlistErstellenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Hole den eingegebenen Namen der Playlist aus dem playlistNameField
                String playlistName = playlistNameField.getText();
                
                // Überprüfe, ob der Playlist-Name nicht leer ist
                if (!playlistName.isEmpty()) {
                	// Erstelle eine Liste, um ausgewählte Songs zu speichern
                    List<Song> selectedSongs = new ArrayList<>();
                    
                    // Iteriere über die ausgewählten Titel in der songList
                    for (String selectedTitle : songList.getSelectedValuesList()) {
                    	// Suche nach dem ausgewählten Titel in der Liste der verfügbaren Songs
                        for (Song song : songs) {
                        	// Wenn der Titel übereinstimmt, füge den Song zur Liste der ausgewählten Songs hinzu und beende die Schleife
                            if (song.getTitle().equals(selectedTitle)) {
                                selectedSongs.add(song);
                                break;
                            }
                        }
                    }

                    // Überprüfe, ob mindestens ein Song ausgewählt wurde
                    if (!selectedSongs.isEmpty()) {
                    	// Erstelle eine Kopie der ausgewählten Songs für die Playlist
                        List<Song> playlistSongs = new ArrayList<>(selectedSongs);
                        
                        // Überprüfe, ob eine Playlist mit dem angegebenen Namen noch nicht existiert
                        if (!artistPlaylists.containsKey(playlistName) && !genrePlaylists.containsKey(playlistName)
                                && !userPlaylists.containsKey(playlistName)) {
                        	// Füge die Playlist zur Map der benutzerdefinierten Playlists hinzu.                       	
                            userPlaylists.put(playlistName, playlistSongs);
                            // Zeige eine Erfolgsmeldung an
                            JOptionPane.showMessageDialog(null, "Die benutzerdefinierte Playlist '" + playlistName + "' wurde erstellt und hinzugefügt.");
                        } else {
                        	// Zeige eine Fehlermeldung an, wenn eine Playlist mit dem Namen bereits existiert
                            JOptionPane.showMessageDialog(null, "Eine Playlist mit dem Namen '" + playlistName + "' existiert bereits.");
                        }
                    } else {
                    	// Zeige eine Fehlermeldung an, wenn kein Song ausgewählt wurde
                        JOptionPane.showMessageDialog(null, "Bitte wählen Sie mindestens einen Song aus.");
                    }
                } else {
                	// Zeige eine Fehlermeldung an, wenn kein Playlist-Name eingegeben wurde
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Playlist-Namen ein.");
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

