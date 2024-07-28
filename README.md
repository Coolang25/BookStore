
# Book Store

The bookstore needs a system to handle book and author data, including adding and listing books and authors. Additionally, the system should differentiate between two user roles:
- Librarian: Can add, edit, and delete books.
- Borrower: Can view and borrow books.

Functionalities:
- Add New Books (Librarian): The system should allow librarians to add new books with details like title and author..
- Add New Authors (Librarian): The system should allow librarians to add new authors with details like name.
- List All Books (Public): The system should provide a public API to list all available books.
- List All Authors (Public): The system should provide a public API to list all authors whose books are available.
- Borrow Book (Borrower): The system should allow borrowers to mark a book as borrowed.
- Log Execution Time: The system should log the execution time of each method for performance monitoring.
- Auditing: The system should track and store create, update, and delete actions performed by information on JWT on each table in the database.
- Use JWT, and role-based authorization. When logging in, the server returns an access token and a refresh token (HTTPOnly Cookie).
- Register API: Create a register endpoint that allows borrowers to create new accounts Implement robust password policies (e.g., minimum length, complexity requirements) and password hashing (bcrypt) to protect user credentials. Librarian accounts are pre-populated in the database and not created through registration.
- Logout API: Create a logout endpoint that invalidates the JWT (refresh token). Save invalidated refresh token in database.
- CORS Configuration:
- Remember Me Functionality: Save refresh token in HTTPOnly Cookie
- Unit and Integration Tests: Incomplete
- Using jQuery.ajax to call API

