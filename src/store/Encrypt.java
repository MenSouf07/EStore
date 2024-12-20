package store;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Encrypt {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b)); // Convertit chaque octet en hexadécimal
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur avec l'algorithme SHA-256", e);
        }
    }

    public static void main(String[] args) {
        // Mot de passe "stocké dans la BDD" (haché)
        String storedHashedPassword = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"; // hachage de "password"

        // Demander à l'utilisateur de saisir son mot de passe
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre mot de passe : ");
        String enteredPassword = scanner.nextLine();

        // Hachage du mot de passe saisi
        String hashedEnteredPassword = hashPassword(enteredPassword);
        // Vérification
        if (hashedEnteredPassword.equals(storedHashedPassword)) {
            System.out.println("Mot de passe valide !");
        } else {
            System.out.println("Mot de passe incorrect.");
        }

        scanner.close();
    }
}
