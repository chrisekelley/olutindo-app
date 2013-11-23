// Copyright (C) 2013 Polychrom Pty Ltd
//
// This program is licensed under the 3-clause "Modified" BSD license,
// see LICENSE file for full definition.

package com.polychrom.examples;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.cordova.*;

// NOTE: cordova-android's DroidGap class currently prevents custom window features, thus we implement our own Activity (with full control) and embed Cordova instead.
public class ActionBarExample extends DroidGap
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		// Request action bar feature BEFORE onCreate (window features must be requested before the window is created)
		super.setBooleanProperty("showTitle", true);
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
        
        // Artificially create our action bar (action bar won't be created until the first call to getActionBar)
        getActionBar();
    }
}
