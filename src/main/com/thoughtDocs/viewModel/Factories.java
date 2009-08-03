package com.thoughtDocs.viewModel;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import com.thoughtDocs.model.SecurityMode;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 11:41:56 PM
 */
@Name("factories")
public class Factories {

    @Factory("securityModes")
    public SecurityMode[] getSecurityModes() {
       return SecurityMode.values();
    }
}
