package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import store.*;

public class DataBaseOperations {

    public static ArrayList<Brand> getAllBrand(Connection connection) throws SQLException {
        String query = "SELECT * FROM Brand";

        ArrayList<Brand> list_brand = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                int brand_id = resultSet.getInt("brand_id");
                String name = resultSet.getString("name");

                Brand b = new Brand(brand_id, name);
                list_brand.add(b);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des marques : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des marques : " + e.getMessage());
        }
        return list_brand;
    }

    public static ArrayList<Brand> getBrandBasedOnBrandId(Connection connection, int ID) throws SQLException {
        String query = "SELECT * FROM Brand WHERE brand_id = ?";

        ArrayList<Brand> list_brand = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int brand_id = resultSet.getInt("brand_id");
                String name = resultSet.getString("name");

                Brand b = new Brand(brand_id, name);
                list_brand.add(b);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des marques : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des marques : " + e.getMessage());
        }
        return list_brand;
    }

    public static void insertBrand(Connection connection, Brand b) throws SQLException {
        String query = "INSERT INTO Brand (name) VALUES (?)";
         
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, b.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) insérée(s).");

            // Récupérer la clé générée
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    b.setId(generatedId);
                    System.out.println("ID de la marque insérée : " + generatedId);
                } else {
                    System.out.println("Aucune clé générée n'a été récupérée.");
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Erreur lors de l'insertion d'un n-uplet ! \n" + //
                                "Erreur d'unicité : une marque avec ce nom existe déjà.");
            //System.err.println("Erreur lors de l'insertion d'un n-uplet ! \n" + //
            //                    "Erreur d'unicité : une marque avec ce nom existe déjà.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'insertion d'un n-uplet : " + e.getMessage());
            //System.err.println("Erreur lors de l'insertion d'un n-uplet : " + e.getMessage());
        }
    }

    public static void updateBrand(Connection connection, Brand b1, Brand b2) throws SQLException {
        String query = "UPDATE Brand SET name = ? WHERE brand_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, b2.getName());
            preparedStatement.setInt(2, b1.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            b1.update(b2); //on devrait pas changer pour b1.setName(b2.getName()); ??
            System.out.println(rowsAffected + " ligne(s) mise(s) à jour.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Erreur lors de la mise à jour du n-uplet ! \n" + //
                                "Erreur d'unicité : une marque avec ce nom existe déjà.");
            //System.err.println("Erreur lors de la mise à jour du n-uplet ! \n" + //
            //                    "Erreur d'unicité : une marque avec ce nom existe déjà.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise à jour du n-uplet : " + e.getMessage());
            //System.err.println("Erreur lors de la mise à jour du n-uplet : " + e.getMessage());
        }
    }

    public static void deleteBrand(Connection connection, Brand b) throws SQLException {
        String query = "DELETE FROM Brand WHERE brand_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, b.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) supprimée(s).");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la marque : " + e.getMessage());
            //System.err.println("Erreur lors de la suppression de la marque : " + e.getMessage());
        }
    }




    
    public static ArrayList<ProductCategory> getAllProductCategories(Connection connection) throws SQLException {
        String query = "SELECT * FROM Product_Category";

        ArrayList<ProductCategory> list_category = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                int category_id = resultSet.getInt("category_id");
                String name = resultSet.getString("name");

                ProductCategory pc = new ProductCategory(category_id, name);
                list_category.add(pc);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des categorie : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des categorie : " + e.getMessage());
        }
        return list_category;
    }

    public static ArrayList<ProductCategory> getProductCategoryBasedOnProductCategoryId(Connection connection, int ID) throws SQLException {
        String query = "SELECT * FROM Product_Category WHERE category_id = ?";

        ArrayList<ProductCategory> list_category = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int category_id = resultSet.getInt("category_id");
                String name = resultSet.getString("name");

                ProductCategory pc = new ProductCategory(category_id, name);
                list_category.add(pc);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des marques : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des marques : " + e.getMessage());
        }
        return list_category;
    }

    public static void insertProductCategory(Connection connection, ProductCategory pc) throws SQLException {
        String query = "INSERT INTO Product_Category (name) VALUES (?)";
         
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pc.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) insérée(s).");

            // Récupérer la clé générée
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    pc.setId(generatedId);
                    System.out.println("ID de la marque insérée : " + generatedId);
                } else {
                    System.out.println("Aucune clé générée n'a été récupérée.");
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Erreur lors de l'insertion d'un n-uplet ! \n" + //
                                "Erreur d'unicité : une categorie avec ce nom existe déjà.");
            //System.err.println("Erreur lors de l'insertion d'un n-uplet ! \n" + //
            //                    "Erreur d'unicité : une categorie avec ce nom existe déjà.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'insertion d'un n-uplet : " + e.getMessage());
            //System.err.println("Erreur lors de l'insertion d'un n-uplet : " + e.getMessage());
        }
    }

    public static void updateProductCategory(Connection connection, ProductCategory pc1, ProductCategory pc2) throws SQLException {
        String query = "UPDATE Product_Category SET name = ? WHERE category_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pc2.getName());
            preparedStatement.setInt(2, pc1.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            pc1.update(pc2);
            System.out.println(rowsAffected + " ligne(s) mise(s) à jour.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Erreur lors de la mise à jour du n-uplet ! \n" + //
                                "Erreur d'unicité : une categorie avec ce nom existe déjà.");
            //System.err.println("Erreur lors de la mise à jour du n-uplet ! \n" + //
            //                    "Erreur d'unicité : une categorie avec ce nom existe déjà.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise à jour du n-uplet : " + e.getMessage());
            //System.err.println("Erreur lors de la mise à jour du n-uplet : " + e.getMessage());
        }
    }

    public static void deleteProductCategory(Connection connection, ProductCategory pc) throws SQLException {
        String query = "DELETE FROM Product_Category WHERE category_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pc.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) supprimée(s).");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la categorie : " + e.getMessage());
            //System.err.println("Erreur lors de la suppression de la categorie : " + e.getMessage());
        }
    }






    public static ArrayList<CreditCard> getAllCreditCards(Connection connection) throws SQLException {
        String query = "SELECT * FROM Credit_Card";

        ArrayList<CreditCard> list_card = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                int card_id = resultSet.getInt("card_id");
                int customer_id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String card_number = resultSet.getString("card_number");
                int expiration_date_month = resultSet.getInt("expiration_date_month");
                int expiration_date_year = resultSet.getInt("expiration_date_year");
                String cvv = resultSet.getString("CVV");

                CreditCard c = new CreditCard(card_id, customer_id, name, card_number, expiration_date_month, expiration_date_year, cvv);
                list_card.add(c);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des cartes : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }
        return list_card;
    }

    public static ArrayList<CreditCard> getCreditCardBasedOnCustomer(Connection connection, int cID) throws SQLException {
        String query = "SELECT * FROM Credit_Card WHERE customer_id = ?";

        ArrayList<CreditCard> list_card = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int card_id = resultSet.getInt("card_id");
                int customer_id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String card_number = resultSet.getString("card_number");
                int expiration_date_month = resultSet.getInt("expiration_date_month");
                int expiration_date_year = resultSet.getInt("expiration_date_year");
                String cvv = resultSet.getString("CVV");

                CreditCard c = new CreditCard(card_id, customer_id, name, card_number, expiration_date_month, expiration_date_year, cvv);
                list_card.add(c);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des cartes : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }
        return list_card;
    }

    public static ArrayList<CreditCard> getCreditCardBasedOnCardId(Connection connection, int ID) throws SQLException {
        String query = "SELECT * FROM Credit_Card WHERE card_id = ?";

        ArrayList<CreditCard> list_card = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int card_id = resultSet.getInt("card_id");
                int customer_id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String card_number = resultSet.getString("card_number");
                int expiration_date_month = resultSet.getInt("expiration_date_month");
                int expiration_date_year = resultSet.getInt("expiration_date_year");
                String cvv = resultSet.getString("CVV");

                CreditCard c = new CreditCard(card_id, customer_id, name, card_number, expiration_date_month, expiration_date_year, cvv);
                list_card.add(c);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des cartes : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }
        return list_card;
    }

    public static void insertCreditCard(Connection connection, CreditCard c) throws SQLException {
        String query = "INSERT INTO Credit_Card (customer_id, name, card_number, expiration_date_month, expiration_date_year, CVV) VALUES (?, ?, ?, ?, ?, ?)";
         
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, c.getCustomerId());
            preparedStatement.setString(2, c.getName());
            preparedStatement.setString(3, c.getCardNumber());
            preparedStatement.setInt(4, c.getExpiration_date_month());
            preparedStatement.setInt(5, c.getExpiration_date_year());
            preparedStatement.setString(6, c.getCVV());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) insérée(s).");

           

            // Récupérer la clé générée
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    c.setId(generatedId);
                    System.out.println("ID de la carte insérée : " + generatedId);
                } else {
                    System.out.println("Aucune clé générée n'a été récupérée.");
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Erreur lors de l'insertion d'un n-uplet ! \n" + //
                                "Erreur d'unicité : une carte avec ce numéro existe déjà.");
            //System.err.println("Erreur lors de l'insertion d'un n-uplet ! \n" + //
            //                    "Erreur d'unicité : une carte avec ce numéro existe déjà.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'insertion d'un n-uplet : " + e.getMessage());
            //System.err.println("Erreur lors de l'insertion d'un n-uplet : " + e.getMessage());
        }
    }

    //Quand utiliser vérifier que l'attribut de customer soit bien modifié au passage.
    public static void updateCreditCard(Connection connection, CreditCard c1, CreditCard c2) throws SQLException {
        String query = "UPDATE Credit_Card SET name = ?, card_number = ?, expiration_date_month = ?, expiration_date_year = ?, CVV = ? WHERE card_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, c2.getName());
            preparedStatement.setString(2, c2.getCardNumber());
            preparedStatement.setInt(3, c2.getExpiration_date_month());
            preparedStatement.setInt(4, c2.getExpiration_date_year());
            preparedStatement.setString(5, c2.getCVV());
            preparedStatement.setInt(6, c1.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            c1.update(c2);
            System.out.println(rowsAffected + " ligne(s) mise(s) à jour.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Erreur lors de la mise à jour du n-uplet ! \n" + //
                                "Erreur d'unicité : une carte avec ce numéro existe déjà.");
            //System.err.println("Erreur lors de la mise à jour du n-uplet ! \n" + //
            //                    "Erreur d'unicité : une carte avec ce numéro existe déjà.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise à jour du n-uplet : " + e.getMessage());
            //System.err.println("Erreur lors de la mise à jour du n-uplet : " + e.getMessage());
        }


    }

    public static void deleteCreditCard(Connection connection, CreditCard c) throws SQLException {
        String query = "DELETE FROM Credit_Card WHERE card_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) supprimée(s).");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la carte : " + e.getMessage());
            //System.err.println("Erreur lors de la suppression de la carte : " + e.getMessage());
        }
    }





