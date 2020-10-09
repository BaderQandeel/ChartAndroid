package bader.com.chart.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class TempData {

    @SerializedName("ADM_SIGNS")
    private List<ADMSIGN> mADMSIGNS;

    public List<ADMSIGN> getADMSIGNS() {
        return mADMSIGNS;
    }

    public void setADMSIGNS(List<ADMSIGN> aDMSIGNS) {
        mADMSIGNS = aDMSIGNS;
    }

    public List<TempChartEntry> getTempChartEntries() {
        /*util to sort by date and return list of entries*/
        Collections.sort(mADMSIGNS);
        List<TempChartEntry> entries = new ArrayList<TempChartEntry>();
        int x = 0;
        for (ADMSIGN admsign :
                mADMSIGNS) {
            entries.add(x, new TempChartEntry(admsign.getVADMDATE(),admsign.getDateLabel(),
                        x, Float.parseFloat(admsign.getTEMPC())));
            x++;
        }
        return entries;
    }

}
