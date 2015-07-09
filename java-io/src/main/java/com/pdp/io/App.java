package com.pdp.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App {
	public static void main(String[] args) throws NoSuchAlgorithmException,
			FileNotFoundException, IOException {
		FileAction action = new FileAction();
		action.findEqualsFiles(System.getenv("file.path"));
	}
}
