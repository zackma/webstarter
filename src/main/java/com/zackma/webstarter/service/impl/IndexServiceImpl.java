package com.zackma.webstarter.service.impl;

import com.zackma.webstarter.service.IndexServiceI;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexServiceI{
    public String getIndex() {
        return "This is web starter project with just SpringMVC Annotation and without 'web.xml'";
    }

    public String getPageIndex() {
        return "index";
    }
}
