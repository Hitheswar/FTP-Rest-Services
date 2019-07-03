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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

	public ResponseEntity<ByteArrayResource> DownloadFileM(JSONObject request) throws Exception {
		
		String path = request.get(Constants.PATH).toString();
        String fileName = path.substring(path.lastIndexOf("/") + 1);
		File file = new File("/root/temp/"+fileName);
		
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        
        ByteArrayResource resource;

        FTPService FTPService = new FTPService();
        FTPClient FTPClient = new FTPClient();
		FTPClient = FTPService.getConnection(request);
		
        InputStream inputStream = FTPClient.retrieveFileStream(path);
        System.out.println("stream :");
        //Files.copy(inputStream, new File("/root/temp/"+fileName).toPath());
        System.out.println("copy stream :");

        Path path1 = Paths.get(file.getAbsolutePath());
        System.out.println("path :");

        resource = new ByteArrayResource(Files.readAllBytes(path1));
        System.out.println("resource :");

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        
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
		FTPClient.enterLocalPassiveMode();
		 FTPClient.setFileType(FTP.BINARY_FILE_TYPE);
        //
        String home = System.getProperty("user.home");
        System.out.println("home   :::"+home);
        FTPClient.setFileType(FTP.BINARY_FILE_TYPE);

       File downloadLocation = new File(home + "/" + "downloads" + "/" + fileName);
   
        
       OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadLocation));
        
        boolean success = FTPClient.retrieveFile(path, outputStream);

        System.out.println("success   :::"+success);
        FTPClient.disconnect();
        outputStream.close();
        
        
		return null;
	}

}