//a verifier
    public static ArrayList<Review> getAllReviews(Connection connection) throws SQLException {
        String query = "SELECT * FROM Review";
        
        ArrayList<Review> list_review = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                int reviewId = resultSet.getInt("review_id");
                int orderId = resultSet.getInt("order_id");
                float rating = resultSet.getFloat("rating");
                String commentTitle = resultSet.getString("comment_title");
                String comment = resultSet.getString("comment");
                LocalDateTime date = resultSet.getTimestamp("created_at").toLocalDateTime();

                Review r = new Review(reviewId, orderId, rating, commentTitle, comment, date);
                list_review.add(r);

                System.out.println("ID: " + reviewId + ", Order ID: " + orderId + ", Rating: " + rating + 
                                   ", Title: " + commentTitle + ", Comment: " + comment);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des avis : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des avis : " + e.getMessage());
        }
        return list_review;
    }

    public static ArrayList<Review> getReviewsBasedOnOrder(Connection connection, int oID) throws SQLException {
        String query = "SELECT * FROM Review WHERE order_id = ?";

        ArrayList<Review> list_review = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, oID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int reviewId = resultSet.getInt("review_id");
                int orderId = resultSet.getInt("order_id");
                float rating = resultSet.getFloat("rating");
                String commentTitle = resultSet.getString("comment_title");
                String comment = resultSet.getString("comment");
                LocalDateTime date = resultSet.getTimestamp("created_at").toLocalDateTime();

                Review r = new Review(reviewId, orderId, rating, commentTitle, comment, date);
                list_review.add(r);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des cartes : " + e.getMessage());
            //System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }
        return list_review;
    }
