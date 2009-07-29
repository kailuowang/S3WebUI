package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Path;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 29, 2009
 * Time: 7:34:49 PM
 */
abstract class AbstractItem {
    protected S3Object s3Object;

    public AbstractItem(S3Object obj) {
        s3Object = obj;
    }

    public String getName() {
        return new Path(getKey()).getItemName();
    }

    public abstract String getKey();


    public void save() throws IOException {
        s3Object.save();
    }

    public void update() throws IOException {
        s3Object.update();
    }

    public boolean isTransient() {
        return s3Object.isTransient();
    }
}
