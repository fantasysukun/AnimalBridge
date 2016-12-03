use cs160test;


insert into animalbridge_users (user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode)
values ("Kun Su", "kun.su@sjsu.edu", "123456", "Y", "2016-12-1", "0001");

insert into animalbridge_users (user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode)
values ("Yitao Zhao", "Yitao.Zhao@sjsu.edu", "123456", "Y", "2016-12-1", "0001");

insert into animalbridge_users (user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode)
values ("Peilu Liu", "Peilu.Liu@sjsu.edu", "123456", "Y", "2016-12-1", "0001");

insert into animalbridge_users (user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode)
values ("Marco Kuang", "Marco.Kuang@sjsu.edu", "123456", "Y", "2016-12-1", "0001");

insert into animalbridge_users (user_Name, user_Email, user_Pass, user_ComfirmStatus, user_RegisteredDate, tokenCode)
values ("Neslon Liang", "Neslon.Liang@sjsu.edu", "123456", "Y", "2016-12-1", "0001");

SELECT * FROM cs160test.animalbridge_users;


insert into animalbridge_aboutus (AboutUs_Title, AboutUs_Description, AboutUs_Date, AboutUs_Image)
values ("TestingTitle1", "TestingDescription1", "2016-12-1", null);

SELECT * FROM cs160test.animalbridge_aboutus;


insert into animalbridge_posting (Posting_Categories, Posting_Priority, Posting_Title, Posting_Address, 
Posting_Description, Posting_Date, Posting_StartingTime, Posting_EndingTIme, 
Posting_Image, Posting_Price, Posting_ContactEmail, Posting_OwnerID, Posting_OwnerName)
values ('Lost', 5, "TestingTitle1", "TestingAddres1",
 "TestingDescription1", "2016-12-1", "00:00:00", "12:00:00",
 null, "$100", "TestingEmail1", 1, "Kun Su");
 
insert into animalbridge_posting (Posting_Categories, Posting_Priority, Posting_Title, Posting_Address, 
Posting_Description, Posting_Date, Posting_StartingTime, Posting_EndingTIme, 
Posting_Image, Posting_Price, Posting_ContactEmail, Posting_OwnerID, Posting_OwnerName)
values ('Emergency', 6, "TestingTitle2", "TestingAddres2",
 "TestingDescription2", "2016-12-1", "00:00:00", "12:00:00",
 null, "$100", "TestingEmail2", 2, "Yitao Zhao");
 
insert into animalbridge_posting (Posting_Categories, Posting_Priority, Posting_Title, Posting_Address, 
Posting_Description, Posting_Date, Posting_StartingTime, Posting_EndingTIme, 
Posting_Image, Posting_Price, Posting_ContactEmail, Posting_OwnerID, Posting_OwnerName)
values ('AdpotionOffer', 4, "TestingTitle3", "TestingAddres3",
 "TestingDescription3", "2016-12-1", "00:00:00", "12:00:00",
 null, "$100", "TestingEmail3", 3, "Peilu Liu");
 
insert into animalbridge_posting (Posting_Categories, Posting_Priority, Posting_Title, Posting_Address, 
Posting_Description, Posting_Date, Posting_StartingTime, Posting_EndingTIme, 
Posting_Image, Posting_Price, Posting_ContactEmail, Posting_OwnerID, Posting_OwnerName)
values ('AdpotionRequest', 3, "TestingTitle4", "TestingAddres4",
 "TestingDescription4", "2016-12-1", "00:00:00", "12:00:00",
 null, "$100", "TestingEmail1", 4, "Marco Kuang");
 
insert into animalbridge_posting (Posting_Categories, Posting_Priority, Posting_Title, Posting_Address, 
Posting_Description, Posting_Date, Posting_StartingTime, Posting_EndingTIme, 
Posting_Image, Posting_Price, Posting_ContactEmail, Posting_OwnerID, Posting_OwnerName)
values ('RecentNews', 1, "TestingTitle5", "TestingAddres5",
 "TestingDescription5", "2016-12-1", "00:00:00", "12:00:00",
 null, "$100", "TestingEmail5", 5, "Neslon Liang");
 
insert into animalbridge_posting (Posting_Categories, Posting_Priority, Posting_Title, Posting_Address, 
Posting_Description, Posting_Date, Posting_StartingTime, Posting_EndingTIme, 
Posting_Image, Posting_Price, Posting_ContactEmail, Posting_OwnerID, Posting_OwnerName)
values ('ShelterPromotion', 2, "TestingTitle6", "TestingAddres6",
 "TestingDescription6", "2016-12-1", "00:00:00", "12:00:00",
 null, "$100", "TestingEmail6", 1, "Kun Su");
 
SELECT * FROM cs160test.animalbridge_posting;


insert into animalbridge_homepage (HomePage_Title, HomePage_Description, HomePage_Date, HomePage_Image, HomePage_RecentNews)
values ("TestingTitle1", "TestingDescription1", "2016-12-1", null, "TestingRecentNews1");
 
insert into animalbridge_homepage (HomePage_Title, HomePage_Description, HomePage_Date, HomePage_Image, HomePage_RecentNews)
values ("TestingTitle2", "TestingDescription2", "2016-12-1", null, "TestingRecentNews2");

insert into animalbridge_homepage (HomePage_Title, HomePage_Description, HomePage_Date, HomePage_Image, HomePage_RecentNews)
values ("TestingTitle3", "TestingDescription3", "2016-12-1", null, "TestingRecentNews3");

insert into animalbridge_homepage (HomePage_Title, HomePage_Description, HomePage_Date, HomePage_Image, HomePage_RecentNews)
values ("TestingTitle4", "TestingDescription4", "2016-12-1", null, "TestingRecentNews4");

