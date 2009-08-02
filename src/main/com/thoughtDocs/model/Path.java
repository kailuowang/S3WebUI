package com.thoughtDocs.model;

/**
 * Created by IntelliJ IDEA.
 * Date: Jul 29, 2009
 * Time: 7:04:29 PM
 * This is an immutable class to parse Path information
 */
public class Path {
    private String key;
    private String itemName;
    private String folderPath;

    public Path(String key) {
        this.key = key;
        int lastSlash = key.lastIndexOf("/");
        int itemNameStart = lastSlash < 0 ? 0 : lastSlash + 1;
        itemName = key.substring(itemNameStart, key.length());
        folderPath = key.substring(0, itemNameStart);
    }

    public String getFolderPath() {
        return folderPath;
    }

    public String getItemName() {
        return itemName;
    }
}
