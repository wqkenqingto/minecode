package com.minecode.base;

/**
 * Created by wqkenqing on 2017/9/18.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minecode.base.model.BaseEntity;
import com.minecode.utils.BeanUtils;
import com.minecode.utils.WebUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;


public abstract class BaseController {

    protected Logger LOGGER = Logger.getLogger(this.getClass());

    protected <T> T bindParams(HttpServletRequest request, Class<T> clazz) {
        T params = null;
        try {
            params = clazz.newInstance();
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String propertyName = (String) enumeration.nextElement();
                String propertyValue = request.getParameter(propertyName).trim();
                propertyValue = propertyValue.replace("\'", "\"");
                BeanUtils.setBeanPropertyByName(params, propertyName, propertyValue);
            }
        } catch (Exception e) {
            LOGGER.error("bindParams", e);
        }
        return params;
    }

    protected <T extends BaseEntity> T bindEntity(HttpServletRequest request, Class<T> clazz) {
        T entity = null;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("bindEntity", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("bindEntity", e);
        }
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String propertyName = (String) enumeration.nextElement();
            String propertyValue = request.getParameter(propertyName).trim();
            propertyValue = propertyValue.replace("\'", "\"");
            try {
                BeanUtils.setBeanPropertyByName(entity, propertyName, propertyValue);
            } catch (Exception e) {
                LOGGER.error("bindEntity", e);
            }
        }
        return entity;
    }

    protected Map<String, String> bindMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = request.getParameter(name);
            // 如果是排序列，并且列名带点的，则两边加双引号
            if ("sort".equals(name)) {
                if (value.indexOf(".") > -1) {
                    value = "\"" + value + "\"";
                }
            }
            value = value.replace("\'", "\"");
            LOGGER.debug("NAME:" + name + ", VALUE:" + value);
            map.put(name, value);
        }
        return map;
    }

    protected Map<String, Object> bindMapObj(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = request.getParameter(name);
            // 如果是排序列，并且列名带点的，则两边加双引号
            if ("sort".equals(name)) {
                if (value.indexOf(".") > -1) {
                    value = "\"" + value + "\"";
                }
            }
            value = value.replace("\'", "\"");
            LOGGER.debug("NAME:" + name + ", VALUE:" + value);
            map.put(name, value);
        }
        return map;
    }

    /**
     * 直接输出纯Json.
     */
    protected void printJson(HttpServletResponse response, String text) {
        doPrint(response, text, "application/json;charset=UTF-8");
    }

    /**
     * 直接输出纯XML.
     */
    protected void printXML(HttpServletResponse response, String text) throws IOException {
        doPrint(response, text, "text/xml;charset=UTF-8");
    }

    /**
     * 直接输出纯HTML.
     */
    protected void printHtml(HttpServletResponse response, String text) throws IOException {
        doPrint(response, text, "text/html;charset=UTF-8");
    }

    /**
     * 直接输出纯字符串.
     */
    protected void printText(HttpServletResponse reponse, String text) throws IOException {
        doPrint(reponse, text, "text/plain;charset=UTF-8");
    }

    /**
     * 直接输出.
     *
     * @param contentType 内容的类型.html,text,xml的值见后，json为"text/x-json;charset=UTF-8"
     */
    private void doPrint(HttpServletResponse response, String text, String contentType) {

        PrintWriter out = null;
        try {
            LOGGER.debug("输出的字符串: " + text + "");
            response.setContentType(contentType);
            out = response.getWriter();
            out.write(text);

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.print("");
                out.close();
            }
        }

    }

    /**
     * 直接输出纯Jsonp.
     */
    protected void printJson(HttpServletResponse response, HttpServletRequest request, String text) {
        doPrintP(response, request, text, "application/json;charset=UTF-8");
    }

    private void doPrintP(HttpServletResponse response, HttpServletRequest request, String text, String contentType) {

        PrintWriter out = null;
        try {
            LOGGER.debug("输出的字符串: " + text + "");
            String callback = request.getParameter("callback");
            response.setContentType(contentType);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers",
                    "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
            out = response.getWriter();
            out.write(callback + "(" + text + ")");

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.print("");
                out.close();
            }
        }

    }

    /**
     * @description：根据传入的字段名称typeStr获取其get方法名称
     * @method：getMethodNameByType
     */
    protected String getMethodNameByType(String typeStr) {
        String firstStr = typeStr.substring(0, 1).toUpperCase();
        String methodNameString = "get" + firstStr + typeStr.substring(1);
        return methodNameString;
    }

    /**
     * @param obj Object对象
     * @return Map<String, Object>
     * @method：strutsMapByObj
     * @description：将javaBean(obj)对象的属性及值组装到Map<String, Object>
     */
    protected Map<String, Object> strutsMapByObj(Object obj) throws NoSuchFieldException {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String nameString = field.getName();
            if (field.getType().isAssignableFrom(String.class))
                map.put(nameString, BeanUtils.forceGetProperty(obj, nameString));
            else
                map.put(nameString, BeanUtils.forceGetProperty(obj, String.valueOf(nameString)));
        }
        return map;
    }

    /**
     * 错误消息外包 </p> 后台往前台打印消息时，添加的外包，以便前台展现技术获取消息. 根据不同的前台展现技术变更。当前支持Ext.
     */
    protected String messageFailureWrap(String message) {
        return WebUtils.getFailureMessage(message);
    }

    /**
     * 成功消息外包 </p> 后台往前台打印消息时，添加的外包，以便前台展现技术获取消息. 根据不同的前台展现技术变更。当前支持Ext.
     */
    protected String messageSuccuseWrap(String message) {
        return WebUtils.getSuccuseMessage(message);
    }

    /**
     * 成功消息外包 </p> 后台往前台打印消息时，添加的外包，以便前台展现技术获取消息. 根据不同的前台展现技术变更。当前支持Ext.
     */
    protected String messageSuccuseWrap() {
        return WebUtils.getSuccuseMessage(null);
    }

    protected String getNameSpace() {
        String ns = null;
        RequestMapping r = getClass().getAnnotation(RequestMapping.class);
        ns = r.value()[0];
        if (!ns.endsWith("/")) {
            ns += "/";
        }
        return ns;
    }

    protected String getErrorIndex() {
        return "/commons/error/index";
    }

}

