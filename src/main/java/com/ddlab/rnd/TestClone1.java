package com.ddlab.rnd;

import java.io.File;
import java.nio.file.Files;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class TestClone1 {

	public static void main(String[] args) {
		String userName = "sambittechy@gmail.com";
		String password = "abcd@1234";
		File cloneDir = new File("deleteMe1");
		String gitURI = "https://github.com/sambittechy/deleteMe1.git";
		
		CredentialsProvider crProvider = new UsernamePasswordCredentialsProvider(userName, password);
		CloneCommand clone = Git.cloneRepository();
		clone.setURI(gitURI);
		clone.setCredentialsProvider(crProvider);
		clone.setDirectory(cloneDir);
		
		
		try {
	        clone.call();
	    } catch (GitAPIException e) {
	        e.printStackTrace();
	    }
		
		
		
//		FileRepositoryBuilder builder = new FileRepositoryBuilder();
//	    Repository repository = builder.setGitDir(dir)
//	            .readEnvironment().findGitDir().setup().build();
//	    CloneCommand clone = Git.cloneRepository();
//	    clone.setBare(false).setCloneAllBranches(true);
//	    clone.setDirectory(f).setURI("git@192.168.2.43:test.git");
//	    
//	    
//	    
//	    try {
//	        clone.call();
//	    } catch (GitAPIException e) {
//	        e.printStackTrace();
//	    }
//	    Files.write("testing it...", new File(folder + "/test2.txt"),
//	            Charsets.UTF_8);
//	    Git g = new Git(repository);
//	    g.add().addFilepattern("*").call();
	}

}
