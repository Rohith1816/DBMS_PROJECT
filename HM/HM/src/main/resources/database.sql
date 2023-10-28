-- Create database--
DROP DATABASE IF EXISTS Hostel;
CREATE DATABASE Hostel;
USE Hostel;

-- Create table--
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE HOSTEL(
  HostelId INT NOT NULL AUTO_INCREMENT,
  HostelName VARCHAR(255) NOT NULL UNIQUE,
  address VARCHAR(255) NOT NULL,
  ContactNumber VARCHAR(15) NOT NULL,
  Capacity INT,
  PRIMARY KEY (HostelId)
);

CREATE TABLE MESS(
  MessId INT NOT NULL AUTO_INCREMENT,
  MessName VARCHAR(255) NOT NULL UNIQUE,
  MessType VARCHAR(255) NOT NULL,
  ContactNumber VARCHAR(15) NOT NULL,
  Capacity INT,
  PRIMARY KEY (MessId),
);

CREATE TABLE HostelApplications(
    applicationId INT NOT NULL AUTO_INCREMENT,
    userId INT NOT NULL,
    hostelId INT NOT NULL,
    is_active BOOLEAN NOT NULL,
    appliedDate DATE NOT NULL,
    closingDate DATE ,
    PRIMARY KEY (applicationId),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (hostelId) REFERENCES hostel(HostelId)
);

CREATE TABLE MessApplications(
    applicationId INT NOT NULL AUTO_INCREMENT,
    int userId INT NOT NULL FOREIGN KEY(users.id),
    int messId INT NOT NULL,
    is_active BOOLEAN NOT NULL,
    appliedDate DATE NOT NULL,
    closingDate DATE,
    PRIMARY KEY (applicationId),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (messId) REFERENCES mess(MessId)
)

-- Queries
-- Insert data into users table--
INSERT INTO users (username, first_name, last_name, email, password) VALUES (?,?,?,?,?);

-- Insert data into hostel table--
INSERT INTO hostel (HostelName,Address,ContactNumber,Capacity) values ("HOSTEL-1","Near Hyderabad Gate","9009897560",250);
INSERT INTO hostel (HostelName,Address,ContactNumber,Capacity) values ("HOSTEL-2","Near LC","9009890960",250);
INSERT INTO hostel (HostelName,Address,ContactNumber,Capacity) values ("HOSTEL-3","Near Hyderabad Gate","9000097560",250);
INSERT INTO hostel (HostelName,Address,ContactNumber,Capacity) values ("HOSTEL-4","Near LC","9009897650",250);
INSERT INTO hostel (HostelName,Address,ContactNumber,Capacity) values ("HOSTEL-5","Near Hyderabad Gate","9009897560",250);

-- Insert data into mess table--
INSERT INTO mess(MessName,MessType,ContactNumber,Capacity) values ("Mess-1","Veg","9090903453",100);
INSERT INTO mess(MessName,MessType,ContactNumber,Capacity) values ("Mess-2","Veg","9090903453",100);
INSERT INTO mess(MessName,MessType,ContactNumber,Capacity) values ("Mess-3","Non-Veg","9090903453",100);
INSERT INTO mess(MessName,MessType,ContactNumber,Capacity) values ("Mess-4","Mixed","9090903453",100);
INSERT INTO mess(MessName,MessType,ContactNumber,Capacity) values ("Mess-5","Satvik","9090903453",100);


