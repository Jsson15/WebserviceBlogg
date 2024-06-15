-- Inserting Liverpool-related addresses
INSERT INTO Address (street, postal_code, city, country)
VALUES
    ('Anfield Road 96', 'L4 0TH', 'Liverpool', 'United Kingdom'),
    ('Penny Lane 23', 'L18 1DG', 'Liverpool', 'United Kingdom'),
    ('Matthew Street 10', 'L2 6RE', 'Liverpool', 'United Kingdom');

-- Inserting Liverpool-related users
INSERT INTO User (user_name, first_name, last_name, address_id, email, phone, member_type)
VALUES
    ('john', 'John', 'Lennon', 1, 'john.lennon@beatles.com', '071 123 45 67', 'premium'),
    ('paul', 'Paul', 'McCartney', 2, 'paul.mccartney@beatles.com', '070 234 56 78', 'premium'),
    ('steven', 'Steven', 'Gerrard', 3, 'steven.gerrard@lfc.com', '070 345 67 89', 'enhanced'),
    ('kenny', 'Kenny', 'Dalglish', 1, 'kenny.dalglish@lfc.com', '073 456 78 90', 'standard'),
    ('jamie', 'Jamie', 'Carragher', 3, 'jamie.carragher@lfc.com', '073 567 89 01', 'standard');

-- Inserting Liverpool-related blog posts
INSERT INTO Post (post_title, post_text, date_for_post, user_id)
VALUES
    ('A Day in the Life',
     'I read the news today, oh boy. About a lucky man who made the grade. Join me as I share my thoughts on the latest happenings in Liverpool.',
     '2023-06-01', 1),
    ('Penny Lane',
     'Penny Lane is in my ears and in my eyes. A nostalgic look at one of Liverpool’s most iconic streets.',
     '2023-06-02', 2),
    ('Captain Fantastic',
     'Reflecting on my career with Liverpool FC and what it means to be a Scouser through and through.',
     '2023-06-03', 3),
    ('King Kenny\'s Corner',
     'Sharing my journey from player to manager and my undying love for Liverpool FC.',
     '2023-06-04', 4),
    ('Defending the Reds',
     'Talking about life in the defense line at Liverpool FC and my passion for the club.',
     '2023-06-05', 5);
