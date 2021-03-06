package com.webcon.sus.entity;

import android.support.annotation.NonNull;
import android.util.Log;

import com.webcon.wp.utils.WPApplication;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 报警消息指的是雷达的报警信息，不是摄像头的报警
 * @author m
 */
public class AlarmNode implements Cloneable,Serializable, Comparable<AlarmNode>{

    /** 报警编号 */
    private int id = -1;  // notificationID
    /** 站场Id便于查找 */
    private int stationId = -1;
    /** 设备ID //暂时没有 */
    private String mDeviceId;
    /** Date格式的时间，用于比较 */
    private Date dAlarmDate;
    /** 备注消息，保留 */
    private String mAlarmInfo;

    /** $报警设备昵称 */
    private String mDeviceName;
    /** $报警日期 */
    private String mAlarmDate;
    /** 报警内容 //暂时没有 */
    private String mContent;
    /** $位置信息类型 */
    private int positionType;
    /** $段位信息 positionType == 0时有效 */
    private int positionSegmentMsg = -1;
    /** $是否有图片 1:有图片  */
    private boolean mCapture;
    /** $是否已经处理      0：未处理  1：已经处理 */
    private short  isSolved;
    /** $处理者 */
    private String mSolver;
    /** $处理时间-本地生成 */
    private long mSolvedDate;
    /** $处理时间 */
    private String mSolvedDateStr;

    private DateFormat sdf;

    public AlarmNode(){
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    /* ----- */
    public void printOut(){
        Log.i("Alarm>>", "device:" + mDeviceName + ", id:" + getId() + ", data:" + mAlarmDate);
    }

    public int getId(){
        if(WPApplication.DEBUG){
            Log.i("AlarmNode", "id:" + id);
        }
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getStationId(){
        return stationId;
    }

    public void setStationId(int stationId){
        this.stationId = stationId;
    }

    /**
     * 请使用{@link AlarmNode#getDeviceName()}
     */
    @Deprecated
    public String getDeviceId() {
        return mDeviceId;
    }

    /**
     * 请使用{@link AlarmNode#setDeviceName(String)}
     */
    @Deprecated
    public void setDeviceId(String mDeviceId) {
        this.mDeviceId = mDeviceId;
        if(WPApplication.DEBUG_NO_ALARM){
            id = mDeviceId.hashCode() + mAlarmDate.hashCode();
        }
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String mDeviceName) {
        this.mDeviceName = mDeviceName;
    }

    public String getAlarmDate(){
        return mAlarmDate;
    }

    public void setAlarmDate(String mDate) {
        //---old------//给dAlarmDate赋值
        String newStr=mDate.replaceAll("_",":"); //服務器發送時以_爲分隔符 而不是：
        if(!("".equals(newStr))){
            try {
                dAlarmDate = sdf.parse(newStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        this.mAlarmDate = newStr;
//      this.mAlarmDate = mDate;
    }

    public Date getFormatDate(){
        return dAlarmDate;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getAlarmInfo() {
        return mAlarmInfo;
    }

    public void setAlarmInfo(String mAlarmInfo) {
        this.mAlarmInfo = mAlarmInfo;
    }

    public short getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(short isSolved) {
        this.isSolved = isSolved;
    }

    public String getSolver() {
        return mSolver;
    }

    public void setSolver(String mSolver) {
        this.mSolver = mSolver;
    }

    public long getSolvedDate() {
        return mSolvedDate;
    }

    public void setSolvedDate(long mSolvedDate) {
        this.mSolvedDate = mSolvedDate;
        this.mSolvedDateStr = sdf.format(new Date(mSolvedDate)); // *1000
    }

    public String getSolvedDateStr(){
        return mSolvedDateStr;
    }

    public void setSolvedDateStr(String mSolvedDateStr){
        this.mSolvedDateStr = mSolvedDateStr;
    }

    public int getPositionType() {
        return positionType;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public int getPositionSegmentMsg() {
        return positionSegmentMsg;
    }

    public void setPositionSegmentMsg(int positionSegmentMsg) {
        this.positionSegmentMsg = positionSegmentMsg;
    }

    public boolean isCapture() {
        return mCapture;
    }

    public void setCapture(boolean mCapture) {
        this.mCapture = mCapture;
    }


    //   日期/场站编号/设备号都相同 才认为是同一alarm
    public boolean isSameAlarm(AlarmNode alarm){
        boolean isEqualTime=this.getAlarmDate().equals(alarm.getAlarmDate());
        boolean isEqualStation=this.getStationId()==alarm.getStationId();
        boolean isEqualDevice=this.getDeviceName().equals(alarm.getDeviceName());
        return isEqualTime&isEqualStation&isEqualDevice;
    }


    /* ----------- */

    @Override
    public int compareTo(@NonNull AlarmNode another) {
        if(isSolved > another.getIsSolved()){
            return 1;
        }else if(isSolved < another.getIsSolved()) {
            return -1;
        }else{
            if(dAlarmDate.before(another.getFormatDate())){
                return 1;
            }else if(dAlarmDate.after(another.getFormatDate())){
                return -1;
            }else{
                return 0;
            }
        }
    }

}
