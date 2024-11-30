Overview

This project is a web application built using Java Servlets and HTML. It provides a platform to create, search, edit, and delete lists of HTTP response codes with their respective images sourced from http.dog. The application allows users to manage these lists while learning about response codes visually.
---

Features

Pages

1. Login/Signup Page

Users can sign up or log in to access the application.

Authentication is handled through sessions.

2. Search Page

Allows filtering HTTP response codes and displays matching images.

Filtering options include:

Specific response code (e.g., 203).

Range-based filters (e.g., 2xx, 3xx).

Partial code filters using wildcards (e.g., 20x, 21x).


Users can save filtered results as a named list.



3. Lists Page

Displays all saved lists, including their metadata:

List name

Creation date

Response codes and corresponding image links


Allows users to:

View saved lists and associated images.

Edit lists (e.g., add/remove response codes, rename the list).

Delete lists.






---

Project Structure

Backend

Java Servlets:

Handles user authentication, filtering, and CRUD operations for lists.

Implements HTTP methods (GET, POST, PUT, DELETE) to manage application data.


Session Management:

Uses HTTP sessions to manage logged-in users.


Database:

Stores user information, saved lists, and their metadata.

Suggested database: PostgreSQL.



Frontend

HTML and CSS:

Provides a responsive user interface.

Displays response code images fetched dynamically from http.dog.


JavaScript (Optional):

Enhances interactivity, such as dynamic filtering and live previews.


