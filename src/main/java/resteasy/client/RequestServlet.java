/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resteasy.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.resteasy.client.ClientResponse;
import resteasy.services.RequestBuilder;
import resteasy.services.RequestType;

/**
 *
 * @author Andrej Galád <agalad@redhat.com>
 */
public class RequestServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        request.setAttribute("urls", generateUrls("http://localhost:8084/TestAppResteasy/rest/content_type/html"));
        request.getRequestDispatcher("/requestBuilder.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String url = request.getParameter("url");
        String accept = request.getParameter("accept");
        String body = request.getParameter("body");
        String mediaType = request.getParameter("mediaType");
        RequestType requestType = RequestType.valueOf(request.getParameter("requestType"));
        
        RequestBuilder requestBuilder = new RequestBuilder(url);
        ClientResponse clientResponse = null;
        try {
            clientResponse = requestBuilder.addHeader("Accept", accept)
                                           .setBody(mediaType, body)
                                           .setReturnType(String.class)
                                           .setRequestType(requestType)
                                           .runRequest();
            
            try (PrintWriter writer = response.getWriter()) {
                writer.println("<div>" + clientResponse.getResponseStatus() + "</div>");
                for (Object object : clientResponse.getHeaders().keySet())
                    writer.print("<div>" + object + " : " + clientResponse.getHeaders().get(object) + "</div>");
                writer.print("<div>" + clientResponse.getEntity() + "</div>");            
            }
        } catch (Exception ex) {
            response.getWriter().print(ex);
        } finally {
            clientResponse.releaseConnection();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String[] generateUrls(String... urls) {
        return urls;
    }
}
