package com.shangwu.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

public class FTPUtils {
	private static FTPClient ftpClient;
	/**
	 * 
	 * @author liangjunfei
	 * @time 2017年4月24日
	 * @param ftpaddress ftp地址
	 * @param ftpPassword ftp密码
	 * @param ftpUserName ftp用户名
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpaddress, String ftpPassword,  
            String ftpUserName) {  
        try {       	
        		ftpClient = new FTPClient();  
                ftpClient.connect(ftpaddress);
                ftpClient.login(ftpUserName, ftpPassword);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
                System.out.println("未连接到FTP，用户名或密码错误。");  
                ftpClient.disconnect();  
            } else {  
            	 System.out.println("FTP连接成功。");  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
            System.out.println("FTP的IP地址可能错误，请正确配置。");  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("FTP的端口错误,请正确配置。");  
        }  
        return ftpClient;  
    }

	public static FTPClient getFTPClientByPort(String ftpaddress, int port,String ftpUserName, String ftpPassword) {
		try {
			ftpClient = new FTPClient();
			System.out.println("开始连接");
			ftpClient.connect(ftpaddress, port);
			System.out.println("开始登陆，用户名：" + ftpUserName);
			ftpClient.login(ftpUserName, ftpPassword);
			System.out.println("已连接");
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				System.out.println("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			} else {
				System.out.println("FTP连接成功。");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("FTP的IP地址可能错误，请正确配置。");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("FTP的端口错误,请正确配置。");
		}
		return ftpClient;
	}

	/**
	 * ftp 文件读取单个文件
	 * @author liangjunfei
	 * @time 2017年4月24日
	 * @param ftpaddress ftpip
	 * @param ftpUserName 用户名
	 * @param ftpPassword 密码
	 * @param ftpPath 文件路径
	 * @param fileName 文件名
	 * @return
	 */
	public static InputStream readFile(String ftpUserName, String ftpPassword,  
	        String ftpPath, String ftpaddress, String fileName){
	        InputStream in = null;         
	        try {  
	            ftpClient = getFTPClient(ftpaddress, ftpPassword, ftpUserName );  
	            ftpClient.setControlEncoding("utf-8");
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
	            ftpClient.enterLocalPassiveMode();  
	            ftpClient.changeWorkingDirectory(ftpPath);  
	            in = ftpClient.retrieveFileStream(fileName);  
	        }catch (Exception e){
	        	e.printStackTrace();
	        }finally{
	        	try {
					ftpClient.disconnect();
					System.out.println("ftp关闭");
				} catch (IOException e) {			
					e.printStackTrace();
				}
	        }
	        return in;
		}
	/**
	 *@author ruanhui
	 *@date 2018/10/8
	 *@description 读取集团指标回执文件
	 */
	public static InputStream readIntegratedFile(String ftpUserName, String ftpPassword,
	        String ftpPath, String ftpaddress,String port, String fileName){
	        InputStream in = null;
	        try {
//	            ftpClient = getFTPClient(ftpaddress, ftpPassword, ftpUserName );
				ftpClient = getFTPClientByPort(ftpaddress, Integer.parseInt(port), ftpUserName,ftpPassword);
	            ftpClient.setControlEncoding("utf-8");
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.changeWorkingDirectory(ftpPath);
	            in = ftpClient.retrieveFileStream(fileName);
	        }catch (Exception e){
	        	e.printStackTrace();
	        }finally{
	        	try {
					ftpClient.disconnect();
					System.out.println("ftp关闭");
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        return in;
		}

	/**
	 * 上传当文件
	 * @author liangjunfei
	 * @time 2017年4月25日
	 * @param in 文件流
	 * @param fileName 文件名
	 * @param filePath 文件路径
	 * @return
	 */
	public static boolean uploadFile(String ftpaddress,String ftpUserName,String ftpPwd,InputStream in,String fileName,String filePath){
		try {
			ftpClient = getFTPClient(ftpaddress, ftpPwd, ftpUserName );
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			int reply = ftpClient.getReplyCode();      
	        if (!FTPReply.isPositiveCompletion(reply)) {      
	        	ftpClient.disconnect();      
	            return false;      
	        } 
	        if(ftpClient.changeWorkingDirectory(filePath)){
	        	ftpClient.storeFile(fileName, in);
	        }else{
	        	ftpClient.makeDirectory(filePath);                
	        	ftpClient.changeWorkingDirectory(filePath);
	        	ftpClient.storeFile(fileName, in);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 *@author ruanhui
	 *@date 2018/9/29
	 *@description 上传集团文件数据
	 */
	public static boolean uploadFileForIntegrated(String ftpaddress,String ftpUserName,String ftpPwd,String port,InputStream in,String fileName,String filePath){
		//重命名文件
		String tempFileName= fileName + ".tmp";
		boolean storeFileFlag;
		try {
//			ftpClient = getFTPClient(ftpaddress, ftpPwd, ftpUserName );
			ftpClient = getFTPClientByPort(ftpaddress, Integer.parseInt(port), ftpUserName,ftpPwd);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			int reply = ftpClient.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	        	ftpClient.disconnect();
	            return false;
	        }
	        if(ftpClient.changeWorkingDirectory(filePath)){
				storeFileFlag = ftpClient.storeFile(tempFileName, in);
	        }else{
	        	ftpClient.makeDirectory(filePath);
	        	ftpClient.changeWorkingDirectory(filePath);
				storeFileFlag = ftpClient.storeFile(tempFileName, in);
	        }
			System.out.println("storeFile结果：" + storeFileFlag);
			System.out.println("集团文件上传完成");
			//上传完成之后再把名字改回来
			if(ftpClient.changeWorkingDirectory(filePath)) {
				ftpClient.rename(tempFileName,fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
