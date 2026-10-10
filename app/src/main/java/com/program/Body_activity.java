package com.program;

import com.program.r2_touch_android.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Body_activity extends Activity {

    private final Button[] doorButtons = new Button[6];
    private final Button[] drawerButtons = new Button[8];
    private final boolean[] doorOpen = new boolean[6];
    private final boolean[] drawerOpen = new boolean[8];

    private TextView statusText;
    private Button syncButton;
    private string_parser stringParser;
    private SharedPreferences prefs;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private static final String[] DEFAULT_DOOR_NAMES = {
            "UL Door (UT)", "UR Door (UT)", "Center Up Door",
            "Center Low Door", "LL Gripper Door", "LR Tool Door"
    };

    private static final String[] DEFAULT_DOOR_CMDS = {
            ":BS7T", ":BS8T", ":BS03",
            ":BS04", ":BS05", ":BS06"
    };

    private static final String[] DEFAULT_DRAWER_NAMES = {
            "Drawer 1", "Drawer 2", "Drawer 3", "Drawer 4",
            "Drawer 5", "Drawer 6", "Drawer 7", "Drawer 8"
    };

    private static final String[] DEFAULT_DRAWER_CMDS = {
            ":BS07", ":BS08", ":BS09", ":BS10",
            ":BS11", ":BS12", ":BS13", ":BS14"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_panel_screen);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        stringParser = new string_parser();

        statusText = (TextView) findViewById(R.id.body_status_text);
        syncButton = (Button) findViewById(R.id.button_sync_dl);

        // Home / Return button
        ImageButton homeButton = (ImageButton) findViewById(R.id.home_Button);
        if (homeButton != null) {
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // Initialize 6 Door Buttons
        doorButtons[0] = (Button) findViewById(R.id.button_door_1);
        doorButtons[1] = (Button) findViewById(R.id.button_door_2);
        doorButtons[2] = (Button) findViewById(R.id.button_door_3);
        doorButtons[3] = (Button) findViewById(R.id.button_door_4);
        doorButtons[4] = (Button) findViewById(R.id.button_door_5);
        doorButtons[5] = (Button) findViewById(R.id.button_door_6);

        for (int i = 0; i < 6; i++) {
            final int index = i;
            if (doorButtons[index] != null) {
                doorButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        triggerDoor(index);
                    }
                });
            }
        }

        // Initialize 8 Drawer Buttons
        drawerButtons[0] = (Button) findViewById(R.id.button_drawer_1);
        drawerButtons[1] = (Button) findViewById(R.id.button_drawer_2);
        drawerButtons[2] = (Button) findViewById(R.id.button_drawer_3);
        drawerButtons[3] = (Button) findViewById(R.id.button_drawer_4);
        drawerButtons[4] = (Button) findViewById(R.id.button_drawer_5);
        drawerButtons[5] = (Button) findViewById(R.id.button_drawer_6);
        drawerButtons[6] = (Button) findViewById(R.id.button_drawer_7);
        drawerButtons[7] = (Button) findViewById(R.id.button_drawer_8);

        for (int i = 0; i < 8; i++) {
            final int index = i;
            if (drawerButtons[index] != null) {
                drawerButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        triggerDrawer(index);
                    }
                });
            }
        }

        // Master Controls
        Button masterOpen = (Button) findViewById(R.id.button_master_open);
        if (masterOpen != null) {
            masterOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allOpen();
                }
            });
        }

        Button masterClose = (Button) findViewById(R.id.button_master_close);
        if (masterClose != null) {
            masterClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allClose();
                }
            });
        }

        Button doorsOpen = (Button) findViewById(R.id.button_doors_open);
        if (doorsOpen != null) {
            doorsOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doorsAllOpen();
                }
            });
        }

        Button doorsClose = (Button) findViewById(R.id.button_doors_close);
        if (doorsClose != null) {
            doorsClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doorsAllClose();
                }
            });
        }

        Button drawersOpen = (Button) findViewById(R.id.button_drawers_open);
        if (drawersOpen != null) {
            drawersOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawersAllOpen();
                }
            });
        }

        Button drawersClose = (Button) findViewById(R.id.button_drawers_close);
        if (drawersClose != null) {
            drawersClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawersAllClose();
                }
            });
        }

        // Sync button
        if (syncButton != null) {
            syncButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    syncFromDroidLink();
                }
            });
        }

        loadLabels();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLabels();
    }

    private void loadLabels() {
        for (int i = 0; i < 6; i++) {
            if (doorButtons[i] != null) {
                String name = prefs.getString("door" + (i + 1) + "_name", DEFAULT_DOOR_NAMES[i]);
                String suffix = doorOpen[i] ? " [OPEN]" : "";
                doorButtons[i].setText(name + suffix);
            }
        }

        for (int i = 0; i < 8; i++) {
            if (drawerButtons[i] != null) {
                String name = prefs.getString("drawer" + (i + 1) + "_name", DEFAULT_DRAWER_NAMES[i]);
                String suffix = drawerOpen[i] ? " [OPEN]" : "";
                drawerButtons[i].setText(name + suffix);
            }
        }
    }

    private void sendCommand(String cmd, String description) {
        if (cmd == null || cmd.trim().isEmpty()) {
            return;
        }
        stringParser.select_string(cmd);
        if (statusText != null) {
            statusText.setText("Sent: " + cmd + " (" + description + ")");
        }
    }

    private void triggerDoor(int index) {
        String cmd = prefs.getString("door" + (index + 1) + "_cmd", DEFAULT_DOOR_CMDS[index]);
        String name = prefs.getString("door" + (index + 1) + "_name", DEFAULT_DOOR_NAMES[index]);
        doorOpen[index] = !doorOpen[index];
        sendCommand(cmd, name);
        loadLabels();
    }

    private void triggerDrawer(int index) {
        String cmd = prefs.getString("drawer" + (index + 1) + "_cmd", DEFAULT_DRAWER_CMDS[index]);
        String name = prefs.getString("drawer" + (index + 1) + "_name", DEFAULT_DRAWER_NAMES[index]);
        drawerOpen[index] = !drawerOpen[index];
        sendCommand(cmd, name);
        loadLabels();
    }

    private void doorsAllOpen() {
        for (int i = 0; i < 6; i++) {
            doorOpen[i] = true;
            String cmd = prefs.getString("door" + (i + 1) + "_cmd", DEFAULT_DOOR_CMDS[i]);
            sendCommand(cmd, "Door " + (i + 1));
        }
        loadLabels();
        Toast.makeText(this, "Opening All Doors", Toast.LENGTH_SHORT).show();
    }

    private void doorsAllClose() {
        // Send Master Close or close individual doors
        String masterCloseCmd = prefs.getString("master_body_close", ":BS00");
        sendCommand(masterCloseCmd, "Close All Doors");
        for (int i = 0; i < 6; i++) {
            doorOpen[i] = false;
        }
        loadLabels();
        Toast.makeText(this, "Closing All Doors", Toast.LENGTH_SHORT).show();
    }

    private void drawersAllOpen() {
        for (int i = 0; i < 8; i++) {
            drawerOpen[i] = true;
            String cmd = prefs.getString("drawer" + (i + 1) + "_cmd", DEFAULT_DRAWER_CMDS[i]);
            sendCommand(cmd, "Drawer " + (i + 1));
        }
        loadLabels();
        Toast.makeText(this, "Opening All Drawers", Toast.LENGTH_SHORT).show();
    }

    private void drawersAllClose() {
        String masterCloseCmd = prefs.getString("master_body_close", ":BS00");
        sendCommand(masterCloseCmd, "Close All Drawers");
        for (int i = 0; i < 8; i++) {
            drawerOpen[i] = false;
        }
        loadLabels();
        Toast.makeText(this, "Closing All Drawers", Toast.LENGTH_SHORT).show();
    }

    private void allOpen() {
        for (int i = 0; i < 6; i++) {
            doorOpen[i] = true;
            String cmd = prefs.getString("door" + (i + 1) + "_cmd", DEFAULT_DOOR_CMDS[i]);
            sendCommand(cmd, "Door " + (i + 1));
        }
        for (int i = 0; i < 8; i++) {
            drawerOpen[i] = true;
            String cmd = prefs.getString("drawer" + (i + 1) + "_cmd", DEFAULT_DRAWER_CMDS[i]);
            sendCommand(cmd, "Drawer " + (i + 1));
        }
        loadLabels();
        Toast.makeText(this, "Opening All Body Panels & Drawers", Toast.LENGTH_SHORT).show();
    }

    private void allClose() {
        String masterCloseCmd = prefs.getString("master_body_close", ":BS00");
        sendCommand(masterCloseCmd, "Close All Body Panels & Drawers");
        for (int i = 0; i < 6; i++) {
            doorOpen[i] = false;
        }
        for (int i = 0; i < 8; i++) {
            drawerOpen[i] = false;
        }
        loadLabels();
        Toast.makeText(this, "Closed All Body Panels & Drawers (:BS00)", Toast.LENGTH_SHORT).show();
    }

    private void syncFromDroidLink() {
        final String ip = prefs.getString("prefUserAddress", "192.168.4.1");
        if (syncButton != null) {
            syncButton.setEnabled(false);
            syncButton.setText("SYNCING...");
        }
        if (statusText != null) {
            statusText.setText("Connecting to http://" + ip + "/load ...");
        }

        DroidLinkSync.syncFromDevice(this, ip, new DroidLinkSync.SyncCallback() {
            @Override
            public void onSuccess(final String summary) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (syncButton != null) {
                            syncButton.setEnabled(true);
                            syncButton.setText("SYNC DROIDLINK");
                        }
                        loadLabels();
                        if (statusText != null) {
                            statusText.setText("Sync successful! Labels updated from DroidLink.");
                        }
                        Toast.makeText(Body_activity.this, "Synced with DroidLink: " + summary, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(final String errorMessage) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (syncButton != null) {
                            syncButton.setEnabled(true);
                            syncButton.setText("SYNC DROIDLINK");
                        }
                        if (statusText != null) {
                            statusText.setText("Sync failed: " + errorMessage);
                        }
                        Toast.makeText(Body_activity.this, "Sync failed: " + errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
