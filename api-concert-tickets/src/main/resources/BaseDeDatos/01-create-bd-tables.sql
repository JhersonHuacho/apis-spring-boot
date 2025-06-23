
-- ============================================
-- Script de creación de base de datos y tablas
-- Proyecto: Tienda en línea de boletos de conciertos
-- Estilo: snake_case (convención recomendada para MariaDB)
-- Fecha: Generado automáticamente
-- ============================================

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS db_concert_tickets;
USE db_concert_tickets;

-- Tabla: customer
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(200) NOT NULL UNIQUE,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    address VARCHAR(300) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    status BIT DEFAULT 1 COMMENT 'Estado del cliente (activo/inactivo)',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP
);

-- Tabla: genre
CREATE TABLE genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    status BIT DEFAULT 1 COMMENT 'Estado del género',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP
);

-- Tabla: concert
CREATE TABLE concerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    place VARCHAR(100) NOT NULL,
    genre_id INT NOT NULL,
    date_event DATETIME DEFAULT CURRENT_TIMESTAMP(),
    image_url VARCHAR(300),
    finalized BIT DEFAULT 0 COMMENT 'Indica si el evento fue finalizado',
    status BIT DEFAULT 1,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

-- Tabla: ticket_type
CREATE TABLE ticket_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    concert_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    status ENUM('DISPONIBLE', 'RESERVADO', 'VENDIDO') DEFAULT 'DISPONIBLE',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (concert_id) REFERENCES concerts(id)
);

-- Tabla: reservation
CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    ticket_type_id INT NOT NULL,
    quantity INT NOT NULL,
    reserved_at DATETIME DEFAULT CURRENT_TIMESTAMP(),
    expires_at DATETIME NOT NULL,
    status ENUM('RESERVED', 'CANCELLED', 'EXPIRED') DEFAULT 'RESERVED',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (ticket_type_id) REFERENCES ticket_types(id)
);

-- Tabla: sale
CREATE TABLE sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    sale_date DATETIME DEFAULT CURRENT_TIMESTAMP(),
    total DECIMAL(10,2) NOT NULL,
    operation_number VARCHAR(20) UNIQUE,
    status ENUM('PENDING','PAID', 'CANCELLED') DEFAULT 'PENDING',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Tabla: sale_detail
CREATE TABLE sale_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id INT NOT NULL,
    ticket_type_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    status BIT DEFAULT 1,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (ticket_type_id) REFERENCES ticket_types(id)
);

-- Tabla: payment
CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id INT NOT NULL,
    payment_date DATETIME DEFAULT CURRENT_TIMESTAMP(),
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    payment_status ENUM('SUCCESS', 'FAILED', 'PENDING') DEFAULT 'PENDING',
    transaction_code VARCHAR(50),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (sale_id) REFERENCES sales(id)
);
