# Weekly Reflection 

# Week 1 

Tasks Completed:
- Chose a project

This week I spent time brainstorming an idea for a project. I had an idea that I previously planned to do, but after thinking it through realized it might not be ideal for this project. My original idea was for an American Mahjong hand analyzer that would help players during the game by analyzing their tiles and telling them the best hand. However, I realized a few things. 

First, American Mahjong is not very popular, and most people don't know how to play it. It might be difficult for classmates and potential employers to relate to this program for a complicated game they don't know how to play. Next, in order to play, you need a special card with the year's hands printed on it. It's illegal to copy or distribute these cards online. By creating an application that tells players hand ideas, I would be going against the creater's wishes by publishing their hands and allowing people to play who don't own a card. 

So with these things in mind, I went to work brainstorming a new project idea. I ended up deciding on an application related to Stardew Valley, a popular video game. I love this idea because it's a great accessible game that a lot of people play, and a resource like the Stardew Valley Assistant will be helpful to people needing quick information. Also, for my final PHP project last year I did a project related to Stardew Valley as well, and I love that my portfolio will be themed and show different ways I'm solving problems in a specific game. 

### Week 2

Tasks completed:
- Set up repository in Github

This week has been crazy - I'm currently working two jobs for the next few weeks (last few weeks of one, first few weeks of another) and have had minimal time for school work unfortunately. However, I did manage to get my project repository set up and linked to the Student repository in Github. 

### Week 3

Tasks completed:
- Finished problem statement 

Still working two jobs, but trying so hard to get caught up. This week I finished adding my problem statement to my README and spent time getting caught up on my classwork. 

### Week 4

Tasks completed:
- Created project plan and planning documents 
- Created wireframes in Figma
- Created user stories
- Updated reflections 

So happy to finally have time to devote to my project. So far this week I sketched out my screens on scratch paper and then built them out as wireframes in Figma. I also spent time setting up the rest of the design and tracking files I need in my project structure, as well as starting to think big picture about what I need to do and learn for the rest of my project.

### Week 5

Tasks completed:
- Implemented first Dao and tables

Overall I'm starting to feel better about this semester, but I'm stressed about my project. I feel like I bit off more than I can chew, and am worried about doing a good job with this project while still maintaining good database design. I think the amount of data I need to work with may be over-ambitious for the time frame of this course. I thought I would be able to use a Stardew Valley API to pull in the data about each item that I needed, not realizing that none exist! I am exploring options to make this project more maneagable for V1 while still staying true to my original vision.

### Week 6

Tasks completed:
- Added the following tables: crops, notes, user, user_favorites
- Implemented Project Lombok
- Created UserDao and NoteDao (will have to switch these out for GenericDao)

I believe I've been comparing my potential application to already completed applications I've seen, and I need to focus on what is possible in 15 weeks vs. what is pie-in-the-sky. I've decided to focus on a subset of information for my V1 project to make sure I have all the functionality hammered out and working properly, and then in the future I can add more data and functionality. V1 will include standard grown crops and information about those crops, such as the seed price, base sell price, season grown, and recipes that crops appear in. This will give me a great framework for the rest of the project once it's built out, but give me more time to focus on the functionality and not how I'm going ot map out and insert thousands of database rows. 

I'm happy with my new direction and spent yesterday building out all the tables I needed for V1. Unfortunately, I then ran into a number of issues when trying to load up the data before each unit test because of the all the foreign keys and dependencies. I had originally planning on all items, no matter what they were, living in the Item column and linking foreign keys back to item_id, but I realized that won't be possible because then in some other tables, I'll need two separate columns to each have the same Item id column as the foreign key. My solution to this is to move recipes outside of the item column since they are groups of other items. However, I haven't yet figured out how I will deal with recipes that use other recipes as ingredients. Am planning on pulling in some others to help me troubleshoot that! 

### Week 7

Tasks completed:
- Implemented GenericDao
- Added role table 
- Created unit tests for Item and Note
- Implemented authorization and login

This week I added the GenericDao and converted all of my unit tests to use it. I was a bit concerned about it at first, but after getting used to it it is so easy! And works so much better than having a separate DAO for every entity. 

I also implemented authorization in my project. I haven't added sign up yet, but I am fully able to log in with users created directly in my database. Yay!

### Week 8

Tasks completed:
 
- Implemented basic javamail structure
- Added JSPs and Crop entity 
- Added CSS
- Added all crop data to database
- Added ProcessItem class to reduce duplicate 
- Added insert note functionality

I got a lot done this week! Technically it was two weeks because of spring break, and the extra time really paid off. I added styling to my project using the Bootstrap theme Sketchy from Bootswatch which I love working with and fits really well for Stardew Valley. I added template jsps to hold my header, nav, footer, etc to reduce duplicate code.

I also gathered and assembled all of the crop data and loaded it into the database, and it's really cool to be able to look up all the different items! While I'm not planning on including fish/forage/etc in V1 of my project, if I have enough time I may add them to the Item table so that they come up when users search for them, and I can output a message that the website doesn't yet support those items. I think that would be a nice touch! 

One issue that came up this week as I set up my different JSPs and servlets that link out from the results page and then return to it is realizing that I had to duplicate the same code over and over. The item data is sent with a request, so every time a user inserted a note they would go back to the same results page, but all of the item data would be gone. I sovled this by creating a Javabean that holds a few methods that reconfigure the item data, and I can call those methods and then reset the attributes every time I need it to reappear. I was hoping to set the attributes in the javabean as well but I couldn't get it to work - I'm not sure if the functionality isn't supported or if I was doing it wrong. When I have more time for nice-to-have project improvements I hope to go back to this.

### Week 9

Tasks completed:
- Added update note and delete note functionality 
- Added error handling for update and delete
- Added sign up functionality 
- Added Role entity 

This week I added update and delete functionality to my project along with user sign ups, which is very exciting! I've gotten a few weird error with logging in that have worried me a little, but they seem to resolve themselves if I refresh the page. It seems to come from logging in and out and back in too quickly when I'm testing different users. I'll need to keep an eye on that. 
I'm very excited about the update and delete functionality. Getting it working was very satisfying, although it took a bit of thinking on my part to figure out every way a user might try to access those pages and make sure they got appropriate error messages instead of null pointer exceptions. I'm hoping I caught them all, but I'm sure more will come up with time.  