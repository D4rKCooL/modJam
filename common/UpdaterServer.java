package fuj1n.awesomeMod.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

import cpw.mods.fml.common.registry.GameRegistry;
import fuj1n.awesomeMod.ModJam;

public class UpdaterServer {

	public static int timeout = 2000;
	private static String serverLocation = "https://dl.dropbox.com/s/qep7kngvujbw7ke/version.txt?token_hash=AAEDgvy7MLrYWWrHCjStYjz0zQlL6HNuxTPGPEpqXuKDCw&dl=1";

	private static URL url;
	public static int currentMod = 0;
	public static int latestMinecraft = 0;
	public static int latestMod = 0;

	public UpdaterServer() {
		try {
			currentMod = getCurrentModRelease();
			latestMinecraft = getLatestCompatibleMinecraft();
			latestMod = getLatestModRelease();
		} catch (IOException e) {
			ModJam.log("Could not establish an internet connection (Connection Timeout).", Level.WARNING);
		}
		ModJam.log("Latest version of the mod detected is: " + latestMod + " for Minecraft " + latestMinecraft + ", while the currently installed version is: " + currentMod + ".", Level.INFO);
		GameRegistry.registerPlayerTracker(new PlayerTrackerModJam(0, currentMod, latestMinecraft, latestMod));
	}

	public static int getLatestModRelease() throws IOException {
		url = new URL(serverLocation);
		URLConnection con = url.openConnection();
		con.setConnectTimeout(timeout);
		con.setReadTimeout(timeout);
		InputStream io = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(io));
		br.readLine();
		int latestModVersion = Integer.parseInt(br.readLine());
		return latestModVersion;
	}

	public static int getLatestCompatibleMinecraft() throws IOException {
		url = new URL(serverLocation);
		URLConnection con = url.openConnection();
		con.setConnectTimeout(timeout);
		con.setReadTimeout(timeout);
		InputStream io = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(io));
		int latestMcVersion = Integer.parseInt(br.readLine());
		return latestMcVersion;
	}

	public static int getCurrentModRelease() {
		return CommonProxyModJam.currentVersion;
	}
}
