package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import org.jboss.seam.faces.FacesManager;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:10:13 PM
 */
public class DocumentDisplayItem extends AbstractDisplayItem{

    public DocumentDisplayItem(Document item) {
        super(item);
    }

    public Document getDocument(){
        return (Document) item;
    }

    public String getPublicUrl() {
        return getDocument().getPublicUrl();
    }

    public String getPassword() {
        return null;
    }

    @Override
     public Folder open() {
        FacesManager.instance().redirectToExternalURL((getDocument().getSignedURL()));
        return null;
    }
}
