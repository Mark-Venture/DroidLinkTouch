package com.program;

import com.program.r2_touch_android.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Body_activity extends Activity implements View.OnTouchListener {

    public static final int COUNT = 17;
    public static final int SEQ_COUNT = 12;

    // DefaultOutput numbers from user specification for wireframe mechanisms
    public static final int[] OUTPUT_NUMS = {
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            16, 17, 18, 19, 20, 21, 22, 23
    };

    public static final String[] KEYS = {
            "FRONT_LEFT_DOOR", "FRONT_RIGHT_DOOR", "BACK_LEFT_DOOR", "BACK_RIGHT_DOOR",
            "CHARGE_BAY", "DATA_PORT", "SMALL_DOOR", "UPPER_UTIL_ARM", "LOWER_UTIL_ARM",
            "DRAWER_1", "DRAWER_2", "DRAWER_3", "DRAWER_4",
            "DRAWER_5", "DRAWER_6", "DRAWER_7", "DRAWER_8"
    };

    public static final String[] DEFAULT_NAMES = {
            "FRONT LEFT DOOR", "FRONT RIGHT DOOR", "BACK LEFT DOOR", "BACK RIGHT DOOR",
            "CHARGE BAY", "DATA PORT", "SMALL DOOR", "UPPER UTIL ARM", "LOWER UTIL ARM",
            "DRAWER 1", "DRAWER 2", "DRAWER 3", "DRAWER 4",
            "DRAWER 5", "DRAWER 6", "DRAWER 7", "DRAWER 8"
    };

    // Default labels and commands for the 12 customizable Body Sequence buttons
    public static final String[] DEFAULT_SEQ_NAMES = {
            "UTIL ARMS WAVE", "FRONT DOORS", "REAR DOORS",
            "DOORS OPEN", "DOORS CLOSE", "DRAWERS 1-3",
            "DRAWERS 4-7", "DRAWERS OPEN", "DRAWERS CLOSE",
            "SEQ 10", "SEQ 11", "SEQ 12"
    };

    public static final String[] DEFAULT_SEQ_CMDS = {
            ":BS07T:BS08T",
            ":BS00T:BS01T",
            ":BS02T:BS03T",
            ":BS00O:BS01O:BS02O:BS03O:BS04O:BS05O:BS06O",
            ":BS00C:BS01C:BS02C:BS03C:BS04C:BS05C:BS06C",
            ":BS16T:BS17T:BS18T",
            ":BS19T:BS20T:BS21T:BS22T",
            ":BS16O:BS17O:BS18O:BS19O:BS20O:BS21O:BS22O:BS23O",
            ":BS16C:BS17C:BS18C:BS19C:BS20C:BS21C:BS22C:BS23C",
            ":SE10",
            ":SE11",
            ":SE12"
    };

    // Panel bounding boxes in 580x720 wireframe coordinate space: {x1, y1, x2, y2}
    private static final int[][] PANEL_RECTS = {
            {410, 52, 452, 274},   // 0: FRONT LEFT DOOR
            {128, 52, 170, 274},   // 1: FRONT RIGHT DOOR
            {148, 508, 190, 614},  // 2: BACK LEFT DOOR
            {390, 508, 432, 614},  // 3: BACK RIGHT DOOR
            {178, 136, 246, 190},  // 4: CHARGE BAY
            {334, 136, 402, 274},  // 5: DATA PORT
            {206, 196, 246, 274},  // 6: SMALL DOOR
            {190, 52, 390, 86},    // 7: UPPER UTIL ARM
            {190, 90, 390, 124},   // 8: LOWER UTIL ARM
            {334, 326, 362, 386},  // 16: DRAWER 1
            {370, 326, 390, 386},  // 17: DRAWER 2
            {396, 326, 414, 386},  // 18: DRAWER 3
            {178, 282, 246, 318},  // 19: DRAWER 4
            {256, 282, 324, 318},  // 20: DRAWER 5
            {334, 282, 402, 318},  // 21: DRAWER 6
            {410, 282, 452, 318},  // 22: DRAWER 7
            {390, 622, 432, 648}   // 23: DRAWER 8
    };

    // Badge circles & pointer targets in 580x720 coordinate space: {bx, by, r, px, py} (px=-1 if no pointer)
    private static final int[][] BADGE_COORDS = {
            {516, 163, 19, 452, 163},  // 0: FRONT LEFT DOOR
            {64,  163, 19, 128, 163},  // 1: FRONT RIGHT DOOR
            {74,  561, 19, 148, 561},  // 2: BACK LEFT DOOR
            {506, 561, 19, 432, 561},  // 3: BACK RIGHT DOOR
            {212, 163, 16, -1,  -1},   // 4: CHARGE BAY
            {368, 205, 16, -1,  -1},   // 5: DATA PORT
            {226, 235, 16, -1,  -1},   // 6: SMALL DOOR
            {245, 69,  16, -1,  -1},   // 7: UPPER UTIL ARM
            {335, 107, 16, -1,  -1},   // 8: LOWER UTIL ARM
            {340, 452, 19, 348, 386},  // 16: DRAWER 1
            {392, 452, 19, 380, 386},  // 17: DRAWER 2
            {444, 452, 19, 405, 386},  // 18: DRAWER 3
            {212, 300, 15, -1,  -1},   // 19: DRAWER 4
            {290, 300, 15, -1,  -1},   // 20: DRAWER 5
            {368, 300, 15, -1,  -1},   // 21: DRAWER 6
            {516, 300, 19, 452, 300},  // 22: DRAWER 7
            {506, 635, 19, 432, 635}   // 23: DRAWER 8
    };

    private static final int[] SEQ_BUTTON_IDS = {
            R.id.button_bseq_1, R.id.button_bseq_2, R.id.button_bseq_3,
            R.id.button_bseq_4, R.id.button_bseq_5, R.id.button_bseq_6,
            R.id.button_bseq_7, R.id.button_bseq_8, R.id.button_bseq_9,
            R.id.button_bseq_10, R.id.button_bseq_11, R.id.button_bseq_12
    };

    private final Button[] seqButtons = new Button[SEQ_COUNT];
    private final boolean[] selected = new boolean[COUNT];

    private ImageView wireframeView;
    private ImageView highlightView;
    private TextView statusText;
    private Button syncButton;
    private string_parser stringParser;
    private SharedPreferences prefs;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public static String getDefaultToggleCmd(int outputNum) {
        return String.format(":BS%02dT", outputNum);
    }

    public static String getDefaultOpenCmd(int outputNum) {
        return String.format(":BS%02dO", outputNum);
    }

    public static String getDefaultCloseCmd(int outputNum) {
        return String.format(":BS%02dC", outputNum);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_panel_screen);

        PreferenceManager.setDefaultValues(this, R.xml.settings, true);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        DroidLinkSync.ensureBodyPanelPreferences(prefs);

        stringParser = new string_parser();

        statusText = (TextView) findViewById(R.id.body_status_text);
        syncButton = (Button) findViewById(R.id.button_sync_dl);
        wireframeView = (ImageView) findViewById(R.id.imageView_Body_Panels);
        highlightView = (ImageView) findViewById(R.id.imageView_Body_Highlights);

        if (wireframeView != null) {
            wireframeView.setOnTouchListener(this);
        }

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

        // Initialize all 12 customizable Sequence buttons
        for (int i = 0; i < SEQ_COUNT; i++) {
            final int index = i;
            seqButtons[i] = (Button) findViewById(SEQ_BUTTON_IDS[i]);
            if (seqButtons[i] != null) {
                seqButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        triggerSequence(index);
                    }
                });
            }
        }

        // Selection Quick Buttons
        Button selNone = (Button) findViewById(R.id.button_sel_none);
        if (selNone != null) {
            selNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectionGroup(false, false, false);
                }
            });
        }

        Button selDoors = (Button) findViewById(R.id.button_sel_doors);
        if (selDoors != null) {
            selDoors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectionGroup(true, false, false);
                }
            });
        }

        Button selArms = (Button) findViewById(R.id.button_sel_arms);
        if (selArms != null) {
            selArms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectionGroup(false, true, false);
                }
            });
        }

        Button selDrawers = (Button) findViewById(R.id.button_sel_drawers);
        if (selDrawers != null) {
            selDrawers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectionGroup(false, false, true);
                }
            });
        }

        Button selAll = (Button) findViewById(R.id.button_sel_all);
        if (selAll != null) {
            selAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectionGroup(true, true, true);
                }
            });
        }

        // Actions on Selected Panels
        Button selOpen = (Button) findViewById(R.id.button_sel_open);
        if (selOpen != null) {
            selOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executeSelectedAction('O');
                }
            });
        }

        Button selClose = (Button) findViewById(R.id.button_sel_close);
        if (selClose != null) {
            selClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executeSelectedAction('C');
                }
            });
        }

        Button selToggle = (Button) findViewById(R.id.button_sel_toggle);
        if (selToggle != null) {
            selToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executeSelectedAction('T');
                }
            });
        }

        // Master All Open/Close Controls
        Button masterOpen = (Button) findViewById(R.id.button_master_open);
        if (masterOpen != null) {
            masterOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executeRangeAction(0, COUNT - 1, 'O', "All Body Doors & Drawers");
                }
            });
        }

        Button masterClose = (Button) findViewById(R.id.button_master_close);
        if (masterClose != null) {
            masterClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executeRangeAction(0, COUNT - 1, 'C', "All Body Doors & Drawers");
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
        updateHighlights();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DroidLinkSync.ensureBodyPanelPreferences(prefs);
        loadLabels();
        updateHighlights();
    }

    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        final int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            return true;
        }
        if (action == MotionEvent.ACTION_UP) {
            int hitIndex = findHitMechanism(ev.getX(), ev.getY());
            if (hitIndex >= 0 && hitIndex < COUNT) {
                selected[hitIndex] = !selected[hitIndex];
                updateHighlights();
                updateSelectionStatus();
            }
            return true;
        }
        return false;
    }

    private int findHitMechanism(float touchX, float touchY) {
        if (wireframeView == null || wireframeView.getDrawable() == null) {
            return -1;
        }
        Drawable d = wireframeView.getDrawable();
        int dw = d.getIntrinsicWidth();
        int dh = d.getIntrinsicHeight();
        if (dw <= 0 || dh <= 0) {
            return -1;
        }

        Matrix inv = new Matrix();
        if (!wireframeView.getImageMatrix().invert(inv)) {
            return -1;
        }
        float[] pts = new float[] { touchX, touchY };
        inv.mapPoints(pts);

        // Convert from drawable intrinsic pixels to 580x720 coordinate space
        float wx = pts[0] * (580f / dw);
        float wy = pts[1] * (720f / dh);

        if (wx < 0 || wx > 580 || wy < 0 || wy > 720) {
            return -1;
        }

        // 1. Check circular number badges first (with generous touch tolerance)
        for (int i = 0; i < COUNT; i++) {
            int bx = BADGE_COORDS[i][0];
            int by = BADGE_COORDS[i][1];
            int r = BADGE_COORDS[i][2] + 6;
            float dx = wx - bx;
            float dy = wy - by;
            if (dx * dx + dy * dy <= r * r) {
                return i;
            }
        }

        // 2. Check panel rectangles
        for (int i = 0; i < COUNT; i++) {
            int x1 = PANEL_RECTS[i][0] - 3;
            int y1 = PANEL_RECTS[i][1] - 3;
            int x2 = PANEL_RECTS[i][2] + 3;
            int y2 = PANEL_RECTS[i][3] + 3;
            if (wx >= x1 && wx <= x2 && wy >= y1 && wy <= y2) {
                return i;
            }
        }

        return -1;
    }

    private void setSelectionGroup(boolean selectDoors, boolean selectArms, boolean selectDrawers) {
        for (int i = 0; i < COUNT; i++) {
            int out = OUTPUT_NUMS[i];
            if (out >= 0 && out <= 6) {
                selected[i] = selectDoors;
            } else if (out == 7 || out == 8) {
                selected[i] = selectArms;
            } else {
                selected[i] = selectDrawers;
            }
        }
        updateHighlights();
        updateSelectionStatus();
    }

    private void updateSelectionStatus() {
        if (statusText == null) return;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < COUNT; i++) {
            if (selected[i]) {
                if (count > 0) sb.append(", ");
                sb.append(OUTPUT_NUMS[i]);
                count++;
            }
        }
        if (count == 0) {
            statusText.setText("No panels selected | Tap wireframe to select or tap a Sequence button");
        } else {
            statusText.setText("Selected (" + count + "): [" + sb.toString() + "] -> Tap OPEN, CLOSE, or TOGGLE");
        }
    }

    private void updateHighlights() {
        if (highlightView == null || wireframeView == null) return;
        Drawable d = wireframeView.getDrawable();
        int dw = (d != null && d.getIntrinsicWidth() > 0) ? d.getIntrinsicWidth() : 580;
        int dh = (d != null && d.getIntrinsicHeight() > 0) ? d.getIntrinsicHeight() : 720;

        Bitmap bmp = Bitmap.createBitmap(dw, dh, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        float sx = dw / 580f;
        float sy = dh / 720f;
        float sAvg = (sx + sy) * 0.5f;

        Paint fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.argb(75, 0, 255, 0));

        Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(3.0f * sAvg);
        strokePaint.setColor(Color.argb(255, 0, 255, 0));

        Paint badgeBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        badgeBgPaint.setStyle(Paint.Style.FILL);
        badgeBgPaint.setColor(Color.argb(210, 0, 50, 0));

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.argb(255, 0, 255, 0));
        textPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        textPaint.setTextAlign(Paint.Align.CENTER);

        for (int i = 0; i < COUNT; i++) {
            if (!selected[i]) continue;

            int[] rect = PANEL_RECTS[i];
            RectF rf = new RectF(rect[0] * sx, rect[1] * sy, rect[2] * sx, rect[3] * sy);
            canvas.drawRect(rf, fillPaint);
            canvas.drawRect(rf, strokePaint);

            int bx = BADGE_COORDS[i][0];
            int by = BADGE_COORDS[i][1];
            int r = BADGE_COORDS[i][2];
            int px = BADGE_COORDS[i][3];
            int py = BADGE_COORDS[i][4];

            if (px >= 0 && py >= 0) {
                float dx = px - bx;
                float dy = py - by;
                float dist = (float) Math.hypot(dx, dy);
                if (dist > r) {
                    float startX = bx + dx * (r / dist);
                    float startY = by + dy * (r / dist);
                    canvas.drawLine(startX * sx, startY * sy, px * sx, py * sy, strokePaint);
                }
            }

            float cX = bx * sx;
            float cY = by * sy;
            float cR = r * sAvg;
            canvas.drawCircle(cX, cY, cR, badgeBgPaint);
            canvas.drawCircle(cX, cY, cR, strokePaint);

            textPaint.setTextSize((r <= 16 ? 14f : 16f) * sAvg);
            Paint.FontMetrics fm = textPaint.getFontMetrics();
            float textY = cY - (fm.ascent + fm.descent) / 2f;
            canvas.drawText(String.valueOf(OUTPUT_NUMS[i]), cX, textY, textPaint);
        }

        highlightView.setImageBitmap(bmp);
    }

    private String getMechanismCmd(int index, char mode) {
        int out = OUTPUT_NUMS[index];
        if (mode == 'O') {
            String def = getDefaultOpenCmd(out);
            String c = prefs.getString("bp_" + out + "_open", def);
            return (c == null || c.trim().isEmpty()) ? def : c.trim();
        } else if (mode == 'C') {
            String def = getDefaultCloseCmd(out);
            String c = prefs.getString("bp_" + out + "_close", def);
            return (c == null || c.trim().isEmpty()) ? def : c.trim();
        } else {
            String def = getDefaultToggleCmd(out);
            String c = prefs.getString("bp_" + out + "_cmd", def);
            return (c == null || c.trim().isEmpty()) ? def : c.trim();
        }
    }

    private void loadLabels() {
        for (int i = 0; i < SEQ_COUNT; i++) {
            if (seqButtons[i] != null) {
                int keyNum = i + 1;
                String name = prefs.getString("bseq" + keyNum + "_name", DEFAULT_SEQ_NAMES[i]);
                seqButtons[i].setText(name);
            }
        }
    }

    private void triggerSequence(int index) {
        int keyNum = index + 1;
        String name = prefs.getString("bseq" + keyNum + "_name", DEFAULT_SEQ_NAMES[index]);
        String cmd = prefs.getString("bseq" + keyNum + "_cmd", DEFAULT_SEQ_CMDS[index]);
        if (cmd == null || cmd.trim().isEmpty()) {
            Toast.makeText(this, "No sequence command assigned to " + name, Toast.LENGTH_SHORT).show();
            return;
        }
        final String cleanCmd = cmd.trim();
        new Thread(new Runnable() {
            @Override
            public void run() {
                stringParser.select_string(cleanCmd);
            }
        }).start();
        if (statusText != null) {
            statusText.setText("Sent Sequence: " + name + " (" + cleanCmd + ")");
        }
        Toast.makeText(this, name + " (" + cleanCmd + ")", Toast.LENGTH_SHORT).show();
    }

    private void executeSelectedAction(char mode) {
        List<String> commands = new ArrayList<>();
        StringBuilder names = new StringBuilder();
        int count = 0;

        for (int i = 0; i < COUNT; i++) {
            if (selected[i]) {
                count++;
                String cmd = getMechanismCmd(i, mode);
                commands.add(cmd);
                if (names.length() > 0) names.append(", ");
                names.append(OUTPUT_NUMS[i]);
            }
        }

        if (count == 0) {
            Toast.makeText(this, "No panels selected! Tap panels or numbers on the wireframe first.", Toast.LENGTH_SHORT).show();
            return;
        }

        sendChainedCommands(commands, mode, "Panels [" + names.toString() + "]");
    }

    private void executeRangeAction(int startIdx, int endIdx, char mode, String groupLabel) {
        List<String> commands = new ArrayList<>();
        for (int i = startIdx; i <= endIdx; i++) {
            commands.add(getMechanismCmd(i, mode));
        }
        sendChainedCommands(commands, mode, groupLabel);
    }

    private void sendChainedCommands(List<String> commands, char mode, String labelSummary) {
        // Concatenate commands in chunks of at most 6 per transmission with NO spaces or commas
        final List<String> chunks = new ArrayList<>();
        StringBuilder currentChunk = new StringBuilder();
        int countInChunk = 0;

        for (String cmd : commands) {
            currentChunk.append(cmd);
            countInChunk++;
            if (countInChunk >= 6) {
                chunks.add(currentChunk.toString());
                currentChunk = new StringBuilder();
                countInChunk = 0;
            }
        }
        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int c = 0; c < chunks.size(); c++) {
                    stringParser.select_string(chunks.get(c));
                    if (c < chunks.size() - 1) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ignored) {}
                    }
                }
            }
        }).start();

        String verb = (mode == 'O') ? "Opened" : (mode == 'C' ? "Closed" : "Toggled");
        StringBuilder fullSent = new StringBuilder();
        for (String c : chunks) {
            fullSent.append(c);
        }
        if (statusText != null) {
            statusText.setText(verb + " " + labelSummary + ": " + fullSent.toString());
        }
        Toast.makeText(this, verb + " " + labelSummary + " (" + fullSent.toString() + ")", Toast.LENGTH_SHORT).show();
    }

    private void syncFromDroidLink() {
        final String ip = prefs.getString("prefUserAddress", "192.168.4.1");
        if (syncButton != null) {
            syncButton.setEnabled(false);
            syncButton.setText("SYNC...");
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
                            syncButton.setText("SYNC");
                        }
                        loadLabels();
                        if (statusText != null) {
                            statusText.setText("Sync successful! Sequence buttons updated from DroidLink.");
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
                            syncButton.setText("SYNC");
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
