package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

import com.epam.rd.autocode.observer.git.Event.Type;

public class commitToBranchWebHookImpl implements WebHook{
	private String branch;
	private List<Event> events = new ArrayList<>();

	public commitToBranchWebHookImpl(String branch) {
		super();
		this.branch = branch;
	}

	@Override
	public String branch() {
		return branch;
	}

	@Override
	public Type type() {
		return Event.Type.COMMIT;
	}

	@Override
	public List<Event> caughtEvents() {
		return events;
	}

	@Override
	public void onEvent(Event event) {
		if(event.type() == Event.Type.COMMIT && event.branch().equals(branch)) {
			events.add(event);
		}
	}
}
