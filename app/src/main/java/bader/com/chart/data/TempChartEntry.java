
package bader.com.chart.data;




public class TempChartEntry {


    private String vADMCODE;

    private String dateLabel;

    private int x;
    private float y;


    public String getVADMCODE() {
        return vADMCODE;
    }

    public void setVADMCODE(String vADMCODE) {
        this.vADMCODE = vADMCODE;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public TempChartEntry(String vADMCODE, String dateLabel, int x, float y) {
        this.vADMCODE = vADMCODE;
        this.dateLabel = dateLabel;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TempChartEntry{" +
                "vADMCODE='" + vADMCODE + '\'' +
                ", dateLabel='" + dateLabel + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
