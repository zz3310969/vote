package com.roof.vote.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.roof.fileupload.api.FileInfo;
import org.roof.fileupload.api.FileInfoService;
import org.roof.fileupload.api.FileManager;
import org.roof.fileupload.api.FileService;
import org.roof.fileupload.exception.FileInfoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import net.coobird.thumbnailator.Thumbnails;

/**
 * Created by zhenglt on 2017/10/2.
 */
@Service
public class VoteFileService implements InitializingBean {

	private Logger logger = LoggerFactory.getLogger(VoteFileService.class);

	private FileManager fileManager;

	private FileInfoService fileInfoService;
	private FileService fileService;

	@Override
	public void afterPropertiesSet() throws Exception {
		fileInfoService = fileManager.getFileInfoService();
		fileService = fileManager.getFileService();
	}

	public InputStream getLargeFile(String filename) {
		InputStream in = fileManager.getFile(filename);
		return in;
	}

	public InputStream getMinFile(String filename) {
		FileInfo fileInfo = null;
		try {
			fileInfo = fileInfoService.loadByName(filename);
		} catch (FileInfoNotFoundException e) {
			return null;
		}
		String minPath = toMinPath(fileInfo.getRealPath());
		Path path = Paths.get(minPath);

		if (!Files.exists(path)) {
			try {
				minImage(fileInfo.getRealPath(), minPath);
			} catch (IOException e) {
				logger.error("生成缩略图出错:", e);
				return null;
			}
		}
		fileInfo.setRealPath(minPath);

		byte[] bs = getImageInCache(filename, fileInfo);// fileService.loadDataByFileInfo(fileInfo);
		return new ByteArrayInputStream(bs);
	}

	public FileInfo saveFile(MultipartFile file) throws Exception {
		Map<String, Object> xdata = new HashMap<String, Object>();
		xdata.put("displayName", file.getOriginalFilename());
		xdata.put("fileSize", file.getSize());
		xdata.put("ContentType", file.getContentType());

		ByteArrayInputStream in = new ByteArrayInputStream(file.getBytes());
		FileInfo fileinfo = fileManager.saveFile(in, xdata);
		this.smallImage(fileinfo.getRealPath(), toSmallPath(fileinfo.getRealPath()));
		return fileinfo;
	}

	public InputStream getSmallFile(String filename) {
		FileInfo fileInfo = null;
		try {
			fileInfo = fileInfoService.loadByName(filename);
		} catch (FileInfoNotFoundException e) {
			return null;
		}
		String smallPath = toSmallPath(fileInfo.getRealPath());
		Path path = Paths.get(smallPath);

		if (!Files.exists(path)) {
			try {
				smallImage(fileInfo.getRealPath(), smallPath);
			} catch (IOException e) {
				logger.error("生成缩略图出错:", e);
				return null;
			}
		}
		fileInfo.setRealPath(smallPath);

		byte[] bs = fileService.loadDataByFileInfo(fileInfo);
		return new ByteArrayInputStream(bs);
	}

	public void smallImage(String filePath, String toPath) throws IOException {
		Thumbnails.of(filePath).scale(1f).outputQuality(0.5f).toFile(toPath);

	}

	Cache<String, byte[]> cache = CacheBuilder.newBuilder().maximumSize(1000).build();

	private byte[] getImageInCache(String imaegName, FileInfo fileInfo) {
		Assert.notNull(fileInfo);
		Assert.notNull(fileInfo.getRealPath());
		Assert.notNull(imaegName);
		try {
			return cache.get(imaegName, new Callable<byte[]>() {
				@Override
				public byte[] call() throws Exception {
					return fileService.loadDataByFileInfo(fileInfo);
				}
			});
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void minImage(String filePath, String toPath) throws IOException {
		Thumbnails.of(filePath).scale(0.25).outputQuality(0.5f).toFile(toPath);

	}

	private String toSmallPath(String str) {
		String small = StringUtils.replace(str, ".", "-small.");
		return small;
	}

	private String toMinPath(String str) {
		String small = StringUtils.replace(str, ".", "-min.");
		return small;
	}

	@Autowired(required = true)
	public void setFileManager(@Qualifier("simpleFileManager") FileManager fileManager) {
		this.fileManager = fileManager;
	}

}
