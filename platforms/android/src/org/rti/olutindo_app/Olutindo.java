/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.rti.olutindo_app;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaChromeClient;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
// import org.jshybugger.DebugServiceClient;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.actionbarsherlock.widget.SearchView.OnSuggestionListener;


public class Olutindo extends SherlockActivity implements CordovaInterface, SearchView.OnQueryTextListener
{

	ActionBarSherlock mSherlock = ActionBarSherlock.wrap(this);
    private static final String TAG = "Olutindo-app";
    
    // CordovaInterface fields
    
    protected ProgressDialog spinnerDialog = null;
    // The webview for our app
    protected CordovaWebView appView;
    // Plugin to call when activity result is received
    protected CordovaPlugin activityResultCallback = null;
    protected boolean activityResultKeepRunning;
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    
    // The initial URL for our app
    // ie http://server/path/index.html#abc?query
    //private String url = null;

    private static int ACTIVITY_STARTING = 0;
    private static int ACTIVITY_RUNNING = 1;
    private static int ACTIVITY_EXITING = 2;
    private int activityState = 0;  // 0=starting, 1=running (after 1st resume), 2=shutting down
    
    // Keep app running when pause is received. (default = true)
    // If true, then the JavaScript and native code continue to run in the background
    // when another application (activity) is started.
    protected boolean keepRunning = true;
    private String initCallbackClass;
    /*
     * The variables below are used to cache some of the activity properties.
     */

    // Draw a splash screen using an image located in the drawable resource directory.
    // This is not the same as calling super.loadSplashscreen(url)
    protected int splashscreen = 0;
    protected int splashscreenTime = 3000;

	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		Config.init(this);
		// Show the title bar, or else the ActionBar will be null on Android 4.x
        //setBooleanProperty("showTitle", true);
        super.onCreate(savedInstanceState);
        //ActionBarSherlockMenuPlugin.setOnInitListener(this);
        // Set by <content src="index.html" /> in config.xml

        //super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html")


        // load html page via jsHybugger content provider
        //super.loadUrl("content://jsHybugger.org/" + Config.getStartUrl());
        // requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main);
        appView = (CordovaWebView) findViewById(R.id.uBridgeView);
        //getActionBar();
        //getSupportActionBar();
        appView.loadUrl(Config.getStartUrl());
    }

	
	// for CordovaWebView
	
	@Override
	public Activity getActivity() {
	  return this;
	}
	
	 @Override
	    public ExecutorService getThreadPool() {
	        return threadPool;
	    }
	
//  @Override
//	public void init(CordovaWebView webView,
//			CordovaWebViewClient webViewClient,
//			CordovaChromeClient webChromeClient) {
//		super.init(webView, webViewClient, webChromeClient);
//
//		// attach web view to debugging service
//		//DebugServiceClient.attachWebView(webView, this);
//	}
	
	
    /*
     * In order to use action items properly with static attachment you
     * need to dispatch create, prepare, and selected events for the
     * native type to the ActionBarSherlock instance. If for some reason
     * you need to use static attachment you should probably create a
     * common base activity that does this for all three methods.
     */
//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//    	Log.d(TAG, "onCreateOptionsMenu in Olutindo");
//        return mSherlock.dispatchCreateOptionsMenu(menu);
//    }
    
