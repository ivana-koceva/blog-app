INSERT INTO BLOG_POST (ID, TITLE, TEXT, CREATED_BY) VALUES (1, 'One', 'Blog About Music', 'ivana_t2');
INSERT INTO BLOG_POST (ID, TITLE, TEXT, CREATED_BY) VALUES (2, 'Two', 'Blog Post About Programming', 'ivana_t3');
INSERT INTO BLOG_POST (ID, TITLE, TEXT, CREATED_BY) VALUES (3, 'Three', 'Another Music Blog', 'ivana_t2');
INSERT INTO BLOG_POST (ID, TITLE, TEXT, CREATED_BY) VALUES (4, 'Four', 'Programming Languages Blog Post', 'ivana_t3');

INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (1, 'Songs', '2024-08-07T14:43:47.830+00:00', 'ivana_t3');
INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (2, 'Music', '2024-08-07T14:43:47.830+00:00', 'ivana_t2');
INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (3, 'Album', '2024-08-07T14:43:47.830+00:00', 'ivana_t3');
INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (4, 'Guitar', '2024-08-07T14:43:47.830+00:00', 'ivana_t2');

INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (5, 'Java', '2024-08-07T14:43:47.830+00:00', 'ivana_t3');
INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (6, 'C#', '2024-08-07T14:43:47.830+00:00', 'ivana_t3');
INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (7, 'C++', '2024-08-07T14:43:47.830+00:00', 'ivana_t2');
INSERT INTO TAG (ID, NAME, TIME_CREATED, CREATED_BY) VALUES (8, 'Python', '2024-08-07T14:43:47.830+00:00', 'ivana_t2');

INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (1, 1);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (1, 2);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (1, 3);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (1, 4);

INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (2, 8);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (2, 5);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (2, 7);

INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (3, 3);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (3, 4);

INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (4, 5);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (4, 6);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (4, 7);
INSERT INTO BLOG_POST_TAGS (BLOG_POSTS_ID, TAGS_ID) VALUES (4, 8);