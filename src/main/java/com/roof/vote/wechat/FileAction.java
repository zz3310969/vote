package com.roof.vote.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.roof.fileupload.api.FileInfo;
import org.roof.fileupload.api.FileManager;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roof.vote.common.VoteFileService;

/**
 * Created by zhenglt on 2017/8/13.
 */
@Controller
@RequestMapping("vote/wechat/fileAction")
public class FileAction {

	private FileManager fileManager;

	@Autowired
	private VoteFileService voteFileService;

	@RequestMapping("/uploadFile")
	@ResponseBody
	public Result uploadFile(@RequestParam("upfile") MultipartFile file, HttpServletRequest request) {
		try {
			FileInfo fileinfo = voteFileService.saveFile(file);
			return new Result(Result.SUCCESS, "", fileinfo.getName());
		} catch (Exception e) {
			return new Result(Result.FAIL, "上传失败");
		}
	}

	@RequestMapping("/getFile")
	public void getFile(String filename, HttpServletResponse response, HttpServletRequest request) {
		if (StringUtils.isBlank(filename)) {
			return;
		}
		InputStream in = voteFileService.getSmallFile(filename);
		// InputStream in = fileManager.getFile(filename);
		response.setContentType("image/png");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			byte[] buf = new byte[1024];
			while (in.read(buf) != -1) {
				out.write(buf);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping("/getMin")
	public void getMin(String filename, HttpServletResponse response, HttpServletRequest request) {
		if (StringUtils.isBlank(filename)) {
			return;
		}
		InputStream in = voteFileService.getMinFile(filename);
		response.setContentType("image/png");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			byte[] buf = new byte[1024];
			while (in.read(buf) != -1) {
				out.write(buf);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Autowired(required = true)
	public void setFileManager(@Qualifier("simpleFileManager") FileManager fileManager) {
		this.fileManager = fileManager;
	}

}
