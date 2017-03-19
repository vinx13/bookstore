# Iteration 1
## Introduction 
The site is built upon frameworks and tools below:
* Lumen
* Eloquent ORM
* Dingo (API)
* Vue.js 
* Gulp
* Webpack
* Bootstrap 3
* Font Awesome
* Admin LTE 2
* Material Design Lite

The site consists of two single page applications (bookstore and admin panel). Data is transferred via Restful API.   

Live preview available at [store](http://bookstore.vincentlin.me) and [admin](http://bookstore.vincentlin.me/admin)   

Generate API documentation:
```
php artisan api:docs
```

## Deploy
```
cp .env.example .env
yarn
composer install
php artisan migrate
php artisan db:seed
chmod -R 777 storage
yarn run build
```  