insert into animalbridge_homepage (HomePage_Title, HomePage_Description, HomePage_Date, HomePage_Image, HomePage_RecentNews)
values ("TestingTitle5", "TestingDescription5", "2016-12-1", null, "TestingRecentNews5");

SELECT * FROM cs160test.animalbridge_homepage;


insert into animalbridge_emergencycontact (EmergencyContact_Title, EmergencyContact_Description, EmergencyContact_Date, 
EmergencyContact_ZipCode, EmergencyContact_Image, EmergencyContact_ContactEmail, EmergencyContact_OwnerID, EmergencyContact_OwnerName)
values ("TestingTitle1", "TestingDescription1", "2016-12-1",
'95132', null, "TestingEmail1", 1, "Kun Su");

insert into animalbridge_emergencycontact (EmergencyContact_Title, EmergencyContact_Description, EmergencyContact_Date, 
EmergencyContact_ZipCode, EmergencyContact_Image, EmergencyContact_ContactEmail, EmergencyContact_OwnerID, EmergencyContact_OwnerName)
values ("TestingTitle2", "TestingDescription2", "2016-12-1",
'95132', null, "TestingEmail2", 2, "Yitao Zhao");

insert into animalbridge_emergencycontact (EmergencyContact_Title, EmergencyContact_Description, EmergencyContact_Date, 
EmergencyContact_ZipCode, EmergencyContact_Image, EmergencyContact_ContactEmail, EmergencyContact_OwnerID, EmergencyContact_OwnerName)
values ("TestingTitle3", "TestingDescription3", "2016-12-1",
'95132', null, "TestingEmail3", 3, "Peilu Liu");

insert into animalbridge_emergencycontact (EmergencyContact_Title, EmergencyContact_Description, EmergencyContact_Date, 
EmergencyContact_ZipCode, EmergencyContact_Image, EmergencyContact_ContactEmail, EmergencyContact_OwnerID, EmergencyContact_OwnerName)
values ("TestingTitle4", "TestingDescription4", "2016-12-1",
'95132', null, "TestingEmail4", 4, "Marco Kuang");

insert into animalbridge_emergencycontact (EmergencyContact_Title, EmergencyContact_Description, EmergencyContact_Date, 
EmergencyContact_ZipCode, EmergencyContact_Image, EmergencyContact_ContactEmail, EmergencyContact_OwnerID, EmergencyContact_OwnerName)
values ("TestingTitle5", "TestingDescription5", "2016-12-1",
'95132', null, "TestingEmail5", 5, "Neslon Liang");

SELECT * FROM cs160test.animalbridge_emergencycontact;


insert into animalbridge_contactus (ContactUs_Title, ContactUs_Description, ContactUs_Date, ContactUs_Image, ContactUs_ContactEmail)
values ("TestingTitle1", "TestingDescription1", "2016-12-1", null, "TestingEmail1");

SELECT * FROM cs160test.animalbridge_contactus;


insert into animalbridge_animals (Animals_Categories, Animals_Name, Animals_Ago, Animals_Breeds, Animals_Price, Animals_Address,
 Animals_Color, Animals_Description, Animals_Image, Animals_Size, Animals_Gender, Animals_OwnerID, Animals_OwnerName)
values ('Dog', "TestingName1", "TestingAgo1", "TestingBreed1", "$100", "TestingAddres1",
"TestingColor1", "TestingDescription1", null, "TestingSize1", 'Female', 1, "Kun Su");

insert into animalbridge_animals (Animals_Categories, Animals_Name, Animals_Ago, Animals_Breeds, Animals_Price, Animals_Address,
 Animals_Color, Animals_Description, Animals_Image, Animals_Size, Animals_Gender, Animals_OwnerID, Animals_OwnerName)
values ('Cat', "TestingName2", "TestingAgo2", "TestingBreed2", "$100", "TestingAddres2",
"TestingColor2", "TestingDescription2", null, "TestingSize2", 'Male', 2, "Yitao Zhao");
 
 insert into animalbridge_animals (Animals_Categories, Animals_Name, Animals_Ago, Animals_Breeds, Animals_Price, Animals_Address,
 Animals_Color, Animals_Description, Animals_Image, Animals_Size, Animals_Gender, Animals_OwnerID, Animals_OwnerName)
values ('Other', "TestingName3", "TestingAgo3", "TestingBreed3", "$100", "TestingAddres1",
"TestingColor3", "TestingDescription3", null, "TestingSize3", 'Other', 3, "Peilu Liu");

insert into animalbridge_animals (Animals_Categories, Animals_Name, Animals_Ago, Animals_Breeds, Animals_Price, Animals_Address,
 Animals_Color, Animals_Description, Animals_Image, Animals_Size, Animals_Gender, Animals_OwnerID, Animals_OwnerName)
values ('Cat', "TestingName4", "TestingAgo4", "TestingBreed4", "$100", "TestingAddres4",
"TestingColor4", "TestingDescription4", null, "TestingSize4", 'Male', 4, "Marco Kuang");
 
 insert into animalbridge_animals (Animals_Categories, Animals_Name, Animals_Ago, Animals_Breeds, Animals_Price, Animals_Address,
 Animals_Color, Animals_Description, Animals_Image, Animals_Size, Animals_Gender, Animals_OwnerID, Animals_OwnerName)
values ('Other', "TestingName5", "TestingAgo5", "TestingBreed5", "$100", "TestingAddres5",
"TestingColor5", "TestingDescription5", null, "TestingSize5", 'Other', 5, "Neslon Liang");

SELECT * FROM cs160test.animalbridge_animals;

