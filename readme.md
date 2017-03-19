# Iteration 1
## Introduction 
The site is built upon frameworks and tools below:
* Lumen
* Dingo (API)
* Vue.js 
* Gulp
* Webpack
* Bootstrap 3
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
php artisan migrate
php artisan db:seed
chmod -R 777 storage
composer install
yarn
yarn run build
```