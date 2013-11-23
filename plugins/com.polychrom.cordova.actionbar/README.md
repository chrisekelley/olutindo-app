cordova-android-actionbar
=========================

cordova-android-actionbar is an [Android](https://github.com/android) [ActionBar](http://developer.android.com/reference/android/app/ActionBar.html) plugin for [cordova-android](https://github.com/apache/cordova-android).

This version is for cordova 3.x and features ActionBarSherlock support.


Basic Example (Menu)
--------------------

	var ActionBar = window.plugins.actionbar;
	
	// Show Logo / Title
	ActionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE);

	// Set navigation mode (NAVIGATION_MODE_STANDARD is the default)
	ActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	
	// Set menu items
	ActionBar.setMenu([
		{ icon: 'img/new.png', text: 'New File', show: ActionBar.SHOW_AS_ACTION_ALWAYS, click: function() { alert('Create new file'); } },
		{ icon: 'img/save.png', text: 'Save',
		  header: { icon: 'img/save.png', text: 'Save as...' },
		  items: [
			{ text: 'PNG', click: function() { alert('Save PNG'); } },
			{ text: 'JPEG', click: function() { alert('Save JPEG'); } }
		  ]
		},
		{ icon: 'img/search.png', text: 'Search', show: ActionBar.SHOW_AS_ACTION_ALWAYS | ActionBar.SHOW_AS_ACTION_WITH_TEXT }
	]);

Basic Example (Tabs)
--------------------

	var ActionBar = window.plugins.actionbar;
	
	// Hide title bar
	ActionBar.setDisplayOptions(0);
	
	// Set navigation mode to display tabs
	ActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
	ActionBar.setTabs([
		{ icon: 'img/inbox.png', text: 'Inbox',
			select: function() { alert('View Inbox!'); },
			reselect: function() { alert('Refresh Inbox!'); },
			unselect: function() { alert('Hide Inbox!'); }
		},
		{ icon: 'icons/settings.png', text: 'Outbox',
			select: function() { alert('View Outbox!'); },
			reselect: function() { alert('Refresh Outbox!'); },
			unselect: function() { alert('Hide Outbox!');
		}
	}
	]);

Project Setup
-------------

1. Copy (or link) all the .java files into your project src (under the appropriate com.polychrom.cordova package).
2. Copy actionbar.js into your cordova app's www directory
3. Add `<script charset="utf-8" src="actionbar.js"></script>` to your cordova app's HTML.
4. Call `requestWindowFeature(Window.FEATURE_ACTION_BAR);` BEFORE loadUrl/setContentView to request the ActionBar feature for your activity's window.
5. Add `getActionBar();` to your Activity's onCreate function AFTER loadUrl/setContentView - Android won't create/display an ActionBar if it isn't called.