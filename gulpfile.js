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
const htmlreplace = require('gulp-htmlreplace')
const inject = require('gulp-inject')
const es = require('event-stream')
const babelify = require('babelify')
const webpack = require('webpack')
const gutil = require('gulp-util')

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


gulp.task('admin-lib', ['clean'], function () {
  // Prefix, compress and concat the CSS assets
  // Afterwards add the MD5 hash to the filename
  const styles = gulp.src(assets.admin.styles)
    .pipe(gulpif(options.env === 'production', concat('admin.min.css')))
    .pipe(gulpif(options.env === 'production', cssnano()))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(gulp.dest('public/static/styles'));

  // Concat and uglify the JavaScript assets
  // Afterwards add the MD5 hash to the filename
  const scripts = gulp.src(assets.admin.scripts)
    .pipe(gulpif(options.env === 'production', concat('admin.min.js')))
    .pipe(gulpif(options.env === 'production', uglify({preserveComments: 'license'})))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(gulp.dest('public/static/scripts'));

  gulp.src("./resources/admin/index.html")
    .pipe(inject(es.merge(styles, scripts), {ignorePath: 'public'}))
    .pipe(gulp.dest('public/static/admin'));

});

gulp.task('webpack', ['clean'], function () {

  webpack(webpackConfig, function (err, stats) {
    if (err) throw new gutil.PluginError("webpack", err);
    gutil.log("[webpack]", stats.toString({
      // output options
    }));
  });
});


gulp.task('store-lib', ['clean'], function () {
  // Prefix, compress and concat the CSS assets
  // Afterwards add the MD5 hash to the filename
  const styles = gulp.src(assets.admin.styles)
    .pipe(gulpif(options.env === 'production', concat('store.min.css')))
    .pipe(gulpif(options.env === 'production', cssnano()))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(gulp.dest('public/static/styles'));

  // Concat and uglify the JavaScript assets
  // Afterwards add the MD5 hash to the filename
  const scripts = gulp.src(assets.admin.scripts)
    .pipe(gulpif(options.env === 'production', concat('store.min.js')))
    .pipe(gulpif(options.env === 'production', uglify({preserveComments: 'license'})))
    .pipe(rev())
    .pipe(rename({dirname: ''}))
    .pipe(gulp.dest('public/static/scripts'));

  gulp.src("./resources/store/index.html")
    .pipe(inject(es.merge(styles, scripts), {ignorePath: 'public'}))
    .pipe(gulp.dest('public/static/store'));

});


// Font task
gulp.task('fonts', ['clean'], function () {
  assets.fonts.forEach(function (item) {
    gulp.src(item)
      .pipe(gulp.dest('public/static/fonts'));
  });
});

// Clean tasks
gulp.task('clean', function () {
  // Cleanup old assets
  try {
    del.sync([
      'public/static/styles/**',
      'public/static/scripts/**',
      'public/static/fonts/**',
      'public/static/*.html',
      'public/static/admin/*.html',
      'public/static/store/*.html'
    ]);
  } catch (e) {
  }
});

gulp.task("dev", ['build-without-webpack'], function () {
  // Start a webpack-dev-server
  const compiler = webpack(webpackConfig);

  new WebpackDevServer(compiler, {
    contentBase: "./public",
    publicPath: "/static/scripts/",
    noInfo: false, //  --no-info option
    hot: true,
    inline: true,

    proxy: {
      '/api/**': {
        target: 'http://[::1]:8000',
        secure: false,
        changeOrigin: true
      }
    }
  }).listen(8080, "localhost", function (err) {
    if (err) throw new gutil.PluginError("webpack-dev-server", err);
    // Server listening
    gutil.log("[webpack-dev-server]", "http://localhost:8080/");

  });
});

// Build tasks
gulp.task('build-without-webpack', ['admin-lib', 'fonts']);
gulp.task('build', ['admin-lib', 'webpack', 'fonts']);


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
