import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class HelloWorldSwing {

    // Méthode pour créer et afficher la fenêtre
    private void createAndShowGUI() {
        // Créer la fenêtre
        JFrame frame = new JFrame("Hello World Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application lorsque la fenêtre est fermée
        frame.setSize(300, 200); // Taille de la fenêtre

        // Créer un label avec le texte "Hello World"
        JLabel label = new JLabel("Hello World", JLabel.CENTER);
        frame.getContentPane().add(label); // Ajouter le label au contenu de la fenêtre

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Utiliser SwingUtilities pour assurer que le GUI est créé sur le thread d'événements
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HelloWorldSwing().createAndShowGUI();
            }
        });
    }
}
