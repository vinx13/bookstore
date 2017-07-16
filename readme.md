# Bookstore

## Architecture
### Frontend
Frontend pages are implemented as two single page applications (admin and store) with Vue.js, communicating with Java backend by RESTful API. These pages are splitted into a set of Vue components in `src/main/web`, and are packed by gulp and webpack when building. 
Dev environment with convenient features such as hot module replacement, live reloading is available. Run `yarn run dev` in web folder to start.

### Backend
The backend is built upon Spring Framework (Boot, Data JPA, Data REST, Security, MVC) and Hibernate. 
Session is managed by Spring Session and stored in Redis. 

## Database
We use retaional database, such as MySQL, for primary data storage. Besides, MongoDB GridFS is utilized for storing images. Redis is used for saving session.

## Functionality
* CRUD for users, books, genres, orders, authors
* Statistics with flexible filters
* User login / registration
* Listing, details browsing and searching of books
* Shopping cart and order placement


## Build
```
cd src/main/web
yarn
yarn run build
cd -
./gradlew build
```
