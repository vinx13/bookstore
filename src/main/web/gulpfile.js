// Dependencies
const gulp = require('gulp')
const gulpif = require('gulp-if')
const uglify = require('gulp-uglify')
const concat = require('gulp-concat')
const rev = require('gulp-rev')
const del = require('del')
const rename = require('gulp-rename')
const cssnano = require('gulp-cssnano')
const minimist = require('minimist')
const inject = require('gulp-inject')
const babelify = require('babelify')
const webpack = require('webpack')
const gutil = require('gulp-util')
const filenames = require("gulp-filenames");
const fs = require('fs');
const WebpackDevServer = require("webpack-dev-server");

// Load assets.json
const assets = require('./assets')

// To compress all the files, use `gulp build --env production`
const knownOptions = {
  string: 'env',
  default: {env: process.env.NODE_ENV || 'development'}
}

const options = minimist(process.argv.slice(2), knownOptions)
const webpackConfig = require(options.env === 'production' ? './webpack.config.production.js' : './webpack.config.js')


gulp.task('admin-style', ['clean'], function () {
  // Prefix, compress and concat the CSS assets
  // Afterwards add the MD5 hash to the filename
  return gulp.src(assets.admin.styles)
    .pipe(gulpif(options.env === 'production', concat('admin.min.css')))
    .pipe(gulpif(options.env === 'production', cssnano()))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(filenames('admin-style'))
    .pipe(gulp.dest('../resources/static/styles'));
})

gulp.task('admin-script', ['clean'], function () {
  // Concat and uglify the JavaScript assets
  // Afterwards add the MD5 hash to the filename
  return gulp.src(assets.admin.scripts)
    .pipe(gulpif(options.env === 'production', concat('admin.min.js')))
    .pipe(gulpif(options.env === 'production', uglify({preserveComments: 'license'})))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(filenames('admin-script'))
    .pipe(gulp.dest('../resources/static/scripts'));

});

gulp.task('webpack', ['clean'], function () {

  webpack(webpackConfig, function (err, stats) {
    if (err) throw new gutil.PluginError("webpack", err);
    gutil.log("[webpack]", stats.toString({
      // output options
    }));
  });
});


gulp.task('store-style', ['clean'], function () {
  // Prefix, compress and concat the CSS assets
  // Afterwards add the MD5 hash to the filename
  return gulp.src(assets.store.styles)
    .pipe(gulpif(options.env === 'production', concat('store.min.css')))
    .pipe(gulpif(options.env === 'production', cssnano()))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(filenames('store-style'))
    .pipe(gulp.dest('../resources/static/styles'));
})

gulp.task('store-script', ['clean'], function () {
  // Concat and uglify the JavaScript assets
  // Afterwards add the MD5 hash to the filename
  return gulp.src(assets.store.scripts)
    .pipe(gulpif(options.env === 'production', concat('store.min.js')))
    .pipe(gulpif(options.env === 'production', uglify({preserveComments: 'license'})))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(filenames('store-script'))
    .pipe(gulp.dest('../resources/static/scripts'));

});

gulp.task('write-property', ['admin-style', 'admin-script', 'store-style', 'store-script'], function () {
  let prop = 'bookstore.store-styles=' + filenames.get('store-style').join() + '\n'
  prop += 'bookstore.store-scripts=' + filenames.get('store-script').join() + '\n'
  prop += 'bookstore.admin-styles=' + filenames.get('admin-style').join() + '\n'
  prop += 'bookstore.admin-scripts=' + filenames.get('admin-script').join() + '\n'
  fs.writeFile('../resources/asset.properties', prop)



})

// Font task
gulp.task('fonts', ['clean'], function () {
  assets.fonts.forEach(function (item) {
    gulp.src(item)
      .pipe(gulp.dest('../resources/static/fonts'))

  });
});

// Clean tasks
gulp.task('clean', function () {
  // Cleanup old assets
  try {
    del.sync([
      '../resources/static/styles/**',
      '../resources/static/scripts/**',
      '../resources/static/fonts/**'
    ]);
  } catch (e) {
  }
});

gulp.task("dev", ['build-without-webpack'], function () {
  // Start a webpack-dev-server
  const compiler = webpack(webpackConfig);

  new WebpackDevServer(compiler, {
    contentBase: "../resources/static",
    noInfo: false, //  --no-info option
    hot: true,
    inline: true,

    proxy: {
      '/api/**': {
        target: 'http://127.0.0.1:8080',
        secure: false,
        changeOrigin: true
      }
    }
  }).listen(8000, "localhost", function (err) {
    if (err) throw new gutil.PluginError("webpack-dev-server", err);
    // Server listening
    gutil.log("[webpack-dev-server]", "http://localhost:8000/");

  });
});

// Build tasks
gulp.task('build-without-webpack', ['admin-style', 'admin-script', 'store-style', 'store-script', 'fonts', 'write-property']);
gulp.task('build', ['build-without-webpack', 'webpack']);


// Watch tasks
gulp.task('watch', function () {
  gulp.watch([
    assets.admin.styles,
    assets.admin.scripts,
    assert.fonts
  ], ['build-without-webpack']);
});


// The default task (called when you run `gulp` from cli)
gulp.task('default', ['build']);
