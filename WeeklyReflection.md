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

I'm happy with my new direction and spent yesterday building out all the tables I needed for V1. Unfortunately, I then ran into a number of issues when trying to load up the data before each unit test because of the all the foreign keys and dependencies. I had originally plannign on all items, no matter what they were, living in the Item column and linking foreign keys back to item_id, but I realized that won't be possible because then in some other tables, I'll need two seperate columns to each have the same Item id column as the foreign key. My solution to this is to move recipes outside of the item column since they are groups of other items. However, I haven't yet figured out how I will deal with recipes that use other recipes as ingredients. Am planning on pulling in some others to help me troubleshoot that! 

