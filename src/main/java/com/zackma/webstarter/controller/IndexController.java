package com.zackma.webstarter.controller;


import com.zackma.webstarter.service.IndexServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private IndexServiceI indexService;


    @RequestMapping(value = "/")
    @ResponseBody
    public String getIndex(){
        return indexService.getIndex();
    }

    @RequestMapping(value = "/pageIndex")
    public String getPageIndex(){
        return indexService.getPageIndex();
    }
}
