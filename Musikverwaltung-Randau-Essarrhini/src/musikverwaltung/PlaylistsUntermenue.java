package musikverwaltung;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse repr�sentiert das Untermen� f�r die Verwaltung von Playlists.
 * In diesem Untermen� k�nnen Benutzer zu den Men�s "Playlist abspielen" oder "Playlist erstellen".
 */
class PlaylistsUntermenue extends JFrame {
	
	/**
     * Konstruktor zur Initialisierung des PlaylistsUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum Hauptmen� zur�ckzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verf�gbaren Songs im System darstellen.
     * @param artistPlaylists Eine Map, die K�nstler-Playlists speichert.
     * @param genrePlaylists Eine Map, die Genre-Playlists speichert.
     * @param userPlaylists Eine Map, die benutzerdefinierte Playlists speichert.
     */
    public PlaylistsUntermenue(JFrame previousFrame, List<Song> songs,
            Map<String, List<Song>> artistPlaylists, Map<String, List<Song>> genrePlaylists,
            Map<String, List<Song>> userPlaylists) {
        setTitle("Playlists");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton playlistsAbspielenButton = new JButton("Playlists abspielen");
        JButton playlistErstellenButton = new JButton("Playlist erstellen");
        JButton zurueckButton = new JButton("Zur�ck");

        Dimension buttonSize = new Dimension(150, 40);
        playlistsAbspielenButton.setPreferredSize(buttonSize);
        playlistErstellenButton.setPreferredSize(buttonSize);
        zurueckButton.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(playlistsAbspielenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(playlistErstellenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(zurueckButton, gbc);

        playlistsAbspielenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new PlaylistAbspielenUntermenue(PlaylistsUntermenue.this, songs, artistPlaylists, genrePlaylists, userPlaylists);
            }
        });

        playlistErstellenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new PlaylistErstellenUntermenue(PlaylistsUntermenue.this, songs, artistPlaylists, genrePlaylists, userPlaylists);
            }
        });

        zurueckButton.addActionListener(e -> {
            // Schlie�e das aktuelle Fenster
            dispose();
            
            // Setze das vorherige Fenster auf sichtbar, um dorthin zur�ckzukehren
            previousFrame.setVisible(true);
        });

        setVisible(true);
        previousFrame.setVisible(false);
    }
}

