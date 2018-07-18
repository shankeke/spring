package com.shanke.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class IOTest {

	public static void main(String[] args) {
		Reader reader = null;
		Writer writer = null;

		InputStream in = null;
		OutputStream out = null;

		Closeable c = null;
		Flushable f = null;
	}

	@Test
	public void testFileReader() throws IOException {
		FileInputStream in = new FileInputStream(new File("F:/in.txt"));
		FileOutputStream out = new FileOutputStream("F:/out.txt");
		byte[] b = new byte[4096];
		int n = 0;
		while ((n = in.read(b, 0, b.length)) != -1) {
			out.write(b);
		}
		out.flush();
		out.close();
		in.close();
	}
}
