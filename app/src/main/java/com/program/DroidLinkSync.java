package com.program;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DroidLinkSync {
    private static final String TAG = "DroidLinkSync";

    public interface SyncCallback {
        void onSuccess(String summary);
        void onError(String errorMessage);
    }

    public static void ensureDomePanelPreferences(SharedPreferences prefs) {
        if (prefs == null) return;
        int ver = prefs.getInt("dome_cmds_version", 0);
        if (ver < 3) {
            SharedPreferences.Editor editor = prefs.edit();
            for (int i = 1; i <= 12; i++) {
                String slotStr = String.format("%02d", i - 1);
                String expectedOpen = ":DS" + slotStr + "O";
                String expectedClose = ":DS" + slotStr + "C";
                String curOpen = prefs.getString("dp" + i + "_open", "");
                String curClose = prefs.getString("dp" + i + "_close", "");
                if (curOpen.isEmpty() || !curOpen.endsWith("O") || curOpen.equals(":DS" + String.format("%02d", i))) {
                    editor.putString("dp" + i + "_open", expectedOpen);
                }
                if (curClose.isEmpty() || !curClose.endsWith("C") || curClose.equals(":DS00") || curClose.equals(":CL" + String.format("%02d", i))) {
                    editor.putString("dp" + i + "_close", expectedClose);
                }
            }
            editor.putInt("dome_cmds_version", 3);
            editor.apply();
        }
    }

    public static void ensureBodyPanelPreferences(SharedPreferences prefs) {
        if (prefs == null) return;
        int ver = prefs.getInt("body_cmds_version", 0);
        if (ver < 2) {
            SharedPreferences.Editor editor = prefs.edit();
            for (int i = 0; i < Body_activity.COUNT; i++) {
                int out = Body_activity.OUTPUT_NUMS[i];
                String defName = Body_activity.DEFAULT_NAMES[i];
                String defToggle = Body_activity.getDefaultToggleCmd(out);
                String defOpen = Body_activity.getDefaultOpenCmd(out);
                String defClose = Body_activity.getDefaultCloseCmd(out);

                if (!prefs.contains("bp_" + out + "_name")) {
                    editor.putString("bp_" + out + "_name", defName);
                }
                if (!prefs.contains("bp_" + out + "_cmd")) {
                    editor.putString("bp_" + out + "_cmd", defToggle);
                }
                if (!prefs.contains("bp_" + out + "_open")) {
                    editor.putString("bp_" + out + "_open", defOpen);
                }
                if (!prefs.contains("bp_" + out + "_close")) {
                    editor.putString("bp_" + out + "_close", defClose);
                }
            }
            editor.putInt("body_cmds_version", 2);
            editor.apply();
        }
    }

    public static void syncFromDevice(final Context context, final String ipAddress, final SyncCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    String urlStr = "http://" + ipAddress + "/load";
                    URL url = new URL(urlStr);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(4000);
                    conn.setReadTimeout(4000);

                    int code = conn.getResponseCode();
                    if (code != 200) {
                        if (callback != null) {
                            callback.onError("Server returned HTTP " + code);
                        }
                        return;
                    }

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();

                    JSONObject root = new JSONObject(sb.toString());
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();

                    int bodyCount = 0;
                    if (root.has("body")) {
                        JSONArray body = root.getJSONArray("body");
                        int count = Math.min(22, body.length());
                        for (int i = 0; i < count; i++) {
                            JSONObject item = body.getJSONObject(i);
                            int keyNum = i + 1;
                            String label = item.optString("l", "Body " + keyNum);
                            String cmd = item.optString("c", "");
                            editor.putString("mk" + keyNum + "_name", label);
                            editor.putString("mk" + keyNum, cmd);

                            if (i < Body_activity.COUNT) {
                                int out = Body_activity.OUTPUT_NUMS[i];
                                editor.putString("bp_" + out + "_name", label);
                                if (cmd != null && !cmd.trim().isEmpty()) {
                                    editor.putString("bp_" + out + "_cmd", cmd.trim());
                                }
                            }

                            bodyCount++;
                        }
                    }

                    int domeCount = 0;
                    if (root.has("dome")) {
                        JSONArray dome = root.getJSONArray("dome");
                        int count = Math.min(22, dome.length());
                        for (int i = 0; i < count; i++) {
                            JSONObject item = dome.getJSONObject(i);
                            int keyNum = i + 1;
                            String label = item.optString("l", "Dome " + keyNum);
                            String cmd = item.optString("c", "");
                            editor.putString("pk" + keyNum + "_name", label);
                            editor.putString("pk" + keyNum, cmd);

                            // Map first 12 dome items to Dome Panels 1-12
                            if (i < 12) {
                                editor.putString("dp" + (i + 1) + "_name", label);
                                String slotStr = String.format("%02d", i);
                                String openCmd = (cmd != null && !cmd.trim().isEmpty()) ? cmd : (":DS" + slotStr + "O");
                                String closeCmd = ":DS" + slotStr + "C";
                                if (openCmd.endsWith("O")) {
                                    closeCmd = openCmd.substring(0, openCmd.length() - 1) + "C";
                                } else if (openCmd.startsWith(":OP")) {
                                    closeCmd = ":CL" + openCmd.substring(3);
                                }
                                editor.putString("dp" + (i + 1) + "_open", openCmd);
                                editor.putString("dp" + (i + 1) + "_close", closeCmd);
                            }

                            domeCount++;
                        }
                    }

                    int audioCount = 0;
                    if (root.has("audio")) {
                        JSONArray audio = root.getJSONArray("audio");
                        int count = Math.min(22, audio.length());
                        for (int i = 0; i < count; i++) {
                            JSONObject item = audio.getJSONObject(i);
                            int keyNum = i + 1;
                            String label = item.optString("l", "Sound " + keyNum);
                            String cmd = item.optString("c", "");
                            editor.putString("sgk" + keyNum + "_name", label);
                            editor.putString("sgk" + keyNum, cmd);
                            audioCount++;
                        }
                    }

                    editor.apply();

                    if (callback != null) {
                        callback.onSuccess("Synced " + bodyCount + " Body buttons, " + domeCount + " Dome buttons, and " + audioCount + " Audio buttons from DroidLink!");
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Sync error", e);
                    if (callback != null) {
                        callback.onError(e.getMessage() != null ? e.getMessage() : e.toString());
                    }
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}
