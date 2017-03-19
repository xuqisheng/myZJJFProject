/*
 * name: auto
 * version: 0.1.0
 * Copyright (c) 2015 - 2016
 */
const gulp = require('gulp');
const sass = require('gulp-sass');
const autoprefixer = require('gulp-autoprefixer');
const cssmin = require('gulp-clean-css');
const webpack = require('webpack-stream');
const webpackCfg = require('./webpack.config.js')
const named = require('vinyl-named');
const uglify = require('gulp-uglify');
const eslint = require('gulp-eslint');
const imagemin = require('gulp-imagemin');
const cache = require('gulp-cache');

const files = {
    sass: ['./resources-src/sass/**/*.scss', '!./resources-src/sass/**/_*.scss'],
    es: ['./resources-src/es/**/*.js', '!./resources-src/es/**/_*.js'],
    js: './resources-src/js/**/*.js',
    images: './resources-src/images/**/*',
    vendor: './resources-src/vendor/**/*'
};

gulp.task('sass', () => {
    return gulp.src(files.sass)
        .pipe(sass().on('error', sass.logError))
        .pipe(autoprefixer({
            browsers: ['last 2 versions', '> 5%'],
            cascade: false
        }))
        .pipe(cssmin({compatibility: 'ie8'}))
        .pipe(gulp.dest('./resources/css/'))
});
gulp.task('js', () => {
    gulp.src(files.es)
        .pipe(named())
        .pipe(webpack(webpackCfg))
        .pipe(uglify())
        .pipe(gulp.dest('./resources/es/'))
    gulp.src(files.js)
        .pipe(uglify())
        .pipe(gulp.dest('./resources/js/'))
});
gulp.task('jslint', () => {
    gulp.src(files.es)
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
    gulp.src(files.js)
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});
gulp.task('images', () => {
    return gulp.src(files.images)
        .pipe(cache(imagemin()))
        .pipe(gulp.dest('./resources/images/'))
});
gulp.task('vendor', () => {
    return gulp.src(files.vendor)
        .pipe(gulp.dest('./resources/vendor/'))
});
gulp.task('watch', () => {
    gulp.watch(files.sass, ['sass']);
    gulp.watch([files.js, files.es], ['js', 'jslint']);
    gulp.watch(files.images, ['images']);
    gulp.watch(files.vendor, ['vendor']);
});
gulp.task('default', ['sass', 'js', 'jslint', 'images', 'vendor', 'watch']);
