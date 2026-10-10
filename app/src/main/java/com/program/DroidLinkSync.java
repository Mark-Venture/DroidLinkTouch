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

                            // Map first 6 body buttons to Doors 1-6
                            if (i < 6) {
                                editor.putString("door" + (i + 1) + "_name", label);
                                editor.putString("door" + (i + 1) + "_cmd", cmd);
                            }
                            // Map next 8 body buttons (7-14) to Drawers 1-8
                            else if (i < 14) {
                                int drawerNum = (i - 6) + 1;
                                editor.putString("drawer" + drawerNum + "_name", label);
                                editor.putString("drawer" + drawerNum + "_cmd", cmd);
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

                            // Map first 10 dome items to Dome Panels 1-10
                            if (i < 10) {
                                editor.putString("dp" + (i + 1) + "_name", label);
                                editor.putString("dp" + (i + 1) + "_open", cmd);
                                if (cmd.startsWith(":DS") || cmd.startsWith(":BS")) {
                                    editor.putString("dp" + (i + 1) + "_close", ":DS00");
                                } else {
                                    editor.putString("dp" + (i + 1) + "_close", ":CL" + String.format("%02d", i + 1));
                                }
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
