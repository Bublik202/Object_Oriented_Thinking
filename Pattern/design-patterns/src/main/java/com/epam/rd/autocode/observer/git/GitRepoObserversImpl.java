package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitRepoObserversImpl implements Repository{
	private Map<String, List<WebHook>> webHooks = new HashMap<>();
	private Map<String, List<Commit>> branches = new HashMap<>();
	
	@Override
	public void addWebHook(WebHook webHook) {
		/*
		 * метод для добавления веб-хука к конкретной ветке. 
		 */
		String name = webHook.branch();
		webHooks.computeIfAbsent(name,  t -> new ArrayList<>()).add(webHook);
	}
	
	@Override
	public Commit commit(String branch, String author, String[] changes) {
		/*
		 *  метод для создания нового коммита в указанной ветке. 
		 *  Метод получает имя ветки, автора коммита и список изменений. 
		 */
		List<Commit> branchCommits = branches.computeIfAbsent(branch, t -> new ArrayList<>()); 
		Commit commit = new Commit(author, changes);
		branchCommits.add(commit);		
		
		List<Event> events = new ArrayList<>();
	    events.add(new Event(Event.Type.COMMIT, branch, List.of(commit)));   
	    
	    List<WebHook> webHookList = webHooks.get(branch);
	    if (webHookList != null) {
	        for (WebHook webHook : webHookList) {
	            webHook.onEvent(new Event(Event.Type.COMMIT, branch, List.of(commit))); 
	        }
	    }		
		return commit;
	}
	
	@Override
	public void merge(String sourceBranch, String targetBranch) {
		List<Commit> sourceCommits = branches.get(sourceBranch);
	    if (sourceCommits == null || sourceCommits.isEmpty()) {
	        return; 
	    }

	    List<Commit> targetCommits = branches.computeIfAbsent(targetBranch, k -> new ArrayList<>());

	    List<Commit> newCommits = new ArrayList<>();
	    for (Commit sourceCommit : sourceCommits) {
	        boolean existsInTarget = false;
	        for (Commit targetCommit : targetCommits) {
	            if (sourceCommit.equals(targetCommit)) {
	                existsInTarget = true;
	                break;
	            }
	        }
	        if (!existsInTarget) {
	            newCommits.add(sourceCommit);
	        }
	    }

	    targetCommits.addAll(newCommits);

	    List<Event> events = new ArrayList<>();
	    if (!newCommits.isEmpty()) {	    	
	        events.add(new Event(Event.Type.MERGE, targetBranch, newCommits));	       
	    }
	    
	    List<WebHook> targetWebHooks = webHooks.get(targetBranch);
	    if (targetWebHooks != null) {
	        for (WebHook webHook : targetWebHooks) {
	            if (!newCommits.isEmpty()) {
	                webHook.onEvent(new Event(Event.Type.MERGE, targetBranch, newCommits));
	            }
	        }
	    }
	}
	
}
