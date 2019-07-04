package com.dama.FTPSpringBootProject.RestController;



import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dama.FTPSpringBootProject.Util.Constants;
@RestController
@EnableAutoConfiguration
@ComponentScan("com.dama.FTPSpringBootProject.*")

public class RestAPI {

	
	@RequestMapping(value = "/checkConnection",method = RequestMethod.POST)
	public JSONObject checkConnection(@RequestBody JSONObject request) throws Exception{
		Controller Controller = new Controller();
		return Controller.getFileList(request);
	}
	@RequestMapping(value = "/getFileList",method = RequestMethod.POST)
	public JSONObject getFileList(@RequestBody JSONObject request) throws Exception{
		Controller Controller = new Controller();
		return Controller.getFileList(request);
	}
	//http://192.168.2.23:30000/api/ifm/download?name=ftp&connectiontype=ftp&hdfspath=/home/aline/dama/test.xlsx
	
	
	@RequestMapping(value = "/downloadM")
	public ResponseEntity<ByteArrayResource> DownloadFileM(@RequestBody JSONObject request,HttpServletResponse response) throws Exception{
		Controller Controller = new Controller();
		ResponseEntity<ByteArrayResource> res = Controller.DownloadFileM(request);
		String path = request.get(Constants.PATH).toString();
        String fileName = path.substring(path.lastIndexOf("/") + 1);
		Files.deleteIfExists(Paths.get("/tmp/"+fileName));
		return res;
	}
	
	@RequestMapping(value = "/downloadL",method = RequestMethod.POST)
	public JSONObject DownloadFileL(@RequestBody JSONObject request,HttpServletResponse response) throws Exception{
		Controller Controller = new Controller();
		return Controller.DownloadFile(request,response);
	}
}
