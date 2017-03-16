package org.shuochen.test.network;

public class IPAllAcceptFilter implements IPAcceptFilter{
    private static IPAcceptFilter instance = null;
     
    /**
     * Access Control
     */
    private IPAllAcceptFilter(){};
     
    /**
     * Ignore multiple thread sync problem in extreme case
     */
    public static IPAcceptFilter getInstance(){
        if(instance == null){
            instance = new IPAllAcceptFilter();
        }
        return instance;
    }

    @Override
    public boolean accept(String ipAddress) {
        return true;
    }


}