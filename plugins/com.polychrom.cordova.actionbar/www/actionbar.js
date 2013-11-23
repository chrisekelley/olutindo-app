// Copyright (C) 2013 Polychrom Pty Ltd
//
// This program is licensed under the 3-clause "Modified" BSD license,
// see LICENSE file for full definition.

var ActionBarSherlock = function() {};

ActionBarSherlock.prototype.DISPLAY_USE_LOGO = 1;
ActionBarSherlock.prototype.DISPLAY_SHOW_HOME = 2;
ActionBarSherlock.prototype.DISPLAY_HOME_AS_UP = 4;
ActionBarSherlock.prototype.DISPLAY_SHOW_TITLE = 8;
ActionBarSherlock.prototype.DISPLAY_SHOW_CUSTOM = 16;

ActionBarSherlock.prototype.NAVIGATION_MODE_STANDARD = 0;
ActionBarSherlock.prototype.NAVIGATION_MODE_LIST = 1;
ActionBarSherlock.prototype.NAVIGATION_MODE_TABS = 2;

ActionBarSherlock.prototype.SHOW_AS_ACTION_NEVER = 0;
ActionBarSherlock.prototype.SHOW_AS_ACTION_IF_ROOM = 1;
ActionBarSherlock.prototype.SHOW_AS_ACTION_ALWAYS = 2;
ActionBarSherlock.prototype.SHOW_AS_ACTION_WITH_TEXT = 4;
ActionBarSherlock.prototype.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;

ActionBarSherlock.prototype.getHeight = function(callback)
{
	return cordova.exec(
		function(result) { callback(undefined, result.height); },
		callback,
		'ActionBarSherlock', 'getHeight', []);
};

ActionBarSherlock.prototype.show = function(callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'show', []);
};

ActionBarSherlock.prototype.hide = function(callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'hide', []);
};

ActionBarSherlock.prototype.toggle = function(callback)
{
	callback = callback || function() {};

	ActionBarSherlock.isShowing(function(error, showing)
	{
		if(error) return callback(error);

		if(showing) ActionBarSherlock.hide();
		else ActionBarSherlock.show();
	});
};

ActionBarSherlock.prototype.isShowing = function(callback)
{
	return cordova.exec(
		function(result) { callback(undefined, result.value); },
		callback,
		'ActionBarSherlock', 'isShowing', []);
};

ActionBarSherlock.prototype.setMenu = function(menu, callback)
{
	callback = callback || function() {};

	var old_menu = ActionBarSherlock.menu;
	ActionBarSherlock.menu = menu;

	return cordova.exec(
		callback,
		function(e) { ActionBarSherlock.menu = old_menu; callback(e); },
		'ActionBarSherlock', 'setMenu', [menu]);
};

ActionBarSherlock.prototype.setTabs = function(tabs, callback)
{
	callback = callback || function() {};

	var old_tabs = ActionBarSherlock.tabs;
	ActionBarSherlock.tabs = tabs;

	return cordova.exec(
		callback,
		function(e) { ActionBarSherlock.tabs = old_tabs; callback(e); },
		'ActionBarSherlock', 'setTabs', [tabs]);
};

ActionBarSherlock.prototype.clearMenu = function(callback)
{
	return this.setMenu([], callback);
};

ActionBarSherlock.prototype.clearTabs = function(callback)
{
	return this.setTabs([], callback);
};

ActionBarSherlock.prototype.setDisplayHomeAsUpEnabled = function(showHomeAsUp, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setDisplayHomeAsUpEnabled', [showHomeAsUp]);
};

ActionBarSherlock.prototype.setDisplayOptions = function(options, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setDisplayOptions', [options]);
};

ActionBarSherlock.prototype.getDisplayOptions = function(callback)
{
	return cordova.exec(
		function(result) { callback(undefined, result.value); },
		callback,
		'ActionBarSherlock', 'getDisplayOptions', []);
};

ActionBarSherlock.prototype.setHomeCallback = function(callback)
{
	ActionBarSherlock.home_callback = callback;
}

ActionBarSherlock.prototype.setDisplayShowHomeEnabled = function(showHome, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setDisplayShowHomeEnabled', [showHome]);
};

ActionBarSherlock.prototype.setDisplayShowTitleEnabled = function(showTitle, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setDisplayShowTitleEnabled', [showTitle]);
};

ActionBarSherlock.prototype.setDisplayUseLogoEnabled = function(useLogo, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setDisplayUseLogoEnabled', [useLogo]);
};

ActionBarSherlock.prototype.setHomeButtonEnabled = function(enabled, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setHomeButtonEnabled', [enabled]);
};

ActionBarSherlock.prototype.setIcon = function(icon, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setIcon', [icon]);
};

ActionBarSherlock.prototype.setListNavigation = function(items, callback)
{
	callback = callback || function() {};

	ActionBarSherlock.navigation_items = items;

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setListNavigation', [items]);
};

ActionBarSherlock.prototype.setLogo = function(logo, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setLogo', [logo]);
};

ActionBarSherlock.prototype.setNavigationMode = function(mode, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setNavigationMode', [mode]);
};

ActionBarSherlock.prototype.getNavigationMode = function(callback)
{
	return cordova.exec(
		function(result) { callback(undefined, result.value); },
		callback,
		'ActionBarSherlock', 'getNavigationMode', []);
};

ActionBarSherlock.prototype.setSelectedNavigationItem = function(position, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setSelectedNavigationItem', [position]);
};

ActionBarSherlock.prototype.getSelectedNavigationItem = function(callback)
{
	// TODO: Map to menu item or tab?

	return cordova.exec(
		function(result) { callback(undefined, result.value); },
		callback,
		'ActionBarSherlock', 'getSelectedNavigationItem', []);
};

ActionBarSherlock.prototype.setSubtitle = function(subtitle, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setSubtitle', [subtitle]);
};

ActionBarSherlock.prototype.getSubtitle = function(callback)
{
	return cordova.exec(
		function(result) { callback(undefined, result.value); },
		callback,
		'ActionBarSherlock', 'getSubtitle', []);
};

ActionBarSherlock.prototype.setTitle = function(title, callback)
{
	callback = callback || function() {};

	return cordova.exec(
		function() { callback(undefined); },
		callback,
		'ActionBarSherlock', 'setTitle', [title]);
};

ActionBarSherlock.prototype.getTitle = function(callback)
{
	return cordova.exec(
		function(result) { callback(undefined, result.value); },
		callback,
		'ActionBarSherlock', 'getTitle', []);
};

//if(!window.plugins) window.plugins = {};
//ActionBarSherlock = new ActionBarSherlock();

module.exports = new ActionBarSherlock();