# Olutindo-app

This is a Cordova (Phonegap)-based Android application that provides a container for the [Olutindo](http://github.com/chrisekelley/olutindo) webapp.
Olutindo uses [Coconut](http://chrisekelley.github.io/coconut/) for most of its front-end form rendering, [backbone.js](http://backbonejs.org) for MVC,
[pouchdb](http://pouchdb.com/) for browser-based data store and replication, and [CouchDB](http://couchdb.apache.org/) for the master data store.

This project is dependent upon [Olutindo](http://github.com/chrisekelley/olutindo) webapp. Clone Olutindo, remove the www directory, and create
a symbolic link named www to the olutindo project.

## Setup

To deploy this app, you must have your Android development tools installed (adb command must work).

Install grunt:

    npm install -g grunt-cli

Run build.sh. That script executes grunt cordova-build, uninstalls the app, and then installs it.