package com.restfriends.sample.app.lde;

import com.github.bordertech.lde.api.LdeLauncher;

/**
 * Start Tomcat Server.
 */
public final class LdeAppLauncherProxy {

	public static void main(final String[] args) throws Exception {
		LdeLauncher.launchServer();
	}
}
