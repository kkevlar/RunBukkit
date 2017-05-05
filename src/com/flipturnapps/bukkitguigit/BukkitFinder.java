package com.flipturnapps.bukkitguigit;

import java.io.File;

public class BukkitFinder 
{
	private File dir;
	private int score;
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
                if(f.getName().equalsIgnoreCase("bukkit.yml"))
                {
                	if(f.getParentFile().getName().equalsIgnoreCase("yold-bukkit"))
                	dir = root;
                }
            }
        }
    }
    public File walk()
    {
    	//System.out.println();
    	walk(System.getProperty("user.home"));
    	if(dir != null)
    	{
    		//System.out.println("foundit: " + dir.getAbsolutePath());
    		return dir;
    	}
    	return null;
    }
    public File informedWalk(String file)
    {
    	if(file == null)
    		return null;
    	//System.out.println();
    	walk(file);
    	if(dir != null)
    	{
    		//System.out.println("foundit: " + dir.getAbsolutePath());
    		return dir;
    	}
    	return null;
    }


}
