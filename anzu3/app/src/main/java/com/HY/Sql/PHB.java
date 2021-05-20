package com.HY.Sql;


    public class PHB {

        private int time;
        private int mc;
        private String day;
        private String busu;
        private String mosi;

        public PHB(){

        }

        public PHB(int time, int mc, String day, String busu, String mosi) {
            this.time = time;
            this.mc = mc;
            this.day = day;
            this.busu = busu;
            this.mosi = mosi;
        }

        @Override
        public String toString() {
            return "PHB{" +
                    "time=" + time +
                    ", mc=" + mc +
                    ", day='" + day + '\'' +
                    ", busu='" + busu + '\'' +
                    ", mosi='" + mosi + '\'' +
                    '}';
        }

        public String getMosi() {
            return mosi;
        }

        public void setMosi(String mosi) {
            this.mosi = mosi;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getMc() {
            return mc;
        }

        public void setMc(int mc) {
            this.mc = mc;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getBusu() {
            return busu;
        }

        public void setBusu(String busu) {
            this.busu = busu;
        }
    }
