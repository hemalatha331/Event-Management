use eventlydb;
-- events table
CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(50),
    location VARCHAR(100),
    date DATE,
    description TEXT
);

-- rsvp table
CREATE TABLE rsvp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    user_name VARCHAR(100),
    attendees INT,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);
CREATE TABLE Participants (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL,
    FullName VARCHAR(100) NOT NULL
);
INSERT INTO events (name, type, location, date, description) VALUES
('AI Summit', 'Conference', 'London', '2025-02-20', 'AI conference with workshops'),
('Sarah & Tom’s Wedding', 'Wedding', 'Manchester', '2025-03-15', 'Private family wedding'),
('Java Programming Workshop', 'Workshop', 'Coventry', '2025-01-28', 'Learn Java Basics'),
('New Year Bash', 'Party', 'Birmingham', '2025-12-31', 'Celebrate the New Year'),
('Tech Innovations', 'Conference', 'Edinburgh', '2025-06-10', 'Showcase of tech trends'),
('Data Science Bootcamp', 'Workshop', 'London', '2025-03-05', 'Intro to data science techniques'),
('Emily & Jack’s Wedding', 'Wedding', 'Leeds', '2025-04-18', 'Garden wedding celebration'),
('Graduation Party', 'Party', 'Cardiff', '2025-07-01', 'Celebrate with graduates'),
('Healthcare Tech', 'Conference', 'Glasgow', '2025-11-02', 'Innovations in healthcare tech'),
('Cybersecurity Basics', 'Workshop', 'Liverpool', '2025-08-15', 'Intro to online safety');

INSERT INTO rsvp (event_id, user_name, attendees) VALUES
(1, 'Alice', 2),
(3, 'Bob', 1),
(5, 'Charlie', 3),
(7, 'Diana', 2);
INSERT INTO Participants (Username, Password, FullName) VALUES
('alice123', 'alicepass', 'Alice Johnson'),
('bob456', 'bobpass', 'Bob Smith'),
('charlie789', 'charliepass', 'Charlie Brown');

INSERT INTO events (name, type, location, date, description) VALUES
-- Conferences
('AI Innovations Expo', 'Conference', 'London', '2025-02-20', 'Explore latest trends in AI and ML.'),
('Global Tech Forum', 'Conference', 'New York', '2025-04-12', 'Technology leaders sharing innovations.'),
('Sustainability Summit', 'Conference', 'Berlin', '2025-05-10', 'Discussions on sustainable development.'),
('Healthcare 2025', 'Conference', 'Glasgow', '2025-11-02', 'Innovations in healthcare tech.'),
('EduTech World', 'Conference', 'Paris', '2025-06-18', 'Educational technologies for the future.'),
('CyberTech Europe', 'Conference', 'Amsterdam', '2025-08-22', 'Cybersecurity and privacy topics.'),
('Quantum Future', 'Conference', 'Zurich', '2025-07-05', 'Quantum computing symposium.'),
('Climate Action Now', 'Conference', 'Oslo', '2025-09-30', 'Climate change and policy summit.'),
('Smart Cities Forum', 'Conference', 'Tokyo', '2025-10-15', 'Urban technology and innovation.'),
('Women in Tech', 'Conference', 'San Francisco', '2025-03-11', 'Empowering women in STEM fields.'),

-- Workshops
('Java Bootcamp', 'Workshop', 'Coventry', '2025-01-28', 'Hands-on Java programming basics.'),
('Data Science 101', 'Workshop', 'London', '2025-03-05', 'Intro to data science techniques.'),
('Cybersecurity Basics', 'Workshop', 'Liverpool', '2025-08-15', 'Intro to online safety.'),
('UI/UX Design Sprint', 'Workshop', 'Manchester', '2025-07-20', 'Design interactive experiences.'),
('Agile Development', 'Workshop', 'Leeds', '2025-04-07', 'Agile methodologies workshop.'),
('Python for Beginners', 'Workshop', 'Sheffield', '2025-02-22', 'Basic Python coding workshop.'),
('Cloud Fundamentals', 'Workshop', 'Dublin', '2025-09-05', 'Intro to cloud computing.'),
('Digital Marketing 101', 'Workshop', 'Bristol', '2025-10-28', 'Basics of digital marketing.'),
('SQL Masterclass', 'Workshop', 'Nottingham', '2025-05-16', 'Deep dive into SQL queries.'),
('Mobile App Dev', 'Workshop', 'Brighton', '2025-06-12', 'Build your first Android app.'),

