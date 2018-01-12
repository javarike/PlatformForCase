/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-11-10 10:06:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.io.*;
import org.apache.commons.lang.StringEscapeUtils;
import com.talk51.Utils.JDBCConnectionPool;

public final class AddCaseApi_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');


request.setCharacterEncoding("UTF-8");
String model_name=request.getParameter("model_name");
String interface_name=request.getParameter("interface_name");
String interface_suff=request.getParameter("interface_suff");
String host_ip=request.getParameter("host_ip");
String priority=request.getParameter("priority");
String expected_result=request.getParameter("expected_result");
String scenario=request.getParameter("scenario");
String method=request.getParameter("method");
String body=URLDecoder.decode(request.getParameter("body"),"UTF-8");
String section2body=URLEncoder.encode(request.getParameter("section2body"),"UTF-8");
String is_cache=request.getParameter("is_cache");
String section2id="";
body=StringEscapeUtils.escapeSql(body);

//Object object=session.getAttribute("userid");
//int user_id=Integer.parseInt(object==null ? "0":object.toString());

Connection connection=JDBCConnectionPool.getConnection();
Statement statement = connection.createStatement();

String insert_sql="insert into cases (project_name,interface_name,call_suff,host_ip,priority,expected_result,scenario,method,body,operator,is_cache,is_deleted) values('"+model_name+"','"+interface_name+"','"+interface_suff+"','"+host_ip+"','"+priority+"','"+expected_result+"','"+scenario+"','"+method+"','"+body+"',"+3+","+is_cache+",0)";
statement.executeUpdate(insert_sql);
ResultSet rsFlag=statement.executeQuery("SELECT @@IDENTITY AS currentID");
if(rsFlag.next()){
section2id=rsFlag.getString("currentID");
}

Statement s2statement = connection.createStatement();
String s2insert_sql="insert into cases_parm_relation (case_id,parm_relation,is_deleted) values ("+section2id+",'"+section2body+"',0)";
s2statement.executeUpdate(s2insert_sql);

Statement statement2 = connection.createStatement();
String check_sql="select * from interface_list where call_Stuff = '"+interface_suff+"' and interface_name = '"+interface_name+"' and model_name='"+model_name+"'";
ResultSet rs = statement2.executeQuery (check_sql);

if (!rs.next())
{
	String new_insert_interface_list_sql="insert into interface_list (model_name,interface_name,call_Stuff) values ('"+model_name+"','"+interface_name+"','"+interface_suff+"')";
	statement2.executeUpdate(new_insert_interface_list_sql);
}

JDBCConnectionPool.close(s2statement);
JDBCConnectionPool.close(rsFlag, statement);
JDBCConnectionPool.close(rs, statement2);

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}