//    @Override
//    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
//    	Log.d(TAG, "onPrepareOptionsMenu in Olutindo");
//        return mSherlock.dispatchPrepareOptionsMenu(menu);
//    }


	 @Override
	 /**
	     * Called when a message is sent to plugin.
	     *
	     * @param id            The message id
	     * @param data          The message data
	     * @return              Object or null
	     */
	    public Object onMessage(String id, Object data) {
	        Log.d(TAG, "onMessage(" + id + "," + data + ")");
	        if ("splashscreen".equals(id)) {
	            if ("hide".equals(data.toString())) {
	                this.removeSplashScreen();
	            }
	            else {
	                // If the splash dialog is showing don't try to show it again
	                if (this.splashDialog == null || !this.splashDialog.isShowing()) {
	                    this.splashscreen = this.getIntegerProperty("SplashScreen", 0);
	                    this.showSplashScreen(this.splashscreenTime);
	                }
	            }
	        }
	        else if ("spinner".equals(id)) {
	            if ("stop".equals(data.toString())) {
	                this.spinnerStop();
	                this.appView.setVisibility(View.VISIBLE);
	            }
	        }
	        else if ("onReceivedError".equals(id)) {
	            JSONObject d = (JSONObject) data;
	            try {
	                this.onReceivedError(d.getInt("errorCode"), d.getString("description"), d.getString("url"));
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	        }
	        else if ("exit".equals(id)) {
	            this.endActivity();
	        }
	        return null;
	    }
	 
	    protected Dialog splashDialog;

	    /**
	     * Removes the Dialog that displays the splash screen
	     */
	    public void removeSplashScreen() {
	        if (splashDialog != null && splashDialog.isShowing()) {
	            splashDialog.dismiss();
	            splashDialog = null;
	        }
	    }
	    
	    /**
	     * Shows the splash screen over the full Activity
	     */
	    @SuppressWarnings("deprecation")
	    protected void showSplashScreen(final int time) {
	        final Olutindo that = this;

	        Runnable runnable = new Runnable() {
	            public void run() {
	                // Get reference to display
	                Display display = getWindowManager().getDefaultDisplay();

	                // Create the layout for the dialog
	                LinearLayout root = new LinearLayout(that.getActivity());
	                root.setMinimumHeight(display.getHeight());
	                root.setMinimumWidth(display.getWidth());
	                root.setOrientation(LinearLayout.VERTICAL);
	                root.setBackgroundColor(that.getIntegerProperty("backgroundColor", Color.BLACK));
	                root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
	                        ViewGroup.LayoutParams.MATCH_PARENT, 0.0F));
	                root.setBackgroundResource(that.splashscreen);
	                
	                // Create and show the dialog
	                splashDialog = new Dialog(that, android.R.style.Theme_Translucent_NoTitleBar);
	                // check to see if the splash screen should be full screen
	                if ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
	                        == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
	                    splashDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	                }
	                splashDialog.setContentView(root);
	                splashDialog.setCancelable(false);
	                splashDialog.show();

	                // Set Runnable to remove splash screen just in case
	                final Handler handler = new Handler();
	                handler.postDelayed(new Runnable() {
	                    public void run() {
	                        removeSplashScreen();
	                    }
	                }, time);
	            }
	        };
	        this.runOnUiThread(runnable);
	    }
	    
	    /**
	     * Stop spinner - Must be called from UI thread
	     */
	    public void spinnerStop() {
	        if (this.spinnerDialog != null && this.spinnerDialog.isShowing()) {
	            this.spinnerDialog.dismiss();
	            this.spinnerDialog = null;
	        }
	    }

	    /**
	     * End this activity by calling finish for activity
	     */
	    public void endActivity() {
	        this.activityState = ACTIVITY_EXITING;
	        super.finish();
	    }

	@Override
	 /**
     * Launch an activity for which you would like a result when it finished. When this activity exits,
     * your onActivityResult() method will be called.
     *
     * @param command           The command object
     * @param intent            The intent to start
     * @param requestCode       The request code that is passed to callback to identify the activity
     */
    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        this.activityResultCallback = command;
        this.activityResultKeepRunning = this.keepRunning;

        // If multitasking turned on, then disable it for activities that return results
        if (command != null) {
            this.keepRunning = false;
        }

        // Start activity
        super.startActivityForResult(intent, requestCode);
    }
	
	 @Override
	    /**
	     * Called when an activity you launched exits, giving you the requestCode you started it with,
	     * the resultCode it returned, and any additional data from it.
	     *
	     * @param requestCode       The request code originally supplied to startActivityForResult(),
	     *                          allowing you to identify who this result came from.
	     * @param resultCode        The integer result code returned by the child activity through its setResult().
	     * @param data              An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
	     */
	    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	        Log.d(TAG, "Incoming Result");
	        super.onActivityResult(requestCode, resultCode, intent);
	        Log.d(TAG, "Request code = " + requestCode);
	        if (appView != null && requestCode == CordovaChromeClient.FILECHOOSER_RESULTCODE) {
	        	ValueCallback<Uri> mUploadMessage = this.appView.getWebChromeClient().getValueCallback();
	            Log.d(TAG, "did we get here?");
	            if (null == mUploadMessage)
	                return;
	            Uri result = intent == null || resultCode != Activity.RESULT_OK ? null : intent.getData();
	            Log.d(TAG, "result = " + result);
//	            Uri filepath = Uri.parse("file://" + FileUtils.getRealPathFromURI(result, this));
//	            Log.d(TAG, "result = " + filepath);
	            mUploadMessage.onReceiveValue(result);
	            mUploadMessage = null;
	        }
	        CordovaPlugin callback = this.activityResultCallback;
	        if(callback == null && initCallbackClass != null) {
	            // The application was restarted, but had defined an initial callback
	            // before being shut down.
	            this.activityResultCallback = appView.pluginManager.getPlugin(initCallbackClass);
	            callback = this.activityResultCallback;
	        }
	        if(callback != null) {
	        	Log.d(TAG, "We have a callback to send this result to");
	            callback.onActivityResult(requestCode, resultCode, intent);
	        }
	    }

		@Override
		public void setActivityResultCallback(CordovaPlugin plugin) {
			this.activityResultCallback = plugin;
		}
	 
	 /**
	     * Report an error to the host application. These errors are unrecoverable (i.e. the main resource is unavailable).
	     * The errorCode parameter corresponds to one of the ERROR_* constants.
	     *
	     * @param errorCode    The error code corresponding to an ERROR_* value.
	     * @param description  A String describing the error.
	     * @param failingUrl   The url that failed to load.
	     */
	    public void onReceivedError(final int errorCode, final String description, final String failingUrl) {
	        final Olutindo me = this;

	        // If errorUrl specified, then load it
	        final String errorUrl = me.getStringProperty("errorUrl", null);
	        if ((errorUrl != null) && (errorUrl.startsWith("file://") || Config.isUrlWhiteListed(errorUrl)) && (!failingUrl.equals(errorUrl))) {

	            // Load URL on UI thread
	            me.runOnUiThread(new Runnable() {
	                public void run() {
	                    // Stop "app loading" spinner if showing
	                    me.spinnerStop();
	                    me.appView.showWebPage(errorUrl, false, true, null);
	                }
	            });
	        }
	        // If not, then display error dialog
	        else {
	            final boolean exit = !(errorCode == WebViewClient.ERROR_HOST_LOOKUP);
	            me.runOnUiThread(new Runnable() {
	                public void run() {
	                    if (exit) {
	                        me.appView.setVisibility(View.GONE);
	                        me.displayError("Application Error", description + " (" + failingUrl + ")", "OK", exit);
	                    }
	                }
	            });
	        }
	    }
	    
	    /**
	     * Display an error dialog and optionally exit application.
	     *
	     * @param title
	     * @param message
	     * @param button
	     * @param exit
	     */
	    public void displayError(final String title, final String message, final String button, final boolean exit) {
	        final Olutindo me = this;
	        me.runOnUiThread(new Runnable() {
	            public void run() {
	                try {
	                    AlertDialog.Builder dlg = new AlertDialog.Builder(me);
	                    dlg.setMessage(message);
	                    dlg.setTitle(title);
	                    dlg.setCancelable(false);
	                    dlg.setPositiveButton(button,
	                            new AlertDialog.OnClickListener() {
	                                public void onClick(DialogInterface dialog, int which) {
	                                    dialog.dismiss();
	                                    if (exit) {
	                                        me.endActivity();
	                                    }
	                                }
	                            });
	                    dlg.create();
	                    dlg.show();
	                } catch (Exception e) {
	                    finish();
	                }
	            }
	        });
	    }
	    
	    /**
	     * Set boolean property on activity.
	     *
	     * @param name
	     * @param value
	     */
	    public void setBooleanProperty(String name, boolean value) {
	        Log.d(TAG, "Setting boolean properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
	        this.getIntent().putExtra(name.toLowerCase(), value);
	    }
	 
	  /**
	     * Get int property for activity.
	     *
	     * @param name
	     * @param defaultValue
	     * @return
	     */
	    public int getIntegerProperty(String name, int defaultValue) {
	        Bundle bundle = this.getIntent().getExtras();
	        if (bundle == null) {
	            return defaultValue;
	        }
	        name = name.toLowerCase(Locale.getDefault());
	        Integer p;
	        try {
	            p = (Integer) bundle.get(name);
	        } catch (ClassCastException e) {
	            p = Integer.parseInt(bundle.get(name).toString());
	        }
	        if (p == null) {
	            return defaultValue;
	        }
	        return p.intValue();
	    }
	 
	    /**
	     * Get string property for activity.
	     *
	     * @param name
	     * @param defaultValue
	     * @return
	     */
	    public String getStringProperty(String name, String defaultValue) {
	        Bundle bundle = this.getIntent().getExtras();
	        if (bundle == null) {
	            return defaultValue;
	        }
	        name = name.toLowerCase(Locale.getDefault());
	        String p = bundle.getString(name);
	        if (p == null) {
	            return defaultValue;
	        }
	        return p;
	    }
	    
	    
	    /*
	     * Hook in Cordova for menu plugins
	     *
	     */
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        this.postMessage("onCreateOptionsMenu", menu);
	        return super.onCreateOptionsMenu(menu);
	    }

	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        this.postMessage("onPrepareOptionsMenu", menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        this.postMessage("onOptionsItemSelected", item);
	        return true;
	    }
	    
	    @Override
	    public boolean onQueryTextSubmit(String query) {
	        //Toast.makeText(this, "You searched for: " + query, Toast.LENGTH_LONG).show();
	        Log.d(TAG, "You searched for: " + query);
	        this.postMessage("onQueryTextSubmit", query);
	        return true;
	    }

	    @Override
	    public boolean onQueryTextChange(String newText) {
	    	Log.d(TAG, "You searched for newText: " + newText);
	        return false;
	    }
	    
	    /**
	     * Send a message to all plugins.
	     *
	     * @param id            The message id
	     * @param data          The message data
	     */
	    public void postMessage(String id, Object data) {
	        if (this.appView != null) {
	            this.appView.postMessage(id, data);
	        }
	    }

	
