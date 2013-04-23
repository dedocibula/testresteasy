/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resteasy.services;

/**
 *
 * @author Andrej Galád <agalad@redhat.com>
 */
public class StringHelper {
    
    public static boolean isNullOrEmpty(String string) {
        return string == null || "".equals(string.trim());
    }
}
