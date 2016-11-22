#Author: Kun Su
DROP DATABASE IF EXISTS cs160test;
CREATE DATABASE cs160test;
USE cs160test;

DROP TABLE IF EXISTS AnimalBridge_users;
CREATE TABLE AnimalBridge_users (
    user_ID INT(11) NOT NULL AUTO_INCREMENT,
    user_Name VARCHAR(100) NOT NULL,
    user_Email VARCHAR(100) NOT NULL UNIQUE,
    user_Pass VARCHAR(100) NOT NULL,
    user_ComfirmStatus ENUM('Y', 'N') NOT NULL DEFAULT 'N',
    user_RegisteredDate DATETIME NOT NULL,
    tokenCode VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_ID , user_Name)
);

DROP TABLE IF EXISTS AnimalBridge_Animals;
CREATE TABLE AnimalBridge_Animals (
    Animals_ID INT(11) NOT NULL AUTO_INCREMENT,
    Animals_Categories ENUM('Dog', 'Cat', 'Other') NOT NULL,
    Animals_Name VARCHAR(100) NOT NULL,
    Animals_Ago VARCHAR(100) NOT NULL,
    Animals_Breeds VARCHAR(100) NOT NULL,
    Animals_Price VARCHAR(100) NOT NULL,
    Animals_Address VARCHAR(100) NOT NULL,
    Animals_Color VARCHAR(100) NOT NULL,
    Animals_Description LONGTEXT NOT NULL,
    Animals_Image LONGBLOB NOT NULL,
    Animals_Size VARCHAR(100) NOT NULL,
    Animals_Gender ENUM('Female', 'male', 'Other') NOT NULL,
    Animals_OwnerID INT(11) NOT NULL,
    Animals_OwnerName VARCHAR(100) NOT NULL,
    FOREIGN KEY (Animals_OwnerID , Animals_OwnerName)
        REFERENCES AnimalBridge_users (user_ID , user_Name)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    PRIMARY KEY (Animals_ID)
);

DROP TABLE IF EXISTS AnimalBridge_Posting;
CREATE TABLE AnimalBridge_Posting (
    Posting_ID INT(11) NOT NULL AUTO_INCREMENT,
    Posting_Categories ENUM('Emergency', 'Lost', 'AdpotionOffer', 'AdpotionRequest', 'RecentNews', 'ShelterPromotion') NOT NULL,
    Posting_Priority INT(2) NOT NULL,
    Posting_Title VARCHAR(100) NOT NULL,
    Posting_Address VARCHAR(100) NOT NULL,
    Posting_Description LONGTEXT NOT NULL,
    Posting_Date DATE NOT NULL,
    Posting_StartingTime TIME NOT NULL,
    Posting_EndingTIme TIME NOT NULL,
    Posting_Image LONGBLOB NOT NULL,
    Posting_Price VARCHAR(100),
    Posting_ContactEmail VARCHAR(100),
    Posting_OwnerID INT(11) NOT NULL,
    Posting_OwnerName VARCHAR(100) NOT NULL,
    FOREIGN KEY (Posting_OwnerID , Posting_OwnerName)
        REFERENCES AnimalBridge_users (user_ID , user_Name)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    PRIMARY KEY (Posting_ID)
);

DROP TABLE IF EXISTS AnimalBridge_AboutUs;
CREATE TABLE AnimalBridge_AboutUs (
    AboutUs_ID INT(11) NOT NULL AUTO_INCREMENT,
    AboutUs_Title VARCHAR(100) NOT NULL,
    AboutUs_Description LONGTEXT NOT NULL,
    AboutUs_Date DATE NOT NULL,
    AboutUs_Image LONGBLOB NOT NULL,
    PRIMARY KEY (AboutUs_ID)
);

DROP TABLE IF EXISTS AnimalBridge_ContactUs;
CREATE TABLE AnimalBridge_ContactUs (
    ContactUs_ID INT(11) NOT NULL AUTO_INCREMENT,
    ContactUs_Title VARCHAR(100) NOT NULL,
    ContactUs_Description LONGTEXT NOT NULL,
    ContactUs_Date DATE NOT NULL,
    ContactUs_Image LONGBLOB NOT NULL,
    ContactUs_ContactEmail VARCHAR(100),
    PRIMARY KEY (ContactUs_ID)
);

DROP TABLE IF EXISTS AnimalBridge_EmergencyContact;
CREATE TABLE AnimalBridge_EmergencyContact (
    EmergencyContact_ID INT(11) NOT NULL AUTO_INCREMENT,
    EmergencyContact_Title VARCHAR(100) NOT NULL,
    EmergencyContact_Description LONGTEXT NOT NULL,
    EmergencyContact_Date DATE NOT NULL,
    EmergencyContact_ZipCode INT(6) NOT NULL,
    EmergencyContact_Image LONGBLOB NOT NULL,
    EmergencyContact_ContactEmail VARCHAR(100),
    EmergencyContact_OwnerID INT(11) NOT NULL,
    EmergencyContact_OwnerName VARCHAR(100) NOT NULL,
    FOREIGN KEY (EmergencyContact_OwnerID , EmergencyContact_OwnerName)
        REFERENCES AnimalBridge_users (user_ID , user_Name)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    PRIMARY KEY (EmergencyContact_ID)
);

DROP TABLE IF EXISTS AnimalBridge_HomePage;
CREATE TABLE AnimalBridge_HomePage (
    HomePage_ID INT(11) NOT NULL AUTO_INCREMENT,
    HomePage_Title VARCHAR(100) NOT NULL,
    HomePage_Description LONGTEXT NOT NULL,
    HomePage_Date DATE NOT NULL,
    HomePage_Image LONGBLOB NOT NULL,
    HomePage_RecentNews VARCHAR(100) NOT NULL,
    PRIMARY KEY (HomePage_ID)
);