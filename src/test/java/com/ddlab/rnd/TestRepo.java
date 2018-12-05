package com.ddlab.rnd;

import java.io.File;

import com.ddlab.tornado.bean.AccountType;
import com.ddlab.tornado.bean.Repo;

public class TestRepo {

	public static void main(String[] args) {
		
		Repo repo = new Repo.RepoBuilder("sds","dsss").account(AccountType.GITHUB).cloneDirectory(new File(".")).build();
		System.out.println(repo.getUserName());

	}

}
