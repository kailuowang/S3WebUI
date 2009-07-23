package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.QueryStringAuthGenerator;
import com.thoughtDocs.model.Document;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 20, 2009
 * Time: 4:29:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignedURLGenerator {
    QueryStringAuthGenerator generator;

    public SignedURLGenerator(QueryStringAuthGenerator generator) {
        this.generator = generator;
        generator.setExpiresIn(10000);
    }

    public String getSignedURL(Document document) {
        return generator.get(document.getRepository().getName(), document.getName(), null);
    }

}
