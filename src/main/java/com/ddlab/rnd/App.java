package com.ddlab.rnd;

import java.io.File;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BaseRepositoryBuilder;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class App {
	public static void main(String[] args) throws Exception {
		String userName = "deba.java@gmail.com";
		String password = "pikupiku11";
		CredentialsProvider crProvider = new UsernamePasswordCredentialsProvider(userName, password);
		Git git = Git.cloneRepository()
				.setCredentialsProvider(crProvider)
				.setURI("https://github.com/debjava/Hello-World.git")
				.setDirectory(new File("mytempRepo"))
				.call();
		
		
//		Repository r = new 
//		Git g = new Git(repo);
		
		AddCommand add = git.add();
		add.addFilepattern("*.*").call();
		
		
		
		
		
		Repository repo = git.getRepository();
		RevWalk walk = new RevWalk(repo);
		ObjectId lastCommitId = repo.resolve(Constants.HEAD);
		RevCommit commit = walk.parseCommit(lastCommitId);
		
		
	}
}
