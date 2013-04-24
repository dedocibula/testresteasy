/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resteasy.services;

/**
 *
 * @author Andrej Galád <agalad@redhat.com>
 */
public class QueryHelper {
    
    public static String addQueryString(String url, String queryString) {
        if (StringHelper.isNullOrEmpty(queryString)) 
            return url;
        return url + "?" + queryString;
    }
}
