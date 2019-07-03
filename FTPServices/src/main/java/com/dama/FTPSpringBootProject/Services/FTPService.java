package com.dama.FTPSpringBootProject.Services;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.dama.FTPSpringBootProject.Util.Constants;
import com.dama.FTPSpringBootProject.Util.Utils;

@Component
public class FTPService {

	public FTPClient getConnection(JSONObject request) throws Exception {
		FTPClient ftpClient = new FTPClient();
		try {
        String host = request.get(Constants.HOST).toString();
        System.out.println("HOST  +"+host);
        String password = request.get(Constants.PASSWORD).toString();
        String username = request.get(Constants.USER).toString();
        ftpClient.connect(host);
        //ftpClient.enterLocalPassiveMode();
        showServerReply(ftpClient);
        ftpClient.login(username, password);
        showServerReply(ftpClient);
		}
		catch(Exception ex)
		{
			throw new Exception ("invalid credentials");
		}
        return ftpClient;
				
	}
	   private static void showServerReply(FTPClient ftpClient) {
	        String[] replies = ftpClient.getReplyStrings();
	        if (replies != null && replies.length > 0) {
	            for (String aReply : replies) {
	                System.out.println("SERVER ====> FTP : " + aReply);
	            }
	        }
	    }
	public JSONArray getFilesArray(FTPFile[] files) {
        JSONArray result = new JSONArray();
        Utils Utils = new Utils();
        for (FTPFile file : files) {
            JSONObject obj = new JSONObject();
            String title = file.getName();
            String file_size = Utils.getSize(file.getSize());
            obj.put("title", title);
            String file_type = file.isDirectory() ? "Directory" : "File";
            if (file.isDirectory()) {
                obj.put("TYPE", "DIRECTORY");
                obj.put("icon_name", "directory");
                obj.put("isOpened", false);
            } else if (file.isFile()) {
                obj.put("TYPE", "FILE");
                obj.put("file_size", file_size);
            }
            result.add(obj);
        }
        return result;

	}
}
