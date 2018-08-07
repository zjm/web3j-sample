package com.ethjava.utils;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class FileUtils {

	// 加密算是是des
	private static final String ALGORITHM = "DES";
	// 转换格式
	private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";

	private static String keyStr = "12345678";

	private static String dirEncryptPath = "D:\\Users\\wallet\\Ethereum\\encrypt";
	private static String dirDecryptPath = "D:\\Users\\wallet\\Ethereum\\dencrypt";

	public static void main(String[] args) {

		try {
//			
//			 TestDES td = new TestDES("aaa");   
//			 td.encrypt("e:/r.txt", "e:/r解密.txt"); //加密   
//			 td.decrypt("e:/r解密.txt", "e:/r1.txt"); //解密  
			String fileName = "D:\\Users\\wallet\\Ethereum\\UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4e";
			String fileNameenc = "D:\\Users\\wallet\\Ethereum\\UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4f";
			String fileNamedec = "D:\\Users\\wallet\\Ethereum\\UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4g";

			encrypt(fileName, fileNameenc);
			decrypt(fileNameenc, fileNamedec);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean makeDir(String dir) {
		File filePath = new File(dir);

		try {
			if (!filePath.exists()) {
				return filePath.mkdirs();
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据filePath创建相应的目录
	 * 
	 * @param filePath 要创建的文件路经
	 * @return file 文件
	 * @throws IOException
	 */
	public static File mkdirFiles(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();

		return file;
	}

	private static void saveFileByStr(String fileName, String fileContent) {
		File file = new File(fileName);
		OutputStream outputStream = null;
		if (file.exists()) {

			file.delete();
		}

		try {
			file.createNewFile();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			outputStream = new FileOutputStream(file);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		byte[] bs = fileContent.getBytes();

		try {
			outputStream.write(bs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String encryptFile(String fileName, String fileContent) {
		String tmpFileName = dirEncryptPath + File.separator + "temp";
		saveFileByStr(tmpFileName, fileContent);
		String destFileName = dirEncryptPath + File.separator + fileName;
		try {
			boolean ret = encrypt(tmpFileName, destFileName);
			if (ret) {
				return destFileName;
			} else {
				return "";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static String readKeyPairFromFile(String fileName) {
		String destFileName = dirDecryptPath + File.separator + fileName;
		File file = new File(destFileName);
		if (!file.exists()) {
			return "";
		}

		try {
			InputStream inputStream = new FileInputStream(file);
			byte[] bs = new byte[(int) file.length()];
			inputStream.read(bs);
			inputStream.close();

			System.out.println(new String(bs));
			return new String(bs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 根据参数生成KEY
	 */
	public static Key getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			Key key = _generator.generateKey();
			_generator = null;
			return key;
		} catch (Exception e) {
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		}
	}

	/**
	 * 文件file进行加密并保存目标文件destFile中
	 * 
	 * @param file     要加密的文件 如c:/test/srcFile.txt
	 * @param destFile 加密后存放的文件名 如c:/加密后文件.txt
	 */
	public static boolean encrypt(String file, String destFile) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			// cipher.init(Cipher.ENCRYPT_MODE, getKey());
			cipher.init(Cipher.ENCRYPT_MODE, getKey(keyStr));
			InputStream is = new FileInputStream(file);
			OutputStream out = new FileOutputStream(destFile);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = cis.read(buffer)) > 0) {
				out.write(buffer, 0, r);
			}
			cis.close();
			is.close();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 文件采用DES算法解密文件
	 * 
	 * @param file 已加密的文件 如c:/加密后文件.txt * @param destFile 解密后存放的文件名 如c:/
	 *             test/解密后文件.txt
	 */
	public static void decrypt(String fileName, String dest) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, getKey(keyStr));
			String fileSrcName = dirEncryptPath + File.separator + fileName;
			String fileDestName = dirDecryptPath + File.separator + dest;
			InputStream is = new FileInputStream(fileSrcName);
			OutputStream out = new FileOutputStream(fileDestName);
			CipherOutputStream cos = new CipherOutputStream(out, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = is.read(buffer)) >= 0) {
				System.out.println();
				cos.write(buffer, 0, r);
			}
			cos.close();
			out.close();
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
