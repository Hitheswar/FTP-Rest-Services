package com.dama.FTPSpringBootProject.RestController;



import org.json.simple.JSONObject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	
	
	@RequestMapping(value = "/download",method = RequestMethod.POST)
	public JSONObject DownloadFile(@RequestBody JSONObject request) throws Exception{
		Controller Controller = new Controller();
		return Controller.DownloadFile(request);
	}
}
