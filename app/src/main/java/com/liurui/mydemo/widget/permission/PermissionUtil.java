package com.liurui.mydemo.widget.permission;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.AppOpsManagerCompat;
import android.text.TextUtils;
import android.util.Log;

import com.liurui.common.utils.ApplicationUtil;
import com.liurui.mydemo.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.List;

/**
 * Created by LiuRui on 2019/1/8
 */
public class PermissionUtil {
    private static final String TAG = "PermissionUtil";

    /**
     * 查询小米手机的权限状态
     * @param context
     * @param permissionName
     * @return
     */
    public static boolean isPermissionGrantedInMIUI(Context context, String permissionName) {
        final String opName = AppOpsManagerCompat.permissionToOp(permissionName);
        if (opName == null) {
            return true;
        } else {
            int mode = AppOpsManagerCompat.noteOp(context,
                    opName, Process.myUid(), context.getPackageName());
            return mode == AppOpsManagerCompat.MODE_ALLOWED;
        }
    }

    public static boolean hasRecordPermission() {
        int minBufferSize = AudioRecord.getMinBufferSize(8000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        int bufferSizeInBytes = 640;
        byte[] audioData = new byte[bufferSizeInBytes];
        int readSize = 0;
        AudioRecord audioRecord = null;
        try {
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, 8000,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, minBufferSize);
            // 开始录音
            audioRecord.startRecording();
        } catch (Exception e) {
            //可能情况一
            if (audioRecord != null) {
                audioRecord.release();
                audioRecord = null;
            }
            return false;
        }
        // 检测是否在录音中,6.0以下会返回此状态
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            //可能情况二
            if (audioRecord != null) {
                audioRecord.stop();
                audioRecord.release();
                audioRecord = null;
            }
            return false;
        } else {// 正在录音
            readSize = audioRecord.read(audioData, 0, bufferSizeInBytes);
            // 检测是否可以获取录音结果
            if (readSize <= 0) {
                //可能情况三
                if (audioRecord != null) {
                    audioRecord.stop();
                    audioRecord.release();
                    audioRecord = null;
                }
                Log.e(TAG, "没有获取到录音数据，无录音权限");
                return false;
            } else {
                //有权限，正常启动录音并有数据
                if (audioRecord != null) {
                    audioRecord.stop();
                    audioRecord.release();
                    audioRecord = null;
                }
                return true;
            }
        }
    }

    public static void requestPermission(final Context context, final OnCheckPermissionListener onCheckPermissionListener, String... permissions){
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (onCheckPermissionListener != null){
                            onCheckPermissionListener.permissionGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(ApplicationUtil.getContext(), permissions)) {
                            showSettingDialog(context, onCheckPermissionListener, permissions);
                        }
                    }
                })
                .start();
    }

    private static void showSettingDialog(final Context context , final OnCheckPermissionListener onCheckPermissionListener, final List<String> permissions){
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_rationale, TextUtils.join("\n", permissionNames));
//        final CommonDialog commonDialog = new CommonDialog(context);
//        commonDialog.setTitle(message)
//                .setCancel(true)
//                .setTouchOutSide(true)
//                .setTwoBtnClickListener(new CommonDialog.TwoBtnClickListener() {
//                    @Override
//                    public void onCancelClick() {
//                        if (onCheckPermissionListener != null){
//                            onCheckPermissionListener.permissionDenied();
//                        }
//                        commonDialog.dismiss();
//                    }
//
//                    @Override
//                    public void onEnsureClick() {
//                        setPermission(context);
//                        commonDialog.dismiss();
//                    }
//                });
//        commonDialog.show();
    }

    /**
     * Set permissions.
     */
    private static void setPermission(Context context) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {

                    }
                })
                .start();
    }

    public interface OnCheckPermissionListener{
        void permissionGranted();
        void permissionDenied();
    }


}
