/*
* name: auto
* version: 0.0.1
* Copyright (c) 2015
*/
var gulp = require('gulp')
var less = require('gulp-less')
var autoprefixer = require('gulp-autoprefixer')
var cssmin = require('gulp-minify-css')
var uglify = require('gulp-uglify')

gulp.task('less', () => {
	return gulp.src('./resources-src/less/**/*.less')
		.pipe(less())
		.pipe(cssmin({compatibility: 'ie8'}))
		.pipe(autoprefixer({
			browsers: ['> 5%'],
			cascade: false
		}))
		.pipe(gulp.dest('./resources/css/'))
});
gulp.task('js', () => {
    return gulp.src('./resources-src/js/**/*.js')
        .pipe(uglify())
        .pipe(gulp.dest('./resources/js/'))
});
gulp.task('img', () => {
	return gulp.src('./resources-src/images/**/*')
		.pipe(gulp.dest('./resources/images/'))
});
gulp.task('vendor', () => {
	return gulp.src('./resources-src/vendor/**/*')
		.pipe(gulp.dest('./resources/vendor/'))
});
gulp.task('watch', () => {
	gulp.watch('./resources-src/less/**/*.less', ['less']);
	gulp.watch('./resources-src/js/**/*', ['js']);
	gulp.watch('./resources-src/images/**/*', ['img']);
	gulp.watch('./resources-src/vendor/**/*', ['vendor']);
});
gulp.task('default', ['less', 'js', 'img', 'vendor', 'watch']);