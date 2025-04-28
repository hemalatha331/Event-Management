# Event Management System

This is a **NetBeans** based **Event Management System** project where:
- **Admin** can **create**, **update**, **delete**, and **view** events.
- **Participants** can **login**, **view** available events, and **register** for events.

The system also implements **machine learning classification** using **Weka's Random Forest** algorithm to analyze event data.

---

## Tech Stack 🛠️
- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java (Servlets)
- **Database:** MySQL
- **Machine Learning:** Weka (Random Forest Classification)

---

## Features ✨
### Admin Side:
- Create new events
- Update existing event details
- Delete unwanted events
- View all events
- Logout functionality

### Participant Side:
- Participant login
- View list of events
- Register for desired events
- Logout functionality

---

## Project Screenshots 📸

| Start Page | Admin Event Management | Add Event | Update/Delete Events |
|:---:|:---:|:---:|:---:|
| ![Start Page](https://github.com/hemalatha331/Event-Management/blob/main/pics/1startpage.png) | ![Admin Event Management](https://github.com/hemalatha331/Event-Management/blob/main/pics/3AdminEventManagement.png) | ![Add Event](https://github.com/hemalatha331/Event-Management/blob/main/pics/2addevent.png) | ![Update/Delete Events](https://github.com/hemalatha331/Event-Management/blob/main/pics/4Updatedeleteevents.png) |

| Participant Home | Event Registration | Weka Results |
|:---:|:---:|:---:|
| ![Participant Home](https://github.com/hemalatha331/Event-Management/blob/main/pics/5participantHomepage.png) | ![Event Registration](https://github.com/hemalatha331/Event-Management/blob/main/pics/6EventRegistration.png) | ![Weka Results](https://github.com/hemalatha331/Event-Management/blob/main/pics/7wekaresults.png) |

---

## How to Run 🔥
1. Open the project in **NetBeans IDE**.
2. Configure the MySQL database with the provided `eventlydb.sql` file.
3. Set up server (like Apache Tomcat/GlassFish) inside NetBeans.
4. Deploy and run the project from NetBeans.
5. For Weka results, use the `events.arff` file to load data and run classification.

---

## Folder Structure 🗂️
- `src/` → Java Servlets and logic
- `web/` → HTML, CSS, JS files
- `eventlydb.sql` → Database dump
- `events.arff` → Weka dataset
- `pics/` → Project screenshots

---

## License
This project is created for educational purposes.

---

