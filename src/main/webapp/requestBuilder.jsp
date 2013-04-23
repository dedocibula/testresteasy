<%-- 
    Document   : requestBuilder
    Created on : Apr 23, 2013, 11:13:10 AM
    Author     : Andrej Galád <agalad@redhat.com>
--%>

<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        <title>Request Builder</title>
    </head>
    <body>
        <h1>New Request</h1>
        
        <form id="mainForm" action="${pageContext.request.contextPath}/request" method="post">
            <table>
                <tr>
                    <th>Request Url</th>
                    <td>
                        <select name="url">
                            <c:forEach items="${urls}" var="url">
                                <option value="${url}">${url}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Request Type</th>
                    <td>
                        <select name="requestType">
                            <option value="GET" selected="selected">GET</option>
                            <option value="POST">POST</option>
                            <option value="PUT">PUT</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Accepts</th>
                    <td><input type="text" name="accept" /></td>
                </tr>
                <tr>
                    <th>MediaType</th>
                    <td><input type="text" name="mediaType" /></td>
                </tr>
                <tr>
                    <th>Body</th>
                    <td><textarea name="body" rows="10" cols="70"></textarea></td>
                </tr>
                <tr>
                    <th><input type="Submit" value="Run" /></th>
                </tr>
            </table>   
        </form>
            
        <br />
        <hr />
        <br />
        
        <div id="response"></div>
            
    <script>
        (function() {
           $('#mainForm').on('submit', function(e) {
               var self = $(this);
               e.preventDefault();
               $.ajax({
                   method: "POST",
                   url: '${pageContext.request.contextPath}/request',
                   data: self.serialize()     
                      }).done(function(results) {
                   var response = $('#response');
                   response.children().remove();
                   response.append($(results));
               });
           }); 
        })();
    </script>
    
    </body>
</html>
