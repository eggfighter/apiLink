package com.kt.psso.onm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

public class FileUtil {

	public static void write(String src, String fileNameWithPath) throws Exception
	{
		File file = new File(fileNameWithPath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(src.getBytes());
		fileOutputStream.close();
	}

	public static void write(String src, String fileNameWithPath, String encoding) throws Exception
	{
		File file = new File(fileNameWithPath);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(src.getBytes(encoding));
		fileOutputStream.close();
	}

	/**
	 * Delete the supplied {@link File} - for directories,
	 * recursively delete any nested directories or files as well.
	 * @param root the root <code>File</code> to delete
	 * @return <code>true</code> if the <code>File</code> was deleted,
	 * otherwise <code>false</code>
	 */
	public static boolean deleteRecursively(File root) {
		if (root != null && root.exists()) {
			if (root.isDirectory()) {
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						deleteRecursively(child);
					}
				}
			}
			return root.delete();
		}
		return false;
	}

	public static boolean deleteRecursivelyWithFormat(File root, String format) {
		if (root != null && root.exists()) {
			if (root.isDirectory()) {
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						deleteRecursivelyWithFormat(child, format);
					}
				}
			}
			else
			{
				System.out.println("============");
				System.out.println("root.getName() : " + root.getName());
				System.out.println("format : " + format);
				System.out.println("============");

				if(root.getName().indexOf(format) >= 0)
				{
					System.out.println("[DELETE]");

					return root.delete();
				}
			}
		}
		return false;
	}

	/**
	 * Recursively copy the contents of the <code>src</code> file/directory
	 * to the <code>dest</code> file/directory.
	 * @param src the source directory
	 * @param dest the destination directory
	 * @throws IOException in the case of I/O errors
	 */
	public static void copyRecursively(File src, File dest) throws IOException {
		Assert.isTrue(src != null && (src.isDirectory() || src.isFile()), "Source File must denote a directory or file");
		Assert.notNull(dest, "Destination File must not be null");
		doCopyRecursively(src, dest);
	}

	/**
	 * Actually copy the contents of the <code>src</code> file/directory
	 * to the <code>dest</code> file/directory.
	 * @param src the source directory
	 * @param dest the destination directory
	 * @throws IOException in the case of I/O errors
	 */
	private static void doCopyRecursively(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			dest.mkdir();
			File[] entries = src.listFiles();
			if (entries == null) {
				throw new IOException("Could not list files in directory: " + src);
			}
			for (File entry : entries) {
				doCopyRecursively(entry, new File(dest, entry.getName()));
			}
		}
		else if (src.isFile()) {
			try {
				dest.createNewFile();
			}
			catch (IOException ex) {
				IOException ioex = new IOException("Failed to create file: " + dest);
				ioex.initCause(ex);
				throw ioex;
			}
			FileCopyUtils.copy(src, dest);
		}
		else {
			// Special File handle: neither a file not a directory.
			// Simply skip it when contained in nested directory...
		}
	}

	/**
	 * Recursively copy the contents of the <code>src</code> file/directory
	 * to the <code>dest</code> file/directory.
	 * @param src the source directory
	 * @param dest the destination directory
	 * @throws IOException in the case of I/O errors
	 */
	public static void copyRecursivelyWithFormat(File src, File dest, String format) throws IOException {
		Assert.isTrue(src != null && (src.isDirectory() || src.isFile()), "Source File must denote a directory or file");
		Assert.notNull(dest, "Destination File must not be null");
		doCopyRecursivelyWithFormat(src, dest, format);
	}

	/**
	 * Actually copy the contents of the <code>src</code> file/directory
	 * to the <code>dest</code> file/directory.
	 * @param src the source directory
	 * @param dest the destination directory
	 * @throws IOException in the case of I/O errors
	 */
	private static void doCopyRecursivelyWithFormat(File src, File dest, String format) throws IOException {
		if (src.isDirectory()) {
			dest.mkdir();
			File[] entries = src.listFiles();
			if (entries == null) {
				throw new IOException("Could not list files in directory: " + src);
			}
			for (File entry : entries) {
				doCopyRecursivelyWithFormat(entry, new File(dest, entry.getName()), format);
			}
		}
		else if (src.isFile()) {

			if(src.getName().indexOf(format) < 0)
			{
				return;
			}

			try {
				dest.createNewFile();
			}
			catch (IOException ex) {
				IOException ioex = new IOException("Failed to create file: " + dest);
				ioex.initCause(ex);
				throw ioex;
			}
			FileCopyUtils.copy(src, dest);
		}
		else {
			// Special File handle: neither a file not a directory.
			// Simply skip it when contained in nested directory...
		}
	}

	public static String read(String fileNameWithPath, String encoding) throws Exception
	{
		File file = new File(fileNameWithPath);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		String readLine = null;
		StringBuffer stringBuffer = new StringBuffer();

		while((readLine = bufferedReader.readLine()) != null)
		{
			stringBuffer.append(readLine);
			stringBuffer.append("\n");
		}

		bufferedReader.close();

		return stringBuffer.toString();
	}
}