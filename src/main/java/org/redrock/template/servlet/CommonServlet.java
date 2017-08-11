package org.redrock.template.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 整合freemarker视图引擎的父类
 */
public class CommonServlet extends HttpServlet {

    private Configuration configuration = null;

//    private Map<String, Object> data = new HashMap<>();
    private ThreadLocal<Map> data;

    @Override
    public void init() {
//        Map (key=>threadId, Map);
        data = new ThreadLocal<>();
        configuration = new Configuration();
        configuration.setServletContextForTemplateLoading(this.getServletContext(), "/WEB-INF/views");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map param = new HashMap();
        data.set(param);
        super.service(req, resp);
    }

    protected void assign(String paramName, Object paramValue) {
        data.get().put(paramName, paramValue);
    }

    public void display(HttpServletResponse response, String template) {
        try {
            Template temp = configuration.getTemplate("test.ftl");
            //输出
            Writer out = response.getWriter();
            //输出 = 数据模型 + 模板
            Map param = data.get();
            temp.process(param, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}