package com.brocodz.devamathacmi.fuctions.readbooks;

public class ReadC {
    String pdf_name;
    String pdf_url;

    public ReadC() {
    }

    public ReadC(String pdf_name, String pdf_url) {
        this.pdf_name = pdf_name;
        this.pdf_url = pdf_url;
    }

    public String getPdf_name() {
        return pdf_name;
    }

    public void setPdf_name(String pdf_name) {
        this.pdf_name = pdf_name;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }
}
