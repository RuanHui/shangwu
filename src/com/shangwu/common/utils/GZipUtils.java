package com.shangwu.common.utils;

//import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
//import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
//import org.apache.commons.compress.compressors.gzip.GzipParameters;
//import org.apache.commons.compress.utils.IOUtils;
//import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * 项目名称:一级渠道运营分析平台
 * 类名称:  GZipUtils
 * 创建人:  guojinggan
 * 创建时间:2018年7月30日 下午7:07:28
 * 版本：    V1.0.0
 */
public abstract class GZipUtils {

	public static final int BUFFER = 1024;
	public static final String EXT = ".gz";

	/**
	 * 数据压缩
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] compress(byte[] data) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 压缩
		compress(bais, baos);

		byte[] output = baos.toByteArray();

		baos.flush();
		baos.close();

		bais.close();

		return output;
	}

	/**
	 * 文件压缩
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void compress(File file) throws Exception {
		compress(file, true);
	}

	/**
	 * 文件压缩
	 * 
	 * @param file
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static String compress(File file, boolean delete) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);

		compress(fis, fos);

		fis.close();
		fos.flush();
		fos.close();

		if (delete) {
			file.delete();
		}
//		new File(file.getPath()+EXT).renameTo(new File("C:\\Users\\aRunn\\Desktop\\1.gz"));

		String rename = rename(file.getPath() + EXT);
		return rename.replaceAll("/","");
	}
	/**
	 * 文件压缩
	 *
	 *            是否删除原始文件
	 * @throws Exception
	 */
	/*public static String compress(String input, String output, String name) throws Exception {
		String compress_name = null;
		if (name != null) {
			compress_name = name;
		} else {
			compress_name = new File(input).getName();
		}
		byte[] buffer = new byte[1024];
		try {
			GzipParameters gp = new GzipParameters();  //设置压缩文件里的文件名
			gp.setFilename(compress_name);
			GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(new FileOutputStream(output), gp);
			FileInputStream fis = new FileInputStream(input);
			int length;
			while ((length = fis.read(buffer)) > 0) {
				gcos.write(buffer, 0, length);
			}
			fis.close();
			gcos.finish();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return "";
//		return output.substring(output.lastIndexOf("/"));
	}*/

	/**
	 * 数据压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void compress(InputStream is, OutputStream os) throws Exception {

		GZIPOutputStream gos = new GZIPOutputStream(os);

		int count;
		byte data[] = new byte[BUFFER];
		while ((count = is.read(data, 0, BUFFER)) != -1) {
			gos.write(data, 0, count);
		}
		gos.finish();

		gos.flush();
		gos.close();
	}

	/**
	 * 文件压缩
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void compress(String path) throws Exception {
		compress(path, true);
	}

	/**
	 * 文件压缩
	 * 
	 * @param path
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static String compress(String path, boolean delete) throws Exception {
		File file = new File(path);
		String compressName = compress(file, delete);
		return compressName;
	}

	/**
	 * 数据解压缩
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decompress(byte[] data) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 解压缩

		decompress(bais, baos);

		data = baos.toByteArray();

		baos.flush();
		baos.close();

		bais.close();

		return data;
	}

	/**
	 * 文件解压缩
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void decompress(File file) throws Exception {
		decompress(file, true);
	}

	/**
	 * 文件解压缩
	 * 
	 * @param file
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static void decompress(File file, boolean delete) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT, ""));
		decompress(fis, fos);
		fis.close();
		fos.flush();
		fos.close();

		if (delete) {
			file.delete();
		}
	}

	/**
	 * 数据解压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void decompress(InputStream is, OutputStream os) throws Exception {

		GZIPInputStream gis = new GZIPInputStream(is);

		int count;
		byte data[] = new byte[BUFFER];
		while ((count = gis.read(data, 0, BUFFER)) != -1) {
			os.write(data, 0, count);
		}

		gis.close();
	}

	/**
	 * 文件解压缩
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void decompress(String path) throws Exception {
		decompress(path, true);
	}

	/**
	 * 文件解压缩
	 * 
	 * @param path
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static void decompress(String path, boolean delete) throws Exception {
		File file = new File(path);
		decompress(file, delete);
	}

	//重命名，去掉.txt
	public static String rename(String path) {
//		String newName = path.replace(".txt","");
//		String newName = path.substring(0,path.lastIndexOf(".txt")) + ".gz";
//		new File(path).renameTo(new File(newName));
//		return newName.substring(newName.lastIndexOf("\\"));
//		return "";
		return path.substring(path.lastIndexOf("/"));
//		return path.substring(path.lastIndexOf("\\"));
	}

	public static void main(String[] args) throws Exception {


//		 FileOutputStream fos = new FileOutputStream("C:\\Users\\aRunn\\Desktop\\520000_201806_0001_00.txt");
		//
		// fos.write("1111111111111111111111111111111".getBytes());
		// fos.flush();
		// fos.close();
		//


//		GZipUtils.compress("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.txt", false);
		GZipUtils.decompress("C:\\Users\\aRunn\\Desktop\\520000_201808_0001_01.txt.gz", false);
//		compress("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.txt","C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.gz",null);
//		GZipUtils.compress(new FileInputStream("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.txt"),new FileOutputStream("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.gz"));

		//
//		 GZipUtils.decompress("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.gz", false);
//		doCompress(new File("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.txt"),new File("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.gz"));
//		doDecompress(new File("C:\\Users\\aRunn\\Desktop\\520000_20180820_0001_00.gz"),new File("C:\\Users\\aRunn\\Desktop"));
		//
		// File file = new File("d:/f.txt");
		//
		// FileInputStream fis = new FileInputStream(file);
		//
		// DataInputStream dis = new DataInputStream(fis);
		//
		// byte[] data = new byte[(int) file.length()];
		// dis.readFully(data);
		//
		// fis.close();

		/*File file = new File("d:/test11111.txt");
		PrintWriter fw = null;
		OutputStreamWriter os = null;
		FileOutputStream fs = null;
		try {
			boolean is = file.createNewFile();
			System.out.println(is);
			fs = new FileOutputStream(file);
			os = new OutputStreamWriter(fs, "UTF-8");
			fw = new PrintWriter(os);
			fw.println("22222222222222222222222222222");
			fw.flush();
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {

				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {

				}
			}
			if (fw != null) {
				fw.close();
			}
			compress(file);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
/*
	public static void doCompress(File srcFile, File destFile) throws IOException {
		OutputStream out = null;
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(srcFile), BUFFER);
			out = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), BUFFER));
			IOUtils.copy(is, out);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(out);
		}
	}

	public static void doDecompress(File srcFile, File destDir) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			File destFile = new File(destDir, FilenameUtils.getBaseName(srcFile.toString()));
			is = new GzipCompressorInputStream(new BufferedInputStream(new FileInputStream(srcFile), BUFFER));
			os = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER);
			IOUtils.copy(is, os);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}*/


}
