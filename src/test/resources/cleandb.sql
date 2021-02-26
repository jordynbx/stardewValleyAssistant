delete from item;
delete from user;
delete from user_favorites;
INSERT INTO item VALUES (1, 'blue jazz', 'crop'),(2, 'cauliflower', 'crop'),(3, 'garlic', 'crop'),(4, 'salmonberry', 'forage'),(5, 'milk', 'animal product'),(6, 'strawberry', 'crop');
INSERT INTO `user` VALUES (1,'user1','password'),(2,'user2','password'),(3,'user3','password');
INSERT INTO `user_favorites` VALUES (1,1,2),(2,1,5),(3,2,1),(4,3,5),(5,3,6),(6,1,1),(7,2,3);