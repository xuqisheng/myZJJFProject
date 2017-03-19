package com.corner.core.utils.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileManager implements FileManagerConfig {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(FileManager.class);

	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;

	static {
		try {
			//String fdfsClientConfigFilePath =PropertiesCacheUtil.getValue("fastdfs_config_path", PropertieNameConts.BASE);
			//logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
			Resource res2 = new ClassPathResource("config/upload/fdfs_client.conf");
			String fdfsClientConfigFilePath =   res2.getFile().getAbsolutePath();
			logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
			ClientGlobal.init(fdfsClientConfigFilePath);
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
		} catch (Exception e) {
			logger.error("初始化DFS失败！",e);
		}
	}

	public static String upload(FastDFSFile file) {
		NameValuePair[] meta_list = new NameValuePair[3];
		meta_list[0] = new NameValuePair("width", "120");
		meta_list[1] = new NameValuePair("heigth", "120");
		meta_list[2] = new NameValuePair("author", "izjjf");
		String[] uploadResults = null;
		try {
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
		} catch (Exception e) {
			logger.error("Exception when uploadind the file: {}" ,file.getName(),e);
			return null;
		}
		
		if (uploadResults == null || uploadResults.length < 2) {
			logger.error("upload file fail, error code:{} " ,storageClient.getErrorCode());
			return null;
		}

		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];
		String fileAbsolutePath = groupName + SEPARATOR + remoteFileName;
		logger.info("upload file successfully!!! group_name:{} ,remoteFileName:{}" , groupName , remoteFileName);
		return fileAbsolutePath;
	}

	public static FileInfo getFile(String groupName, String remoteFileName) {
		try {
			
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException e) {
			System.out.println("IO Exception: Get File from Fast DFS failed");
		} catch (Exception e) {
			System.out.println("Non IO Exception: Get File from Fast DFS failed");
		}
		return null;
	}

	public static void deleteFile(String groupName, String remoteFileName) throws Exception {
		storageClient.delete_file(groupName, remoteFileName);
	}

	public static StorageServer[] getStoreStorages(String groupName) throws IOException {
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}

	public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}

	public static void main(String[] args) throws Exception {
		
	}
}