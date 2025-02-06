package musikverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Diese Klasse repräsentiert das Untermenü zum Anzeigen und Abspielen von Songs nach Titel sortiert.
 * Benutzer können die Songs nach Titel anzeigen und einzelne Songs abspielen.
 */
public class SongsNachTitelUntermenue extends JFrame {
    private List<Song> songs;

    /**
     * Konstruktor zur Initialisierung des SongsNachTitelUntermenue-Objekts.
     *
     * @param previousFrame Das vorherige JFrame-Objekt, um zum vorherigen Menü zurückzukehren.
     * @param songs Eine Liste von Song-Objekten, die alle verfügbaren Songs im System darstellen.
     */
    public SongsNachTitelUntermenue(JFrame previousFrame, List<Song> songs) {
        this.songs = songs;

        setTitle("Songs nach Titel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        DefaultListModel<Song> listModel = new DefaultListModel<>();
        JList<Song> songList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(songList);

        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparing(Song::getTitle));

        for (Song song : sortedSongs) {
            listModel.addElement(song);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        JButton abspielenButton = new JButton("Abspielen");
        JButton zurueckButton = new JButton("Zurück");

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(abspielenButton, gbc);

        gbc.gridx = 1;
        add(zurueckButton, gbc);

        abspielenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hole den ausgewählten Song aus der JList songList
                Song selectedSong = songList.getSelectedValue();
                
                // Überprüfe, ob ein Song ausgewählt wurde
                if (selectedSong != null) {
                    // Zeige eine Meldung an, dass der ausgewählte Song abgespielt wird, und zeige die Details des Songs an
                    JOptionPane.showMessageDialog(null, "Song wird abgespielt:\n" + selectedSong.toString());
                } else {
                    // Zeige eine Fehlermeldung an, wenn kein Song ausgewählt wurde
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie einen Song aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
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