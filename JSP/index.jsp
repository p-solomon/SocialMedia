<style>
<!--

-->
.mainDiv
{
 height:300px;
 width:600px;
 background-color:#808080;
 margin-left:400px;
 margin-top:200px;
 border-radius:20px;
 border:2px soloid black;
}
.formDiv
{
 padding:30px;

}
.txtField
{
 height:35px;
 width:300px;
 border-radius:15px;
}
.label
{
color:white;
font-weight:bold;
}
.submitBtn
{
  height:40px;
  width:200px;
  border-radius:20px;
}
.header
{
height:50px;
width:600px;
 background-color:black;
}
.textHeader
{
 font-size:25px;
 font-weight:bold;
 color:gray;
 margin-left:260px;
 margin-top:50px;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<%@ taglib uri="/struts-tags" prefix="s" %>  
<div class="mainDiv">

<div class="header"><span class="textHeader">Login</span></div>
<%
String msg=(String)request.getAttribute("msg");
if(msg!=null)
{
%>
<%=msg %>
<%} %>
<div class="formDiv">
<s:form action="login">  
<s:textfield name="username" label="User Name" cssClass="txtField"></s:textfield>  
<s:password  name="pass" label="Password" cssClass="txtField"></s:password >  
  
<s:submit value="Login" cssClass="submitBtn"></s:submit>  
<s:a href="signupRequest" >Sign Up</s:a>
</s:form>  
</div>
</div>