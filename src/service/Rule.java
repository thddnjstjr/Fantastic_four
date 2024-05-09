package service;

import gomoku.components.Background;

public class Rule {

	Background mContext;
	Rule33 rule33;
	WinRule winRule;

	public Rule(Background mContext) {
		this.mContext = mContext;
		rule33 = new Rule33(mContext);
		// winRule = new WinRule(mContext);
	}

	public void winrule() {
		new Thread(() -> {

			// winrule();
		}).start();
	}

	public boolean checkRule33() {
		return rule33.checkRule33();
	}
}
