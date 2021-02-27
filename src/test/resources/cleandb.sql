delete from notes;
delete from user_favorites;
delete from recipe_ingredients;
delete from recipes;
delete from crops;
delete from user;
delete from item;
delete from item_type;

INSERT INTO `item_type` VALUES (3,'animal product'),(1,'crop'),(4,'fish'),(2,'forage'),(5,'recipe');
INSERT INTO item VALUES (1, 'blue jazz', 'crop'),(2, 'cauliflower', 'crop'),(3, 'garlic', 'crop'),(4, 'salmonberry', 'forage'),(5, 'milk', 'animal product'),(6, 'strawberry', 'crop');
INSERT INTO `user` VALUES (1,'user1','password'),(2,'user2','password'),(3,'user3','password');
INSERT INTO `user_favorites` VALUES (1,1,2),(2,1,5),(3,2,1),(4,3,5),(5,3,6),(6,1,1),(7,2,3);
INSERT INTO `crops` VALUES (1,3,'Spring',80,175,1);
INSERT INTO `recipes` VALUES (1,8,'Pam (Mail - 3+)');
INSERT INTO `recipe_ingredients` VALUES (1,1,2);
INSERT INTO `user_favorites` VALUES (1,1,1),(2,1,6),(3,2,8),(4,3,2),(5,1,3),(6,3,1);
INSERT INTO `notes` VALUES (1,2,2,'Need 2 more gold for bundle'),(2,1,1,'lucky lunch'),(3,2,6,'can\'t buy seeds til spring 13');