package eist;

import java.util.*;

public class AccessControlList {
    private Map<String, Set<String>> accessControlMap;


    public AccessControlList() {
        accessControlMap = new HashMap<>();
    }

    //TODO: fill accessControlMap instance with the given user and permission information.
    public void grantAccess(String user, String permission) {
        if(accessControlMap.containsKey(user)){
            accessControlMap.get(user).add(permission);
        }else{
            Set<String> newPer = new HashSet<>();
            newPer.add(permission);
            accessControlMap.put(user, newPer);
        }

    }

    //TODO: check if accessControlMap instance has given user and permission. Return result in a boolean.
    public boolean hasAccess(String user, String permission) {
        if(accessControlMap.get(user).contains(permission)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return accessControlMap.toString();
    }

    public Map<String, Set<String>> getAccessControlMap() {
        return accessControlMap;
    }

    public void setAccessControlMap(Map<String, Set<String>> accessControlMap) {
        this.accessControlMap = accessControlMap;
    }
}