cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/org.apache.cordova.plugin.version/www/version.js",
        "id": "org.apache.cordova.plugin.version.version",
        "clobbers": [
            "Version"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.vibration/www/vibration.js",
        "id": "org.apache.cordova.core.vibration.notification",
        "merges": [
            "navigator.notification"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.dialogs/www/notification.js",
        "id": "org.apache.cordova.core.dialogs.notification",
        "merges": [
            "navigator.notification"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.dialogs/www/android/notification.js",
        "id": "org.apache.cordova.core.dialogs.notification_android",
        "merges": [
            "navigator.notification"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.inappbrowser/www/InAppBrowser.js",
        "id": "org.apache.cordova.core.inappbrowser.InAppBrowser",
        "clobbers": [
            "window.open"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/DirectoryEntry.js",
        "id": "org.apache.cordova.core.file.DirectoryEntry",
        "clobbers": [
            "window.DirectoryEntry"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/DirectoryReader.js",
        "id": "org.apache.cordova.core.file.DirectoryReader",
        "clobbers": [
            "window.DirectoryReader"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/Entry.js",
        "id": "org.apache.cordova.core.file.Entry",
        "clobbers": [
            "window.Entry"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/File.js",
        "id": "org.apache.cordova.core.file.File",
        "clobbers": [
            "window.File"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileEntry.js",
        "id": "org.apache.cordova.core.file.FileEntry",
        "clobbers": [
            "window.FileEntry"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileError.js",
        "id": "org.apache.cordova.core.file.FileError",
        "clobbers": [
            "window.FileError"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileReader.js",
        "id": "org.apache.cordova.core.file.FileReader",
        "clobbers": [
            "window.FileReader"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileSystem.js",
        "id": "org.apache.cordova.core.file.FileSystem",
        "clobbers": [
            "window.FileSystem"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileUploadOptions.js",
        "id": "org.apache.cordova.core.file.FileUploadOptions",
        "clobbers": [
            "window.FileUploadOptions"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileUploadResult.js",
        "id": "org.apache.cordova.core.file.FileUploadResult",
        "clobbers": [
            "window.FileUploadResult"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/FileWriter.js",
        "id": "org.apache.cordova.core.file.FileWriter",
        "clobbers": [
            "window.FileWriter"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/Flags.js",
        "id": "org.apache.cordova.core.file.Flags",
        "clobbers": [
            "window.Flags"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/LocalFileSystem.js",
        "id": "org.apache.cordova.core.file.LocalFileSystem",
        "clobbers": [
            "window.LocalFileSystem"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/Metadata.js",
        "id": "org.apache.cordova.core.file.Metadata",
        "clobbers": [
            "window.Metadata"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/ProgressEvent.js",
        "id": "org.apache.cordova.core.file.ProgressEvent",
        "clobbers": [
            "window.ProgressEvent"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/requestFileSystem.js",
        "id": "org.apache.cordova.core.file.requestFileSystem",
        "clobbers": [
            "window.requestFileSystem"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file/www/resolveLocalFileSystemURI.js",
        "id": "org.apache.cordova.core.file.resolveLocalFileSystemURI",
        "clobbers": [
            "window.resolveLocalFileSystemURI"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file-transfer/www/FileTransferError.js",
        "id": "org.apache.cordova.core.file-transfer.FileTransferError",
        "clobbers": [
            "window.FileTransferError"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.core.file-transfer/www/FileTransfer.js",
        "id": "org.apache.cordova.core.file-transfer.FileTransfer",
        "clobbers": [
            "window.FileTransfer"
        ]
    },
    {
        "file": "plugins/com.borismus.webintent/www/webintent.js",
        "id": "com.borismus.webintent.WebIntent",
        "clobbers": [
            "WebIntent"
        ]
    },
    {
        "file": "plugins/com.simonmacdonald.prefs/src/android/applicationPreferences.js",
        "id": "com.simonmacdonald.prefs.applicationPreferences",
        "clobbers": [
            "applicationPreferences"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.plugin.sms/www/sms.js",
        "id": "org.apache.cordova.plugin.sms.Sms",
        "clobbers": [
            "window.sms"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.device/www/device.js",
        "id": "org.apache.cordova.device.device",
        "clobbers": [
            "device"
        ]
    }
]
});