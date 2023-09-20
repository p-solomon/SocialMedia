<style>
<!--

-->
.mainDiv
{
 height:400px;
 width:600px;
 background-color:#808080;
 margin-left:400px;
 margin-top:150px;
 border-radius:20px;
 border:2px soloid black;
}
.formDiv
{
 padding:70px;

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
<%@ taglib uri="/struts-tags" prefix="s" %>  
<div class="mainDiv">
<div class="header"><span class="textHeader">Sign Up</span></div>
<div class="formDiv">
<s:form action="signup">  
<s:textfield name="fname" label="First Name" cssClass="txtField"></s:textfield>  
<s:textfield name="sname" label="Surname" cssClass="txtField"></s:textfield>  
<s:textfield name="email" label="E-Mail" cssClass="txtField"></s:textfield>  
  <s:textfield name="mob" label="Mobile" cssClass="txtField"></s:textfield>  
  <s:textfield name="username" label="User Name" cssClass="txtField"></s:textfield>  
<s:submit value="Sign Up" cssClass="submitBtn"></s:submit>  
</s:form>  
</div>
</div>