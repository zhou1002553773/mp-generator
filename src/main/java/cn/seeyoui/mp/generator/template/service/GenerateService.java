package cn.seeyoui.mp.generator.template.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GenerateService {

    Boolean generate(String applicationPrimaryKey, HttpServletResponse response, HttpServletRequest request);
}