//	@Override
//    public boolean onCreateOptionsMenu(final Menu menu) {
//		Log.d(TAG, "onCreateOptionsMenu actionbarsherlock version");
//		// Now it's safe to access ActionBarSherlockTabBarPlugin.instance because the plugin was instantiated
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run()
//            {
//                Log.d(TAG, "Creating the menu");
//            	createMenu(menu);
//            }
//        });
//
//        return true;
//    }
//	
//	private void createMenu(Menu menu) {
//		Log.d(TAG, "Setting sherlock, then creating the menu.");
//		
//		final ActionBar actionBar = mSherlock.getActionBar();
//
//		//Create the search view
//		SearchView searchView = new SearchView(actionBar.getThemedContext());
//		searchView.setQueryHint("Search for phone...");
//		//searchView.setOnQueryTextListener((OnQueryTextListener) this);
//		//searchView.setOnSuggestionListener((OnSuggestionListener) this);
//
//		menu.add("Search")
//		.setIcon(R.drawable.abs__ic_search)
//		.setActionView(searchView)
//		.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//		
//		String menuId = "Search";
//		int icon = R.drawable.abs__ic_search;
//		View view = searchView;
//		int actionEnum = MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
//		
//		//ActionBarSherlockMenuPlugin.instance.addMenuItem(menuId, icon, view, actionEnum, menu);
//	}



