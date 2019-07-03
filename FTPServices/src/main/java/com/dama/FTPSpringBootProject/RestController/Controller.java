package com.dama.FTPSpringBootProject.RestController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

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

	public JSONObject DownloadFile(JSONObject request, HttpServletResponse response) throws IOException {
		
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
		FTPClient.enterLocalActiveMode();
      //  String home = System.getProperty("user.home");
		String home = System.getProperty("java.io.tmpdir");
		System.out.println("home  :::"+home);
		/*
		 * File temp_location = new File(home + "/" + "downloads" + "/" + fileName);
		 * File downloadLocation = new File("C:\\Users\\Aline\\downloads\\data.csv");
		 * OutputStream outputStream = new BufferedOutputStream(new
		 * FileOutputStream(downloadLocation)); boolean success =
		 * FTPClient.retrieveFile(path, outputStream);
		 */
       // System.out.println("downloadLocation   ::"+downloadLocation);


        
		return null;
	}
}
