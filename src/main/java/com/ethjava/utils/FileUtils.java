package com.chuangshi.cloud.blockart.ethereumwallet;

import jnr.ffi.Struct;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.io.*;
import java.net.HttpURLConnection;
import java.security.Key;
import java.security.SecureRandom;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	// 加密算是是des
	private static final String ALGORITHM = "DES";
	// 转换格式
	private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";

	private static String keyStr = "p8k1d5y9";

//	private static String dirEncryptPath = "\\encrypt";
////	private static String dirDecryptPath = "\\dencrypt";

//	public static void main(String[] args) {
//
//		try {
//			
//			 TestDES td = new TestDES("aaa");   
//			 td.encrypt("e:/r.txt", "e:/r解密.txt"); //加密   
//			 td.decrypt("e:/r解密.txt", "e:/r1.txt"); //解密  
//			String fileName = "D:\\Users\\wallet\\Ethereum\\UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4e";
//			String fileNameenc = "D:\\Users\\wallet\\Ethereum\\UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4f";
//			String fileNamedec = "D:\\Users\\wallet\\Ethereum\\UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4g";

			//encrypt(fileName, fileNameenc);
			//decrypt(fileNameenc, fileNamedec);
//			String url = "http://localhost:8088/upload/1000060937822658562/2b6b2225aeae5a2b99a8a1a3feec7995/original/UTC--2018-08-08T10-11-8.232Z--2ca9302ffc5bfa20a055e775dd63c34737bd6151";
//			downLoadFileFromUrl(url);
//			decrypt("UTC--2018-08-08T10-11-8.232Z--2ca9302ffc5bfa20a055e775dd63c34737bd6151","UTC--2018-08-08T10-11-8.232Z--2ca9302ffc5bfa20a055e775dd63c34737bd61510");
			//https://api-ropsten.etherscan.io/api?module=transaction&action=getstatus&txhash=0x52199b1436f63322dda52bc7b9efa6e63c21a231825a35b5299236e171e944a0&apikey=YourApiKeyToken
//			String url = String.format(WalletEnvironment.CheckContractStatus,"0x740b13816d6107a22798da2df2a4caafca1a573b1fcfc94c66af5924f1f81940");
//			System.out.println("url:"+url);
//			sendHttpGet(url);
//			url = String.format(WalletEnvironment.CheckContractStatus,"0x52199b1436f63322dda52bc7b9efa6e63c21a231825a35b5299236e171e944a0");
//			System.out.println("url:"+url);
//			sendHttpGet(url);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String encryptFile(String fileName, String fileContent) {
		String tmpFileName = "temp.tmp";// dirEncryptPath + File.separator + "temp.tmp";
		saveFileByStr(tmpFileName, fileContent);
		String destFileName = fileName;//dirEncryptPath + File.separator + fileName;
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

	public  static  void deleteFile()
	{
		String tmpFileName ="temp.tmp"; //dirEncryptPath + File.separator + "temp.tmp";
		File file = new File(tmpFileName);
		if (file.exists()&&file.isFile()) {
			 file.delete();
		//	System.out.println(rets);
			//System.out.println("tmpFileName:" + tmpFileName);

		}
	}

	public  static  void deleteFileByFileName(String fileName)
	{
	//	String tmpFileName ="temp.tmp"; //dirEncryptPath + File.separator + "temp.tmp";
		File file = new File(fileName);
		if (file.exists()&&file.isFile()) {
			file.delete();
			//	System.out.println(rets);
			//System.out.println("tmpFileName:" + tmpFileName);

		}
	}

	public static String readKeyPairFromFile(String fileName) {
		String destFileName =fileName;//dirDecryptPath + File.separator + fileName;
		File file = new File(destFileName);
		if (!file.exists()) {
			return "";
		}

		try {
			InputStream inputStream = new FileInputStream(file);
			byte[] bs = new byte[(int) file.length()];
			inputStream.read(bs);
			inputStream.close();

			//System.out.println(new String(bs));
			return new String(bs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *
	 * @param fileName 文件绝对路径
	 * @return 字节数组
	 */
	public static    byte[] readByteArryFromFile(String fileName) {
		String destFileName =fileName;//dirDecryptPath + File.separator + fileName;
		File file = new File(destFileName);
		if (!file.exists()) {
			return null;
		}

		try {
			InputStream inputStream = new FileInputStream(file);
			byte[] bs = new byte[(int) file.length()];
			inputStream.read(bs);
			inputStream.close();

			//System.out.println(new String(bs));
			return  bs;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取路径下的所有文件/文件夹
	 * @param directoryPath 需要遍历的文件夹路径
	 * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
	 * @return
	 */
	public static List<String> getAllFile(String directoryPath,boolean isAddDirectory) {
		List<String> list = new ArrayList<String>();
		File baseFile = new File(directoryPath);
		if (baseFile.isFile() || !baseFile.exists()) {
			return list;
		}
		File[] files = baseFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				if(isAddDirectory){
					list.add(file.getAbsolutePath());
				}
				list.addAll(getAllFile(file.getAbsolutePath(),isAddDirectory));
			} else {
				list.add(file.getAbsolutePath());
			}
		}
		return list;
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
	 *  @param fileName 源文件
	 * @param dest 已加密的文件 如c:/加密后文件.txt * @param destFile 解密后存放的文件名 如c:/
	 *             test/解密后文件.txt
	 */
	public static void decrypt(String fileName, String dest) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, getKey(keyStr));
			String fileSrcName =fileName;//dirEncryptPath + File.separator + fileName;
			String fileDestName =dest;// dirDecryptPath + File.separator + dest;
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

	/**
	 *
	 * @param fileUrl 文件URL链接
	 */
	public static  String downLoadFileFromUrl(String fileUrl)
	{
		int start = fileUrl.lastIndexOf("/");
		String urlFileName = fileUrl.substring(start+1);
		try {
			URL url = new URL(fileUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(3*1000);
			//防止屏蔽程序抓取而返回403错误
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			InputStream inputStream = connection.getInputStream();
			byte[] getData = readInputStream(inputStream);
			//String fileName = connection.getURL().getFile();

			//System.out.println("downLoadFileFromUrl-fileName:"+fileName);
			File file = new File(urlFileName);

			FileOutputStream fos = new FileOutputStream(file);
			fos.write(getData);
			if (fos!=null)
			{
				fos.close();
			}
			if (inputStream!=null)
			{
				inputStream.close();
			}
			System.out.println("info:"+url+" download success");
		}catch (Exception e)
		{
			e.printStackTrace();
			urlFileName="";
		}

		return urlFileName;


	}

	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static  byte[] readInputStream(InputStream inputStream)  {
		try {
			byte[] buffer = new byte[1024];
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.close();
			return bos.toByteArray();
		}catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static  String sendHttpGet(String url)
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
	//	String retJson="";
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
			String result = null;
			HttpEntity entity = response.getEntity();
			if (entity!=null){
				result = EntityUtils.toString(entity);
				//System.out.println("result:"+result);
			}
			return  result;

		}catch (IOException e1)
		{
			e1.printStackTrace();
			return null;
		}
	}


	}
