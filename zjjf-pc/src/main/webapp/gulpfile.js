  /*
 * name: auto
 * version: 0.0.1
 * Copyright (c) 2015 - 2016
 */
const gulp = require('gulp');
const less = require('gulp-less');
const autoprefixer = require('gulp-autoprefixer');
const cssmin = require('gulp-clean-css');
const uglify = require('gulp-uglify');
const imagemin = require('gulp-imagemin');
const cache = require('gulp-cache');

const paths = {
    less: ['./resources-src/less/**/*.less', '!./resources-src/less/**/__*.less'],
    js: './resources-src/js/**/*.js',
    images: './resources-src/images/**/*',
    lib: './resources-src/vendor/lib/*',
    vendor: ['./resources-src/vendor/**/*', '!./resources-src/vendor/lib/**/*']
};

gulp.task('less', () => {
    return gulp.src(paths.less)
        .pipe(less())
        .pipe(cssmin({compatibility: 'ie8'}))
        .pipe(autoprefixer({
            browsers: ['last 2 versions', '> 5%'],
            cascade: false
        }))
        .pipe(gulp.dest('./resources/css/'))
});
gulp.task('js', () => {
    return gulp.src(paths.js)
        .pipe(uglify())
        .pipe(gulp.dest('./resources/js/'))
});
gulp.task('img', () => {
  return gulp.src(paths.images)
      .pipe(cache(imagemin()))
      .pipe(gulp.dest('./resources/images/'))
});
gulp.task('vendor', () => {
    gulp.src(paths.lib)
        .pipe(uglify())
        .pipe(gulp.dest('./resources/vendor/lib/'))
    gulp.src(paths.vendor)
        .pipe(gulp.dest('./resources/vendor/'))
});
gulp.task('watch', () => {
    gulp.watch(paths.less, ['less']);
    gulp.watch(paths.js, ['js']);
    gulp.watch(paths.images, ['img']);
    gulp.watch('./resources-src/vendor/**/*', ['vendor']);
});

gulp.task('default', ['less', 'js', 'img', 'vendor', 'watch']);
