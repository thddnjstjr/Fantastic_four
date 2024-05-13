package gomoku4.service;

import gomoku4.components.Background;

public class Rule {

	Background mContext;
	Rule33 rule33;
	WinRule winRule;

	public Rule(Background mContext) {
		this.mContext = mContext;
		rule33 = new Rule33(mContext);
	}

	public void winrule() {
		new Thread(() -> {

		}).start();
	}
}
