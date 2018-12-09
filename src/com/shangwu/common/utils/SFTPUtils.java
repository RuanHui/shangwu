package com.shangwu.common.utils;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 
 *SFTP工具类
 *@Description:
 *@Author:youjian
 *@Since:2017年9月25日
 */
public class SFTPUtils {

	private final static Logger log = Logger
			.getLogger(SFTPUtils.class);

	public  	ChannelSftp channelSftp;
	public     Session	session;
	public String attchmentProperties = "attachment_address.properties";
	
	/**
	 * 获取sftp连接
	 * @param ftpaddress
	 * @param ftpPassword
	 * @param ftpUserName
	 * @return
	 * @Author:youjian
	 * @Since:2017年9月21日
	 */
	public  ChannelSftp getSFTPClient(String ftpaddress, String ftpPassword,  
            String ftpUserName,String ftpPort) {
		//开始时间  用于计时	@author ruanhui  20171214
		long startTime = System.currentTimeMillis();
		JSch jsch = new JSch();// 创建JSch对象
	    	Channel channel = null;
	        try {
              session = jsch.getSession(ftpUserName, ftpaddress,Integer.valueOf(ftpPort)); // 根据用户名，主机ip，端口获取一个Session对象
          
              session.setPassword(ftpPassword); // 设置密码
            
              Properties config = new Properties();
              config.put("StrictHostKeyChecking", "no");
              session.setConfig(config); // 为Session对象设置properties
            //  session.setTimeout(timeout); // 设置timeout时间
              session.connect(); // 通过Session建立链接
           
              channel = session.openChannel("sftp"); // 打开SFTP通道
              channel.connect(); // 建立SFTP通道的连接
				long endTime = System.currentTimeMillis();
				log.debug("连接sftp耗时" + (endTime - startTime) + "毫秒");
				System.out.println("连接sftp耗时" + (endTime - startTime) + "毫秒");
				return (ChannelSftp) channel;
	         
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        return null;  
    }  
	/**
	 * 上传文件
	 * @param ftpaddress
	 * @param ftpUserName
	 * @param ftpPwd
	 * @param in
	 * @param fileName
	 * @param filePath
	 * @return
	 * @Author:youjian
	 * @Since:2017年9月22日
	 */
	public  boolean uploadFile(String ftpaddress,  String ftpUserName, String ftpPassword,  String ftpPort,
							   InputStream in,String fileName,String filePath){

		try{
			ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);
			File dir=new File(filePath);
			if(!dir.exists())
				dir.mkdirs();

			channelSftp.put(in, filePath+"/"+fileName);
			return true;
		}catch(Exception e){
			e.printStackTrace();
            closeSftp();
		}
		return false;

	}


	/**
	 * 上传一体化文件
	 * @param ftpaddress
	 * @param ftpUserName
	 * @param in
	 * @param fileName
	 * @param filePath
	 */
	public  boolean uploadFileForIntegrated(String ftpaddress,  String ftpUserName, String ftpPassword,  String ftpPort,
											InputStream in,String fileName,String filePath){
		//重命名文件
		String tempFileName= fileName + ".tmp";
		try{
			System.out.println("开始连接sftp...");
			ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);
			System.out.println("已连接sftp...");
			File dir=new File(filePath);
			if(!dir.exists())
				dir.mkdirs();

			channelSftp.put(in, filePath+"/"+tempFileName);
			//上传完成之后再把名字改回来
			channelSftp.rename(filePath+"/"+tempFileName,filePath+"/"+fileName);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 上传文件
	 * @Author:ruanhui
	 * @Since:2018年7月12日
	 */
	public boolean uploadFile(InputStream in,String fileName,String moduleName){

		try{
			//先从配置文件读取IP地址和用户名密码、路径
			String ftpaddress = PropertiesUtils.getSchema(attchmentProperties,"ip");
			String ftpUserName = PropertiesUtils.getSchema(attchmentProperties,"username");
			String ftpPassword = PropertiesUtils.getSchema(attchmentProperties,"password");
			String ftpPort = PropertiesUtils.getSchema(attchmentProperties,"port");
			String filePath = PropertiesUtils.getSchema(attchmentProperties,"path");
			//建立连接
			ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);
			//判断文件夹是否存在，不存在则创建
			if (isDirExist(filePath + "/" + moduleName,channelSftp)) {
                channelSftp.put(in, filePath + "/" + moduleName + "/"+fileName);
                return true;
            } else {
			    //创建文件夹在上传文件
			    createDir(filePath + "/" + moduleName,channelSftp);
                channelSftp.put(in, filePath + "/" + moduleName + "/"+fileName);
                return true;
            }
		}catch(Exception e){
			e.printStackTrace();
            closeSftp();
		}
		return false;

	}
	/**
	 * 读取sftp文件
	 * @author youjian
	 * 2017年9月22日
	 * @param ftpaddress
	 * @param ftpPassword
	 * @param ftpUserName
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public  InputStream readFile( String ftpUserName,String ftpPassword, String ftpPort,
			String filePath,String ftpaddress, String fileName){
		
		
			ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);
		
			try {
				return channelSftp.get(filePath+"/"+fileName);
			} catch (SftpException e) {
				System.out.println("获取 ："+filePath+"/"+fileName+" 文件流错误");
                closeSftp();
			}
			
		
		return null;
		
	}
	/**
	 * 读取sftp文件
	 * @author ruanhui
	 * 2018年7月12日
	 */
	public  InputStream readFile(String moudleName,String fileName){

		//先从配置文件读取IP地址和用户名密码、路径
		String ftpaddress = PropertiesUtils.getSchema(attchmentProperties,"ip");
		String ftpUserName = PropertiesUtils.getSchema(attchmentProperties,"username");
		String ftpPassword = PropertiesUtils.getSchema(attchmentProperties,"password");
		String ftpPort = PropertiesUtils.getSchema(attchmentProperties,"port");
		String filePath = PropertiesUtils.getSchema(attchmentProperties,"path");
		//建立连接
		ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);

