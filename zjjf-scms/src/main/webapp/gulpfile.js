/*
 * name: auto
 * version: 0.1.0
 * Copyright (c) 2015 - 2016
 */
const gulp = require('gulp');
const less = require('gulp-less');
const autoprefixer = require('gulp-autoprefixer');
const cssmin = require('gulp-clean-css');
// const babel = require('gulp-babel');
const uglify = require('gulp-uglify');
const eslint = require('gulp-eslint');
const imagemin = require('gulp-imagemin');
const cache = require('gulp-cache');

const paths = {
    less: ['./resources-src/less/**/*.less', '!./resources-src/less/**/_*.less'],
    js: './resources-src/js/**/*.js',
    images: './resources-src/images/**/*',
    vendor: './resources-src/vendor/**/*'
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
gulp.task('less-purchase', () => {
    return gulp.src(['./resources-src/purchase/less/**/*.less', '!./resources-src/purchase/less/normalize.less', '!./resources-src/purchase/less/base-*.less', '!./resources-src/purchase/less/*-comm.less'])
        .pipe(less())
        .pipe(cssmin({compatibility: 'ie8'}))
        .pipe(autoprefixer({
            browsers: ['last 2 versions', '> 5%'],
            cascade: false
        }))
        .pipe(gulp.dest('./resources/purchase/css/'))
});
gulp.task('js', () => {
    return gulp.src(paths.js)
        //.pipe(babel({
        //    presets: ['es2015']
        //}))
        .pipe(uglify())
        .pipe(gulp.dest('./resources/js/'))
});
gulp.task('js-purchase', () => {
    return gulp.src('./resources-src/purchase/js/**/*.js')
        .pipe(uglify())
        .pipe(gulp.dest('./resources/purchase/js/'))
});
gulp.task('eslint', () => {
    return gulp.src(paths.js)
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});
gulp.task('eslint-purchase', () => {
    return gulp.src('./resources-src/purchase/js/**/*.js')
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});
gulp.task('images', () => {
    return gulp.src(paths.images)
        .pipe(cache(imagemin()))
        .pipe(gulp.dest('./resources/images/'))
});
gulp.task('images-purchase', () => {
    return gulp.src('./resources-src/purchase/images/**/*')
        .pipe(cache(imagemin()))
        .pipe(gulp.dest('./resources/purchase/images/'))
});
gulp.task('vendor', () => {
    return gulp.src(paths.vendor)
        .pipe(gulp.dest('./resources/vendor/'))
});
gulp.task('watch', () => {
    gulp.watch(paths.less, ['less']);
    gulp.watch('./resources-src/purchase/less/**/*.less', ['less-purchase']);
    gulp.watch(paths.js, ['js']);
    gulp.watch('./resources-src/purchase/js/**/*.js', ['js-purchase']);
    gulp.watch(paths.images, ['images']);
    gulp.watch('./resources-src/purchase/images/**/*', ['images-purchase']);
    gulp.watch(paths.vendor, ['vendor']);
});
gulp.task('default', ['less', 'js', 'images', 'vendor', 'watch']);
gulp.task('purchase', ['less-purchase', 'js-purchase', 'images-purchase', 'vendor', 'watch']);
