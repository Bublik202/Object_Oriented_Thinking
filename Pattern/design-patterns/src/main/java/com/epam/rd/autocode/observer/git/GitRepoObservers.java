package com.epam.rd.autocode.observer.git;

public class GitRepoObservers {
    public static Repository newRepository(){
    	return new GitRepoObserversImpl();
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new mergeToBranchWebHookImpl(branchName);
    }

    public static WebHook commitToBranchWebHook(String branchName){
    	return new commitToBranchWebHookImpl(branchName);
    }
    
}
