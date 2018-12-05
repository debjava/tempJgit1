package com.ddlab.rnd;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class TestAdd1 {

	public static void main(String[] args) throws Exception {
		String userName = "sambittechy@gmail.com";
		String password = "abcd@1234";
		File cloneDir = new File("deleteMe1");
		String gitURI = "https://github.com/sambittechy/deleteMe1.git";
		
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		try {
			Repository repository = builder.setGitDir(cloneDir)
			        .readEnvironment().findGitDir().setup().build();
			System.out.println("Now repository : "+repository);
			boolean bareFlag = repository.isBare();
			System.out.println("Repository is bare : "+bareFlag);
			
			System.out.println(repository.getDirectory());
			System.out.println(repository.getRefDatabase());
			
			
			
			Git git = Git.init().setDirectory(cloneDir).call();
			
//			Git git = Git.open(cloneDir);
			
//			System.out.println("------"+repository.getDirectory().getParent());
			
//			File myfile = new File(repository.getDirectory(), "testfile");
//            if(!myfile.createNewFile()) {
//                throw new IOException("Could not create file " + myfile);
//            }
			
			
			git.add().addFilepattern(".").call();
			
//			git.commit()
//            .setMessage("Commit all changes including additions")
//            .call();
			
			git.commit()
            .setAll(true)
            .setMessage("Commit changes to all files")
            .call();
			
			
			CredentialsProvider cp = new UsernamePasswordCredentialsProvider(userName, password);
			PushCommand pc = git.push();
	        pc.setCredentialsProvider(cp)
	                .setForce(true)
	                .setPushAll();
	        
	        try {
	            Iterator<PushResult> it = pc.call().iterator();
	            if(it.hasNext()){
	                System.out.println(it.next().toString());
	            }
	        } catch (InvalidRemoteException e) {
	            e.printStackTrace();
	        }
            
			
			
//			final StoredConfig config = repository.getConfig();
//			RemoteConfig remoteConfig = new RemoteConfig(config, "deleteMe1");
//			URIish uri = new URIish(repository.getDirectory().toURI().toURL());
//			remoteConfig.addURI(uri);
//			remoteConfig.update(config);
//			config.save();
//			
//			terable<PushResult> resultIterable = git.push().setRemote(remoteConfig)
//				    .setRefSpecs(spec).call();
//			
//			git.push().call();
			
			
			
//            git.add().addFilepattern("temp1.txt").call();
//            git.commit().setMessage("Added testfile").call();
//            try (Git git = new Git(repository)) {
//                git.add()
//                        .addFilepattern("testfile")
//                        .call();
//
//
//                // and then commit the changes
//                git.commit()
//                        .setMessage("Added testfile")
//                        .call();
//            }

//            System.out.println("Added file " + myfile + " to repository at " + repository.getDirectory());

			
			
//			ObjectId lastCommitId = repository.resolve(Constants.HEAD);
//			System.out.println("lastCommitId------>"+lastCommitId);
//			
//			
//			Git git = new Git(repository);
//			AddCommand addCmd = git.add();
//			addCmd.setUpdate(false);
//			git.add().addFilepattern(".").call();
			
			
//			try (RevWalk revWalk = new RevWalk(repository)) {
//				System.out.println("RevWalk :::"+revWalk);
//                RevCommit commit = revWalk.parseCommit(lastCommitId);
//                System.out.println("Now commit : "+commit);
//                // and using commit's tree find the path
//                RevTree tree = commit.getTree();
//                System.out.println("Having tree: " + tree);
//
//                // now try to find a specific file
//                try (TreeWalk treeWalk = new TreeWalk(repository)) {
//                    treeWalk.addTree(tree);
//                    treeWalk.setRecursive(true);
//                    treeWalk.setFilter(PathFilter.create("README.md"));
//                    if (!treeWalk.next()) {
//                        throw new IllegalStateException("Did not find expected file 'README.md'");
//                    }
//
//                    ObjectId objectId = treeWalk.getObjectId(0);
//                    ObjectLoader loader = repository.open(objectId);
//
//                    // and then one can the loader to read the file
//                    loader.copyTo(System.out);
//                }
//
//                revWalk.dispose();
//        }
			
			
			
//			Git g = new Git(repository);
////			g.add().addFilepattern(".").call();
//			File localPath = repository.getWorkTree();
//			g.add().setUpdate(true).addFilepattern(".").call();
////			g.add().addFilepattern("temp1.txt").call();
////			// and then commit the changes
////			g.commit().setMessage("Added testfile").call();
			
			
			
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		catch (NoFilepatternException e) {
//			e.printStackTrace();
//		} catch (GitAPIException e) {
//			e.printStackTrace();
//		}
	}

}
