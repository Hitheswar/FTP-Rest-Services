package com.dama.FTPSpringBootProject.RestController;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.dama.FTPSpringBootProject.Services.FTPService;
import com.dama.FTPSpringBootProject.Util.Constants;

public class Controller {
    
	public JSONObject getFileList(JSONObject request) throws SocketException, IOException {
		FTPService FTPService = new FTPService();
		FTPClient FTPClient = new FTPClient();
		try {
			FTPClient = FTPService.getConnection(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = request.get(Constants.PATH).toString();
		FTPFile[] files = FTPClient.listFiles(path);
        JSONArray filesArray =FTPService.getFilesArray(files);
        JSONObject result = new JSONObject();
        result.put("data", filesArray);
		return result;
	}

	public JSONObject checkConnection(JSONObject request) {
		return null;
	}

	public JSONObject DownloadFile(JSONObject request) {
		
		String path = request.get(Constants.PATH).toString();
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        FTPService FTPService = new FTPService();
		FTPClient FTPClient = new FTPClient();
		try {
			FTPClient = FTPService.getConnection(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String home = System.getProperty("user.home");
		System.out.println("home dir  :"+home);
        		
		return null;
	}
}
