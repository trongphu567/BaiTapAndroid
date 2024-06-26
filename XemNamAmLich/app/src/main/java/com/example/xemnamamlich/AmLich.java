package com.example.xemnamamlich;

public class AmLich {
    private String[] can = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất",
     "Bính", "Đinh", "Mậu", "Kỷ"};
    private String[] chi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ",
    "Ngọ", "Mùi"};
    private int nam;
    public AmLich(int Nam) {
        this.nam = Nam;
    }
    public String getNamAL() {
        String can = this.can[nam % 10];
        String chi = this.chi[nam % 12];
        return can + " " + chi;
    }

}
