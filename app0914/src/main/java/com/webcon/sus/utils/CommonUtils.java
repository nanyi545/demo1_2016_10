package com.webcon.sus.utils;

import android.util.Log;

import com.webcon.sus.eventObjects.ErrorEvent;
import com.webcon.wp.utils.WPApplication;

import java.text.MessageFormat;

import de.greenrobot.event.EventBus;

/**其他工具
 * @author m
 */
public class CommonUtils {
    public static String ORIGINAL_ALRAM                   = "！{0}条警报";
    public static String SEGMENT_CONTENT                  = "段位:{0}";
    public static String ANGLE_CONTENT                    = "角度:{0}";
    public static final String ORIGINAL_NO_ALARM          = "无警报";
    public static final String ORIGINAL_DEFENCE_OPENED    = "已开启布防";
    public static final String ORIGINAL_DEFENCE_CLOSED    = "已关闭布防";
    public static final String ORIGINAL_ONLINE            = "当前在线 / ";
    public static final String ORIGINAL_OFFLINE           = "当前离线";

    public static String placeHolderSwitch(int num){
        return MessageFormat.format(ORIGINAL_ALRAM, num);
    }

    public static String segmentContentSwitch(int num){
        return MessageFormat.format(SEGMENT_CONTENT, num);
    }

    public static String angleContentSwitch(int num){
        return MessageFormat.format(ANGLE_CONTENT, num);
    }

    public static int getStamp(){
        return ++WPApplication.getInstance().alarmSeqSeed;
    }

    public static void checkByteBinary(byte[] data){
        StringBuilder sb = new StringBuilder();
        for(byte b : data){
            sb.append(((int)b & 0x080)>>7);
            sb.append(((int)b & 0x040)>>6);
            sb.append(((int)b & 0x020)>>5);
            sb.append(((int)b & 0x010)>>4);
            sb.append(((int)b & 0x08)>>3);
            sb.append(((int)b & 0x04)>>2);
            sb.append(((int)b & 0x02)>>1);
            sb.append((int)b & 0x01);
            Log.i("T", "byte:" + sb.toString());
            sb.delete(0, sb.length());
        }
    }

    /**
     * 分成两个short
     */
    public static int[] divideIntData(int req){
        int[] a = new int[2];
        a[1] = req & 0xFF;
        Log.i("BBB","----A1:"+a[1]);
        a[0] = req >> 8;
        Log.i("BBB","----A0:"+a[0]);
        return a;
    }

    /**
     * 合并两个int
     */
    public static int mergeIntData(int command, int state){
//        return command<<8 + state;  // 计算优先级出错，先+后位移--->应该先位移
        return ((command<<8) + state);
    }

    /**
     * 显示错误
     * @param pdu 由服务端返回的pdu类型
     * @param code 错误码（自定义）
     */
    public static void sendErrorEvent(short pdu, int code){
        if(WPApplication.DEBUG){
            Log.e("Error", "-------error-------");
        }
        String description = null;
        switch(pdu){
            /* -------- 回复解析错误 --------- */
            case SUConstant.SUB_RSP_ALARM_IMAGE:
                description = "请求抓图失败";
                break;
            case SUConstant.PARSE_RSP_ERROR_WRONG_DATA:
                description = "错误的解析数据";
                break;
            case SUConstant.PARSE_RSP_ERROR_COMMUNICATION:
                description = "请求失败：" + code;
                break;
            case SUConstant.SUB_DB_RSP_MODIFY_UN:
                description = "请求修改用户昵称失败";
                break;
            case SUConstant.SUB_DB_RSP_MODIFY_PW:
                if(code == -4005){
                    description = "旧密码错误";
                }else if(code == -4004){
                    description = "账号不存在";
                }else{
                    description = "请求修改用户密码失败";
                }
                break;
            // 请求停止预览 回复
            case SUConstant.SUB_RSP_STOP_PREVIEW:
                description = "请求停止视频预览失败";
                break;
            // 请求云台控制 回复
            case SUConstant.SUB_RSP_PTZ:
                description = "请求云台控制失败";
                break;
            // 请求开启布防 回复
            case SUConstant.SUB_RSP_OPEN_DEFENCE:
                description = "请求开启布防失败";
                break;
            // 请求停止布防 回复
            case SUConstant.SUB_RSP_STOP_DEFENCE:
                description = "请求停止布防失败";
                break;
            // 请求播放报警语音 回复
            case SUConstant.SUB_RSP_ALARM_PLAYER:
                description = "请求播放报警语音失败";
                break;
            // 报警处理请求 回复
            case SUConstant.SUB_RSP_PROCESS_ALARM:
                if(code == -1){
                    description = "请求处理报警消息: 连接错误";
                }else if(code == -2){
                    description = "请求处理报警消息: 已被处理";
                }else{
                    description = "请求处理报警消息失败";
                }
                break;
            // 请求开启预览 回复
            case SUConstant.SUB_RSP_START_PREVIEW:
                if (code==SUConstant.ERROR_RETURN_OVERTIME) {
                    EventBus.getDefault().post(new ErrorEvent(ErrorEvent.ERROR_EVENT_TYPE_CAPTURE_OVERTIME));
                    return;
                } else
                description = "请求视频预览失败";
                return;  // break;  // 忽略 其它失败情况  --请求视频预览失败
            default:
                description = "未知 请求错误";
                break;
        }
        // 请求处理
        ErrorEvent event = new ErrorEvent(ErrorEvent.ERROR_EVENT_TYPE_PARSE_SUB_PDU);
        event.description = description;
        EventBus.getDefault().post(event);
    }


}