//je me suis arrete la

    public static void insertReview(Connection connection, int orderId, double rating, String commentTitle, String comment) throws SQLException {
        String query = "INSERT INTO Review (order_id, rating, comment_title, comment) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setDouble(2, rating);
            preparedStatement.setString(3, commentTitle);
            preparedStatement.setString(4, comment);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) insérée(s).");
    
            // Récupérer la clé générée
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("ID de la revue insérée : " + generatedId);
                } else {
                    System.out.println("Aucune clé générée n'a été récupérée.");
                }
            }
            
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'insertion : " + e.getMessage());
            //System.err.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }

    public static void updateReview(Connection connection, int reviewId, double newRating, String newCommentTitle, String newComment) throws SQLException {
        String query = "UPDATE Review SET rating = ?, comment_title = ?, comment = ? WHERE review_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, newRating);
            preparedStatement.setString(2, newCommentTitle);
            preparedStatement.setString(3, newComment);
            preparedStatement.setInt(4, reviewId);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) mise(s) à jour.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise à jour de l'avis : " + e.getMessage());
            //System.err.println("Erreur lors de la mise à jour de l'avis : " + e.getMessage());
        }
    }

    public static void deleteReview(Connection connection, int reviewId) throws SQLException {
        String query = "DELETE FROM Review WHERE review_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reviewId);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) supprimée(s).");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de l'avis : " + e.getMessage());
            //System.err.println("Erreur lors de la suppression de l'avis : " + e.getMessage());
        }
    }



    public static void TestFunctionCreditCard(){
        DataBaseConnection dbCo = new DataBaseConnection();
        try (Connection connection = dbCo.getConnection()) {
            System.out.println("Connexion réussie !");
            
/*
            // Insérer un nouvel avis
            DataBaseOperations.insertReview(connection, 1, 4.5, "Super produit", "J'ai adoré ce produit !");
            
            // Mettre à jour un avis existant
            DataBaseOperations.updateReview(connection, 1, 5.0, "Produit excellent", "Je le recommande à tous !");
            
            // Supprimer un avis
            DataBaseOperations.deleteReview(connection, 1);
            
            // Afficher tous les avis
            DataBaseOperations.getReviews(connection);
*/ 
            ArrayList<CreditCard> l = DataBaseOperations.getAllCreditCards(connection);
            System.out.println(l);

            CreditCard c = l.get(0);
            for (CreditCard cc : l){
                System.out.println(c.equals(cc));
            }

            l = DataBaseOperations.getCreditCardBasedOnCustomer(connection,3);
            System.out.println(l);
            System.out.println();



            /*
            CreditCard c = new CreditCard(1, "TEST", "0000 0000 0000 0000" , 12, 2100,"666");
            System.out.println();

            System.out.println("id carte Test : "+ c.getId());
            DataBaseOperations.insertCreditCard(connection, c);
            System.out.println("id carte Test : "+ c.getId());
            System.out.println();

            CreditCard clone = c.clone();
            clone.setName("Rename Test");
            clone.setCardNumber("6666 6666 6666 6666");
            clone.setCVV("000");
            System.out.println(c);
            System.out.println(clone);
            System.out.println();

            DataBaseOperations.updateCreditCard(connection, c, clone);
            System.out.println(c);
            System.out.println(clone);
            System.out.println();

            int cid = c.getId();
            DataBaseOperations.deleteCreditCard(connection, c);
            l = DataBaseOperations.getCreditCardBasedOnCardId(connection, cid);
            System.out.println(l);
            */

        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
    public static void TestFunctionBrand(){
        DataBaseConnection dbCo = new DataBaseConnection();
        try (Connection connection = dbCo.getConnection()) {
            System.out.println("Connexion réussie !");
         
            ArrayList<Brand> l = DataBaseOperations.getAllBrand(connection);
            System.out.println(l);

            Brand b = l.get(0);
            for (Brand br : l){
                System.out.println(b.equals(br));
            }

            l = DataBaseOperations.getBrandBasedOnBrandId(connection,5);
            System.out.println(l);
            System.out.println();

            Brand new_b = new Brand("TEST");
            System.out.println("id brand test : " + new_b.getId());
            DataBaseOperations.insertBrand(connection, new_b);
            System.out.println("id brand test : " + new_b.getId());
            System.out.println();

            Brand clone = new_b.clone();
            clone.setName("Rename Test");

            System.out.println(new_b);
            System.out.println(clone);
            System.out.println();
            DataBaseOperations.updateBrand(connection, new_b, clone);
            System.out.println(new_b);
            System.out.println(clone);
            System.out.println();

            int bid = new_b.getId();
            DataBaseOperations.deleteBrand(connection, new_b);
            l = DataBaseOperations.getBrandBasedOnBrandId(connection, bid);
            System.out.println(l);

        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
    public static void TestFunctionProductCategory(){
        DataBaseConnection dbCo = new DataBaseConnection();
        try (Connection connection = dbCo.getConnection()) {
            System.out.println("Connexion réussie !");
         
            ArrayList<ProductCategory> l = DataBaseOperations.getAllProductCategories(connection);
            System.out.println(l);

            ProductCategory c = l.get(0);
            for (ProductCategory pc : l){
                System.out.println(c.equals(pc));
            }

            l = DataBaseOperations.getProductCategoryBasedOnProductCategoryId(connection, 3);
            System.out.println(l);
            System.out.println();

            ProductCategory pc = new ProductCategory("Test");
            System.out.println("id category test : " + pc.getId());
            DataBaseOperations.insertProductCategory(connection, pc);
            System.out.println("id category test : " + pc.getId());
            System.out.println();

            ProductCategory clone = pc.clone();
            clone.setName("Rename Test");

            System.out.println(pc);
            System.out.println(clone);
            System.out.println();
            DataBaseOperations.updateProductCategory(connection, pc, clone);
            System.out.println(pc);
            System.out.println(clone);
            System.out.println();

            int pcid = pc.getId();
            DataBaseOperations.deleteProductCategory(connection, pc);
            l = DataBaseOperations.getProductCategoryBasedOnProductCategoryId(connection, pcid);
            System.out.println(l);

        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TestFunctionProductCategory();
    }
    

}
