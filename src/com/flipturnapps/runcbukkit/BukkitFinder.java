package com.flipturnapps.runcbukkit;

import java.io.File;

public class BukkitFinder 
{
	private File dir;
    public void walk( String path ) 
    {
    	if(dir != null)
    		return;
        File root = new File( path );
        File[] list = root.listFiles();

        if (dir != null || list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
               // System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else
            {
                //System.out.println( "File:" + f.getAbsoluteFile() );
                if(f.getName().equalsIgnoreCase("craftbukkit.jar"))
                {
                	dir = root;
                }
            }
        }
    }
    public void walk()
    {
    	//System.out.println();
    	walk(System.getProperty("user.home"));
    	if(dir != null)
    	{
    		//System.out.println("foundit: " + dir.getAbsolutePath());
    		Main.setcBukkitDir(dir);
    	}
    }


}
