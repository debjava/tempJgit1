package com.ddlab.tornado.bean;

import java.io.File;

public class Repo {

	private String userName;
	private String password;
	private AccountType accountType;
	private File cloneDirectory;

	private Repo(RepoBuilder repoBuilder) {
		super();
		this.userName = repoBuilder.userName;
		this.password = repoBuilder.password;
		this.accountType = repoBuilder.accountType;
		this.cloneDirectory = repoBuilder.cloneDirectory;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public File getCloneDirectory() {
		return cloneDirectory;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	@Override
	public String toString() {
		return "Repo [userName=" + userName + ", password=" + password + ", accountType=" + accountType
				+ ", cloneDirectory=" + cloneDirectory + "]";
	}

	public static class RepoBuilder {
		private String userName;
		private String password;
		private AccountType accountType;
		private File cloneDirectory;

		public RepoBuilder(String userName, String password) {
			super();
			this.userName = userName;
			this.password = password;
		}

		public RepoBuilder account(AccountType accountType) {
			this.accountType = accountType;
			return this;
		}

		public RepoBuilder cloneDirectory(File cloneDirectory) {
			this.cloneDirectory = cloneDirectory;
			return this;
		}

		public Repo build() {
			return new Repo(this);
		}

	}

}
