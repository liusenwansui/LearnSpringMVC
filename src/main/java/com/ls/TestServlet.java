package com.ls;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = -4976416069947994442L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 返回
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = resp.getWriter();
        out.write("ok");
        out.close();
    }
}
