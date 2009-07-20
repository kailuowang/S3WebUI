package com.thoughtDocs.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class RepositoryListTest extends SeamTest {

	//@Test
	public void test_repositoryList() throws Exception {
		new FacesRequest("/repositoryList.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{repositoryList.repositoryList}");
			}
		}.run();
	}
}
