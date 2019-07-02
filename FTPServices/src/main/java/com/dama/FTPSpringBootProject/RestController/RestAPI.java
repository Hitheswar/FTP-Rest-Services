package com.dama.FTPSpringBootProject.RestController;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/Request",method = RequestMethod.POST)
	public JSONObject getFileList(@RequestBody JSONObject request) throws Exception{
		Controller Controller = new Controller();
		return Controller.getFileList(request);
	}
}
