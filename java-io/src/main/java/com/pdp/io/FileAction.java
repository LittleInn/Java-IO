package com.pdp.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

public class FileAction {
	private List<FileData> list = new ArrayList<>();

	public void findEqualsFiles(String path) throws NoSuchAlgorithmException,
			FileNotFoundException, IOException {
		HashMap<String, List<String>> mapEquals = new HashMap<>();
		List<FileData> allFilesFromDirectory = getAllFilesFromDirectory(path);
		for (FileData data : allFilesFromDirectory) {
			addFilesToMap(mapEquals, data.getHash(), data.getPath());
		}
		getEqualsFiles(mapEquals);
	}

	private List<FileData> getAllFilesFromDirectory(String path)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		File root = new File(path);
		File[] fileList = root.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				getAllFilesFromDirectory(file.getAbsolutePath());
			} else {
				String generateSHAHash = generateSHAHash(file.getAbsoluteFile());
				FileData data = new FileData(file.getAbsolutePath(),
						generateSHAHash);
				list.add(data);
			}
		}
		return list;
	}

	private void addFilesToMap(HashMap<String, List<String>> map, String key,
			String value) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<String>());
		}
		map.get(key).add(value);
	}

	private void getEqualsFiles(Map<String, List<String>> mapEquals) {
		for (Map.Entry<String, List<String>> entry : mapEquals.entrySet()) {
			if (entry.getValue().size() > 1) {
				System.out.println(entry.getValue());
			}
		}
	}

	private String generateSHAHash(File file) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] buffer = new byte[1024];
		byte[] bytes = null;
		try (FileInputStream fis = new FileInputStream(file)) {

			int readBytes = 0;
			while ((readBytes = fis.read(buffer)) != -1) {
				digest.update(buffer, 0, readBytes);
			}
			bytes = digest.digest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Hex.encodeHex(bytes));
	}
}
