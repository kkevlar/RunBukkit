package com.flipturnapps.bukkitguigit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.flipturnapps.kevinLibrary.helper.FileHelper;

public class RepoFinder 
{
	private boolean hasWalked = false;
	private String repoFilepath = null;
	private String remote;

	public RepoFinder(String remote)
	{
		this.remote = remote;
	}
	
    public void walk( String path ) 
    {
    	if(getRepoFilepath() != null || isHasWalked() == true || path == null)
    		return;
        File root = new File( path );
        File[] list = root.listFiles();

        if (getRepoFilepath() != null || list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else
            {
               if(root.getName().equalsIgnoreCase(".git") && f.getName().equalsIgnoreCase("config"))
               {
            	   try
            	   {
            	   BufferedReader reader = new BufferedReader(new FileReader(f));
            	   while(true)
            	   {
            		   String line = reader.readLine();
            		   if(line == null)
            			   break;
            		   if(line.contains(remote))
            		   {
            			   File dir = new File(FileHelper.fileInDir(root.getParentFile(), "yold-bukkit"));
            			   setRepoFilepath(dir.getAbsolutePath());
            			   setHasWalked(true);
            			   break;
            		   }
            	   }
            	   reader.close();
            	   }
            	   catch(Exception ex)
            	   {
            		   
            	   }
               }
            }
        }
    }
    public void walk()
    {
    	walk(System.getProperty("user.home"));
    }

	public boolean isHasWalked() {
		return hasWalked;
	}

	public void setHasWalked(boolean hasWalked) {
		this.hasWalked = hasWalked;
	}

	public String getRepoFilepath() {
		return repoFilepath;
	}

	public void setRepoFilepath(String repoFilepath) {
		this.repoFilepath = repoFilepath;
	}
}
