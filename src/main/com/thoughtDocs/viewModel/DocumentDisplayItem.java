package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Document;
import org.jboss.seam.faces.FacesManager;

import java.io.IOException;

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

    public String getPassword() throws IOException {
        return getDocument().getPassword();
    }

    @Override
     public void open(ItemOpener opener) throws IOException {
        opener.open(this.getDocument());
    }

    
}
