/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resteasy.services;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

/**
 *
 * @author Andrej Galád <agalad@redhat.com>
 */
public class RequestBuilder {
    
    private ClientRequest clientRequest;
    private RequestType type;
    private Class returnType;
    
    public RequestBuilder(String url) {
        if (StringHelper.isNullOrEmpty(url))
            throw new IllegalArgumentException("url cannot be null");
        clientRequest = new ClientRequest(url);
        type = RequestType.GET;
        returnType = String.class;
    }
    
    public RequestBuilder addHeader(String headerName, String value) {
        if (StringHelper.isNullOrEmpty(headerName) || StringHelper.isNullOrEmpty(value)) 
            return this;    
        clientRequest.header(headerName, value);
        return this;
    }
    
    public RequestBuilder setBody(String mediaType, String value) {
        if (StringHelper.isNullOrEmpty(mediaType) || StringHelper.isNullOrEmpty(value)) 
            return this;
        clientRequest.body(mediaType, value);
        return this;
    }
    
    public RequestBuilder setRequestType(RequestType type) {
        this.type = type;
        return this;
    }
    
    public RequestBuilder setReturnType(Class returnType) {
        this.returnType = returnType;
        return this;
    }
    
    public ClientResponse runRequest() throws Exception {
        switch (type) {
            case POST:
                return clientRequest.post(returnType);
            case PUT:
                return clientRequest.put(returnType);
            case GET:
                return clientRequest.get(returnType);
            default:
                return null;
        }
    }
}
