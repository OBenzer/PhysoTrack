package edu.sce.tom.physotrack.Algorithm;

import android.content.Context;

public class SesRunSingletone {
    private static SessionRunner sessionRunner=null;

    //This is the method needed to create the object, Application context needed//
     public static SessionRunner getInstance(Context c){
         if(sessionRunner==null) //Creates the object if needed and the Context is ok
            if(c==null)
                return null;
            else
                sessionRunner = new SessionRunner(c);

         return sessionRunner;
    }

    //Method to call when the object already exists (by splash screen)
    public static SessionRunner getInstance(){
        return getInstance(null);
    }
}
