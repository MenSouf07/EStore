-- Active: 1728640950810@@127.0.0.1@3306
CREATE DATABASE EStore
    DEFAULT CHARACTER SET = 'utf8mb4';
USE EStore

CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    admin BOOLEAN NOT NULL,
    password VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(10) UNIQUE,
    billing_address VARCHAR(200) NOT NULL,
    shipping_address VARCHAR(200) NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Product_Category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Brand (
    brand_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,    
    category_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    brand_id INT NOT NULL,
    description TEXT,
    initial_price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(3, 2),
    stock_quantity INT DEFAULT 0,
    avg_rating DECIMAL(2, 1),
    review_count INT,
    added_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    images TEXT,
    CONSTRAINT check_discount CHECK (discount >= 0 AND discount <= 1),
    CONSTRAINT check_avg_rating CHECK (avg_rating >= 0 AND avg_rating <= 5),
    FOREIGN KEY (category_id) REFERENCES ProductCategory(category_id) ON DELETE CASCADE,
    FOREIGN KEY (brand_id) REFERENCES Brand(brand_id) ON DELETE CASCADE
);

CREATE TABLE Customer_Order (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Validated', 'Delivered') NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE CASCADE
);

CREATE TABLE Review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT UNIQUE NOT NULL,
    rating DECIMAL(2, 1),
    comment_title VARCHAR(100),
    comment TEXT,
    review_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES CustomerOrder(order_id) ON DELETE CASCADE,
    CONSTRAINT check_rating CHECK (rating >= 0 AND rating <= 5 AND rating * 2 = FLOOR(rating * 2)),
    CONSTRAINT check_comment_title CHECK (comment IS NULL OR comment_title IS NOT NULL)
);


CREATE TABLE Product_Return (
    return_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT UNIQUE NOT NULL,
    return_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    reason TEXT,
    FOREIGN KEY (order_id) REFERENCES CustomerOrder(order_id) ON DELETE CASCADE
);

CREATE TABLE Transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES CustomerOrder(order_id) ON DELETE CASCADE
);




/*

-- Active: 1728640950810@@127.0.0.1@3306
CREATE DATABASE EStore
    DEFAULT CHARACTER SET = 'utf8mb4';
USE EStore

CREATE TABLE Client (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    Admin BOOLEAN NOT NULL,
    mot_de_passe VARCHAR(100) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telephone VARCHAR(10) UNIQUE,
    date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    adresse_de_facturation VARCHAR(200) NOT NULL,
    adresse_de_livraison VARCHAR(200) NOT NULL
);

CREATE TABLE CategorieProduit (
    categorie_id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Marque (
    marque_id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Produit (
    produit_id INT PRIMARY KEY AUTO_INCREMENT,
    categorie_id INT NOT NULL,
    nom VARCHAR(100) NOT NULL,
    marque_id INT NOT NULL,
    description TEXT,
    prix_initial DECIMAL(10, 2) NOT NULL,
    reduction DECIMAL(3, 2),
    quantite_stock INT DEFAULT 0,
    note_moy DECIMAL(2, 1),
    nb_evaluation INT,
    date_ajout DATETIME DEFAULT CURRENT_TIMESTAMP,
    images TEXT,
    CONSTRAINT check_reduction CHECK (reduction >= 0 AND reduction <= 1),
    CONSTRAINT check_note_moy CHECK (note_moy >= 0 AND note_moy <= 5),
    FOREIGN KEY (categorie_id) REFERENCES CategorieProduit(categorie_id) ON DELETE CASCADE,
    FOREIGN KEY (marque_id) REFERENCES Marque(marque_id) ON DELETE CASCADE
);

CREATE TABLE Commande (
    commande_id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    produit_id INT NOT NULL,
    quantite INT NOT NULL,
    date_commande DATETIME DEFAULT CURRENT_TIMESTAMP,
    etat ENUM('En cours', 'Validée', 'Livrée') NOT NULL DEFAULT 'En cours',
    FOREIGN KEY (client_id) REFERENCES Client(client_id) ON DELETE CASCADE,
    FOREIGN KEY (produit_id) REFERENCES Produit(produit_id) ON DELETE CASCADE
);


CREATE TABLE Evaluation (
    evaluation_id INT PRIMARY KEY AUTO_INCREMENT,
    commande_id INT NOT NULL,
    note DECIMAL(2, 1),    
    titre_commentaire VARCHAR(100),
    commentaire TEXT,
    date_evaluation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (commande_id) REFERENCES Commande(commande_id) ON DELETE CASCADE,
    CONSTRAINT check_note CHECK (note >= 0 AND note <= 5),
    CONSTRAINT check_titre_commentaire CHECK (commentaire IS NULL OR titre_commentaire IS NOT NULL)
);

CREATE TABLE Retour (
    retour_id INT PRIMARY KEY AUTO_INCREMENT,
    commande_id INT NOT NULL,
    date_retour DATETIME DEFAULT CURRENT_TIMESTAMP,
    raison TEXT,
    FOREIGN KEY (commande_id) REFERENCES Commande(commande_id) ON DELETE CASCADE
);

CREATE TABLE Transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    commande_id INT NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    date_transaction DATETIME DEFAULT CURRENT_TIMESTAMP,
    statut VARCHAR(20) NOT NULL,
    FOREIGN KEY (commande_id) REFERENCES Commande(commande_id) ON DELETE CASCADE
);


*/