package com.mrwhoknows.gocorona;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CoronaData {

    @SerializedName("success")
    private boolean success;
    @SerializedName("lastRefreshed")
    private String lastRefreshed;
    @SerializedName("data")
    private Data data;

    public static class Data{
        @SerializedName("summary")
        private Summary summary;
        @SerializedName("regional")
        private ArrayList<Regional> regional = new ArrayList<>();

        public static class Summary {
            @SerializedName("total")
            int totalCases;
            @SerializedName("confirmedCasesIndian")
            int indianCases;
            @SerializedName("confirmedCasesForeign")
            int foreignCases;
            @SerializedName("discharged")
            int cured;
            @SerializedName("deaths")
            int deaths;

            public int getTotalCases() {
                return totalCases;
            }

            public int getIndianCases() {
                return indianCases;
            }

            public int getForeignCases() {
                return foreignCases;
            }

            public int getCured() {
                return cured;
            }

            public int getDeaths() {
                return deaths;
            }
        }

        public static class Regional {

            @SerializedName("loc")
            private String loc;
            @SerializedName("confirmedCasesIndian")
            private int indianCases;
            @SerializedName("confirmedCasesForeign")
            private int foreignCases;
            @SerializedName("discharged")
            private int cured;
            @SerializedName("deaths")
            private int deaths;

            public Regional(String loc, int indianCases, int foreignCases, int cured, int deaths) {
                this.loc = loc;
                this.indianCases = indianCases;
                this.foreignCases = foreignCases;
                this.cured = cured;
                this.deaths = deaths;
            }

            public String getLoc() {
                return loc;
            }

            public int getIndianCases() {
                return indianCases;
            }

            public int getForeignCases() {
                return foreignCases;
            }

            public int getCured() {
                return cured;
            }

            public int getDeaths() {
                return deaths;
            }
        }

        public Summary getSummary() {
            return summary;
        }

        public ArrayList<Regional> getRegional() {
            return regional;
        }
    }


    public boolean isSuccess() {
        return success;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public Data getData() {
        return data;
    }
}
