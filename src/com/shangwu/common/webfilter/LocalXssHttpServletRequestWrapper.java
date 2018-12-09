package com.shangwu.common.webfilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 
 * @author xiangyong
 * @date 创建时间：2018-5-21 上午11:00:15
 */
public class LocalXssHttpServletRequestWrapper extends HttpServletRequestWrapper
{
	 private String[]filterChars;
	  private String[]replaceChars;
  public LocalXssHttpServletRequestWrapper(HttpServletRequest request, String filterChar, String replaceChar, String splitChar)
  {
    super(request);
    
    if(filterChar!=null&&filterChar.length()>0){
        filterChars=filterChar.split(splitChar);
      }
      if(replaceChar!=null&&replaceChar.length()>0){
        replaceChars=replaceChar.split(splitChar);
      }
  }

  public String getQueryString() {
    String value = super.getQueryString();
    if (value != null) {
      value = xssEncode(value);
      
      value = xssEncode2Str(value);
    }
    return value;
  }

  public String getParameter(String name)
  {
	  name = xssEncode2Str(name);
    String value = super.getParameter(xssEncode(name));
    if (value != null) {
      value = xssEncode(value);
      
      value = xssEncode2Str(value);
    }
    return value;
  }

  public String[] getParameterValues(String name) {
    String[] parameters = super.getParameterValues(name);
    if ((parameters == null) || (parameters.length == 0)) {
      return null;
    }
    for (int i = 0; i < parameters.length; ++i) {
      parameters[i] = xssEncode(parameters[i]);
      
      parameters[i] = xssEncode2Str(parameters[i]);
    }
    return parameters;
  }

  public String getHeader(String name)
  {
	  name = xssEncode2Str(name);
    String value = super.getHeader(xssEncode(name));
    if (value != null) {
      value = xssEncode(value);
      
      value = xssEncode2Str(value);
    }
    return value;
  }

  private String xssEncode(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return s;
    }
    try
    {
      s = URLDecoder.decode(s, "utf-8");
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    catch (Exception localException)
    {
    }

    StringBuilder sb = new StringBuilder(s.length() + 16);
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      switch (c)
      {

      case '>':
        sb.append("&lt;");
        break;
      case '<':
        sb.append("&gt;");
        break;
      case '\'':
        sb.append("‘");
        break;
      case '\t':
          sb.append("");
          break; 
      case '\r':
          sb.append("");
          break; 
      case '\n':
          sb.append("");
          break; 
      case '"':
        sb.append("“");
        break;
        //在线上渠道现状的环形图中,有传参数web&wap,导致报错,,故屏蔽; --ruanhui --20180608
//      case '&':
//        sb.append("＆");
//        break;
      case '%':
        sb.append("％");
        break;
      case '|':
        sb.append("丨");
        break;
        //在查看分解值时,查不出来策略名称，故屏蔽; --ruanhui --20180607
//      case ';':
//        sb.append("；");
//        break;
        //在后台修改角色权限管理时,会把通过菜单ID用英文逗号隔开的拼起来的替换为中文逗号,导致后台报错,,故屏蔽; --ruanhui --20180607
//      case ',':
//          sb.append("，");
//          break;
      case '$':
        sb.append("S");
        break;
//      case '@':
//        sb.append("at");
//        break;
//      case '(':
//        sb.append("&#40;");
//        break;
//      case ')':
//        sb.append("&#41;");
//        break;
//      case '+':
//        sb.append("＋");
//        break;
      case '\\':
        sb.append("");
        break;
      default:
        sb.append(c);
      }
    }
    return sb.toString();
  }
  
  /**
   * 将容易引起xss漏洞的半角字符直接替换成全角字符
   * 
   * @param s
   * @return
   */
  private  String xssEncode2Str(String s) {
    if (s == null || s.equals("")) {
      return s;
    }
    try {
      s = URLDecoder.decode(s, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < filterChars.length; i++) {
//      if(s.contains(filterChars[i])){
//        s=s.replace(filterChars[i], replaceChars[i]);
//      }
      s = replaceStr(s, this.filterChars[i], this.replaceChars[i]);
    }
    return s;
  }
  public static String HTMLEncode(String s){
    StringBuffer out = new StringBuffer();
    for (int i = 0; i < s.length(); i++)
    {
       char c = s.charAt(i);
       if(c > 0x7f || c=='"' || c=='&' || c=='<' || c=='>')
       out.append("&#" + (int) c + ";");
       else out.append(c);
    }
    return out.toString();
  }

  private static String halfWidth2FullWidth(String fullWidthStr)
  {
    if ((fullWidthStr == null) || (fullWidthStr.length() <= 0)) {
      return "";
    }
    char[] charArray = fullWidthStr.toCharArray();

    for (int i = 0; i < charArray.length; ++i) {
      int charIntValue = charArray[i];

      if (((charIntValue <= 126) && (charIntValue > 122)) || ((charIntValue < 97) && (charIntValue >= 33)))
        charArray[i] = (char)(charIntValue + 65248);
      else if (charIntValue == 32) {
        charArray[i] = 12288;
      }
    }
    return new String(charArray);
  }
  
  public String replaceStr(String s, String f, String r) {
	    int indx = -1;
	    int fs_len = f.length();
	    String t = s.toLowerCase();
	    if (t.contains(f)) {
	      indx = t.indexOf(f);
	      int ts_len = s.substring(0, indx).length();

	      if (ts_len + fs_len == s.length())
	        s = s.substring(0, indx) + r;
	      else {
	        s = s.substring(0, indx) + r + s.substring(indx + fs_len);
	      }
	    }
	    return s;
	  }
}