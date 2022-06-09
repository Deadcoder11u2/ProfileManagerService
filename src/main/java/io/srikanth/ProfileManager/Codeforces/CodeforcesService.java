package io.srikanth.ProfileManager.Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeforcesService {

    @Autowired
    public ContestRepository contestRepository;

    private final String API = "https://codeforces.com/api/";

    private static final Logger logger = LogManager.getLogger(CodeforcesService.class);

    public boolean cacheContests() throws IOException {
	URL url = new URL(API + "contest.list");
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	con.setRequestMethod("GET");
	con.setRequestProperty("Content-Type", "application/json");
	int status_code = con.getResponseCode();
	if (status_code > 300) {
	    logger.error("Status code error :" + status_code);
	    return false;
	}
	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
	String line;
	StringBuilder response = new StringBuilder();
	while ((line = br.readLine()) != null) {
	    response.append(line);
	}
	JSONObject obj = new JSONObject(response.toString());
	String status = obj.getString("status");
	if (!status.equals("OK")) {
	    logger.error("Status Error API", status);
	    return false;
	}
	JSONArray contests_list = obj.getJSONArray("result");
	for (int i = 0; i < contests_list.length(); i++) {
	    JSONObject contest_json = contests_list.getJSONObject(i);
	    try {
		Contest contest = new Contest(Integer.toString(contest_json.getInt("id")), contest_json.getString("name"));
		String contest_type = contest_json.getString("type");
		if (contest_type.equals("CF")) {
		    contest.setType(ContestType.CF);
		} else if (contest_type.equals("IOI")) {
		    contest.setType(ContestType.IOI);
		} else if (contest_type.equals("ICPC")) {
		    contest.setType(ContestType.ICPC);
		} else {
		    logger.error("Contest Type missing for contest", contest);
		}
		contestRepository.save(contest);
	    } catch (JSONException e) {
		logger.error("Json Object Error", e.toString());
	    }
	}
	return true;
    }
}
