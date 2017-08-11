package org.redrock.template.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends CommonServlet {

    private static int i = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //配置
        Configuration configuration = new Configuration();
        configuration.setServletContextForTemplateLoading(req.getServletContext(), "/WEB-INF/views");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //构造数据模型
        Map<String, Object> data = new HashMap<>();
        data.put("name", "jjjjjjjj");
        Template temp = configuration.getTemplate("test.ftl");
        //输出
        Writer out = resp.getWriter();
        try {
            //输出 = 数据模型 + 模板
            temp.process(data, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.display(resp, "test.ftl");
    }
}