		try {
			return channelSftp.get(filePath+"/" + moudleName + "/"+fileName);
		} catch (SftpException e) {
			System.out.println("获取 ："+filePath+"/"+fileName+" 文件流错误");
            closeSftp();
		}


		return null;

	}
	
	
	/**
	 * 
	 * 关闭sftp
	 * @Author:youjian
	 * @Since:2017年9月25日
	 */
	public  void closeSftp() {
		 if (channelSftp != null) {
	        	channelSftp.disconnect();
	        }
	        if (session != null) {
	        	session.disconnect();
	        }
	}

	//测试
	public static void main(String[] args) {
		SFTPUtils sftpUtils = new SFTPUtils();
		try {
			/*FileInputStream file = new FileInputStream("D:\\abc文档 - 副本.txt");
//			FileInputStream file = new FileInputStream("D:\\abc文档.txt");
			sftpUtils.uploadFile(file,"abc文档1.txt","O2O");
			sftpUtils.closeSftp();*/
			InputStream inputStream = sftpUtils.readFile("O2O","abc文档.txt");
			FileOutputStream  fos = new FileOutputStream("D:\\aaa测试qqq.txt");
			byte[]  buff = new byte[1024];
			int length = 0;
			while((length = inputStream.read(buff))!=-1){
				fos.write(buff, 0, length);
			}
			inputStream.close();
			fos.close();
            sftpUtils.closeSftp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


    /**
     * 创建一个文件目录
     */
    public void createDir(String createpath, ChannelSftp sftp) {
        try {
            if (isDirExist(createpath,sftp)) {
                sftp.cd(createpath);
                return;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString(),sftp)) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(createpath);
        } catch (SftpException e) {
//            throw new SystemException("创建路径错误：" + createpath);
        }
    }

	//判断目录是否存在
    public boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }



	/**
	 * @author : ruanhui
	 * @date : 2017/11/30
	 * @description : 新增列出该文件夹下的所有文件
	 */

	public List<String> getDectList(String ftpUserName, String ftpPassword, String ftpPort,
									String filePath, String ftpaddress) {
		ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);
		List<String> list = new ArrayList<>();
		try {
			//切换到指定目录
//			channelSftp.cd(filePath);
			Vector ls = channelSftp.ls(filePath);
			//遍历该文件夹下的所有文件
			Iterator iterator = ls.iterator();
			while(iterator.hasNext()) {
				ChannelSftp.LsEntry lsEntry  = (ChannelSftp.LsEntry) iterator.next();
				String filename = lsEntry.getFilename();
				//如果文件名包含点(.)
				if(filename.contains(".")) {
					String prev = filename.substring(0, filename.indexOf("."));
					String end = filename.substring( filename.lastIndexOf(".") + 1,filename.length());
					if(StringUtils.isNotEmpty(prev) && StringUtils.isNotEmpty(end)) {
						list.add(filename);
					}
				}
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 *@author ruanhui
	 *@date 2018/11/1
	 *@description 创建文件夹
	 */

	public void mkdir(String ftpUserName, String ftpPassword, String ftpPort,
					  String filePath, String ftpaddress, String dirName) {
		try {
			ChannelSftp channelSftp=getSFTPClient(ftpaddress,ftpPassword,ftpUserName,ftpPort);
			if (channelSftp != null) {
				channelSftp.cd(filePath);
				channelSftp.mkdir(dirName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