-- Weddings
('Sarah & Tom’s Wedding', 'Wedding', 'Manchester', '2025-03-15', 'Private family wedding.'),
('Emily & Jack’s Wedding', 'Wedding', 'Leeds', '2025-04-18', 'Garden wedding celebration.'),
('Olivia & Ben', 'Wedding', 'Birmingham', '2025-06-25', 'Traditional English wedding.'),
('Liam & Emma', 'Wedding', 'Cardiff', '2025-08-10', 'Rustic outdoor wedding.'),
('Chloe & Ethan', 'Wedding', 'Glasgow', '2025-10-02', 'Coastal ceremony and reception.'),
('Noah & Lily', 'Wedding', 'York', '2025-11-18', 'Historic castle wedding.'),
('Ava & James', 'Wedding', 'Belfast', '2025-05-09', 'Elegant church ceremony.'),
('Mason & Mia', 'Wedding', 'Newcastle', '2025-12-01', 'Winter wonderland wedding.'),
('Sophie & Lucas', 'Wedding', 'Exeter', '2025-09-17', 'Intimate countryside wedding.'),
('Harper & Owen', 'Wedding', 'Oxford', '2025-07-14', 'Classic wedding at university venue.'),

-- Parties
('New Year Bash', 'Party', 'Birmingham', '2025-12-31', 'Celebrate the New Year.'),
('Graduation Party', 'Party', 'Cardiff', '2025-07-01', 'Celebrate with graduates.'),
('Halloween Spookfest', 'Party', 'Liverpool', '2025-10-31', 'Costume party and fun activities.'),
('Summer Luau', 'Party', 'Brighton', '2025-08-05', 'Beachside summer bash.'),
('Christmas Gala', 'Party', 'Nottingham', '2025-12-24', 'Formal holiday celebration.'),
('Valentine’s Dance', 'Party', 'London', '2025-02-14', 'Love-themed event.'),
('St. Patrick’s Party', 'Party', 'Dublin', '2025-03-17', 'Green-themed celebration.'),
('Carnival Fiesta', 'Party', 'Leicester', '2025-06-08', 'Lively carnival with music and games.'),
('Back-to-School Bash', 'Party', 'Bristol', '2025-09-01', 'Start of academic year celebration.'),
('Spring Bloom Festival', 'Party', 'Bath', '2025-04-20', 'Celebrate nature and renewal.'),

-- Mix types
('Tech Innovations', 'Conference', 'Edinburgh', '2025-06-10', 'Showcase of tech trends.'),
('AI Summit', 'Conference', 'London', '2025-02-20', 'AI conference with workshops.'),
('Photography Workshop', 'Workshop', 'York', '2025-03-25', 'Learn camera basics and composition.'),
('Startup Pitch Day', 'Conference', 'Cambridge', '2025-07-07', 'Pitch your startup to investors.'),
('Mini Coding Camp', 'Workshop', 'Reading', '2025-05-27', 'Teens learn to code in 2 days.'),
('Henna Night', 'Party', 'Bradford', '2025-09-19', 'Pre-wedding celebration.'),
('Anna & John’s Wedding', 'Wedding', 'Derby', '2025-10-23', 'Modern chic wedding.'),
('Team Building Camp', 'Workshop', 'Coventry', '2025-11-11', 'Outdoor games and leadership training.'),
('Digital Ethics Conf', 'Conference', 'Leeds', '2025-04-04', 'Tech and moral discussions.'),
('Bollywood Night', 'Party', 'Leicester', '2025-06-30', 'Music and dancing Bollywood-style.');





