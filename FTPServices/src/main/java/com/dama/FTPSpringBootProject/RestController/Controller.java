package com.dama.FTPSpringBootProject.RestController;

import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.dama.FTPSpringBootProject.Services.FTPService;
import com.dama.FTPSpringBootProject.Util.Constants;

public class Controller {
    
	public JSONObject getFileList(JSONObject request) throws SocketException, IOException {
		FTPService FTPService = new FTPService();
		FTPClient FTPClient= FTPService.getConnection(request);
		String path = request.get(Constants.PATH).toString();
		FTPFile[] files = FTPClient.listFiles(path);
        JSONArray filesArray =FTPService.getFilesArray(files);
        JSONObject result = new JSONObject();
        result.put("data", filesArray);
		return result;
	}
}