//    	private void createTabs()
//          {
//    		ActionBarSherlockTabBarPlugin.instance.setSherlock(sherlock);
//    		
//    		//sherlock.dispatchCreateOptionsMenu(menu)
//
//            // You can either provide a text by string literal or resource ID (R.string.something)
//            ActionBarSherlockTabBarPlugin.instance.addTab("tab-home", "Home", null);
//            ActionBarSherlockTabBarPlugin.instance.addTab("tab-settings", R.string.tab_settings, null);
//
//            // Icons are possible, too, but not together with a text label!
//            ActionBarSherlockTabBarPlugin.instance.addTab("tab-search", null, R.drawable.ic_action_search);
//            ActionBarSherlockTabBarPlugin.instance.addTab("tab-refresh", null, R.drawable.ic_action_refresh);
//            
//            final ActionBar actionBar = sherlock.getActionBar();
//
//            //Create the search view
//            SearchView searchView = new SearchView(actionBar.getThemedContext());
//            searchView.setQueryHint("Search for phone...");
//            searchView.setOnQueryTextListener(this);
//            searchView.setOnSuggestionListener(this);
//
////            if (mSuggestionsAdapter == null) {
////                MatrixCursor cursor = new MatrixCursor(COLUMNS);
////                cursor.addRow(new String[]{"1", "'Murica"});
////                cursor.addRow(new String[]{"2", "Canada"});
////                cursor.addRow(new String[]{"3", "Denmark"});
////                mSuggestionsAdapter = new SuggestionsAdapter(getActionBar().getThemedContext(), cursor);
////            }
//
////            searchView.setSuggestionsAdapter(mSuggestionsAdapter);
//            
//
//            Tab tab = actionBar.newTab();
//            tab.setIcon(R.drawable.ic_action_search);
//
////            .setActionView(searchView)
////            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//          }

//          @Override
//          public void onActionBarSherlockMenuPluginInitialized()
//          {
//              // Now it's safe to access ActionBarSherlockMenuPlugin.instance because the plugin was instantiated
//              runOnUiThread(new Runnable() {
//                  @Override
//                  public void run()
//                  {
//                      //createTabs();
//                	  Log.d(TAG, "onActionBarSherlockMenuPluginInitialized");
//                	  
//                  }
//              });
//          }
//
//		@Override
//		public boolean onSuggestionSelect(int position) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean onSuggestionClick(int position) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean onQueryTextSubmit(String query) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean onQueryTextChange(String newText) {
//			// TODO Auto-generated method stub
//			return false;
//		}
	
      }
