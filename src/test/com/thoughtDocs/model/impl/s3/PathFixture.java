package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Path;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 29, 2009
 * Time: 7:30:03 PM
 */
public class PathFixture {
    @Test
    public void testPathGetItemNameAndFolderPath() {
        Path p = new Path("/ge/lala.pdf");
        Assert.assertEquals(p.getItemName(), "lala.pdf");
        Assert.assertEquals(p.getFolderPath(), "/ge/");

    }
}
