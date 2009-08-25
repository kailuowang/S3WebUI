package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.impl.s3.UserS3;
import com.thoughtDocs.model.impl.s3.UserS3Store;
import com.thoughtDocs.viewModel.ItemOpener;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Date;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;


public class DocumentDisplayItem extends AbstractDisplayItem {


    public DocumentDisplayItem(Document item) {
        super(item);
    }


    public Document getDocument() {
        return (Document) item;
    }

    public String getPublicUrl() {
        return "http://"+getCurrentUsernamePrefix() + getDocument().getPublicUrl();
    }

    private String getCurrentUsernamePrefix() {

        UserS3 currentUser = (UserS3) Component.getInstance("currentUser");
        String username = currentUser.getUsername();
        if(username.equals(UserS3Store.DEFAULT_USERNAME))
            return "";
        else
            return  username + ".";
    }


    @Override
    public void open(ItemOpener opener) throws IOException {
        opener.open(this.getDocument());
    }

    public String getIconFile() {
        return "/img/document.png";
    }

    public String getSize() {
       float size = getDocument().getSize();
       final float KB = 1024;
       final float MB = 1024 * KB;
       final float GB = 1024 * MB;
       DecimalFormat format = new DecimalFormat("#,###.##");

       if(size > GB )
            return format.format( size / GB ) + "GB";
       if(size > MB )
            return format.format( size / MB ) + "MB";
       if(size > KB )
            return format.format( size / KB ) + "KB";
       return format.format(size) + "Byte";
    }

    public String getLastModified(){
        Date date = getDocument().getLastModified();
        return date != null ? date.toString():"N/A";
    }
    
}
