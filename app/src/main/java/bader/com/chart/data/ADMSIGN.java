
package bader.com.chart.data;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("unused")
public class ADMSIGN implements Comparable<ADMSIGN>{

    @SerializedName("CVP")
    private Object mCVP;
    @SerializedName("DOC_NAME")
    private String mDOCNAME;
    @SerializedName("EtCO2")
    private Object mEtCO2;
    @SerializedName("HeartRate")
    private String mHeartRate;
    @SerializedName("IBP")
    private String mIBP;
    @SerializedName("NIBP")
    private Object mNIBP;
    @SerializedName("RR_MIN")
    private String mRRMIN;
    @SerializedName("SAO2")
    private String mSAO2;
    @SerializedName("TEMP_C")
    private String mTEMPC;
    @SerializedName("UrineOut")
    private Object mUrineOut;
    @SerializedName("V_ADM_ADMISSION_CD")
    private String mVADMADMISSIONCD;
    @SerializedName("V_ADM_CODE")
    private String mVADMCODE;
    @SerializedName("V_ADM_CREATED_BY_CD")
    private String mVADMCREATEDBYCD;
    @SerializedName("V_ADM_DATE")
    private String mVADMDATE;
    @SerializedName("V_ADM_PATREC_CD")
    private String mVADMPATRECCD;
    @SerializedName("VITAL_NOTE")
    private Object mVITALNOTE;

    public Object getCVP() {
        return mCVP;
    }

    public void setCVP(Object cVP) {
        mCVP = cVP;
    }

    public String getDOCNAME() {
        return mDOCNAME;
    }

    public void setDOCNAME(String dOCNAME) {
        mDOCNAME = dOCNAME;
    }

    public Object getEtCO2() {
        return mEtCO2;
    }

    public void setEtCO2(Object etCO2) {
        mEtCO2 = etCO2;
    }

    public String getHeartRate() {
        return mHeartRate;
    }

    public void setHeartRate(String heartRate) {
        mHeartRate = heartRate;
    }

    public String getIBP() {
        return mIBP;
    }

    public void setIBP(String iBP) {
        mIBP = iBP;
    }

    public Object getNIBP() {
        return mNIBP;
    }

    public void setNIBP(Object nIBP) {
        mNIBP = nIBP;
    }

    public String getRRMIN() {
        return mRRMIN;
    }

    public void setRRMIN(String rRMIN) {
        mRRMIN = rRMIN;
    }

    public String getSAO2() {
        return mSAO2;
    }

    public void setSAO2(String sAO2) {
        mSAO2 = sAO2;
    }

    public String getTEMPC() {
        return mTEMPC;
    }

    public void setTEMPC(String tEMPC) {
        mTEMPC = tEMPC;
    }

    public Object getUrineOut() {
        return mUrineOut;
    }

    public void setUrineOut(Object urineOut) {
        mUrineOut = urineOut;
    }

    public String getVADMADMISSIONCD() {
        return mVADMADMISSIONCD;
    }

    public void setVADMADMISSIONCD(String vADMADMISSIONCD) {
        mVADMADMISSIONCD = vADMADMISSIONCD;
    }

    public String getVADMCODE() {
        return mVADMCODE;
    }

    public void setVADMCODE(String vADMCODE) {
        mVADMCODE = vADMCODE;
    }

    public String getVADMCREATEDBYCD() {
        return mVADMCREATEDBYCD;
    }

    public void setVADMCREATEDBYCD(String vADMCREATEDBYCD) {
        mVADMCREATEDBYCD = vADMCREATEDBYCD;
    }

    public String getVADMDATE() {
        return mVADMDATE;
    }

    public void setVADMDATE(String vADMDATE) {
        mVADMDATE = vADMDATE;
    }

    public String getVADMPATRECCD() {
        return mVADMPATRECCD;
    }

    public void setVADMPATRECCD(String vADMPATRECCD) {
        mVADMPATRECCD = vADMPATRECCD;
    }

    public Object getVITALNOTE() {
        return mVITALNOTE;
    }

    public void setVITALNOTE(Object vITALNOTE) {
        mVITALNOTE = vITALNOTE;
    }

    @Override
    public String toString() {
        return "ADMSIGN{" +
                "mCVP=" + mCVP +
                ", mDOCNAME='" + mDOCNAME + '\'' +
                ", mEtCO2=" + mEtCO2 +
                ", mHeartRate='" + mHeartRate + '\'' +
                ", mIBP='" + mIBP + '\'' +
                ", mNIBP=" + mNIBP +
                ", mRRMIN='" + mRRMIN + '\'' +
                ", mSAO2='" + mSAO2 + '\'' +
                ", mTEMPC='" + mTEMPC + '\'' +
                ", mUrineOut=" + mUrineOut +
                ", mVADMADMISSIONCD='" + mVADMADMISSIONCD + '\'' +
                ", mVADMCODE='" + mVADMCODE + '\'' +
                ", mVADMCREATEDBYCD='" + mVADMCREATEDBYCD + '\'' +
                ", mVADMDATE='" + mVADMDATE + '\'' +
                ", mVADMPATRECCD='" + mVADMPATRECCD + '\'' +
                ", mVITALNOTE=" + mVITALNOTE +
                '}';
    }


    @Override
    public int compareTo(ADMSIGN admsign) {
        if (getVADMDATE() == null || admsign.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(admsign.getDate());
    }

    public Date getDate() {
        Date date=null;
        try {
            String [] dateParts = this.mVADMDATE.split("/");
            String day = dateParts[0];
            String month = dateParts[1];
            String year = dateParts[2].substring(0,4);
            String hours_minutes = dateParts[2].substring(dateParts[2].length() - 5).trim();
            String strdate = day+"/"+month+"/"+year+" "+hours_minutes;
            date=new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).parse(strdate);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Data",e.getMessage());
        }
        return date;
    }
    public String getDateLabel(){
        String [] dateParts = this.mVADMDATE.split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2].substring(0,4);
        String hours_minutes = dateParts[2].substring(dateParts[2].length() - 5).trim();
        String strdate = day+"/"+month+"/"+year+"\n"+hours_minutes;
        return strdate;
    }
}
