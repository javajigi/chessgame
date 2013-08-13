package io;

import junit.framework.TestCase;

public class FileExplorerTest extends TestCase {
	public void testExplorer() throws Exception {
		FileExplorer explorer = new FileExplorer();
		explorer.showTree(".");
	}
}
